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
package org.lorislab.appky.web.mobile.view;

import java.io.Serializable;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.wrapper.ejb.ApplicationWrapperService;
import org.lorislab.appky.application.wrapper.model.ApplicationWrapper;
import org.lorislab.appky.application.wrapper.model.PlatformWrapper;
import org.lorislab.appky.process.config.ServerConfiguration;
import org.lorislab.appky.process.ejb.UserApplicationProcessService;
import org.lorislab.appky.process.model.ApplicationPackage;
import org.lorislab.appky.web.admin.profile.view.UserProfileViewController;
import org.lorislab.appky.web.mobile.action.RemoveApplicationAction;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;

/**
 * The mobile platform view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("mobilePlatformVC")
@SessionScoped
public class MobilePlatformViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2329418513920833121L;
    /**
     * The platform wrapper.
     */
    private PlatformWrapper wrapper;
    /**
     * The application wrapper.
     */
    private ApplicationWrapper applicationWrapper;
    /**
     * The remove application action.
     */
    private RemoveApplicationAction removeApplicationAction;
    /**
     * The user application process service.
     */
    @EJB
    private UserApplicationProcessService processService;
    /**
     * The application wrapper service.
     */
    @EJB
    private ApplicationWrapperService service;
    /**
     * The user profile view controller.
     */
    @Inject
    private UserProfileViewController userProfileVC;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationService configService;
    /**
     * The default constructor.
     */
    public MobilePlatformViewController() {
        removeApplicationAction = new RemoveApplicationAction(this);
    }

    /**
     * Gets the remove application action.
     *
     * @return the remove application action.
     */
    public RemoveApplicationAction getRemoveApplicationAction() {
        return removeApplicationAction;
    }

    /**
     * Gets the platform wrapper.
     *
     * @return the platform wrapper.
     */
    public PlatformWrapper getWrapper() {
        return wrapper;
    }

    /**
     * Gets the application wrapper.
     *
     * @return the application wrapper.
     */
    public ApplicationWrapper getApplicationWrapper() {
        return applicationWrapper;
    }

    /**
     * The remove application method.
     *
     * @throws ServiceException if the method fails.
     */
    @FacesServiceMethod
    public void removeApplication() throws ServiceException {
        UserApplication userApp = processService.uninstallApplication(wrapper);
        // update user application model in the view
        if (userApp != null) {
            wrapper.setUserApplication(userApp);
        }
    }

    /**
     * Process the application (install/update/reinstall)
     * 
     * @return the response object.
     * @throws ServiceException if the method fails.
     */
    @FacesServiceMethod
    public ApplicationPackage processApplication() throws ServiceException {
        ApplicationPackage result = null;
        if (wrapper != null) {
            // create and process application pacakge
            result = processService.processApplication(wrapper);
            // update user application model in the view
            if (result != null) {
                wrapper.setUserApplication(result.getUserApplication());
            }
        }
        return result;
    }
    
    /**
     * Open the platform wrapper by the application.
     *
     * @param applicationWrapper the application wrapper.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void open(ApplicationWrapper applicationWrapper) throws Exception {

        wrapper = null;
        this.applicationWrapper = applicationWrapper;

        if (this.applicationWrapper != null) {

            String guid = this.applicationWrapper.getPlatform().getGuid();
            UserProfile userProfile = userProfileVC.getModel();
            Locale userLocale = userProfile.getLocale();

            // set default user locale
            ServerConfiguration config = configService.getConfiguration(ServerConfiguration.class);
            Locale defaultLocale = config.getServerLang();

            wrapper = service.loadPlatformWrapperByPlatformGuid(guid, userProfile.getGuid(), userLocale, defaultLocale);
        }

    }
}
