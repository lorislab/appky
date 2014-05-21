/*
 * Copyright 2011 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.config.ejb;

import org.lorislab.appky.config.model.AbstractConfigurationModel;
import org.lorislab.jel.ejb.exception.ServiceException;


/**
 * The configuration service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface ConfigurationServiceLocal {

    /**
     * Reloads the server configuration.
     *
     * @throws ServiceException if the method fails.
     */
    void reloadConfiguration() throws ServiceException;

    /**
     * Loads the configuration model.
     *
     * @param <T> the configuration model.
     * @param clazz the class of the configuration model.
     * @return the configuration model.
     * @throws ServiceException if the method fails.
     */
    <T extends AbstractConfigurationModel> T loadConfiguration(Class<T> clazz) throws ServiceException;

    /**
     * Loads the configuration model.
     *
     * @param <T> the configuration model.
     * @return the configuration model.
     * @throws ServiceException if the method fails.
     */
    <T extends AbstractConfigurationModel> T loadConfiguration(T model) throws ServiceException;
}
