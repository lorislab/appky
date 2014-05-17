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
import org.lorislab.appky.application.criteria.PlatformSearchCriteria;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The platform service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface PlatformServiceLocal {

    /**
     * Loads platform by GUID.
     *
     * @param guid the platform GUID.
     * @return the platform corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    Platform loadPlatformByGuid(String guid) throws ServiceException;

    /**
     * Saves the platform.
     *
     * @param platform the platform.
     * @return the saved platform.
     * @throws ServiceException if the method fails.
     */
    Platform savePlatform(Platform platform) throws ServiceException;

    /**
     * Loads full platform by GUID.
     *
     * @param guid the platform GUID.
     * @return the platform corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    Platform loadFullPlatformByGuid(String guid) throws ServiceException;

    /**
     * Deletes the platform.
     *
     * @param platform the platform.
     * @return <code>true</code> if the platform was deleted.
     * @throws ServiceException if the method fails.
     */
    boolean deletePlatform(Platform platform) throws ServiceException;

    /**
     * Finds the list of platform by search criteria.
     *
     * @param criteria the platform search criteria.
     * @return the list of platform corresponding the search criteria.
     * @throws ServiceException if the method fails.
     */
    List<Platform> findsPlatformBySearchCriteria(PlatformSearchCriteria criteria) throws ServiceException;
}