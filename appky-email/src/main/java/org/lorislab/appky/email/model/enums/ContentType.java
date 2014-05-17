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
package org.lorislab.appky.email.model.enums;

/**
 * The content type enumeration.
 *
 * @see http://en.wikipedia.org/wiki/Internet_media_type
 *
 * @author Andrej_Petras
 *
 */
public enum ContentType {

    /**
     * The octet stream.
     */
    OCTETSTREAM("application/octet-stream", ""),
    /**
     * The PDF type.
     */
    PDF("application/pdf", ".pdf"),
    /**
     * The ZIP type.
     */
    ZIP("application/zip", ".zip");
    /**
     * The content type.
     */
    private String type;
    /**
     * The file extension.
     */
    private String extension;

    /**
     * The default constructor.
     *
     * @param type the content type.
     */
    private ContentType(String type, String extension) {
        this.type = type;
        this.extension = extension;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the extension.
     *
     * @return the extension.
     */
    public String getExtension() {
        return extension;
    }
}
