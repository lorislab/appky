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
package org.lorislab.appky.application.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The document.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "ST_DOCUMENT")
public class Document extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 4301920980430367559L;
    /**
     * The document name.
     */
    @Column(name = "C_NAME")
    private String name;
    /**
     * The document content.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "C_DOCUMENT_GUID")
    private DocumentContent content;
    /**
     * The document type.
     */
    @Column(name = "C_CONTENTTYPE")
    private String contentType;
    /**
     * The content size.
     */
    @Column(name = "C_CONTENTSIZE")
    private int contentSize;

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the document content.
     *
     * @return the content.
     */
    public DocumentContent getContent() {
        return content;
    }

    /**
     * Sets the document content.
     *
     * @param content the content to set.
     */
    public void setContent(DocumentContent content) {
        this.content = content;
    }

    /**
     * Gets the document type.
     *
     * @return the document type.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the content type.
     *
     * @param contentType the type to set.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Sets the content size.
     *
     * @param contentSize the content size.
     */
    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }

    /**
     * Gets the content size.
     *
     * @return the content size.
     */
    public int getContentSize() {
        return contentSize;
    }

    /**
     * Returns
     * <code>true</code> if the content is empty.
     *
     * @return <code>true</code> if the content is empty.
     */
    public boolean isNoData() {
        return content == null || content.getBytes() == null || content.getBytes().length == 0;
    }
}
