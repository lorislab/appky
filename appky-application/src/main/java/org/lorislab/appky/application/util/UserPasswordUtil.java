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
package org.lorislab.appky.application.util;

import org.lorislab.appky.application.model.UserPassword;
import java.util.Arrays;

/**
 * The user password utility class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class UserPasswordUtil {

    /**
     * The default constructor.
     */
    private UserPasswordUtil() {
        // empty constructor
    }

    /**
     * Resets the user password.
     *
     * @param userPassword the current user password model.
     * @param newPassword the new password.
     * @return <code>true</code> if the password has been reset.
     */
    public static boolean resetPassword(UserPassword userPassword, char[] newPassword) {
        boolean result = false;
        if (userPassword != null) {
            userPassword.setPassword2(userPassword.getPassword1());
            userPassword.setPassword1(newPassword);
            result = true;
        }
        return result;
    }

    /**
     * Changes the user password.
     *
     * @param userPassword the current user password model.
     * @param password the new password.
     * @param newPassword the new confirm password
     * @return <code>true</code> if the password has been changed.
     */
    public static boolean changePassword(UserPassword userPassword, char[] password, char[] newPassword) {
        boolean result = false;
        if (userPassword != null) {
            if (Arrays.equals(password, userPassword.getPassword1())) {
                userPassword.setPassword2(userPassword.getPassword1());
                userPassword.setPassword1(newPassword);
                result = true;
            }
        }
        return result;
    }
}
