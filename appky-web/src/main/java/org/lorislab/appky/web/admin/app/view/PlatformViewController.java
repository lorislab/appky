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

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.appky.application.ejb.PlatformService;
import org.lorislab.appky.application.factory.ApplicationObjectFactory;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.model.ManifestIOS;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.process.config.ServerConfiguration;
import org.lorislab.appky.web.admin.app.action.PlatformSaveAction;
import org.lorislab.appky.web.admin.app.model.DocumentImageType;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.view.AbstractEntityViewController;

/**
 * The platform view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("platformVC")
@SessionScoped
public class PlatformViewController extends AbstractEntityViewController<Platform> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 5370847392516544224L;
    /**
     * The map of images.
     */
    private Map<Object, Document> images;
    /**
     * The description view controller.
     */
    private DescriptionViewController descriptionVC;
    /**
     * The save action.
     */
    private PlatformSaveAction saveAction;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationService configService;
    /**
     * The platform service.
     */
    @EJB
    private PlatformService platformService;

    /**
     * The default constructor.
     */
    public PlatformViewController() {
        saveAction = new PlatformSaveAction(this);
        descriptionVC = new DescriptionViewController();
    }

    /**
     * Gets the save action.
     *
     * @return the save action.
     */
    public PlatformSaveAction getSaveAction() {
        return saveAction;
    }

    /**
     * Opens the platform by GUID.
     *
     * @param guid the platform GUID.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void open(String guid) throws Exception {
        images = null;
        model = platformService.loadFullPlatformByGuid(guid);

        if (model != null) {
            ServerConfiguration config = configService.getConfiguration(ServerConfiguration.class);
            List<Locale> serverLangs = config.getServerLangs();
            Locale defaultLocale = config.getServerLang();
            descriptionVC.open(model.getDescriptions(), serverLangs, defaultLocale);

            List<Document> docImages = model.getImages();
            images = new HashMap<>();

            addToCache(model.getIcon());

            if (docImages != null && !docImages.isEmpty()) {
                for (Document document : model.getImages()) {
                    addToCache(document);
                }
            }

            if (model.getManifest() != null) {
                addToCache(model.getManifest().getLargeIcon());
                addToCache(model.getManifest().getSmallIcon());
            }

        }
    }

    /**
     * Saves the platform.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void save() throws Exception {
        model = platformService.savePlatform(model);
    }

    /**
     * Switch the document content of the two documents.
     *
     * @param index1 the index of the first document.
     * @param index2 the index of the second document.
     */
    private void switchDocumentContent(int index1, int index2) {
        List<Document> tmp = model.getImages();
        if (tmp != null && !tmp.isEmpty()) {
            int size = tmp.size();
            if (0 <= index1 && 0 <= index2 && index1 < size && index2 < size) {
                Document doc1 = tmp.get(index1);
                Document doc2 = tmp.get(index2);
                ApplicationObjectFactory.switchDocumentContent(doc1, doc2);
            }
        }
    }

    /**
     * Moves the image to the previous position.
     *
     * @param guid the image GUID.
     * @param index the index.
     */
    @FacesServiceMethod
    public void prevImage(String guid, String index) {
        int i = -1;
        try {
            i = Integer.valueOf(index);
        } catch (Exception ex) {
            // do nothing
        }
        switchDocumentContent(i, i - 1);
    }

    /**
     * Moves the image to the next position.
     *
     * @param guid the image GUID.
     * @param index the index.
     */
    @FacesServiceMethod
    public void nextImage(String guid, String index) {
        int i = -10;
        try {
            i = Integer.valueOf(index);
        } catch (Exception ex) {
            // do nothing
        }
        switchDocumentContent(i, i + 1);
    }

    /**
     * Updates the document image.
     *
     * @param type the document image type.
     * @param guid the GUID.
     * @param document the document.
     */
    @FacesServiceMethod
    public void updateImage(DocumentImageType type, Object guid, Document document) {
        switch (type) {
            case ICON:
                ApplicationObjectFactory.copyDocument(model.getIcon(), document);
                break;
            case IMAGE:
                Document data = images.get(guid);
                if (data != null) {
                    ApplicationObjectFactory.copyDocument(data, document);
                } else {
                    model.getImages().add(document);
                    addToCache(document);
                }
                break;
            case CUSTOM2:
                ApplicationObjectFactory.copyDocument(model.getManifest().getLargeIcon(), document);
                addToCache(document);
                break;
            case CUSTOM1:
                ApplicationObjectFactory.copyDocument(model.getManifest().getSmallIcon(), document);
                addToCache(document);
                break;
        }
    }

    /**
     * Deletes the document image.
     *
     * @param type the document image type.
     * @param guid the GUID.
     */
    @FacesServiceMethod
    public void deleteImage(DocumentImageType type, Object guid) {
        ManifestIOS manifest = model.getManifest();
        switch (type) {
            case ICON:
                remoteFromCache(model.getIcon());
                model.setIcon(ApplicationObjectFactory.createDocument());
                addToCache(model.getIcon());
                break;
            case IMAGE:
                Document data = images.get(guid);
                if (data != null) {
                    model.getImages().remove(data);
                    remoteFromCache(data);
                }
                break;
            case CUSTOM1:
                remoteFromCache(manifest.getLargeIcon());
                manifest.setLargeIcon(ApplicationObjectFactory.createDocument());
                addToCache(manifest.getLargeIcon());
                break;
            case CUSTOM2:
                remoteFromCache(manifest.getSmallIcon());
                manifest.setSmallIcon(ApplicationObjectFactory.createDocument());
                addToCache(manifest.getSmallIcon());
                break;
        }
    }

    /**
     * Gets the description view controller.
     *
     * @return the description view controller.
     */
    public DescriptionViewController getDescriptionVC() {
        return descriptionVC;
    }

    /**
     * Gets the image by GUID.
     *
     * @param guid the image GUID.
     * @return the image corresponding to the GUID.
     */
    public Document getImage(String guid) {
        return images.get(guid);
    }

    /**
     * Removes document from cache.
     *
     * @param document the document.
     */
    protected void remoteFromCache(Document document) {
        images.remove(document.getGuid());
    }

    /**
     * Adds the document to the cache.
     *
     * @param document the document.
     */
    protected void addToCache(Document document) {
        images.put(document.getGuid(), document);
    }
}
