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
package org.lorislab.appky.application.tmpresource.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The temporary resource.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "TMP_RESOURCE")
public class TemporaryResource extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 1135641957002181338L;
    /**
     * The user GUID.
     */
    @Column(name = "C_USERGUID")
    private String userGuid;
    /**
     * The validate to date.
     */
    @Column(name = "C_VALIDATETO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validateTo;
    /**
     * The set of resources.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "C_PARENT_GUID")
    private List<TemporaryResourceData> resources;

    /**
     * The default constructor.
     */
    public TemporaryResource() {
        resources = new ArrayList<>();
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
     * Gets the set of resources.
     *
     * @return the set of resources.
     */
    public List<TemporaryResourceData> getResources() {
        return resources;
    }

    /**
     * Sets the set of resources.
     *
     * @param resources the set of resources.
     */
    public void setResources(List<TemporaryResourceData> resources) {
        this.resources = resources;
    }
}
