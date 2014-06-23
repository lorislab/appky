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
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.appky.application.factory.ApplicationObjectFactory;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.model.enums.PlatformType;
import org.lorislab.appky.web.admin.app.action.PackageAcceptAction;
import org.lorislab.appky.web.admin.app.action.PackageCancelAction;
import org.lorislab.appky.web.admin.app.action.PackageUploadAction;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * The package view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@SessionScoped
@Named("packageVC")
public class PackageViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1626279054599540757L;
    /**
     * The map of allow files.
     */
    private static final Map<PlatformType, String> ALLOW_FILES = new HashMap<>();

    /**
     * The static block.
     */
    static {
        ALLOW_FILES.put(PlatformType.IOS, "/(\\.|\\/)(ipa)$/");
        ALLOW_FILES.put(PlatformType.ANDROID, "/(\\.|\\/)(apk)$/");
        ALLOW_FILES.put(PlatformType.WINDOWS8, "/(\\.|\\/)(zip)$/");
    }
    /**
     * The document.
     */
    private Document document;
    /**
     * The allow files.
     */
    private String allowFiles;
    /**
     * The upload action.
     */
    private PackageUploadAction uploadAction;
    /**
     * The accept action.
     */
    private PackageAcceptAction acceptAction;
    /**
     * The cancel action.
     */
    private PackageCancelAction cancelAction;

    /**
     * The default constructor.
     */
    public PackageViewController() {
        allowFiles = "/(\\.|\\/)(none)$/";
        uploadAction = new PackageUploadAction(this);
        acceptAction = new PackageAcceptAction(this);
        cancelAction = new PackageCancelAction(this);
    }

    /**
     * Gets the allow files.
     *
     * @return the allow files.
     */
    public String getAllowFiles() {
        return allowFiles;
    }

    /**
     * Gets the accept action.
     *
     * @return the accept action.
     */
    public PackageAcceptAction getAcceptAction() {
        return acceptAction;
    }

    /**
     * Gets the cancel action.
     *
     * @return the cancel action.
     */
    public PackageCancelAction getCancelAction() {
        return cancelAction;
    }

    /**
     * Gets the upload action.
     *
     * @return the upload action.
     */
    public PackageUploadAction getUploadAction() {
        return uploadAction;
    }

    /**
     * Gets the document.
     *
     * @return the document.
     */
    public Document getDocument() {
        return document;
    }

    /**
     * THe upload event method.
     *
     * @param event the upload event.
     */
    @FacesServiceMethod
    public void uploadEvent(FileUploadEvent event) {
        if (event != null && event.getFile() != null) {
            upload(event.getFile());
        }
    }

    /**
     * Upload file.
     *
     * @param file the upload file.
     */
    @FacesServiceMethod
    public void upload(UploadedFile file) {
        document.getContent().setBytes(file.getContents());
        document.setName(file.getFileName());
        document.setContentType(file.getContentType());
        document.setContentSize(document.getContent().getBytes().length);
    }

    /**
     * Creates new document.
     */
    public void create() {
        this.document = ApplicationObjectFactory.createDocument();
    }

    /**
     * Opens the document by the platform type.
     *
     * @param document the document.
     * @param type the platform type.
     */
    @FacesServiceMethod
    public void open(Document document, PlatformType type) {
        this.document = ApplicationObjectFactory.createDocument(document);
        allowFiles = ALLOW_FILES.get(type);
    }
}
