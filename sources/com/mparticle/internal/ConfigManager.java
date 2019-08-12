package com.mparticle.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.mparticle.C4586a;
import com.mparticle.MParticle;
import com.mparticle.MParticle.Environment;
import com.mparticle.MParticle.LogLevel;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigManager {
    public static final String CONFIG_JSON = "json";
    private static final int DEVMODE_UPLOAD_INTERVAL_MILLISECONDS = 10000;
    public static final String KEY_AAID_LAT = "rdlat";
    private static final String KEY_DEVICE_PERFORMANCE_METRICS_DISABLED = "dpmd";
    public static final String KEY_EMBEDDED_KITS = "eks";
    private static final String KEY_INCLUDE_SESSION_HISTORY = "inhd";
    private static final String KEY_INFLUENCE_OPEN = "pio";
    private static final String KEY_MESSAGE_MATCHES = "mm";
    static final String KEY_OPT_OUT = "oo";
    public static final String KEY_PUSH_MESSAGES = "pmk";
    static final String KEY_RAMP = "rp";
    static final String KEY_SESSION_TIMEOUT = "stl";
    private static final String KEY_TRIGGER_ITEMS = "tri";
    private static final String KEY_TRIGGER_ITEM_HASHES = "evts";
    public static final String KEY_UNHANDLED_EXCEPTIONS = "cue";
    static final String KEY_UPLOAD_INTERVAL = "uitl";
    private static final String PREFERENCES_FILE = "mp_preferences";
    public static final String VALUE_APP_DEFINED = "appdefined";
    public static final String VALUE_CUE_CATCH = "forcecatch";
    static SharedPreferences mPreferences;
    private static JSONArray sPushKeys;
    private Context mContext;
    private C4586a mExHandler;
    private boolean mIncludeSessionHistory = true;
    private long mInfluenceOpenTimeout = 3600000;
    private String mLogUnhandledExceptions = VALUE_APP_DEFINED;
    private JSONObject mProviderPersistence;
    private int mRampValue = -1;
    private boolean mRestrictAAIDfromLAT = true;
    private boolean mSendOoEvents;
    private int mSessionTimeoutInterval = -1;
    private JSONArray mTriggerMessageHashes = null;
    private JSONArray mTriggerMessageMatches;
    private int mUploadInterval = -1;
    private int mUserBucket = -1;
    C4603a sLocalPrefs;

    private ConfigManager() {
    }

    public ConfigManager(Context context, Environment environment, String apiKey, String apiSecret) {
        this.mContext = context.getApplicationContext();
        mPreferences = this.mContext.getSharedPreferences(PREFERENCES_FILE, 0);
        this.sLocalPrefs = new C4603a(this.mContext, environment, mPreferences, apiKey, apiSecret);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.String, org.json.JSONArray] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [java.lang.String, org.json.JSONArray]
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
      uses: [java.lang.String, org.json.JSONArray]
      mth insns count: 13
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONArray getLatestKitConfiguration() {
        /*
            r3 = this;
            r0 = 0
            android.content.SharedPreferences r1 = mPreferences
            java.lang.String r2 = "json"
            java.lang.String r1 = r1.getString(r2, r0)
            boolean r2 = com.mparticle.internal.MPUtility.isEmpty(r1)
            if (r2 != 0) goto L_0x001c
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x001d }
            r2.<init>(r1)     // Catch:{ Exception -> 0x001d }
            java.lang.String r1 = "eks"
            org.json.JSONArray r0 = r2.optJSONArray(r1)     // Catch:{ Exception -> 0x001d }
        L_0x001c:
            return r0
        L_0x001d:
            r1 = move-exception
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mparticle.internal.ConfigManager.getLatestKitConfiguration():org.json.JSONArray");
    }

    /* access modifiers changed from: 0000 */
    public void saveConfigJson(JSONObject json) {
        if (json != null) {
            mPreferences.edit().putString(CONFIG_JSON, json.toString()).apply();
        }
    }

    public synchronized void updateConfig(JSONObject responseJSON) throws JSONException {
        updateConfig(responseJSON, true);
    }

    public synchronized void updateConfig(JSONObject responseJSON, boolean persistJson) throws JSONException {
        Editor edit = mPreferences.edit();
        if (persistJson) {
            saveConfigJson(responseJSON);
        }
        if (responseJSON.has(KEY_UNHANDLED_EXCEPTIONS)) {
            this.mLogUnhandledExceptions = responseJSON.getString(KEY_UNHANDLED_EXCEPTIONS);
        }
        if (responseJSON.has(KEY_PUSH_MESSAGES)) {
            sPushKeys = responseJSON.getJSONArray(KEY_PUSH_MESSAGES);
            edit.putString(KEY_PUSH_MESSAGES, sPushKeys.toString());
        }
        this.mRampValue = responseJSON.optInt(KEY_RAMP, -1);
        if (responseJSON.has(KEY_OPT_OUT)) {
            this.mSendOoEvents = responseJSON.getBoolean(KEY_OPT_OUT);
        } else {
            this.mSendOoEvents = false;
        }
        if (responseJSON.has("cms")) {
            setProviderPersistence(new C4622o(responseJSON, this.mContext));
        } else {
            setProviderPersistence(null);
        }
        this.mSessionTimeoutInterval = responseJSON.optInt(KEY_SESSION_TIMEOUT, -1);
        this.mUploadInterval = responseJSON.optInt(KEY_UPLOAD_INTERVAL, -1);
        this.mTriggerMessageMatches = null;
        this.mTriggerMessageHashes = null;
        if (responseJSON.has(KEY_TRIGGER_ITEMS)) {
            try {
                JSONObject jSONObject = responseJSON.getJSONObject(KEY_TRIGGER_ITEMS);
                if (jSONObject.has(KEY_MESSAGE_MATCHES)) {
                    this.mTriggerMessageMatches = jSONObject.getJSONArray(KEY_MESSAGE_MATCHES);
                }
                if (jSONObject.has(KEY_TRIGGER_ITEM_HASHES)) {
                    this.mTriggerMessageHashes = jSONObject.getJSONArray(KEY_TRIGGER_ITEM_HASHES);
                }
            } catch (JSONException e) {
            }
        }
        if (responseJSON.has(KEY_INFLUENCE_OPEN)) {
            this.mInfluenceOpenTimeout = responseJSON.getLong(KEY_INFLUENCE_OPEN) * 60 * 1000;
        } else {
            this.mInfluenceOpenTimeout = 1800000;
        }
        this.mRestrictAAIDfromLAT = responseJSON.optBoolean(KEY_AAID_LAT, true);
        this.mIncludeSessionHistory = responseJSON.optBoolean(KEY_INCLUDE_SESSION_HISTORY, true);
        if (responseJSON.has(KEY_DEVICE_PERFORMANCE_METRICS_DISABLED)) {
            MParticle.setDevicePerformanceMetricsDisabled(responseJSON.optBoolean(KEY_DEVICE_PERFORMANCE_METRICS_DISABLED, false));
        }
        edit.apply();
        applyConfig();
        MParticle.getInstance().getKitManager().updateKits(responseJSON.optJSONArray(KEY_EMBEDDED_KITS));
    }

    public String getActiveModuleIds() {
        return MParticle.getInstance().getKitManager().getActiveModuleIds();
    }

    public boolean getIncludeSessionHistory() {
        return this.mIncludeSessionHistory;
    }

    public boolean getRestrictAAIDBasedOnLAT() {
        return this.mRestrictAAIDfromLAT;
    }

    public void delayedStart() {
        this.sLocalPrefs.mo44856a();
        if (isPushEnabled() && PushRegistrationHelper.getLatestPushRegistration(this.mContext) == null) {
            MParticle.getInstance().Messaging().enablePushNotifications(getPushSenderId());
        }
    }

    public JSONArray getTriggerMessageMatches() {
        return this.mTriggerMessageMatches;
    }

    public long getInfluenceOpenTimeoutMillis() {
        return this.mInfluenceOpenTimeout;
    }

    private void applyConfig() {
        if (getLogUnhandledExceptions()) {
            enableUncaughtExceptionLogging(false);
        } else {
            disableUncaughtExceptionLogging(false);
        }
    }

    public void enableUncaughtExceptionLogging(boolean userTriggered) {
        if (this.mExHandler == null) {
            UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (!(defaultUncaughtExceptionHandler instanceof C4586a)) {
                this.mExHandler = new C4586a(defaultUncaughtExceptionHandler);
                Thread.setDefaultUncaughtExceptionHandler(this.mExHandler);
                if (userTriggered) {
                    setLogUnhandledExceptions(true);
                }
            }
        }
    }

    public void disableUncaughtExceptionLogging(boolean userTriggered) {
        if (this.mExHandler != null && (Thread.getDefaultUncaughtExceptionHandler() instanceof C4586a)) {
            Thread.setDefaultUncaughtExceptionHandler(this.mExHandler.mo44543a());
            this.mExHandler = null;
            if (userTriggered) {
                setLogUnhandledExceptions(false);
            }
        }
    }

    public boolean getLogUnhandledExceptions() {
        if (this.mLogUnhandledExceptions.equals(VALUE_APP_DEFINED)) {
            return this.sLocalPrefs.f3737c;
        }
        return this.mLogUnhandledExceptions.equals(VALUE_CUE_CATCH);
    }

    public void setLogUnhandledExceptions(boolean log) {
        this.sLocalPrefs.f3737c = log;
    }

    public String getApiKey() {
        return this.sLocalPrefs.f3735a;
    }

    public String getApiSecret() {
        return this.sLocalPrefs.f3736b;
    }

    public long getUploadInterval() {
        if (getEnvironment().equals(Environment.Development)) {
            return 10000;
        }
        if (this.mUploadInterval > 0) {
            return (long) (this.mUploadInterval * 1000);
        }
        return (long) (this.sLocalPrefs.f3739e * 1000);
    }

    public static Environment getEnvironment() {
        return C4603a.m2219b();
    }

    public void setUploadInterval(int uploadInterval) {
        this.sLocalPrefs.f3739e = uploadInterval;
    }

    public int getSessionTimeout() {
        if (this.mSessionTimeoutInterval > 0) {
            return this.mSessionTimeoutInterval * 1000;
        }
        return this.sLocalPrefs.f3738d * 1000;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sLocalPrefs.f3738d = sessionTimeout;
    }

    public boolean isPushEnabled() {
        if (this.sLocalPrefs.f3740f || (mPreferences.getBoolean("mp::push_enabled", false) && getPushSenderId() != null)) {
            return true;
        }
        return false;
    }

    public String getPushSenderId() {
        String c = this.sLocalPrefs.mo44858c();
        return !MPUtility.isEmpty(c) ? c : mPreferences.getString("mp::push_sender_id", null);
    }

    public void setPushSenderId(String senderId) {
        mPreferences.edit().putString("mp::push_sender_id", senderId).putBoolean("mp::push_enabled", true).apply();
    }

    public static void log(LogLevel priority, String... messages) {
        log(priority, null, messages);
    }

    public static void log(LogLevel priority, Throwable error, String... messages) {
        if (messages != null && C4603a.f3733k.ordinal() >= priority.ordinal() && getEnvironment().equals(Environment.Development)) {
            StringBuilder sb = new StringBuilder();
            for (String append : messages) {
                sb.append(append);
            }
            switch (priority) {
                case ERROR:
                    if (error != null) {
                        Log.e("mParticle SDK", sb.toString(), error);
                        return;
                    } else {
                        Log.e("mParticle SDK", sb.toString());
                        return;
                    }
                case WARNING:
                    if (error != null) {
                        Log.w("mParticle SDK", sb.toString(), error);
                        return;
                    } else {
                        Log.w("mParticle SDK", sb.toString());
                        return;
                    }
                case DEBUG:
                    if (error != null) {
                        Log.v("mParticle SDK", sb.toString(), error);
                        return;
                    } else {
                        Log.v("mParticle SDK", sb.toString());
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public String getLicenseKey() {
        return this.sLocalPrefs.f3741g;
    }

    public boolean isLicensingEnabled() {
        return this.sLocalPrefs.f3741g != null && this.sLocalPrefs.f3742h;
    }

    public void setPushSoundEnabled(boolean pushSoundEnabled) {
        mPreferences.edit().putBoolean("mp::push::sound", pushSoundEnabled).apply();
    }

    public void setPushVibrationEnabled(boolean pushVibrationEnabled) {
        mPreferences.edit().putBoolean("mp::push::vibration", pushVibrationEnabled).apply();
    }

    public boolean isEnabled() {
        return !getOptedOut() || this.mSendOoEvents;
    }

    public void setOptOut(boolean optOut) {
        mPreferences.edit().putBoolean("mp::optout::", optOut).apply();
    }

    public boolean getOptedOut() {
        return mPreferences.getBoolean("mp::optout::", false);
    }

    public boolean isAutoTrackingEnabled() {
        return this.sLocalPrefs.f3743i;
    }

    public boolean isPushSoundEnabled() {
        return mPreferences.getBoolean("mp::push::sound", false);
    }

    public boolean isPushVibrationEnabled() {
        return mPreferences.getBoolean("mp::push::vibration", false);
    }

    public void setPushNotificationIcon(int pushNotificationIcon) {
        mPreferences.edit().putInt("mp::push::icon", pushNotificationIcon).apply();
    }

    public void setPushNotificationTitle(int pushNotificationTitle) {
        mPreferences.edit().putInt("mp::push::title", pushNotificationTitle).apply();
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_FILE, 0);
    }

    public static JSONArray getPushKeys(Context context) {
        if (sPushKeys == null) {
            try {
                sPushKeys = new JSONArray(getPreferences(context).getString(KEY_PUSH_MESSAGES, null));
            } catch (Exception e) {
                sPushKeys = new JSONArray();
            }
        }
        return sPushKeys;
    }

    public static int getPushTitle(Context context) {
        return getPreferences(context).getInt("mp::push::title", 0);
    }

    public static int getPushIcon(Context context) {
        return getPreferences(context).getInt("mp::push::icon", 0);
    }

    public static int getBreadcrumbLimit() {
        if (mPreferences != null) {
            return mPreferences.getInt("mp::breadcrumbs::limit", 50);
        }
        return 50;
    }

    public void setBreadcrumbLimit(int newLimit) {
        mPreferences.edit().putInt("mp::breadcrumbs::limit", newLimit).apply();
    }

    private synchronized void setProviderPersistence(JSONObject persistence) {
        this.mProviderPersistence = persistence;
    }

    public synchronized JSONObject getProviderPersistence() {
        return this.mProviderPersistence;
    }

    public void setMpid(long mpid) {
        mPreferences.edit().putLong("mp::mpid", mpid).apply();
    }

    public long getMpid() {
        if (mPreferences.contains("mp::mpid")) {
            return mPreferences.getLong("mp::mpid", 0);
        }
        long generateMpid = MPUtility.generateMpid();
        mPreferences.edit().putLong("mp::mpid", generateMpid).apply();
        return generateMpid;
    }

    public int getAudienceTimeout() {
        return this.sLocalPrefs.f3744j;
    }

    public void setLogLevel(LogLevel level) {
        C4603a aVar = this.sLocalPrefs;
        C4603a.f3733k = level;
    }

    public int getCurrentRampValue() {
        return this.mRampValue;
    }

    public JSONArray getTriggerMessageHashes() {
        return this.mTriggerMessageHashes;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006e, code lost:
        if (r15.getBoolean(r0) == r7.getBoolean(r0)) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0070, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0072, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0086, code lost:
        r0 = false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ab A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:20:0x0055] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldTrigger(com.mparticle.internal.C4609g r15) {
        /*
            r14 = this;
            r1 = 1
            r2 = 0
            org.json.JSONArray r5 = r14.getTriggerMessageMatches()
            org.json.JSONArray r6 = r14.getTriggerMessageHashes()
            java.lang.String r0 = r15.mo44873c()
            java.lang.String r3 = "pm"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0024
            java.lang.String r0 = r15.mo44873c()
            java.lang.String r3 = "cm"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0063
        L_0x0024:
            r3 = r1
        L_0x0025:
            if (r3 != 0) goto L_0x008e
            if (r5 == 0) goto L_0x008e
            int r0 = r5.length()
            if (r0 <= 0) goto L_0x008e
            r4 = r2
            r3 = r1
        L_0x0031:
            if (r3 == 0) goto L_0x008e
            int r0 = r5.length()
            if (r4 >= r0) goto L_0x008e
            org.json.JSONObject r7 = r5.getJSONObject(r4)     // Catch:{ Exception -> 0x00a8 }
            java.util.Iterator r8 = r7.keys()     // Catch:{ Exception -> 0x00a8 }
        L_0x0041:
            if (r3 == 0) goto L_0x0088
            boolean r0 = r8.hasNext()     // Catch:{ Exception -> 0x00a8 }
            if (r0 == 0) goto L_0x0088
            java.lang.Object r0 = r8.next()     // Catch:{ Exception -> 0x00a8 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x00a8 }
            boolean r3 = r15.has(r0)     // Catch:{ Exception -> 0x00a8 }
            if (r3 == 0) goto L_0x00b0
            java.lang.String r9 = r7.getString(r0)     // Catch:{ JSONException -> 0x0065, Exception -> 0x00ab }
            java.lang.String r10 = r15.getString(r0)     // Catch:{ JSONException -> 0x0065, Exception -> 0x00ab }
            boolean r0 = r9.equalsIgnoreCase(r10)     // Catch:{ JSONException -> 0x0065, Exception -> 0x00ab }
        L_0x0061:
            r3 = r0
            goto L_0x0041
        L_0x0063:
            r3 = r2
            goto L_0x0025
        L_0x0065:
            r9 = move-exception
            boolean r9 = r15.getBoolean(r0)     // Catch:{ JSONException -> 0x0074, Exception -> 0x00ab }
            boolean r0 = r7.getBoolean(r0)     // Catch:{ JSONException -> 0x0074, Exception -> 0x00ab }
            if (r9 != r0) goto L_0x0072
            r0 = r1
            goto L_0x0061
        L_0x0072:
            r0 = r2
            goto L_0x0061
        L_0x0074:
            r9 = move-exception
            double r10 = r15.getDouble(r0)     // Catch:{ JSONException -> 0x0085, Exception -> 0x00ab }
            double r12 = r7.getDouble(r0)     // Catch:{ JSONException -> 0x0085, Exception -> 0x00ab }
            int r0 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r0 != 0) goto L_0x0083
            r0 = r1
            goto L_0x0061
        L_0x0083:
            r0 = r2
            goto L_0x0061
        L_0x0085:
            r0 = move-exception
            r0 = r2
            goto L_0x0061
        L_0x0088:
            r0 = r3
        L_0x0089:
            int r3 = r4 + 1
            r4 = r3
            r3 = r0
            goto L_0x0031
        L_0x008e:
            r0 = r3
            if (r0 != 0) goto L_0x00ae
            if (r6 == 0) goto L_0x00ae
        L_0x0093:
            int r3 = r6.length()
            if (r2 >= r3) goto L_0x00ae
            int r3 = r6.getInt(r2)     // Catch:{ JSONException -> 0x00a4 }
            int r4 = r15.mo44874d()     // Catch:{ JSONException -> 0x00a4 }
            if (r3 != r4) goto L_0x00a5
        L_0x00a3:
            return r1
        L_0x00a4:
            r3 = move-exception
        L_0x00a5:
            int r2 = r2 + 1
            goto L_0x0093
        L_0x00a8:
            r0 = move-exception
            r0 = r3
            goto L_0x0089
        L_0x00ab:
            r0 = move-exception
            r0 = r3
            goto L_0x0089
        L_0x00ae:
            r1 = r0
            goto L_0x00a3
        L_0x00b0:
            r0 = r3
            goto L_0x0061
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mparticle.internal.ConfigManager.shouldTrigger(com.mparticle.internal.g):boolean");
    }

    public int getUserBucket() {
        if (this.mUserBucket < 0) {
            this.mUserBucket = (int) (Math.abs(getMpid() >> 8) % 100);
        }
        return this.mUserBucket;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0039 A[Catch:{ JSONException -> 0x0030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004b A[Catch:{ JSONException -> 0x0030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0060 A[Catch:{ JSONException -> 0x0030 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setIntegrationAttributes(int r5, java.util.Map<java.lang.String, java.lang.String> r6) {
        /*
            r4 = this;
            r0 = 0
            if (r6 == 0) goto L_0x0071
            boolean r1 = r6.isEmpty()     // Catch:{ JSONException -> 0x0030 }
            if (r1 != 0) goto L_0x0071
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0030 }
            r2.<init>()     // Catch:{ JSONException -> 0x0030 }
            java.util.Set r0 = r6.entrySet()     // Catch:{ JSONException -> 0x0030 }
            java.util.Iterator r3 = r0.iterator()     // Catch:{ JSONException -> 0x0030 }
        L_0x0016:
            boolean r0 = r3.hasNext()     // Catch:{ JSONException -> 0x0030 }
            if (r0 == 0) goto L_0x0032
            java.lang.Object r0 = r3.next()     // Catch:{ JSONException -> 0x0030 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ JSONException -> 0x0030 }
            java.lang.Object r1 = r0.getKey()     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ JSONException -> 0x0030 }
            java.lang.Object r0 = r0.getValue()     // Catch:{ JSONException -> 0x0030 }
            r2.put(r1, r0)     // Catch:{ JSONException -> 0x0030 }
            goto L_0x0016
        L_0x0030:
            r0 = move-exception
        L_0x0031:
            return
        L_0x0032:
            r1 = r2
        L_0x0033:
            org.json.JSONObject r0 = r4.getIntegrationAttributes()     // Catch:{ JSONException -> 0x0030 }
            if (r0 != 0) goto L_0x003e
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0030 }
            r0.<init>()     // Catch:{ JSONException -> 0x0030 }
        L_0x003e:
            java.lang.String r2 = java.lang.Integer.toString(r5)     // Catch:{ JSONException -> 0x0030 }
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x0030 }
            int r1 = r0.length()     // Catch:{ JSONException -> 0x0030 }
            if (r1 <= 0) goto L_0x0060
            android.content.SharedPreferences r1 = mPreferences     // Catch:{ JSONException -> 0x0030 }
            android.content.SharedPreferences$Editor r1 = r1.edit()     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r2 = "mp::integrationattributes"
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x0030 }
            android.content.SharedPreferences$Editor r0 = r1.putString(r2, r0)     // Catch:{ JSONException -> 0x0030 }
            r0.apply()     // Catch:{ JSONException -> 0x0030 }
            goto L_0x0031
        L_0x0060:
            android.content.SharedPreferences r0 = mPreferences     // Catch:{ JSONException -> 0x0030 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r1 = "mp::integrationattributes"
            android.content.SharedPreferences$Editor r0 = r0.remove(r1)     // Catch:{ JSONException -> 0x0030 }
            r0.apply()     // Catch:{ JSONException -> 0x0030 }
            goto L_0x0031
        L_0x0071:
            r1 = r0
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mparticle.internal.ConfigManager.setIntegrationAttributes(int, java.util.Map):void");
    }

    public Map<String, String> getIntegrationAttributes(int kitId) {
        HashMap hashMap = new HashMap();
        JSONObject integrationAttributes = getIntegrationAttributes();
        if (integrationAttributes != null) {
            JSONObject optJSONObject = integrationAttributes.optJSONObject(Integer.toString(kitId));
            if (optJSONObject != null) {
                try {
                    Iterator keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        if (optJSONObject.get(str) instanceof String) {
                            hashMap.put(str, optJSONObject.getString(str));
                        }
                    }
                } catch (JSONException e) {
                }
            }
        }
        return hashMap;
    }

    public JSONObject getIntegrationAttributes() {
        String string = mPreferences.getString("mp::integrationattributes", null);
        if (string == null) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            return null;
        }
    }
}
