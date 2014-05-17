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
package org.lorislab.appky.email.template.ejb;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.appky.email.template.model.MailTemplate;
import org.lorislab.appky.email.template.model.MailTemplateResource;
import org.lorislab.appky.email.template.util.EmailTemplateBuilder;
import org.lorislab.jel.base.util.FileUtil;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractServiceBean;

/**
 * The email template service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(MailTemplateServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MailTemplateServiceBean extends AbstractServiceBean implements MailTemplateServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 8764828101399056285L;
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(MailTemplateServiceBean.class.getName());
    /**
     * The template content file.
     */
    private static final String TEMPLATE_CONTENT_FILE = "email.content";
    /**
     * The template subject file.
     */
    private static final String TEMPLATE_SUBJECT_FILE = "email.subject";

    /**
     * The email configuration file.
     */
    private static final String CONF_EMAIL = "email.properties";
    /**
     * {@inheritDoc}
     */
    @Override
    public MailTemplate loadMailTemplateByName(String name, Locale locale) throws ServiceException {

        String subject = null;
        String content = null;

        try {
            // load the email configuration: content for the locale.
            ResourceBundle bundle = ResourceBundle.getBundle(CONF_EMAIL, locale, this.getClass().getClassLoader());
            String contentFile = bundle.getString(TEMPLATE_CONTENT_FILE);
            String subjectFile = bundle.getString(TEMPLATE_SUBJECT_FILE);
            // load the content of the template
            subject = FileUtil.readFileAsString(EmailTemplateBuilder.getFilePathFromTemplate(name, contentFile), this.getClass().getClassLoader());
            content = FileUtil.readFileAsString(EmailTemplateBuilder.getFilePathFromTemplate(name, subjectFile), this.getClass().getClassLoader());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error reading the templates", ex);
        }
        // create template
        MailTemplate template = new MailTemplate();
        template.setName(name);
        template.setSubject(subject);
        template.setContent(content);
        return template;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MailTemplateResource loadMailTemplateResource(String template, String name) throws ServiceException {

        byte[] data = null;
        try {
            // load the file
            data = FileUtil.readFileAsByteArray(EmailTemplateBuilder.getFilePathFromTemplate(template,name), this.getClass().getClassLoader());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error reading the template " + template + " resource " + name, ex);
        }
        // create template resource
        MailTemplateResource result = new MailTemplateResource();
        result.setName(name);
        result.setContent(data);
        return result;
    }
}
