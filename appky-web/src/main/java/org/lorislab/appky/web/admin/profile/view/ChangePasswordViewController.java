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
package org.lorislab.appky.web.admin.profile.view;

import org.lorislab.appky.util.PasswordUtil;
import org.lorislab.appky.web.admin.profile.action.ChangePasswordAction;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.ejb.UserPasswordServiceLocal;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;

/**
 * The change password view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@RequestScoped
@Named("changePasswordVC")
public class ChangePasswordViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -3804797905409564360L;
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ChangePasswordViewController.class.getName());
    /**
     * The password.
     */
    private String password;
    /**
     * The password1.
     */
    private String password1;
    /**
     * The password2.
     */
    private String password2;
    /**
     * The change password action.
     */
    private ChangePasswordAction changePasswordAction;
    /**
     * The user password service.
     */
    @EJB
    private UserPasswordServiceLocal userPasswordService;
    /**
     * The user profile view controller.
     */
    @Inject
    private UserProfileViewController userProfileVC;

    /**
     * The default constructor.
     */
    public ChangePasswordViewController() {
        changePasswordAction = new ChangePasswordAction(this);
    }

    /**
     * Gets the change password action.
     *
     * @return the change password action.
     */
    public ChangePasswordAction getChangePasswordAction() {
        return changePasswordAction;
    }

    /**
     * Gets the password.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password.
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Change password.
     */
    @FacesServiceMethod
    public void changePassword() throws Exception {
        String userGuid = userProfileVC.getModel().getGuid();
        if (password1 != null && password2 != null) {
            if (password1.equals(password2)) {
                char[] currentPassword = PasswordUtil.createPassword(password);
                char[] newPassword = PasswordUtil.createPassword(password1);
                userPasswordService.changePassword(userGuid, currentPassword, newPassword);
            } else {
                LOGGER.log(Level.SEVERE, "Password1 != Password2");
            }
        } else {
            LOGGER.log(Level.SEVERE, "Password is null!");
        }
    }
}
