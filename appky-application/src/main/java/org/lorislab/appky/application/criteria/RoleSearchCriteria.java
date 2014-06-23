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

import org.lorislab.jel.base.criteria.AbstractSearchCriteria;

/**
 * The role search criteria.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class RoleSearchCriteria extends AbstractSearchCriteria {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -8947944587648721643L;
    /**
     * The role name.
     */
    private String name;
    /**
     * The editable flag.
     */
    private Boolean editable;
    /**
     * The deleted flag.
     */
    private Boolean deleted;

    /**
     * Gets the role name.
     *
     * @return the role name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the role name.
     *
     * @param name the role name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the editable flag.
     *
     * @return the editable flag.
     */
    public Boolean getEditable() {
        return editable;
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
     * Gets the deleted flag.
     *
     * @return the deleted flag.
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * Sets the editable flag.
     *
     * @param editable the editable flag.
     */
    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void reset() {
        deleted = null;
        editable = null;
        name = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return isEmpty(deleted, editable, name);
    }
}
