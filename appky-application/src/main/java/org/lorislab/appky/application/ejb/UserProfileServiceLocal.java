/*
 * Copyright 2011 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.application.ejb;

import java.util.List;
import org.lorislab.appky.application.criteria.UserProfileSearchCriteria;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The user profile service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface UserProfileServiceLocal {

    /**
     * Saves the user profile.
     *
     * @param userProfile the user profile.
     * @return the saved user profile.
     * @throws ServiceException if the method fails.
     */
    UserProfile saveUserProfile(UserProfile userProfile) throws ServiceException;

    /**
     * Deletes the user profile.
     *
     * @param userProfile the user profile.
     * @return <code>true</code> if the user profile was deleted.
     * @throws ServiceException if the method fails.
     */
    boolean deleteUserProfile(UserProfile userProfile) throws ServiceException;

    /**
     * Finds user profiles by search criteria.
     *
     * @param criteria the search criteria.
     * @return the list of user profiles corresponding to the search criteria.
     * @throws ServiceException if the method fails.s
     */
    List<UserProfile> findUserProfilesByCriteria(UserProfileSearchCriteria criteria) throws ServiceException;

    /**
     * Loads the user profile.
     *
     * @param guid the user profile GUID.
     * @return the user profile corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    UserProfile loadUserProfile(String guid) throws ServiceException;

    /**
     * Loads the full user profile.
     *
     * @param guid the user profile GUID.
     * @return the user profile corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    UserProfile loadFullUserProfile(String guid) throws ServiceException;
}
