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
package org.lorislab.appky.process.log;


import java.util.ArrayList;
import java.util.List;
import org.kohsuke.MetaInfServices;
import org.lorislab.jel.log.config.LogServiceConfiguration;
import org.lorislab.jel.log.context.ContextLogger;
import org.lorislab.jel.log.context.impl.DefaultContextLogger;
import org.lorislab.jel.log.parameters.ClassLogParameter;
import org.lorislab.jel.log.parameters.InstanceOfLogParameter;
import org.lorislab.jel.log.parameters.LogParameter;
import org.lorislab.jel.log.parameters.impl.BasicLogParamater;
import org.lorislab.jel.log.parameters.impl.DefaultReflectionLogParameter;

/**
 * The store log service configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@MetaInfServices
public class LogServiceConfigurationImpl implements LogServiceConfiguration {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ClassLogParameter> getClassLogParameters() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<InstanceOfLogParameter> getInstanceOfLogParameters() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogParameter getDefaultLogParameter() {
        return new DefaultReflectionLogParameter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContextLogger getContextLogger() {
        return new DefaultContextLogger();
    }
}
