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
package org.lorislab.appky.web.admin.role.view;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.appky.application.criteria.RoleSearchCriteria;
import org.lorislab.appky.application.ejb.RoleServiceLocal;
import org.lorislab.appky.application.model.Role;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.primefaces.model.DualListModel;

/**
 * The role converter view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("roleConverterVC")
@SessionScoped
public class RoleConverterViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2149866607538004756L;
    /**
     * The role service.
     */
    @EJB
    private RoleServiceLocal service;
    /**
     * The list of roles.
     */
    private List<Role> roles;
    /**
     * The temporary map of roles.
     */
    private Map<String, Role> tmp;

    /**
     * The default constructor.
     */
    public RoleConverterViewController() {
        roles = new ArrayList<>();
        tmp = new HashMap<>();
    }

    /**
     * Gets the role by GUID.
     *
     * @param guid the GUID.
     * @return the role corresponding to the GUID.
     */
    public Role getRole(String guid) {
        return tmp.get(guid);
    }

    /**
     * Gets the list of roles.
     *
     * @return the list of roles.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Creates the role of dual list.
     *
     * @param target the list of roles.
     * @param notEditable not editable flag.
     * @return the dual list of roles.
     * @throws Exception if the method fails.
     */
    public DualListModel createRoleDualListModel(List<Role> target, boolean notEditable) throws Exception {
        DualListModel result = new DualListModel();
        List<Role> source = refresh(target, notEditable);
        result.setSource(source);
        result.setTarget(target);
        return result;
    }

    /**
     * Refresh the list of roles.
     *
     * @param target the target list.
     * @param notEditable not editable flag.
     * @return the list of roles.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public List<Role> refresh(List<Role> target, boolean notEditable) throws Exception {
        roles = new ArrayList<>();
        tmp = new HashMap<>();
        RoleSearchCriteria criteria = new RoleSearchCriteria();
        criteria.setDeleted(false);
        if (!notEditable) {
            criteria.setEditable(true);
        }
        roles = service.findRolesByCriteria(criteria);
        for (Role role : roles) {
            tmp.put(role.getGuid(), role);
        }
        roles.removeAll(target);
        return roles;
    }
}
