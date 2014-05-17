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
package org.lorislab.appky.web.admin.app.view;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.criteria.VersionSearchCriteria;
import org.lorislab.appky.application.ejb.VersionServiceLocal;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.web.admin.app.action.VersionListSearchAction;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.view.AbstractTableSearchViewController;

/**
 * The version list view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("versionsVC")
@SessionScoped
public class VersionsListViewController extends AbstractTableSearchViewController<Version, VersionSearchCriteria> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 3036964469248986723L;
    /**
     * The version service.
     */
    @EJB
    private VersionServiceLocal service;
    /**
     * The version view controller.
     */
    @Inject
    private VersionViewController versionVC;
    /**
     * The version list search action.
     */
    private VersionListSearchAction searchAction;

    /**
     * The default constructor.
     */
    public VersionsListViewController() {
        searchAction = new VersionListSearchAction(this);
        VersionSearchCriteria criteria = new VersionSearchCriteria();
        criteria.setDeleted(false);
        setCriteria(criteria);
    }

    /**
     * Gets the version list search action.
     *
     * @return the version list search action.
     */
    public VersionListSearchAction getSearchAction() {
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
    protected List<Version> doSearch() throws Exception {
        getCriteria().setPlatformGuid(versionVC.getPlatform().getGuid());
        return service.findVersionsBySearchCriteria(getCriteria());
    }
}
