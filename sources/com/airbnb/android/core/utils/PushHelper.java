package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.android.core.services.JPushBroadcastReceiver;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PushHelper {
    private static final String GCM = "GCM";
    private static final String JPush = "JPUSH";
    public static final String PROPERTY_AIR_NOTIF_DEVICE_ID = "air_notification_device_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    public static final String PROPERTY_PUSH_SERVICE = "push_service";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String TAG = PushHelper.class.getSimpleName();
    private final Context mContext;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PushService {
    }

    public static PushHelper newInstance(Context context) {
        if (MiscUtils.hasGooglePlayServices(context)) {
            return new GCMHelper(context);
        }
        if (JPushHelper.enableJPush(context)) {
            return new JPushHelper(context);
        }
        return new PushHelper(context);
    }

    public PushHelper(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setupPush() {
        if (TextUtils.isEmpty(getCachedRegistrationIdSafe())) {
            registerBackground();
        }
    }

    public String getCachedRegistrationIdSafe() {
        SharedPreferences prefs = getPushPreferences(this.mContext);
        String registrationId = prefs.getString("registration_id", "");
        if (registrationId.length() == 0) {
            Log.v(TAG, "Registration not found.");
            return "";
        } else if (prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE) != BuildHelper.versionCode()) {
            Log.v(TAG, "App version changed.");
            return "";
        } else if (isPushEnviromentChanged()) {
            return "";
        } else {
            return registrationId;
        }
    }

    private boolean isPushEnviromentChanged() {
        SharedPreferences prefs = getPushPreferences(this.mContext);
        String oldEnviroment = prefs.getString(PROPERTY_PUSH_SERVICE, "");
        if (TextUtils.isEmpty(oldEnviroment)) {
            return true;
        }
        boolean hasGooglePlayServices = MiscUtils.hasGooglePlayServices(this.mContext);
        if (oldEnviroment.equals(JPush) && hasGooglePlayServices) {
            prefs.edit().putString(PROPERTY_PUSH_SERVICE, JPush).apply();
            return true;
        } else if (!oldEnviroment.equals(GCM) || hasGooglePlayServices) {
            return false;
        } else {
            prefs.edit().putString(PROPERTY_PUSH_SERVICE, GCM).apply();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public SharedPreferences getPushPreferences(Context context) {
        return context.getSharedPreferences(PushHelper.class.getSimpleName(), 0);
    }

    /* access modifiers changed from: protected */
    public void registerBackground() {
    }

    public void deleteRegistrationId() {
        getPushPreferences(this.mContext).edit().remove("registration_id").apply();
    }

    public void setRegistrationId(Context context, String regId) {
        SharedPreferences prefs = getPushPreferences(context);
        int appVersion = BuildHelper.versionCode();
        Log.v(TAG, "Saving regId on app version " + appVersion);
        Editor editor = prefs.edit();
        editor.putString("registration_id", regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.putString(PROPERTY_PUSH_SERVICE, MiscUtils.hasGooglePlayServices(this.mContext) ? GCM : JPush);
        editor.apply();
    }

    public void forceUpdateOnNextLaunch() {
        getPushPreferences(this.mContext).edit().remove("registration_id").apply();
    }

    public static void completeWakefulIntent(Context context, Intent intent) {
        if (MiscUtils.hasGooglePlayServices(context)) {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        } else {
            JPushBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    public void saveAirNotificationDeviceId(long airNotificationDeviceId) {
        getPushPreferences(this.mContext).edit().putLong(PROPERTY_AIR_NOTIF_DEVICE_ID, airNotificationDeviceId).apply();
    }

    public long getAirNotificationDeviceId() {
        return getPushPreferences(this.mContext).getLong(PROPERTY_AIR_NOTIF_DEVICE_ID, 0);
    }

    public String getDeviceType() {
        return "";
    }
}
