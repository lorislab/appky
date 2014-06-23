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
package org.lorislab.appky.web.admin.role.view;


import org.lorislab.appky.web.admin.role.action.RoleSearchAction;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.appky.application.criteria.RoleSearchCriteria;
import org.lorislab.appky.application.ejb.RoleService;
import org.lorislab.appky.application.model.Role;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.view.AbstractTableSearchViewController;

/**
 * The role table view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("roleTableVC")
@SessionScoped
public class RoleTableViewController extends AbstractTableSearchViewController<Role, RoleSearchCriteria> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2149866607538004756L;
    /**
     * The role search action.
     */
    private RoleSearchAction searchAction;
    /**
     * The role service.
     */
    @EJB
    private RoleService service;

    /**
     * The default constructor.
     */
    public RoleTableViewController() {
        searchAction = new RoleSearchAction(this);
        RoleSearchCriteria tmp = new RoleSearchCriteria();
        tmp.setDeleted(false);
        setCriteria(tmp);
    }

    /**
     * Gets the role search action.
     *
     * @return the role search action.
     */
    public RoleSearchAction getSearchAction() {
        return searchAction;
    }

    /**
     * Search the list of role.
     *
     * @return the list of role.
     * @throws Exception if the method fails.
     */
    @Override
    protected List<Role> doSearch() throws Exception {
        List<Role> result = service.findRolesByCriteria(getCriteria());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @FacesServiceMethod
    public void search() throws Exception {
        super.search();
    }
}
