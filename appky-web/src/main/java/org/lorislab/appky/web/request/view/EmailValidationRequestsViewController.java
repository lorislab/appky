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
package org.lorislab.appky.web.request.view;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.lorislab.appky.process.registration.ejb.RegistrationService;
import org.lorislab.appky.process.registration.model.enums.EmailValidationAction;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;

/**
 * The email validation requests view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named(value = "emailValidationVC")
@RequestScoped
public class EmailValidationRequestsViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2939122028597295649L;
    /**
     * The GUID.
     */
    private String guid;
    /**
     * The action.
     */
    private String action;
    /**
     * The result.
     */
    private boolean result;
    /**
     * The registration service.
     */
    @EJB
    private RegistrationService registrationService;

    /**
     * The validation email action.
     */
    @FacesServiceMethod
    public void validationEmailAction() throws Exception {
        EmailValidationAction valAction = EmailValidationAction.valueOf(getAction());
        registrationService.validateEmailByRequest(getGuid(), valAction);
        result = true;
    }

    /**
     * Gets the GUID.
     *
     * @return the GUID.
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the GUID.
     *
     * @param guid the GUID to set.
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * Gets the action.
     *
     * @return the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action.
     *
     * @param action the action to set.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Gets the result flag.
     *
     * @return the result flag.
     */
    public boolean isResult() {
        return result;
    }

    /**
     * Sets the result flag.
     *
     * @param result the result to set.
     */
    public void setResult(boolean result) {
        this.result = result;
    }
}
