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
import org.lorislab.appky.application.ejb.VersionService;
import org.lorislab.appky.application.factory.ApplicationObjectFactory;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.application.trash.ejb.TrashService;
import org.lorislab.appky.process.config.ServerConfiguration;
import org.lorislab.appky.process.ejb.UserApplicationProcessService;
import org.lorislab.appky.web.admin.app.action.VersionCreateAction;
import org.lorislab.appky.web.admin.app.action.VersionDeleteAction;
import org.lorislab.appky.web.admin.app.action.VersionReleaseAction;
import org.lorislab.appky.web.admin.app.action.VersionSaveAction;
import org.lorislab.appky.web.admin.app.action.VersionUnreleaseAction;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.view.AbstractEntityViewController;
import org.primefaces.event.SelectEvent;

/**
 * The version view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("versionVC")
@SessionScoped
public class VersionViewController extends AbstractEntityViewController<Version> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6707739365812129182L;
    /**
     * The release action.
     */
    private VersionReleaseAction releaseAction;
    /**
     * The create action.
     */
    private VersionCreateAction createAction;
    /**
     * The save action.
     */
    private VersionSaveAction saveAction;
    /**
     * The delete action.
     */
    private VersionDeleteAction deleteAction;
    /**
     * The un-release action.
     */
    private VersionUnreleaseAction unreleaseAction;
    /**
     * The description view controller.
     */
    private DescriptionViewController descriptionVC;
    /**
     * The version service.
     */
    @EJB
    protected VersionService service;
    /**
     * The user application process service.
     */
    @EJB
    private UserApplicationProcessService processService;
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
     * The versions list view controller.
     */
    @Inject
    private VersionsListViewController versionsVC;
    /**
     * The platform.
     */
    private Platform platform;

    /**
     * The default constructor.
     */
    public VersionViewController() {
        releaseAction = new VersionReleaseAction(this);
        saveAction = new VersionSaveAction(this);
        deleteAction = new VersionDeleteAction(this);
        createAction = new VersionCreateAction(this);
        unreleaseAction = new VersionUnreleaseAction(this);
        descriptionVC = new DescriptionViewController();
    }

    /**
     * Gets the un-release action.
     *
     * @return the un-release action.
     */
    public VersionUnreleaseAction getUnreleaseAction() {
        return unreleaseAction;
    }

    /**
     * Gets the create action.
     *
     * @return the create action.
     */
    public VersionCreateAction getCreateAction() {
        return createAction;
    }

    /**
     * Gets the save action.
     *
     * @return the save action.
     */
    public VersionSaveAction getSaveAction() {
        return saveAction;
    }

    /**
     * Gets the delete action.
     *
     * @return the delete action.
     */
    public VersionDeleteAction getDeleteAction() {
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
     * Gets the release action.
     *
     * @return the release action.
     */
    public VersionReleaseAction getReleaseAction() {
        return releaseAction;
    }

    /**
     * Gets the platform.
     *
     * @return the platform.
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Opens the platform.
     *
     * @param platform the platform.
     */
    @FacesServiceMethod
    public void open(Platform platform) throws Exception {
        if (platform != null) {
            this.platform = platform;
            reset();
            versionsVC.search();
        }
    }

    /**
     * On row select event method.
     *
     * @param event row select event.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void onRowSelect(SelectEvent event) throws Exception {
        Version version = (Version) event.getObject();
        open(version.getGuid());
    }

    /**
     * Creates new version.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void create() throws Exception {
        ServerConfiguration config = configService.getConfiguration(ServerConfiguration.class);
        model = ApplicationObjectFactory.createVersion(platform, config.getServerLang());
        if (model != null) {
            List<Locale> serverLangs = config.getServerLangs();
            Locale defaultLocale = config.getServerLang();
            descriptionVC.open(model.getDescriptions(), serverLangs, defaultLocale);
        }
    }

    /**
     * Opens the version by GUID.
     *
     * @param guid the GUID.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void open(String guid) throws Exception {
        if (guid != null) {
            model = service.loadFullVersion(guid);
            if (model != null) {
                ServerConfiguration config = configService.getConfiguration(ServerConfiguration.class);
                List<Locale> serverLangs = config.getServerLangs();
                Locale defaultLocale = config.getServerLang();
                descriptionVC.open(model.getDescriptions(), serverLangs, defaultLocale);
            }
        }
    }

    /**
     * Saves the version.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void save() throws Exception {
        model = service.saveVersion(model);
        versionsVC.tableUpdate(model);
    }

    /**
     * Un-release the version.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void unrelease() throws Exception {
        model = processService.unreleaseVersion(model);
        versionsVC.tableUpdate(model);
    }

    /**
     * Releases the version.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void release() throws Exception {
        model = processService.releaseVersion(model);
        versionsVC.search();
    }

    /**
     * Deletes the version.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void delete() throws Exception {
        trashService.trashVersion(model);
        versionsVC.tableDelete(model);
        model = null;
    }

    /**
     * Update package.
     *
     * @param document the package document.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void updatePackage(Document document) throws Exception {
        ApplicationObjectFactory.copyDocument(model.getData(), document);
    }
}
