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
package org.lorislab.appky.web.converter;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 * The locale converter.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Named("localeConverter")
public class LocaleConverter implements Converter, Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6847247997306966842L;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Locale result = null;
        if (value != null && !value.isEmpty()) {
            result = new Locale(value);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = null;
        if (value != null) {
            if (value instanceof Locale) {
                result = ((Locale) value).getLanguage();
            }
        }
        return result;
    }
}
