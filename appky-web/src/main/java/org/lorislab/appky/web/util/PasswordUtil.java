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
package org.lorislab.appky.web.util;

import java.io.ByteArrayOutputStream;

/**
 * The password utility class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class PasswordUtil {

    /**
     * The MD5 instance.
     */
    private static String INSTANCE = "MD5";

    /**
     * Creates password from the string.
     *
     * @param password the password.
     * @return the password by MD5.
     * @throws Exception if the method fails.
     */
    public static char[] createPassword(String password) throws Exception {
         throw new UnsupportedOperationException();
//        char[] result = null;
//        try {
//            byte[] hash = java.security.MessageDigest.getInstance(INSTANCE).digest(password.getBytes());
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//           
//            
//            Base64Encoder.encode(hash, out);
//            byte[] data = out.toByteArray();
//            if (data != null) {
//                result = new char[data.length];
//                for (int i = 0; i < data.length; i++) {
//                    result[i] = (char) data[i];
//                }
//            }
//            
//        } catch (Exception e) {
//            throw new Exception("Error create password", e);
//        }
//        return result;
    }

    /**
     * Validate passwords.
     *
     * @param password1 the password.
     * @param password2 the password.
     * @return return <code>true</code> if the passwords are equals.
     */
    public static boolean validatePasswords(String password1, String password2) {
        boolean result = false;
        if (password1 != null && password2 != null) {
            if (!password1.isEmpty() && !password2.isEmpty()) {
                result = password1.equals(password2);
            }
        }
        return result;
    }

    /**
     * Validate passwords.
     *
     * @param password1 the password.
     * @param password2 the password.
     * @return return <code>true</code> if the passwords are equals.
     */
    public static boolean validatePasswords(char[] password1, char[] password2) {
        boolean result = false;
        if (password1 != null && password2 != null) {
            if (password1.length == password2.length && password1.length != 0) {
                int size = password1.length;
                boolean result2 = true;
                int i = 0;
                while (i < size && result2) {
                    result2 = password1[i] == password2[i];
                    i++;
                }
                result = result2;
            }
        }
        return result;
    }
}
