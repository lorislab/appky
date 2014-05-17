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
package org.lorislab.appky.application.factory;

import org.lorislab.appky.application.model.Application;
import org.lorislab.appky.application.model.Description;
import org.lorislab.appky.application.model.Document;
import org.lorislab.appky.application.model.DocumentContent;
import org.lorislab.appky.application.model.Group;
import org.lorislab.appky.application.model.ManifestIOS;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.application.model.enums.PlatformType;
import java.util.Locale;

/**
 * The application object factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ApplicationObjectFactory {

    /**
     * The default constructor.
     */
    private ApplicationObjectFactory() {
        // empty constructor
    }

    /**
     * Creates the application.
     *
     * @param name the application name.
     * @param locale the application default locale.
     * @return the new created application.
     */
    public static Application createApplication(String name, Locale locale) {
        Application result = new Application();
        result.setName(name);
        result.setEnabled(false);
        result.setDeleted(false);
        result.getPlatforms().add(createPlatform(locale, PlatformType.ANDROID));
        result.getPlatforms().add(createPlatform(locale, PlatformType.IOS));
        result.getPlatforms().add(createPlatform(locale, PlatformType.WINDOWS8));

        Description title = createDescription(locale);
        result.getTitles().add(title);

        Description desc = createDescription(locale);
        result.getDescriptions().add(desc);

        return result;
    }

    /**
     * Creates the document.
     *
     * @return the document.
     */
    public static Document createDocument() {
        Document result = new Document();
        result.setContent(new DocumentContent());
        return result;
    }

    /**
     * Creates the copy of the document.
     *
     * @param document the document.
     * @return new created document as a copy of input document.
     */
    public static Document createDocument(Document document) {
        Document result = createDocument();
        result.setContentSize(document.getContentSize());
        result.setContentType(document.getContentType());
        result.setName(document.getName());
        result.getContent().setBytes(document.getContent().getBytes());
        return result;
    }

    /**
     * Switch the document content of the two documents.
     *
     * @param doc1 the first document.
     * @param doc2 the second document.
     */
    public static void switchDocumentContent(Document doc1, Document doc2) {
        if (doc1 != null && doc2 != null) {
            DocumentContent docContent = doc1.getContent();
            int contentSize = doc1.getContentSize();
            String contentType = doc1.getContentType();
            String name = doc1.getName();

            doc1.setContent(doc2.getContent());
            doc1.setContentSize(doc2.getContentSize());
            doc1.setContentType(doc2.getContentType());
            doc1.setName(doc2.getName());
            doc2.setContent(docContent);
            doc2.setContentSize(contentSize);
            doc2.setContentType(contentType);
            doc2.setName(name);
        }
    }

    /**
     * Creates the document.
     *
     * @param name the document name.
     * @param type the document type.
     * @param data the content.
     * @param size the content size.
     * @return the new created document.
     */
    public static Document createDocument(String name, String type, byte[] data, int size) {
        Document result = createDocument();
        result.setName(name);
        result.setContentSize(size);
        result.setContentType(type);
        DocumentContent content = new DocumentContent();
        content.setBytes(data);
        result.setContent(content);
        return result;
    }

    /**
     * Copy the document from
     * <code>from</code> to
     * <code>to</code>.
     *
     * @param to to document.
     * @param from from document.
     */
    public static void copyDocument(Document to, Document from) {
        if (to != null && from != null) {
            to.setContentSize(from.getContentSize());
            to.setContentType(from.getContentType());
            to.setName(from.getName());
            to.getContent().setBytes(from.getContent().getBytes());
        }
    }

    /**
     * Creates the group.
     *
     * @param name the group name.
     * @param locale the group default locale.
     * @return the new created group.
     */
    public static Group createGroup(String name, Locale locale) {
        Group result = new Group();
        result.setName(name);
        result.setEnabled(false);
        result.setDeleted(false);
        result.setIcon(createDocument());

        Description title = createDescription(locale);
        result.getTitles().add(title);

        Description desc = createDescription(locale);
        result.getDescriptions().add(desc);

        return result;
    }

    /**
     * Creates the description.
     *
     * @param locale the default locale.
     * @return the new create description.
     */
    public static Description createDescription(Locale locale) {
        Description result = new Description();
        result.setLocale(locale);
        return result;
    }

    /**
     * Creates the description.
     *
     * @param locale the default locale of the description.
     * @param value the description value.
     * @return the new created description.
     */
    public static Description createDescription(Locale locale, String value) {
        Description result = createDescription(locale);
        result.setValue(value);
        return result;
    }

    /**
     * Creates the application.
     *
     * @param group the group.
     * @param name the name.
     * @param locale the default locale.
     * @return the new created application.
     */
    public static Application create(Group group, String name, Locale locale) {
        Application result = new Application();
        result.setName(name);
        result.getPlatforms().add(createPlatform(locale, PlatformType.ANDROID));
        result.getPlatforms().add(createPlatform(locale, PlatformType.IOS));
        result.setGroup(group);

        Description title = createDescription(locale);
        result.getTitles().add(title);

        Description desc = createDescription(locale);
        result.getDescriptions().add(desc);

        return result;
    }

    /**
     * Creates the platform manifest for IOS.
     *
     * @return the platform manifest for IOS.
     */
    public static ManifestIOS createPlatformManifestIOS() {
        ManifestIOS result = new ManifestIOS();
        result.setIconShine(true);
        result.setLargeIconShine(true);
        result.setLargeIcon(createDocument());
        result.setSmallIcon(createDocument());
        return result;
    }

    /**
     * Creates the platform.
     *
     * @param locale the default platform locale.
     * @param type the platform type.
     * @return the new created platform.
     */
    public static Platform createPlatform(Locale locale, PlatformType type) {
        Platform result = new Platform();
        result.setIcon(createDocument());
        result.setType(type);
        result.setEnabled(true);
        result.setEnabled(false);
        if (PlatformType.IOS.equals(type)) {
            result.setManifest(createPlatformManifestIOS());
        }
        Description desc = createDescription(locale);
        result.getDescriptions().add(desc);
        return result;
    }

    /**
     * Creates the version.
     *
     * @param platform the platform.
     * @param locale the version default locale.
     * @return the new created version.
     */
    public static Version createVersion(Platform platform, Locale locale) {
        return createVersion(platform, null, locale);
    }

    /**
     * Creates the version.
     *
     * @param platform the platform.
     * @return the new created version.
     */
    public static Version createVersion(Platform platform) {
        Version result = new Version();
        result.setDeleted(false);
        result.setReleased(false);
        result.setPlatform(platform);
        return result;
    }

    /**
     * Creates the version.
     *
     * @param platform the platform.
     * @param name the name.
     * @param locale the version default locale.
     * @return the new created version.
     */
    public static Version createVersion(Platform platform, String name, Locale locale) {
        Version result = createVersion(platform);
        result.setName(name);
        result.setData(createDocument());
        Description desc = createDescription(locale);
        result.getDescriptions().add(desc);
        return result;
    }
}
