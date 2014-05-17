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

import org.lorislab.appky.web.admin.app.action.GroupDeleteAction;
import org.lorislab.appky.web.admin.app.action.GroupSaveAction;
import org.lorislab.appky.web.admin.app.model.DocumentImageType;
import org.lorislab.appky.web.admin.role.view.RoleConverterViewController;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.ejb.ApplicationServiceLocal;
import org.lorislab.appky.application.ejb.GroupServiceLocal;
import org.lorislab.appky.application.factory.ApplicationObjectFactory;
import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.model.Group;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.trash.ejb.TrashServiceLocal;
import org.lorislab.appky.config.ejb.ConfigurationServiceLocal;
import org.lorislab.appky.process.config.ServerConfiguration;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.view.AbstractEntityViewController;
import org.primefaces.model.DualListModel;

/**
 * The group view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("groupVC")
@SessionScoped
public class GroupViewController extends AbstractEntityViewController<Group> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 2604832300425774464L;
    /**
     * The group service.
     */
    @EJB
    private GroupServiceLocal service;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationServiceLocal configService;
    /**
     * The application service.
     */
    @EJB
    private ApplicationServiceLocal appService;
    /**
     * The trash service.
     */
    @EJB
    private TrashServiceLocal trashService;
    /**
     * The tree view controller.
     */
    @Inject
    private TreeViewController treeViewController;
    /**
     * The role converter controller.
     */
    @Inject
    private RoleConverterViewController roleConverterVC;
    /**
     * The default list of roles.
     */
    private DualListModel<Role> groupRoles;
    /**
     * The description view controller.
     */
    private DescriptionViewController descriptionVC;
    /**
     * The title view controller.
     */
    private DescriptionViewController titleVC;
    /**
     * The save group action.
     */
    private GroupSaveAction saveAction;
    /**
     * The delete group action.
     */
    private GroupDeleteAction deleteAction;

    /**
     * The default constructor.
     */
    public GroupViewController() {
        descriptionVC = new DescriptionViewController();
        titleVC = new DescriptionViewController();
        saveAction = new GroupSaveAction(this);
        deleteAction = new GroupDeleteAction(this);
    }

    /**
     * Gets the save group action.
     *
     * @return the save group action.
     */
    public GroupSaveAction getSaveAction() {
        return saveAction;
    }

    /**
     * Gets the delete group action.
     *
     * @return the delete group action.
     */
    public GroupDeleteAction getDeleteAction() {
        return deleteAction;
    }

    /**
     * Gets the description view controller.
     *
     * @return the description view controller.
     */
    public DescriptionViewController getDescriptionVC() {
        return descriptionVC;
    }

    /**
     * Gets the title view controller.
     *
     * @return the title view controller.
     */
    public DescriptionViewController getTitleVC() {
        return titleVC;
    }

    /**
     * Gets the dual list of group roles.
     *
     * @return the dual list of group roles.
     */
    public DualListModel<Role> getGroupRoles() {
        return groupRoles;
    }

    /**
     * Sets the dual list of group roles.
     *
     * @param groupRoles the dual list of group roles.
     */
    public void setGroupRoles(DualListModel<Role> groupRoles) {
        this.groupRoles = groupRoles;
    }

    /**
     * Adds the new application.
     *
     * @param name the application name.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void addApplication(String name) throws Exception {
        ServerConfiguration config = configService.loadConfiguration(ServerConfiguration.class);
        Application application = ApplicationObjectFactory.createApplication(name, config.getServerLang());
        application.setGroup(model);
        appService.saveApplication(application);
        treeViewController.addModel(application, model);
    }

    /**
     * Creates new group by name.
     *
     * @param name the group name.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void create(String name) throws Exception {
        if (name != null && !name.isEmpty()) {
            ServerConfiguration config = configService.loadConfiguration(new ServerConfiguration());
            Group newGroup = ApplicationObjectFactory.createGroup(name, config.getServerLang());
            service.saveGroup(newGroup);
            treeViewController.addModel(newGroup, null);
        }
    }

    /**
     * Open group by GUID.
     *
     * @param guid the group GUID.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void open(String guid) throws Exception {
        if (guid != null) {
            model = service.loadFullGroupByGuid(guid);
            if (model != null) {
                groupRoles = roleConverterVC.createRoleDualListModel(model.getRoles(), false);

                ServerConfiguration config = configService.loadConfiguration(new ServerConfiguration());
                List<Locale> serverLangs = config.getServerLangs();
                Locale defaultLocale = config.getServerLang();
                descriptionVC.open(model.getDescriptions(), serverLangs, defaultLocale);
                titleVC.open(model.getTitles(), serverLangs, defaultLocale);
            }
        }
    }

    /**
     * Saves the group.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void save() throws Exception {
        model.setRoles(groupRoles.getTarget());
        model = service.saveGroup(model);
        treeViewController.updateModel(model);
    }

    /**
     * Deletes the group.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void delete() throws Exception {
        trashService.trashGroup(model);
        treeViewController.deleteModel(model);
        reset();
    }

    /**
     * Updates image.
     *
     * @param type the image type.
     * @param guid the object GUID.
     * @param document the document.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void updateImage(DocumentImageType type, Object guid, Document document) throws Exception {
        switch (type) {
            case ICON:
                ApplicationObjectFactory.copyDocument(model.getIcon(), document);
                break;
        }
    }

    /**
     * Deletes image.
     *
     * @param type the image type.
     * @param guid the object GUID..
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void deleteImage(DocumentImageType type, Object guid) throws Exception {
        switch (type) {
            case ICON:
                model.setIcon(ApplicationObjectFactory.createDocument());
                break;
        }
    }
}
