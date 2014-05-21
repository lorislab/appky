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
package org.lorislab.appky.web.rs;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.lorislab.appky.email.template.ejb.MailTemplateServiceLocal;
import org.lorislab.appky.email.template.model.MailTemplateResource;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The email template rest-service.
 *
 * @author Andrej_Petras
 */
@Path("public/mail")
public class MailTemplateService {

    /**
     * The mail template service.
     */
    @EJB
    private MailTemplateServiceLocal mailTemplateService;

    /**
     * Gets the email template image (resource).
     *
     * @param template the template name.
     * @param name the resource name.
     *
     * @return the resource content.
     *
     * @throws ServiceException if the method fails.
     */
    @GET
    @Path("resource/{template}/{name}")
    @Produces({"image/jpeg", "image/png", "image/gif"})
    public byte[] getMailResource(@PathParam("template") String template, @PathParam("name") String name) throws ServiceException {
        byte[] result = null;
        MailTemplateResource resource = mailTemplateService.loadMailTemplateResource(template, name);
        if (resource != null) {
            result = resource.getContent();
        }
        return result;
    }
}
