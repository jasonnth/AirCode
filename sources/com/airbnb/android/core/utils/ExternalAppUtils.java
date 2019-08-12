package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import com.airbnb.android.core.CoreApplication;
import p005cn.jpush.android.JPushConstants.PushActivity;

public class ExternalAppUtils {
    public static final String AMAZON_STORE_APP_ID = "com.amazon.venezia";
    public static final String BAIDU_STORE_APP_ID = "com.baidu.appsearch";
    public static final String FACEBOOK_APP_ID = "com.facebook.katana";
    public static final String GOOGLE_MAPS_APP_ID = "com.google.android.apps.maps";
    public static final String GOOGLE_PLAY_STORE_APP_ID = "com.android.vending";
    public static final String WECHAT_APP_ID = "com.tencent.mm";

    public static boolean isKnownInstaller(Context context) {
        String installerPackage = getInstallerPackage(context);
        return TextUtils.equals(installerPackage, "com.android.vending") || TextUtils.equals(installerPackage, BAIDU_STORE_APP_ID);
    }

    public static boolean isFacebookInstalled(Context context) {
        return isAppInstalled(context, FACEBOOK_APP_ID);
    }

    public static boolean isWechatInstalled(Context context) {
        return isAppInstalled(context, WECHAT_APP_ID);
    }

    private static boolean isAppInstalled(Context context, String appId) {
        try {
            try {
                context.getPackageManager().getPackageInfo(appId, 1);
                return true;
            } catch (NameNotFoundException e) {
                return false;
            }
        } catch (RuntimeException e2) {
            return false;
        }
    }

    public static Intent intentToAppSettings() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory(PushActivity.CATEGORY_1);
        intent.setData(Uri.parse("package:" + CoreApplication.appContext().getPackageName()));
        intent.addFlags(268435456);
        intent.addFlags(1073741824);
        intent.addFlags(8388608);
        return intent;
    }

    public static String getInstallerPackage(Context context) {
        return SanitizeUtils.emptyIfNull(context.getPackageManager().getInstallerPackageName(context.getPackageName()));
    }

    public static Uri getAppStoreUri(Context context) {
        String installerPackage = getInstallerPackage(context);
        if (BuildHelper.isAmazonDevice() || TextUtils.equals(AMAZON_STORE_APP_ID, installerPackage)) {
            return Uri.parse("amzn://apps/android?p=com.airbnb.android");
        }
        if (MiscUtils.hasGooglePlayServices(CoreApplication.appContext()) || TextUtils.equals("com.android.vending", installerPackage)) {
            return Uri.parse("market://details?id=com.airbnb.android");
        }
        if (AppLaunchUtils.isUserInChina()) {
            return Uri.parse("market://details?id=com.airbnb.android");
        }
        return Uri.parse("https://play.google.com/store/apps/details?id=com.airbnb.android");
    }
}
