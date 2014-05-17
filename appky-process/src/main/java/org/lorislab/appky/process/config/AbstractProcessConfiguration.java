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
package org.lorislab.appky.process.config;

import org.lorislab.appky.config.model.AbstractConfigurationModel;



/**
 * The abstract registration configuration model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class AbstractProcessConfiguration extends AbstractConfigurationModel {

    /**
     * The module name.
     */
    private static final String MODULE = "process";
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 5313881033337138319L;

    /**
     * The default constructor.
     */
    public AbstractProcessConfiguration() {
        super(MODULE);
    }
}
