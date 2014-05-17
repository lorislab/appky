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
package org.lorislab.appky.application.wrapper.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.appky.application.criteria.ApplicationSearchCriteria;
import org.lorislab.appky.application.criteria.GroupSearchCriteria;
import org.lorislab.appky.application.criteria.PlatformSearchCriteria;
import org.lorislab.appky.application.criteria.VersionSearchCriteria;
import org.lorislab.appky.application.ejb.ApplicationServiceLocal;
import org.lorislab.appky.application.ejb.GroupServiceLocal;
import org.lorislab.appky.application.ejb.PlatformServiceLocal;
import org.lorislab.appky.application.ejb.VersionServiceLocal;
import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Group;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.application.wrapper.model.ApplicationWrapper;
import org.lorislab.appky.application.wrapper.model.GroupWrapper;
import org.lorislab.appky.application.wrapper.model.PlatformWrapper;
import org.lorislab.appky.application.criteria.UserApplicationSearchCriteria;
import org.lorislab.appky.application.ejb.UserApplicationServiceLocal;
import org.lorislab.appky.application.ejb.UserProfileServiceLocal;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.enums.UserApplicationStatus;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractServiceBean;

/**
 * The application wrapper service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(ApplicationWrapperServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ApplicationWrapperServiceBean extends AbstractServiceBean implements ApplicationWrapperServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -3733667481019376254L;
    /**
     * The platform service.
     */
    @EJB
    private PlatformServiceLocal platformService;
    /**
     * The version service.
     */
    @EJB
    private VersionServiceLocal versionService;
    /**
     * The user application service.
     */
    @EJB
    private UserApplicationServiceLocal userApplicationService;
    /**
     * The group service.
     */
    @EJB
    private GroupServiceLocal groupService;
    /**
     * The application service.
     */
    @EJB
    private ApplicationServiceLocal applicationService;
    
    @EJB
    private UserProfileServiceLocal userProfileService;

    /**
     * {@inheritDoc}
     */
    @Override
    public PlatformWrapper loadPlatformWrapperByPlatformGuid(String guid, String userGuid, Locale userLocale, Locale defaultUserLocale) throws ServiceException {
        // load release version
        Version version = null;
        VersionSearchCriteria versionCriteria = new VersionSearchCriteria();
        versionCriteria.setDeleted(false);
        versionCriteria.setReleased(true);
        versionCriteria.setPlatformGuid(guid);
        versionCriteria.setUserLocale(userLocale);
        versionCriteria.setDefaultUserLocale(defaultUserLocale);
        List<Version> versions = versionService.findVersionsBySearchCriteria(versionCriteria);
        if (versions != null && !versions.isEmpty()) {
            version = versions.get(0);
        }

        // Load the platform
        PlatformSearchCriteria platformCriteria = new PlatformSearchCriteria();
        platformCriteria.setGuid(guid);
        platformCriteria.setUserLocale(userLocale);
        platformCriteria.setDefaultUserLocale(defaultUserLocale);
        Platform platform = platformService.loadFullPlatformByGuid(guid);

        // Load the user application
        UserApplication userApp = null;
        UserApplicationSearchCriteria criteria = new UserApplicationSearchCriteria();
        criteria.setUserGuid(userGuid);
        criteria.setPlatformGuid(guid);
        criteria.setStatus(UserApplicationStatus.INSTALLED);
        List<UserApplication> data = userApplicationService.findUserApplicationsByCriteria(criteria);
        if (data != null && !data.isEmpty()) {
            userApp = data.get(0);
        }

        // load user current version
        Version userVersion = null;
        if (userApp != null) {

            // check if the current version is the user version
            if (version != null) {
                if (userApp.getVersionGuid().equals(version.getGuid())) {
                    userVersion = version;
                }
            }

            // if no version set search for the user version
            if (userVersion == null) {
                VersionSearchCriteria userVersionCriteria = new VersionSearchCriteria();
                userVersionCriteria.setGuid(userApp.getVersionGuid());
                versionCriteria.setDeleted(false);
                userVersionCriteria.setUserLocale(userLocale);
                userVersionCriteria.setDefaultUserLocale(defaultUserLocale);
                List<Version> tmp = versionService.findVersionsBySearchCriteria(userVersionCriteria);
                if (tmp != null && !tmp.isEmpty()) {
                    userVersion = tmp.get(0);
                }
            }
        }

        // load modification user
        UserProfile modificationUser = null;
        if (version != null) {
            modificationUser = userProfileService.loadUserProfile(version.getModificationUser());
        }
        
        // modificationUser: if (version == null) modificationUser = null
        // userApp: could be null if user has no installed/updated version
        // userVersion: could be null if user has no install version
        // version: could be null if there is no release version for the platform        
        // create wrapper
        PlatformWrapper result = new PlatformWrapper(userGuid, modificationUser, platform, version, userVersion, userApp, userLocale);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GroupWrapper> findGroupsBySearchCriteria(GroupSearchCriteria searchCriteria) throws ServiceException {
        List<GroupWrapper> result = new ArrayList<>();
        List<Group> tmp = groupService.findGroupsBySearchCriteria(searchCriteria);
        if (tmp != null && !tmp.isEmpty()) {
            Locale userLocale = searchCriteria.getUserLocale();
            for (Group group : tmp) {
                result.add(new GroupWrapper(group, userLocale));
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ApplicationWrapper> findApplicationsBySearchCriteria(ApplicationSearchCriteria searchCriteria) throws ServiceException {
        List<ApplicationWrapper> result = new ArrayList<>();
        List<Application> tmp = applicationService.findApplicationsBySearchCriteria(searchCriteria);
        if (tmp != null && !tmp.isEmpty()) {
            Locale userLocale = searchCriteria.getUserLocale();
            for (Application app : tmp) {
                result.add(new ApplicationWrapper(app, userLocale));
            }
        }
        return result;
    }
}
