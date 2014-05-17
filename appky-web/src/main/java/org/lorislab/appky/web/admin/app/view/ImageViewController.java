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


import org.lorislab.appky.util.ImageUtil;
import org.lorislab.appky.web.admin.app.action.ImageAcceptAction;
import org.lorislab.appky.web.admin.app.action.ImageCancelAction;
import org.lorislab.appky.web.admin.app.action.ImageUploadAction;
import org.lorislab.appky.web.admin.app.model.DocumentImageType;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;
import org.lorislab.appky.application.factory.ApplicationObjectFactory;
import org.lorislab.appky.application.model.Document;
import org.lorislab.jel.jsf.interceptor.annotations.FacesServiceMethod;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * The image view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("imageVC")
@SessionScoped
public class ImageViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 8133665810595256841L;
    /**
     * The image format.
     */
    private static final String IMAGE_FORMAT = "png";
    /**
     * The document.
     */
    private Document document;
    /**
     * The document image type.
     */
    private DocumentImageType type;
    /**
     * The width.
     */
    private long width = -1;
    /**
     * The height.
     */
    private long height = -1;
    /**
     * The object GUID.
     */
    private Object guid;
    /**
     * The upload action.
     */
    private ImageUploadAction uploadAction;
    /**
     * The accept action.
     */
    private ImageAcceptAction acceptAction;
    /**
     * The cancel action.
     */
    private ImageCancelAction cancelAction;

    /**
     * The default constructor.
     */
    public ImageViewController() {
        uploadAction = new ImageUploadAction(this);
        acceptAction = new ImageAcceptAction(this);
        cancelAction = new ImageCancelAction(this);
    }

    /**
     * Gets the accept action.
     *
     * @return the accept action.
     */
    public ImageAcceptAction getAcceptAction() {
        return acceptAction;
    }

    /**
     * Gets the cancel action.
     *
     * @return the cancel action.
     */
    public ImageCancelAction getCancelAction() {
        return cancelAction;
    }

    /**
     * Gets the upload action.
     *
     * @return the upload action.
     */
    public ImageUploadAction getUploadAction() {
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
     * Gets the height.
     *
     * @return the height.
     */
    public long getHeight() {
        return height;
    }

    /**
     * Gets the width.
     *
     * @return the width.
     */
    public long getWidth() {
        return width;
    }

    /**
     * Gets the GUID.
     *
     * @return the GUID.
     */
    public Object getGuid() {
        return guid;
    }

    /**
     * Gets the document image type.
     *
     * @return the document image type.
     */
    public DocumentImageType getType() {
        return type;
    }

    /**
     * Upload event method.
     *
     * @param event the upload event.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void uploadEvent(FileUploadEvent event) throws Exception {
        if (event != null && event.getFile() != null) {
            upload(event.getFile());
        }
    }

    /**
     * Upload file.
     *
     * @param file the file.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void upload(UploadedFile file) throws Exception {

        byte[] fileContents = null;

        if (width != -1 && height != -1) {
            BufferedImage imageContent = ImageIO.read(new ByteArrayInputStream(file.getContents()));
            imageContent = ImageUtil.resizeImage(imageContent, (int) width, (int) height);

            ByteArrayOutputStream stream = new ByteArrayOutputStream(50);
            ImageIO.write(imageContent, IMAGE_FORMAT, stream);
            fileContents = stream.toByteArray();
        } else {
            fileContents = file.getContents();
        }

        document.getContent().setBytes(fileContents);
        document.setName(file.getFileName());
        document.setContentType(file.getContentType());
        document.setContentSize(document.getContent().getBytes().length);


    }

    /**
     * Update size and type.
     *
     * @param type the image type.
     * @param width the width.
     * @param height the height.
     */
    private void update(DocumentImageType type, long width, long height) {
        this.type = type;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates the image.
     *
     * @param type the image type.
     * @param width the width.
     * @param height the height.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void create(DocumentImageType type, long width, long height) throws Exception {
        update(type, width, height);
        this.document = ApplicationObjectFactory.createDocument();
        this.guid = document.getGuid();
    }

    /**
     * Opens the document image.
     *
     * @param type the image type.
     * @param document the document.
     * @param width the width.
     * @param height the height.
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void open(DocumentImageType type, Document document, long width, long height) throws Exception {
        update(type, width, height);
        this.guid = document.getGuid();
        this.document = ApplicationObjectFactory.createDocument(document);
    }
}
