/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lorislab.appky.process.registration.util;

import org.lorislab.appky.process.registration.model.Registration;

/**
 * The registration utility class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class RegistrationUtil {

    /**
     * The default constructor.
     */
    private RegistrationUtil() {
        // empty constructor
    }

    /**
     * Gets the email from the registration model.
     *
     * @param registration the registration model.
     * @return the email.
     */
    public static String getEmail(Registration registration) {
        String result = null;
        if (registration != null) {
            result = registration.getEmail();
        }
        return result;
    }
}
