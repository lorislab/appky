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


import org.lorislab.appky.application.model.enums.PlatformType;
import java.util.Locale;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;

/**
 * The application search criteria.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationSearchCriteria extends AbstractSearchCriteria {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 5710592761343904624L;
    /**
     * The deleted flag.
     */
    private Boolean deleted;
    /**
     * The enabled flag.
     */
    private Boolean enabled;
    /**
     * The application name.
     */
    private String name;
    /**
     * The application group GUID.
     */
    private String groupGuid;
    /**
     * The user GUID.
     */
    private String userGuid;
    /**
     * The user role deleted flag.
     */
    private Boolean userRoleDeleted;
    /**
     * The user role enabled flag.
     */
    private Boolean userRoleEnabled;
    /**
     * The platform type.
     */
    private PlatformType platformType;
    /**
     * The platform flag.
     */
    private Boolean platformEnabled;
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
        deleted = null;
        enabled = null;
        name = null;
        userRoleDeleted = null;
        userRoleEnabled = null;
        groupGuid = null;
        userGuid = null;
        platformType = null;
        platformEnabled = null;
        userLocale = null;
        defaultUserLocale = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return isEmpty(deleted, enabled, name, userRoleDeleted, userRoleEnabled,
                groupGuid, userGuid, platformType, platformEnabled, userLocale, defaultUserLocale);
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
     * Gets the platform enabled flag.
     *
     * @return the platform enabled flag.
     */
    public Boolean getPlatformEnabled() {
        return platformEnabled;
    }

    /**
     * Sets the platform enabled flag.
     *
     * @param platformEnabled the platform enabled flag.
     */
    public void setPlatformEnabled(Boolean platformEnabled) {
        this.platformEnabled = platformEnabled;
    }

    /**
     * Gets the platform type.
     *
     * @return the platform type.
     */
    public PlatformType getPlatformType() {
        return platformType;
    }

    /**
     * Sets the platform type.
     *
     * @param platformType the platform type.
     */
    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
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
     * Gets the enabled flag.
     *
     * @return the enabled flag
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled flag
     *
     * @param enabled the enabled flag
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the group ID.
     *
     * @return the group ID.
     */
    public String getGroupGuid() {
        return groupGuid;
    }

    /**
     * Sets the group ID.
     *
     * @param groupGuid the group ID.
     */
    public void setGroupGuid(String groupGuid) {
        this.groupGuid = groupGuid;
    }

    /**
     * Gets the user GUID.
     *
     * @return the user GUID.
     */
    public String getUserGuid() {
        return userGuid;
    }

    /**
     * Sets the user GUID.
     *
     * @param userGuid the user GUID.
     */
    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    /**
     * Gets the user role deleted flag.
     *
     * @return the user role deleted flag.
     */
    public Boolean getUserRoleDeleted() {
        return userRoleDeleted;
    }

    /**
     * Sets the user role deleted flag.
     *
     * @param userRoleDeleted the user role deleted flag.
     */
    public void setUserRoleDeleted(Boolean userRoleDeleted) {
        this.userRoleDeleted = userRoleDeleted;
    }

    /**
     * Sets the user role enabled flag.
     *
     * @param userRoleEnabled the user role enabled flag.
     */
    public void setUserRoleEnabled(Boolean userRoleEnabled) {
        this.userRoleEnabled = userRoleEnabled;
    }

    /**
     * Gets the user role enabled flag.
     *
     * @return the user role enabled flag.
     */
    public Boolean getUserRoleEnabled() {
        return userRoleEnabled;
    }
}
