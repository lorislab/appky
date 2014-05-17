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
package org.lorislab.appky.application.ejb;


import java.util.List;
import org.lorislab.appky.application.criteria.EmailRequestCriteria;
import org.lorislab.appky.application.model.EmailRequest;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The email request service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface EmailRequestServiceLocal {

    /**
     * Finds the registration request by criteria.
     *
     * @param searchCriteria the search criteria.
     * @return the list of email request corresponding to the criteria.
     * @throws ServiceException if the method fails.
     */
    List<EmailRequest> findRegistrationRequestByCriteria(EmailRequestCriteria searchCriteria) throws ServiceException;

    /**
     * Finds the registration request by GUID.
     *
     * @param guid the object GUID.
     * @return the email registration request.
     * @throws ServiceException if the method fails.
     */
    EmailRequest findRegistrationRequestByGuid(Object guid) throws ServiceException;

    /**
     * Saves the registration request.
     *
     * @param request the registration request.
     * @return the new saved registration request.
     * @throws ServiceException if the method fails.
     */
    EmailRequest saveRegistrationRequest(EmailRequest request) throws ServiceException;
}
