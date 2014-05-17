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
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.appky.application.criteria.VersionSearchCriteria;
import org.lorislab.appky.application.model.Description;
import org.lorislab.appky.application.model.Description_;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.model.Document_;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.Platform_;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.application.model.Version_;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The version service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(VersionServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class VersionServiceBean extends AbstractEntityServiceBean<Version> implements VersionServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6909704154512518401L;

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Version saveVersion(Version version) throws ServiceException {
        Version result = this.save(version);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Version loadVersion(String guid) throws ServiceException {
        Version result = null;
        try {

            CriteriaBuilder criteriaBuilder = getBaseEAO().getCriteriaBuilder();
            CriteriaQuery<Version> criteriaQuery = getBaseEAO().createCriteriaQuery();
            Root<Version> root = criteriaQuery.from(Version.class);

            Fetch<Version, Document> data = root.fetch(Version_.data, JoinType.LEFT);
            data.fetch(Document_.content, JoinType.LEFT);

            criteriaQuery.where(criteriaBuilder.equal(root.get(Version_.guid), guid));
            TypedQuery<Version> query = getBaseEAO().createTypedQuery(criteriaQuery);
            List<Version> tmp = query.getResultList();
            if (tmp != null && !tmp.isEmpty()) {
                result = tmp.get(0);
            }

        } catch (NoResultException e) {
            // do nothing
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Version loadFullVersion(String guid) throws ServiceException {
        Version result = null;
        try {

            CriteriaBuilder criteriaBuilder = getBaseEAO().getCriteriaBuilder();
            CriteriaQuery<Version> criteriaQuery = getBaseEAO().createCriteriaQuery();
            Root<Version> root = criteriaQuery.from(Version.class);

            root.fetch(Version_.descriptions, JoinType.LEFT);

            Fetch<Version, Document> data = root.fetch(Version_.data, JoinType.LEFT);
            data.fetch(Document_.content, JoinType.LEFT);

            criteriaQuery.where(criteriaBuilder.equal(root.get(Version_.guid), guid));
            TypedQuery<Version> query = getBaseEAO().createTypedQuery(criteriaQuery);
            List<Version> tmp = query.getResultList();
            if (tmp != null && !tmp.isEmpty()) {
                result = tmp.get(0);
            }
        } catch (NoResultException e) {
            // do nothing
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteVersion(Version version) throws ServiceException {
        return delete(version);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Version> findVersionsBySearchCriteria(VersionSearchCriteria criteria) throws ServiceException {
        List<Version> result = new ArrayList<>();
        if (criteria != null) {
            try {

                CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
                CriteriaQuery<Version> cq = getBaseEAO().createCriteriaQuery();
                Root<Version> root = cq.from(Version.class);

                List<Predicate> predicates = new ArrayList<>();
                
                if (criteria.getUserLocale() != null || criteria.getDefaultUserLocale() != null) {
                    //FIXME: works only for hibernate       
                    Join<Version, Description> descriptions = (Join<Version, Description>) root.fetch(Version_.descriptions, JoinType.LEFT);
                    predicates.add(
                            cb.or(
                            cb.equal(descriptions.get(Description_.locale), criteria.getUserLocale()),
                            cb.equal(descriptions.get(Description_.locale), criteria.getDefaultUserLocale())));
                }
  
                if (criteria.getGuid() != null) {
                    predicates.add(cb.equal(root.get(Version_.guid), criteria.getGuid()));
                }
                
                if (criteria.getDeleted() != null) {
                    predicates.add(cb.equal(root.get(Version_.deleted), criteria.getDeleted()));
                }

                if (criteria.getPlatformGuid() != null) {
                    Join<Version, Platform> group = root.join(Version_.platform);
                    Predicate condition = cb.equal(group.get(Platform_.guid), criteria.getPlatformGuid());
                    predicates.add(condition);
                }
                
                if (criteria.getReleased() != null) {
                    predicates.add(cb.equal(root.get(Version_.released), criteria.getReleased()));
                }
                
                if (!predicates.isEmpty()) {
                    cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                }
                
                TypedQuery<Version> typeQuery = getBaseEAO().createTypedQuery(cq);
                result = typeQuery.getResultList();

            } catch (NoResultException e) {
                // do nothing
            }
        }
        return result;
    }
}
