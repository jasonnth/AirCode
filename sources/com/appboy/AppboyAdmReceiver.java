package com.appboy;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.push.AppboyNotificationActionUtils;
import com.appboy.push.AppboyNotificationUtils;
import com.appboy.support.AppboyLogger;

public final class AppboyAdmReceiver extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyAdmReceiver.class.getName()});

    public class HandleAppboyAdmMessageTask extends AsyncTask<Void, Void, Void> {
        private final Context mContext;
        private final Intent mIntent;

        public HandleAppboyAdmMessageTask(Context context, Intent intent) {
            this.mContext = context;
            this.mIntent = intent;
            execute(new Void[0]);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voids) {
            try {
                AppboyAdmReceiver.this.handleAppboyAdmMessage(this.mContext, this.mIntent);
            } catch (Exception e) {
                AppboyLogger.m1736e(AppboyAdmReceiver.TAG, "Failed to create and display notification.", e);
            }
            return null;
        }
    }

    public void onReceive(Context context, Intent intent) {
        AppboyLogger.m1737i(TAG, String.format("Received broadcast message. Message: %s", new Object[]{intent.toString()}));
        String action = intent.getAction();
        if ("com.amazon.device.messaging.intent.REGISTRATION".equals(action)) {
            handleRegistrationEventIfEnabled(new AppboyConfigurationProvider(context), context, intent);
        } else if ("com.amazon.device.messaging.intent.RECEIVE".equals(action)) {
            handleAppboyAdmReceiveIntent(context, intent);
        } else if ("com.appboy.action.CANCEL_NOTIFICATION".equals(action)) {
            AppboyNotificationUtils.handleCancelNotificationAction(context, intent);
        } else if ("com.appboy.action.APPBOY_ACTION_CLICKED".equals(action)) {
            AppboyNotificationActionUtils.handleNotificationActionClicked(context, intent);
        } else if ("com.appboy.action.APPBOY_PUSH_CLICKED".equals(action)) {
            AppboyNotificationUtils.handleNotificationOpened(context, intent);
        } else {
            AppboyLogger.m1739w(TAG, "The ADM receiver received a message not sent from Appboy. Ignoring the message.");
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean handleRegistrationIntent(Context context, Intent intent) {
        String error = intent.getStringExtra("error");
        String registrationId = intent.getStringExtra("registration_id");
        String unregistered = intent.getStringExtra("unregistered");
        if (error != null) {
            AppboyLogger.m1735e(TAG, "Error during ADM registration: " + error);
        } else if (registrationId != null) {
            AppboyLogger.m1737i(TAG, "Registering for ADM messages with registrationId: " + registrationId);
            Appboy.getInstance(context).registerAppboyPushMessages(registrationId);
        } else if (unregistered != null) {
            AppboyLogger.m1737i(TAG, "Unregistering from ADM: " + unregistered);
            Appboy.getInstance(context).unregisterAppboyPushMessages();
        } else {
            AppboyLogger.m1739w(TAG, "The ADM registration intent is missing error information, registration id, and unregistration confirmation. Ignoring.");
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean handleAppboyAdmMessage(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if ("deleted_messages".equals(intent.getStringExtra("message_type"))) {
            int totalDeleted = intent.getIntExtra("total_deleted", -1);
            if (totalDeleted == -1) {
                AppboyLogger.m1735e(TAG, String.format("Unable to parse ADM message. Intent: %s", new Object[]{intent.toString()}));
            } else {
                AppboyLogger.m1737i(TAG, String.format("ADM deleted %d messages. Fetch them from Appboy.", new Object[]{Integer.valueOf(totalDeleted)}));
            }
            return false;
        }
        Bundle admExtras = intent.getExtras();
        AppboyLogger.m1733d(TAG, String.format("Push message payload received: %s", new Object[]{admExtras}));
        Bundle appboyExtras = AppboyNotificationUtils.getAppboyExtrasWithoutPreprocessing(admExtras);
        admExtras.putBundle("extra", appboyExtras);
        if (AppboyNotificationUtils.isNotificationMessage(intent)) {
            int notificationId = AppboyNotificationUtils.getNotificationId(admExtras);
            admExtras.putInt("nid", notificationId);
            Notification notification = AppboyNotificationUtils.getActiveNotificationFactory().createNotification(new AppboyConfigurationProvider(context), context, admExtras, appboyExtras);
            if (notification == null) {
                AppboyLogger.m1733d(TAG, "Notification created by notification factory was null. Not displaying notification.");
                return false;
            }
            notificationManager.notify("appboy_notification", notificationId, notification);
            AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, admExtras);
            AppboyNotificationUtils.wakeScreenIfHasPermission(context, admExtras);
            if (admExtras.containsKey("nd")) {
                Context context2 = context;
                AppboyNotificationUtils.setNotificationDurationAlarm(context2, getClass(), notificationId, Integer.parseInt(admExtras.getString("nd")));
            }
            return true;
        }
        AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, admExtras);
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void handleAppboyAdmReceiveIntent(Context context, Intent intent) {
        if (AppboyNotificationUtils.isAppboyPushMessage(intent)) {
            new HandleAppboyAdmMessageTask(context, intent);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean handleRegistrationEventIfEnabled(AppboyConfigurationProvider appConfigurationProvider, Context context, Intent intent) {
        AppboyLogger.m1737i(TAG, String.format("Received ADM registration. Message: %s", new Object[]{intent.toString()}));
        if (appConfigurationProvider.isAdmMessagingRegistrationEnabled()) {
            AppboyLogger.m1733d(TAG, "ADM enabled in appboy.xml. Continuing to process ADM registration intent.");
            handleRegistrationIntent(context, intent);
            return true;
        }
        AppboyLogger.m1739w(TAG, "ADM not enabled in appboy.xml. Ignoring ADM registration intent. Note: you must set com_appboy_push_adm_messaging_registration_enabled to true in your appboy.xml to enable ADM.");
        return false;
    }
}
