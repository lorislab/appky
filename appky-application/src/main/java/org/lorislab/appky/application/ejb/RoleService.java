/*
 * Copyright 2014 lorislab.org.
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


import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.appky.application.criteria.RoleSearchCriteria;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.model.Role_;
import org.lorislab.appky.application.resources.ErrorKeys;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;
import org.lorislab.jel.jpa.model.util.PersistentUtil;

/**
 * The role service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RoleService extends AbstractEntityServiceBean<Role> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 4649808861133716367L;

    /**
     * Save the role.
     *
     * @param role the role.
     * @return the new saved role.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Role saveRole(Role role) throws ServiceException {
        Role result = null;
        try {
            result = this.save(role);
        } catch (Exception ex) {
            String guid = PersistentUtil.getGuid(role);
            throw new ServiceException(ErrorKeys.SAVE_ROLE_FAILED, ex, guid);
        }
        return result;
    }

    /**
     * Loads the role by GUID.
     *
     * @param guid the role GUID.
     * @return the role corresponding to the role.
     * @throws ServiceException if the method fails.
     */
    public Role loadRole(Object guid) throws ServiceException {
        Role result = null;
        try {
            result = this.getById(guid);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.LOAD_ROLE_FAILED, (String)guid, ex, (String)guid);
        }
        return result;
    }

    /**
     * Finds roles by criteria.
     *
     * @param criteria the role search criteria.
     * @return the list of roles corresponding to the search criteria.
     * @throws ServiceException if the method fails.
     */
    public List<Role> findRolesByCriteria(RoleSearchCriteria searchCriteria) throws ServiceException {
        List<Role> result = new ArrayList<>();
        if (searchCriteria != null) {
            try {
                CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
                CriteriaQuery<Role> cq = getBaseEAO().createCriteriaQuery();
                Root<Role> root = cq.from(Role.class);

                List<Predicate> predicates = new ArrayList<>();

                if (searchCriteria.getName() != null) {
                    Predicate condition = cb.equal(root.get(Role_.name), searchCriteria.getName());
                    predicates.add(condition);
                }

                if (searchCriteria.getDeleted() != null) {
                    Predicate condition = cb.equal(root.get(Role_.deleted), searchCriteria.getDeleted());
                    predicates.add(condition);
                }

                if (searchCriteria.getEditable() != null) {
                    Predicate condition = cb.equal(root.get(Role_.editable), searchCriteria.getEditable());
                    predicates.add(condition);
                }

                if (!predicates.isEmpty()) {
                    cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                }

                TypedQuery<Role> typeQuery = getBaseEAO().createTypedQuery(cq);
                result = typeQuery.getResultList();

            } catch (Exception ex) {
                throw new ServiceException(ErrorKeys.FIND_ALL_ROLES_FAILED, ex);
            }
        }
        return result;
    }

    /**
     * Deletes the role.
     *
     * @param role the role.
     * @return <code>true</code> if the role was deleted.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteRole(Role role) throws ServiceException {
        boolean result = false;
        try {
            result = delete(role);
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }
}
