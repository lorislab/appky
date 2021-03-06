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
package org.lorislab.appky.web.admin.role.action;


import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.web.admin.role.view.RoleViewController;
import org.lorislab.jel.jsf.view.AbstractViewControllerAction;

/**
 * The role delete action.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class RoleDeleteAction extends AbstractViewControllerAction<RoleViewController> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -7650717894295189862L;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     */
    public RoleDeleteAction(RoleViewController parent) {
        super(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        boolean result = !getParent().isEmpty();
        if (result) {
            Role role = getParent().getModel();
            result = role.isEditable() && !role.isNew();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute() throws Exception {
        getParent().delete();
        return null;
    }
}
