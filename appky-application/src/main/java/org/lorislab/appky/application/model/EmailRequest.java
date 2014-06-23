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


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.lorislab.appky.application.model.enums.EmailRequestStatus;
import org.lorislab.appky.application.model.enums.EmailRequestType;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The email request.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "AY_REQUEST")
public class EmailRequest extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4924712010746372807L;
    /**
     * The user GUID.
     */
    @Column(name = "C_USER")
    private String userGuid;
    /**
     * The user email.
     */
    @Column(name = "C_EMAIL")
    private String email;
    /**
     * The email request type.
     */
    @Column(name = "C_TYPE")
    @Enumerated(EnumType.STRING)
    private EmailRequestType type;
    /**
     * The email request status.
     */
    @Column(name = "C_STATUS")
    @Enumerated(EnumType.STRING)
    private EmailRequestStatus status;
    /**
     * The parent GUID.
     */
    @Column(name = "C_PARENT")
    private String parentGuid;
    /**
     * The validate to date.
     */
    @Column(name = "C_VALIDATETO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validateTo;

    /**
     * Gets the validate to date.
     *
     * @return the validate to date.
     */
    public Date getValidateTo() {
        return validateTo;
    }

    /**
     * Sets the validate to date.
     *
     * @param validateTo the validate to date.
     */
    public void setValidateTo(Date validateTo) {
        this.validateTo = validateTo;
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
     * Gets the user email.
     *
     * @return the user email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user email.
     *
     * @param email the user email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the email request type.
     *
     * @return the email request type.
     */
    public EmailRequestType getType() {
        return type;
    }

    /**
     * Sets the email request type.
     *
     * @param type the email request type.
     */
    public void setType(EmailRequestType type) {
        this.type = type;
    }

    /**
     * Gets the email request status.
     *
     * @return the email request status.
     */
    public EmailRequestStatus getStatus() {
        return status;
    }

    /**
     * Sets the email request status.
     *
     * @param status the email request status.
     */
    public void setStatus(EmailRequestStatus status) {
        this.status = status;
    }

    /**
     * Gets the parent GUID.
     *
     * @return the parent GUID.
     */
    public String getParentGuid() {
        return parentGuid;
    }

    /**
     * Sets the parent GUID.
     *
     * @param parentGuid the parent GUID.
     */
    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid;
    }
}
