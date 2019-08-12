package com.braintreepayments.api.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public class UUIDHelper {
    public static String getPersistentUUID(Context context) {
        SharedPreferences prefs = BraintreeSharedPreferences.getSharedPreferences(context);
        String uuid = prefs.getString("braintreeUUID", null);
        if (uuid != null) {
            return uuid;
        }
        String uuid2 = getFormattedUUID();
        prefs.edit().putString("braintreeUUID", uuid2).apply();
        return uuid2;
    }

    public static String getFormattedUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
