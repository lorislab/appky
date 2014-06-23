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
package org.lorislab.appky.web.admin.user.view;


import org.lorislab.appky.web.admin.user.action.UserSearchAction;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.appky.application.criteria.UserProfileSearchCriteria;
import org.lorislab.appky.application.ejb.UserProfileService;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.view.AbstractTableSearchViewController;

/**
 * The user table view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("userTableVC")
@SessionScoped
public class UserTableViewController extends AbstractTableSearchViewController<UserProfile, UserProfileSearchCriteria> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 4042394598141020708L;
    /**
     * The user profile service.
     */
    @EJB
    private UserProfileService service;
    /**
     * The user search action.
     */
    private UserSearchAction searchAction;

    /**
     * The default constructor.
     */
    public UserTableViewController() {
        searchAction = new UserSearchAction(this);
        UserProfileSearchCriteria criteria = new UserProfileSearchCriteria();
        criteria.setDeleted(false);
        setCriteria(criteria);
    }

    /**
     * Gets the user search action.
     *
     * @return the user search action.
     */
    public UserSearchAction getSearchAction() {
        return searchAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @FacesServiceMethod
    public void search() throws Exception {
        super.search();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<UserProfile> doSearch() throws Exception {
        return service.findUserProfilesByCriteria(getCriteria());
    }
}
