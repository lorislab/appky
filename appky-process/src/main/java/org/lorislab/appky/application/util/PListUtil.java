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
package org.lorislab.appky.application.util;

import org.lorislab.appky.application.model.ManifestIOS;
import org.lorislab.appky.application.tmpresource.factory.TemporaryResourceFactory;
import org.lorislab.appky.application.tmpresource.model.TemporaryResource;

/**
 * The PLIST utility class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class PListUtil {

    /**
     * The default constructor.
     */
    private PListUtil() {
        // empty constructor
    }

    /**
     * Converts the model to the iOS PLIST format.
     *
     * @param serverConfig the server configuration.
     * @param packageGuid the package GUID.
     * @param manifest the IOS manifest.
     * @param tmpResource the temporary resource.
     * @return the string as a PLIST corresponding to the input modules.F
     */
    public static String convertToPList(String serverConfig, String packageGuid, ManifestIOS manifest, TemporaryResource tmpResource) {
        StringBuilder sb = new StringBuilder();
        if (manifest != null) {

            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            sb.append("<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">");
            sb.append("<plist version=\"1.0\"><dict><key>items</key><array>");

            // package
            if (packageGuid != null) {
                sb.append("<dict><key>kind</key><string>software-package</string><key>url</key><string>");
                sb.append(serverConfig);
                sb.append("/rest/public/mobile/ipa/");
                sb.append(tmpResource.getGuid());
                sb.append('/');
                sb.append(packageGuid);
                TemporaryResourceFactory.addResourceGuid(tmpResource, packageGuid);
                sb.append("</string></dict>");
            }

            // display image
            if (manifest.getSmallIcon().getContentSize() > 0) {
                sb.append("<dict><key>kind</key><string>display-image</string><key>needs-shine</key>");
                addBoolean(sb, manifest.isIconShine());

                sb.append("<key>url</key><string>");
                sb.append(serverConfig);
                sb.append("/rest/public/mobile/image/");
                sb.append(tmpResource.getGuid());
                sb.append('/');
                sb.append(manifest.getSmallIcon().getGuid());
                TemporaryResourceFactory.addResourceGuid(tmpResource, manifest.getSmallIcon().getGuid());

                sb.append("</string></dict>");
            }

            if (manifest.getLargeIcon().getContentSize() > 0) {
                sb.append("<dict><key>kind</key><string>full-size-image</string><key>needs-shine</key>");
                addBoolean(sb, manifest.isLargeIconShine());

                sb.append("<key>url</key><string>");
                sb.append(serverConfig);
                sb.append("/rest/public/mobile/image/");
                sb.append(tmpResource.getGuid());
                sb.append('/');
                sb.append(manifest.getLargeIcon().getGuid());
                TemporaryResourceFactory.addResourceGuid(tmpResource, manifest.getLargeIcon().getGuid());
                sb.append("</string></dict>");
            }

            sb.append("</array><key>metadata</key><dict>");

            // identifier
            sb.append("<key>bundle-identifier</key><string>");
            sb.append(manifest.getIdentifier());
            sb.append("</string>");

            // version
            sb.append("<key>bundle-version</key><string>1.1</string>");

            // kind
            sb.append("<key>kind</key><string>software</string>");

            // title
            if (manifest.getTitle() != null) {
                sb.append("<key>title</key><string>");
                sb.append(manifest.getTitle());
                sb.append("</string>");
            }

            // subtitle
            if (manifest.getSubtitle() != null) {
                sb.append("<key>subtitle</key><string>");
                sb.append(manifest.getSubtitle());
                sb.append("</string>");
            }
            sb.append("</dict></dict></plist>");
        }

        return sb.toString();
    }

    /**
     * Adds the boolean value to the string builder.
     * @param sb the string builder.
     * @param value the boolean value.
     */
    private static void addBoolean(StringBuilder sb, boolean value) {
        if (value) {
            sb.append("<true/>");
        } else {
            sb.append("<false/>");
        }
    }
}
