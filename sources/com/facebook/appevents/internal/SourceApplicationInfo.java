package com.facebook.appevents.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import bolts.AppLinks;
import com.facebook.FacebookSdk;

class SourceApplicationInfo {
    private static final String CALL_APPLICATION_PACKAGE_KEY = "com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage";
    private static final String OPENED_BY_APP_LINK_KEY = "com.facebook.appevents.SourceApplicationInfo.openedByApplink";
    private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
    private String callingApplicationPackage;
    private boolean openedByApplink;

    public static class Factory {
        public static SourceApplicationInfo create(Activity activity) {
            boolean openedByApplink = false;
            ComponentName callingApplication = activity.getCallingActivity();
            if (callingApplication == null) {
                return null;
            }
            String callingApplicationPackage = callingApplication.getPackageName();
            if (callingApplicationPackage.equals(activity.getPackageName())) {
                return null;
            }
            Intent openIntent = activity.getIntent();
            if (openIntent != null && !openIntent.getBooleanExtra(SourceApplicationInfo.SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, false)) {
                openIntent.putExtra(SourceApplicationInfo.SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, true);
                Bundle applinkData = AppLinks.getAppLinkData(openIntent);
                if (applinkData != null) {
                    openedByApplink = true;
                    Bundle applinkReferrerData = applinkData.getBundle("referer_app_link");
                    if (applinkReferrerData != null) {
                        callingApplicationPackage = applinkReferrerData.getString("package");
                    }
                }
            }
            openIntent.putExtra(SourceApplicationInfo.SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, true);
            return new SourceApplicationInfo(callingApplicationPackage, openedByApplink);
        }
    }

    private SourceApplicationInfo(String callingApplicationPackage2, boolean openedByApplink2) {
        this.callingApplicationPackage = callingApplicationPackage2;
        this.openedByApplink = openedByApplink2;
    }

    public static SourceApplicationInfo getStoredSourceApplicatioInfo() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
        if (!sharedPreferences.contains(CALL_APPLICATION_PACKAGE_KEY)) {
            return null;
        }
        return new SourceApplicationInfo(sharedPreferences.getString(CALL_APPLICATION_PACKAGE_KEY, null), sharedPreferences.getBoolean(OPENED_BY_APP_LINK_KEY, false));
    }

    public static void clearSavedSourceApplicationInfoFromDisk() {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        editor.remove(CALL_APPLICATION_PACKAGE_KEY);
        editor.remove(OPENED_BY_APP_LINK_KEY);
        editor.apply();
    }

    public String getCallingApplicationPackage() {
        return this.callingApplicationPackage;
    }

    public boolean isOpenedByApplink() {
        return this.openedByApplink;
    }

    public String toString() {
        String openType = "Unclassified";
        if (this.openedByApplink) {
            openType = "Applink";
        }
        if (this.callingApplicationPackage != null) {
            return openType + "(" + this.callingApplicationPackage + ")";
        }
        return openType;
    }

    public void writeSourceApplicationInfoToDisk() {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        editor.putString(CALL_APPLICATION_PACKAGE_KEY, this.callingApplicationPackage);
        editor.putBoolean(OPENED_BY_APP_LINK_KEY, this.openedByApplink);
        editor.apply();
    }
}
