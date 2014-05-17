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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.lorislab.appky.application.model.Role;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The application.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "ST_APPLICATION")
public class Application extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5455579600837938292L;
    /**
     * The name.
     */
    @Column(name = "C_NAME", unique = true)
    private String name;
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
     * The group.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "C_GROUP_GUID", insertable = true, updatable = true)
    private Group group;
    /**
     * The list of platform.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "C_APP_GUID")
    private List<Platform> platforms;
    /**
     * The list of roles.
     */
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @OrderColumn(name = "C_INDEX")
    @JoinTable(name = "ST_APP_ROLE",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"C_APP_ID", "C_ROLE_ID"})},
    joinColumns = {
        @JoinColumn(name = "C_APP_ID")},
    inverseJoinColumns = {
        @JoinColumn(name = "C_ROLE_ID")})
    private List<Role> roles;
    /**
     * The list of description.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderColumn(name = "C_INDEX")
    @JoinTable(name = "ST_APP_TITLE",
    joinColumns = {
        @JoinColumn(name = "C_APP")},
    inverseJoinColumns = {
        @JoinColumn(name = "C_DESCRIPTION")})
    private List<Description> titles;
    /**
     * The description.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderColumn(name = "C_INDEX")
    @JoinTable(name = "ST_APP_DESC",
    joinColumns = {
        @JoinColumn(name = "C_APP")},
    inverseJoinColumns = {
        @JoinColumn(name = "C_DESCRIPTION")})
    private List<Description> descriptions;

    /**
     * The default constructor.
     */
    public Application() {
        platforms = new ArrayList<>();
        roles = new ArrayList<>();
        titles = new ArrayList<>();
        descriptions = new ArrayList<>();
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
     * @param name the name to set/
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of titles.
     *
     * @return the list of description.
     */
    public List<Description> getTitles() {
        return titles;
    }

    /**
     * Sets the list of titles.
     *
     * @param titles the list of description.
     */
    public void setTitles(List<Description> titles) {
        this.titles = titles;
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
     * @param descriptions the description to set.
     */
    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Gets the group.
     *
     * @return the group.
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets the group.
     *
     * @param group the group.
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * Returns
     * <code>true</code> if the group is enabled.
     *
     * @return <code>true</code> if the group is enabled.
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
     * <code>true</code> if the group is deleted.
     *
     * @return <code>true</code> if the group is deleted.
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
     * Gets the list of platforms.
     *
     * @return the list of platforms.
     */
    public List<Platform> getPlatforms() {
        return platforms;
    }

    /**
     * Sets the list of platforms.
     *
     * @param platforms the list of platforms.
     */
    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    /**
     * Gets the list of roles.
     *
     * @return the list of roles.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the list of roles.
     *
     * @param roles the list of roles.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
