package com.appboy;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.p000v4.app.NotificationManagerCompat;
import android.util.Log;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.push.AppboyNotificationActionUtils;
import com.appboy.push.AppboyNotificationUtils;
import com.appboy.support.AppboyLogger;

public final class AppboyGcmReceiver extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyGcmReceiver.class.getName()});

    public class HandleAppboyGcmMessageTask extends AsyncTask<Void, Void, Void> {
        private final Context mContext;
        private final Intent mIntent;

        public HandleAppboyGcmMessageTask(Context context, Intent intent) {
            this.mContext = context;
            this.mIntent = intent;
            execute(new Void[0]);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voids) {
            try {
                AppboyGcmReceiver.this.handleAppboyGcmMessage(this.mContext, this.mIntent);
            } catch (Exception e) {
                AppboyLogger.m1736e(AppboyGcmReceiver.TAG, "Failed to create and display notification.", e);
            }
            return null;
        }
    }

    public void onReceive(Context context, Intent intent) {
        AppboyLogger.m1737i(TAG, String.format("Received broadcast message. Message: %s", new Object[]{intent.toString()}));
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            handleRegistrationEventIfEnabled(new AppboyConfigurationProvider(context), context, intent);
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
            handleAppboyGcmReceiveIntent(context, intent);
        } else if ("com.appboy.action.CANCEL_NOTIFICATION".equals(action)) {
            AppboyNotificationUtils.handleCancelNotificationAction(context, intent);
        } else if ("com.appboy.action.APPBOY_ACTION_CLICKED".equals(action)) {
            AppboyNotificationActionUtils.handleNotificationActionClicked(context, intent);
        } else if ("com.appboy.action.APPBOY_PUSH_CLICKED".equals(action)) {
            AppboyNotificationUtils.handleNotificationOpened(context, intent);
        } else {
            AppboyLogger.m1739w(TAG, "The GCM receiver received a message not sent from Appboy. Ignoring the message.");
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean handleRegistrationIntent(Context context, Intent intent) {
        String error = intent.getStringExtra("error");
        String registrationId = intent.getStringExtra("registration_id");
        if (error != null) {
            if ("SERVICE_NOT_AVAILABLE".equals(error)) {
                Log.e(TAG, "Unable to connect to the GCM registration server. Try again later.");
            } else if ("ACCOUNT_MISSING".equals(error)) {
                Log.e(TAG, "No Google account found on the phone. For pre-3.0 devices, a Google account is required on the device.");
            } else if ("AUTHENTICATION_FAILED".equals(error)) {
                Log.e(TAG, "Unable to authenticate Google account. For Android versions <4.0.4, a valid Google Play account is required for Google Cloud Messaging to function. This phone will be unable to receive Google Cloud Messages until the user logs in with a valid Google Play account or upgrades the operating system on this device.");
            } else if ("INVALID_SENDER".equals(error)) {
                Log.e(TAG, "One or multiple of the sender IDs provided are invalid.");
            } else if ("PHONE_REGISTRATION_ERROR".equals(error)) {
                Log.e(TAG, "Device does not support GCM.");
            } else if ("INVALID_PARAMETERS".equals(error)) {
                Log.e(TAG, "The request sent by the device does not contain the expected parameters. This phone does not currently support GCM.");
            } else {
                AppboyLogger.m1739w(TAG, String.format("Received an unrecognised GCM registration error type. Ignoring. Error: %s", new Object[]{error}));
            }
        } else if (registrationId != null) {
            Appboy.getInstance(context).registerAppboyPushMessages(registrationId);
        } else if (intent.hasExtra("unregistered")) {
            Appboy.getInstance(context).unregisterAppboyPushMessages();
        } else {
            AppboyLogger.m1739w(TAG, "The GCM registration message is missing error information, registration id, and unregistration confirmation. Ignoring.");
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean handleAppboyGcmMessage(Context context, Intent intent) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if ("deleted_messages".equals(intent.getStringExtra("message_type"))) {
            int totalDeleted = intent.getIntExtra("total_deleted", -1);
            if (totalDeleted == -1) {
                Log.e(TAG, String.format("Unable to parse GCM message. Intent: %s", new Object[]{intent.toString()}));
            } else {
                AppboyLogger.m1737i(TAG, String.format("GCM deleted %d messages. Fetch them from Appboy.", new Object[]{Integer.valueOf(totalDeleted)}));
            }
            return false;
        }
        Bundle gcmExtras = intent.getExtras();
        AppboyLogger.m1737i(TAG, String.format("Push message payload received: %s", new Object[]{gcmExtras}));
        Bundle appboyExtras = AppboyNotificationUtils.getAppboyExtrasWithoutPreprocessing(gcmExtras);
        gcmExtras.putBundle("extra", appboyExtras);
        if (AppboyNotificationUtils.isNotificationMessage(intent)) {
            AppboyLogger.m1733d(TAG, "Received notification push");
            int notificationId = AppboyNotificationUtils.getNotificationId(gcmExtras);
            gcmExtras.putInt("nid", notificationId);
            Notification notification = AppboyNotificationUtils.getActiveNotificationFactory().createNotification(new AppboyConfigurationProvider(context), context, gcmExtras, appboyExtras);
            if (notification == null) {
                AppboyLogger.m1733d(TAG, "Notification created by notification factory was null. Not displaying notification.");
                return false;
            }
            notificationManager.notify("appboy_notification", notificationId, notification);
            AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, gcmExtras);
            AppboyNotificationUtils.wakeScreenIfHasPermission(context, gcmExtras);
            if (gcmExtras != null && gcmExtras.containsKey("nd")) {
                Context context2 = context;
                AppboyNotificationUtils.setNotificationDurationAlarm(context2, getClass(), notificationId, Integer.parseInt(gcmExtras.getString("nd")));
            }
            return true;
        }
        AppboyLogger.m1733d(TAG, "Received data push");
        AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, gcmExtras);
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void handleAppboyGcmReceiveIntent(Context context, Intent intent) {
        if (AppboyNotificationUtils.isAppboyPushMessage(intent)) {
            new HandleAppboyGcmMessageTask(context, intent);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean handleRegistrationEventIfEnabled(AppboyConfigurationProvider appConfigurationProvider, Context context, Intent intent) {
        if (!appConfigurationProvider.isGcmMessagingRegistrationEnabled()) {
            return false;
        }
        handleRegistrationIntent(context, intent);
        return true;
    }
}
