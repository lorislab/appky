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
package org.lorislab.appky.process.config;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lorislab.appky.application.model.UserConfigParam;

/**
 * User configuration mapping class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class UserProfileConfiguration {

    /**
     * The news notification parameter.
     */
    public static final String PARAM_NOTIFI_NEWS = "notification.news";
    /**
     * The news notification parameter default value.
     */
    public static final String PARAM_NOTIFI_NEWS_DEFAULT = "true";
    /**
     * The new application notification parameter.
     */
    public static final String PARAM_NOTIFI_NEW_APP = "notification.newApp";
    /**
     * The new application notification parameter default value.
     */
    public static final String PARAM_NOTIFI_NEW_APP_DEFAULT = "true";
    /**
     * The update application notification parameter.
     */
    public static final String PARAM_NOTIFI_UPDATE_APP = "notification.updateApp";
    /**
     * The update application notification parameter default value.
     */
    public static final String PARAM_NOTIFI_UPDATE_APP_DEFAULT = "true";
    /**
     * The map of the user parameters.
     */
    private Map<String, String> tmp;

    /**
     * The default constructor.
     *
     * @param properties the profile properties.
     */
    public UserProfileConfiguration(List<UserConfigParam> properties) {
        tmp = new HashMap<>();
        if (properties != null) {
            for (UserConfigParam p : properties) {
                tmp.put(p.getName(), p.getValue());
            }
        }
    }

    /**
     * Returns
     * <code>true</code> if notification news is enabled.
     *
     * @return <code>true</code> if notification news is enabled.
     */
    public boolean isNotificationNews() {
        return getBooleanValue(PARAM_NOTIFI_NEWS, PARAM_NOTIFI_NEWS_DEFAULT);
    }

    /**
     * Returns
     * <code>true</code> if notification about new application is enabled.
     *
     * @return <code>true</code> if notification about new application is
     * enabled.
     */
    public boolean isNotificationNewApplication() {
        return getBooleanValue(PARAM_NOTIFI_NEW_APP, PARAM_NOTIFI_NEW_APP_DEFAULT);
    }

    /**
     * Returns
     * <code>true</code> if notification about update application is enabled.
     *
     * @return <code>true</code> if notification about update application is
     * enabled.
     */
    public boolean isNotificationUpdateApplication() {
        return getBooleanValue(PARAM_NOTIFI_UPDATE_APP, PARAM_NOTIFI_UPDATE_APP_DEFAULT);
    }

    /**
     * Gets the boolean value from the key.
     *
     * @param key the key.
     * @param defaultValue the default value.
     * @return the boolean value corresponding to the key.
     */
    private boolean getBooleanValue(String key, String defaultValue) {
        String value = tmp.get(key);
        if (value == null) {
            value = defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
}
