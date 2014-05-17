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
package org.lorislab.appky.process.registration.resources;

/**
 * The registration service error keys.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public enum ErrorKeys {

    /**
     * The RESET_PASSWORD_ACCEPT_REQUEST_FAILED error key.
     */
    RESET_PASSWORD_ACCEPT_REQUEST_FAILED,
    /**
     * The RESET_PASSWORD_EMAIL_REQUEST_FAILED error key.
     */
    RESET_PASSWORD_EMAIL_REQUEST_FAILED,
    /**
     * The CREATE_USER_FAILED error key.
     */
    CREATE_USER_FAILED,
    /**
     * The CANCEL_REQUEST_FAILED error key.
     */
    CANCEL_REQUEST_FAILED,
    /**
     * The INVITATION_ACCEPT_REQUEST_FAILED error key.
     */
    INVITATION_ACCEPT_REQUEST_FAILED,
    /**
     * The INVITATION_EMAIL_REQUEST_FAILED error key.
     */
    INVITATION_EMAIL_REQUEST_FAILED,
    /**
     * The VALIDATE_EMAIL_REQUEST_FAILED error key.
     */
    VALIDATE_EMAIL_REQUEST_FAILED,
    /**
     * The REGISTRATION_EMAIL_SEND_FAILED error key.
     */
    REGISTRATION_EMAIL_SEND_FAILED,
    /**
     * The REGISTRATION_FAILED error key.
     */
    REGISTRATION_FAILED,
    /**
     * The CHECK_USER_FAILED error key.
     */
    CHECK_USER_FAILED;
}
