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
package org.lorislab.appky.email.ejb;

import java.util.Date;
import java.util.List;
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
import org.lorislab.appky.email.model.Email;
import org.lorislab.appky.email.model.EmailAttachment;
import org.lorislab.appky.email.resources.ErrorKeys;
import org.lorislab.appky.email.template.ejb.MailTemplateServiceLocal;
import org.lorislab.appky.email.template.model.MailTemplate;
import org.lorislab.appky.email.template.util.EmailTemplateBuilder;
import org.lorislab.appky.email.util.EmailUtil;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractServiceBean;

/**
 * The mail service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(MailServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MailServiceBean extends AbstractServiceBean implements MailServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 3930997628193393730L;
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(MailServiceBean.class.getName());
    /**
     * The email object constant.
     */
    private static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
    /**
     * The email object constant.
     */
    private static final String CONTENT_TYPE = "Content-Type";
    /**
     * The mail session.
     */
    @Resource(lookup = "java:/CoreMailServer")
    private Session mailSession;
  
    /**
     * The mail template service.
     */
    @EJB
    private MailTemplateServiceLocal templateService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Asynchronous
    public void sendEmail(Email email) throws ServiceException {
            sendSynchronyEmail(email);
    }

    /**
     * Sends the email synchrony.
     *
     * @param email the email object.
     *
     * @throws ServiceException if the method fails.
     */
    private void sendSynchronyEmail(Email email) throws ServiceException {
        try {

            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(EmailUtil.createAddress(email.getFrom()));
            message.setRecipients(Message.RecipientType.TO, EmailUtil.createAddresses(email.getTo()));

            MailTemplate template = templateService.loadMailTemplateByName(email.getTemplate(), email.getLocale());

            String subject = EmailTemplateBuilder.getEmailContent(template.getSubject(), email.getParameters());
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
            String content = EmailTemplateBuilder.getEmailContent(template.getContent(), email.getParameters());
            messagePart.setText(content, email.getContentCharset());
            
            // Email header
            messagePart.setHeader(CONTENT_TRANSFER_ENCODING, email.getTransferEncoding());
            messagePart.setHeader(CONTENT_TYPE, email.getContentType());

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
}
