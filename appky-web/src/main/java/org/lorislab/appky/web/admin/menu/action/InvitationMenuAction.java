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
package org.lorislab.appky.web.admin.menu.action;

import org.lorislab.appky.web.common.ApplicationRoles;
import org.lorislab.appky.web.Navigation;
import org.lorislab.appky.web.admin.menu.view.MenuViewController;
import org.lorislab.jel.jsf.util.FacesUtil;
import org.lorislab.jel.jsf.view.AbstractViewControllerAction;

/**
 * The invitation menu action.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class InvitationMenuAction extends AbstractViewControllerAction<MenuViewController> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 2114892282764599009L;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     */
    public InvitationMenuAction(MenuViewController parent) {
        super(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAvailable() {
        return FacesUtil.isUserInRole(ApplicationRoles.MENU_INVITATION_ROLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute() throws Exception {
        return Navigation.NAV_MENU_TO_INVITATION;
    }
}
