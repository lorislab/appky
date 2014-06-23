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
package org.lorislab.appky.process.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.appky.application.ejb.UserPasswordService;
import org.lorislab.appky.application.model.UserPassword;
import org.lorislab.appky.application.util.UserPasswordUtil;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The user password process service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserPasswordProcessService {

    /**
     * The user password service.
     */
    @EJB
    private UserPasswordService service;
    
    /**
     * Reset user password.
     *
     * @param userGuid the user GUID.
     * @param newPassword the new user password.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void resetPassword(String userGuid, char[] newPassword) throws ServiceException {
        UserPassword up = service.findUserPasswordByUser(userGuid);
        if (UserPasswordUtil.resetPassword(up, newPassword)) {
            service.saveUserPassword(up);
        }
    }

    /**
     * Changes the user password.
     *
     * @param userGuid the user GUID.
     * @param password the current user password.
     * @param newPassword the new user password.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void changePassword(String userGuid, char[] password, char[] newPassword) throws ServiceException {
        UserPassword up = service.findUserPasswordByUser(userGuid);
        if (UserPasswordUtil.changePassword(up, password, newPassword)) {
            service.saveUserPassword(up);
        }
    }
}
