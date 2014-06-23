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
package org.lorislab.appky.web.admin.profile.view;


import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.appky.application.ejb.UserProfileService;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.process.config.ServerConfiguration;
import org.lorislab.appky.web.admin.profile.action.SaveProfileAction;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.view.AbstractEntityViewController;

/**
 * The user profile view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("userProfileVC")
@SessionScoped
public class UserProfileViewController extends AbstractEntityViewController<UserProfile> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -3010689055700294402L;
    
    /**
     * The logger for this class. 
     */
    private static final Logger LOGGER = Logger.getLogger(UserProfileViewController.class.getName());
    
    /**
     * The user profile service.
     */
    @EJB
    private UserProfileService userProfileService;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationService configService;
    /**
     * The save profile action.
     */
    private SaveProfileAction saveAction;

    /**
     * The server default list of locale.
     */
    private List<Locale> locales;
    
    /**
     * The default constructor.
     */
    public UserProfileViewController() {
        saveAction = new SaveProfileAction(this);      
    }

    /**
     * The post construct method.
     */
    @PostConstruct
    public void postConstruct() {
        ServerConfiguration config = configService.getConfiguration(ServerConfiguration.class);
        locales = config.getServerLangs();          
    }
    
    /**
     * Gets the save action.
     *
     * @return the save action.
     */
    public SaveProfileAction getSaveAction() {
        return saveAction;
    }

    /**
     * Gets the GUID.
     *
     * @return the GUID.
     */
    public String getGuid() {
        if (model != null) {
            return model.getGuid();
        }
        return null;
    }

    /**
     * Gets the list of server languages.
     *
     * @return the list of server languages.
     * @throws Exception if the method fails.
     */
    public List<Locale> getLocales() throws Exception {
        return locales;
    }

    /**
     * Saves the user profile.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void save() throws Exception {
        model = userProfileService.saveUserProfile(model);
    }
}
