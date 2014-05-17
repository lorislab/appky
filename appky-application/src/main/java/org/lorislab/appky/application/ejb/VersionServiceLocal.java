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
import org.lorislab.appky.application.criteria.VersionSearchCriteria;
import org.lorislab.appky.application.model.Version;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The version service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface VersionServiceLocal {

    /**
     * Saves the version.
     *
     * @param version the version.
     * @return the saved version.
     * @throws ServiceException if the method fails.
     */
    Version saveVersion(Version version) throws ServiceException;

    /**
     * Loads the version by GUID.
     *
     * @param guid the version GUID.
     * @return the version corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    Version loadVersion(String guid) throws ServiceException;

    /**
     * Loads the full version by version GUID.
     *
     * @param guid the version GUID.
     * @return the version corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    Version loadFullVersion(String guid) throws ServiceException;

    /**
     * Deletes the version.
     *
     * @param version the version.
     * @return <code>true</code> if the version was deleted.
     * @throws ServiceException if the method fails.
     */
    boolean deleteVersion(Version version) throws ServiceException;

    /**
     * Finds the list of versions by search criteria.
     *
     * @param criteria the search criteria.
     * @return the list of version corresponding to the search criteria.
     * @throws ServiceException if the method fails.
     */
    List<Version> findVersionsBySearchCriteria(VersionSearchCriteria criteria) throws ServiceException;
}
