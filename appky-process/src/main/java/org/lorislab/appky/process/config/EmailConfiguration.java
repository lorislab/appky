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

import org.lorislab.appky.config.model.AbstractConfigurationModel;

/**
 * The email configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class EmailConfiguration extends AbstractConfigurationModel {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 7919809814409201358L;
    /**
     * The module.
     */
    private static final String MODULE = "email";
    /**
     * The email from address.
     */    
    private static final String CONF_EMAIL_FROM = "email.from";
    /**
     * The email from address default value.
     */    
    private static final String CONF_EMAIL_FROM_DEFAULT = "email.from@server.com";
    /**
     * The email char-set key.
     */
    private static final String CONF_EMAIL_CONTENT_CHARSET = "email.content.charset";
    /**
     * The email char-set default value.
     */    
    private static final String CONF_EMAIL_CONTENT_CHARSET_DEFAULT = "UTF-8";
    /**
     * The email content type key.
     */    
    private static final String CONF_EMAIL_CONTENT_TYPE = "email.content.type";
    /**
     * The email content type default value.
     */    
    private static final String CONF_EMAIL_CONTENT_TYPE_DEFAULT = "text/html;charset=\"UTF-8\"";
    /**
     * The email transfer encoding key.
     */    
    private static final String CONF_EMAIL_TRANSFER_ENCODING = "email.transfer.encoding";
    /**
     * The email transfer encoding default value.
     */        
    private static final String CONF_EMAIL_TRANSFER_ENCODING_DEFAULT = "quoted-printable";


    /**
     * The default constructor.
     */
    public EmailConfiguration() {
        super(MODULE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefaultValues() {                
        addValue(CONF_EMAIL_FROM, CONF_EMAIL_FROM_DEFAULT);
        addValue(CONF_EMAIL_CONTENT_CHARSET, CONF_EMAIL_CONTENT_CHARSET_DEFAULT);
        addValue(CONF_EMAIL_CONTENT_TYPE, CONF_EMAIL_CONTENT_TYPE_DEFAULT);
        addValue(CONF_EMAIL_TRANSFER_ENCODING, CONF_EMAIL_TRANSFER_ENCODING_DEFAULT);
    }

    /**
     * Gets the email from value.
     *
     * @return the email from value.
     */
    public String getEmailFrom() {
        return this.getStringValue(CONF_EMAIL_FROM);
    }

    /**
     * Gets the content char-set.
     *
     * @return the content char-set.
     */
    public String getContentCharset() {
        return this.getStringValue(CONF_EMAIL_CONTENT_CHARSET);
    }

    /**
     * Gets the content type.
     *
     * @return the content type.
     */
    public String getContentType() {
        return this.getStringValue(CONF_EMAIL_CONTENT_TYPE);
    }

    /**
     * Gets the transfer encoding.
     *
     * @return the transfer encoding.
     */
    public String getTransferEncoding() {
        return this.getStringValue(CONF_EMAIL_TRANSFER_ENCODING);
    }
}
