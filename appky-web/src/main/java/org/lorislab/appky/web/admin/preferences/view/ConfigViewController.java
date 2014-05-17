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
package org.lorislab.appky.web.admin.preferences.view;


import org.lorislab.appky.web.admin.preferences.action.ConfigReloadAction;
import org.lorislab.appky.web.admin.preferences.action.ConfigSaveAction;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.appky.config.ejb.ConfigServiceLocal;
import org.lorislab.appky.config.ejb.ConfigurationServiceLocal;
import org.lorislab.appky.config.model.Config;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.util.FacesResourceUtil;
import org.lorislab.jel.jsf.util.FacesUtil;
import org.lorislab.jel.jsf.view.AbstractTableViewController;

/**
 * The configuration view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("configVC")
@SessionScoped
public class ConfigViewController extends AbstractTableViewController<Config> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -574938681000966231L;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationServiceLocal configService;
    /**
     * The configuration model service.
     */
    @EJB
    private ConfigServiceLocal service;
    /**
     * The configuration save and reload action.
     */
    private ConfigSaveAction saveAction;
    /**
     * The configuration reload action.
     */
    private ConfigReloadAction reloadAction;

    /**
     * The default constructor.
     */
    public ConfigViewController() {
        saveAction = new ConfigSaveAction(this);
        reloadAction = new ConfigReloadAction(this);
    }

    /**
     * Gets the configuration save and reload action.
     *
     * @return the configuration save and reload action.
     */
    public ConfigSaveAction getSaveAction() {
        return saveAction;
    }

    /**
     * Gets the configuration reload action.
     *
     * @return the configuration reload action.
     */
    public ConfigReloadAction getReloadAction() {
        return reloadAction;
    }

    /**
     * Saves the configuration.
     */
    @FacesServiceMethod
    public void save(Config config) throws Exception {
        config = service.saveConfig(config);
        this.tableUpdate(config);
    }

    /**
     * Reloads the configuration.
     */
    @FacesServiceMethod
    public void reload() throws Exception {
        configService.reloadConfiguration();
        search();
    }

    /**
     * Initialise the data for the view controller.
     *
     */
    @PostConstruct
    public void postConstruct() {
        try {
            search();
        } catch (Exception ex) {
            FacesResourceUtil.handleExceptionMessage(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @FacesServiceMethod
    public void search() throws Exception {
        super.search();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Config> doSearch() throws Exception {
        return service.findAllConfig();
    }
}
