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
package org.lorislab.appky.email.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import org.lorislab.appky.email.model.Email;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;

/**
 * The email utility class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class EmailUtil {

    /**
     * The email object constant.
     */
    public static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
    /**
     * The email object constant.
     */
    public static final String CONTENT_TYPE = "Content-Type";

    /**
     * The email pattern.
     */
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    /**
     * The path separator.
     */
    private static final String PATH_SEPARATOR = "/";    
    /**
     * The pattern object.
     */
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);
    /**
     * The content file prefix.
     */
    private static final String TEMPLATE_RESOURCE = "resources";
    /**
     * The content file prefix.
     */
    private static final String TEMPLATE_CONTENT = "content";
    /**
     * The subject file prefix.
     */
    private static final String TEMPLATE_SUBJECT = "subject";
    /**
     * The bundle file name
     */
    private static final String BUNDLE = "mailtemplate";
    /**
     * The template directory.
     */
    private static final String TEMPLATE_DIR = System.getProperty(EmailUtil.class.getName(), null);
    /**
     * The map of templates.
     */
    private static final Map<String, CompiledTemplate> TEMPLATES = new HashMap<>();

    /**
     * The default constructor.
     */
    private EmailUtil() {
        // empty constructor
    }

    /**
     * Gets the file path from template.
     *
     * @param template
     * @param locale
     * @param resource 
     * @exception Exception
     * @return
     */
    public static final String getMailResourcePath(String template, Locale locale, String resource) throws Exception {
        String tmp = getResourcePath(TEMPLATE_RESOURCE, template, locale);
        StringBuilder sb = new StringBuilder();
        sb.append(tmp);
        sb.append(PATH_SEPARATOR);
        sb.append(resource);      
        return sb.toString();
    }

    /**
     * Gets the mail content.
     *
     * @param template the mail template.
     * @param locale the mail locale.
     * @param parameters the mail parameters.
     * @return the mail content.
     * @throws Exception if the method fails.
     */
    public static String getMailContent(String template, Locale locale, Map<String, Object> parameters) throws Exception {
        return getContent(TEMPLATE_CONTENT, template, locale, parameters);
    }

    /**
     * Gets the mail subject.
     *
     * @param template the mail template.
     * @param locale the mail locale.
     * @param parameters the mail parameters.
     * @return the mail subject.
     * @throws Exception if the method fails.
     */
    public static String getMailSubject(String template, Locale locale, Map<String, Object> parameters) throws Exception {
        return getContent(TEMPLATE_SUBJECT, template, locale, parameters);
    }

    private static String getResourcePath(String template, String name, Locale locale) throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE, locale);
        // create ID
        StringBuilder sb = new StringBuilder();
        sb.append(template).append('.').append(name);
        String key = rb.getString(sb.toString());
        if (key == null) {
            throw new Exception("Missing mail template key: " + key);
        }        
        return key;
    }
    
    /**
     * Gets the email content.
     *
     * @param name the name of the content.
     * @param template the template.
     * @param parameters the list of parameters.
     * @return the email content.
     * @throws Exception if the method fails.
     */
    private static String getContent(String name, String template, Locale locale, Map<String, Object> parameters) throws Exception {

        String key = getResourcePath(template, name, locale);

        CompiledTemplate compiled = TEMPLATES.get(key);
        // if not exist create compiled template
        if (compiled == null) {
            // load from external directory
            if (TEMPLATE_DIR != null) {
                compiled = TemplateCompiler.compileTemplate(new File(TEMPLATE_DIR, key));
            } else {
                // load from class path
                InputStream stream = null;
                try {
                    stream = EmailUtil.class.getResourceAsStream(key);
                    compiled = TemplateCompiler.compileTemplate(stream);
                } finally {
                    if (stream != null) {
                        stream.close();
                    }
                }
            }
            // add to the template cache.
            if (compiled != null && TEMPLATE_DIR == null) {
                TEMPLATES.put(key, compiled);
            }
        }
        // create the result
        String result = (String) TemplateRuntime.execute(compiled, parameters);
        return result;
    }

    public static boolean validate(String email) {
        return PATTERN.matcher(email).matches();
    }

    /**
     * Gets the email GUID.
     *
     * @param email the email.
     * @return the email GUID or <code>null</code> if the email * is
     * <code>null</code>.
     */
    public static Object getGuid(Email email) {
        Object result = null;
        if (email != null) {
            result = email.getGuid();
        }
        return result;
    }

    /**
     * Creates the list of addresses.
     *
     * @param addresses the set of addresses.
     * @return the list of addresses.
     * @throws Exception if the method fails.
     */
    public static Address[] createAddresses(Set<String> addresses) throws Exception {
        Address[] result = null;
        if (addresses != null && !addresses.isEmpty()) {
            List<Address> items = new ArrayList<>();
            for (String address : addresses) {
                Address item = createAddress(address);
                items.add(item);
            }

            if (!items.isEmpty()) {
                result = items.toArray(new Address[items.size()]);
            }
        }
        return result;
    }

    /**
     * Creates the email address object.
     *
     * @param address the address as a string.
     * @return the email address object.
     * @throws Exception if the method fails.
     */
    public static Address createAddress(String address) throws Exception {
        Address result = null;
        if (address != null) {
            result = new InternetAddress(address);
        }
        return result;
    }
}
