/*
 * Copyright 2013 Andrej Petras <andrej@ajka-andrej.com>.
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The activity model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "TR_ACTIVITY")
public class Activity extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 544289509425982033L;
  
    /**
     * The user.
     */
    @Column(name = "C_USER")
    private String user;
    /**
     * The module.
     */
    @Column(name = "C_MODULE")
    private String module;
    /**
     * The creation date.
     */
    @Column(name = "C_CREATIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    /**
     * The activity code.
     */
    @Column(name = "C_ACTIVITY")
    private String activity;
    /**
     * The activity parameters.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "C_PARENT_GUID")
    private List<ActivityParameter> parameters;

    /**
     * The default constructor.
     */
    public Activity() {
        parameters = new ArrayList<>();
    }

    /**
     * Creates the creation date.
     */
    @PrePersist
    protected void prePersist() {
        creationDate = new Date();
    }

    /**
     * Gets the user.
     *
     * @return the user.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the user to set.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the module.
     *
     * @return the module.
     */
    public String getModule() {
        return module;
    }

    /**
     * Sets the module.
     *
     * @param module the module to set.
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * Gets the creation date.
     *
     * @return the creation date.
     */
    public Date getCreationDate() {
        return creationDate;
    }
//
//    /**
//     * Sets the creation date.
//     *
//     * @param creationDate the creationDate to set.
//     */
//    protected void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate;
//    }

    /**
     * Gets the activity.
     *
     * @return the activity.
     */
    public String getActivity() {
        return activity;
    }

    /**
     * Sets the activity.
     *
     * @param activity the activity to set.
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * Gets the activity parameters.
     *
     * @return the activity parameters.
     */
    public List<ActivityParameter> getParameters() {
        return parameters;
    }

    /**
     * Sets the activity parameters.
     *
     * @param parameters the activity parameters.
     */
    public void setParameters(List<ActivityParameter> parameters) {
        this.parameters = parameters;
    }
}
