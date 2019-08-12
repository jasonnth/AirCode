package com.appboy.support;

import com.airbnb.android.cohosting.utils.CohostingConstants;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class ValidationUtils {

    /* renamed from: a */
    private static final String f2917a = AppboyLogger.getAppboyLogTag(ValidationUtils.class);

    /* renamed from: b */
    private static final Set<String> f2918b = new HashSet(Arrays.asList(new String[]{"appboy"}));

    /* renamed from: c */
    private static final Set<String> f2919c = new HashSet(Arrays.asList(new String[]{CohostingConstants.FIRST_NAME_FIELD, "last_name", "email", "gender", "dob", "country", "home_city", "email_subscribe", "push_subscribe", "phone", "facebook", "twitter", "image_url"}));

    public static boolean isValidEmailAddress(String emailAddress) {
        return emailAddress != null && emailAddress.toLowerCase().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^[0-9 .\\(\\)\\+\\-]+$");
    }

    public static boolean isValidCustomAttributeKey(String key) {
        if (key == null) {
            AppboyLogger.m1739w(f2917a, "Custom attribute key cannot be null.");
            return false;
        } else if (customAttributeKeyHasReservedPrefix(key) || customAttributeKeyIsReservedKey(key)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isBlacklistedCustomAttributeKey(String key, Set<String> blacklistedAttributes) {
        if (!blacklistedAttributes.contains(key)) {
            return false;
        }
        AppboyLogger.m1739w(f2917a, String.format("Custom attribute key cannot be blacklisted attribute: %s.", new Object[]{key}));
        return true;
    }

    public static boolean isValidCustomAttributeValue(String value) {
        if (value != null) {
            return true;
        }
        AppboyLogger.m1739w(f2917a, "Custom attribute value cannot be null.");
        return false;
    }

    public static boolean customAttributeKeyHasReservedPrefix(String key) {
        String key2 = key.trim();
        for (String startsWith : f2918b) {
            if (key2.toLowerCase().startsWith(startsWith)) {
                AppboyLogger.m1739w(f2917a, String.format("'%s' contains a reserved prefix. Cannot use the given key.", new Object[]{key2}));
                return true;
            }
        }
        return false;
    }

    public static boolean customAttributeKeyIsReservedKey(String key) {
        String key2 = key.trim();
        if (!f2919c.contains(key2)) {
            return false;
        }
        AppboyLogger.m1739w(f2917a, String.format("'%s' is a reserved attribute key. Cannot use the given key.", new Object[]{key2}));
        return true;
    }

    public static String[] ensureCustomAttributeArrayValues(String[] values) {
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                values[i] = ensureAppboyFieldLength(values[i]);
            }
        }
        return values;
    }

    public static String ensureAppboyFieldLength(String field) {
        String field2 = field.trim();
        if (field2.length() <= 255) {
            return field2;
        }
        AppboyLogger.m1739w(f2917a, String.format("Provided string field is too long [%d]. The max length is %d, truncating provided field.", new Object[]{Integer.valueOf(field2.length()), Integer.valueOf(255)}));
        return field2.substring(0, 255);
    }
}
