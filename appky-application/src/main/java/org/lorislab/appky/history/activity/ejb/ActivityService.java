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
package org.lorislab.appky.history.activity.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.appky.history.activity.model.Activity;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;


/**
 * The activity service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ActivityService extends AbstractEntityServiceBean<Activity> {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 581691904770624358L;

    /**
     * Saves the activity model.
     *
     * @param activity the activity model.
     * @return the new saved activity model.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Activity saveActivity(Activity activity) throws ServiceException {
        return save(activity);
    }
}
