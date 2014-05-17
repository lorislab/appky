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


import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Group;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.jel.ejb.exception.ServiceException;


/**
 * The trash service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface TrashServiceLocal {

    /**
     * Sends the user profile in the trash.
     *
     * @param profile the user profile.
     * @throws ServiceException if the method fails.
     */
    void trashUser(UserProfile profile) throws ServiceException;

    /**
     * Sends the version in the trash.
     *
     * @param version the version
     * @throws ServiceException if the method fails.
     */
    void trashVersion(Version version) throws ServiceException;

    /**
     * Sends the group and all items in this group in the trash.
     *
     * @param group the group.
     * @throws ServiceException if the method fails.
     */
    void trashGroup(Group group) throws ServiceException;

    /**
     * Sends the application and all version of the application in the trash.
     *
     * @param application the application.
     * @throws ServiceException if the method fails.
     */
    void trashApplication(Application application) throws ServiceException;

    /**
     * Sends the role in the trash.
     *
     * @param role the role.
     * @throws ServiceException if the method fails.
     */
    void trashRole(Role role) throws ServiceException;
}
