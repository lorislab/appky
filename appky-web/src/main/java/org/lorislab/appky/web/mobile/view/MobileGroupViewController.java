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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.criteria.GroupSearchCriteria;
import org.lorislab.appky.application.wrapper.ejb.ApplicationWrapperService;
import org.lorislab.appky.application.wrapper.model.GroupWrapper;
import org.lorislab.appky.process.config.ServerConfiguration;
import org.lorislab.appky.web.admin.profile.view.UserProfileViewController;
import org.lorislab.appky.web.common.ApplicationRoles;
import org.lorislab.appky.web.mobile.action.RefreshApplicationAction;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.util.FacesUtil;

/**
 * The mobile group view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("mobileGroupVC")
@SessionScoped
public class MobileGroupViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 510911000062957607L;
    /**
     * The refresh application action.
     */
    private RefreshApplicationAction refreshAction;
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
     * The list of group wrappers.
     */
    private List<GroupWrapper> groups;
    /**
     * The group search criteria.
     */
    private GroupSearchCriteria criteria;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationService configService;

    /**
     * The default constructor.
     */
    public MobileGroupViewController() {
        refreshAction = new RefreshApplicationAction(this);
        criteria = new GroupSearchCriteria();
        criteria.setDeleted(false);
        criteria.setUserRoleDeleted(false);
        criteria.setUserRoleEnabled(true);
    }

    /**
     * Gets the refresh action.
     *
     * @return the refresh action.
     */
    public RefreshApplicationAction getRefreshAction() {
        return refreshAction;
    }

    /**
     * The refresh method.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void refresh() throws Exception {
        groups = new ArrayList<>();
        // set user locale
        Locale userLocale = userProfileVC.getModel().getLocale();
        criteria.setUserLocale(userLocale);
        // set default user locale
        ServerConfiguration config = configService.getConfiguration(ServerConfiguration.class);
        Locale defaultLocale = config.getServerLang();
        criteria.setDefaultUserLocale(defaultLocale);
        // show only enabled groups                 
        boolean enabled = FacesUtil.isUserInRole(ApplicationRoles.USER_APP_ADMIN);
        criteria.setEnabled(enabled);
        // the user guid.
        String name = FacesUtil.getCurrentUser();
        criteria.setUserGuid(name);

        groups = service.findGroupsBySearchCriteria(criteria);
    }

    /**
     * Gets the list of group wrappers.
     *
     * @return the list of group wrappers.
     * @throws Exception if the method fails.
     */
    public List<GroupWrapper> getGroups() throws Exception {
        if (groups == null) {
            refresh();
        }
        return groups;
    }
}
