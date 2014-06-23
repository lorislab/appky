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
package org.lorislab.appky.process.config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * The server configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ServerConfiguration implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 5240550628044434862L;

    /**
     * The list of server languages key.
     */
    private List<Locale> serverLangs = Arrays.asList(Locale.ENGLISH, Locale.GERMAN);

    /**
     * The server default language key.
     */
    private Locale serverLang = Locale.ENGLISH;
    
    /**
     * The server URL.
     */
    private String serverUrl = "http://localhost:8080/appky";   

    /**
     * Gets the server languages list.
     *
     * @return the server languages list.
     */
    public List<Locale> getServerLangs() {
        return serverLangs;
    }

    /**
     * Sets the server languages list.
     *
     * @param serverLangs the server languages list.
     */
    public void setServerLangs(List<Locale> serverLangs) {
        this.serverLangs = serverLangs;
    }
    
    /**
     * Gets the server language.
     *
     * @return the server language.
     */
    public Locale getServerLang() {
        return serverLang;
    }

    /**
     * Sets the server language.
     * 
     * @param serverLang the server language.
     */
    public void setServerLang(Locale serverLang) {
        this.serverLang = serverLang;
    }
        
    /**
     * Gets the server URL.
     *
     * @return the server URL.
     */
    public String getServerURL() {
        return serverUrl;
    }

    /**
     * Sets the server URL.
     *
     * @param serverUrl the server URL.
     */    
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
    
}
