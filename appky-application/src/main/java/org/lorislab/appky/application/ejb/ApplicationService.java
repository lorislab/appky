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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.lorislab.appky.application.criteria.ApplicationSearchCriteria;
import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Application_;
import org.lorislab.appky.application.model.Description;
import org.lorislab.appky.application.model.Description_;
import org.lorislab.appky.application.model.Group;
import org.lorislab.appky.application.model.Group_;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.Platform_;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.model.Role_;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.UserProfile_;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The application service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ApplicationService extends AbstractEntityServiceBean<Application> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6909704154512518401L;

    /**
     * Saves the application.
     *
     * @param application the application.
     * @return the saved application.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Application saveApplication(Application application) throws ServiceException {
        Application result = this.save(application);
        return result;
    }

    /**
     * Loads the application.
     *
     * @param guid the application GUID.
     * @return the application corresponding to GUID.
     * @throws ServiceException if the method fails.
     */
    public Application loadApplication(String guid) throws ServiceException {
        Application result = getById(guid);
        return result;
    }

    /**
     * Deletes the application.
     *
     * @param application the application.
     * @return <code>true</code> if the application was deleted.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteApplication(Application application) throws ServiceException {
        return delete(application);
    }

    /**
     * Load the full application.
     *
     * @param guid the application GUID.
     * @return the application corresponding to GUID.
     * @throws ServiceException if the method fails.
     */
    public Application loadFullApplication(String guid) throws ServiceException {
        Application result = null;
        try {
            CriteriaBuilder criteriaBuilder = getBaseEAO().getCriteriaBuilder();
            CriteriaQuery<Application> criteriaQuery = getBaseEAO().createCriteriaQuery();
            Root<Application> root = criteriaQuery.from(Application.class);
            root.fetch(Application_.group, JoinType.LEFT);
            root.fetch(Application_.descriptions, JoinType.LEFT);
            root.fetch(Application_.titles, JoinType.LEFT);
            root.fetch(Application_.platforms, JoinType.LEFT);
            root.fetch(Application_.roles, JoinType.LEFT);
            criteriaQuery.distinct(true);

            criteriaQuery.where(criteriaBuilder.equal(root.get(Application_.guid), guid));
            TypedQuery<Application> query = getBaseEAO().createTypedQuery(criteriaQuery);
            List<Application> tmp = query.getResultList();
            if (tmp != null && !tmp.isEmpty()) {
                result = tmp.get(0);
            }
        } catch (NoResultException e) {
            // do nothing
        }
        return result;
    }

    /**
     * Finds the applications by search criteria.
     *
     * @param criteria the search criteria.
     * @return the list of application corresponding to the criteria.
     * @throws ServiceException if the method fails.
     */
    public List<Application> findApplicationsBySearchCriteria(ApplicationSearchCriteria criteria) throws ServiceException {
        List<Application> result = new ArrayList<>();
        if (criteria != null) {
            try {
                CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
                CriteriaQuery<Application> cq = getBaseEAO().createCriteriaQuery();
                Root<Application> root = cq.from(Application.class);
                cq.distinct(true);

                List<Predicate> predicates = new ArrayList<>();

                if (criteria.getUserLocale() != null || criteria.getDefaultUserLocale() != null) {
                    //FIXME: works only for hibernate       
                    Join<Application, Description> titles = (Join<Application, Description>) root.fetch(Application_.titles, JoinType.LEFT);
                    predicates.add(
                            cb.or(
                                    cb.equal(titles.get(Description_.locale), criteria.getUserLocale()),
                                    cb.equal(titles.get(Description_.locale), criteria.getDefaultUserLocale())));

                    Join<Application, Description> descriptions = (Join<Application, Description>) root.fetch(Application_.descriptions, JoinType.LEFT);
                    predicates.add(
                            cb.or(
                                    cb.equal(descriptions.get(Description_.locale), criteria.getUserLocale()),
                                    cb.equal(descriptions.get(Description_.locale), criteria.getDefaultUserLocale())));
                }

                if (criteria.getPlatformType() != null || criteria.getPlatformEnabled() != null) {
                    //FIXME: works only for hibernate
                    Join<Application, Platform> platforms = (Join<Application, Platform>) root.fetch(Application_.platforms, JoinType.LEFT);

                    if (criteria.getPlatformType() != null) {
                        predicates.add(cb.equal(platforms.get(Platform_.type), criteria.getPlatformType()));
                    }
                    if (criteria.getPlatformEnabled() != null) {
                        predicates.add(cb.equal(platforms.get(Platform_.enabled), true));
                    }
                }

                if (criteria.getDeleted() != null) {
                    Predicate condition = cb.equal(root.get(Application_.deleted), criteria.getDeleted());
                    predicates.add(condition);
                }

                if (criteria.getEnabled() != null) {
                    Predicate condition = cb.equal(root.get(Application_.enabled), criteria.getEnabled());
                    predicates.add(condition);
                }

                if (criteria.getName() != null) {
                    Predicate condition = cb.like(root.get(Application_.name), criteria.getName());
                    predicates.add(condition);
                }

                if (criteria.getGroupGuid() != null) {
                    Join<Application, Group> group = root.join(Application_.group);
                    root.fetch(Application_.group, JoinType.LEFT);
                    predicates.add(cb.equal(group.get(Group_.guid), criteria.getGroupGuid()));
                }

                if (criteria.getUserGuid() != null) {
                    Subquery<String> sq = cq.subquery(String.class);
                    Root<UserProfile> project = sq.from(UserProfile.class);
                    Join<UserProfile, Role> sqEmp = project.join(UserProfile_.roles);
                    Path<String> roles = root.join(Application_.roles).get(Role_.guid);

                    List<Predicate> subPredicates = new ArrayList<>();
                    subPredicates.add(cb.equal(project.get(UserProfile_.guid), criteria.getUserGuid()));

                    if (criteria.getUserRoleDeleted() != null) {
                        subPredicates.add(cb.equal(sqEmp.get(Role_.deleted), criteria.getUserRoleDeleted()));
                    }
                    if (criteria.getUserRoleEnabled() != null) {
                        subPredicates.add(cb.equal(sqEmp.get(Role_.enabled), criteria.getUserRoleEnabled()));
                    }
                    sq.select(sqEmp.get(Role_.guid)).
                            where(cb.and(subPredicates.toArray(new Predicate[subPredicates.size()])));

                    Predicate condition = cb.in(roles).value(sq);
                    predicates.add(condition);

                    sq.distinct(true);
                }
                if (!predicates.isEmpty()) {
                    cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                }

                TypedQuery<Application> typeQuery = getBaseEAO().createTypedQuery(cq);
                result = typeQuery.getResultList();

            } catch (NoResultException ex) {
                // do nothing
            }
        }
        return result;
    }

}
