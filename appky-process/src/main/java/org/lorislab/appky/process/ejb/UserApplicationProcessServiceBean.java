/*
 * Copyright 2012 Andrej Petras <andrej@ajka-andrej.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.appky.process.ejb;


import org.lorislab.appky.process.config.EmailConfiguration;
import org.lorislab.appky.process.config.ProcessApplicationConfiguration;
import org.lorislab.appky.process.config.ServerConfiguration;
import org.lorislab.appky.process.config.UserProfileConfiguration;
import org.lorislab.appky.process.factory.ApplicationPackageFactory;
import org.lorislab.appky.process.model.ApplicationPackage;

import org.lorislab.appky.process.tracking.factory.StoreTrackingDataFactory;
import org.lorislab.appky.process.tracking.model.AbstractStoreTrackingData;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.appky.application.criteria.UserApplicationSearchCriteria;
import org.lorislab.appky.application.criteria.UserProfileSearchCriteria;
import org.lorislab.appky.application.criteria.VersionSearchCriteria;
import org.lorislab.appky.application.ejb.DocumentServiceLocal;
import org.lorislab.appky.application.ejb.UserApplicationServiceLocal;
import org.lorislab.appky.application.ejb.UserProfileServiceLocal;
import org.lorislab.appky.application.ejb.VersionServiceLocal;
import org.lorislab.appky.application.factory.UserObjectFactory;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.model.ManifestIOS;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.application.model.enums.PlatformType;
import org.lorislab.appky.application.model.enums.UserApplicationStatus;
import org.lorislab.appky.application.tmpresource.ejb.TemporaryResourceServiceLocal;
import org.lorislab.appky.application.tmpresource.factory.TemporaryResourceFactory;
import org.lorislab.appky.application.tmpresource.model.TemporaryResource;
import org.lorislab.appky.application.util.PListUtil;
import org.lorislab.appky.application.wrapper.model.PlatformWrapper;
import org.lorislab.appky.application.wrapper.model.enums.UserProcessAction;
import static org.lorislab.appky.application.wrapper.model.enums.UserProcessAction.INSTALL;
import static org.lorislab.appky.application.wrapper.model.enums.UserProcessAction.REINSTALL;
import static org.lorislab.appky.application.wrapper.model.enums.UserProcessAction.UPDATE;
import org.lorislab.appky.config.ejb.ConfigurationServiceLocal;
import org.lorislab.appky.email.ejb.MailServiceLocal;
import org.lorislab.appky.email.factory.EmailFactory;
import org.lorislab.appky.email.model.Email;
import org.lorislab.appky.tracking.ejb.TrackingServiceLocal;
import org.lorislab.jel.base.util.DateUtil;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractServiceBean;
import org.lorislab.jel.jpa.model.util.PersistentUtil;


/**
 * The user application process service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(UserApplicationProcessServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserApplicationProcessServiceBean extends AbstractServiceBean implements UserApplicationProcessServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -155323751811352127L;

    /**
     * The user application service.
     */
    @EJB
    private UserApplicationServiceLocal userApplicationService;
    /**
     * The document service.
     */
    @EJB
    private DocumentServiceLocal documentService;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationServiceLocal configService;
    /**
     * The temporary service.
     */
    @EJB
    private TemporaryResourceServiceLocal tmpService;
    /**
     * The version service.
     */
    @EJB
    private VersionServiceLocal versionService;

    /**
     * The tracking service.
     */
    @EJB
    private TrackingServiceLocal trackingService;

    @EJB
    private NotificationServiceLocal notificationService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ApplicationPackage processApplication(PlatformWrapper wrapper) throws ServiceException {
        ApplicationPackage result = null;
        Object tmp = null;
        if (wrapper != null) {

            UserApplication userApp = findUserApplication(wrapper);

            UserProcessAction action = wrapper.getProcessAction();
            PlatformType type = wrapper.getPlatform().getType();
                      
            switch (action) {
                // install application
                case INSTALL:
                    if (userApp == null || UserApplicationStatus.REMOVED.equals(userApp.getStatus())) {

                        if (userApp == null) {
                            String platformGuid = PersistentUtil.getGuid(wrapper.getPlatform());
                            String applicationGuid = PersistentUtil.getGuid(wrapper.getApplication()); 
                            String versionGuid = PersistentUtil.getGuid(wrapper.getModel());
                            userApp = UserObjectFactory.createUserApplication(wrapper.getUserGuid(), applicationGuid, platformGuid, versionGuid);
                        }

                        userApp.setVersionGuid(wrapper.getModel().getGuid());
                        userApp.setStatus(UserApplicationStatus.INSTALLED);

                        if (PlatformType.IOS.equals(type)) {
                            ProcessApplicationConfiguration config = configService.loadConfiguration(ProcessApplicationConfiguration.class);
                            ServerConfiguration serverConfig = configService.loadConfiguration(ServerConfiguration.class);
                            
                            Date validateTo = DateUtil.add(Calendar.MINUTE, config.getResourceValidateTo());
                            TemporaryResource tmpResource = TemporaryResourceFactory.create(wrapper.getUserGuid(), validateTo);
                            tmp = getPListData(serverConfig.getServerURL(), tmpResource, wrapper);
                            tmpService.saveTemporaryResource(tmpResource);
                        } else {
                            tmp = getDocumentData(wrapper.getModel());
                        }
                    }

                    break;
                // reinstall application
                case REINSTALL:
                    if (userApp != null) {
                        if (UserApplicationStatus.INSTALLED.equals(userApp.getStatus())
                                && wrapper.getModel().equals(wrapper.getUserVersion())) {

                            if (PlatformType.IOS.equals(type)) {
                                ProcessApplicationConfiguration config = configService.loadConfiguration(ProcessApplicationConfiguration.class);
                                ServerConfiguration serverConfig = configService.loadConfiguration(ServerConfiguration.class);
                                
                                Date validateTo = DateUtil.add(Calendar.MINUTE, config.getResourceValidateTo());
                                TemporaryResource tmpResource = TemporaryResourceFactory.create(wrapper.getUserGuid(), validateTo);
                                tmp = getPListData(serverConfig.getServerURL(), tmpResource, wrapper);
                                tmpService.saveTemporaryResource(tmpResource);
                            } else {
                                tmp = getDocumentData(wrapper.getModel());
                            }
                        }
                    }


                    break;
                // update application
                case UPDATE:
                    if (userApp != null) {
                        if (UserApplicationStatus.INSTALLED.equals(userApp.getStatus())
                                && !wrapper.getModel().equals(wrapper.getUserVersion())) {

                            if (PlatformType.IOS.equals(type)) {
                                ProcessApplicationConfiguration config = configService.loadConfiguration(ProcessApplicationConfiguration.class);
                                ServerConfiguration serverConfig = configService.loadConfiguration(ServerConfiguration.class);
                                
                                Date validateTo = DateUtil.add(Calendar.MINUTE, config.getResourceValidateTo());
                                TemporaryResource tmpResource = TemporaryResourceFactory.create(wrapper.getUserGuid(), validateTo);
                                tmp = getPListData(serverConfig.getServerURL(), tmpResource, wrapper);
                                tmpService.saveTemporaryResource(tmpResource);
                            } else {
                                tmp = getDocumentData(wrapper.getModel());
                            }
                            // update version number in the user application status
                            userApp.setVersionGuid(wrapper.getModel().getGuid());
                        }
                    }
            }
            // check result
            if (tmp != null) {
                // save user application status
                userApp = userApplicationService.saveUserApplication(userApp);
                // create application package
                result = ApplicationPackageFactory.createApplicationPackage(userApp, action, tmp, type);    
                // save tracking data
                AbstractStoreTrackingData activity = StoreTrackingDataFactory.createApplicationProcessActivity(action, userApp);
                trackingService.saveTrackingData(activity);
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UserApplication uninstallApplication(PlatformWrapper wrapper) throws ServiceException {
        UserApplication result = null;
        UserApplication userApp = findUserApplication(wrapper);
        if (userApp != null) {
            if (UserApplicationStatus.INSTALLED.equals(userApp.getStatus())) {
                userApp.setStatus(UserApplicationStatus.REMOVED);
                userApp = userApplicationService.saveUserApplication(userApp);
                result = userApp;
                // save tracking data
                AbstractStoreTrackingData activity = StoreTrackingDataFactory.createApplicationProcessActivity(UserProcessAction.REMOVE, userApp);
                trackingService.saveTrackingData(activity);                
            }
        }
        return result;
    }

    /**
     * Gets the document content.
     *
     * @param version the version.
     * @return the document content.
     * @throws ServiceException if the method fails.
     */
    private byte[] getDocumentData(Version version) throws ServiceException {
        byte[] result = null;
        String documentGuid = version.getData().getGuid();
        Document document = documentService.loadDocumentFullByGuid(documentGuid);
        if (document != null && document.getContent().getBytes() != null) {
            result = document.getContent().getBytes();
        }
        return result;
    }

    /**
     * Finds the user application by the platform wrapper.
     *
     * @param wrapper the platform wrapper.
     * @return the user application.
     * @throws ServiceException if the method fails.
     */
    private UserApplication findUserApplication(PlatformWrapper wrapper) throws ServiceException {
        UserApplication result = null;

        String platformGuid = PersistentUtil.getGuid(wrapper.getPlatform());

        UserApplicationSearchCriteria criteria = new UserApplicationSearchCriteria();
        criteria.setUserGuid(wrapper.getUserGuid());
        criteria.setPlatformGuid(platformGuid);
        List<UserApplication> data = userApplicationService.findUserApplicationsByCriteria(criteria);
        if (data != null && !data.isEmpty()) {
            result = data.get(0);
        }
        return result;
    }

    /**
     * Gets the P-List data for the iOS platform.
     *
     * @param server the server.
     * @param tmpResource the temporary resource.
     * @param wrapper the platform wrapper.
     * @return the P-List for the iOs platform.
     * @throws ServiceException if the method fails.
     */
    private String getPListData(String server, TemporaryResource tmpResource, PlatformWrapper wrapper) throws ServiceException {
        String packageGuid = wrapper.getModel().getData().getGuid();
        Platform platform = wrapper.getPlatform();
        ManifestIOS manifest = platform.getManifest();
        String result = PListUtil.convertToPList(server, packageGuid, manifest, tmpResource);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Version unreleaseVersion(Version version) throws ServiceException {
        version.setReleased(false);
        Version result = versionService.saveVersion(version);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Version releaseVersion(Version version) throws ServiceException {

        // 1. disable current version
        String platformGuid = version.getPlatform().getGuid();
        Version activeVersion = null;

        VersionSearchCriteria criteria = new VersionSearchCriteria();
        criteria.setDeleted(false);
        criteria.setReleased(true);
        criteria.setPlatformGuid(platformGuid);
        List<Version> versions = versionService.findVersionsBySearchCriteria(criteria);
        if (versions != null && !versions.isEmpty()) {
            activeVersion = versions.get(0);
        }

        if (activeVersion != null) {
            activeVersion.setReleased(false);
            versionService.saveVersion(activeVersion);
        }

        // 2. active new version
        version.setReleased(true);
        version.setReleasedDate(new Date());
        Version result = versionService.saveVersion(version);

        // 3. (async) send emails to all active user for this platform and version
        notificationService.sendReleaseVersionNotification(version);

        return result;
    }

  
   
}
