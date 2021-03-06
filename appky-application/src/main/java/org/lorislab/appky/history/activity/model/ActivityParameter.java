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
package org.lorislab.appky.history.activity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "AY_ACTIVITY_PARAM")
public class ActivityParameter extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 2562167422512858890L;
    /**
     * The parameter name.
     */
    @Column(name = "C_NAME")
    private String name;
    /**
     * The parameter value.
     */
    @Column(name = "C_VALUE")
    private String value;

    /**
     * Gets the parameter name.
     *
     * @return the parameter name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the parameter name.
     *
     * @param name the parameter name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the parameter value.
     *
     * @return the parameter value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the parameter value.
     *
     * @param value the parameter value.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
