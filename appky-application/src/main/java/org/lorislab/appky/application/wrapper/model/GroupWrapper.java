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
package org.lorislab.appky.application.wrapper.model;


import java.util.Locale;
import org.lorislab.appky.application.model.Description;
import org.lorislab.appky.application.model.Group;
import org.lorislab.appky.application.wrapper.util.DescriptionUtil;
import org.lorislab.jel.ejb.wrapper.AbstractWrapper;

/**
 * The group wrapper model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class GroupWrapper extends AbstractWrapper<Group> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1468935695316083265L;
    /**
     * The title.
     */
    private Description title;
    /**
     * The description.
     */
    private Description description;

    /**
     * The default constructor.
     *
     * @param group the group.
     * @param locale the user locale.
     */
    public GroupWrapper(Group group, Locale locale) {
        setModel(group);
        title = DescriptionUtil.findDescription(group.getTitles(), locale);
        description = DescriptionUtil.findDescription(group.getDescriptions(), locale);
    }

    /**
     * Gets the group description.
     *
     * @return the group description.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Gets the group title.
     *
     * @return the group title.
     */
    public Description getTitle() {
        return title;
    }

    /**
     * Gets the group GUID.
     *
     * @return the group GUID.
     */
    @Override
    public String getGuid() {
        return model.getGuid();
    }
}
