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
import org.lorislab.appky.application.criteria.GroupSearchCriteria;
import org.lorislab.appky.application.model.Group;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The group service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface GroupServiceLocal {

    /**
     * Saves the group.
     *
     * @param group the group.
     * @return the saved group.
     * @throws ServiceException if the method fails.
     */
    Group saveGroup(Group group) throws ServiceException;

    /**
     * Loads the group by GUID.
     *
     * @param guid the group GUID.
     * @return the group corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    Group loadGroup(String guid) throws ServiceException;

    /**
     * Deletes the group.
     *
     * @param group the group.
     * @return <code>true</code> if the group was deleted.
     * @throws ServiceException if the method fails.
     */
    boolean deleteGroup(Group group) throws ServiceException;

    /**
     * Loads the full group by GUID.
     *
     * @param guid the group GUID.
     * @return the group corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    Group loadFullGroupByGuid(String guid) throws ServiceException;

    /**
     * Finds the group by the search criteria.
     *
     * @param criteria the search criteria.
     * @return the list of group corresponding to the search criteria.
     * @throws ServiceException if the method fails.
     */
    List<Group> findGroupsBySearchCriteria(GroupSearchCriteria criteria) throws ServiceException;
}
