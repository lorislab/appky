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


import java.util.Date;
import org.lorislab.appky.application.model.enums.EmailRequestStatus;
import org.lorislab.appky.application.model.enums.EmailRequestType;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;
import org.lorislab.jel.base.criteria.BetweenCriterion;

/**
 * The email request criteria.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class EmailRequestCriteria extends AbstractSearchCriteria {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1707792332810024237L;
    /**
     * The email request status.
     */
    private EmailRequestStatus status;
    /**
     * The creation date between criteria.
     */
    private BetweenCriterion<Date> creationDate;
    /**
     * The validate to flag.
     */
    private boolean validateTo;
    /**
     * The email.
     */
    private String email;
    /**
     * The email request type.
     */
    private EmailRequestType type;
    /**
     * The email request GUID.
     */
    private String guid;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        creationDate = new BetweenCriterion<>();
        email = null;
        status = null;
        type = null;
        guid = null;
        validateTo = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        if (isEmpty(guid, email, validateTo, status, type)) {
            return false;
        }
        if (isEmpty(creationDate)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the email request GUID.
     *
     * @return the email request GUID.
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the email request GUID.
     *
     * @param guid the email request GUID.
     */
    public void setGuid(String guid) {
        this.guid = guid;
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
     * @param status the email request status to set
     */
    public void setStatus(EmailRequestStatus status) {
        this.status = status;
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
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the between creation date.
     *
     * @return the between creation date.
     */
    public BetweenCriterion<Date> getCreationDate() {
        return creationDate;
    }

    /**
     * Gets the validate to flag.
     *
     * @return the validate to flag.
     */
    public boolean isValidateTo() {
        return validateTo;
    }

    /**
     * Sets the validate to flag.
     *
     * @param validateTo the validate to flag.
     */
    public void setValidateTo(boolean validateTo) {
        this.validateTo = validateTo;
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
}
