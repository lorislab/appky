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
import java.util.Locale;
import javax.persistence.*;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The user profile.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "AY_USER_PROFILE")
public class UserProfile extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 5745546844622663915L;
    /**
     * The first name.
     */
    @Column(name = "C_FIRSTNAME")
    private String firstName;
    /**
     * The middle name.
     */
    @Column(name = "C_MIDDLENAME")
    private String middleName;
    /**
     * The last name.
     */
    @Column(name = "C_LASTNAME")
    private String lastName;
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
     * The email.
     */
    @Column(name = "C_EMAIL", unique = true)
    private String email;
    /**
     * The locale.
     */
    @Column(name = "C_LANG")
    private Locale locale;
    /**
     * The list of user configuration parameter.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderColumn(name = "C_INDEX")
    @JoinColumn(name = "C_USER_GUID")
    private List<UserConfigParam> properties;
    /**
     * The user roles list.
     */
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "AY_USER_ROLES",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"C_USER_ID", "C_ROLE_ID"})},
    joinColumns = {
        @JoinColumn(name = "C_USER_ID")},
    inverseJoinColumns = {
        @JoinColumn(name = "C_ROLE_ID")})
    private List<Role> roles;

    /**
     * The default constructor.
     */
    public UserProfile() {
        properties = new ArrayList<>();
        roles = new ArrayList<>();
    }

    /**
     * Gets the locale.
     *
     * @return the locale.
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets the locale.
     *
     * @param locale the locale.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Returns
     * <code>true</code> if the user was deleted.
     *
     * @return <code>true</code> if the user was deleted.
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
     * <code>true</code> if the user was enabled.
     *
     * @return <code>true</code> if the user was enabled.
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
     * Gets the first name.
     *
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the middle name.
     *
     * @return the middle name.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name.
     *
     * @param middleName the middle name.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @param email the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user configuration parameters.
     *
     * @return the user configuration parameters.
     */
    public List<UserConfigParam> getProperties() {
        return properties;
    }

    /**
     * Sets the user configuration parameters.
     *
     * @param properties the user configuration parameters.
     */
    public void setProperties(List<UserConfigParam> properties) {
        this.properties = properties;
    }

    /**
     * Gets the roles.
     *
     * @return the roles.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles.
     *
     * @param roles the roles.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
