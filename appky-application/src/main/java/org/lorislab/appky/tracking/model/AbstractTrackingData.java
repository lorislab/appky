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
package org.lorislab.appky.tracking.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The abstract tracking data.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class AbstractTrackingData implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6275795569384665926L;
    /**
     * The user.
     */
    private String user;
    /**
     * The module.
     */
    private String module;
    /**
     * The activity.
     */
    private Enum<?> activity;
    /**
     * The data.
     */
    private Map<String, String> data;

    /**
     * The default constructor.
     *
     * @param module the module.
     * @param activity the activity.
     */
    public AbstractTrackingData(String module, Enum<?> activity) {
        data = new HashMap<>();
        this.module = module;
        this.activity = activity;
    }

    /**
     * Sets the user.
     *
     * @param user the user.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the data.
     *
     * @return the data.
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * Adds the parameter to the tracking data.
     *
     * @param key the key.
     * @param value the value.
     */
    protected void addParameter(String key, String value) {
        data.put(key, value);
    }

    /**
     * Gets the activity.
     *
     * @return the activity.
     */
    public Enum<?> getActivity() {
        return activity;
    }

    /**
     * Gets the module.
     *
     * @return the module.
     */
    public String getModule() {
        return module;
    }

    /**
     * Gets the user.
     *
     * @return the user.
     */
    public String getUser() {
        return user;
    }
}
