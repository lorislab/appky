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
package org.lorislab.appky.application.factory;

import org.lorislab.appky.application.model.EmailRequest;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.UserConfigParam;
import org.lorislab.appky.application.model.UserPassword;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.enums.EmailRequestStatus;
import org.lorislab.appky.application.model.enums.EmailRequestType;
import org.lorislab.appky.application.model.enums.UserApplicationStatus;
import org.lorislab.appky.application.model.enums.UserConfigParamType;
import java.util.Date;

/**
 * The user object factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class UserObjectFactory {

    /**
     * The default constructor.
     */
    private UserObjectFactory() {
        // empty constructor
    }

    /**
     * Creates the email request.
     *
     * @param userGuid the user GUID.
     * @param email the user email.
     * @param type the request type.
     * @param parentGuid the parent object GUID.
     * @param validateTo the request validate to.
     * @return the new email request.
     */
    public static EmailRequest createEmailRequest(String userGuid, String email, EmailRequestType type, String parentGuid, Date validateTo) {
        EmailRequest request = new EmailRequest();
        request.setUserGuid(userGuid);
        request.setEmail(email);
        request.setStatus(EmailRequestStatus.OPEN);
        request.setType(type);
        request.setParentGuid(parentGuid);
        request.setValidateTo(validateTo);
        return request;
    }

    /**
     * Creates the role.
     *
     * @return the created role.
     */
    public static Role createRole() {
        Role result = new Role();
        result.setEditable(true);
        result.setEnabled(false);
        result.setDeleted(false);
        return result;
    }
    
    /**
     * Creates the role.
     *
     * @param id the role ID.
     * @param name the role name.
     * @return the created role.
     */
    public static Role createRole(String id, String name) {
        Role result = createRole();
        result.setId(id);
        result.setName(name);
        return result;
    }

    /**
     * Creates the user profile configuration parameter.
     *
     * @param name the parameter name.
     * @param value the parameter value.
     * @param type the parameter type.
     *
     * @return the user profile parameter.
     */
    public static UserConfigParam create(String name, String value, UserConfigParamType type) {
        UserConfigParam result = new UserConfigParam();
        result.setName(name);
        result.setValue(value);
        result.setType(type);
        return result;
    }

    /**
     * Creates the user password.
     *
     * @param userGuid the user GUID.
     * @param password the user password.
     * @return the created user password.
     */
    public static UserPassword createUserPassword(String userGuid, char[] password) {
        UserPassword result = new UserPassword();
        result.setUserGuid(userGuid);
        result.setPassword1(password);
        return result;
    }

    /**
     * Creates the user profile.
     *
     * @return the created user profile.
     */
    public static UserProfile createUserProfile() {
        UserProfile profile = new UserProfile();
        profile.setEnabled(true);
        return profile;
    }

    /**
     * Creates the user profile.
     *
     * @param email the user email.
     * @param firstName the first name.
     * @param middleName the middle name.
     * @param lastName the last name.
     * @return the created user profile.
     */
    public static UserProfile createUserProfile(String email, String firstName, String middleName, String lastName) {
        UserProfile profile = createUserProfile();
        profile.setEmail(email);
        profile.setFirstName(firstName);
        profile.setMiddleName(middleName);
        profile.setLastName(lastName);
        return profile;
    }

    /**
     * Creates user application.
     *
     * @param userGuid the user GUID.
     * @return the created user application.
     */
    public static UserApplication createUserApplication(String userGuid) {
        UserApplication result = new UserApplication();
        result.setUserGuid(userGuid);
        result.setStatus(UserApplicationStatus.INSTALLED);
        return result;
    }

    /**
     * Creates the user application.
     *
     * @param userGuid the user GUID.
     * @param applicationGuid the application GUID.
     * @param platformGuid the platform GUID.
     * @param versionGuid the version GUID.
     * @return the created user application.
     */
    public static UserApplication createUserApplication(String userGuid, String applicationGuid, String platformGuid, String versionGuid) {
        UserApplication result = createUserApplication(userGuid);
        result.setPlatformGuid(platformGuid);
        result.setApplicationGuid(applicationGuid);
        result.setVersionGuid(versionGuid);
        return result;
    }
}
