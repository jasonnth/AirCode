package com.facebook;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.Base64;
import android.util.Log;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.BoltsMeasurementEventListener;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.LockOnGetVariable;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class FacebookSdk {
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    public static final String APPLICATION_NAME_PROPERTY = "com.facebook.sdk.ApplicationName";
    private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
    public static final String AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY = "com.facebook.sdk.AutoLogAppEventsEnabled";
    static final String CALLBACK_OFFSET_CHANGED_AFTER_INIT = "The callback request code offset can't be updated once the SDK is initialized. Call FacebookSdk.setCallbackRequestCodeOffset inside your Application.onCreate method";
    static final String CALLBACK_OFFSET_NEGATIVE = "The callback request code offset can't be negative.";
    public static final String CALLBACK_OFFSET_PROPERTY = "com.facebook.sdk.CallbackOffset";
    public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
    private static final int DEFAULT_CALLBACK_REQUEST_CODE_OFFSET = 64206;
    private static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_KEEP_ALIVE = 1;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
    private static final int DEFAULT_THEME = C3344R.C3347style.com_facebook_activity_theme;
    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger counter = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "FacebookSdk #" + this.counter.incrementAndGet());
        }
    };
    private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE = new LinkedBlockingQueue(10);
    private static final String FACEBOOK_COM = "facebook.com";
    private static final Object LOCK = new Object();
    private static final int MAX_REQUEST_CODE_RANGE = 100;
    private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
    private static final String TAG = FacebookSdk.class.getCanonicalName();
    public static final String WEB_DIALOG_THEME = "com.facebook.sdk.WebDialogTheme";
    private static volatile String appClientToken;
    /* access modifiers changed from: private */
    public static Context applicationContext;
    private static volatile String applicationId;
    private static volatile String applicationName;
    private static volatile Boolean autoLogAppEventsEnabled;
    private static LockOnGetVariable<File> cacheDir;
    private static int callbackRequestCodeOffset = DEFAULT_CALLBACK_REQUEST_CODE_OFFSET;
    private static Executor executor;
    private static volatile String facebookDomain = FACEBOOK_COM;
    private static String graphApiVersion = ServerProtocol.getDefaultAPIVersion();
    private static volatile boolean isDebugEnabled = false;
    private static boolean isLegacyTokenUpgradeSupported = false;
    private static final HashSet<LoggingBehavior> loggingBehaviors = new HashSet<>(Arrays.asList(new LoggingBehavior[]{LoggingBehavior.DEVELOPER_ERRORS}));
    private static AtomicLong onProgressThreshold = new AtomicLong(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
    private static Boolean sdkInitialized = Boolean.valueOf(false);
    private static volatile int webDialogTheme;

    public interface InitializeCallback {
        void onInitialized();
    }

    @Deprecated
    public static synchronized void sdkInitialize(Context applicationContext2, int callbackRequestCodeOffset2) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(applicationContext2, callbackRequestCodeOffset2, null);
        }
    }

    @Deprecated
    public static synchronized void sdkInitialize(Context applicationContext2, int callbackRequestCodeOffset2, InitializeCallback callback) {
        synchronized (FacebookSdk.class) {
            if (sdkInitialized.booleanValue() && callbackRequestCodeOffset2 != callbackRequestCodeOffset) {
                throw new FacebookException(CALLBACK_OFFSET_CHANGED_AFTER_INIT);
            } else if (callbackRequestCodeOffset2 < 0) {
                throw new FacebookException(CALLBACK_OFFSET_NEGATIVE);
            } else {
                callbackRequestCodeOffset = callbackRequestCodeOffset2;
                sdkInitialize(applicationContext2, callback);
            }
        }
    }

    @Deprecated
    public static synchronized void sdkInitialize(Context applicationContext2) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(applicationContext2, (InitializeCallback) null);
        }
    }

    @Deprecated
    public static synchronized void sdkInitialize(final Context applicationContext2, final InitializeCallback callback) {
        synchronized (FacebookSdk.class) {
            if (!sdkInitialized.booleanValue()) {
                Validate.notNull(applicationContext2, "applicationContext");
                Validate.hasFacebookActivity(applicationContext2, false);
                Validate.hasInternetPermissions(applicationContext2, false);
                applicationContext = applicationContext2.getApplicationContext();
                loadDefaultsFromMetadata(applicationContext);
                if (Utility.isNullOrEmpty(applicationId)) {
                    throw new FacebookException("A valid Facebook app id must be set in the AndroidManifest.xml or set by calling FacebookSdk.setApplicationId before initializing the sdk.");
                }
                sdkInitialized = Boolean.valueOf(true);
                FetchedAppSettingsManager.loadAppSettingsAsync();
                NativeProtocol.updateAllAvailableProtocolVersionsAsync();
                BoltsMeasurementEventListener.getInstance(applicationContext);
                cacheDir = new LockOnGetVariable<>((Callable<T>) new Callable<File>() {
                    public File call() throws Exception {
                        return FacebookSdk.applicationContext.getCacheDir();
                    }
                });
                getExecutor().execute(new FutureTask<>(new Callable<Void>() {
                    public Void call() throws Exception {
                        AccessTokenManager.getInstance().loadCurrentAccessToken();
                        ProfileManager.getInstance().loadCurrentProfile();
                        if (AccessToken.getCurrentAccessToken() != null && Profile.getCurrentProfile() == null) {
                            Profile.fetchProfileForCurrentAccessToken();
                        }
                        if (callback != null) {
                            callback.onInitialized();
                        }
                        AppEventsLogger.newLogger(applicationContext2.getApplicationContext()).flush();
                        return null;
                    }
                }));
            } else if (callback != null) {
                callback.onInitialized();
            }
        }
    }

    public static synchronized boolean isInitialized() {
        boolean booleanValue;
        synchronized (FacebookSdk.class) {
            booleanValue = sdkInitialized.booleanValue();
        }
        return booleanValue;
    }

    public static Set<LoggingBehavior> getLoggingBehaviors() {
        Set<LoggingBehavior> unmodifiableSet;
        synchronized (loggingBehaviors) {
            unmodifiableSet = Collections.unmodifiableSet(new HashSet(loggingBehaviors));
        }
        return unmodifiableSet;
    }

    public static void addLoggingBehavior(LoggingBehavior behavior) {
        synchronized (loggingBehaviors) {
            loggingBehaviors.add(behavior);
            updateGraphDebugBehavior();
        }
    }

    public static void removeLoggingBehavior(LoggingBehavior behavior) {
        synchronized (loggingBehaviors) {
            loggingBehaviors.remove(behavior);
        }
    }

    public static void clearLoggingBehaviors() {
        synchronized (loggingBehaviors) {
            loggingBehaviors.clear();
        }
    }

    public static boolean isLoggingBehaviorEnabled(LoggingBehavior behavior) {
        boolean z;
        synchronized (loggingBehaviors) {
            z = isDebugEnabled() && loggingBehaviors.contains(behavior);
        }
        return z;
    }

    public static boolean isDebugEnabled() {
        return isDebugEnabled;
    }

    public static void setIsDebugEnabled(boolean enabled) {
        isDebugEnabled = enabled;
    }

    public static boolean isLegacyTokenUpgradeSupported() {
        return isLegacyTokenUpgradeSupported;
    }

    private static void updateGraphDebugBehavior() {
        if (loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_INFO) && !loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            loggingBehaviors.add(LoggingBehavior.GRAPH_API_DEBUG_WARNING);
        }
    }

    public static void setLegacyTokenUpgradeSupported(boolean supported) {
        isLegacyTokenUpgradeSupported = supported;
    }

    public static Executor getExecutor() {
        synchronized (LOCK) {
            if (executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return executor;
    }

    public static void setExecutor(Executor executor2) {
        Validate.notNull(executor2, "executor");
        synchronized (LOCK) {
            executor = executor2;
        }
    }

    public static String getFacebookDomain() {
        return facebookDomain;
    }

    public static void setFacebookDomain(String facebookDomain2) {
        Log.w(TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
        facebookDomain = facebookDomain2;
    }

    public static Context getApplicationContext() {
        Validate.sdkInitialized();
        return applicationContext;
    }

    public static void setGraphApiVersion(String graphApiVersion2) {
        if (!Utility.isNullOrEmpty(graphApiVersion2) && !graphApiVersion.equals(graphApiVersion2)) {
            graphApiVersion = graphApiVersion2;
        }
    }

    public static String getGraphApiVersion() {
        return graphApiVersion;
    }

    public static void publishInstallAsync(Context context, final String applicationId2) {
        final Context applicationContext2 = context.getApplicationContext();
        getExecutor().execute(new Runnable() {
            public void run() {
                FacebookSdk.publishInstallAndWaitForResponse(applicationContext2, applicationId2);
            }
        });
    }

    /* JADX INFO: used method not loaded: com.facebook.internal.Utility.logd(java.lang.String, java.lang.Exception):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.facebook.FacebookRequestError.<init>(java.net.HttpURLConnection, java.lang.Exception):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.facebook.FacebookException.<init>(java.lang.String, java.lang.Throwable):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00ea, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00eb, code lost:
        r0 = new com.facebook.FacebookException("An error occurred while publishing install.", (java.lang.Throwable) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00f7, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return new com.facebook.GraphResponse(null, null, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        com.facebook.internal.Utility.logd("Facebook-publish", r4);
        r0 = new com.facebook.FacebookRequestError((java.net.HttpURLConnection) null, r4);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:2:0x0004, B:9:0x007e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.facebook.GraphResponse publishInstallAndWaitForResponse(android.content.Context r24, java.lang.String r25) {
        /*
            if (r24 == 0) goto L_0x0004
            if (r25 != 0) goto L_0x002b
        L_0x0004:
            java.lang.IllegalArgumentException r19 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x000d }
            java.lang.String r20 = "Both context and applicationId must be non-null"
            r19.<init>(r20)     // Catch:{ Exception -> 0x000d }
            throw r19     // Catch:{ Exception -> 0x000d }
        L_0x000d:
            r4 = move-exception
            java.lang.String r19 = "Facebook-publish"
            r0 = r19
            com.facebook.internal.Utility.logd(r0, r4)
            com.facebook.GraphResponse r19 = new com.facebook.GraphResponse
            r20 = 0
            r21 = 0
            com.facebook.FacebookRequestError r22 = new com.facebook.FacebookRequestError
            r23 = 0
            r0 = r22
            r1 = r23
            r0.<init>(r1, r4)
            r19.<init>(r20, r21, r22)
        L_0x002a:
            return r19
        L_0x002b:
            com.facebook.internal.AttributionIdentifiers r8 = com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(r24)     // Catch:{ Exception -> 0x000d }
            java.lang.String r19 = "com.facebook.sdk.attributionTracking"
            r20 = 0
            r0 = r24
            r1 = r19
            r2 = r20
            android.content.SharedPreferences r14 = r0.getSharedPreferences(r1, r2)     // Catch:{ Exception -> 0x000d }
            java.lang.StringBuilder r19 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x000d }
            r19.<init>()     // Catch:{ Exception -> 0x000d }
            r0 = r19
            r1 = r25
            java.lang.StringBuilder r19 = r0.append(r1)     // Catch:{ Exception -> 0x000d }
            java.lang.String r20 = "ping"
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ Exception -> 0x000d }
            java.lang.String r13 = r19.toString()     // Catch:{ Exception -> 0x000d }
            java.lang.StringBuilder r19 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x000d }
            r19.<init>()     // Catch:{ Exception -> 0x000d }
            r0 = r19
            r1 = r25
            java.lang.StringBuilder r19 = r0.append(r1)     // Catch:{ Exception -> 0x000d }
            java.lang.String r20 = "json"
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ Exception -> 0x000d }
            java.lang.String r9 = r19.toString()     // Catch:{ Exception -> 0x000d }
            r20 = 0
            r0 = r20
            long r10 = r14.getLong(r13, r0)     // Catch:{ Exception -> 0x000d }
            r19 = 0
            r0 = r19
            java.lang.String r12 = r14.getString(r9, r0)     // Catch:{ Exception -> 0x000d }
            com.facebook.internal.AppEventsLoggerUtility$GraphAPIActivityType r19 = com.facebook.internal.AppEventsLoggerUtility.GraphAPIActivityType.MOBILE_INSTALL_EVENT     // Catch:{ JSONException -> 0x00ea }
            java.lang.String r20 = com.facebook.appevents.AppEventsLogger.getAnonymousAppDeviceGUID(r24)     // Catch:{ JSONException -> 0x00ea }
            boolean r21 = getLimitEventAndDataUsage(r24)     // Catch:{ JSONException -> 0x00ea }
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r24
            org.json.JSONObject r15 = com.facebook.internal.AppEventsLoggerUtility.getJSONObjectForGraphAPICall(r0, r8, r1, r2, r3)     // Catch:{ JSONException -> 0x00ea }
            java.lang.String r19 = "%s/activities"
            r20 = 1
            r0 = r20
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x000d }
            r20 = r0
            r21 = 0
            r20[r21] = r25     // Catch:{ Exception -> 0x000d }
            java.lang.String r18 = java.lang.String.format(r19, r20)     // Catch:{ Exception -> 0x000d }
            r19 = 0
            r20 = 0
            r0 = r19
            r1 = r18
            r2 = r20
            com.facebook.GraphRequest r16 = com.facebook.GraphRequest.newPostRequest(r0, r1, r15, r2)     // Catch:{ Exception -> 0x000d }
            r20 = 0
            int r19 = (r10 > r20 ? 1 : (r10 == r20 ? 0 : -1))
            if (r19 == 0) goto L_0x010d
            r6 = 0
            if (r12 == 0) goto L_0x00c4
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0136 }
            r7.<init>(r12)     // Catch:{ JSONException -> 0x0136 }
            r6 = r7
        L_0x00c4:
            if (r6 != 0) goto L_0x00f8
            java.lang.String r19 = "true"
            r20 = 0
            com.facebook.GraphRequestBatch r21 = new com.facebook.GraphRequestBatch     // Catch:{ Exception -> 0x000d }
            r22 = 1
            r0 = r22
            com.facebook.GraphRequest[] r0 = new com.facebook.GraphRequest[r0]     // Catch:{ Exception -> 0x000d }
            r22 = r0
            r23 = 0
            r22[r23] = r16     // Catch:{ Exception -> 0x000d }
            r21.<init>(r22)     // Catch:{ Exception -> 0x000d }
            java.util.List r19 = com.facebook.GraphResponse.createResponsesFromString(r19, r20, r21)     // Catch:{ Exception -> 0x000d }
            r20 = 0
            java.lang.Object r19 = r19.get(r20)     // Catch:{ Exception -> 0x000d }
            com.facebook.GraphResponse r19 = (com.facebook.GraphResponse) r19     // Catch:{ Exception -> 0x000d }
            goto L_0x002a
        L_0x00ea:
            r4 = move-exception
            com.facebook.FacebookException r19 = new com.facebook.FacebookException     // Catch:{ Exception -> 0x000d }
            java.lang.String r20 = "An error occurred while publishing install."
            r0 = r19
            r1 = r20
            r0.<init>(r1, r4)     // Catch:{ Exception -> 0x000d }
            throw r19     // Catch:{ Exception -> 0x000d }
        L_0x00f8:
            com.facebook.GraphResponse r19 = new com.facebook.GraphResponse     // Catch:{ Exception -> 0x000d }
            r20 = 0
            r21 = 0
            r22 = 0
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r0.<init>(r1, r2, r3, r6)     // Catch:{ Exception -> 0x000d }
            goto L_0x002a
        L_0x010d:
            com.facebook.GraphResponse r17 = r16.executeAndWait()     // Catch:{ Exception -> 0x000d }
            android.content.SharedPreferences$Editor r5 = r14.edit()     // Catch:{ Exception -> 0x000d }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x000d }
            r5.putLong(r13, r10)     // Catch:{ Exception -> 0x000d }
            org.json.JSONObject r19 = r17.getJSONObject()     // Catch:{ Exception -> 0x000d }
            if (r19 == 0) goto L_0x012f
            org.json.JSONObject r19 = r17.getJSONObject()     // Catch:{ Exception -> 0x000d }
            java.lang.String r19 = r19.toString()     // Catch:{ Exception -> 0x000d }
            r0 = r19
            r5.putString(r9, r0)     // Catch:{ Exception -> 0x000d }
        L_0x012f:
            r5.apply()     // Catch:{ Exception -> 0x000d }
            r19 = r17
            goto L_0x002a
        L_0x0136:
            r19 = move-exception
            goto L_0x00c4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookSdk.publishInstallAndWaitForResponse(android.content.Context, java.lang.String):com.facebook.GraphResponse");
    }

    public static String getSdkVersion() {
        return "4.23.0";
    }

    public static boolean getLimitEventAndDataUsage(Context context) {
        Validate.sdkInitialized();
        return context.getSharedPreferences(AppEventsLogger.APP_EVENT_PREFERENCES, 0).getBoolean("limitEventUsage", false);
    }

    public static void setLimitEventAndDataUsage(Context context, boolean limitEventUsage) {
        context.getSharedPreferences(AppEventsLogger.APP_EVENT_PREFERENCES, 0).edit().putBoolean("limitEventUsage", limitEventUsage).apply();
    }

    public static long getOnProgressThreshold() {
        Validate.sdkInitialized();
        return onProgressThreshold.get();
    }

    public static void setOnProgressThreshold(long threshold) {
        onProgressThreshold.set(threshold);
    }

    static void loadDefaultsFromMetadata(Context context) {
        if (context != null) {
            try {
                ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (ai != null && ai.metaData != null) {
                    if (applicationId == null) {
                        Object appId = ai.metaData.get("com.facebook.sdk.ApplicationId");
                        if (appId instanceof String) {
                            String appIdString = (String) appId;
                            if (appIdString.toLowerCase(Locale.ROOT).startsWith("fb")) {
                                applicationId = appIdString.substring(2);
                            } else {
                                applicationId = appIdString;
                            }
                        } else if (appId instanceof Integer) {
                            throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
                        }
                    }
                    if (applicationName == null) {
                        applicationName = ai.metaData.getString(APPLICATION_NAME_PROPERTY);
                    }
                    if (appClientToken == null) {
                        appClientToken = ai.metaData.getString(CLIENT_TOKEN_PROPERTY);
                    }
                    if (webDialogTheme == 0) {
                        setWebDialogTheme(ai.metaData.getInt(WEB_DIALOG_THEME));
                    }
                    if (callbackRequestCodeOffset == DEFAULT_CALLBACK_REQUEST_CODE_OFFSET) {
                        callbackRequestCodeOffset = ai.metaData.getInt(CALLBACK_OFFSET_PROPERTY, DEFAULT_CALLBACK_REQUEST_CODE_OFFSET);
                    }
                    if (autoLogAppEventsEnabled == null) {
                        autoLogAppEventsEnabled = Boolean.valueOf(ai.metaData.getBoolean(AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY, true));
                    }
                }
            } catch (NameNotFoundException e) {
            }
        }
    }

    public static String getApplicationSignature(Context context) {
        Validate.sdkInitialized();
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        try {
            PackageInfo pInfo = packageManager.getPackageInfo(context.getPackageName(), 64);
            Signature[] signatures = pInfo.signatures;
            if (signatures == null || signatures.length == 0) {
                return null;
            }
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                md.update(pInfo.signatures[0].toByteArray());
                return Base64.encodeToString(md.digest(), 9);
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
        } catch (NameNotFoundException e2) {
            return null;
        }
    }

    public static String getApplicationId() {
        Validate.sdkInitialized();
        return applicationId;
    }

    public static void setApplicationId(String applicationId2) {
        applicationId = applicationId2;
    }

    public static String getApplicationName() {
        Validate.sdkInitialized();
        return applicationName;
    }

    public static void setApplicationName(String applicationName2) {
        applicationName = applicationName2;
    }

    public static String getClientToken() {
        Validate.sdkInitialized();
        return appClientToken;
    }

    public static void setClientToken(String clientToken) {
        appClientToken = clientToken;
    }

    public static int getWebDialogTheme() {
        Validate.sdkInitialized();
        return webDialogTheme;
    }

    public static void setWebDialogTheme(int theme) {
        if (theme == 0) {
            theme = DEFAULT_THEME;
        }
        webDialogTheme = theme;
    }

    public static boolean getAutoLogAppEventsEnabled() {
        Validate.sdkInitialized();
        return autoLogAppEventsEnabled.booleanValue();
    }

    public static void setAutoLogAppEventsEnabled(boolean flag) {
        autoLogAppEventsEnabled = Boolean.valueOf(flag);
    }

    public static File getCacheDir() {
        Validate.sdkInitialized();
        return (File) cacheDir.getValue();
    }

    public static void setCacheDir(File cacheDir2) {
        cacheDir = new LockOnGetVariable<>(cacheDir2);
    }

    public static int getCallbackRequestCodeOffset() {
        Validate.sdkInitialized();
        return callbackRequestCodeOffset;
    }

    public static boolean isFacebookRequestCode(int requestCode) {
        return requestCode >= callbackRequestCodeOffset && requestCode < callbackRequestCodeOffset + 100;
    }
}
