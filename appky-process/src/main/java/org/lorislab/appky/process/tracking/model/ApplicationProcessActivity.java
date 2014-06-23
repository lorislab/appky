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
package org.lorislab.appky.process.tracking.model;

import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.process.tracking.model.enums.UserActivity;

/**
 * The application process activity.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationProcessActivity extends AbstractStoreTrackingData {
    
    private static final long serialVersionUID = -2149853392008203501L;
    
    public static final String PARAM_APP_GUID = "app_guid";
    
    public static final String PARAM_PLATFORM_GUID = "platform_guid";
    
    public static final String PARAM_VERSION_GUID = "version_guid";
    
    public ApplicationProcessActivity(UserActivity activity, UserApplication userApplication) {
        super(activity);
        setUser(userApplication.getUserGuid());
        getData().put(PARAM_APP_GUID, userApplication.getApplicationGuid());
        getData().put(PARAM_PLATFORM_GUID, userApplication.getPlatformGuid());
        getData().put(PARAM_VERSION_GUID, userApplication.getVersionGuid());
    }
}
