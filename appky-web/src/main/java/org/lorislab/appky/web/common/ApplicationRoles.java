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
package org.lorislab.appky.web.common;

/**
 * The application roles.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ApplicationRoles {
    
    public static final String USER_REG_ROLE = "u-reg-store";
    
    public static final String USER_CREATE_ROLE = "u-store";
    
    public static final String MENU_APPLICATION_ROLE = "menu-app";
    
    public static final String MENU_USERS_ROLE = "menu-users";
    
    public static final String MENU_PREFERENCES_ROLE = "menu-preferences";
    
    public static final String MENU_STATISTICS_ROLE = "menu-statistics";
    
    public static final String MENU_INVITATION_ROLE = "menu-invitation";
    
    public static final String MENU_PROFILE_ROLE = USER_CREATE_ROLE;
    
    public static final String USER_APP_ADMIN = "u-app-admin";
    
    /**
     * The default constructor.
     */
    private ApplicationRoles() {
        // empty constructor
    }
}
