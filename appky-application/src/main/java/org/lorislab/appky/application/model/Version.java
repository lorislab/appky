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


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.lorislab.appky.application.model.enums.VersionStatus;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The platform version.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "ST_VERSION")
public class Version extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -7531756282617808791L;
    /**
     * The name.
     */
    @Column(name = "C_NAME")
    private String name;
    /**
     * The release flag.
     */
    @Column(name = "C_RELEASED")
    private boolean released;
    /**
     * The release date.
     */
    @Column(name = "C_RELEASEDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releasedDate;
    /**
     * The deleted flag.
     */
    @Column(name = "C_DELETED")
    private boolean deleted;
    /**
     * The status.
     */
    @Column(name = "C_STATUS")
    @Enumerated(EnumType.STRING)
    private VersionStatus status;
    /**
     * The largeIcon.
     */
    @JoinColumn(name = "C_DATA")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Document data;
    /**
     * The description.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderColumn(name = "C_INDEX")
    @JoinTable(name = "ST_VERSION_DESC",
    joinColumns = {
        @JoinColumn(name = "C_VERSION")},
    inverseJoinColumns = {
        @JoinColumn(name = "C_DESCRIPTION")})
    private List<Description> descriptions;
    /**
     * The platform for this version.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "C_PLATFORM_GUID", insertable = true, updatable = true)
    private Platform platform;

    /**
     * The default constructor.
     */
    public Version() {
        descriptions = new ArrayList<>();
    }

    /**
     * Gets the release date.
     *
     * @return the release date.
     */
    public Date getReleasedDate() {
        return releasedDate;
    }

    /**
     * Sets the release date.
     *
     * @param releasedDate the release date.
     */
    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    /**
     * Returns
     * <code>true</code> if the version is deleted.
     *
     * @return <code>true</code> if the version is deleted.
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
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of description.
     *
     * @return the list of description.
     */
    public List<Description> getDescriptions() {
        return descriptions;
    }

    /**
     * Sets the list of description.
     *
     * @param descriptions the list of description.
     */
    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Gets the version status.
     *
     * @return the version status.
     */
    public VersionStatus getStatus() {
        return status;
    }

    /**
     * Sets the list of description.
     *
     * @param status the list of description.
     */
    public void setStatus(VersionStatus status) {
        this.status = status;
    }

    /**
     * Gets the data.
     *
     * @return the data.
     */
    public Document getData() {
        return data;
    }

    /**
     * Sets the data.
     *
     * @param data the data.
     */
    public void setData(Document data) {
        this.data = data;
    }

    /**
     * Returns
     * <code>true</code> if the version is released.
     *
     * @return <code>true</code> if the version is released.
     */
    public boolean isReleased() {
        return released;
    }

    /**
     * Sets the released flag.
     *
     * @param released the released flag.
     */
    public void setReleased(boolean released) {
        this.released = released;
    }

    /**
     * Gets the platform.
     *
     * @return the platform.
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Sets the platform.
     *
     * @param platform the platform.
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
