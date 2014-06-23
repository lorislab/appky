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
package org.lorislab.appky.web.admin.app.view;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.web.admin.app.action.GroupPopUpCreateAction;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;

/**
 * The group pop-up view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("groupPopUpVC")
@SessionScoped
public class GroupPopUpViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 2604832300425774464L;
    /**
     * The group name.
     */
    private String name;
    /**
     * The group view controller.
     */
    @Inject
    private GroupViewController groupVC;
    /**
     * The group pop-up create action.
     */
    private GroupPopUpCreateAction createAction;

    /**
     * The default constructor.
     */
    public GroupPopUpViewController() {
        createAction = new GroupPopUpCreateAction(this);
    }

    /**
     * Gets the create group action.
     *
     * @return the create group action.
     */
    public GroupPopUpCreateAction getCreateAction() {
        return createAction;
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
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Creates new group corresponding to the name.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void create() throws Exception {
        if (name != null && !name.isEmpty()) {
            groupVC.create(name);
            name = null;
        }
    }
}
