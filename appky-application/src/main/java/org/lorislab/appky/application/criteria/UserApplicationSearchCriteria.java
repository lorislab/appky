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

import org.lorislab.appky.application.model.enums.UserApplicationStatus;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;

/**
 * The user application search criteria.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class UserApplicationSearchCriteria extends AbstractSearchCriteria {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5447358147943467188L;
    /**
     * The user GUID.
     */
    private String userGuid;
    /**
     * The platform GUID.
     */
    private String platformGuid;
    /**
     * The version GUID.
     */
    private String versionGuid;
    /**
     * The application GUID.
     */
    private String applicationGuid;
    /**
     * The user application status.
     */
    private UserApplicationStatus status;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        status = null;
        userGuid = null;
        platformGuid = null;
        versionGuid = null;
        applicationGuid = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return isEmpty(status, userGuid, platformGuid, versionGuid, applicationGuid);
    }

    /**
     * Gets the application GUID.
     *
     * @return the application GUID.
     */
    public String getApplicationGuid() {
        return applicationGuid;
    }

    /**
     * Sets the application GUID.
     *
     * @param applicationGuid the application GUID.
     */
    public void setApplicationGuid(String applicationGuid) {
        this.applicationGuid = applicationGuid;
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
     * Gets the version GUID.
     *
     * @return the version GUID.
     */
    public String getVersionGuid() {
        return versionGuid;
    }

    /**
     * Sets the version GUID.
     *
     * @param versionGuid the version GUID.
     */
    public void setVersionGuid(String versionGuid) {
        this.versionGuid = versionGuid;
    }

    /**
     * Gets the user application status.
     *
     * @return the status the user application status.
     */
    public UserApplicationStatus getStatus() {
        return status;
    }

    /**
     * Sets the user application status.
     *
     * @param status the user application status.
     */
    public void setStatus(UserApplicationStatus status) {
        this.status = status;
    }
}
