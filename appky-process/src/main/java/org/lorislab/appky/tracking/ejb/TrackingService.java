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
package org.lorislab.appky.tracking.ejb;

;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.appky.history.activity.ejb.ActivityService;
import org.lorislab.appky.history.activity.factory.ActivityFactory;
import org.lorislab.appky.history.activity.model.Activity;
import org.lorislab.appky.tracking.model.AbstractTrackingData;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractServiceBean;


/**
 * The tracking service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TrackingService {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2279242488740889552L;

    /**
     * The activity service.
     */
    @EJB
    private ActivityService activityService;
    
    /**
     * Save the save tracking data.
     *
     * @param data the tracking data.
     * @throws ServiceException if the method fails.
     */
    @Asynchronous
    public void saveTrackingData(AbstractTrackingData data) throws ServiceException {
        Activity activity = ActivityFactory.createActivity(data.getUser(), data.getModule(), data.getActivity().name(), data.getData());                            
        activityService.saveActivity(activity);    
    }
}
