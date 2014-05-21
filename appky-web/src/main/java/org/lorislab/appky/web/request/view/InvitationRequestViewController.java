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
package org.lorislab.appky.web.request.view;


import org.lorislab.appky.web.util.PasswordUtil;
import org.lorislab.appky.web.Navigation;
import org.lorislab.appky.web.request.action.InvitationCancelAction;
import org.lorislab.appky.web.request.action.InvitationSignUpAction;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.appky.application.ejb.UserProfileServiceLocal;
import org.lorislab.appky.application.model.EmailRequest;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.enums.EmailRequestType;
import org.lorislab.appky.process.registration.ejb.RegistrationServiceLocal;
import org.lorislab.appky.process.registration.factory.RegistrationFactory;
import org.lorislab.appky.process.registration.model.Registration;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;

/**
 * The invitation request view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named(value = "invitationRequestVC")
@SessionScoped
public class InvitationRequestViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -7362282139140400503L;
    /**
     * The registration.
     */
    private Registration registration;
    /**
     * The GUID.
     */
    private String guid;
    /**
     * The action.
     */
    private String action;
    /**
     * The validate.
     */
    private boolean validate;
    /**
     * The password1.
     */
    private String password1;
    /**
     * The password2.
     */
    private String password2;
    /**
     * The invitation cancel action.
     */
    private InvitationCancelAction cancelAction;
    /**
     * The invitation sign up action.
     */
    private InvitationSignUpAction singupAction;
    /**
     * The email request.
     */
    private EmailRequest request;
    /**
     * The user profile.
     */
    private UserProfile requestUser;
    /**
     * The registration service.
     */
    @EJB
    private RegistrationServiceLocal registrationService;
    /**
     * The user profile service.
     */
    @EJB
    private UserProfileServiceLocal userProfileService;

    /**
     * The default constructor.
     */
    public InvitationRequestViewController() {
        registration = RegistrationFactory.createRegistration();
        cancelAction = new InvitationCancelAction(this);
        singupAction = new InvitationSignUpAction(this);
    }

    /**
     * Validate the request.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void validateRequest() throws Exception {
        validate = false;
        request = null;
        if (guid != null) {
            request = registrationService.findValidEmailRequest(guid, EmailRequestType.INVITATION);
            if (request != null) {
                registration.setEmail(request.getEmail());
                requestUser = userProfileService.loadUserProfile(request.getParentGuid());
                if (requestUser != null) {
                    validate = true;
                }
            }
        }
    }

    /**
     * Sing up the request.
     *
     * @return the navigation rule.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public Object singUp() throws Exception {
        if (PasswordUtil.validatePasswords(password1, password2)) {
            char[] password = PasswordUtil.createPassword(password1);
            registration.setPassword(password);
            registrationService.acceptInvitationRequest(request, registration);
        }
        return Navigation.NAV_TO_PUBLIC;
    }

    /**
     * The cancel request.
     *
     * @return the navigation rule.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public Object cancel() throws Exception {
        registrationService.cancelRequest(request);
        return Navigation.NAV_TO_PUBLIC;
    }

    /**
     * Gets the registration.
     *
     * @return the registration.
     */
    public Registration getRegistration() {
        return registration;
    }

    /**
     * Sets the registration.
     *
     * @param registration the registration.
     */
    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    /**
     * Gets the validation flag.
     *
     * @return the validation flag.
     */
    public boolean isValidate() {
        return validate;
    }

    /**
     * Gets the action.
     *
     * @return the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action.
     *
     * @param action the action.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Gets the GUID.
     *
     * @return the GUID.
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the GUID.
     *
     * @param guid the GUID.
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * Gets the password1.
     *
     * @return the password1.
     */
    public String getPassword1() {
        return password1;
    }

    /**
     * Sets the password1.
     *
     * @param password1 the password1.
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     * Gets the password2.
     *
     * @return the password2.
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * Sets the password2.
     *
     * @param password2 the password2.
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     * Gets the invitation cancel action.
     *
     * @return the invitation cancel action.
     */
    public InvitationCancelAction getCancelAction() {
        return cancelAction;
    }

    /**
     * Gets the sing up action.
     *
     * @return the sing up action.
     */
    public InvitationSignUpAction getSingupAction() {
        return singupAction;
    }

    /**
     * Gets the email request.
     *
     * @return the email request.
     */
    public EmailRequest getRequest() {
        return request;
    }

    /**
     * Gets the user profile.
     *
     * @return the user profile.
     */
    public UserProfile getRequestUser() {
        return requestUser;
    }
}
