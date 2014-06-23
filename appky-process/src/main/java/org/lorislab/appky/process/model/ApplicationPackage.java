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
package org.lorislab.appky.process.model;

import java.io.Serializable;
import org.lorislab.appky.application.model.UserApplication;
import org.lorislab.appky.application.model.enums.PlatformType;
import org.lorislab.appky.application.wrapper.model.enums.UserProcessAction;

/**
 * The application package.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationPackage implements Serializable {
    
    private static final long serialVersionUID = 556534088569995517L;
    
    private Object content;
    
    private String mimeType;
       
    private UserProcessAction action;
    
    private UserApplication userApplication;

    private PlatformType platformType;

    public UserApplication getUserApplication() {
        return userApplication;
    }

    public void setUserApplication(UserApplication userApplication) {
        this.userApplication = userApplication;
    }
    
    public PlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
    }
        
    public UserProcessAction getAction() {
        return action;
    }

    public void setAction(UserProcessAction action) {
        this.action = action;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }        
}
