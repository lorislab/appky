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
package org.lorislab.appky.process.tracking.factory;

import java.util.HashMap;
import java.util.Map;
import org.lorislab.appky.application.model.EmailRequest;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.enums.EmailRequestType;
import org.lorislab.appky.application.wrapper.model.enums.UserProcessAction;
import org.lorislab.appky.process.tracking.model.AbstractStoreTrackingData;
import org.lorislab.appky.process.tracking.model.ApplicationProcessActivity;
import org.lorislab.appky.process.tracking.model.LoginActivity;
import org.lorislab.appky.process.tracking.model.LogoutActivity;
import org.lorislab.appky.process.tracking.model.RequestEmailValidationAcceptActivity;
import org.lorislab.appky.process.tracking.model.RequestEmailValidationActivity;
import org.lorislab.appky.process.tracking.model.RequestEmailValidationDeclineActivity;
import org.lorislab.appky.process.tracking.model.RequestInvitationAcceptActivity;
import org.lorislab.appky.process.tracking.model.RequestInvitationActivity;
import org.lorislab.appky.process.tracking.model.RequestInvitationDeclineActivity;
import org.lorislab.appky.process.tracking.model.RequestRegistrationActivity;
import org.lorislab.appky.process.tracking.model.RequestResetPasswordAcceptActivity;
import org.lorislab.appky.process.tracking.model.RequestResetPasswordActivity;
import org.lorislab.appky.process.tracking.model.RequestResetPasswordDeclineActivity;
import org.lorislab.appky.process.tracking.model.enums.UserActivity;

/**
 * The store tracking factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class StoreTrackingDataFactory {

    /**
     * The activity mapping map.
     */
    private static final Map<UserProcessAction, UserActivity> ACTIVITY_MAPPING = new HashMap<>();

    /**
     * The static block.
     */
    static {
        ACTIVITY_MAPPING.put(UserProcessAction.INSTALL, UserActivity.APP_INSTALL);
        ACTIVITY_MAPPING.put(UserProcessAction.REINSTALL, UserActivity.APP_REINSTALL);
        ACTIVITY_MAPPING.put(UserProcessAction.REMOVE, UserActivity.APP_UNINSTALL);
        ACTIVITY_MAPPING.put(UserProcessAction.UPDATE, UserActivity.APP_UPDATE);
    }

    /**
     * The default constructor
     */
    private StoreTrackingDataFactory() {
        // empty constructor
    }

    /**
     * Creates the application process activity model.
     *
     * @param action the user process action.
     * @param userApplication the user application.
     * @return the application process activity.
     */
    public static AbstractStoreTrackingData createApplicationProcessActivity(UserProcessAction action, UserApplication userApplication) {
        UserActivity activity = ACTIVITY_MAPPING.get(action);
        ApplicationProcessActivity result = new ApplicationProcessActivity(activity, userApplication);
        return result;
    }

    /**
     * Creates the login activity.
     *
     * @param userGuid the user GUID.
     * @return the login activity.
     */
    public static AbstractStoreTrackingData createLoginActivity(String userGuid) {
        LoginActivity result = new LoginActivity();
        result.setUser(userGuid);
        return result;
    }

    /**
     * Creates the logout activity.
     *
     * @param userGuid the user GUID.
     * @param timeout the timeout flag.
     * @return the logout activity.
     */
    public static AbstractStoreTrackingData createLogoutActivity(String userGuid, boolean timeout) {
        LogoutActivity result = new LogoutActivity(timeout);
        result.setUser(userGuid);
        return result;
    }

    /**
     * Creates the request invitation activity.
     *
     * @param request the user request.
     * @return the request invitation activity.
     */
    public static AbstractStoreTrackingData createRequestInvitationActivity(EmailRequest request) {
        RequestInvitationActivity result = new RequestInvitationActivity(request.getGuid(), request.getEmail());
        result.setUser(request.getParentGuid());
        return result;
    }
    
    /**
     * Creates the accept request invitation activity.
     *
     * @param request the user request.
     * @return the request invitation activity.
     */
    public static AbstractStoreTrackingData createRequestInvitationAcceptActivity(EmailRequest request) {
        RequestInvitationAcceptActivity result = new RequestInvitationAcceptActivity(request.getGuid());
        result.setUser(request.getUserGuid());
        return result;
    }
    
    /**
     * Creates the decline request activity.
     *
     * @param request the user request.
     * @return the request invitation activity.
     */
    public static AbstractStoreTrackingData createRequestDeclineActivity(EmailRequest request) {        
        AbstractStoreTrackingData result = null;
        if (EmailRequestType.INVITATION.equals(request.getType())) {
            result = new RequestInvitationDeclineActivity(request.getGuid());
        } else if (EmailRequestType.RESET_PASSWORD.equals(request.getType())) {
            result = new RequestResetPasswordDeclineActivity(request.getGuid());
        } else if (EmailRequestType.REGISTRATION.equals(request.getType())) {
            result = new RequestEmailValidationDeclineActivity(request.getGuid());            
        }   
        result.setUser(request.getUserGuid());
        return result;
    }
    
    public static AbstractStoreTrackingData createRequestResetPasswordActivity(EmailRequest request) {
        RequestResetPasswordActivity result = new RequestResetPasswordActivity(request.getGuid());
        result.setUser(request.getUserGuid());
        return result;
    }
    
    public static AbstractStoreTrackingData createRequestResetPasswordAcceptActivity(EmailRequest request) {
        RequestResetPasswordAcceptActivity result = new RequestResetPasswordAcceptActivity(request.getGuid());
        result.setUser(request.getUserGuid());
        return result;
    }
    
       
    public static AbstractStoreTrackingData createRequestRegistrationActivity(EmailRequest request) {
        RequestRegistrationActivity result = new RequestRegistrationActivity(request.getGuid());
        result.setUser(request.getUserGuid());
        return result;
    }    
    
    public static AbstractStoreTrackingData createRequestEmailValidationActivity(EmailRequest request) {
        RequestEmailValidationActivity result = new RequestEmailValidationActivity(request.getGuid());
        result.setUser(request.getUserGuid());
        return result;
    } 
    
    public static AbstractStoreTrackingData createRequestEmailValidationAcceptActivity(EmailRequest request) {
        RequestEmailValidationAcceptActivity result = new RequestEmailValidationAcceptActivity(request.getGuid());
        result.setUser(request.getUserGuid());
        return result;
    }     
           
}
