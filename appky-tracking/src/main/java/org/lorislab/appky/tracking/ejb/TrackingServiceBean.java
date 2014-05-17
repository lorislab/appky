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
package org.lorislab.appky.tracking.ejb;

;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.appky.history.activity.ejb.ActivityServiceLocal;
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
@Local(TrackingServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TrackingServiceBean extends AbstractServiceBean implements TrackingServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2279242488740889552L;

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(TrackingServiceBean.class.getName());

    /**
     * The activity service.
     */
    @EJB
    private ActivityServiceLocal activityService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Asynchronous
    public void saveTrackingData(AbstractTrackingData data) throws ServiceException {
        Activity activity = ActivityFactory.createActivity(data.getUser(), data.getModule(), data.getActivity().name(), data.getData());                            
        activityService.saveActivity(activity);    
    }
}
