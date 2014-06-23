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
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.lorislab.appky.application.criteria.GroupSearchCriteria;
import org.lorislab.appky.application.model.Description;
import org.lorislab.appky.application.model.Description_;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.model.Document_;
import org.lorislab.appky.application.model.Group;
import org.lorislab.appky.application.model.Group_;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.model.Role_;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.UserProfile_;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The group service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GroupService extends AbstractEntityServiceBean<Group> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6909704154512518401L;

    /**
     * Saves the group.
     *
     * @param group the group.
     * @return the saved group.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Group saveGroup(Group group) throws ServiceException {
        Group result = this.save(group);
        return result;
    }

    /**
     * Loads the group by GUID.
     *
     * @param guid the group GUID.
     * @return the group corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    public Group loadGroup(String guid) throws ServiceException {
        Group result = getById(guid);
        return result;
    }

    /**
     * Deletes the group.
     *
     * @param group the group.
     * @return <code>true</code> if the group was deleted.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteGroup(Group group) throws ServiceException {
        return delete(group);
    }

    /**
     * Loads the full group by GUID.
     *
     * @param guid the group GUID.
     * @return the group corresponding to the GUID.
     * @throws ServiceException if the method fails.
     */
    public Group loadFullGroupByGuid(String guid) throws ServiceException {
        Group result = null;
        try {
            CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
            CriteriaQuery<Group> criteriaQuery = getBaseEAO().createCriteriaQuery();
            Root<Group> root = criteriaQuery.from(Group.class);

            root.fetch(Group_.descriptions, JoinType.LEFT);
            root.fetch(Group_.titles, JoinType.LEFT);

            Fetch<Group, Document> icon = root.fetch(Group_.icon, JoinType.LEFT);
            icon.fetch(Document_.content, JoinType.LEFT);

            criteriaQuery.where(cb.equal(root.get(Group_.guid), guid));

            TypedQuery<Group> query = getBaseEAO().createTypedQuery(criteriaQuery);
            List<Group> g = query.getResultList();
            if (!g.isEmpty()) {
                result = g.get(0);
            }

        } catch (NoResultException e) {
            // do nothing
        }
        return result;
    }

    /**
     * Finds the group by the search criteria.
     *
     * @param criteria the search criteria.
     * @return the list of group corresponding to the search criteria.
     * @throws ServiceException if the method fails.
     */
    public List<Group> findGroupsBySearchCriteria(GroupSearchCriteria criteria) throws ServiceException {
        List<Group> result = new ArrayList<>();
        if (criteria != null) {
            try {
                CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
                CriteriaQuery<Group> cq = getBaseEAO().createCriteriaQuery();
                Root<Group> root = cq.from(Group.class);
                cq.distinct(true);
 
                List<Predicate> predicates = new ArrayList<>();
                
                if (criteria.getUserLocale() != null || criteria.getDefaultUserLocale() != null) {
                    //FIXME: works only for hibernate       
                    Join<Group, Description> titles = (Join<Group, Description>) root.fetch(Group_.titles, JoinType.LEFT);
                    predicates.add(
                            cb.or(
                            cb.equal(titles.get(Description_.locale), criteria.getUserLocale()),
                            cb.equal(titles.get(Description_.locale), criteria.getDefaultUserLocale())));
                    
                    Join<Group, Description> descriptions = (Join<Group, Description>) root.fetch(Group_.descriptions, JoinType.LEFT);
                    predicates.add(
                            cb.or(
                            cb.equal(descriptions.get(Description_.locale), criteria.getUserLocale()),
                            cb.equal(descriptions.get(Description_.locale), criteria.getDefaultUserLocale())));
                }

                if (criteria.getDeleted() != null) {
                    predicates.add(cb.equal(root.get(Group_.deleted), criteria.getDeleted()));
                }

                if (criteria.getEnabled() != null) {
                    predicates.add(cb.equal(root.get(Group_.enabled), criteria.getEnabled()));
                }

                if (criteria.getUserGuid() != null) {
                    Path<String> roles = root.join(Group_.roles).get(Role_.guid);

                    Subquery<String> sq = cq.subquery(String.class);
                    Root<UserProfile> project = sq.from(UserProfile.class);
                    Join<UserProfile, Role> sqEmp = project.join(UserProfile_.roles);

                    List<Predicate> subPredicates = new ArrayList<>();
                    if (criteria.getUserRoleDeleted() != null) {
                        subPredicates.add(cb.equal(sqEmp.get(Role_.deleted), criteria.getUserRoleDeleted()));
                    }
                    if (criteria.getUserRoleEnabled() != null) {
                        subPredicates.add(cb.equal(sqEmp.get(Role_.enabled), criteria.getUserRoleEnabled()));
                    }
                    subPredicates.add(cb.equal(project.get(UserProfile_.guid), criteria.getUserGuid()));


                    sq.select(sqEmp.get(Role_.guid)).
                            where(cb.and(subPredicates.toArray(new Predicate[subPredicates.size()])));

                    Predicate condition = cb.in(roles).value(sq);
                    predicates.add(condition);

                    cq.distinct(true);
                }

                if (!predicates.isEmpty()) {
                    cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                }

                TypedQuery<Group> typeQuery = getBaseEAO().createTypedQuery(cq);
                result = typeQuery.getResultList();

            } catch (NoResultException ex) {
            }
        }
        return result;
    }
}
