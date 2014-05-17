/*
 * Copyright 2013 Andrej Petras <andrej@ajka-andrej.com>.
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

import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.jel.ejb.exception.ServiceException;


/**
 * The user process service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface UserProcessServiceLocal {

    /**
     * Finds user profile for login.
     * @param email the user email.
     * @return the user profile.
     * @throws ServiceException if the method fails.
     */
    UserProfile findUserProfileForLogin(String email) throws ServiceException;
    
    /**
     * Login the user and returns the user profile.
     *
     * @param userProfile the user profile.
     * @throws ServiceException if the method fails.
     */
    void loginUser(UserProfile userProfile) throws ServiceException;

    /**
     * Logout the user.
     *
     * @param userGuid the user GUID.
     * @param timeout the timeout flag.
     * @throws ServiceException if the method fails.
     */
    void logoutUser(String userGuid, boolean timeout) throws ServiceException;
}
