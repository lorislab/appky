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
package org.lorislab.appky.web.login.view;


import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.process.config.ViewConfiguration;
import org.lorislab.appky.process.ejb.UserProcessService;
import org.lorislab.appky.process.registration.ejb.RegistrationService;
import org.lorislab.appky.web.Constants;
import org.lorislab.appky.web.Navigation;
import org.lorislab.appky.web.admin.profile.view.UserProfileViewController;
import org.lorislab.appky.web.login.action.ForgotPasswordAction;
import org.lorislab.appky.web.login.action.LoginAction;
import org.lorislab.appky.web.login.action.LogoutAction;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.util.FacesResourceUtil;

/**
 * The login view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("loginVC")
@RequestScoped
public class LoginViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -8065319068476020821L;
    /**
     * The email.
     */
    private String email;
    /**
     * The password.
     */
    private String password;
    /**
     * The public login configuration.
     */
    private ViewConfiguration config;
    /**
     * The login action.
     */
    private LoginAction loginAction;
    /**
     * The logout action.
     */
    private LogoutAction logoutAction;
    /**
     * The forgot password action.
     */
    private ForgotPasswordAction forgotAction;
    /**
     * The user profile view controller.
     */
    @Inject
    private UserProfileViewController userProfileVC;
    /**
     * The registration service.
     */
    @EJB
    private RegistrationService registrationService;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationService configurationService;
    /**
     * The user process service.
     */
    @EJB
    private UserProcessService userProcessService;

    /**
     * The default constructor.
     */
    public LoginViewController() {
        loginAction = new LoginAction(this);
        logoutAction = new LogoutAction(this);
        forgotAction = new ForgotPasswordAction(this);
    }

    /**
     * Post constructor method.
     */
    @PostConstruct
    public void init() {
        try {
            config = configurationService.getConfiguration(ViewConfiguration.class);
        } catch (Exception ex) {
            FacesResourceUtil.handleExceptionMessage(ex);
        }
    }

    /**
     * Gets the public login configuration.
     *
     * @return the public login configuration.
     */
    public ViewConfiguration getConfig() {
        return config;
    }

    /**
     * Gets the login action.
     *
     * @return the login action.
     */
    public LoginAction getLoginAction() {
        return loginAction;
    }

    /**
     * Gets the logout action.
     *
     * @return the logout action.
     */
    public LogoutAction getLogoutAction() {
        return logoutAction;
    }

    /**
     * Gets the forgot action.
     *
     * @return the forgot action.
     */
    public ForgotPasswordAction getForgotAction() {
        return forgotAction;
    }

    /**
     * Gets the email.
     *
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email.
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Forgot method.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void forgot() throws Exception {
        registrationService.sendResetPasswordEmailRequest(email);
    }

    /**
     * The login method.
     *
     * @return the navigation rule.
     */
    @FacesServiceMethod
    public Object login() throws Exception {
        String result = null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            UserProfile tmp = userProcessService.findUserProfileForLogin(email);
            if (tmp != null) {
                request.login(tmp.getGuid(), password);
                userProfileVC.setModel(tmp);
                // set the user guid to the session (see timeout listener)
                HttpSession session = request.getSession(true);
                session.setAttribute(Constants.SESSION_USER_GUID, tmp.getGuid());
                // save tracking data
                userProcessService.loginUser(tmp);
                // navigation to private
                result = Navigation.NAV_TO_PRIVATE;
            } else {
                context.addMessage(null, new FacesMessage("Missing profile"));
            }
        } catch (Exception e) {
            userProfileVC.reset();
            context.addMessage(null, new FacesMessage("Login failed"));
            throw e;
        }
        return result;
    }

    /**
     * Logout the user.
     *
     * @return the navigation rule.
     */
    public Object logout() throws Exception {  
        // request logout
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.logout();
        // invalidate session
        HttpSession se = (HttpSession) context.getExternalContext().getSession(true);
        se.setAttribute(Constants.SESSION_USER_TIMEOUT, false);        
        se.invalidate();       
        // navigation public
        return Navigation.NAV_TO_PUBLIC;
    }
}
