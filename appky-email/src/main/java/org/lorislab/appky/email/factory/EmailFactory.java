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
package org.lorislab.appky.email.factory;

import org.lorislab.appky.email.model.Email;
import java.util.Locale;
import java.util.Map;

/**
 * The email factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class EmailFactory {

    /**
     * The template key.
     */
    private static final String TEMPLATE_KEY = "template";

    /**
     * The default constructor.
     */
    private EmailFactory() {
        // empty constructor
    }

    /**
     * Creates the email.
     *
     * @param locale the email locale.
     * @param from the email from address.
     * @param to the email to address.
     * @param template the email template.
     * @param contentCharset the content char-ser
     * @param transferEncoding the transfer encoding.
     * @param contentType the content type.
     * @param values the list of template values.
     * @return the created email.
     */
    public static Email createEmail(Locale locale, String from, String to, String template, String contentCharset, String transferEncoding, String contentType, Object... values) {
        Email result = new Email();
        result.getTo().add(to);
        // set email locale
        result.setLocale(locale);
        // sets the attributes
        result.setTemplate(template);
        result.setContentType(contentType);
        result.setContentCharset(contentCharset);
        result.setTransferEncoding(transferEncoding);
        // add parameters
        Map<String, Object> par = result.getParameters();
        if (values != null) {
            for (Object item : values) {
                par.put(item.getClass().getSimpleName(), item);
            }
        }
        // put the email template
        par.put(TEMPLATE_KEY, template);
        return result;
    }
}
