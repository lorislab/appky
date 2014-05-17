/*
 * Copyright 2011 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.email.util;

import org.lorislab.appky.email.model.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;

/**
 * The email utility class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class EmailUtil {

    /**
     * The email pattern.
     */
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /**
     * The pattern object.
     */
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * The default constructor.
     */
    private EmailUtil() {
        // empty constructor
    }

    public static boolean validate(String email) {
        return pattern.matcher(email).matches();
    }

    /**
     * Gets the email GUID.
     *
     * @param email the email.
     * @return the email GUID or <code>null</code> if the email *
     * is <code>null</code>.
     */
    public static Object getGuid(Email email) {
        Object result = null;
        if (email != null) {
            result = email.getGuid();
        }
        return result;
    }

    /**
     * Creates the list of addresses.
     *
     * @param addresses the set of addresses.
     * @return the list of addresses.
     * @throws Exception if the method fails.
     */
    public static Address[] createAddresses(Set<String> addresses) throws Exception {
        Address[] result = null;
        if (addresses != null && !addresses.isEmpty()) {
            List<Address> items = new ArrayList<>();
            for (String address : addresses) {
                Address item = createAddress(address);
                items.add(item);
            }

            if (!items.isEmpty()) {
                result = items.toArray(new Address[items.size()]);
            }
        }
        return result;
    }

    /**
     * Creates the email address object.
     *
     * @param address the address as a string.
     * @return the email address object.
     * @throws Exception if the method fails.
     */
    public static Address createAddress(String address) throws Exception {
        Address result = null;
        if (address != null) {
            result = new InternetAddress(address);
        }
        return result;
    }
}
