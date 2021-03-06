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
package org.lorislab.appky.application.tmpresource.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.appky.application.tmpresource.criteria.TemporaryResourceSearchCriteria;
import org.lorislab.appky.application.tmpresource.model.TemporaryResource;
import org.lorislab.appky.application.tmpresource.model.TemporaryResourceData;
import org.lorislab.appky.application.tmpresource.model.TemporaryResourceData_;
import org.lorislab.appky.application.tmpresource.model.TemporaryResource_;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The temporary service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
public class TemporaryResourceService extends AbstractEntityServiceBean<TemporaryResource> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -670618413508635886L;

    /**
     * Saves the temporary resource.
     *
     * @param resource the temporary resource.
     * @return the saved temporary resource.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public TemporaryResource saveTemporaryResource(TemporaryResource resource) throws ServiceException {
        return this.save(resource);
    }

    /**
     * Loads the temporary resource by GUID.
     *
     * @param guid the temporary resource GUID.
     * @return the temporary resource corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    public TemporaryResource loadTemporaryResourceByGuid(String guid) throws ServiceException {
        return this.getById(guid);
    }

    /**
     * Finds the list of temporary resources by search criteria.
     *
     * @param criteria the search criteria.
     * @return the list of temporary resources corresponding to the search
     * criteria.
     * @throws ServiceException if the method fails.
     */
    public List<TemporaryResource> findTemporaryResourcesBySearchCriteria(TemporaryResourceSearchCriteria criteria) throws ServiceException {
        List<TemporaryResource> result = new ArrayList<>();
        try {

            CriteriaQuery<TemporaryResource> cq = getBaseEAO().createCriteriaQuery();
            CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
            Root<TemporaryResource> root = cq.from(TemporaryResource.class);
            List<Predicate> predicates = new ArrayList<>();
            root.fetch(TemporaryResource_.resources);
            
            if (criteria.getGuid() != null) {
                predicates.add(cb.equal(root.get(TemporaryResource_.guid), criteria.getGuid()));
            }
            if (criteria.getValidateTo() != null) {
                predicates.add(cb.equal(root.get(TemporaryResource_.validateTo), criteria.getValidateTo()));
            }

            if (criteria.getResource() != null) {
                Join<TemporaryResource, TemporaryResourceData> data = root.join(TemporaryResource_.resources);
                Predicate condition = cb.equal(data.get(TemporaryResourceData_.resourceGuid), criteria.getResource());
                predicates.add(condition);
            }

            cq.distinct(true);
            
            if (!predicates.isEmpty()) {
                cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            }

            TypedQuery<TemporaryResource> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();

        } catch (NoResultException ne) {
            // do nothing
        }
        return result;
    }

    /**
     * Deletes all temporary resources
     * @param date the validation date.
     * @return returns the count of deleted entities.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int deleteTemporaryResourcesByValidationDate(Date date) throws ServiceException {
        //FIXME: create name queries
        Query query = this.baseEAO.createQuery("DELETE FROM TemporaryResourceData d WHERE d.parent in (SELECT t FROM TemporaryResource t WHERE t.validateTo < :date)");
        query.setParameter("date", date).executeUpdate();
        
        query = this.baseEAO.createQuery("DELETE FROM TemporaryResource t WHERE t.validateTo < :date");
        int result = query.setParameter("date", date).executeUpdate();
        return result;
    }
}
