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
package org.lorislab.appky.process.registration.factory;

import org.lorislab.appky.process.registration.model.Registration;

/**
 * The registration factory class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class RegistrationFactory {

    /**
     * The default constructor.
     */
    private RegistrationFactory() {
        // empty constructor
    }

    /**
     * Creates the registration model.
     *
     * @return the registration model.
     */
    public static Registration createRegistration() {
        Registration result = new Registration();
        return result;
    }
}
