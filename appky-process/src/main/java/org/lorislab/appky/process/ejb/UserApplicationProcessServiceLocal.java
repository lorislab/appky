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
package org.lorislab.appky.process.ejb;


import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.application.wrapper.model.PlatformWrapper;
import org.lorislab.appky.process.model.ApplicationPackage;
import org.lorislab.jel.ejb.exception.ServiceException;


/**
 * The user application process service local interface.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface UserApplicationProcessServiceLocal {

    /**
     * Process application.
     *
     * @param wrapper the platform wrapper.
     * @return the result object (package, p-list, ... )
     * @throws ServiceException if the method fails.
     */
    ApplicationPackage processApplication(PlatformWrapper wrapper) throws ServiceException;

    /**
     * Un-install the application.
     *
     * @param wrapper the platform wrapper.
     * @return the user application model.
     * @throws ServiceException if the method fails.
     */
    UserApplication uninstallApplication(PlatformWrapper wrapper) throws ServiceException;

    /**
     * Release the version.
     *
     * @param version the version.
     * @return the release version.
     * @throws ServiceException if the method fails.
     */
    Version releaseVersion(Version version) throws ServiceException;

    /**
     * Sends the un-release version notification.
     *
     * @param version the version.
     * @throws ServiceException if the method fails.
     */
    Version unreleaseVersion(Version version) throws ServiceException;

}
