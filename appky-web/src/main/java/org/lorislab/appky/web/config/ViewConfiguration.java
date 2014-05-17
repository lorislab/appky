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
package org.lorislab.appky.web.config;

import org.lorislab.appky.config.model.AbstractConfigurationModel;



/**
 * The public login configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ViewConfiguration extends AbstractConfigurationModel {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 72168413623648302L;
    /**
     * The module name.
     */
    private static final String MODULE = "view";
    /**
     * The registration public key.
     */
    private static final String CONF_REG_PUBLIC = "view.registration";
    /**
     * The registration public default value.
     */
    private static final String CONF_REG_PUBLIC_DEFAULT = "false";
    /**
     * The forgot public key.
     */
    private static final String CONF_FORGOT_PUBLIC = "view.forgotPassword";
    /**
     * The forgot public default value.
     */
    private static final String CONF_FORGOT_PUBLIC_DEFAULT = "false";

    /**
     * The default constructor.
     */
    public ViewConfiguration() {
        super(MODULE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefaultValues() {
        addValue(CONF_REG_PUBLIC, CONF_REG_PUBLIC_DEFAULT);
        addValue(CONF_FORGOT_PUBLIC, CONF_FORGOT_PUBLIC_DEFAULT);
    }

    /**
     * Gets the public forgot flag.
     *
     * @return the public forgot flag.
     */
    public boolean isPublicForgot() {
        return getBooleanValue(CONF_FORGOT_PUBLIC);
    }

    /**
     * Gets the public registration flag.
     *
     * @return the public registration flag.
     */
    public boolean isPublicRegistration() {
        return getBooleanValue(CONF_REG_PUBLIC);
    }
}
