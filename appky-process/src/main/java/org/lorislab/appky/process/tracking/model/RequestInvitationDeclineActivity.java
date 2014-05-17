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
package org.lorislab.appky.process.tracking.model;

import org.lorislab.appky.process.tracking.model.enums.UserActivity;

/**
 * The decline request invitation activity.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class RequestInvitationDeclineActivity extends AbstractStoreTrackingData {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -8556844636770064632L;
    
    /**
     * The default constructor.
     * 
     * @param requestGuid the request GUID.
     */
    public RequestInvitationDeclineActivity(String requestGuid) {
        super(UserActivity.REQUEST_INVITATION_DECLINE);
        addParameter(PARAM_REQUEST, requestGuid);
    }
}
