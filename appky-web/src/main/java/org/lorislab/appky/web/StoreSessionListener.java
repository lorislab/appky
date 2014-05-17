/*
 * Copyright 2013 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.web;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.lorislab.appky.process.ejb.UserProcessServiceLocal;

/**
 * The session listener.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@WebListener
public class StoreSessionListener implements HttpSessionListener {
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(StoreSessionListener.class.getName());
    /**
     * The user process service.
     */
    @EJB
    private UserProcessServiceLocal userProcessService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // empty method
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String userGuid = (String) se.getSession().getAttribute(Constants.SESSION_USER_GUID);
        if (userGuid != null) {
            try {
                Object timeoutAttr = se.getSession().getAttribute(Constants.SESSION_USER_TIMEOUT);
                boolean timeout = timeoutAttr == null;                
                userProcessService.logoutUser(userGuid, timeout);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error logout the user.", ex);
            }
        }
    }
}
