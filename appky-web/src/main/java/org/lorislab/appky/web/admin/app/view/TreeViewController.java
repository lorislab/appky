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
package org.lorislab.appky.web.admin.app.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.appky.application.criteria.ApplicationSearchCriteria;
import org.lorislab.appky.application.criteria.GroupSearchCriteria;
import org.lorislab.appky.application.criteria.PlatformSearchCriteria;
import org.lorislab.appky.application.ejb.ApplicationService;
import org.lorislab.appky.application.ejb.GroupService;
import org.lorislab.appky.application.ejb.PlatformService;
import org.lorislab.appky.application.factory.ApplicationObjectFactory;
import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Group;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.web.admin.app.action.TreeSearchAction;
import org.lorislab.appky.web.admin.app.model.ParentTreeNode;
import org.lorislab.jel.jpa.model.Persistent;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 * The tree view controller.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("treeVC")
@SessionScoped
public class TreeViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 5652972953302847185L;
    /**
     * The root type.
     */
    private static final String TYPE_ROOT = "root";
    /**
     * The root node.
     */
    private TreeNode root;
    /**
     * The data cache.
     */
    private Map<String, DefaultTreeNode> data;
    /**
     * The search action.
     */
    private TreeSearchAction searchAction;
    /**
     * The group service.
     */
    @EJB
    private GroupService service;
    /**
     * The application service.
     */
    @EJB
    private ApplicationService appService;
    /**
     * The platform service.
     */
    @EJB
    private PlatformService platformService;
    /**
     * The selected node.
     */
    private TreeNode selectedNode;
    /**
     * The application view controller.
     */
    @Inject
    private ApplicationViewController appVC;
    /**
     * The group view controller.
     */
    @Inject
    private GroupViewController groupVC;
    /**
     * The platform view controller.
     */
    @Inject
    private PlatformViewController platformVC;
    /**
     * The version view controller.
     */
    @Inject
    private VersionViewController versionVC;

    /**
     * The default constructor.
     */
    public TreeViewController() {
        createRootNode();
        searchAction = new TreeSearchAction(this);
    }

    /**
     * Gets the search action.
     *
     * @return the search action.
     */
    public TreeSearchAction getSearchAction() {
        return searchAction;
    }

    /**
     * Creates the root node.
     */
    private void createRootNode() {
        root = new DefaultTreeNode(TYPE_ROOT, null);
        data = new HashMap<>();
        selectedNode = null;
    }

    /**
     * Creates new node.
     *
     * @param model the model.
     * @param parent the parent node.
     * @param leaf the leaf flag.
     * @return the new created node.
     */
    private TreeNode createNode(Persistent model, TreeNode parent, boolean leaf) {
        return createNode(model.getClass().getSimpleName(), model, parent, leaf);
    }

    /**
     * Creates the node.
     *
     * @param type the type.
     * @param model the model.
     * @param parent the parent node.
     * @param leaf the leaf flag.
     * @return the new created node.
     */
    private TreeNode createNode(String type, Persistent model, TreeNode parent, boolean leaf) {
        ParentTreeNode result = new ParentTreeNode(type, model, parent, leaf);
        data.put(model.getGuid(), result);
        return result;
    }

    /**
     * Refresh the tree of groups and applications.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void search() throws Exception {
        createRootNode();

        GroupSearchCriteria criteria = new GroupSearchCriteria();
        criteria.setDeleted(false);
        List<Group> result = service.findGroupsBySearchCriteria(criteria);
        if (result != null) {
            for (Group item : result) {
                createNode(item, root, false);
            }
        }
    }

    /**
     * Gets the root node.
     *
     * @return the root node.
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Gets the selected node.
     *
     * @return the selected node.
     */
    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    /**
     * The on node expand event method.
     *
     * @param event on node expand event
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void onNodeExpand(NodeExpandEvent event) throws Exception {

        DefaultTreeNode node = (DefaultTreeNode) event.getTreeNode();
        Persistent model = (Persistent) node.getData();
        if (model instanceof Group) {
            node.setChildren(new ArrayList<TreeNode>());
            Group group = (Group) node.getData();
            ApplicationSearchCriteria criteria = new ApplicationSearchCriteria();
            criteria.setGroupGuid(group.getGuid());
            criteria.setDeleted(false);
            List<Application> apps = appService.findApplicationsBySearchCriteria(criteria);
            if (apps != null) {
                for (Application app : apps) {
                    createNode(app, node, false);
                }
            }
        } else if (model instanceof Application) {
            node.setChildren(new ArrayList<TreeNode>());
            Application application = (Application) node.getData();
            PlatformSearchCriteria platformCriteria = new PlatformSearchCriteria();
            platformCriteria.setApplicationGuid(application.getGuid());

            List<Platform> platforms = platformService.findsPlatformBySearchCriteria(platformCriteria);
            if (platforms != null) {
                for (Platform platform : platforms) {
                    TreeNode platformNode = createNode(platform.getType().name(), platform, node, false);
                    Version version = ApplicationObjectFactory.createVersion(platform);
                    createNode(version, platformNode, true);
                }
            }
        }

    }
    
    /**
     * Sets the selected node.
     *
     * @param selectedNode the new selected node.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod(contextLogger = false)
    public void setSelectedNode(TreeNode selectedNode) throws Exception {
        this.selectedNode = selectedNode;
        boolean noChange = true;

        if (selectedNode != null) {
            if (this.selectedNode != null) {
                Persistent newObject = (Persistent) selectedNode.getData();
                Persistent oldObject = (Persistent) this.selectedNode.getData();
                noChange = !oldObject.equals(newObject);
            }
        }
        this.selectedNode = selectedNode;
        if (!noChange && this.selectedNode != null && this.selectedNode.getData() != null) {
            Persistent model = (Persistent) this.selectedNode.getData();
            if (model instanceof Group) {
                groupVC.open(model.getGuid());
            } else if (model instanceof Application) {
                appVC.open(model.getGuid());
            } else if (model instanceof Platform) {
                platformVC.open(model.getGuid());
            } else {
                Version version = (Version) model;
                versionVC.open(version.getPlatform());
            }
        }
    }

    /**
     * Adds the model to the tree.
     *
     * @param model the model.
     * @param parent the parent node.
     */
    public void addModel(Persistent model, Persistent parent) {
        TreeNode p = null;
        if (parent != null) {
            p = data.get(parent.getGuid());
        }
        if (p == null) {
            p = root;
        }
        createNode(model, p, false);
    }

    /**
     * Deletes the model from tree.
     *
     * @param model the model.
     */
    public void deleteModel(Persistent model) {
        if (model != null) {
            DefaultTreeNode node = data.get(model.getGuid());
            TreeNode parent = node.getParent();
            if (parent != null) {
                parent.getChildren().remove(node);
            }
            data.remove(model.getGuid());
            if (node != null) {
                node.setData(model);
            }
            selectedNode = null;
        }
    }

    /**
     * Updates the model in the tree.
     *
     * @param model the model.
     */
    public void updateModel(Persistent model) {
        if (model != null) {
            DefaultTreeNode node = data.get(model.getGuid());
            if (node != null) {
                node.setData(model);
            }
        }
    }

    /**
     * Returns
     * <code>true</code> if the selected node is group.
     *
     * @return <code>true</code> if the selected node is group.
     */
    public boolean isGroupNode() {
        boolean result = false;
        if (selectedNode != null) {
            result = (selectedNode.getData() instanceof Group);
        }
        return result;
    }

    /**
     * Returns
     * <code>true</code> if the selected node is application.
     *
     * @return <code>true</code> if the selected node is application.
     */
    public boolean isApplicationNode() {
        boolean result = false;
        if (selectedNode != null) {
            result = (selectedNode.getData() instanceof Application);
        }
        return result;
    }

    /**
     * Returns
     * <code>true</code> if the selected node is platform.
     *
     * @return <code>true</code> if the selected node is platform.
     */
    public boolean isPlatformNode() {
        boolean result = false;
        if (selectedNode != null) {
            result = (selectedNode.getData() instanceof Platform);
        }
        return result;
    }

    /**
     * Returns
     * <code>true</code> if the selected node is version.
     *
     * @return <code>true</code> if the selected node is version.
     */
    public boolean isVersionNode() {
        boolean result = false;
        if (selectedNode != null) {
            result = (selectedNode.getData() instanceof Version);
        }
        return result;
    }
}
