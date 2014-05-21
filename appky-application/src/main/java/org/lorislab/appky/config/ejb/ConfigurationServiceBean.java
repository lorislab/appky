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
import org.lorislab.appky.config.model.Config;
import org.lorislab.appky.config.model.ConfigParam;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractServiceBean;

/**
 * The configuration singleton service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Singleton
@Local(ConfigurationServiceLocal.class)
public class ConfigurationServiceBean extends AbstractServiceBean implements ConfigurationServiceLocal {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ConfigurationServiceBean.class.getName());
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -8098793922903930861L;
    /**
     * The cache parameter.
     */
    private Map<String, Map<String, ConfigParam>> cache = new HashMap<>();
    /**
     * The configuration model service.
     */
    @EJB
    private ConfigServiceLocal service;

    /**
     * The startup method.
     */
    @PostConstruct
    public void startup() {
        try {
            List<Config> data = service.findAllConfig();
            if (data != null) {
                for (Config config : data) {
                    Map<String, ConfigParam> tmp = new HashMap<>();
                    if (config.getProperties() != null) {
                        for (ConfigParam param : config.getProperties()) {
                            tmp.put(param.getName(), param);
                        }
                    }
                    cache.put(config.getModule(), tmp);
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error reading the configuration!", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reloadConfiguration() throws ServiceException {
        cache = new HashMap<>();
        startup();
    }

    /**
     * Gets the module configuration parameter.
     *
     * @param application the application (module).
     * @param keys the set of keys.
     * @return the map of the configuration parameter.
     */
    private Map<String, String> getModuleProperties(String application, Set<String> keys) {
        Map<String, ConfigParam> config = cache.get(application);
        Map<String, String> result = new HashMap<>();
        if (config != null && keys != null) {
            for (String key : keys) {
                ConfigParam param = config.get(key);
                if (param != null) {
                    result.put(key, param.getValue());
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends AbstractConfigurationModel> T loadConfiguration(T model) throws ServiceException {
        if (model != null) {
            Map<String, String> data = getModuleProperties(model.getModule(), model.getKeys());
            model.getData().putAll(data);
        }
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends AbstractConfigurationModel> T loadConfiguration(Class<T> clazz) throws ServiceException {
        T result = null;
        try {
            result = clazz.getConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.log(Level.SEVERE, "Error by creating the configuration model instance!", ex);
        }
        result = loadConfiguration(result);
        return result;
    }
}
