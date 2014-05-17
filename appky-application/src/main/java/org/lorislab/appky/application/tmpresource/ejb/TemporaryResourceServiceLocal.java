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
package org.lorislab.appky.application.tmpresource.ejb;

import java.util.Date;
import java.util.List;
import org.lorislab.appky.application.tmpresource.criteria.TemporaryResourceSearchCriteria;
import org.lorislab.appky.application.tmpresource.model.TemporaryResource;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The temporary resource service locale interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface TemporaryResourceServiceLocal {

    /**
     * Deletes all temporary resources
     * @param date the validation date.
     * @return returns the count of deleted entities.
     * @throws ServiceException if the method fails.
     */
    int deleteTemporaryResourcesByValidationDate(Date date) throws ServiceException;

    /**
     * Saves the temporary resource.
     *
     * @param resource the temporary resource.
     * @return the saved temporary resource.
     * @throws ServiceException if the method fails.
     */
    TemporaryResource saveTemporaryResource(TemporaryResource resource) throws ServiceException;

    /**
     * Loads the temporary resource by GUID.
     *
     * @param guid the temporary resource GUID.
     * @return the temporary resource corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    TemporaryResource loadTemporaryResourceByGuid(String guid) throws ServiceException;

    /**
     * Finds the list of temporary resources by search criteria.
     *
     * @param criteria the search criteria.
     * @return the list of temporary resources corresponding to the search
     * criteria.
     * @throws ServiceException if the method fails.
     */
    List<TemporaryResource> findTemporaryResourcesBySearchCriteria(TemporaryResourceSearchCriteria criteria) throws ServiceException;
}
