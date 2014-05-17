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
package org.lorislab.appky.application.model;

import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The description.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "ST_DESCRIPTION")
public class Description extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 1803850224344868323L;
    /**
     * The locale.
     */
    @Column(name = "C_LOCALE")
    private Locale locale;
    /**
     * The value.
     */
    @Column(name = "C_VALUE", length = 2000)
    private String value;

    /**
     * Gets the locale.
     *
     * @return the locale.
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets the locale.
     *
     * @param locale the locale.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Gets the value.
     *
     * @return the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the value.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
