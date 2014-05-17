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
package org.lorislab.appky.application.ejb;

import java.util.List;
import org.lorislab.appky.application.criteria.UserApplicationSearchCriteria;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The user application service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface UserApplicationServiceLocal {

    /**
     * Finds the user application by GUID.
     *
     * @param guid the user application by GUID.
     * @return the user application.
     * @throws ServiceException if the method fails.
     */
    UserApplication findUserApplicationByGuid(String guid) throws ServiceException;

    /**
     * Saves the user application.
     *
     * @param userApp the user application.
     * @return the new saved user application.
     * @throws ServiceException if the method fails.
     */
    UserApplication saveUserApplication(UserApplication userApp) throws ServiceException;

    /**
     * Finds the user applications by search criteria.
     *
     * @param criteria the search criteria.
     * @return the list of user application corresponding to the search
     * criteria.
     * @throws ServiceException
     */
    List<UserApplication> findUserApplicationsByCriteria(UserApplicationSearchCriteria criteria) throws ServiceException;
}
