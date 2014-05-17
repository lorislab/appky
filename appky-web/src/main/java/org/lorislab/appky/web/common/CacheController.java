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
package org.lorislab.appky.web.common;


import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.lorislab.appky.application.factory.ApplicationObjectFactory;
import org.lorislab.appky.application.model.Document;

/**
 * The web cache controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("cache")
@ApplicationScoped
public class CacheController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -642243835118102824L;
    /**
     * The cache.
     */
    private Map<String, Document> tmp = new HashMap<>();

    /**
     * Load document from context.
     *
     * @param context the servlet context.
     * @param path the default document path.
     * @return the document.
     */
    private Document loadDocumentFromContext(ServletContext context, String path) {
        Document result = null;
        result = ApplicationObjectFactory.createDocument();

        InputStream in = null;
        try {
            URL url = context.getResource(path);
            in = url.openStream();
            byte[] bytes = IOUtils.toByteArray(in);
            if (bytes != null) {
                result.setContentSize(bytes.length);
                result.setName(path);
                result.getContent().setBytes(bytes);
            }
        } catch (Exception ex) {
            Logger.getLogger(CacheController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(CacheController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        tmp.put(path, result);
        return result;
    }

    /**
     * Gets the document by servlet context and default path.
     *
     * @param context the servlet context.
     * @param path the default path.
     * @return the document.
     */
    public Document getDocument(ServletContext context, String path) {
        Document result = tmp.get(path);
        if (result == null) {
            result = loadDocumentFromContext(context, path);
        }
        return result;
    }
}
