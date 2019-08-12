package com.airbnb.android.utils;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.utils.PasswordStrength.Level;
import com.airbnb.utils.R;

public final class PasswordUtils {

    public enum PasswordResult {
        TOO_LONG(R.string.registration_password_error_too_long),
        TOO_SHORT(R.string.registration_password_error_too_short),
        TOO_WEAK(R.string.registration_password_error_too_weak),
        CONTAINS_FORBIDDEN_CONTENT(R.string.registration_password_error_contains_forbidden_content),
        VALID(0);
        
        public final int messageRes;

        private PasswordResult(int messageRes2) {
            this.messageRes = messageRes2;
        }
    }

    private PasswordUtils() {
    }

    public static boolean isValidPassword(String password) {
        return checkPasswordWithForbiddenContent(password, new String[0]) == PasswordResult.VALID;
    }

    public static String getInvalidPasswordErrorMessage(Context context, String password) {
        PasswordResult result = checkPasswordWithForbiddenContent(password, new String[0]);
        if (result != PasswordResult.VALID) {
            return context.getString(result.messageRes);
        }
        return null;
    }

    public static PasswordResult checkPasswordWithForbiddenContent(String password, String... forbiddenContent) {
        if (TextUtils.isEmpty(password) || password.length() < 8) {
            return PasswordResult.TOO_SHORT;
        }
        for (String s : forbiddenContent) {
            if (password.toLowerCase().contains(s.toLowerCase())) {
                return PasswordResult.CONTAINS_FORBIDDEN_CONTENT;
            }
        }
        if (password.length() > 128) {
            return PasswordResult.TOO_LONG;
        }
        if (!PasswordStrength.test(password).isValid(Level.Good)) {
            return PasswordResult.TOO_WEAK;
        }
        return PasswordResult.VALID;
    }
}
