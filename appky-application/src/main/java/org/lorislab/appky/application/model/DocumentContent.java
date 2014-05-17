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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The document content.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "ST_DOCUMENT_CONTENT")
public class DocumentContent extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -6002886906894984591L;
    /**
     * The real content of the document as byte array.
     */
    @Lob
    @Column(name = "C_BYTES")
    private byte[] bytes;

    /**
     * Gets the bytes.
     *
     * @return the bytes.
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Sets the bytes.
     *
     * @param bytes the bytes to set
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
