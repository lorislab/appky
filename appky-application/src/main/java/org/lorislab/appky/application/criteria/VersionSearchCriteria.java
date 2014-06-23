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
package org.lorislab.appky.application.criteria;


import java.util.Locale;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;

/**
 * The version search criteria.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class VersionSearchCriteria extends AbstractSearchCriteria {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 4791840766629210403L;
    /**
     * The version GUID.
     */
    private String guid;
    /**
     * The platform GUID.
     */
    private String platformGuid;
    /**
     * The deleted flag.
     */
    private Boolean deleted;
    /**
     * The released flag.
     */
    private Boolean released;
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
        platformGuid = null;
        deleted = null;
        released = null;
        userLocale = null;
        defaultUserLocale = null;
        guid = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return isEmpty(platformGuid, deleted, released, userLocale, defaultUserLocale, guid);
    }

    /**
     * Gets the version GUID.
     *
     * @return the version GUID.
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the version GUID.
     *
     * @param guid the version GUID.
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * Gets the default user locale.
     *
     * @return the default user locale.
     */
    public Locale getDefaultUserLocale() {
        return defaultUserLocale;
    }

    /**
     * Sets the default user locale.
     *
     * @param defaultUserLocale the default user locale.
     */
    public void setDefaultUserLocale(Locale defaultUserLocale) {
        this.defaultUserLocale = defaultUserLocale;
    }

    /**
     * Gets the user locale.
     *
     * @return the user locale.
     */
    public Locale getUserLocale() {
        return userLocale;
    }

    /**
     * Sets the user locale.
     *
     * @param userLocale the user locale.
     */
    public void setUserLocale(Locale userLocale) {
        this.userLocale = userLocale;
    }

    /**
     * Gets the deleted flag.
     *
     * @return the deleted flag.
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * Sets the deleted flag.
     *
     * @param deleted the deleted flag.
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Gets the platform GUID.
     *
     * @return the platform GUID.
     */
    public String getPlatformGuid() {
        return platformGuid;
    }

    /**
     * Sets the platform GUID.
     *
     * @param platformGuid the platform GUID.
     */
    public void setPlatformGuid(String platformGuid) {
        this.platformGuid = platformGuid;
    }

    /**
     * Gets the released flag.
     *
     * @return the released flag.
     */
    public Boolean getReleased() {
        return released;
    }

    /**
     * Sets the released flag.
     *
     * @param released the released flag.
     */
    public void setReleased(Boolean released) {
        this.released = released;
    }
}
