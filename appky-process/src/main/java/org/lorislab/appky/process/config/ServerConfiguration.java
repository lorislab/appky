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
import java.util.List;
import java.util.Locale;

/**
 * The server configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ServerConfiguration extends AbstractConfigurationModel {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 4216792931625355919L;
    /**
     * The module name.
     */
    private static final String MODULE = "server";
    /**
     * The list of server languages key.
     */
    private static final String CONF_SERVER_LANGS = "server.langs";
    /**
     * The list of server languages.
     */
    private static final String CONF_SERVER_LANGS_DEFAULT = "en,de";
    /**
     * The server default language key.
     */
    private static final String CONF_SERVER_LANG = "server.lang";
    /**
     * The default server language.
     */
    private static final String CONF_SERVER_LANG_DEFAULT = "en";
    /**
     * The server URL.
     */
    private static final String CONF_SERVER_URL = "server.url";
    /**
     * The server URL default value.
     */    
    private static final String CONF_SERVER_URL_DEFAULT = "http://localhost:8080/store";    

    /**
     * The default constructor.
     */
    public ServerConfiguration() {
        super(MODULE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefaultValues() {
        addValue(CONF_SERVER_URL, CONF_SERVER_URL_DEFAULT);
        addValue(CONF_SERVER_LANGS, CONF_SERVER_LANGS_DEFAULT);
        addValue(CONF_SERVER_LANG, CONF_SERVER_LANG_DEFAULT);
    }

    /**
     * Gets the server languages list.
     *
     * @return the server languages list.
     */
    public List<Locale> getServerLangs() {
        return getLocaleList(CONF_SERVER_LANGS);
    }

    /**
     * Gets the server language.
     *
     * @return the server language.
     */
    public Locale getServerLang() {
        return getLocaleValue(CONF_SERVER_LANG);
    }
    
    /**
     * Gets the server URL.
     *
     * @return the server URL.
     */
    public String getServerURL() {
        return getStringValue(CONF_SERVER_URL);
    }    
}
