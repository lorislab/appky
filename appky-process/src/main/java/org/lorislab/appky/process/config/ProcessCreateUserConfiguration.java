/*
 * Copyright 2014 lorislab.org.
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
package org.lorislab.appky.process.config;

/**
 * The create user configuration model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ProcessCreateUserConfiguration {

    /**
     * The default role.
     */
    private String defaultRole = "u-store";

    /**
     * The email validation role.
     */
    private String emailValidationRole = "u-reg-store";

    /**
     * Gets the default role.
     *
     * @return the default role.
     */
    public String getDefaultRole() {
        return defaultRole;
    }

    /**
     * Sets the default role.
     *
     * @param defaultRole the default role.
     */
    public void setDefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }

    /**
     * Gets the email validation role.
     *
     * @return the email validation role.
     */
    public String getEmailValidationRole() {
        return emailValidationRole;
    }

    /**
     * Sets the email validation role.
     *
     * @param emailValidationRole the email validation role.
     */
    public void setEmailValidationRole(String emailValidationRole) {
        this.emailValidationRole = emailValidationRole;
    }

}
