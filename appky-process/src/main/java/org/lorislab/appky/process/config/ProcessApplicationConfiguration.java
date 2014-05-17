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
 * The process configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ProcessApplicationConfiguration extends AbstractProcessConfiguration {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -966352466111631332L;
    /**
     * The template for the application update email.
     */
    private static final String CONFIG_EMAIL_APP_UPDATE_TEMPLATE = "template.application.update.email";
    /**
     * The template for the application update email default value.
     */
    private static final String CONFIG_EMAIL_APP_UPDATE_TEMPLATE_DEFAULT = "app.update.email";
    /**
     * The temporary resource validation time.
     */
    private static final String CONF_RESOURCE_VALIDATE_TO = "tmp.resource.validateTo";
    /**
     * The temporary resource validation time default value.
     */
    private static final String CONF_RESOURCE_VALIDATE_TO_DEFAULT = "1";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setDefaultValues() {
        addValue(CONF_RESOURCE_VALIDATE_TO, CONF_RESOURCE_VALIDATE_TO_DEFAULT);
        addValue(CONFIG_EMAIL_APP_UPDATE_TEMPLATE, CONFIG_EMAIL_APP_UPDATE_TEMPLATE_DEFAULT);
    }
  
    /**
     * Gets the temporary resource validation time.
     *
     * @return the temporary resource validation time.
     */
    public int getResourceValidateTo() {
        return getIntegerValue(CONF_RESOURCE_VALIDATE_TO);
    }

    /**
     * The template for the application update email.
     *
     * @return the template for the application update email.
     */
    public String getTemplateApplicationUpdateEmail() {
        return getStringValue(CONFIG_EMAIL_APP_UPDATE_TEMPLATE);
    }
}
