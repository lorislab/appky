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
package org.lorislab.appky.web.admin.role.converter;


import org.lorislab.appky.web.admin.role.view.RoleConverterViewController;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.model.Role;

/**
 * The role converter.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("roleConverter")
public class RoleConverter implements Converter, Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 2223569437900536590L;
    /**
     * The role converter view controller.
     */
    @Inject
    private RoleConverterViewController viewController;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Role role = viewController.getRole(value);
        return role;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Role) value).getGuid();
    }
}
