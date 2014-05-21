/*
 * Copyright 2011 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.web.login.view;


import org.lorislab.appky.web.util.PasswordUtil;
import org.lorislab.appky.web.login.action.RegisterAction;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.lorislab.appky.process.registration.ejb.RegistrationServiceLocal;
import org.lorislab.appky.process.registration.factory.RegistrationFactory;
import org.lorislab.appky.process.registration.model.Registration;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;

/**
 * The registration view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("registrationVC")
@RequestScoped
public class RegistrationViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5114488682236324314L;
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(RegistrationViewController.class.getName());
    /**
     * The registration model.
     */
    private Registration registration;
    /**
     * The password1.
     */
    private String password1;
    /**
     * The password2.
     */
    private String password2;
    /**
     * The registration action.
     */
    private RegisterAction registerAction;
    /**
     * The registration service.
     */
    @EJB
    private RegistrationServiceLocal registrationService;

    /**
     * The default constructor.
     */
    public RegistrationViewController() {
        registration = RegistrationFactory.createRegistration();
        registerAction = new RegisterAction(this);
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
     * Gets the registration action.
     *
     * @return the registration action.
     */
    public RegisterAction getRegisterAction() {
        return registerAction;
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
     * Check the user email.
     *
     * @return <code>true</code> if the password does not exits.
     */
    @FacesServiceMethod
    public boolean checkUserEmail() throws Exception {
        boolean result = true;
        if (registration != null) {
            String email = registration.getEmail();
            if (email != null && !email.isEmpty()) {
                result = registrationService.checkUserEmail(email);
            }
        }
        return result;
    }

    /**
     * Register the user.
     */
    @FacesServiceMethod
    public void register() throws Exception {
        if (checkUserEmail()) {
            if (PasswordUtil.validatePasswords(password1, password2)) {
                char[] password = PasswordUtil.createPassword(password1);
                registration.setPassword(password);
                registrationService.createUserRegistration(registration);
                registration = RegistrationFactory.createRegistration();
            } else {
                LOGGER.log(Level.SEVERE, "Password validation: error");
            }
        } else {
            LOGGER.log(Level.SEVERE, "Email already exists");
        }
    }
}
