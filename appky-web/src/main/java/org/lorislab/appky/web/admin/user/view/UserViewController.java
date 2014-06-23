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
package org.lorislab.appky.web.admin.user.view;

import org.lorislab.appky.web.admin.role.view.RoleConverterViewController;
import org.lorislab.appky.process.factory.CommonObjectFactory;
import org.lorislab.appky.web.admin.user.action.UserCreateAction;
import org.lorislab.appky.web.admin.user.action.UserDeleteAction;
import org.lorislab.appky.web.admin.user.action.UserSaveAction;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.ejb.UserProfileService;
import org.lorislab.appky.application.factory.UserObjectFactory;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.trash.ejb.TrashService;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.view.AbstractEntityViewController;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

/**
 * The user view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("userVC")
@SessionScoped
public class UserViewController extends AbstractEntityViewController<UserProfile> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -7610809207801013992L;
    /**
     * The user profile service.
     */
    @EJB
    private UserProfileService service;
    /**
     * The user table view controller.
     */
    @Inject
    private UserTableViewController userTableVC;
    /**
     * The trash service.
     */
    @EJB
    private TrashService trashService;
    /**
     * The role converter view controller.
     */
    @Inject
    private RoleConverterViewController roleConverterVC;
    /**
     * The dual list of roles.
     */
    private DualListModel<Role> userRoles;
    /**
     * The create action.
     */
    private UserCreateAction createAction;
    /**
     * The user save action.
     */
    private UserSaveAction saveAction;
    /**
     * The user delete action.
     */
    private UserDeleteAction deleteAction;

    /**
     * The default constructor.
     */
    public UserViewController() {
        createAction = new UserCreateAction(this);
        saveAction = new UserSaveAction(this);
        deleteAction = new UserDeleteAction(this);
        userRoles = new DualListModel<>();
    }

    /**
     * Gets the create action.
     *
     * @return the create action.
     */
    public UserCreateAction getCreateAction() {
        return createAction;
    }

    /**
     * Gets the user delete action.
     *
     * @return the user delete action.
     */
    public UserDeleteAction getDeleteAction() {
        return deleteAction;
    }

    /**
     * Gets the user save action.
     *
     * @return the user save action.
     */
    public UserSaveAction getSaveAction() {
        return saveAction;
    }

    /**
     * Gets the dual list or roles.
     *
     * @return the dual list or roles.
     */
    public DualListModel<Role> getUserRoles() {
        return userRoles;
    }

    /**
     * Sets the user roles dual list.
     *
     * @param userRoles the user roles dual list.
     */
    public void setUserRoles(DualListModel<Role> userRoles) {
        this.userRoles = userRoles;
    }

    /**
     * On row select event method.
     *
     * @param event on row select event.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void onRowSelect(SelectEvent event) throws Exception {
        UserProfile userProfile = (UserProfile) event.getObject();
        open(userProfile.getGuid());
    }

    /**
     * Creates the user profile.
     */
    @FacesServiceMethod
    public void create() {
        model = UserObjectFactory.createUserProfile();
        CommonObjectFactory.setDefaultUserProfileConfigParam(model);
        userRoles = new DualListModel<>();
    }

    /**
     * Opens the user profile.
     *
     * @param guid the GUID.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void open(String guid) throws Exception {
        userRoles = new DualListModel<>();
        model = service.loadFullUserProfile(guid);
        if (model != null) {
            userRoles = roleConverterVC.createRoleDualListModel(model.getRoles(), true);
        }
    }

    /**
     * Deletes the user profile.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void delete() throws Exception {
        trashService.trashUser(model);
        userTableVC.tableDelete(model);
        model = null;
        userRoles = new DualListModel<>();
    }

    /**
     * Saves the user profile.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void save() throws Exception {
        model.setRoles(userRoles.getTarget());
        model = service.saveUserProfile(model);
        if (model != null) {
            userTableVC.tableUpdate(model);
        }
        model = null;
        userRoles = new DualListModel<>();
    }
}
