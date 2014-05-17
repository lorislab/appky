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


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The configuration model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "CORE_CONFIG")
public class Config extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1409289102340097888L;
    /**
     * The module name.
     */
    @Column(name = "C_MODULE", unique = true)
    private String module;
    /**
     * The list of parameters.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "C_PARENT_GUID") 
    private List<ConfigParam> properties;

    /**
     * The default constructor.
     */
    public Config() {
        properties = new ArrayList<>();
    }

    /**
     * Gets the list of configuration parameters.
     *
     * @return the list of configuration parameters.
     */
    public List<ConfigParam> getProperties() {
        return properties;
    }

    /**
     * Sets the list of configuration parameters.
     *
     * @param properties the list of configuration parameters.
     */
    public void setProperties(List<ConfigParam> properties) {
        this.properties = properties;
    }

    /**
     * Gets the module name.
     *
     * @return the module name.
     */
    public String getModule() {
        return module;
    }

    /**
     * Sets the module name.
     *
     * @param module the module name.
     */
    public void setModule(String module) {
        this.module = module;
    }
}
