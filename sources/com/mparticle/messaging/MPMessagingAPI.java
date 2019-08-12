package com.mparticle.messaging;

import android.content.Context;
import com.mparticle.MPService;
import com.mparticle.MParticle;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.internal.ConfigManager;
import com.mparticle.internal.MPUtility;
import com.mparticle.internal.PushRegistrationHelper;

public class MPMessagingAPI {
    public static final String BROADCAST_NOTIFICATION_RECEIVED = "com.mparticle.push.RECEIVE";
    public static final String BROADCAST_NOTIFICATION_TAPPED = "com.mparticle.push.TAP";
    public static final String CLOUD_ACTION_EXTRA = "mp-push-action";
    public static final String CLOUD_MESSAGE_EXTRA = "mp-push-message";
    private final Context mContext;

    private MPMessagingAPI() {
        this.mContext = null;
    }

    public MPMessagingAPI(Context context) {
        this.mContext = context;
    }

    public void setPushNotificationIcon(int resId) {
    }

    public void setPushNotificationTitle(int resId) {
    }

    public void enablePushNotifications(String senderId) {
        MParticle.getInstance().getConfigManager().setPushSenderId(senderId);
        if (!MPUtility.isInstanceIdAvailable()) {
            ConfigManager.log(LogLevel.ERROR, "Push is enabled but Google Cloud Messaging library not found - you must add com.google.android.gms:play-services-gcm:7.5 or later to your application.");
        } else if (!MPUtility.isServiceAvailable(this.mContext, MPService.class)) {
            ConfigManager.log(LogLevel.ERROR, "Push is enabled but you have not added <service android:name=\"com.mparticle.MPService\" /> to the <application> section of your AndroidManifest.xml");
        } else if (!MPUtility.checkPermission(this.mContext, "com.google.android.c2dm.permission.RECEIVE")) {
            ConfigManager.log(LogLevel.ERROR, "Attempted to enable push notifications without required permission: ", "\"com.google.android.c2dm.permission.RECEIVE\"");
        } else {
            PushRegistrationHelper.requestInstanceId(this.mContext, senderId);
        }
    }

    public void disablePushNotifications() {
        PushRegistrationHelper.disablePushNotifications(this.mContext);
    }

    public void setNotificationSoundEnabled(Boolean enabled) {
    }

    public void setNotificationVibrationEnabled(Boolean enabled) {
    }
}
