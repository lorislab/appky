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
package org.lorislab.appky.application.criteria;

import org.lorislab.appky.application.model.enums.PlatformType;
import java.util.Locale;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;

/**
 * The platform search criteria.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class PlatformSearchCriteria extends AbstractSearchCriteria {
    /** The UID for this class. */
    private static final long serialVersionUID = 8503822128198650355L;
    
    private String guid;
    
    private String applicationGuid;
    
    private PlatformType type;
    
    private Boolean enabled;
    /**
     * The user locale.
     */
    private Locale userLocale;
    /**
     * The user default locale.
     */
    private Locale defaultUserLocale;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        guid = null;
        applicationGuid = null;
        userLocale = null;
        defaultUserLocale = null;
        type = null;
        enabled = null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return isEmpty(guid, applicationGuid, userLocale, defaultUserLocale, type, enabled);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public PlatformType getType() {
        return type;
    }

    public void setType(PlatformType type) {
        this.type = type;
    }
    
    public String getApplicationGuid() {
        return applicationGuid;
    }

    public void setApplicationGuid(String applicationGuid) {
        this.applicationGuid = applicationGuid;
    }

    public Locale getDefaultUserLocale() {
        return defaultUserLocale;
    }

    public void setDefaultUserLocale(Locale defaultUserLocale) {
        this.defaultUserLocale = defaultUserLocale;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Locale getUserLocale() {
        return userLocale;
    }

    public void setUserLocale(Locale userLocale) {
        this.userLocale = userLocale;
    }    
}
