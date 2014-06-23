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

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.lorislab.appky.application.ejb.DocumentService;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.tmpresource.criteria.TemporaryResourceSearchCriteria;
import org.lorislab.appky.application.tmpresource.ejb.TemporaryResourceService;
import org.lorislab.appky.application.tmpresource.model.TemporaryResource;
import org.lorislab.jel.ejb.exception.ServiceException;


/**
 * The public document service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Path("public/mobile")
public class PublicDocumentRestService {

    /**
     * The document service.
     */
    @EJB
    private DocumentService service;
    /**
     * The temporary resource service.
     */
    @EJB
    private TemporaryResourceService tmpService;

    /**
     * Gets the image by temporary resource link and GUID.
     *
     * @param tmp the temporary resource GUID.
     * @param resource the resource GUID.
     * @return the document content.
     * @throws ServiceException if the method fails.
     */
    @GET
    @Path("image/{tmp}/{resource}")
    @Produces({"image/jpeg", "image/png", "image/gif"})
    public byte[] getImage(@PathParam("tmp") String tmp, @PathParam("resource") String resource) throws ServiceException {
        return getContent(tmp, resource);
    }

    /**
     * Gets the IPA package by temporary resource link and GUID.
     *
     * @param tmp the temporary resource GUID.
     * @param resource the resource GUID.
     * @return the document content.
     * @throws ServiceException if the method fails.
     */
    @GET
    @Path("ipa/{tmp}/{resource}")
    @Produces("application/octet-stream")
    public byte[] getIpa(@PathParam("tmp") String tmp, @PathParam("resource") String resource) throws ServiceException {
        return getContent(tmp, resource);
    }

    /**
     * Gets the document content.
     *
     * @param tmp the temporary resource GUID.
     * @param resource the resource GUID.
     * @return the document content.
     * @throws ServiceException
     */
    private byte[] getContent(String tmp, String resource) throws ServiceException {
        byte[] result = null;
        TemporaryResourceSearchCriteria criteria = new TemporaryResourceSearchCriteria();
        criteria.setGuid(tmp);
        criteria.setResource(resource);
        List<TemporaryResource> resources = tmpService.findTemporaryResourcesBySearchCriteria(criteria);
        TemporaryResource tmpResource = null;
        if (resources != null && !resources.isEmpty()) {
            tmpResource = resources.get(0);
        }
        if (tmpResource != null) {
            Document document = service.loadDocumentFullByGuid(resource);
            if (document != null) {
                result = document.getContent().getBytes();
            }
        }
        return result;
    }
}
