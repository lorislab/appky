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
package org.lorislab.appky.web.rs;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.lorislab.appky.application.ejb.DocumentService;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.process.model.ApplicationPackage;
import org.lorislab.appky.web.common.CacheController;
import org.lorislab.appky.web.mobile.view.MobilePlatformViewController;
import org.lorislab.jel.ejb.exception.ServiceException;


/**
 * The mobile document rest service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Path("mobile/document")
public class DocumentRestService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(DocumentRestService.class.getName());
    /**
     * The document service.
     */
    @EJB
    private DocumentService service;
    /**
     * The mobile platform view controller.
     */
    @Inject
    private MobilePlatformViewController mobilePlatformVC;
    /**
     * The cache controller.
     */
    @Inject
    private CacheController cacheController;
    /**
     * The servlet context.
     */
    @Context
    private ServletContext context;

    /**
     * Gets the image by the resource GUID.
     *
     * @param resource the resource GUID.
     * @param path the default image path.
     * @return the image content.
     * @throws ServiceException if the method fails.
     */
    @GET
    @Path("image/{resource}")
    @Produces("image/*")
    public byte[] getImage(@PathParam("resource") String resource, @QueryParam("path") String path) throws ServiceException {
        Document document = service.loadDocumentFullByGuid(resource);
        if (document == null || document.getContentSize() <= 0) {
            document = cacheController.getDocument(context, path);
        }
        return document.getContent().getBytes();
    }

    /**
     * Gets the package for the version.
     *
     * @return the package.
     * @throws ServiceException if the method fails.
     */
    @GET
    @Path("package")
    public Response getPackage() throws ServiceException {
        Response result;
        if (mobilePlatformVC != null) {
            try {
                ApplicationPackage tmp = mobilePlatformVC.processApplication();
                result = Response.ok().entity(tmp.getContent()).type(tmp.getMimeType()).build();
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error process the application", ex);
                result = Response.status(500).build();
            }
        } else {
            result = Response.status(500).build();
        }
        return result;
    }
}
