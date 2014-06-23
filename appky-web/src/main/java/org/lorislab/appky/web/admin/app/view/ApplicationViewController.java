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

import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.ejb.ApplicationService;
import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.trash.ejb.TrashService;
import org.lorislab.appky.process.config.ServerConfiguration;
import org.lorislab.appky.web.admin.app.action.ApplicationDeleteAction;
import org.lorislab.appky.web.admin.app.action.ApplicationSaveAction;
import org.lorislab.appky.web.admin.role.view.RoleConverterViewController;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.view.AbstractEntityViewController;
import org.primefaces.model.DualListModel;

/**
 * The application view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("applicationVC")
@SessionScoped
public class ApplicationViewController extends AbstractEntityViewController<Application> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 7547269948285757352L;
    /**
     * The application service.
     */
    @EJB
    private ApplicationService service;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationService configService;
    /**
     * The trash service.
     */
    @EJB
    private TrashService trashService;
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
     * The dual list for the roles.
     */
    private DualListModel<Role> applicationRoles;
    /**
     * The description view controller.
     */
    private DescriptionViewController descriptionVC;
    /**
     * The title view controller.
     */
    private DescriptionViewController titleVC;
    /**
     * The application save action.
     */
    private ApplicationSaveAction saveAction;
    /**
     * The application delete action.
     */
    private ApplicationDeleteAction deleteAction;

    /**
     * The default constructor.
     */
    public ApplicationViewController() {
        descriptionVC = new DescriptionViewController();
        titleVC = new DescriptionViewController();
        saveAction = new ApplicationSaveAction(this);
        deleteAction = new ApplicationDeleteAction(this);
    }

    /**
     * Gets the save action.
     *
     * @return the save action.
     */
    public ApplicationSaveAction getSaveAction() {
        return saveAction;
    }

    /**
     * Gets the delete action.
     *
     * @return the delete action.
     */
    public ApplicationDeleteAction getDeleteAction() {
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
     * Gets the dual list of roles.
     *
     * @return the dual list of roles.
     */
    public DualListModel<Role> getApplicationRoles() {
        return applicationRoles;
    }

    /**
     * Sets the dual list of roles.
     *
     * @param applicationRoles the dual list of roles.
     */
    public void setApplicationRoles(DualListModel<Role> applicationRoles) {
        this.applicationRoles = applicationRoles;
    }

    /**
     * Opens the application by GUID.
     *
     * @param guid the application GUID.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void open(String guid) throws Exception {
        if (guid != null) {
            model = service.loadFullApplication(guid);
            if (model != null) {
                applicationRoles = roleConverterVC.createRoleDualListModel(model.getRoles(), false);

                ServerConfiguration config = configService.getConfiguration(ServerConfiguration.class);
                List<Locale> serverLangs = config.getServerLangs();
                Locale defaultLocale = config.getServerLang();
                descriptionVC.open(model.getDescriptions(), serverLangs, defaultLocale);
                titleVC.open(model.getTitles(), serverLangs, defaultLocale);
            }
        }
    }

    /**
     * Saves the application.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void save() throws Exception {
        model.setRoles(applicationRoles.getTarget());
        model = service.saveApplication(model);
        treeViewController.updateModel(model);
    }

    /**
     * Deletes the application.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void delete() throws Exception {
        trashService.trashApplication(model);
        treeViewController.deleteModel(model);
        reset();
    }
}
