/*
 * Copyright 2012 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.application.wrapper.util;

import org.lorislab.appky.application.factory.ApplicationObjectFactory;
import org.lorislab.appky.application.model.Description;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * The description wrapper utility class.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class DescriptionUtil {

    /**
     * Finds the validate description.
     * @param tmp the list of descriptions.
     * @param userLocale the user locale.
     * @return the validate description.
     */
    public static Description findDescription(List<Description> tmp, Locale userLocale) {
        Description result = null;
        if (tmp != null && !tmp.isEmpty()) {

            Iterator<Description> iter = tmp.iterator();
            Description item;
            boolean findResult = false;
            while (iter.hasNext() && !findResult) {
                item = iter.next();
                if (item != null) {
                    findResult = item.getLocale().equals(userLocale);
                    result = item;
                }
            }
        }
        if (result == null) {
            result = ApplicationObjectFactory.createDescription(userLocale);
        }
        return result;
    }
}
