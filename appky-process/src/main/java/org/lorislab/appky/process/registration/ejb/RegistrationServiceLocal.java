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
package org.lorislab.appky.process.registration.ejb;

import org.lorislab.appky.process.registration.model.Registration;
import org.lorislab.appky.process.registration.model2.enums.EmailValidationAction;
import org.lorislab.appky.application.model.EmailRequest;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.enums.EmailRequestType;
import org.lorislab.jel.ejb.exception.ServiceException;


/**
 * The registration service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface RegistrationServiceLocal {

    /**
     * Creates user registration request.
     *
     * @param registration the registration request.
     * @throws ServiceException if the method fails.
     */
    void createUserRegistration(Registration registration) throws ServiceException;

    /**
     * Check the user email address.
     *
     * @param email the email address.
     * @return <code>true</code> if the does not exist in the application.
     * @throws ServiceException if the method fails.
     */
    boolean checkUserEmail(String email) throws ServiceException;

    /**
     * Accepts email validation request.
     *
     * @param guid the email request GUID.
     * @param action the email request action.
     * @throws ServiceException if the method fails.
     */
    void validateEmailByRequest(String guid, EmailValidationAction action) throws ServiceException;

    /**
     * Send invitation request for the user profile.
     *
     * @param profile the user profile.
     * @param email the email.
     * @throws ServiceException if the method fails.
     */
    void sendInvitationEmailRequest(UserProfile profile, String email) throws ServiceException;

    /**
     * Find valid email request.
     *
     * @param guid the request GUID.
     * @param type the email type request.
     *
     * @return email request.
     *
     * @throws ServiceException if the method fails.
     */
    public EmailRequest findValidEmailRequest(String guid, EmailRequestType type) throws ServiceException;
            
    /**
     * Cancel request.
     *
     * @param request the email request.
     * @throws ServiceException if the method fails.
     */
    void cancelRequest(EmailRequest request) throws ServiceException;

    /**
     * Accepts the invitation request.
     *
     * @param request the invitation request.
     * @param registration the registration model.
     * @throws ServiceException if the method fails.
     */
    void acceptInvitationRequest(EmailRequest request, Registration registration) throws ServiceException;

    /**
     * Sends the reset password request for the email.
     *
     * @param email the email.
     * @throws ServiceException if the method fails.
     */
    void sendResetPasswordEmailRequest(String email) throws ServiceException;

    /**
     * Accepts the reset password request.
     *
     * @param request the email request.
     * @param password the new password.
     * @throws ServiceException if the method fails.
     */
    void acceptResetPasswordRequest(EmailRequest request, char[] password) throws ServiceException;
}
