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
package org.lorislab.appky.email.ejb;

import org.lorislab.appky.email.model.Email;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The mail service local interface.
 * 
 * @author Andrej_Petras
 */
public interface MailServiceLocal {
      
    /**
     * Sends email.
     * 
     * @param email the email object.
     * 
     * @throws ServiceException if the method fails.
     */
    void sendEmail(Email email) throws ServiceException;

}
