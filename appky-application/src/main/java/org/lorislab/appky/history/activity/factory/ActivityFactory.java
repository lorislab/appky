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
package org.lorislab.appky.history.activity.factory;

import org.lorislab.appky.history.activity.model.Activity;
import org.lorislab.appky.history.activity.model.ActivityParameter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The activity factory class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ActivityFactory {

    /**
     * The default constructor.
     */
    private ActivityFactory() {
        // empty constructor
    }

    /**
     * Creates activity.
     *
     * @param user the user.
     * @param module the module.
     * @param activity the activity.
     * @param parameters the map of parameters.
     * @return the new created activity.
     */
    public static Activity createActivity(String user, String module, String activity, Map<String, String> parameters) {
        Activity result = new Activity();
        result.setActivity(activity);
        result.setModule(module);
        result.setUser(user);
        loadParameters(result, parameters);
        return result;
    }

    /**
     * Creates activity parameter.
     *
     * @param name the name.
     * @param value the value.
     * @return the activity parameter.
     */
    private static ActivityParameter createActivityParameter(String name, String value) {
        ActivityParameter result = new ActivityParameter();
        result.setName(name);
        result.setValue(value);
        return result;
    }

    /**
     * Loads the activity parameters from map to the activity.
     *
     * @param activity the activity.
     * @param parameters the map of parameters.
     */
    private static void loadParameters(Activity activity, Map<String, String> parameters) {
        if (parameters != null && !parameters.isEmpty() && activity != null) {
            List<ActivityParameter> tmp = activity.getParameters();
            for (Entry<String, String> entry : parameters.entrySet()) {
                tmp.add(createActivityParameter(entry.getKey(), entry.getValue()));
            }
        }
    }
}
