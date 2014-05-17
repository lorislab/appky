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
package org.lorislab.appky.process.config;

/**
 * The registration configuration model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ProcessRegistrationConfiguration extends AbstractProcessConfiguration {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 2499400874836753916L;
    /**
     * The registration interval.
     */
    private static final String CONF_REG_INTERVAL = "registration.interval";
    /**
     * The registration interval default.
     */
    private static final String CONF_REG_INTERVAL_DEFAULT = "30";
    /**
     * Sends the registration email flag.
     */
    private static final String CONF_REG_SEND_EMAIL = "registration.sendEmail";
    /**
     * Sends the registration email flag default.
     */
    private static final String CONF_REG_SEND_EMAIL_DEFAULT = "true";
    /**
     * The invitation interval.
     */
    private static final String CONF_INVITATION_INTERVAL = "invitation.interval";
    /**
     * The invitation interval default.
     */
    private static final String CONF_INVITATION_INTERVAL_DEFAULT = "2";
    /**
     * The reset password interval.
     */
    private static final String CONF_RESET_PASSWORD_INTERVAL = "resetpassword.interval";
    /**
     * The reset password interval default.
     */
    private static final String CONF_RESET_PASSWORD_INTERVAL_DEFAULT = "2";
    /**
     * The email template for change request.
     */
    private static final String CONF_TEMPLATE_CHANGE_EMAIL = "template.change.request";
    /**
     * The email template for change request default.
     */
    private static final String CONF_TEMPLATE_CHANGE_EMAIL_DEFAULT = "change.email.request";
    /**
     * The email template for registration request.
     */
    private static final String CONF_TEMPLATE_REGISTRATION_EMAIL = "template.registration.request";
    /**
     * The email template for registration request default.
     */
    private static final String CONF_TEMPLATE_REGISTRATION_EMAIL_DEFAULT = "registration.email.request";
    /**
     * The email template for reset password request.
     */
    private static final String CONF_TEMPLATE_RESET_PASSWORD_EMAIL = "template.resetpassword.request";
    /**
     * The email template for reset password request default.
     */
    private static final String CONF_TEMPLATE_RESET_PASSWORD_EMAIL_DEFAULT = "resetpassword.email.request";
    /**
     * The email template for invitation request.
     */
    private static final String CONF_TEMPLATE_INVITATION_EMAIL = "template.invitation.request";
    /**
     * The email template for invitation request default.
     */
    private static final String CONF_TEMPLATE_INVITATION_EMAIL_DEFAULT = "invitation.email.request";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefaultValues() {
        addValue(CONF_REG_INTERVAL, CONF_REG_INTERVAL_DEFAULT);
        addValue(CONF_REG_SEND_EMAIL, CONF_REG_SEND_EMAIL_DEFAULT);
        addValue(CONF_INVITATION_INTERVAL, CONF_INVITATION_INTERVAL_DEFAULT);
        addValue(CONF_RESET_PASSWORD_INTERVAL, CONF_RESET_PASSWORD_INTERVAL_DEFAULT);
        addValue(CONF_TEMPLATE_CHANGE_EMAIL, CONF_TEMPLATE_CHANGE_EMAIL_DEFAULT);
        addValue(CONF_TEMPLATE_REGISTRATION_EMAIL, CONF_TEMPLATE_REGISTRATION_EMAIL_DEFAULT);
        addValue(CONF_TEMPLATE_RESET_PASSWORD_EMAIL, CONF_TEMPLATE_RESET_PASSWORD_EMAIL_DEFAULT);
        addValue(CONF_TEMPLATE_INVITATION_EMAIL, CONF_TEMPLATE_INVITATION_EMAIL_DEFAULT);
    }

    /**
     * Gets the template for change email.
     *
     * @return the template for change email.
     */
    public String getTemplateChangeEmail() {
        return getStringValue(CONF_TEMPLATE_CHANGE_EMAIL);
    }

    /**
     * Gets the template for registration email.
     *
     * @return the template for registration email.
     */
    public String getTemplateRegistrationEmail() {
        return getStringValue(CONF_TEMPLATE_REGISTRATION_EMAIL);
    }

    /**
     * Gets the template for reset password email.
     *
     * @return the template for reset password email.
     */
    public String getTemplateResetPasswordEmail() {
        return getStringValue(CONF_TEMPLATE_RESET_PASSWORD_EMAIL);
    }

    /**
     * Gets the template for invitation email.
     *
     * @return the template for invitation email.
     */
    public String getTemplateInvitationEmail() {
        return getStringValue(CONF_TEMPLATE_INVITATION_EMAIL);
    }

    /**
     * Gets the reset password interval.
     *
     * @return the reset password interval.
     */
    public int getResetPasswordInterval() {
        return getIntegerValue(CONF_RESET_PASSWORD_INTERVAL);
    }

    /**
     * Gets the invitation interval.
     *
     * @return the invitation interval.
     */
    public int getInvitationInterval() {
        return getIntegerValue(CONF_INVITATION_INTERVAL);
    }

    /**
     * Gets the send registration email flag.
     *
     * @return the send registration email flag.
     */
    public boolean isSendRegistrationEmail() {
        return getBooleanValue(CONF_REG_SEND_EMAIL);
    }

    /**
     * Gets the registration interval.
     *
     * @return the registration interval.
     */
    public int getRegistrationInterval() {
        return getIntegerValue(CONF_REG_INTERVAL);
    }
}
