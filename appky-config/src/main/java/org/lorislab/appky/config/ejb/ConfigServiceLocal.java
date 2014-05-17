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
package org.lorislab.appky.config.ejb;

import org.lorislab.appky.config.model.Config;
import java.util.List;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The configuration model service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface ConfigServiceLocal {

    /**
     * Loads the configuration model by GUID.
     *
     * @param guid the GUID of the model.
     * @return the configuration model.
     * @throws ServiceException if the method fails.
     */
    Config loadConfig(String guid) throws ServiceException;

    /**
     * Saves the configuration model.
     *
     * @param config the configuration model.
     * @return the new saved configuration model.
     * @throws ServiceException if the method fails.
     */
    Config saveConfig(Config config) throws ServiceException;

    /**
     * Finds all configuration models.
     *
     * @return the list of all configuration models.
     * @throws ServiceException if the method fails.
     */
    List<Config> findAllConfig() throws ServiceException;
}
