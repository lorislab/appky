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


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.model.Document_;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The document service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DocumentService extends AbstractEntityServiceBean<Document> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 4546828036715043347L;

    /**
     * Loads the full document by GUID.
     *
     * @param guid the document GUID.
     * @return the document.
     * @throws ServiceException if the method fails.
     */
    public Document loadDocumentFullByGuid(String guid) throws ServiceException {
        Document result = null;
        try {

            CriteriaBuilder criteriaBuilder = getBaseEAO().getCriteriaBuilder();
            CriteriaQuery<Document> criteriaQuery = getBaseEAO().createCriteriaQuery();
            Root<Document> root = criteriaQuery.from(Document.class);
            root.fetch(Document_.content, JoinType.LEFT);

            criteriaQuery.where(criteriaBuilder.equal(root.get(Document_.guid), guid));
            TypedQuery<Document> query = getBaseEAO().createTypedQuery(criteriaQuery);
            result = query.getSingleResult();
        } catch (NoResultException e) {
            // do nothing
        }
        return result;
    }
}
