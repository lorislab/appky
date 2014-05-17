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
package org.lorislab.appky.email.template.util;
//
//import freemarker.cache.StringTemplateLoader;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
import java.io.StringWriter;
import java.util.Map;

/**
 * The email template builder.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class EmailTemplateBuilder {
    /**
     * The template directory.
     */
    private static final String TEMPLATE_DIR_NAME = "/templates/";
    /**
     * The path separator.
     */
    private static final String PATH_SEPARATOR = "/";    
    /**
     * The template.
     */
    private static final String TEMPLATE = "content";

    /**
     * The default constructor.
     */
    private EmailTemplateBuilder() {
        // empty constructor
    }

    /**
     * Gets the file path from template.
     * @param template
     * @param file
     * @return 
     */
    public static final String getFilePathFromTemplate(String template, String file) {
        return TEMPLATE_DIR_NAME + template + PATH_SEPARATOR + file;
    }
    
    /**
     * Gets the email content.
     *
     * @param template the template.
     * @param parameters the list of parameters.
     * @return the email content.
     * @throws Exception if the method fails.
     */
    public static String getEmailContent(String template, Map<String, Object> parameters) throws Exception {
        String result = null;
        if (template != null && !template.isEmpty()) {
//            Configuration cfg = new Configuration();
//
//            StringTemplateLoader stringLoader = new StringTemplateLoader();
//            stringLoader.putTemplate(TEMPLATE, template);
//            cfg.setTemplateLoader(stringLoader);
//
//            Template contentTmp = cfg.getTemplate(TEMPLATE);
//            final StringWriter writer = new StringWriter();
//            contentTmp.process(parameters, writer);
//            result = writer.toString();
            throw new UnsupportedOperationException();
        }
        return result;
    }
}
