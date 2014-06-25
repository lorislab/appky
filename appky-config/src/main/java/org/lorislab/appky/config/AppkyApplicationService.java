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

package org.lorislab.appky.config;

import org.kohsuke.MetaInfServices;
import org.lorislab.barn.api.service.ApplicationService;

/**
 * The application service for the BARN.
 * 
 * @author Andrej Petras
 */
@MetaInfServices
public class AppkyApplicationService implements ApplicationService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getApplication() {
        return "APPKY";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        return "1.0.0";
    }
    
}
