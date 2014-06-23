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
package org.lorislab.appky.process.factory;


import java.util.ArrayList;
import java.util.List;
import org.lorislab.appky.application.factory.UserObjectFactory;
import org.lorislab.appky.application.model.UserConfigParam;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.enums.UserConfigParamType;
import org.lorislab.appky.process.config.UserProfileConfiguration;

/**
 * The common object factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class CommonObjectFactory {

    /**
     * The default constructor.
     */
    private CommonObjectFactory() {
        // empty constructor
    }

    /**
     * Sets the user profile default configuration parameters.
     *
     * @param profile the user profile.
     */
    public static void setDefaultUserProfileConfigParam(UserProfile profile) {
        if (profile != null) {
            profile.setProperties(createUserConfigParamDefaultList());
        }
    }

    /**
     * Creates default list of user profile parameters.
     *
     * @return the list of user profile parameters.
     */
    public static List<UserConfigParam> createUserConfigParamDefaultList() {
        List<UserConfigParam> result = new ArrayList<>();
        result.add(UserObjectFactory.create(UserProfileConfiguration.PARAM_NOTIFI_NEWS, UserProfileConfiguration.PARAM_NOTIFI_NEWS_DEFAULT, UserConfigParamType.BOOLEAN));
        result.add(UserObjectFactory.create(UserProfileConfiguration.PARAM_NOTIFI_NEW_APP, UserProfileConfiguration.PARAM_NOTIFI_NEW_APP_DEFAULT, UserConfigParamType.BOOLEAN));
        result.add(UserObjectFactory.create(UserProfileConfiguration.PARAM_NOTIFI_UPDATE_APP, UserProfileConfiguration.PARAM_NOTIFI_UPDATE_APP_DEFAULT, UserConfigParamType.BOOLEAN));
        return result;
    }
}
