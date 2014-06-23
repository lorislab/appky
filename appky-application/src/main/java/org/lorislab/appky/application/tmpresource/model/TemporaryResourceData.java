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
package org.lorislab.appky.application.tmpresource.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The temporary resource data.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "AY_RESOURCE_DATA")
public class TemporaryResourceData extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2252874722032372194L;
    /**
     * The parent.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "C_PARENT_GUID", insertable = false, updatable = false)
    private TemporaryResource parent;
    
    /**
     * The resource GUID.
     */
    @Column(name = "C_RESOURCE")
    private String resourceGuid;

    /**
     * Gets the parent.
     *
     * @return the parent.
     */
    public TemporaryResource getParent() {
        return parent;
    }

    /**
     * Sets the parent.
     *
     * @param parent the parent.
     */
    public void setParent(TemporaryResource parent) {
        this.parent = parent;
    }

    /**
     * Gets the resource GUID.
     *
     * @return the resource GUID.
     */
    public String getResourceGuid() {
        return resourceGuid;
    }

    /**
     * Sets the resource GUID.
     *
     * @param resourceGuid the resource GUID.
     */
    public void setResourceGuid(String resourceGuid) {
        this.resourceGuid = resourceGuid;
    }
}
