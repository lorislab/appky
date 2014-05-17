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
import org.lorislab.appky.application.criteria.ApplicationSearchCriteria;
import org.lorislab.appky.application.model.Application;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The application service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface ApplicationServiceLocal {

    /**
     * Saves the application.
     *
     * @param application the application.
     * @return the saved application.
     * @throws ServiceException if the method fails.
     */
    Application saveApplication(Application application) throws ServiceException;

    /**
     * Loads the application.
     *
     * @param guid the application GUID.
     * @return the application corresponding to GUID.
     * @throws ServiceException if the method fails.
     */
    Application loadApplication(String guid) throws ServiceException;

    /**
     * Load the full application.
     *
     * @param guid the application GUID.
     * @return the application corresponding to GUID.
     * @throws ServiceException if the method fails.
     */
    Application loadFullApplication(String guid) throws ServiceException;

    /**
     * Deletes the application.
     *
     * @param application the application.
     * @return <code>true</code> if the application was deleted.
     * @throws ServiceException if the method fails.
     */
    boolean deleteApplication(Application application) throws ServiceException;

    /**
     * Finds the applications by search criteria.
     *
     * @param criteria the search criteria.
     * @return the list of application corresponding to the criteria.
     * @throws ServiceException if the method fails.
     */
    List<Application> findApplicationsBySearchCriteria(ApplicationSearchCriteria criteria) throws ServiceException;
}
