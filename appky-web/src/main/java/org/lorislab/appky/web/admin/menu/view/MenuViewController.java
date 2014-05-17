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
package org.lorislab.appky.web.admin.menu.view;

import org.lorislab.appky.web.Navigation;
import org.lorislab.appky.web.admin.app.view.TreeViewController;
import org.lorislab.appky.web.admin.menu.action.ApplicationMenuAction;
import org.lorislab.appky.web.admin.menu.action.HomeMenuAction;
import org.lorislab.appky.web.admin.menu.action.InvitationMenuAction;
import org.lorislab.appky.web.admin.menu.action.PreferencesMenuAction;
import org.lorislab.appky.web.admin.menu.action.ProfileMenuAction;
import org.lorislab.appky.web.admin.menu.action.RolesMenuAction;
import org.lorislab.appky.web.admin.menu.action.StatisticsMenuAction;
import org.lorislab.appky.web.admin.menu.action.UsersMenuAction;
import org.lorislab.appky.web.admin.preferences.view.ConfigViewController;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The menu view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@SessionScoped
@Named("menuVC")
public class MenuViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6908504289776795802L;
    /**
     * THe active menu index.
     */
    private int activeIndex;
    /**
     * The application menu action.
     */
    private ApplicationMenuAction applicationAction;
    /**
     * The home menu action.
     */
    private HomeMenuAction homeAction;
    /**
     * The invitation menu action.
     */
    private InvitationMenuAction invitationAction;
    /**
     * The preferences menu action.
     */
    private PreferencesMenuAction preferencesAction;
    /**
     * The profile menu action.
     */
    private ProfileMenuAction profileAction;
    /**
     * The users menu action.
     */
    private UsersMenuAction usersAction;
    /**
     * The roles menu action.
     */
    private RolesMenuAction rolesAction;    
    /**
     * The statistics menu action.
     */
    private StatisticsMenuAction statisticsAction;
    /**
     * The application view controller.
     */
    @Inject
    private TreeViewController treeVC;
    
    /**
     * The default constructor.
     */
    public MenuViewController() {
        activeIndex = 0;
        applicationAction = new ApplicationMenuAction(this);
        homeAction = new HomeMenuAction(this);
        invitationAction = new InvitationMenuAction(this);
        preferencesAction = new PreferencesMenuAction(this);
        profileAction = new ProfileMenuAction(this);
        usersAction = new UsersMenuAction(this);
        statisticsAction = new StatisticsMenuAction(this);
        rolesAction = new RolesMenuAction(this);
    }

    /**
     * Gets the menu active index.
     *
     * @return the menu active index.
     */
    public int getActiveIndex() {
        return activeIndex;
    }

    /**
     * Sets the menu active index.
     *
     * @param activeIndex the menu active index.
     */
    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    /**
     * Gets the statistics menu action.
     *
     * @return the statistics menu action.
     */
    public StatisticsMenuAction getStatisticsAction() {
        return statisticsAction;
    }

    /**
     * Gets the application menu action.
     *
     * @return the application menu action.
     */
    public ApplicationMenuAction getApplicationAction() {
        return applicationAction;
    }

    /**
     * Gets the home menu action.
     *
     * @return the home menu action.
     */
    public HomeMenuAction getHomeAction() {
        return homeAction;
    }

    /**
     * Gets the invitation menu action.
     *
     * @return the invitation menu action.
     */
    public InvitationMenuAction getInvitationAction() {
        return invitationAction;
    }

    /**
     * Gets the preferences menu action.
     *
     * @return the preferences menu action.
     */
    public PreferencesMenuAction getPreferencesAction() {
        return preferencesAction;
    }

    /**
     * Gets the profile menu action.
     *
     * @return the profile menu action.
     */
    public ProfileMenuAction getProfileAction() {
        return profileAction;
    }

    /**
     * Gets the users menu action.
     *
     * @return the users menu action.
     */
    public UsersMenuAction getUsersAction() {
        return usersAction;
    }

    /**
     * Gets the roles menu action.
     *
     * @return the roles menu action.
     */
    public RolesMenuAction getRolesAction() {
        return rolesAction;
    }
    
    /**
     * The menu application action.
     *
     * @throws Exception if the method fails.
     */
    public Object menuApplicationAction() throws Exception {
        treeVC.getSearchAction().execute();
        return Navigation.NAV_MENU_TO_APP;
    }

    public Object menuHomeAction() throws Exception {
        return Navigation.NAV_MENU_TO_HOME;
    }

    public Object menuUsersAction() throws Exception {
        return Navigation.NAV_MENU_TO_USERS;
    }

    public Object menuRolesAction() throws Exception {
        return Navigation.NAV_MENU_TO_ROLES;
    }
    
    public Object menuPreferencesAction() throws Exception {
        return Navigation.NAV_MENU_TO_PREFERENCES;
    }
}
