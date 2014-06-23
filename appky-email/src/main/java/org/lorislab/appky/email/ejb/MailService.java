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
package org.lorislab.appky.email.ejb;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.lorislab.appky.email.config.MailConfig;
import org.lorislab.appky.email.model.Email;
import org.lorislab.appky.email.model.EmailAttachment;
import org.lorislab.appky.email.model.MailTemplateResource;
import org.lorislab.appky.email.resources.ErrorKeys;
import org.lorislab.appky.email.util.EmailUtil;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.jel.base.util.FileUtil;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractServiceBean;

/**
 * The mail service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MailService extends AbstractServiceBean {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 3930997628193393730L;
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(MailService.class.getName());
    
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationService configService;
    
    /**
     * The mail session.
     */
    @Resource(lookup = "java:jboss/mail/org.lorislab.appky.mail")
    private Session mailSession;

    /**
     * Sends email.
     * 
     * @param email the email object.
     * 
     * @throws ServiceException if the method fails.
     */
    @Asynchronous
    public void sendEmail(Email email) throws ServiceException {
        
        MailConfig config = configService.getConfiguration(MailConfig.class);

        if (config.isEnabled()) {
            // set email locale
            email.setLocale(config.getLocale());
            // set email from
            email.setFrom(config.getFrom());
            // sets the attributes
            email.setContentType(config.getContentType());
            email.setContentCharset(config.getContentCharset());
            email.setTransferEncoding(config.getTransferEncoding());
            // add special parameter
            email.getParameters().put(MailConfig.class.getSimpleName(), config);
            email.getParameters().put(Email.class.getSimpleName(), email);
            // send email
            sendMail(email);
        } else {
            LOGGER.log(Level.FINE, "The send email is disabled!");
        }        
    }
    
    private void sendMail(Email email) throws ServiceException {
        try {

            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(EmailUtil.createAddress(email.getFrom()));
            message.setRecipients(Message.RecipientType.TO, EmailUtil.createAddresses(email.getTo()));

            
            String subject = EmailUtil.getMailSubject(email.getTemplate(), email.getLocale(), email.getParameters());
            message.setSubject(subject);
            message.setSentDate(new Date());

            // BCC addresses
            if (!email.getBcc().isEmpty()) {
                message.setRecipients(Message.RecipientType.BCC, EmailUtil.createAddresses(email.getBcc()));
            }

            // CC addresses
            if (!email.getCc().isEmpty()) {
                message.setRecipients(Message.RecipientType.CC, EmailUtil.createAddresses(email.getCc()));
            }

            // Email content
            MimeBodyPart messagePart = new MimeBodyPart();
            String content = EmailUtil.getMailContent(email.getTemplate(), email.getLocale(), email.getParameters());
            messagePart.setText(content, email.getContentCharset());
            
            // Email header
            messagePart.setHeader(EmailUtil.CONTENT_TRANSFER_ENCODING, email.getTransferEncoding());
            messagePart.setHeader(EmailUtil.CONTENT_TYPE, email.getContentType());

            // create message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messagePart);

            List<EmailAttachment> attachments = email.getAttachments();
            if (attachments != null && !attachments.isEmpty()) {
                for (EmailAttachment attachment : attachments) {

                    MimeBodyPart attachmentPart = new MimeBodyPart();

                    byte[] fileContent = attachment.getContent();
                    attachmentPart.setContent(fileContent, attachment.getContentType().getType());

                    attachmentPart.setFileName(attachment.getName());
                    multipart.addBodyPart(attachmentPart);
                }
            }
            message.setContent(multipart);

            Transport.send(message);

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error by sending the email", ex);
            Object guid = EmailUtil.getGuid(email);
            throw new ServiceException(ErrorKeys.SEND_EMAIL_FAILED, (String)guid, ex, (String)guid);
        }
    }
    
    /**
     * Loads the template resource.
     *
     * @param template the template name.
     * @param name the resource name.
     * @return the template resource.
     * @throws ServiceException if the method fails.
     */
    public MailTemplateResource loadMailTemplateResource(String template, String name) throws ServiceException {

        byte[] data = null;
        try {
            // load the file
            String path = EmailUtil.getMailResourcePath(template, Locale.ENGLISH, name);
            data = FileUtil.readFileAsByteArray(path, this.getClass().getClassLoader());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error reading the template " + template + " resource " + name, ex);
        }
        // create template resource
        MailTemplateResource result = new MailTemplateResource();
        result.setName(name);
        result.setContent(data);
        return result;
    }    
}
