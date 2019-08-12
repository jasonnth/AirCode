package com.paypal.android.sdk.data.collector;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public class InstallationIdentifier {
    public static String getInstallationGUID(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("PayPalOTC", 0);
        String existingGUID = preferences.getString("InstallationGUID", null);
        if (existingGUID != null) {
            return existingGUID;
        }
        String newGuid = UUID.randomUUID().toString();
        preferences.edit().putString("InstallationGUID", newGuid).apply();
        return newGuid;
    }
}
