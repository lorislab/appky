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
package org.lorislab.appky.web.admin.invitation.action;

import org.lorislab.appky.web.admin.invitation.view.InvitationViewController;
import org.lorislab.jel.jsf.view.AbstractViewControllerAction;

/**
 * The send invitation action.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SendInvitationAction extends AbstractViewControllerAction<InvitationViewController> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -6910197693330591428L;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     */
    public SendInvitationAction(InvitationViewController parent) {
        super(parent);
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public boolean isEnabled() {
        return getParent().isValidate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute() throws Exception {
        return getParent().sendInvitation();
    }
}
