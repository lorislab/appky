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
package org.lorislab.appky.web.admin.role.view;


import org.lorislab.appky.web.admin.role.action.RoleCreateAction;
import org.lorislab.appky.web.admin.role.action.RoleDeleteAction;
import org.lorislab.appky.web.admin.role.action.RoleSaveAction;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.ejb.RoleService;
import org.lorislab.appky.application.factory.UserObjectFactory;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.trash.ejb.TrashService;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.view.AbstractEntityViewController;
import org.primefaces.event.SelectEvent;

/**
 * The role view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("roleVC")
@SessionScoped
public class RoleViewController extends AbstractEntityViewController<Role> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -7610809207801013992L;
    /**
     * The role service.
     */
    @EJB
    private RoleService service;
    /**
     * The role table view controller.
     */
    @Inject
    private RoleTableViewController roleTableVC;
    /**
     * The trash service.
     */
    @EJB
    private TrashService trashService;
    /**
     * The role save action.
     */
    private RoleSaveAction saveAction;
    /**
     * The role delete action.
     */
    private RoleDeleteAction deleteAction;
    /**
     * The role create action.
     */
    private RoleCreateAction createAction;

    /**
     * The default constructor.
     */
    public RoleViewController() {
        saveAction = new RoleSaveAction(this);
        createAction = new RoleCreateAction(this);
        deleteAction = new RoleDeleteAction(this);
    }

    /**
     * Gets the save action.
     *
     * @return the save action.
     */
    public RoleSaveAction getSaveAction() {
        return saveAction;
    }

    /**
     * Gets the create action.
     *
     * @return the create action.
     */
    public RoleCreateAction getCreateAction() {
        return createAction;
    }

    /**
     * Gets the delete action.
     *
     * @return the delete action.
     */
    public RoleDeleteAction getDeleteAction() {
        return deleteAction;
    }

    /**
     * On row select event method.
     *
     * @param event on row select event.
     */
    public void onRowSelect(SelectEvent event) throws Exception {
        Role role = (Role) event.getObject();
        open(role.getGuid());
    }

    /**
     * Opens the role by GUID.
     *
     * @param guid the GUID.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void open(String guid) throws Exception {
        model = service.loadRole(guid);
    }

    /**
     * Saves the role.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void save() throws Exception {
        model = service.saveRole(model);
        roleTableVC.tableUpdate(model);
        model = null;
    }

    /**
     * Creates the new role.
     */
    @FacesServiceMethod
    public void create() throws Exception {
        model = UserObjectFactory.createRole();
    }

    /**
     * Deletes the role.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void delete() throws Exception {
        trashService.trashRole(model);
        roleTableVC.tableDelete(model);
        model = null;
    }
}
