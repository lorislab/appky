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
package org.lorislab.appky.application.model;


import javax.persistence.*;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The role.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "CORE_ROLE")
public class Role extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -3475994436346328164L;
    /**
     * The role id.
     */
    @Column(name = "C_ID", unique = true)
    private String id;
    /**
     * The role name.
     */
    @Column(name = "C_NAME")
    private String name;
    /**
     * The editable flag.
     */
    @Column(name = "C_EDITABLE")
    private boolean editable;
    /**
     * The enabled flag.
     */
    @Column(name = "C_ENABLED")
    private boolean enabled;
    /**
     * The deleted flag.
     */
    @Column(name = "C_DELETED")
    private boolean deleted;

    /**
     * Gets the role id.
     *
     * @return the role id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the role id.
     *
     * @param id the role id.
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * Returns
     * <code>true</code> if the role was deleted.
     *
     * @return <code>true</code> if the role was deleted.
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the deleted flag.
     *
     * @param deleted the deleted flag.
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Returns
     * <code>true</code> if the role was enabled.
     *
     * @return <code>true</code> if the role was enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled flag.
     *
     * @param enabled the enabled flag.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns
     * <code>true</code> if the role was editable.
     *
     * @return <code>true</code> if the role was editable.
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Sets the editable flag.
     *
     * @param editable the editable flag.
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
