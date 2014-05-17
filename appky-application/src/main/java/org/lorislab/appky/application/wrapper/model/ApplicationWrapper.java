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
package org.lorislab.appky.application.wrapper.model;

import java.util.Locale;
import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Description;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.wrapper.util.DescriptionUtil;
import org.lorislab.jel.ejb.wrapper.AbstractWrapper;

/**
 * The application wrapper model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationWrapper extends AbstractWrapper<Application> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -6419455558011310408L;
    /**
     * The application title.
     */
    private Description title;
    /**
     * The application description.
     */
    private Description description;
    /**
     * The current application platform.
     */
    private Platform platform;

    /**
     * The default constructor.
     *
     * @param application the application.
     * @param locale the user locale.
     */
    public ApplicationWrapper(Application application, Locale locale) {
        setModel(application);
        title = DescriptionUtil.findDescription(application.getTitles(), locale);
        description = DescriptionUtil.findDescription(application.getDescriptions(), locale);
        platform = application.getPlatforms().get(0);
    }

    /**
     * Gets the current platform.
     *
     * @return the current platform.
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Gets the application description.
     *
     * @return the application description.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Gets the application title.
     *
     * @return the application title.
     */
    public Description getTitle() {
        return title;
    }

    /**
     * Gets the application GUID.
     *
     * @return the application GUID.
     */
    @Override
    public String getGuid() {
        return model.getGuid();
    }
}
