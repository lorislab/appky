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
package org.lorislab.appky.application.wrapper.ejb;

import java.util.List;
import java.util.Locale;
import org.lorislab.appky.application.criteria.ApplicationSearchCriteria;
import org.lorislab.appky.application.criteria.GroupSearchCriteria;
import org.lorislab.appky.application.wrapper.model.ApplicationWrapper;
import org.lorislab.appky.application.wrapper.model.GroupWrapper;
import org.lorislab.appky.application.wrapper.model.PlatformWrapper;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The application wrapper service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface ApplicationWrapperServiceLocal {

    /**
     * Loads the platform wrapper by platform GUID.
     *
     * @param guid the platform GUID.
     * @param userGuid the user GUID.
     * @return the platform wrapper.
     * @throws ServiceException if the method fails.
     */
    PlatformWrapper loadPlatformWrapperByPlatformGuid(String guid, String userGuid, Locale userLocale, Locale defaultUserLocale) throws ServiceException;

    /**
     * Finds the list of group wrappers by search criteria.
     *
     * @param searchCriteria the group search criteria.
     * @return the list of group wrappers.
     * @throws ServiceException if the method fails.
     */
    List<GroupWrapper> findGroupsBySearchCriteria(GroupSearchCriteria searchCriteria) throws ServiceException;

    /**
     * Finds the list of application wrappers by search criteria.
     *
     * @param searchCriteria the application search criteria.
     * @return the list of application wrappers.
     * @throws ServiceException if the method fails.
     */
    List<ApplicationWrapper> findApplicationsBySearchCriteria(ApplicationSearchCriteria searchCriteria) throws ServiceException;
}
