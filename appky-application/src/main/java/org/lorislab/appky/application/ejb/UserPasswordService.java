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
import javax.persistence.criteria.Root;
import org.lorislab.appky.application.model.UserPassword;
import org.lorislab.appky.application.model.UserPassword_;
import org.lorislab.appky.application.resources.ErrorKeys;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;
import org.lorislab.jel.jpa.model.util.PersistentUtil;

/**
 * The user password service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserPasswordService extends AbstractEntityServiceBean<UserPassword> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5247191624686787186L;

    /**
     * Saves the user password.
     *
     * @param userPassword the user password.
     * @return the saved user password model.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserPassword saveUserPassword(UserPassword userPassword) throws ServiceException {
        UserPassword result = null;
        try {
            result = this.save(userPassword);
        } catch (Exception ex) {
            String guid = PersistentUtil.getGuid(userPassword);
            throw new ServiceException(ErrorKeys.SAVE_USER_PASSWORD_FAILED, ex, guid);
        }

        return result;
    }

    /**
     * Finds the user password by user GUID.
     *
     * @param userGuid the user GUID.
     * @return the user password corresponding to the user GUID.
     * @throws ServiceException if the method fails.
     */
    public UserPassword findUserPasswordByUser(String userGuid) throws ServiceException {
        UserPassword result = null;
        try {
            CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
            CriteriaQuery<UserPassword> cq = getBaseEAO().createCriteriaQuery();
            Root<UserPassword> root = cq.from(UserPassword.class);

            cq.where(cb.equal(root.get(UserPassword_.userGuid), userGuid));
            TypedQuery<UserPassword> query = getBaseEAO().createTypedQuery(cq);
            result = query.getSingleResult();
        } catch (NoResultException ex) {
            // do nothing
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.FIND_USER_PASSWORD_BY_USER_FAILED, ex, userGuid);
        }
        return result;
    }

    /**
     * Deletes the user password.
     *
     * @param userPassword the user password.
     * @return <code>true</code> if the user password wad deleted.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteUserPassword(UserPassword userPassword) throws ServiceException {
        return this.delete(userPassword);
    }
}
