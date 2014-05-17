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
package org.lorislab.appky.web.admin.user.action;


import org.lorislab.appky.web.admin.user.view.UserTableViewController;
import org.lorislab.jel.jsf.view.AbstractViewControllerAction;

/**
 * The user search action.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class UserSearchAction extends AbstractViewControllerAction<UserTableViewController> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6615034367535842329L;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     */
    public UserSearchAction(UserTableViewController parent) {
        super(parent);
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute() throws Exception {
        getParent().search();
        return null;
    }
}