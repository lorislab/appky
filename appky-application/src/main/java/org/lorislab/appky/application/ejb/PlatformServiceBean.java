/*
 * Copyright 2012 Andrej Petras <andrej@ajka-andrej.com>.
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
import org.lorislab.appky.application.criteria.PlatformSearchCriteria;
import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Application_;
import org.lorislab.appky.application.model.Description;
import org.lorislab.appky.application.model.Description_;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.model.Document_;
import org.lorislab.appky.application.model.ManifestIOS;
import org.lorislab.appky.application.model.ManifestIOS_;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.Platform_;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The platform service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(PlatformServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PlatformServiceBean extends AbstractEntityServiceBean<Platform> implements PlatformServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -7600035268267449462L;

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform loadPlatformByGuid(String guid) throws ServiceException {
        return this.getById(guid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Platform savePlatform(Platform platform) throws ServiceException {
        Platform result = this.save(platform);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform loadFullPlatformByGuid(String guid) throws ServiceException {
        Platform result = null;
        try {

            CriteriaBuilder criteriaBuilder = getBaseEAO().getCriteriaBuilder();
            CriteriaQuery<Platform> criteriaQuery = getBaseEAO().createCriteriaQuery();
            Root<Platform> root = criteriaQuery.from(Platform.class);
            // icon
            Fetch<Platform, Document> icon = root.fetch(Platform_.icon, JoinType.LEFT);
            icon.fetch(Document_.content, JoinType.LEFT);
            // images
            Fetch<Platform, Document> images = root.fetch(Platform_.images, JoinType.LEFT);
            images.fetch(Document_.content, JoinType.LEFT);

            // description
            root.fetch(Platform_.descriptions, JoinType.LEFT);

            Fetch<Platform, ManifestIOS> manifest = root.fetch(Platform_.manifest, JoinType.LEFT);

            // smallIcon
            Fetch<ManifestIOS, Document> smallIcon = manifest.fetch(ManifestIOS_.smallIcon, JoinType.LEFT);
            smallIcon.fetch(Document_.content, JoinType.LEFT);

            Fetch<ManifestIOS, Document> largeIcon = manifest.fetch(ManifestIOS_.largeIcon, JoinType.LEFT);
            largeIcon.fetch(Document_.content, JoinType.LEFT);

            criteriaQuery.where(criteriaBuilder.equal(root.get(Platform_.guid), guid));

            TypedQuery<Platform> query = getBaseEAO().createTypedQuery(criteriaQuery);
            List<Platform> tmp = query.getResultList();
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
    public boolean deletePlatform(Platform platform) throws ServiceException {
        return delete(platform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Platform> findsPlatformBySearchCriteria(PlatformSearchCriteria criteria) throws ServiceException {
   List<Platform> result = new ArrayList<>();
        if (criteria != null) {
            try {
                CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
                CriteriaQuery<Platform> cq = getBaseEAO().createCriteriaQuery();
                Root<Platform> root = cq.from(Platform.class);                
                cq.distinct(true);
                
                List<Predicate> predicates = new ArrayList<>();

                if (criteria.getUserLocale() != null || criteria.getDefaultUserLocale() != null) {
                    //FIXME: works only for hibernate       
                    Join<Platform, Description> descriptions = (Join<Platform, Description>) root.fetch(Platform_.descriptions, JoinType.LEFT);
                    predicates.add(
                            cb.or(
                            cb.equal(descriptions.get(Description_.locale), criteria.getUserLocale()),
                            cb.equal(descriptions.get(Description_.locale), criteria.getDefaultUserLocale())));
                }
 
        
                if (criteria.getGuid() != null) {
                    Predicate condition = cb.equal(root.get(Platform_.guid), criteria.getGuid());
                    predicates.add(condition);
                }

                if (criteria.getEnabled() != null) {
                    Predicate condition = cb.equal(root.get(Platform_.enabled), criteria.getEnabled());
                    predicates.add(condition);
                }

                if (criteria.getApplicationGuid() != null) {
                    Join<Platform, Application> application = root.join(Platform_.application);
                    predicates.add(cb.equal(application.get(Application_.guid), criteria.getApplicationGuid()));
                }

             
                if (!predicates.isEmpty()) {
                    cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                }

                TypedQuery<Platform> typeQuery = getBaseEAO().createTypedQuery(cq);
                result = typeQuery.getResultList();

            } catch (NoResultException ex) {
                // do nothing
            }
        }
        return result;
    }
}
