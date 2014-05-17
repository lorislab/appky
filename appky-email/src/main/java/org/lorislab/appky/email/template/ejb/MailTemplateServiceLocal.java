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

import java.util.Locale;
import org.lorislab.appky.email.template.model.MailTemplate;
import org.lorislab.appky.email.template.model.MailTemplateResource;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The mail template service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface MailTemplateServiceLocal {

    /**
     * Loads the mail template by name.
     *
     * @param name the template name.
     * @param locale the email locale.
     * @return the mail template.
     * @throws ServiceException if the method fails.
     */
    MailTemplate loadMailTemplateByName(String name, Locale locale) throws ServiceException;

    /**
     * Loads the template resource.
     *
     * @param template the template name.
     * @param name the resource name.
     * @return the template resource.
     * @throws ServiceException if the method fails.
     */
    MailTemplateResource loadMailTemplateResource(String template, String name) throws ServiceException;
}
