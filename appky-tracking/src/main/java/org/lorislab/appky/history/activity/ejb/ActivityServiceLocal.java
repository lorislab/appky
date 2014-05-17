/*
 * Copyright 2013 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.history.activity.ejb;

import org.lorislab.appky.history.activity.model.Activity;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 * The activity service local interface.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface ActivityServiceLocal {
    
    /**
     * Saves the activity model.
     *
     * @param activity the activity model.
     * @return the new saved activity model.
     * @throws ServiceException if the method fails.
     */
    Activity saveActivity(Activity activity) throws ServiceException;    
}
