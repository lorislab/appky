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
import java.util.Date;
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
import org.lorislab.appky.application.criteria.EmailRequestCriteria;
import org.lorislab.appky.application.model.EmailRequest;
import org.lorislab.appky.application.model.EmailRequest_;
import org.lorislab.appky.application.resources.ErrorKeys;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;
import org.lorislab.jel.jpa.criteria.PersistenceSearchCriteriaUtil;
import org.lorislab.jel.jpa.model.util.PersistentUtil;

/**
 * The email request service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmailRequestService extends AbstractEntityServiceBean<EmailRequest> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4176774913105021031L;

    /**
     * Finds the registration request by GUID.
     *
     * @param guid the object GUID.
     * @return the email registration request.
     * @throws ServiceException if the method fails.
     */
    public EmailRequest findRegistrationRequestByGuid(Object guid) throws ServiceException {
        EmailRequest result = null;
        try {
            result = getById(guid);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.FIND_REGISTRATION_REQUEST_BY_GUID_FAILED, (String)guid, ex, (String)guid);
        }
        return result;
    }

    /**
     * Saves the registration request.
     *
     * @param request the registration request.
     * @return the new saved registration request.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public EmailRequest saveRegistrationRequest(EmailRequest request) throws ServiceException {
        EmailRequest result = null;
        try {
            result = save(request);
        } catch (Exception ex) {
            String guid = PersistentUtil.getGuid(request);
            throw new ServiceException(ErrorKeys.SAVE_REGISTRATION_REQUEST_FAILED, ex, guid);
        }
        return result;
    }

    /**
     * Finds the registration request by criteria.
     *
     * @param searchCriteria the search criteria.
     * @return the list of email request corresponding to the criteria.
     * @throws ServiceException if the method fails.
     */    
    public List<EmailRequest> findRegistrationRequestByCriteria(EmailRequestCriteria searchCriteria) throws ServiceException {
        List<EmailRequest> result = new ArrayList<>();
        try {
            CriteriaQuery<EmailRequest> criteria = getBaseEAO().createCriteriaQuery();
            CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
            Root<EmailRequest> root = criteria.from(EmailRequest.class);
            List<Predicate> predicates = new ArrayList<>();

            if (searchCriteria.getGuid() != null) {
                Predicate condition = cb.equal(root.get(EmailRequest_.guid), searchCriteria.getGuid());
                predicates.add(condition);
            }

            if (searchCriteria.getEmail() != null) {
                Predicate condition = cb.equal(root.get(EmailRequest_.email), searchCriteria.getEmail());
                predicates.add(condition);
            }

            if (searchCriteria.getStatus() != null) {
                Predicate condition = cb.equal(root.get(EmailRequest_.status), searchCriteria.getStatus());
                predicates.add(condition);
            }

            if (searchCriteria.getType() != null) {
                Predicate condition = cb.equal(root.get(EmailRequest_.type), searchCriteria.getType());
                predicates.add(condition);
            }

            if (searchCriteria.isValidateTo()) {
                Predicate condition2 = cb.greaterThanOrEqualTo(root.get(EmailRequest_.validateTo), new Date());
                predicates.add(condition2);
            }

            Predicate creationDate = PersistenceSearchCriteriaUtil.createBetweenPredicate(cb, root.get(EmailRequest_.creationDate), searchCriteria.getCreationDate());
            if (creationDate != null) {
                predicates.add(creationDate);
            }

            if (!predicates.isEmpty()) {
                criteria.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            }

            TypedQuery<EmailRequest> typeQuery = getBaseEAO().createTypedQuery(criteria);
            typeQuery.setMaxResults(100);
            result = typeQuery.getResultList();

        } catch (NoResultException ne) {
            // do nothing, result list is empty
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.FIND_REGISTRATION_REQUEST_BY_CRITERIA_FAILED, ex);
        }
        return result;
    }
}
