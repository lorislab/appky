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

import java.io.Serializable;
import java.util.List;
import org.lorislab.appky.application.criteria.RoleSearchCriteria;
import org.lorislab.appky.application.model.Role;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The role service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface RoleServiceLocal extends Serializable {

    /**
     * Save the role.
     *
     * @param role the role.
     * @return the new saved role.
     * @throws ServiceException if the method fails.
     */
    Role saveRole(Role role) throws ServiceException;

    /**
     * Loads the role by GUID.
     *
     * @param guid the role GUID.
     * @return the role corresponding to the role.
     * @throws ServiceException if the method fails.
     */
    Role loadRole(Object guid) throws ServiceException;

    /**
     * Deletes the role.
     *
     * @param role the role.
     * @return <code>true</code> if the role was deleted.
     * @throws ServiceException if the method fails.
     */
    boolean deleteRole(Role role) throws ServiceException;

    /**
     * Finds roles by criteria.
     *
     * @param criteria the role search criteria.
     * @return the list of roles corresponding to the search criteria.
     * @throws ServiceException if the method fails.
     */
    List<Role> findRolesByCriteria(RoleSearchCriteria criteria) throws ServiceException;
}
