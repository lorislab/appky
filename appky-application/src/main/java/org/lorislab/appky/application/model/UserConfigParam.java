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
package org.lorislab.appky.application.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lorislab.appky.application.model.enums.UserConfigParamType;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The user configuration parameter.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "CORE_USER_PARAM")
public class UserConfigParam extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 2279817589824206323L;
    /**
     * The name.
     */
    @Column(name = "C_NAME")
    private String name;
    /**
     * The value.
     */
    @Column(name = "C_VALUE")
    private String value;
    /**
     * The type.
     */
    @Column(name = "C_TYPE")
    @Enumerated(EnumType.STRING)
    private UserConfigParamType type;
    /**
     * The user profile.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "C_USER_GUID", insertable = false, updatable = false)
    private UserProfile userProfile;

    /**
     * Gets the user profile.
     *
     * @return the user profile.
     */
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Sets the user profile.
     *
     * @param userProfile the user profile.
     */
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
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
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type.
     *
     * @return the type.
     */
    public UserConfigParamType getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type.
     */
    public void setType(UserConfigParamType type) {
        this.type = type;
    }

    /**
     * Gets the value.
     *
     * @return the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the value.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
