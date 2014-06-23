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
package org.lorislab.appky.application.wrapper.model;

import java.util.Locale;
import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Description;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.application.wrapper.model.enums.UserProcessAction;
import org.lorislab.appky.application.wrapper.util.DescriptionUtil;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.jel.ejb.wrapper.AbstractWrapper;

/**
 * The platform wrapper model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class PlatformWrapper extends AbstractWrapper<Version> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 5527705721312428139L;
    /**
     * The user GUID.
     */
    private String userGuid;
    /**
     * The platform.
     */
    private Platform platform;
    /**
     * The user application.
     */
    private UserApplication userApplication;
    /**
     * The user version.
     */
    private Version userVersion;
    /**
     * The user process action.
     */
    private UserProcessAction processAction;
    private Description platformDescription;
    private Description versionDescription;
    private UserProfile modificationUser;

    /**
     * The default constructor.
     *
     * @param userGuid the user GUID.
     * @param modificationUser the modification user.
     * @param platform the platform.
     * @param version the version.
     * @param userVersion the user version.
     * @param userApplication the user application.
     */
    public PlatformWrapper(String userGuid, UserProfile modificationUser, Platform platform, Version version, Version userVersion, UserApplication userApplication, Locale locale) {
        setModel(version);
        if (version != null) {
            this.versionDescription = DescriptionUtil.findDescription(version.getDescriptions(), locale);
        }

        this.modificationUser = modificationUser;
        this.userGuid = userGuid;

        this.platform = platform;
        if (platform != null) {
            this.platformDescription = DescriptionUtil.findDescription(platform.getDescriptions(), locale);
        }

        this.userVersion = userVersion;
        this.userApplication = userApplication;



        // update process user action
        updateStatus();
    }

    private void updateStatus() {
        if (!isModelEmpty()) {
            if (this.userApplication != null) {
                switch (this.userApplication.getStatus()) {
                    case INSTALLED:
                        if (getModel().equals(userVersion)) {
                            processAction = UserProcessAction.REINSTALL;
                        } else {
                            processAction = UserProcessAction.UPDATE;
                        }
                        break;
                    case REMOVED:
                        processAction = UserProcessAction.INSTALL;
                        break;
                }
            } else {
                processAction = UserProcessAction.INSTALL;
            }
        } else {
            processAction = null;
        }
    }

    public void setUserApplication(UserApplication userApplication) {
        this.userApplication = userApplication;
        updateStatus();
    }

    public UserProfile getModificationUser() {
        return modificationUser;
    }

    public Description getPlatformDescription() {
        return platformDescription;
    }

    public Description getVersionDescription() {
        return versionDescription;
    }

    /**
     * Gets the user GUID.
     *
     * @return the user GUID.
     */
    public String getUserGuid() {
        return userGuid;
    }

    /**
     * Gets the application.
     *
     * @return the application.
     */
    public Application getApplication() {
        if (platform != null) {
            return platform.getApplication();
        }
        return null;
    }

    /**
     * Gets the platform.
     *
     * @return the platform.
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Gets the user version.
     *
     * @return the user version.
     */
    public Version getUserVersion() {
        return userVersion;
    }

    /**
     * Gets the user application.
     *
     * @return the user application.
     */
    public UserApplication getUserApplication() {
        return userApplication;
    }

    /**
     * Gets the process action.
     *
     * @return the process action.
     */
    public UserProcessAction getProcessAction() {
        return processAction;
    }

    /**
     * Gets the model GUID.
     *
     * @return the model GUID.
     */
    @Override
    public String getGuid() {
        if (isModelEmpty()) {
            return null;
        }
        return model.getGuid();
    }
}
