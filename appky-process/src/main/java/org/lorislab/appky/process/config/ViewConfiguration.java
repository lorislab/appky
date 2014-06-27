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
 * The public login configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ViewConfiguration {

    /**
     * The registration public key.
     */
    private boolean publicRegistration = Boolean.FALSE;

    /**
     * The forgot public key.
     */
    private boolean forgotPublic = Boolean.FALSE;

    /**
     * Gets the public forgot flag.
     *
     * @return the public forgot flag.
     */
    public boolean isPublicForgot() {
        return forgotPublic;
    }

    /**
     * Sets the public forgot flag.
     *
     * @param forgotPublic the public forgot flag.
     */    
    public void setForgotPublic(boolean forgotPublic) {
        this.forgotPublic = forgotPublic;
    }
    
    /**
     * Gets the public registration flag.
     *
     * @return the public registration flag.
     */
    public boolean isPublicRegistration() {
        return publicRegistration;
    }

    /**
     * Sets the public registration flag.
     *
     * @param publicRegistration the public registration flag.
     */    
    public void setPublicRegistration(boolean publicRegistration) {
        this.publicRegistration = publicRegistration;
    }
        
}
