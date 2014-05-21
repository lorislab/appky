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
package org.lorislab.appky.config.ejb;

import org.lorislab.appky.config.model.Config;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The configuration model service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(ConfigServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConfigServiceBean extends AbstractEntityServiceBean<Config> implements ConfigServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 1424496917138664956L;

    /**
     * {@inheritDoc}
     */
    @Override
    public Config loadConfig(String guid) throws ServiceException {
        Config result = null;
        try {
            result = this.getById(guid);
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Config saveConfig(Config config) throws ServiceException {
        Config result = null;
        try {
            result = this.save(config);
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Config> findAllConfig() throws ServiceException {
        List<Config> result = null;
        try {            
            CriteriaQuery<Config> cq = getBaseEAO().createCriteriaQuery();
            cq.from(Config.class);            
            TypedQuery<Config> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }
}
