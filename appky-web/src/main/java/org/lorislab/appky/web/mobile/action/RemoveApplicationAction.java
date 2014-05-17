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
package org.lorislab.appky.web.mobile.action;

import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.enums.UserApplicationStatus;
import org.lorislab.appky.application.wrapper.model.PlatformWrapper;
import org.lorislab.appky.web.mobile.view.MobilePlatformViewController;
import org.lorislab.jel.jsf.view.AbstractViewControllerAction;

/**
 * The remove application action.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class RemoveApplicationAction extends AbstractViewControllerAction<MobilePlatformViewController> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 1507457515731283018L;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     */
    public RemoveApplicationAction(MobilePlatformViewController parent) {
        super(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAvailable() {
        boolean result = false;
        PlatformWrapper wrapper = getParent().getWrapper();
        if (wrapper != null) {
            UserApplication userApp = wrapper.getUserApplication();
            if (userApp != null) {
                if (UserApplicationStatus.INSTALLED.equals(userApp.getStatus())) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute() throws Exception {
        getParent().removeApplication();
        return null;
    }
}
