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
package org.lorislab.appky.web.mobile.view;

import org.lorislab.appky.web.UserAgentController;
import org.lorislab.appky.web.admin.profile.view.UserProfileViewController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.criteria.ApplicationSearchCriteria;
import org.lorislab.appky.application.model.enums.PlatformType;
import org.lorislab.appky.application.wrapper.ejb.ApplicationWrapperServiceLocal;
import org.lorislab.appky.application.wrapper.model.ApplicationWrapper;
import org.lorislab.appky.config.ejb.ConfigurationServiceLocal;
import org.lorislab.appky.process.config.ServerConfiguration;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.util.FacesUtil;

/**
 * The mobile application view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("mobileApplicationVC")
@SessionScoped
public class MobileApplicationViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2329418513920833121L;
    /**
     * The list of application wrappers.
     */
    private List<ApplicationWrapper> applications;
    /**
     * The application wrapper service.
     */
    @EJB
    private ApplicationWrapperServiceLocal service;
    /**
     * The user agent controller.
     */
    @Inject
    private UserAgentController userAgent;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationServiceLocal configService;
    /**
     * The user profile view controller.
     */
    @Inject
    private UserProfileViewController userProfileVC;
    /**
     * The application search criteria.
     */
    private ApplicationSearchCriteria criteria;

    /**
     * The default constructor.
     */
    public MobileApplicationViewController() {
        applications = new ArrayList<>();

        criteria = new ApplicationSearchCriteria();
        criteria.setUserRoleDeleted(false);
        criteria.setUserRoleEnabled(true);
        criteria.setDeleted(false);
        criteria.setEnabled(true);
        criteria.setPlatformEnabled(true);
    }

    /**
     * Opens the list of applications wrappers by group GUID.
     *
     * @param groupGuid the group GUID.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void open(String groupGuid) throws Exception {
        applications = new ArrayList<>();
        // set user locale
        Locale userLocale = userProfileVC.getModel().getLocale();
        criteria.setUserLocale(userLocale);

        // set default user locale
        ServerConfiguration config = configService.loadConfiguration(ServerConfiguration.class);
        Locale defaultLocale = config.getServerLang();
        criteria.setDefaultUserLocale(defaultLocale);
        // set user id
        criteria.setUserGuid(FacesUtil.getCurrentUser());
        // set group GUID
        criteria.setGroupGuid(groupGuid);
        // set platform
        if (userAgent.isIOSMobile()) {
            criteria.setPlatformType(PlatformType.IOS);
        } else if (userAgent.isAndroidMobile()) {
            criteria.setPlatformType(PlatformType.ANDROID);
        } else if (userAgent.isWindows8()) {
            criteria.setPlatformType(PlatformType.WINDOWS8);
        } else {
            criteria.setPlatformType(PlatformType.NOT_SUPPORTED);
        }

        applications = service.findApplicationsBySearchCriteria(criteria);

    }

    /**
     * Gets the list of application wrappers.
     *
     * @return the list of application wrappers.
     */
    public List<ApplicationWrapper> getApplications() {
        return applications;
    }
}
