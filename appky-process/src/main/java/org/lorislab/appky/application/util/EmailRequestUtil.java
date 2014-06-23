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

import org.lorislab.appky.application.model.EmailRequest;

/**
 * The email request utility class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class EmailRequestUtil {

    /**
     * The default constructor.
     */
    private EmailRequestUtil() {
        // empty constructor
    }

    /**
     * Gets the email from the email request.
     *
     * @param request the email request.
     * @return the email or <code>null</code> if the email request
     * is <code>null</code>.
     */
    public static String getEmail(EmailRequest request) {
        String result = null;
        if (request != null) {
            result = request.getEmail();
        }
        return result;
    }
}
