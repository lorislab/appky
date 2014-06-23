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
package org.lorislab.appky.process.tracking.model;

import org.lorislab.appky.process.tracking.model.enums.UserActivity;

/**
 * The login activity.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class LoginActivity extends AbstractStoreTrackingData {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -6477043071616214733L;

    /**
     * The default constructor.
     */
    public LoginActivity() {
        super(UserActivity.LOGIN);
    }
}
