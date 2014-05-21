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
package org.lorislab.appky.tracking.log;

import java.util.Map;
import java.util.Map.Entry;
import org.kohsuke.MetaInfServices;
import org.lorislab.appky.tracking.model.AbstractTrackingData;
import org.lorislab.jel.log.parameters.InstanceOfLogParameter;

/**
 * The tracking data log parameter.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@MetaInfServices
public class TrackingDataLogParameter implements InstanceOfLogParameter {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean instanceOfClasses(Object parameter) {
        return parameter instanceof AbstractTrackingData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isResult() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getObject(Object parameter) {
        AbstractTrackingData tmp = (AbstractTrackingData) parameter;
        StringBuilder sb = new StringBuilder();
        sb.append(tmp.getClass().getSimpleName());
        sb.append("[user=");
        sb.append(tmp.getUser());
        sb.append(",module=");
        sb.append(tmp.getModule());
        sb.append(",activity=");
        sb.append(tmp.getActivity());
        Map<String,String> data = tmp.getData();
        if (data != null && !data.isEmpty()) {
            sb.append(",data={");
            boolean first = false;
            for (Entry<String, String> entry : data.entrySet()) {
                if (first) {
                    sb.append(',');
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                first = true;
            }
        }
        sb.append("}]");
        return sb.toString();
    }
}
