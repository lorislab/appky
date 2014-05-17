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
package org.lorislab.appky.process.config;

/**
 * The create user configuration model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ProcessCreateUserConfiguration extends AbstractProcessConfiguration {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 3750186286911751807L;
    /**
     * The default role.
     */
    private static final String CONFIG_DEFAULT_ROLE = "role.default";
    /**
     * The default role default.
     */
    private static final String CONFIG_DEFAULT_ROLE_DEFAULT = "u-store";
    /**
     * The email validation role.
     */
    private static final String CONFIG_EMAILVALIDATION_ROLE = "role.emailValidation";
    /**
     * The email validation role default.
     */
    private static final String CONFIG_EMAILVALIDATION_ROLE_DEFAULT = "u-reg-store";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefaultValues() {
        addValue(CONFIG_DEFAULT_ROLE, CONFIG_DEFAULT_ROLE_DEFAULT);
        addValue(CONFIG_EMAILVALIDATION_ROLE, CONFIG_EMAILVALIDATION_ROLE_DEFAULT);
    }

    /**
     * Gets the default role.
     *
     * @return the default role.
     */
    public String getDefaultRole() {
        return getStringValue(CONFIG_DEFAULT_ROLE);
    }

    /**
     * Gets the email validation role.
     *
     * @return the email validation role.
     */
    public String getEmailValidationRole() {
        return getStringValue(CONFIG_EMAILVALIDATION_ROLE);
    }

}
