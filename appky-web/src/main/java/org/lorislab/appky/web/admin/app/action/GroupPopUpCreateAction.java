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
package org.lorislab.appky.web.admin.app.action;

import org.lorislab.jel.jsf.view.AbstractViewControllerAction;
import org.lorislab.appky.web.admin.app.view.GroupPopUpViewController;

/**
 * The group pop-up create action.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class GroupPopUpCreateAction extends AbstractViewControllerAction<GroupPopUpViewController> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -3403311795813584482L;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     */
    public GroupPopUpCreateAction(GroupPopUpViewController parent) {
        super(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        String name = getParent().getName();
        return name != null && !name.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute() throws Exception {
        getParent().create();
        return null;
    }
}
