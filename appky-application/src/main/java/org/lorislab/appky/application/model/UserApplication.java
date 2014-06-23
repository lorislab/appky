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
package org.lorislab.appky.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import org.lorislab.appky.application.model.enums.UserApplicationStatus;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The user application.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "AY_USER_APP")
public class UserApplication extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 9042456214371866895L;
    /**
     * The user GUID.
     */
    @Column(name = "C_USER")
    private String userGuid;
    /**
     * The application GUID.
     */
    @Column(name = "C_APP")
    private String applicationGuid;
    /**
     * The platform GUID.
     */
    @Column(name = "C_PLATFORM")
    private String platformGuid;
    /**
     * The version GUID.
     */
    @Column(name = "C_VERSION")
    private String versionGuid;
    /**
     * The user application status.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "C_STATUS")
    private UserApplicationStatus status;

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
     * Gets the user application status.
     *
     * @return the user application status.
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
}
