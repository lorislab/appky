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
package org.lorislab.appky.config.model;

import org.lorislab.appky.config.model.enums.ConfigParamType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The configuration parameter.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "CORE_CONFIG_PARAM")
public class ConfigParam extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -7259693690432171338L;
    /**
     * The configuration.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "C_PARENT_GUID", insertable = false, updatable = false)
    private Config parent;
    /**
     * The parameter name.
     */
    @Column(name = "C_NAME")
    private String name;
    /**
     * The value.
     */
    @Column(name = "C_VALUE")
    private String value;
    /**
     * The default value.
     */
    @Column(name = "C_DEFAULT")
    private String defaultValue;
    /**
     * The parameter type.
     */
    @Column(name = "C_TYPE")
    @Enumerated(EnumType.STRING)
    private ConfigParamType type;
    /**
     * The editable flag.
     */
    @Column(name = "C_EDIT")
    private boolean editable;

    /**
     * Gets the editable flag.
     *
     * @return the editable flag.
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

    /**
     * Gets the configuration.
     *
     * @return the configuration.
     */
    public Config getParent() {
        return parent;
    }

    /**
     * Sets the configuration.
     *
     * @param parent the configuration.
     */
    public void setParent(Config parent) {
        this.parent = parent;
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
     * Sets the value.
     *
     * @return the value.
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the default value.
     *
     * @return the default value.
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value.
     *
     * @param defaultValue the default value.
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets the parameter type.
     *
     * @return the parameter type.
     */
    public ConfigParamType getType() {
        return type;
    }

    /**
     * Sets the parameter type.
     *
     * @param type the parameter type.
     */
    public void setType(ConfigParamType type) {
        this.type = type;
    }
}
