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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The group.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "AY_GROUP")
public class Group extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 8840538384894237166L;
    /**
     * The name.
     */
    @Column(name = "C_NAME")
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
     * The list of applications.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "C_GROUP_GUID")
    private List<Application> applications;
    /**
     * The icon.
     */
    @JoinColumn(name = "C_ICON")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Document icon;
    /**
     * The list of roles.
     */
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @OrderColumn(name = "C_INDEX")
    @JoinTable(name = "AY_GROUP_ROLE",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"C_GROUP_ID", "C_ROLE_ID"})},
    joinColumns = {
        @JoinColumn(name = "C_GROUP_ID")},
    inverseJoinColumns = {
        @JoinColumn(name = "C_ROLE_ID")})
    private List<Role> roles;
    /**
     * The list of titles.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderColumn(name = "C_INDEX")
    @JoinTable(name = "AY_GROUP_TITLE",
    joinColumns = {
        @JoinColumn(name = "C_GROUP")},
    inverseJoinColumns = {
        @JoinColumn(name = "C_DESCRIPTION")})
    private List<Description> titles;
    /**
     * The list of descriptions.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderColumn(name = "C_INDEX")
    @JoinTable(name = "AY_GROUP_DESC",
    joinColumns = {
        @JoinColumn(name = "C_GROUP")},
    inverseJoinColumns = {
        @JoinColumn(name = "C_DESCRIPTION")})
    private List<Description> descriptions;

    /**
     * The default constructor.
     */
    public Group() {
        applications = new ArrayList<>();
        roles = new ArrayList<>();
        descriptions = new ArrayList<>();
        titles = new ArrayList<>();
    }

    /**
     * Gets name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of titles.
     *
     * @return the list of titles.
     */
    public List<Description> getTitles() {
        return titles;
    }

    /**
     * Sets the list of titles.
     *
     * @param titles the list of titles.
     */
    public void setTitles(List<Description> titles) {
        this.titles = titles;
    }

    /**
     * Gets the list of descriptions.
     *
     * @return the list of descriptions.
     */
    public List<Description> getDescriptions() {
        return descriptions;
    }

    /**
     * Sets the list of descriptions.
     *
     * @param descriptions the list of descriptions.
     */
    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Gets the applications.
     *
     * @return the applications.
     */
    public List<Application> getApplications() {
        return applications;
    }

    /**
     * Sets the applications.
     *
     * @param applications the applications
     */
    public void setApplications(List<Application> applications) {
        this.applications = applications;
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
     * Gets the icon.
     *
     * @return the icon.
     */
    public Document getIcon() {
        return icon;
    }

    /**
     * Sets the icon.
     *
     * @param icon the icon.
     */
    public void setIcon(Document icon) {
        this.icon = icon;
    }
}
