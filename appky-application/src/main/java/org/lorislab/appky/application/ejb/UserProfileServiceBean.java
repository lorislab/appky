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


import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.lorislab.appky.application.criteria.UserProfileSearchCriteria;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.UserApplication_;
import org.lorislab.appky.application.model.UserConfigParam;
import org.lorislab.appky.application.model.UserConfigParam_;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.UserProfile_;
import org.lorislab.appky.application.resources.ErrorKeys;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;
import org.lorislab.jel.jpa.model.util.PersistentUtil;

/**
 * The user profile service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(UserProfileServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserProfileServiceBean extends AbstractEntityServiceBean<UserProfile> implements UserProfileServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5164176256589437727L;

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserProfile saveUserProfile(UserProfile userProfile) throws ServiceException {
        UserProfile result = null;
        try {
            result = this.save(userProfile);
        } catch (Exception ex) {
            String guid = PersistentUtil.getGuid(userProfile);
            throw new ServiceException(ErrorKeys.SAVE_USER_PROFILE_FAILED, ex, guid);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteUserProfile(UserProfile userProfile) throws ServiceException {
        boolean result = false;
        try {
            result = this.delete(userProfile);
        } catch (Exception ex) {
            String guid = PersistentUtil.getGuid(userProfile);
            throw new ServiceException(ErrorKeys.DELETE_USER_PROFILE_FAILED, ex, guid);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserProfile> findUserProfilesByCriteria(UserProfileSearchCriteria searchCriteria) throws ServiceException {
        List<UserProfile> result = null;
        if (searchCriteria != null) {
            try {
                CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
                CriteriaQuery<UserProfile> cq = getBaseEAO().createCriteriaQuery();
                Root<UserProfile> root = cq.from(UserProfile.class);

                List<Predicate> predicates = new ArrayList<>();

                if (searchCriteria.getFirstName() != null) {
                    Predicate condition = cb.equal(root.get(UserProfile_.firstName), searchCriteria.getFirstName());
                    predicates.add(condition);
                }

                if (searchCriteria.getMiddleName() != null) {
                    Predicate condition = cb.equal(root.get(UserProfile_.middleName), searchCriteria.getMiddleName());
                    predicates.add(condition);
                }

                if (searchCriteria.getLastName() != null) {
                    Predicate condition = cb.equal(root.get(UserProfile_.lastName), searchCriteria.getLastName());
                    predicates.add(condition);
                }

                if (searchCriteria.getEmail() != null) {
                    Predicate condition = cb.equal(root.get(UserProfile_.email), searchCriteria.getEmail());
                    predicates.add(condition);
                }

                if (searchCriteria.getDeleted() != null) {
                    Predicate condition = cb.equal(root.get(UserProfile_.deleted), searchCriteria.getDeleted());
                    predicates.add(condition);
                }

                if (searchCriteria.getEnabled() != null) {
                    Predicate condition = cb.equal(root.get(UserProfile_.enabled), searchCriteria.getEnabled());
                    predicates.add(condition);
                }

                if (searchCriteria.getParameterName() != null || searchCriteria.getParameterValue() != null) {
                    Join<UserProfile, UserConfigParam> params = root.join(UserProfile_.properties, JoinType.LEFT);
                    if (searchCriteria.getParameterName() != null) {
                        predicates.add(cb.equal(params.get(UserConfigParam_.name), searchCriteria.getParameterName()));
                    }
                    if (searchCriteria.getParameterValue() != null) {
                        predicates.add(cb.equal(params.get(UserConfigParam_.value), searchCriteria.getParameterValue()));
                    }
                }

                if (searchCriteria.getUserApplicationStatus() != null || searchCriteria.getPlatformGuid() != null) {
                    Subquery<String> sq = cq.subquery(String.class);
                    Root<UserApplication> userApp = sq.from(UserApplication.class);

                    List<Predicate> subPredicates = new ArrayList<>();
                    if (searchCriteria.getPlatformGuid() != null) {
                        subPredicates.add(cb.equal(userApp.get(UserApplication_.platformGuid), searchCriteria.getPlatformGuid()));
                    }

                    if (searchCriteria.getUserApplicationStatus() != null) {
                        subPredicates.add(cb.equal(userApp.get(UserApplication_.status), searchCriteria.getUserApplicationStatus()));
                    }

                    sq.select(userApp.get(UserApplication_.userGuid)).
                            where(cb.and(subPredicates.toArray(new Predicate[subPredicates.size()])));

                    Predicate condition = cb.in(root.get(UserProfile_.guid)).value(sq);
                    predicates.add(condition);
                }

                if (!predicates.isEmpty()) {
                    cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                }

                TypedQuery<UserProfile> typeQuery = getBaseEAO().createTypedQuery(cq);
                result = typeQuery.getResultList();

            } catch (Exception ex) {
                throw new ServiceException(ErrorKeys.SAVE_USER_PROFILE_FAILED, ex);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserProfile loadFullUserProfile(String guid) throws ServiceException {
        UserProfile result = null;
        try {
            CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
            CriteriaQuery<UserProfile> cq = getBaseEAO().createCriteriaQuery();
            Root<UserProfile> root = cq.from(UserProfile.class);
            root.fetch(UserProfile_.roles, JoinType.LEFT);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get(UserProfile_.guid), guid));

            if (!predicates.isEmpty()) {
                cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            }

            TypedQuery<UserProfile> typeQuery = getBaseEAO().createTypedQuery(cq);

            List<UserProfile> tmp = typeQuery.getResultList();
            if (tmp != null && !tmp.isEmpty()) {
                result = tmp.get(0);
            }
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserProfile loadUserProfile(String guid) throws ServiceException {
        UserProfile result = null;
        try {
            result = this.getById(guid);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.LOAD_USER_PROFILE_FAILED, ex, guid);
        }

        return result;
    }
}
