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
package org.lorislab.appky.web.request.view;


import org.lorislab.appky.web.util.PasswordUtil;
import org.lorislab.appky.web.Navigation;
import org.lorislab.appky.web.admin.user.view.ResetPasswordViewController;
import org.lorislab.appky.web.request.action.ResetPasswordAction;
import org.lorislab.appky.web.request.action.ResetPasswordCancelAction;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.appky.application.ejb.UserProfileService;
import org.lorislab.appky.application.model.EmailRequest;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.enums.EmailRequestType;
import org.lorislab.appky.process.registration.ejb.RegistrationService;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;

/**
 * The reset password request view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@SessionScoped
@Named("resetPasswordRequestVC")
public class ResetPasswordRequestViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -3804797905409564360L;
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ResetPasswordViewController.class.getName());
    /**
     * The validation flag.
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
     * The GUID.
     */
    private String guid;
    /**
     * The action.
     */
    private String action;
    /**
     * The email request.
     */
    private EmailRequest request;
    /**
     * The user profile.
     */
    private UserProfile userProfile;
    /**
     * The reset password action.
     */
    private ResetPasswordAction resetAction;
    /**
     * The reset password cancel action.
     */
    private ResetPasswordCancelAction cancelAction;
    /**
     * The user profile service.
     */
    @EJB
    private UserProfileService userProfileService;
    /**
     * The registration service.
     */
    @EJB
    private RegistrationService registrationService;

    /**
     * The default constructor.
     */
    public ResetPasswordRequestViewController() {
        resetAction = new ResetPasswordAction(this);
        cancelAction = new ResetPasswordCancelAction(this);
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
     * Returns
     * <code>true</code> if the password is validate.
     *
     * @return <code>true</code> if the password is validate.
     */
    public boolean isValidate() {
        return validate;
    }

    /**
     * Sets the validation flag.
     *
     * @param validate the validation flag.
     */
    public void setValidate(boolean validate) {
        this.validate = validate;
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
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Gets the reset password cancel action.
     *
     * @return the reset password cancel action.
     */
    public ResetPasswordCancelAction getCancelAction() {
        return cancelAction;
    }

    /**
     * Gets the reset password action.
     *
     * @return the reset password action.
     */
    public ResetPasswordAction getResetAction() {
        return resetAction;
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
            request = registrationService.findValidEmailRequest(guid, EmailRequestType.RESET_PASSWORD);
            if (request != null) {
                userProfile = userProfileService.loadUserProfile(request.getParentGuid());
                if (userProfile != null) {
                    validate = true;
                }
            }
        }
    }

    /**
     * Cancel the request.
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
     * Resets the password.
     *
     * @return the navigation rule.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public Object resetPassword() throws Exception {

        if (password1 != null && password2 != null) {
            if (password1.equals(password2)) {
                char[] newPassword = PasswordUtil.createPassword(password1);
                registrationService.acceptResetPasswordRequest(request, newPassword);
            } else {
                LOGGER.log(Level.SEVERE, "Password1 != Password2");
            }
        } else {
            LOGGER.log(Level.SEVERE, "Password is null!");
        }

        return null;
    }
}
