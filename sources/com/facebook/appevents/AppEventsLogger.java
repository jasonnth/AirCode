package com.facebook.appevents;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import bolts.AppLinks;
import com.airbnb.android.airdate.AirDateConstants;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppEventsLogger {
    public static final String ACTION_APP_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_FLUSHED";
    public static final String APP_EVENTS_EXTRA_FLUSH_RESULT = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
    public static final String APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
    private static final String APP_EVENT_NAME_PUSH_OPENED = "fb_mobile_push_opened";
    public static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final String APP_EVENT_PUSH_PARAMETER_ACTION = "fb_push_action";
    private static final String APP_EVENT_PUSH_PARAMETER_CAMPAIGN = "fb_push_campaign";
    private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;
    private static final int FLUSH_APP_SESSION_INFO_IN_SECONDS = 30;
    private static final String PUSH_PAYLOAD_CAMPAIGN_KEY = "campaign";
    private static final String PUSH_PAYLOAD_KEY = "fb_push_payload";
    private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
    /* access modifiers changed from: private */
    public static final String TAG = AppEventsLogger.class.getCanonicalName();
    private static String anonymousAppDeviceGUID;
    /* access modifiers changed from: private */
    public static ScheduledThreadPoolExecutor backgroundExecutor;
    private static String externalAnalyticsUserID;
    private static FlushBehavior flushBehavior = FlushBehavior.AUTO;
    private static boolean isActivateAppEventRequested;
    private static boolean isOpenedByApplink;
    private static String pushNotificationsRegistrationId;
    private static String sourceApplication;
    private static Object staticLock = new Object();
    private final AccessTokenAppIdPair accessTokenAppId;
    private final String contextName;

    public enum FlushBehavior {
        AUTO,
        EXPLICIT_ONLY
    }

    static class PersistedAppSessionInfo {
        private static final String PERSISTED_SESSION_INFO_FILENAME = "AppEventsLogger.persistedsessioninfo";
        private static final Runnable appSessionInfoFlushRunnable = new Runnable() {
            public void run() {
                PersistedAppSessionInfo.saveAppSessionInformation(FacebookSdk.getApplicationContext());
            }
        };
        private static Map<AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap;
        private static boolean hasChanges = false;
        private static boolean isLoaded = false;
        private static final Object staticLock = new Object();

        PersistedAppSessionInfo() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:21:0x0052 A[Catch:{ FileNotFoundException -> 0x0044, Exception -> 0x0063, all -> 0x0060 }] */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0090 A[Catch:{ FileNotFoundException -> 0x0044, Exception -> 0x0063, all -> 0x0060 }] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00ac A[Catch:{ FileNotFoundException -> 0x0044, Exception -> 0x0063, all -> 0x0060 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static void restoreAppSessionInformation(android.content.Context r7) {
            /*
                r1 = 0
                java.lang.Object r4 = staticLock
                monitor-enter(r4)
                boolean r3 = isLoaded     // Catch:{ all -> 0x0060 }
                if (r3 != 0) goto L_0x0042
                java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ FileNotFoundException -> 0x0044, Exception -> 0x0063 }
                java.lang.String r3 = "AppEventsLogger.persistedsessioninfo"
                java.io.FileInputStream r3 = r7.openFileInput(r3)     // Catch:{ FileNotFoundException -> 0x0044, Exception -> 0x0063 }
                r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0044, Exception -> 0x0063 }
                java.lang.Object r3 = r2.readObject()     // Catch:{ FileNotFoundException -> 0x00c3, Exception -> 0x00c0, all -> 0x00bd }
                java.util.HashMap r3 = (java.util.HashMap) r3     // Catch:{ FileNotFoundException -> 0x00c3, Exception -> 0x00c0, all -> 0x00bd }
                appSessionInfoMap = r3     // Catch:{ FileNotFoundException -> 0x00c3, Exception -> 0x00c0, all -> 0x00bd }
                com.facebook.LoggingBehavior r3 = com.facebook.LoggingBehavior.APP_EVENTS     // Catch:{ FileNotFoundException -> 0x00c3, Exception -> 0x00c0, all -> 0x00bd }
                java.lang.String r5 = "AppEvents"
                java.lang.String r6 = "App session info loaded"
                com.facebook.internal.Logger.log(r3, r5, r6)     // Catch:{ FileNotFoundException -> 0x00c3, Exception -> 0x00c0, all -> 0x00bd }
                com.facebook.internal.Utility.closeQuietly(r2)     // Catch:{ all -> 0x00ba }
                java.lang.String r3 = "AppEventsLogger.persistedsessioninfo"
                r7.deleteFile(r3)     // Catch:{ all -> 0x00ba }
                java.util.Map<com.facebook.appevents.AccessTokenAppIdPair, com.facebook.appevents.FacebookTimeSpentData> r3 = appSessionInfoMap     // Catch:{ all -> 0x00ba }
                if (r3 != 0) goto L_0x003b
                java.util.HashMap r3 = new java.util.HashMap     // Catch:{ all -> 0x00ba }
                r3.<init>()     // Catch:{ all -> 0x00ba }
                appSessionInfoMap = r3     // Catch:{ all -> 0x00ba }
            L_0x003b:
                r3 = 1
                isLoaded = r3     // Catch:{ all -> 0x00ba }
                r3 = 0
                hasChanges = r3     // Catch:{ all -> 0x00ba }
                r1 = r2
            L_0x0042:
                monitor-exit(r4)     // Catch:{ all -> 0x0060 }
                return
            L_0x0044:
                r3 = move-exception
            L_0x0045:
                com.facebook.internal.Utility.closeQuietly(r1)     // Catch:{ all -> 0x0060 }
                java.lang.String r3 = "AppEventsLogger.persistedsessioninfo"
                r7.deleteFile(r3)     // Catch:{ all -> 0x0060 }
                java.util.Map<com.facebook.appevents.AccessTokenAppIdPair, com.facebook.appevents.FacebookTimeSpentData> r3 = appSessionInfoMap     // Catch:{ all -> 0x0060 }
                if (r3 != 0) goto L_0x0059
                java.util.HashMap r3 = new java.util.HashMap     // Catch:{ all -> 0x0060 }
                r3.<init>()     // Catch:{ all -> 0x0060 }
                appSessionInfoMap = r3     // Catch:{ all -> 0x0060 }
            L_0x0059:
                r3 = 1
                isLoaded = r3     // Catch:{ all -> 0x0060 }
                r3 = 0
                hasChanges = r3     // Catch:{ all -> 0x0060 }
                goto L_0x0042
            L_0x0060:
                r3 = move-exception
            L_0x0061:
                monitor-exit(r4)     // Catch:{ all -> 0x0060 }
                throw r3
            L_0x0063:
                r0 = move-exception
            L_0x0064:
                java.lang.String r3 = com.facebook.appevents.AppEventsLogger.TAG     // Catch:{ all -> 0x009e }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x009e }
                r5.<init>()     // Catch:{ all -> 0x009e }
                java.lang.String r6 = "Got unexpected exception restoring app session info: "
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x009e }
                java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x009e }
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x009e }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x009e }
                android.util.Log.w(r3, r5)     // Catch:{ all -> 0x009e }
                com.facebook.internal.Utility.closeQuietly(r1)     // Catch:{ all -> 0x0060 }
                java.lang.String r3 = "AppEventsLogger.persistedsessioninfo"
                r7.deleteFile(r3)     // Catch:{ all -> 0x0060 }
                java.util.Map<com.facebook.appevents.AccessTokenAppIdPair, com.facebook.appevents.FacebookTimeSpentData> r3 = appSessionInfoMap     // Catch:{ all -> 0x0060 }
                if (r3 != 0) goto L_0x0097
                java.util.HashMap r3 = new java.util.HashMap     // Catch:{ all -> 0x0060 }
                r3.<init>()     // Catch:{ all -> 0x0060 }
                appSessionInfoMap = r3     // Catch:{ all -> 0x0060 }
            L_0x0097:
                r3 = 1
                isLoaded = r3     // Catch:{ all -> 0x0060 }
                r3 = 0
                hasChanges = r3     // Catch:{ all -> 0x0060 }
                goto L_0x0042
            L_0x009e:
                r3 = move-exception
            L_0x009f:
                com.facebook.internal.Utility.closeQuietly(r1)     // Catch:{ all -> 0x0060 }
                java.lang.String r5 = "AppEventsLogger.persistedsessioninfo"
                r7.deleteFile(r5)     // Catch:{ all -> 0x0060 }
                java.util.Map<com.facebook.appevents.AccessTokenAppIdPair, com.facebook.appevents.FacebookTimeSpentData> r5 = appSessionInfoMap     // Catch:{ all -> 0x0060 }
                if (r5 != 0) goto L_0x00b3
                java.util.HashMap r5 = new java.util.HashMap     // Catch:{ all -> 0x0060 }
                r5.<init>()     // Catch:{ all -> 0x0060 }
                appSessionInfoMap = r5     // Catch:{ all -> 0x0060 }
            L_0x00b3:
                r5 = 1
                isLoaded = r5     // Catch:{ all -> 0x0060 }
                r5 = 0
                hasChanges = r5     // Catch:{ all -> 0x0060 }
                throw r3     // Catch:{ all -> 0x0060 }
            L_0x00ba:
                r3 = move-exception
                r1 = r2
                goto L_0x0061
            L_0x00bd:
                r3 = move-exception
                r1 = r2
                goto L_0x009f
            L_0x00c0:
                r0 = move-exception
                r1 = r2
                goto L_0x0064
            L_0x00c3:
                r3 = move-exception
                r1 = r2
                goto L_0x0045
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.restoreAppSessionInformation(android.content.Context):void");
        }

        /* JADX WARNING: Unknown top exception splitter block from list: {B:12:0x0031=Splitter:B:12:0x0031, B:24:0x005b=Splitter:B:24:0x005b} */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static void saveAppSessionInformation(android.content.Context r7) {
            /*
                r1 = 0
                java.lang.Object r4 = staticLock
                monitor-enter(r4)
                boolean r3 = hasChanges     // Catch:{ all -> 0x0057 }
                if (r3 == 0) goto L_0x0031
                java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x0033 }
                java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x0033 }
                java.lang.String r5 = "AppEventsLogger.persistedsessioninfo"
                r6 = 0
                java.io.FileOutputStream r5 = r7.openFileOutput(r5, r6)     // Catch:{ Exception -> 0x0033 }
                r3.<init>(r5)     // Catch:{ Exception -> 0x0033 }
                r2.<init>(r3)     // Catch:{ Exception -> 0x0033 }
                java.util.Map<com.facebook.appevents.AccessTokenAppIdPair, com.facebook.appevents.FacebookTimeSpentData> r3 = appSessionInfoMap     // Catch:{ Exception -> 0x0065, all -> 0x0062 }
                r2.writeObject(r3)     // Catch:{ Exception -> 0x0065, all -> 0x0062 }
                r3 = 0
                hasChanges = r3     // Catch:{ Exception -> 0x0065, all -> 0x0062 }
                com.facebook.LoggingBehavior r3 = com.facebook.LoggingBehavior.APP_EVENTS     // Catch:{ Exception -> 0x0065, all -> 0x0062 }
                java.lang.String r5 = "AppEvents"
                java.lang.String r6 = "App session info saved"
                com.facebook.internal.Logger.log(r3, r5, r6)     // Catch:{ Exception -> 0x0065, all -> 0x0062 }
                com.facebook.internal.Utility.closeQuietly(r2)     // Catch:{ all -> 0x005f }
                r1 = r2
            L_0x0031:
                monitor-exit(r4)     // Catch:{ all -> 0x0057 }
                return
            L_0x0033:
                r0 = move-exception
            L_0x0034:
                java.lang.String r3 = com.facebook.appevents.AppEventsLogger.TAG     // Catch:{ all -> 0x005a }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x005a }
                r5.<init>()     // Catch:{ all -> 0x005a }
                java.lang.String r6 = "Got unexpected exception while writing app session info: "
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x005a }
                java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x005a }
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x005a }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x005a }
                android.util.Log.w(r3, r5)     // Catch:{ all -> 0x005a }
                com.facebook.internal.Utility.closeQuietly(r1)     // Catch:{ all -> 0x0057 }
                goto L_0x0031
            L_0x0057:
                r3 = move-exception
            L_0x0058:
                monitor-exit(r4)     // Catch:{ all -> 0x0057 }
                throw r3
            L_0x005a:
                r3 = move-exception
            L_0x005b:
                com.facebook.internal.Utility.closeQuietly(r1)     // Catch:{ all -> 0x0057 }
                throw r3     // Catch:{ all -> 0x0057 }
            L_0x005f:
                r3 = move-exception
                r1 = r2
                goto L_0x0058
            L_0x0062:
                r3 = move-exception
                r1 = r2
                goto L_0x005b
            L_0x0065:
                r0 = move-exception
                r1 = r2
                goto L_0x0034
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.saveAppSessionInformation(android.content.Context):void");
        }

        static void onResume(Context context, AccessTokenAppIdPair accessTokenAppId, AppEventsLogger logger, long eventTime, String sourceApplicationInfo) {
            synchronized (staticLock) {
                getTimeSpentData(context, accessTokenAppId).onResume(logger, eventTime, sourceApplicationInfo);
                onTimeSpentDataUpdate();
            }
        }

        static void onSuspend(Context context, AccessTokenAppIdPair accessTokenAppId, AppEventsLogger logger, long eventTime) {
            synchronized (staticLock) {
                getTimeSpentData(context, accessTokenAppId).onSuspend(logger, eventTime);
                onTimeSpentDataUpdate();
            }
        }

        private static FacebookTimeSpentData getTimeSpentData(Context context, AccessTokenAppIdPair accessTokenAppId) {
            restoreAppSessionInformation(context);
            FacebookTimeSpentData result = (FacebookTimeSpentData) appSessionInfoMap.get(accessTokenAppId);
            if (result != null) {
                return result;
            }
            FacebookTimeSpentData result2 = new FacebookTimeSpentData();
            appSessionInfoMap.put(accessTokenAppId, result2);
            return result2;
        }

        private static void onTimeSpentDataUpdate() {
            if (!hasChanges) {
                hasChanges = true;
                AppEventsLogger.backgroundExecutor.schedule(appSessionInfoFlushRunnable, 30, TimeUnit.SECONDS);
            }
        }
    }

    public static void activateApp(Application application) {
        activateApp(application, (String) null);
    }

    public static void activateApp(Application application, String applicationId) {
        if (!FacebookSdk.isInitialized()) {
            throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
        }
        AnalyticsUserIDStore.initStore();
        if (applicationId == null) {
            applicationId = FacebookSdk.getApplicationId();
        }
        FacebookSdk.publishInstallAsync(application, applicationId);
        ActivityLifecycleTracker.startTracking(application, applicationId);
    }

    @Deprecated
    public static void activateApp(Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
            return;
        }
        FacebookSdk.sdkInitialize(context);
        activateApp(context, Utility.getMetadataApplicationId(context));
    }

    @Deprecated
    public static void activateApp(Context context, String applicationId) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
        } else if (context == null || applicationId == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        } else {
            AnalyticsUserIDStore.initStore();
            if (context instanceof Activity) {
                setSourceApplication((Activity) context);
            } else {
                resetSourceApplication();
                Log.d(AppEventsLogger.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
            }
            FacebookSdk.publishInstallAsync(context, applicationId);
            AppEventsLogger logger = new AppEventsLogger(context, applicationId, (AccessToken) null);
            final long eventTime = System.currentTimeMillis();
            final String sourceApplicationInfo = getSourceApplication();
            backgroundExecutor.execute(new Runnable(logger) {
                final /* synthetic */ AppEventsLogger val$logger;

                {
                    this.val$logger = r1;
                }

                public void run() {
                    this.val$logger.logAppSessionResumeEvent(eventTime, sourceApplicationInfo);
                }
            });
        }
    }

    @Deprecated
    public static void deactivateApp(Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
        } else {
            deactivateApp(context, Utility.getMetadataApplicationId(context));
        }
    }

    @Deprecated
    public static void deactivateApp(Context context, String applicationId) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
        } else if (context == null || applicationId == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        } else {
            resetSourceApplication();
            AppEventsLogger logger = new AppEventsLogger(context, applicationId, (AccessToken) null);
            final long eventTime = System.currentTimeMillis();
            backgroundExecutor.execute(new Runnable(logger) {
                final /* synthetic */ AppEventsLogger val$logger;

                {
                    this.val$logger = r1;
                }

                public void run() {
                    this.val$logger.logAppSessionSuspendEvent(eventTime);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void logAppSessionResumeEvent(long eventTime, String sourceApplicationInfo) {
        PersistedAppSessionInfo.onResume(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, eventTime, sourceApplicationInfo);
    }

    /* access modifiers changed from: private */
    public void logAppSessionSuspendEvent(long eventTime) {
        PersistedAppSessionInfo.onSuspend(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, eventTime);
    }

    public static AppEventsLogger newLogger(Context context) {
        return new AppEventsLogger(context, (String) null, (AccessToken) null);
    }

    public static AppEventsLogger newLogger(Context context, AccessToken accessToken) {
        return new AppEventsLogger(context, (String) null, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String applicationId, AccessToken accessToken) {
        return new AppEventsLogger(context, applicationId, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String applicationId) {
        return new AppEventsLogger(context, applicationId, (AccessToken) null);
    }

    public static FlushBehavior getFlushBehavior() {
        FlushBehavior flushBehavior2;
        synchronized (staticLock) {
            flushBehavior2 = flushBehavior;
        }
        return flushBehavior2;
    }

    public static void setFlushBehavior(FlushBehavior flushBehavior2) {
        synchronized (staticLock) {
            flushBehavior = flushBehavior2;
        }
    }

    public void logEvent(String eventName) {
        logEvent(eventName, (Bundle) null);
    }

    public void logEvent(String eventName, double valueToSum) {
        logEvent(eventName, valueToSum, (Bundle) null);
    }

    public void logEvent(String eventName, Bundle parameters) {
        logEvent(eventName, null, parameters, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public void logEvent(String eventName, double valueToSum, Bundle parameters) {
        logEvent(eventName, Double.valueOf(valueToSum), parameters, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public void logPurchase(BigDecimal purchaseAmount, Currency currency) {
        logPurchase(purchaseAmount, currency, null);
    }

    public void logPurchase(BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        if (purchaseAmount == null) {
            notifyDeveloperError("purchaseAmount cannot be null");
        } else if (currency == null) {
            notifyDeveloperError("currency cannot be null");
        } else {
            if (parameters == null) {
                parameters = new Bundle();
            }
            parameters.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency.getCurrencyCode());
            logEvent(AppEventsConstants.EVENT_NAME_PURCHASED, purchaseAmount.doubleValue(), parameters);
            eagerFlush();
        }
    }

    public void logPushNotificationOpen(Bundle payload) {
        logPushNotificationOpen(payload, null);
    }

    public void logPushNotificationOpen(Bundle payload, String action) {
        String campaignId = null;
        try {
            String payloadString = payload.getString(PUSH_PAYLOAD_KEY);
            if (!Utility.isNullOrEmpty(payloadString)) {
                campaignId = new JSONObject(payloadString).getString(PUSH_PAYLOAD_CAMPAIGN_KEY);
                if (campaignId == null) {
                    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, TAG, "Malformed payload specified for logging a push notification open.");
                    return;
                }
                Bundle parameters = new Bundle();
                parameters.putString(APP_EVENT_PUSH_PARAMETER_CAMPAIGN, campaignId);
                if (action != null) {
                    parameters.putString(APP_EVENT_PUSH_PARAMETER_ACTION, action);
                }
                logEvent(APP_EVENT_NAME_PUSH_OPENED, parameters);
            }
        } catch (JSONException e) {
        }
    }

    public void flush() {
        AppEventQueue.flush(FlushReason.EXPLICIT);
    }

    public static void onContextStop() {
        AppEventQueue.persistToDisk();
    }

    public boolean isValidForAccessToken(AccessToken accessToken) {
        return this.accessTokenAppId.equals(new AccessTokenAppIdPair(accessToken));
    }

    public static void setPushNotificationsRegistrationId(String registrationId) {
        synchronized (staticLock) {
            if (!Utility.stringsEqualOrEmpty(pushNotificationsRegistrationId, registrationId)) {
                pushNotificationsRegistrationId = registrationId;
                AppEventsLogger logger = newLogger(FacebookSdk.getApplicationContext());
                logger.logEvent(AppEventsConstants.EVENT_NAME_PUSH_TOKEN_OBTAINED);
                if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
                    logger.flush();
                }
            }
        }
    }

    static String getPushNotificationsRegistrationId() {
        String str;
        synchronized (staticLock) {
            str = pushNotificationsRegistrationId;
        }
        return str;
    }

    public static void setUserID(String userID) {
        AnalyticsUserIDStore.setUserID(userID);
    }

    public static String getUserID() {
        return AnalyticsUserIDStore.getUserID();
    }

    public static void clearUserID() {
        AnalyticsUserIDStore.setUserID(null);
    }

    public static void updateUserProperties(Bundle parameters, Callback callback) {
        updateUserProperties(parameters, FacebookSdk.getApplicationId(), callback);
    }

    public static void updateUserProperties(final Bundle parameters, final String applicationID, final Callback callback) {
        final String userID = getUserID();
        if (userID == null || userID.isEmpty()) {
            Logger.log(LoggingBehavior.APP_EVENTS, TAG, "AppEventsLogger userID cannot be null or empty");
        } else {
            getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    Bundle userPropertiesParams = new Bundle();
                    userPropertiesParams.putString("user_unique_id", userID);
                    userPropertiesParams.putBundle("custom_data", parameters);
                    AttributionIdentifiers identifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                    if (!(identifiers == null || identifiers.getAndroidAdvertiserId() == null)) {
                        userPropertiesParams.putString("advertiser_id", identifiers.getAndroidAdvertiserId());
                    }
                    Bundle data = new Bundle();
                    try {
                        JSONObject userData = BundleJSONConverter.convertToJSON(userPropertiesParams);
                        JSONArray dataArray = new JSONArray();
                        dataArray.put(userData);
                        data.putString("data", dataArray.toString());
                        GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.US, "%s/user_properties", new Object[]{applicationID}), data, HttpMethod.POST, callback);
                        request.setSkipClientToken(true);
                        request.executeAsync();
                    } catch (JSONException ex) {
                        throw new FacebookException("Failed to construct request", (Throwable) ex);
                    }
                }
            });
        }
    }

    public void logSdkEvent(String eventName, Double valueToSum, Bundle parameters) {
        logEvent(eventName, valueToSum, parameters, true, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public String getApplicationId() {
        return this.accessTokenAppId.getApplicationId();
    }

    private AppEventsLogger(Context context, String applicationId, AccessToken accessToken) {
        this(Utility.getActivityName(context), applicationId, accessToken);
    }

    protected AppEventsLogger(String activityName, String applicationId, AccessToken accessToken) {
        Validate.sdkInitialized();
        this.contextName = activityName;
        if (accessToken == null) {
            accessToken = AccessToken.getCurrentAccessToken();
        }
        if (accessToken == null || (applicationId != null && !applicationId.equals(accessToken.getApplicationId()))) {
            if (applicationId == null) {
                applicationId = Utility.getMetadataApplicationId(FacebookSdk.getApplicationContext());
            }
            this.accessTokenAppId = new AccessTokenAppIdPair(null, applicationId);
        } else {
            this.accessTokenAppId = new AccessTokenAppIdPair(accessToken);
        }
        initializeTimersIfNeeded();
    }

    private static void initializeTimersIfNeeded() {
        synchronized (staticLock) {
            if (backgroundExecutor == null) {
                backgroundExecutor = new ScheduledThreadPoolExecutor(1);
                backgroundExecutor.scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        Set<String> applicationIds = new HashSet<>();
                        for (AccessTokenAppIdPair accessTokenAppId : AppEventQueue.getKeySet()) {
                            applicationIds.add(accessTokenAppId.getApplicationId());
                        }
                        for (String applicationId : applicationIds) {
                            FetchedAppSettingsManager.queryAppSettings(applicationId, true);
                        }
                    }
                }, 0, AirDateConstants.SECONDS_PER_DAY, TimeUnit.SECONDS);
            }
        }
    }

    private void logEvent(String eventName, Double valueToSum, Bundle parameters, boolean isImplicitlyLogged, UUID currentSessionId) {
        try {
            logEvent(FacebookSdk.getApplicationContext(), new AppEvent(this.contextName, eventName, valueToSum, parameters, isImplicitlyLogged, currentSessionId), this.accessTokenAppId);
        } catch (JSONException jsonException) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", jsonException.toString());
        } catch (FacebookException e) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", e.toString());
        }
    }

    private static void logEvent(Context context, AppEvent event, AccessTokenAppIdPair accessTokenAppId2) {
        AppEventQueue.add(accessTokenAppId2, event);
        if (!event.getIsImplicit() && !isActivateAppEventRequested) {
            if (event.getName() == AppEventsConstants.EVENT_NAME_ACTIVATED_APP) {
                isActivateAppEventRequested = true;
            } else {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
            }
        }
    }

    static void eagerFlush() {
        if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
            AppEventQueue.flush(FlushReason.EAGER_FLUSHING_EVENT);
        }
    }

    private static void notifyDeveloperError(String message) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", message);
    }

    private static void setSourceApplication(Activity activity) {
        ComponentName callingApplication = activity.getCallingActivity();
        if (callingApplication != null) {
            String callingApplicationPackage = callingApplication.getPackageName();
            if (callingApplicationPackage.equals(activity.getPackageName())) {
                resetSourceApplication();
                return;
            }
            sourceApplication = callingApplicationPackage;
        }
        Intent openIntent = activity.getIntent();
        if (openIntent == null || openIntent.getBooleanExtra(SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, false)) {
            resetSourceApplication();
            return;
        }
        Bundle applinkData = AppLinks.getAppLinkData(openIntent);
        if (applinkData == null) {
            resetSourceApplication();
            return;
        }
        isOpenedByApplink = true;
        Bundle applinkReferrerData = applinkData.getBundle("referer_app_link");
        if (applinkReferrerData == null) {
            sourceApplication = null;
            return;
        }
        sourceApplication = applinkReferrerData.getString("package");
        openIntent.putExtra(SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, true);
    }

    static void setSourceApplication(String applicationPackage, boolean openByAppLink) {
        sourceApplication = applicationPackage;
        isOpenedByApplink = openByAppLink;
    }

    static String getSourceApplication() {
        String openType = "Unclassified";
        if (isOpenedByApplink) {
            openType = "Applink";
        }
        if (sourceApplication != null) {
            return openType + "(" + sourceApplication + ")";
        }
        return openType;
    }

    static void resetSourceApplication() {
        sourceApplication = null;
        isOpenedByApplink = false;
    }

    static Executor getAnalyticsExecutor() {
        if (backgroundExecutor == null) {
            initializeTimersIfNeeded();
        }
        return backgroundExecutor;
    }

    public static String getAnonymousAppDeviceGUID(Context context) {
        if (anonymousAppDeviceGUID == null) {
            synchronized (staticLock) {
                if (anonymousAppDeviceGUID == null) {
                    anonymousAppDeviceGUID = context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).getString("anonymousAppDeviceGUID", null);
                    if (anonymousAppDeviceGUID == null) {
                        anonymousAppDeviceGUID = "XZ" + UUID.randomUUID().toString();
                        context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).edit().putString("anonymousAppDeviceGUID", anonymousAppDeviceGUID).apply();
                    }
                }
            }
        }
        return anonymousAppDeviceGUID;
    }
}
