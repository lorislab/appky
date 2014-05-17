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
package org.lorislab.appky.process.tracking.model.enums;

/**
 * The user activity.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public enum UserActivity {

    /**
     * The APP_INSTALL activity.
     */
    APP_INSTALL,
    /**
     * The APP_UNINSTALL activity.
     */
    APP_UNINSTALL,
    /**
     * The APP_UPDATE activity.
     */
    APP_UPDATE,
    /**
     * The APP_REINSTALL activity.
     */
    APP_REINSTALL,
    /**
     * The ADMIN_APP_RELEASE activity.
     */
    ADMIN_APP_RELEASE,
    /**
     * The REQUEST_EMAIL_VALIDATION activity.
     */
    REQUEST_EMAIL_VALIDATION,
    /**
     * The REQUEST_EMAIL_VALIDATION_ACCEPT activity.
     */
    REQUEST_EMAIL_VALIDATION_ACCEPT,
    /**
     * The REQUEST_EMAIL_VALIDATION_DECLINE activity.
     */
    REQUEST_EMAIL_VALIDATION_DECLINE,    
    /**
     * The REQUEST_REGISTRATION activity.
     */
    REQUEST_REGISTRATION,
    /**
     * The REQUEST_RESET_PASSWORD activity.
     */
    REQUEST_RESET_PASSWORD,
    /**
     * The REQUEST_RESET_PASSWORD_ACCEPT activity.
     */    
    REQUEST_RESET_PASSWORD_ACCEPT,
    /**
     * The REQUEST_RESET_PASSWORD_DECLINE activity.
     */    
    REQUEST_RESET_PASSWORD_DECLINE,
    /**
     * The REQUEST_INVITATION activity.
     */
    REQUEST_INVITATION,
    /**
     * The REQUEST_INVITATION_DECLINE activity.
     */    
    REQUEST_INVITATION_DECLINE,
    /**
     * The REQUEST_INVITATION_ACCEPT activity.
     */    
    REQUEST_INVITATION_ACCEPT,
    /**
     * The LOGIN activity.
     */
    LOGIN,
    /**
     * The LOGOUT activity.
     */
    LOGOUT;
}
