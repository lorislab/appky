/*
 * Copyright 2011 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.web;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * The user agent controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@ManagedBean(name = "userAgent")
@SessionScoped
public class UserAgentController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6646046911858468496L;
    /**
     * The user agent key.
     */
    private static final String KEY = "user-agent";
    /**
     * The user agent info.
     */
    private String userAgentStr;
    /**
     * The iPhone device.
     */
    public static final String DEVICE_IPHONE = "iphone";
    /**
     * The iPad device.
     */
    public static final String DEVICE_IPA = "ipad";
    /**
     * The web kit device.
     */
    public static final String ENGINE_WEB_KIT = "webkit";
    /**
     * The android device.
     */
    public static final String DEVICE_ANDROID = "android";

    /**
     * The ARM device.
     */
    public static final String DEVICE_ARM = "arm";
    
    /**
     * Post construct method.
     */
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        userAgentStr = request.getHeader(KEY).toLowerCase();
    }

    /**
     * Returns
     * <code>true</code> if the device is android device.
     *
     * @return <code>true</code> if the device is android device.
     */
    public boolean isAndroidMobile() {
        return (userAgentStr.indexOf(DEVICE_ANDROID) != -1);
    }

    /**
     * Returns
     * <code>true</code> if the device is iOS device.
     *
     * @return <code>true</code> if the device is iOs device.
     */
    public boolean isIOSMobile() {
        boolean result = false;
        if (userAgentStr.indexOf(ENGINE_WEB_KIT) != -1) {
            result = (userAgentStr.indexOf(DEVICE_IPHONE) != -1 || userAgentStr.indexOf(DEVICE_IPA) != -1);
        }
        return result;
    }

    /**
     * Returns
     * <code>true</code> if the device is mobile device.
     *
     * @return <code>true</code> if the device is mobile device.
     */
    public boolean isMobile() {
        init();
        return isIOSMobile() || isAndroidMobile() || isWindows8();
    }
    
    /**
     * Returns
     * <code>true</code> if the device is ARM device.
     *
     * @return <code>true</code> if the device is ARM device.
     */
    public boolean isWindows8() {
        return userAgentStr.indexOf(DEVICE_ARM) != -1;
    }
}