package com.appboy.support;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.security.MessageDigest;
import java.util.Collection;

public final class StringUtils {

    /* renamed from: a */
    private static final String f2916a = AppboyLogger.getAppboyLogTag(StringUtils.class);

    public static String checkNotNullOrEmpty(String reference) {
        if (reference == null) {
            throw new NullPointerException("Provided String must be non-null.");
        } else if (reference.length() != 0) {
            return reference;
        } else {
            throw new IllegalArgumentException("Provided String must be non-empty.");
        }
    }

    public static String join(Collection<String> strings, String sep) {
        if (strings == null) {
            return "";
        }
        return join((String[]) strings.toArray(new String[strings.size()]), sep);
    }

    public static String join(String[] strings, String sep) {
        if (strings == null || sep == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            if (str != null) {
                sb.append(str).append(sep);
            }
        }
        String sb2 = sb.toString();
        if (sb2.endsWith(sep)) {
            return sb2.substring(0, sb2.length() - sep.length());
        }
        return sb2;
    }

    public static boolean isNullOrEmpty(String reference) {
        return reference == null || reference.length() == 0;
    }

    public static boolean isNullOrBlank(String reference) {
        return reference == null || reference.trim().length() == 0;
    }

    public static String emptyToNull(String input) {
        if (input.trim().equals("")) {
            return null;
        }
        return input;
    }

    public static int countOccurrences(String reference, String subString) {
        return reference.split(subString, -1).length - 1;
    }

    public static String getCacheFileSuffix(Context context, String userId, String apiKey) {
        if (userId == null) {
            userId = "null";
        }
        if (userId.equals("null")) {
            return m1741a("37a6259cc0c1dae299a7866489dff0bd", apiKey);
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.appboy.support.stringutils.cachefilesuffix", 0);
        String string = sharedPreferences.getString("user_id_key", null);
        if (string != null && string.equals(userId)) {
            String string2 = sharedPreferences.getString("user_id_hash_value", null);
            if (!isNullOrEmpty(string2)) {
                return m1741a(string2, apiKey);
            }
            AppboyLogger.m1733d(f2916a, "The saved user id hash was null or empty.");
        }
        AppboyLogger.m1733d(f2916a, String.format("Generating MD5 for user id: %s apiKey: %s", new Object[]{userId, apiKey}));
        String MD5 = MD5(userId);
        if (MD5 == null) {
            MD5 = Integer.toString(userId.hashCode());
        }
        Editor edit = sharedPreferences.edit();
        edit.putString("user_id_key", userId);
        edit.putString("user_id_hash_value", MD5);
        edit.apply();
        return m1741a(MD5, apiKey);
    }

    public static String getCacheFileSuffix(Context context, String userId) {
        return getCacheFileSuffix(context, userId, null);
    }

    /* renamed from: a */
    private static String m1741a(String str, String str2) {
        if (isNullOrBlank(str2)) {
            return "." + str;
        }
        return "." + str + "." + str2;
    }

    public static String MD5(String md5) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            AppboyLogger.m1738i(f2916a, "Failed to calculate MD5 hash", e);
            return null;
        }
    }
}
