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
package org.lorislab.appky.web.admin.invitation.view;


import org.lorislab.appky.web.admin.invitation.action.SendInvitationAction;
import org.lorislab.appky.web.admin.profile.view.UserProfileViewController;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.email.util.EmailUtil;
import org.lorislab.appky.process.registration.ejb.RegistrationServiceLocal;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;

/**
 * The invitation view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named(value = "invitationVC")
@SessionScoped
public class InvitationViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -7362282139140400503L;       
    /**
     * The email.
     */
    private String email;
    /**
     * The email validation flag.
     */
    private boolean validate;
    /**
     * The send invitation action.
     */
    private SendInvitationAction sendAction;
    /**
     * The registration service.
     */
    @EJB
    private RegistrationServiceLocal service;
    /**
     * The user profile view controller.
     */
    @Inject
    private UserProfileViewController userProfileVC;

    /**
     * The default constructor.
     */
    public InvitationViewController() {
        sendAction = new SendInvitationAction(this);
    }

    /**
     * Sends the invitation method.
     *
     * @return the navigation rule string.
     */
    @FacesServiceMethod
    public Object sendInvitation() throws Exception {
        service.sendInvitationEmailRequest(userProfileVC.getModel(), email);
        return null;
    }

    /**
     * Gets the send invitation action.
     *
     * @return the send invitation action.
     */
    public SendInvitationAction getSendAction() {
        return sendAction;
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
     * Gets the email validation flag.
     * 
     * @return the email validation flag.
     */
    public boolean isValidate() {
        return validate;
    }
        
    /**
     * Check the user email.
     *
     * @return <code>true</code> if the password does not exits.
     */
    @FacesServiceMethod
    public void checkEmail(javax.faces.event.AjaxBehaviorEvent event) throws Exception {
        try {
        validate = false;
        if (email != null && EmailUtil.validate(email)) {
            if (email != null && !email.isEmpty()) {
                validate = service.checkUserEmail(email);
            }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(email + ":" + validate);
    }    
}
