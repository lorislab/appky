/*
 * Copyright 2012 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.config.log;

import org.lorislab.appky.config.model.AbstractConfigurationModel;
import java.util.Map;
import org.kohsuke.MetaInfServices;
import org.lorislab.jel.log.parameters.InstanceOfLogParameter;

/**
 * The configuration log parameter.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@MetaInfServices
public class ConfigurationModelLogParameter implements InstanceOfLogParameter {
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
        AbstractConfigurationModel tmp = (AbstractConfigurationModel) parameter;
        Map<String,String> data = tmp.getData();
        StringBuilder sb = new StringBuilder();
        sb.append(parameter.getClass().getSimpleName());
        sb.append('[');
        if (data != null) {
            boolean first = false;
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if (first) {
                    sb.append(' ');
                }
                sb.append(entry.getKey());
                sb.append(':');
                sb.append(entry.getValue());
                first = true;
            }
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean instanceOfClasses(Object parameter) {
        return parameter instanceof AbstractConfigurationModel;
    }    
}
