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
package org.lorislab.appky.process.ejb;


import org.lorislab.appky.process.tracking.factory.StoreTrackingDataFactory;
import org.lorislab.appky.process.tracking.model.AbstractStoreTrackingData;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.appky.application.criteria.UserProfileSearchCriteria;
import org.lorislab.appky.application.ejb.UserProfileServiceLocal;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.tracking.ejb.TrackingServiceLocal;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractServiceBean;

/**
 * The user process service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(UserProcessServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserProcessServiceBean extends AbstractServiceBean implements UserProcessServiceLocal {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -378917446240057038L;
    /**
     * The user profile service.
     */
    @EJB
    private UserProfileServiceLocal userProfileService;
    /**
     * The tracking service.
     */
    @EJB
    private TrackingServiceLocal trackingService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void loginUser(UserProfile profile) throws ServiceException {
        if (profile != null) {
            AbstractStoreTrackingData activity = StoreTrackingDataFactory.createLoginActivity(profile.getGuid());
            trackingService.saveTrackingData(activity);
        }
    }

/**
     * {@inheritDoc}
     */
    @Override
    public UserProfile findUserProfileForLogin(String email) throws ServiceException {
        UserProfile profile = null;
        UserProfileSearchCriteria criteria = new UserProfileSearchCriteria();
        criteria.setEmail(email);
        List<UserProfile> profiles = userProfileService.findUserProfilesByCriteria(criteria);
        if (profiles != null && !profiles.isEmpty()) {
            profile = profiles.get(0);
        }
        return profile;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void logoutUser(String userGuid, boolean timeout) throws ServiceException {
        AbstractStoreTrackingData activity = StoreTrackingDataFactory.createLogoutActivity(userGuid, timeout);
        trackingService.saveTrackingData(activity);
    }
}
