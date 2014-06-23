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
package org.lorislab.appky.application.tmpresource.factory;

import org.lorislab.appky.application.tmpresource.model.TemporaryResource;
import org.lorislab.appky.application.tmpresource.model.TemporaryResourceData;
import java.util.Date;

/**
 * The temporary resource factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class TemporaryResourceFactory {

    /**
     * The default constructor.
     */
    private TemporaryResourceFactory() {
        // empty constructor
    }

    /**
     * Creates the temporary resource.
     *
     * @param userGuid the user GUID.
     * @param validateTo the validate to date.
     * @return the new created temporary resource.
     */
    public static TemporaryResource create(String userGuid, Date validateTo) {
        TemporaryResource result = new TemporaryResource();
        result.setUserGuid(userGuid);
        result.setValidateTo(validateTo);
        return result;
    }

    /**
     * Adds the resource GUID to the temporary resource.
     * @param tmp the temporary resource.
     * @param resourceGuid the resource GUID.
     * @return the temporary resource.
     */
    public static TemporaryResource addResourceGuid(TemporaryResource tmp, String resourceGuid) {
        tmp.getResources().add(createData(resourceGuid));
        return tmp;
    }
    
    /**
     * Creates the temporary resource data.
     *
     * @param resourceGuid the resource GUID.
     * @return the new created temporary resource data.
     */
    public static TemporaryResourceData createData(String resourceGuid) {
        TemporaryResourceData result = new TemporaryResourceData();
        result.setResourceGuid(resourceGuid);
        return result;
    }
}
