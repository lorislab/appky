/*
 * Copyright 2013 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.process.factory;


import org.lorislab.appky.process.model.ApplicationPackage;

import java.util.HashMap;
import java.util.Map;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.enums.PlatformType;
import org.lorislab.appky.application.wrapper.model.enums.UserProcessAction;

/**
 * The application package factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ApplicationPackageFactory {

    /**
     * The map of content type for the platform type.
     */
    private static final Map<PlatformType, String> CONTENT_TYPE = new HashMap<>();

    /**
     * The static block.
     */
    static {
        CONTENT_TYPE.put(PlatformType.IOS, "text/xml");
        CONTENT_TYPE.put(PlatformType.ANDROID, "application/octet-stream");
        CONTENT_TYPE.put(PlatformType.WINDOWS8, "application/zip");
    }

    /**
     * The default constructor.
     */
    private ApplicationPackageFactory() {
        // empty constructor
    }

    /**
     * Creates the application package.
     *
     * @param userApplication the user process application.
     * @param action the action.
     * @param content the content.
     * @param platformType the platform type.
     * @return the application platform.
     */
    public static ApplicationPackage createApplicationPackage(UserApplication userApplication, UserProcessAction action, Object content, PlatformType platformType) {
        ApplicationPackage result = new ApplicationPackage();
        result.setAction(action);
        result.setContent(content);
        result.setUserApplication(userApplication);
        result.setPlatformType(platformType);
        result.setContent(CONTENT_TYPE.get(platformType));
        return result;
    }
}
