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
 * The process configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ProcessApplicationConfiguration {

    /**
     * The template for the application update email.
     */
    private String templateApplicationUpdateEmail = "app.update.email";
    /**
     * The temporary resource validation time.
     */
    private int resourceValidateTo = 1;
  
    /**
     * Gets the temporary resource validation time.
     *
     * @return the temporary resource validation time.
     */
    public int getResourceValidateTo() {
        return resourceValidateTo;
    }

    /**
     * Sets the temporary resource validation time.
     *
     * @param resourceValidateTo the temporary resource validation time.
     */    
    public void setResourceValidateTo(int resourceValidateTo) {
        this.resourceValidateTo = resourceValidateTo;
    }    
    
    /**
     * The template for the application update email.
     *
     * @return the template for the application update email.
     */
    public String getTemplateApplicationUpdateEmail() {
        return templateApplicationUpdateEmail;
    }

    /**
     * The template for the application update email.
     *
     * @param templateApplicationUpdateEmail the template for the application update email.
     */    
    public void setTemplateApplicationUpdateEmail(String templateApplicationUpdateEmail) {
        this.templateApplicationUpdateEmail = templateApplicationUpdateEmail;
    }
    
    
}
