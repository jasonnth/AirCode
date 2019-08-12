package com.appboy.push;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.NotificationCompat.Action;
import android.support.p000v4.app.NotificationCompat.Builder;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyImageUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.IntentUtils;
import com.appboy.support.PermissionUtils;
import com.appboy.support.StringUtils;

public class AppboyNotificationActionUtils {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyNotificationActionUtils.class.getName()});

    private static class ShareTask extends AsyncTask<Intent, Integer, Intent> {
        private final Context mContext;

        public ShareTask(Context context) {
            this.mContext = context;
        }

        /* access modifiers changed from: protected */
        public Intent doInBackground(Intent... intents) {
            if (this.mContext != null) {
                return AppboyNotificationActionUtils.createShareActionIntent(this.mContext, intents[0]);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Intent shareIntent) {
            if (this.mContext == null) {
                return;
            }
            if (shareIntent != null) {
                this.mContext.startActivity(shareIntent);
            } else {
                AppboyLogger.m1739w(AppboyNotificationActionUtils.TAG, "Null share intent received. Not starting share intent.");
            }
        }
    }

    @TargetApi(16)
    public static void addNotificationActions(Context context, Builder notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras == null) {
            try {
                AppboyLogger.m1739w(TAG, "Notification extras were null. Doing nothing.");
            } catch (Exception e) {
                AppboyLogger.m1736e(TAG, "Caught exception while adding notification action buttons.", e);
            }
        } else if (VERSION.SDK_INT >= 16) {
            for (int actionIndex = 0; !StringUtils.isNullOrBlank(getActionFieldAtIndex(actionIndex, notificationExtras, "ab_a*_a")); actionIndex++) {
                addNotificationAction(context, notificationBuilder, notificationExtras, actionIndex);
            }
        }
    }

    @TargetApi(16)
    public static void handleNotificationActionClicked(Context context, Intent intent) {
        try {
            String actionType = intent.getStringExtra("appboy_action_type");
            if (StringUtils.isNullOrBlank(actionType)) {
                AppboyLogger.m1739w(TAG, "Notification action button type was blank or null. Doing nothing.");
                return;
            }
            int notificationId = intent.getIntExtra("nid", -1);
            if (!actionType.equals("ab_none")) {
                logNotificationActionClicked(context, intent);
            }
            if (actionType.equals("ab_uri") || actionType.equals("ab_open")) {
                AppboyNotificationUtils.cancelNotification(context, notificationId);
                context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                if (!actionType.equals("ab_uri") || !intent.getExtras().containsKey("appboy_action_uri")) {
                    intent.removeExtra("uri");
                } else {
                    intent.putExtra("uri", intent.getStringExtra("appboy_action_uri"));
                }
                AppboyNotificationUtils.sendNotificationOpenedBroadcast(context, intent);
                if (new AppboyConfigurationProvider(context).getHandlePushDeepLinksAutomatically()) {
                    AppboyNotificationUtils.routeUserWithNotificationOpenedIntent(context, intent);
                }
            } else if (actionType.equals("ab_share")) {
                AppboyNotificationUtils.cancelNotification(context, notificationId);
                context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                handleShareActionClicked(context, intent);
            } else if (actionType.equals("ab_none")) {
                AppboyNotificationUtils.cancelNotification(context, notificationId);
            } else {
                AppboyLogger.m1737i(TAG, "Custom notification action button clicked. Doing nothing and passing on data to client receiver.");
                AppboyNotificationUtils.sendNotificationOpenedBroadcast(context, intent);
            }
        } catch (Exception e) {
            AppboyLogger.m1736e(TAG, "Caught exception while handling notification action button click.", e);
        }
    }

    static boolean canShareImage(Context context, Bundle appboyExtras) {
        return appboyExtras != null && appboyExtras.containsKey("appboy_image_url") && PermissionUtils.hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    static boolean isCustomActionType(String actionType) {
        if (!actionType.equals("ab_uri") && !actionType.equals("ab_open") && !actionType.equals("ab_none") && !actionType.equals("ab_share")) {
            return true;
        }
        return false;
    }

    @TargetApi(16)
    private static void addNotificationAction(Context context, Builder notificationBuilder, Bundle notificationExtras, int actionIndex) {
        Bundle notificationActionExtras = new Bundle(notificationExtras);
        String actionType = getActionFieldAtIndex(actionIndex, notificationExtras, "ab_a*_a");
        notificationActionExtras.putInt("appboy_action_index", actionIndex);
        notificationActionExtras.putString("appboy_action_type", actionType);
        notificationActionExtras.putString("appboy_action_id", getActionFieldAtIndex(actionIndex, notificationExtras, "ab_a*_id"));
        notificationActionExtras.putString("appboy_action_uri", getActionFieldAtIndex(actionIndex, notificationExtras, "ab_a*_uri"));
        notificationActionExtras.putBoolean("appboy_is_custom_action", isCustomActionType(actionType));
        Intent sendIntent = new Intent("com.appboy.action.APPBOY_ACTION_CLICKED").setClass(context, AppboyNotificationUtils.getNotificationReceiverClass());
        sendIntent.putExtras(notificationActionExtras);
        Action.Builder notificationActionBuilder = new Action.Builder(0, getActionFieldAtIndex(actionIndex, notificationExtras, "ab_a*_t"), PendingIntent.getBroadcast(context, IntentUtils.getRequestCode(), sendIntent, 134217728));
        notificationActionBuilder.addExtras(new Bundle(notificationActionExtras));
        notificationBuilder.addAction(notificationActionBuilder.build());
    }

    private static void logNotificationActionClicked(Context context, Intent intent) {
        String campaignId = intent.getStringExtra("cid");
        String actionButtonId = intent.getStringExtra("appboy_action_id");
        if (StringUtils.isNullOrBlank(campaignId)) {
            AppboyLogger.m1737i(TAG, "No campaign Id associated with this notification. Not logging push action click to Appboy.");
        } else if (StringUtils.isNullOrBlank(actionButtonId)) {
            AppboyLogger.m1737i(TAG, "No action button Id associated with this notification action. Not logging push action click to Appboy.");
        } else {
            AppboyLogger.m1737i(TAG, "Logging push action click to Appboy. Campaign Id: " + campaignId + " Action Button Id: " + actionButtonId);
            Appboy.getInstance(context).logPushNotificationActionClicked(campaignId, actionButtonId);
        }
    }

    private static void handleShareActionClicked(Context context, Intent intent) {
        new ShareTask(context).execute(new Intent[]{intent});
    }

    /* access modifiers changed from: private */
    public static Intent createShareActionIntent(Context context, Intent intent) {
        Bundle notificationExtras = intent.getExtras();
        Intent shareIntent = new Intent("android.intent.action.SEND");
        shareIntent.putExtra("android.intent.extra.SUBJECT", notificationExtras.getString("t"));
        shareIntent.putExtra("android.intent.extra.TEXT", notificationExtras.getString("a"));
        Bundle appboyExtras = notificationExtras.getBundle("extra");
        if (canShareImage(context, appboyExtras)) {
            String fileName = Long.toString(System.currentTimeMillis());
            Uri localImageUri = AppboyImageUtils.storePushBitmapInExternalStorage(context.getApplicationContext(), AppboyImageUtils.getBitmap(Uri.parse(appboyExtras.getString("appboy_image_url"))), fileName, "Shared Photos");
            shareIntent.setType("image/*");
            shareIntent.putExtra("android.intent.extra.STREAM", localImageUri);
            shareIntent.addFlags(1);
        } else {
            shareIntent.setType("text/plain");
        }
        shareIntent.setFlags(268435456);
        return shareIntent;
    }

    static String getActionFieldAtIndex(int actionIndex, Bundle notificationExtras, String actionFieldKeyTemplate) {
        String actionFieldValue = notificationExtras.getString(actionFieldKeyTemplate.replace("*", String.valueOf(actionIndex)));
        if (actionFieldValue == null) {
            return "";
        }
        return actionFieldValue;
    }
}
