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
package org.lorislab.appky.web.admin.user.view;


import org.lorislab.appky.web.util.PasswordUtil;
import org.lorislab.appky.web.admin.user.action.UserResetPasswordAction;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.ejb.UserPasswordServiceLocal;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;

/**
 * The reset password view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@RequestScoped
@Named("resetPasswordVC")
public class ResetPasswordViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -3804797905409564360L;
    /**
     * The password1.
     */
    private String password1;
    /**
     * The password2.
     */
    private String password2;
    /**
     * The user reset password action.
     */
    private UserResetPasswordAction resetPasswordAction;
    /**
     * The user password service.
     */
    @EJB
    private UserPasswordServiceLocal service;
    /**
     * The user view controller.
     */
    @Inject
    private UserViewController userVC;

    /**
     * The default constructor.
     */
    public ResetPasswordViewController() {
        resetPasswordAction = new UserResetPasswordAction(this);
    }

    /**
     * Gets the reset password action.
     *
     * @return the reset password action.
     */
    public UserResetPasswordAction getResetPasswordAction() {
        return resetPasswordAction;
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
     * Resets password.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void resetPassword() throws Exception {
        String userGuid = userVC.getModel().getGuid();
        char[] newPassword = PasswordUtil.createPassword(password1);
        service.resetPassword(userGuid, newPassword);
    }
}
