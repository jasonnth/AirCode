package com.mparticle.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Looper;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.google.android.gms.iid.InstanceID;
import com.mparticle.MParticle;
import com.mparticle.MParticle.LogLevel;

public class PushRegistrationHelper {

    public static class PushRegistration {
        public String instanceId;
        public String senderId;
    }

    private static int getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public static PushRegistration getLatestPushRegistration(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0);
        String string = sharedPreferences.getString("mp::push_reg_id", "");
        if (MPUtility.isEmpty(string)) {
            return null;
        }
        String string2 = sharedPreferences.getString("mp::push_reg_sender_id", "");
        int i = sharedPreferences.getInt("mp::appversion", Integer.MIN_VALUE);
        int appVersion = getAppVersion(context);
        int i2 = sharedPreferences.getInt("mp::osversion", Integer.MIN_VALUE);
        if (i == appVersion && i2 == VERSION.SDK_INT) {
            PushRegistration pushRegistration = new PushRegistration();
            pushRegistration.instanceId = string;
            pushRegistration.senderId = string2;
            return pushRegistration;
        }
        ConfigManager.log(LogLevel.DEBUG, "App or OS version changed, clearing instance ID.");
        return null;
    }

    public static void requestInstanceId(Context context) {
        requestInstanceId(context, MParticle.getInstance().getConfigManager().getPushSenderId());
    }

    public static void requestInstanceId(final Context context, final String senderId) {
        if (getLatestPushRegistration(context) == null && MPUtility.isInstanceIdAvailable()) {
            C46011 r0 = new Runnable() {
                public void run() {
                    try {
                        MParticle.getInstance().logPushRegistration(InstanceID.getInstance(context).getToken(senderId, "GCM"), senderId);
                    } catch (Exception e) {
                        ConfigManager.log(LogLevel.ERROR, "Error registering for GCM Instance ID: ", e.getMessage());
                    }
                }
            };
            if (Looper.getMainLooper() == Looper.myLooper()) {
                new Thread(r0).start();
            } else {
                r0.run();
            }
        }
    }

    public static void setInstanceId(Context context, PushRegistration registration) {
        int appVersion = getAppVersion(context);
        Editor edit = context.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0).edit();
        edit.putString("mp::push_reg_id", registration.instanceId);
        edit.putString("mp::push_reg_sender_id", registration.senderId);
        edit.putInt("mp::appversion", appVersion);
        edit.putInt("mp::osversion", VERSION.SDK_INT);
        edit.apply();
    }

    public static void disablePushNotifications(final Context context) {
        if (MPUtility.isInstanceIdAvailable()) {
            new Thread(new Runnable() {
                public void run() {
                    PushRegistrationHelper.clearInstanceId(context);
                }
            }).start();
        }
    }

    public static void clearInstanceId(Context context) {
        context.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0).edit().remove("mp::push_reg_id").remove("mp::push_reg_sender_id").putBoolean("mp::push_enabled", false).apply();
    }
}
