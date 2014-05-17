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
package org.lorislab.appky.application.criteria;

import org.lorislab.appky.application.model.enums.UserApplicationStatus;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;

/**
 * The user profile search criteria.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class UserProfileSearchCriteria extends AbstractSearchCriteria {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 4890136811555693509L;
    /**
     * The first name.
     */
    private String firstName;
    /**
     * The middle name.
     */
    private String middleName;
    /**
     * The last name.
     */
    private String lastName;
    /**
     * The email.
     */
    private String email;
    /**
     * The enabled flag.
     */
    private Boolean enabled;
    /**
     * The deleted flag.
     */
    private Boolean deleted;
    /**
     * The platform GUID.
     */
    private String platformGuid;
    /**
     * The user application status.
     */
    private UserApplicationStatus userApplicationStatus;
    /**
     * The parameter name.
     */
    private String parameterName;
    /**
     * The parameter value.
     */
    private String parameterValue;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void reset() {
        email = null;
        deleted = null;
        enabled = null;
        firstName = null;
        middleName = null;
        lastName = null;
        platformGuid = null;
        userApplicationStatus = null;
        parameterName = null;
        parameterValue = null;
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
     * Gets the user application status.
     *
     * @return the user application status.
     */
    public UserApplicationStatus getUserApplicationStatus() {
        return userApplicationStatus;
    }

    /**
     * Sets the user application status.
     *
     * @param userApplicationStatus the user application status.
     */
    public void setUserApplicationStatus(UserApplicationStatus userApplicationStatus) {
        this.userApplicationStatus = userApplicationStatus;
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
     * @return the enabled flag.
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled flag.
     *
     * @param enabled the enabled flag.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the email.
     *
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the first name.
     *
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the middle name.
     *
     * @return the middle name.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name.
     *
     * @param middleName the middle name.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the parameter name.
     *
     * @return the parameter name.
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * Sets the parameter name.
     *
     * @param parameterName the parameter name.
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * Gets the parameter value.
     *
     * @return the parameter value.
     */
    public String getParameterValue() {
        return parameterValue;
    }

    /**
     * Sets the parameter value.
     *
     * @param parameterValue the parameter value.
     */
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return isEmpty(email, enabled, deleted, firstName, middleName, lastName,
                userApplicationStatus, platformGuid, parameterName, parameterValue);
    }
}
