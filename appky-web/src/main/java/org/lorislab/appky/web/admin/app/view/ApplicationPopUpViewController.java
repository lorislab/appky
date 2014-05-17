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
package org.lorislab.appky.web.admin.app.view;

import org.lorislab.appky.web.admin.app.action.ApplicationPopUpCreateAction;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;

/**
 * The application pop-up view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("applicationPopUpVC")
@SessionScoped
public class ApplicationPopUpViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 2604832300425774464L;
    /**
     * The application name.
     */
    private String name;
    /**
     * The group view controller.
     */
    @Inject
    private GroupViewController groupVC;
    /**
     * The application pop-up create action.
     */
    private ApplicationPopUpCreateAction createAction;

    /**
     * The default constructor.
     */
    public ApplicationPopUpViewController() {
        createAction = new ApplicationPopUpCreateAction(this);
    }

    /**
     * Gets the application pop-up create action.
     *
     * @return the application pop-up create action.
     */
    public ApplicationPopUpCreateAction getCreateAction() {
        return createAction;
    }

    /**
     * Sets the application name.
     *
     * @param name the application name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the application name.
     *
     * @return the application name.
     */
    public String getName() {
        return name;
    }

    /**
     * Create application method.
     * 
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void create() throws Exception {
        if (name != null && !name.isEmpty()) {
            groupVC.addApplication(name);
            name = null;
        }
    }
}
