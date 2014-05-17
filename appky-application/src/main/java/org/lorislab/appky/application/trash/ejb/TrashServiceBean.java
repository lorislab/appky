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
package org.lorislab.appky.application.trash.ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.appky.application.criteria.ApplicationSearchCriteria;
import org.lorislab.appky.application.criteria.VersionSearchCriteria;
import org.lorislab.appky.application.ejb.ApplicationServiceLocal;
import org.lorislab.appky.application.ejb.GroupServiceLocal;
import org.lorislab.appky.application.ejb.VersionServiceLocal;
import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Group;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.application.ejb.RoleServiceLocal;
import org.lorislab.appky.application.ejb.UserProfileServiceLocal;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractServiceBean;

/**
 * The trash service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(TrashServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NEVER)
public class TrashServiceBean extends AbstractServiceBean implements TrashServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -826783139642053857L;
    /**
     * The group service.
     */
    @EJB
    private GroupServiceLocal groupService;
    /**
     * The application service.
     */
    @EJB
    private ApplicationServiceLocal appService;
    /**
     * The version service.
     */
    @EJB
    private VersionServiceLocal versionService;
    /**
     * The user profile service.
     */
    @EJB
    private UserProfileServiceLocal userProfileService;
    /**
     * The role service.
     */
    @EJB
    private RoleServiceLocal roleService;

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void trashGroup(Group group) throws ServiceException {
        
        // group
        if (group != null) {
            group.setDeleted(true);
            groupService.saveGroup(group);

            // applications
            ApplicationSearchCriteria criteria = new ApplicationSearchCriteria();
            criteria.setGroupGuid(group.getGuid());
            criteria.setDeleted(false);
            List<Application> applicaitons = appService.findApplicationsBySearchCriteria(criteria);
            if (applicaitons != null) {
                for (Application application : applicaitons) {
                    application.setDeleted(true);
                    appService.saveApplication(application);
                    
                    // platforms
                    if (application.getPlatforms() != null) {
                        for (Platform platform : application.getPlatforms()) {
                            
                            // versions
                            VersionSearchCriteria versionCriteria = new VersionSearchCriteria();
                            versionCriteria.setDeleted(false);
                            versionCriteria.setPlatformGuid(platform.getGuid());
                            List<Version> versions = versionService.findVersionsBySearchCriteria(versionCriteria);
                            if (versions != null) {
                                for (Version version : versions) {
                                    version.setDeleted(true);
                                    versionService.saveVersion(version);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void trashApplication(Application application) throws ServiceException {
        // application
        if (application != null) {
            application.setDeleted(true);
            appService.saveApplication(application);
            
            // platforms
            if (application.getPlatforms() != null) {
                for (Platform platform : application.getPlatforms()) {
                    
                    // versions
                    VersionSearchCriteria criteria = new VersionSearchCriteria();
                    criteria.setDeleted(false);
                    criteria.setPlatformGuid(platform.getGuid());
                    List<Version> versions = versionService.findVersionsBySearchCriteria(criteria);
                    if (versions != null) {
                        for (Version version : versions) {
                            version.setDeleted(true);
                            versionService.saveVersion(version);
                        }
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void trashUser(UserProfile profile) throws ServiceException {
        if (profile != null) {
            profile.setDeleted(true);
            userProfileService.saveUserProfile(profile);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void trashVersion(Version version) throws ServiceException {
        if (version != null) {
            version.setDeleted(true);
            versionService.saveVersion(version);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void trashRole(Role role) throws ServiceException {
        if (role != null) {
            role.setDeleted(true);
            roleService.saveRole(role);
        }
    }
}
