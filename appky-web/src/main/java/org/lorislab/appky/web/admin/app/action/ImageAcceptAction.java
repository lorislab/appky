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
package org.lorislab.appky.web.admin.app.action;

import org.lorislab.appky.web.admin.app.view.ImageViewController;
import org.lorislab.appky.application.model.Document;
import org.lorislab.jel.jsf.view.AbstractViewControllerAction;

/**
 * The image accept action.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>s
 */
public class ImageAcceptAction extends AbstractViewControllerAction<ImageViewController> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5538875040013494773L;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     */
    public ImageAcceptAction(ImageViewController parent) {
        super(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        boolean result = false;
        Document doc = getParent().getDocument();
        if (doc != null && doc.getContent() != null) {
            if (doc.getContent().getBytes() != null && doc.getContent().getBytes().length > 0) {
                result = true;
            }
        }
        return result;
    }
}
