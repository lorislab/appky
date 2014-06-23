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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.appky.application.criteria.UserApplicationSearchCriteria;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.UserApplication_;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The user application service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserApplicationService extends AbstractEntityServiceBean<UserApplication> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 5281009772925126566L;

    /**
     * Saves the user application.
     *
     * @param userApp the user application.
     * @return the new saved user application.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserApplication saveUserApplication(UserApplication userApp) throws ServiceException {
        return this.save(userApp);
    }

    /**
     * Finds the user application by GUID.
     *
     * @param guid the user application by GUID.
     * @return the user application.
     * @throws ServiceException if the method fails.
     */
    public UserApplication findUserApplicationByGuid(String guid) throws ServiceException {
        return this.getById(guid);
    }

    /**
     * Finds the user applications by search criteria.
     *
     * @param searchCriteria the search criteria.
     * @return the list of user application corresponding to the search
     * criteria.
     * @throws ServiceException
     */
    public List<UserApplication> findUserApplicationsByCriteria(UserApplicationSearchCriteria searchCriteria) throws ServiceException {

        List<UserApplication> result = new ArrayList<>();
        try {

            CriteriaQuery<UserApplication> criteria = getBaseEAO().createCriteriaQuery();
            CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
            Root<UserApplication> root = criteria.from(UserApplication.class);

            List<Predicate> predicates = new ArrayList<>();

            if (searchCriteria.getPlatformGuid() != null) {
                Predicate condition = cb.equal(root.get(UserApplication_.platformGuid), searchCriteria.getPlatformGuid());
                predicates.add(condition);
            }

            if (searchCriteria.getUserGuid() != null) {
                Predicate condition = cb.equal(root.get(UserApplication_.userGuid), searchCriteria.getUserGuid());
                predicates.add(condition);
            }

            if (searchCriteria.getApplicationGuid() != null) {
                Predicate condition = cb.equal(root.get(UserApplication_.applicationGuid), searchCriteria.getApplicationGuid());
                predicates.add(condition);
            }

            if (searchCriteria.getStatus() != null) {
                Predicate condition = cb.equal(root.get(UserApplication_.status), searchCriteria.getStatus());
                predicates.add(condition);
            }

            if (searchCriteria.getVersionGuid() != null) {
                Predicate condition = cb.equal(root.get(UserApplication_.versionGuid), searchCriteria.getVersionGuid());
                predicates.add(condition);
            }

            if (!predicates.isEmpty()) {
                criteria.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            }

            TypedQuery<UserApplication> typeQuery = getBaseEAO().createTypedQuery(criteria);
            result = typeQuery.getResultList();


        } catch (NoResultException ne) {
            // do nothing
        }
        return result;
    }
}
