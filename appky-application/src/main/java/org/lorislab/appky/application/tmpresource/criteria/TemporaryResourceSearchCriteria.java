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
package org.lorislab.appky.application.tmpresource.criteria;


import java.util.Date;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;

/**
 * The temporary resource search criteria.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class TemporaryResourceSearchCriteria extends AbstractSearchCriteria {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 7274486869848216647L;
    /**
     * The GUID.
     */
    private String guid;
    /**
     * The validate to date.
     */
    private Date validateTo;
    /**
     * The resource.
     */
    private String resource;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        guid = null;
        validateTo = null;
        resource = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return isEmpty(guid, validateTo, resource);
    }

    /**
     * Gets the GUID.
     *
     * @return the GUID.
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the GUID.
     *
     * @param guid the GUID.
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * Gets the resource.
     *
     * @return the resource.
     */
    public String getResource() {
        return resource;
    }

    /**
     * Sets the resource.
     *
     * @param resource the resource.
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

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
}
