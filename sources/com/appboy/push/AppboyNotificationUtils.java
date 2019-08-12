package com.appboy.push;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.support.p000v4.app.NotificationCompat;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.support.p000v4.app.TaskStackBuilder;
import android.util.Log;
import com.appboy.Appboy;
import com.appboy.AppboyAdmReceiver;
import com.appboy.AppboyGcmReceiver;
import com.appboy.Constants;
import com.appboy.IAppboyNotificationFactory;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyImageUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.IntentUtils;
import com.appboy.support.PermissionUtils;
import com.appboy.support.StringUtils;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class AppboyNotificationUtils {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyNotificationUtils.class.getName()});

    public static void handleNotificationOpened(Context context, Intent intent) {
        try {
            logNotificationOpened(context, intent);
            sendNotificationOpenedBroadcast(context, intent);
            if (new AppboyConfigurationProvider(context).getHandlePushDeepLinksAutomatically()) {
                routeUserWithNotificationOpenedIntent(context, intent);
            }
        } catch (Exception e) {
            AppboyLogger.m1736e(TAG, "Exception occurred attempting to handle notification.", e);
        }
    }

    public static void routeUserWithNotificationOpenedIntent(Context context, Intent intent) {
        Bundle extras = intent.getBundleExtra("extra");
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString("cid", intent.getStringExtra("cid"));
        extras.putString("source", "Appboy");
        Intent startActivityIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        startActivityIntent.setFlags(872415232);
        if (extras != null) {
            startActivityIntent.putExtras(extras);
        }
        String deepLink = intent.getStringExtra("uri");
        if (!StringUtils.isNullOrBlank(deepLink)) {
            Log.d(TAG, String.format("Found a deep link %s.", new Object[]{deepLink}));
            Intent uriIntent = new Intent("android.intent.action.VIEW", Uri.parse(deepLink)).putExtras(extras);
            List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(uriIntent, 0);
            if (resolveInfos.size() > 1) {
                Iterator it = resolveInfos.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ResolveInfo resolveInfo = (ResolveInfo) it.next();
                    if (resolveInfo.activityInfo.packageName.equals(context.getPackageName())) {
                        uriIntent.setPackage(resolveInfo.activityInfo.packageName);
                        break;
                    }
                }
            }
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntent(startActivityIntent);
            stackBuilder.addNextIntent(uriIntent);
            try {
                stackBuilder.startActivities(extras);
            } catch (ActivityNotFoundException e) {
                Log.w(TAG, String.format("Could not find appropriate activity to open for deep link %s.", new Object[]{deepLink}));
            }
        } else {
            Log.d(TAG, "Push notification had no deep link. Opening main activity.");
            context.startActivity(startActivityIntent);
        }
    }

    public static Bundle getAppboyExtrasWithoutPreprocessing(Bundle notificationExtras) {
        if (notificationExtras == null) {
            return null;
        }
        if (!Constants.IS_AMAZON.booleanValue()) {
            return parseJSONStringDictionaryIntoBundle(bundleOptString(notificationExtras, "extra", "{}"));
        }
        return new Bundle(notificationExtras);
    }

    @TargetApi(12)
    public static String bundleOptString(Bundle bundle, String key, String defaultValue) {
        if (VERSION.SDK_INT >= 12) {
            return bundle.getString(key, defaultValue);
        }
        String result = bundle.getString(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    public static Bundle parseJSONStringDictionaryIntoBundle(String jsonStringDictionary) {
        try {
            Bundle bundle = new Bundle();
            JSONObject json = new JSONObject(jsonStringDictionary);
            Iterator keys = json.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                bundle.putString(key, json.getString(key));
            }
            return bundle;
        } catch (JSONException e) {
            AppboyLogger.m1736e(TAG, "Unable parse JSON into a bundle.", e);
            return null;
        }
    }

    public static boolean isAppboyPushMessage(Intent intent) {
        Bundle extras = intent.getExtras();
        return extras != null && "true".equals(extras.getString("_ab"));
    }

    public static boolean isNotificationMessage(Intent intent) {
        Bundle extras = intent.getExtras();
        return extras != null && extras.containsKey("t") && extras.containsKey("a");
    }

    public static void sendPushMessageReceivedBroadcast(Context context, Bundle notificationExtras) {
        Intent pushReceivedIntent = new Intent(context.getPackageName() + ".intent.APPBOY_PUSH_RECEIVED");
        if (notificationExtras != null) {
            pushReceivedIntent.putExtras(notificationExtras);
        }
        AppboyLogger.m1733d(TAG, "Sending push message received broadcast");
        context.sendBroadcast(pushReceivedIntent);
    }

    public static void setNotificationDurationAlarm(Context context, Class<?> thisClass, int notificationId, int durationInMillis) {
        Intent cancelIntent = new Intent(context, thisClass);
        cancelIntent.setAction("com.appboy.action.CANCEL_NOTIFICATION");
        cancelIntent.putExtra("nid", notificationId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, cancelIntent, 134217728);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (durationInMillis >= 1000) {
            AppboyLogger.m1733d(TAG, String.format("Setting Notification duration alarm for %d ms", new Object[]{Integer.valueOf(durationInMillis)}));
            alarmManager.set(3, SystemClock.elapsedRealtime() + ((long) durationInMillis), pendingIntent);
        }
    }

    public static int getNotificationId(Bundle notificationExtras) {
        if (notificationExtras == null) {
            AppboyLogger.m1733d(TAG, String.format("Message without extras bundle received. Using default notification id: -1", new Object[0]));
            return -1;
        } else if (notificationExtras.containsKey("n")) {
            try {
                int notificationId = Integer.parseInt(notificationExtras.getString("n"));
                AppboyLogger.m1733d(TAG, "Using notification id provided in the message's extras bundle: " + notificationId);
                return notificationId;
            } catch (NumberFormatException e) {
                AppboyLogger.m1736e(TAG, "Unable to parse notification id provided in the message's extras bundle. Using default notification id instead: -1", e);
                return -1;
            }
        } else {
            int notificationId2 = (bundleOptString(notificationExtras, "t", "") + bundleOptString(notificationExtras, "a", "")).hashCode();
            AppboyLogger.m1733d(TAG, "Message without notification id provided in the extras bundle received. Using a hash of the message: " + notificationId2);
            return notificationId2;
        }
    }

    @TargetApi(16)
    public static int getNotificationPriority(Bundle notificationExtras) {
        if (notificationExtras != null && notificationExtras.containsKey("p")) {
            try {
                int notificationPriority = Integer.parseInt(notificationExtras.getString("p"));
                if (isValidNotificationPriority(notificationPriority)) {
                    return notificationPriority;
                }
                AppboyLogger.m1735e(TAG, String.format("Received invalid notification priority %d", new Object[]{Integer.valueOf(notificationPriority)}));
            } catch (NumberFormatException e) {
                AppboyLogger.m1736e(TAG, "Unable to parse custom priority. Returning default priority of 0", e);
            }
        }
        return 0;
    }

    @TargetApi(16)
    public static boolean isValidNotificationPriority(int priority) {
        return priority >= -2 && priority <= 2;
    }

    public static boolean wakeScreenIfHasPermission(Context context, Bundle notificationExtras) {
        if (!PermissionUtils.hasPermission(context, "android.permission.WAKE_LOCK")) {
            return false;
        }
        if (VERSION.SDK_INT >= 16 && getNotificationPriority(notificationExtras) == -2) {
            return false;
        }
        WakeLock wakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(268435482, TAG);
        wakeLock.acquire();
        wakeLock.release();
        return true;
    }

    public static IAppboyNotificationFactory getActiveNotificationFactory() {
        IAppboyNotificationFactory customAppboyNotificationFactory = Appboy.getCustomAppboyNotificationFactory();
        if (customAppboyNotificationFactory == null) {
            return AppboyNotificationFactory.getInstance();
        }
        return customAppboyNotificationFactory;
    }

    public static void setTitleIfPresent(Builder notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras != null) {
            AppboyLogger.m1733d(TAG, "Setting title for notification");
            notificationBuilder.setContentTitle(notificationExtras.getString("t"));
        }
    }

    public static void setContentIfPresent(Builder notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras != null) {
            AppboyLogger.m1733d(TAG, "Setting content for notification");
            notificationBuilder.setContentText(notificationExtras.getString("a"));
        }
    }

    public static void setTickerIfPresent(Builder notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras != null) {
            AppboyLogger.m1733d(TAG, "Setting ticker for notification");
            notificationBuilder.setTicker(notificationExtras.getString("t"));
        }
    }

    public static void setContentIntentIfPresent(Context context, Builder notificationBuilder, Bundle notificationExtras) {
        try {
            Intent pushOpenedIntent = new Intent("com.appboy.action.APPBOY_PUSH_CLICKED").setClass(context, getNotificationReceiverClass());
            if (notificationExtras != null) {
                pushOpenedIntent.putExtras(notificationExtras);
            }
            notificationBuilder.setContentIntent(PendingIntent.getBroadcast(context, IntentUtils.getRequestCode(), pushOpenedIntent, 1073741824));
        } catch (Exception e) {
            AppboyLogger.m1736e(TAG, "Error setting content.", e);
        }
    }

    public static int setSmallIcon(AppboyConfigurationProvider appConfigurationProvider, Builder notificationBuilder) {
        int smallNotificationIconResourceId = appConfigurationProvider.getSmallNotificationIconResourceId();
        if (smallNotificationIconResourceId == 0) {
            AppboyLogger.m1733d(TAG, "Small notification icon resource was not found. Will use the app icon when displaying notifications.");
            smallNotificationIconResourceId = appConfigurationProvider.getApplicationIconResourceId();
        } else {
            AppboyLogger.m1733d(TAG, "Setting small icon for notification via resource id");
        }
        notificationBuilder.setSmallIcon(smallNotificationIconResourceId);
        return smallNotificationIconResourceId;
    }

    public static boolean setLargeIconIfPresentAndSupported(Context context, AppboyConfigurationProvider appConfigurationProvider, Builder notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT < 11) {
            AppboyLogger.m1733d(TAG, "Setting large icon for notification not supported on this android version");
            return false;
        }
        if (notificationExtras != null) {
            try {
                if (notificationExtras.containsKey("ab_li")) {
                    AppboyLogger.m1733d(TAG, "Setting large icon for notification");
                    notificationBuilder.setLargeIcon(AppboyImageUtils.getBitmap(Uri.parse(notificationExtras.getString("ab_li"))));
                    return true;
                }
            } catch (Exception e) {
                AppboyLogger.m1736e(TAG, "Error setting large notification icon", e);
            }
        }
        AppboyLogger.m1733d(TAG, "Large icon bitmap url not present in extras. Attempting to use resource id instead.");
        int largeNotificationIconResourceId = appConfigurationProvider.getLargeNotificationIconResourceId();
        if (largeNotificationIconResourceId != 0) {
            notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeNotificationIconResourceId));
            return true;
        }
        AppboyLogger.m1733d(TAG, "Large icon resource id not present for notification");
        AppboyLogger.m1733d(TAG, "Large icon not set for notification");
        return false;
    }

    public static void setSummaryTextIfPresentAndSupported(Builder notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT < 16) {
            return;
        }
        if (notificationExtras == null || !notificationExtras.containsKey("s")) {
            AppboyLogger.m1733d(TAG, "Summary text not present in notification extras. Not setting summary text for notification.");
            return;
        }
        String summaryText = notificationExtras.getString("s");
        if (summaryText != null) {
            AppboyLogger.m1733d(TAG, "Setting summary text for notification");
            notificationBuilder.setSubText(summaryText);
        }
    }

    public static void setPriorityIfPresentAndSupported(Builder notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT >= 16 && notificationExtras != null) {
            AppboyLogger.m1733d(TAG, "Setting priority for notification");
            notificationBuilder.setPriority(getNotificationPriority(notificationExtras));
        }
    }

    public static void setStyleIfSupported(Context context, Builder notificationBuilder, Bundle notificationExtras, Bundle appboyExtras) {
        if (VERSION.SDK_INT >= 16 && notificationExtras != null) {
            AppboyLogger.m1733d(TAG, "Setting style for notification");
            notificationBuilder.setStyle(AppboyNotificationStyleFactory.getBigNotificationStyle(context, notificationExtras, appboyExtras));
        }
    }

    public static void setAccentColorIfPresentAndSupported(AppboyConfigurationProvider appConfigurationProvider, Builder notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT < 21) {
            return;
        }
        if (notificationExtras == null || !notificationExtras.containsKey("ac")) {
            AppboyLogger.m1733d(TAG, "Using default accent color for notification");
            notificationBuilder.setColor(appConfigurationProvider.getDefaultNotificationAccentColor());
            return;
        }
        AppboyLogger.m1733d(TAG, "Using accent color for notification from extras bundle");
        notificationBuilder.setColor((int) Long.parseLong(notificationExtras.getString("ac")));
    }

    public static void setCategoryIfPresentAndSupported(Builder notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT < 21) {
            AppboyLogger.m1733d(TAG, "Notification category not supported on this android version. Not setting category for notification.");
        } else if (notificationExtras == null || !notificationExtras.containsKey("ab_ct")) {
            AppboyLogger.m1733d(TAG, "Category not present in notification extras. Not setting category for notification.");
        } else {
            AppboyLogger.m1733d(TAG, "Setting category for notification");
            notificationBuilder.setCategory(notificationExtras.getString("ab_ct"));
        }
    }

    public static void setVisibilityIfPresentAndSupported(Builder notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT < 21) {
            AppboyLogger.m1733d(TAG, "Notification visibility not supported on this android version. Not setting visibility for notification.");
        } else if (notificationExtras != null && notificationExtras.containsKey("ab_vs")) {
            try {
                int visibility = Integer.parseInt(notificationExtras.getString("ab_vs"));
                if (isValidNotificationVisibility(visibility)) {
                    AppboyLogger.m1733d(TAG, "Setting visibility for notification");
                    notificationBuilder.setVisibility(visibility);
                    return;
                }
                AppboyLogger.m1735e(TAG, String.format("Received invalid notification visibility %d", new Object[]{Integer.valueOf(visibility)}));
            } catch (Exception e) {
                AppboyLogger.m1736e(TAG, "Failed to parse visibility from notificationExtras", e);
            }
        }
    }

    public static void setPublicVersionIfPresentAndSupported(Context context, AppboyConfigurationProvider appboyConfigurationProvider, Builder notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT >= 21 && notificationExtras != null && notificationExtras.containsKey("ab_pn")) {
            Bundle publicNotificationExtras = parseJSONStringDictionaryIntoBundle(notificationExtras.getString("ab_pn"));
            Builder publicNotificationBuilder = new Builder(context);
            setContentIfPresent(publicNotificationBuilder, publicNotificationExtras);
            setTitleIfPresent(publicNotificationBuilder, publicNotificationExtras);
            setSummaryTextIfPresentAndSupported(publicNotificationBuilder, publicNotificationExtras);
            setSmallIcon(appboyConfigurationProvider, publicNotificationBuilder);
            setAccentColorIfPresentAndSupported(appboyConfigurationProvider, publicNotificationBuilder, publicNotificationExtras);
            notificationBuilder.setPublicVersion(publicNotificationBuilder.build());
        }
    }

    @TargetApi(21)
    public static boolean isValidNotificationVisibility(int visibility) {
        return visibility == -1 || visibility == 0 || visibility == 1;
    }

    public static void handleCancelNotificationAction(Context context, Intent intent) {
        try {
            if (intent.hasExtra("nid")) {
                int notificationId = intent.getIntExtra("nid", -1);
                AppboyLogger.m1733d(TAG, String.format("Cancelling notification action with id: %d", new Object[]{Integer.valueOf(notificationId)}));
                ((NotificationManager) context.getSystemService("notification")).cancel("appboy_notification", notificationId);
            }
        } catch (Exception e) {
            AppboyLogger.m1736e(TAG, "Exception occurred handling cancel notification intent.", e);
        }
    }

    public static void cancelNotification(Context context, int notificationId) {
        try {
            AppboyLogger.m1733d(TAG, String.format("Cancelling notification action with id: %d", new Object[]{Integer.valueOf(notificationId)}));
            Intent cancelNotificationIntent = new Intent("com.appboy.action.CANCEL_NOTIFICATION").setClass(context, getNotificationReceiverClass());
            cancelNotificationIntent.putExtra("nid", notificationId);
            context.sendBroadcast(cancelNotificationIntent);
        } catch (Exception e) {
            AppboyLogger.m1736e(TAG, "Exception occurred attempting to cancel notification.", e);
        }
    }

    public static Class<?> getNotificationReceiverClass() {
        if (Constants.IS_AMAZON.booleanValue()) {
            return AppboyAdmReceiver.class;
        }
        return AppboyGcmReceiver.class;
    }

    static String getOptionalStringResource(Resources resources, int stringResourceId, String defaultString) {
        try {
            return resources.getString(stringResourceId);
        } catch (NotFoundException e) {
            return defaultString;
        }
    }

    static void sendNotificationOpenedBroadcast(Context context, Intent intent) {
        AppboyLogger.m1733d(TAG, "Sending notification opened broadcast");
        Intent pushOpenedIntent = new Intent(context.getPackageName() + ".intent.APPBOY_NOTIFICATION_OPENED");
        if (intent.getExtras() != null) {
            pushOpenedIntent.putExtras(intent.getExtras());
        }
        context.sendBroadcast(pushOpenedIntent);
    }

    private static void logNotificationOpened(Context context, Intent intent) {
        Appboy.getInstance(context).logPushNotificationOpened(intent);
    }
}
