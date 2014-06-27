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
package org.lorislab.appky.process.config;

/**
 * The registration configuration model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ProcessRegistrationConfiguration {

    /**
     * The registration interval.
     */
    private int registrationInterval = 30;
    /**
     * Sends the registration email flag.
     */
    private boolean sendRegistrationEmail = Boolean.FALSE;
    /**
     * The invitation interval.
     */
    private int invitationInterval = 2;
    /**
     * The reset password interval.
     */
    private int resetPasswordInterval = 2;
    /**
     * The email template for change request.
     */
    private String templateChangeEmail = "change.email.request";
    /**
     * The email template for registration request.
     */
    private String templateRegistrationEmail = "registration.email.request";
    /**
     * The email template for reset password request.
     */
    private String templateResetPasswordEmail = "resetpassword.email.request";
    /**
     * The email template for invitation request.
     */
    private String templateInvitationEmail = "invitation.email.request";

    /**
     * Gets the template for change email.
     *
     * @return the template for change email.
     */
    public String getTemplateChangeEmail() {
        return templateChangeEmail;
    }

    /**
     * Sets the template for change email.
     *
     * @param templateChangeEmail the template for change email.
     */    
    public void setTemplateChangeEmail(String templateChangeEmail) {
        this.templateChangeEmail = templateChangeEmail;
    }
    
    /**
     * Gets the template for registration email.
     *
     * @return the template for registration email.
     */
    public String getTemplateRegistrationEmail() {
        return templateRegistrationEmail;
    }

    /**
     * Sets the template for registration email.
     *
     * @param templateRegistrationEmail the template for registration email.
     */    
    public void setTemplateRegistrationEmail(String templateRegistrationEmail) {
        this.templateRegistrationEmail = templateRegistrationEmail;
    }
    
    /**
     * Gets the template for reset password email.
     *
     * @return the template for reset password email.
     */
    public String getTemplateResetPasswordEmail() {
        return templateResetPasswordEmail;
    }

    /**
     * Sets the template for reset password email.
     *
     * @param templateResetPasswordEmail the template for reset password email.
     */    
    public void setTemplateResetPasswordEmail(String templateResetPasswordEmail) {
        this.templateResetPasswordEmail = templateResetPasswordEmail;
    }
    
    /**
     * Gets the template for invitation email.
     *
     * @return the template for invitation email.
     */
    public String getTemplateInvitationEmail() {
        return templateInvitationEmail;
    }
    
    /**
     * Sets the template for invitation email.
     *
     * @param templateInvitationEmail the template for invitation email.
     */    
    public void setTemplateInvitationEmail(String templateInvitationEmail) {
        this.templateInvitationEmail = templateInvitationEmail;
    }        

    /**
     * Gets the reset password interval.
     *
     * @return the reset password interval.
     */
    public int getResetPasswordInterval() {
        return resetPasswordInterval;
    }

    /**
     * Sets the reset password interval.
     *
     * @param resetPasswordInterval the reset password interval.
     */
    public void setResetPasswordInterval(int resetPasswordInterval) {
        this.resetPasswordInterval = resetPasswordInterval;
    }
    
    /**
     * Gets the invitation interval.
     *
     * @return the invitation interval.
     */
    public int getInvitationInterval() {
        return invitationInterval;
    }

    /**
     * Sets the invitation interval.
     *
     * @param invitationInterval the invitation interval.
     */    
    public void setInvitationInterval(int invitationInterval) {
        this.invitationInterval = invitationInterval;
    }
    
    /**
     * Gets the send registration email flag.
     *
     * @return the send registration email flag.
     */
    public boolean isSendRegistrationEmail() {
        return sendRegistrationEmail;
    }

    /**
     * Sets the send registration email flag.
     *
     * @param sendRegistrationEmail the send registration email flag.
     */    
    public void setSendRegistrationEmail(boolean sendRegistrationEmail) {
        this.sendRegistrationEmail = sendRegistrationEmail;
    }
    
    /**
     * Gets the registration interval.
     *
     * @return the registration interval.
     */
    public int getRegistrationInterval() {
        return registrationInterval;
    }
    
    /**
     * Sets the registration interval.
     *
     * @param registrationInterval the registration interval.
     */
    public void setRegistrationInterval(int registrationInterval) {
        this.registrationInterval = registrationInterval;
    }
    
    
}
