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
package org.lorislab.appky.web.admin.app.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.lorislab.appky.application.factory.ApplicationObjectFactory;
import org.lorislab.appky.application.model.Description;
import org.primefaces.event.TabCloseEvent;

/**
 * The description view controller.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class DescriptionViewController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 4700095496439621522L;
    /**
     * The list of descriptions.
     */
    private List<Description> descriptions;
    /**
     * The list of languages.
     */
    private Set<Locale> locales;
    /**
     * The locale.
     */
    private String locale;
    /**
     * The default locale.
     */
    private Locale defaultLocale;
    /**
     * The temporary map.
     */
    private Map<Locale, Description> tmp;

    /**
     * The default constructor.
     */
    public DescriptionViewController() {
        locales = new HashSet<>();
        tmp = new HashMap<>();
        descriptions = new ArrayList<>();
    }

    /**
     * Gets the default language.
     *
     * @return the default language.
     */
    public Locale getDefaultLocale() {
        return defaultLocale;
    }

    /**
     * Gets the language.
     *
     * @return the language.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the language.
     *
     * @param locale the language.
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Gets the set of languages.
     *
     * @return the set of languages.
     */
    public Set<Locale> getLocales() {
        return locales;
    }

    /**
     * Adds the new description corresponding by selected language.
     */
    public void add() {
        if (locale != null) {
            Locale loc = new Locale(locale);
            Description result = tmp.get(loc);
            if (result == null) {
                result = ApplicationObjectFactory.createDescription(loc);
                tmp.put(loc, result);
                locales.remove(loc);
                locale = null;
                descriptions.add(result);
            }
        }
    }

    /**
     * Opens the list of descriptions.
     *
     * @param descriptions the list of descriptions.
     * @param locales the list of languages.
     * @param defaultLocale the default language.
     */
    public void open(List<Description> descriptions, List<Locale> locales, Locale defaultLocale) {
        this.descriptions = descriptions;
        tmp = new HashMap<>();
        this.defaultLocale = defaultLocale;
        this.locales = new HashSet<>(locales);
        if (descriptions != null) {
            for (Description item : descriptions) {
                tmp.put(item.getLocale(), item);
                this.locales.remove(item.getLocale());
            }
        }
    }

    /**
     * On tab click event.
     *
     * @param event the on tab click event.
     */
    public void onTabClose(TabCloseEvent event) {
        Object obj = event.getData();
        if (obj != null) {
            if (obj instanceof Description) {
                Description description = (Description) obj;
                tmp.remove(description.getLocale());
                locales.add(description.getLocale());
                locale = null;
                descriptions.remove(description);
            }
        }
    }

    /**
     * Gets the list of descriptions.
     *
     * @return the list of descriptions.
     */
    public List<Description> getDescriptions() {
        return descriptions;
    }
}
