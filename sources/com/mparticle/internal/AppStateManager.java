package com.mparticle.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.mparticle.BuildConfig;
import com.mparticle.MPEvent.Builder;
import com.mparticle.MParticle;
import com.mparticle.MParticle.LogLevel;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

public class AppStateManager {
    private static final long ACTIVITY_DELAY = 1000;
    public static final String APP_STATE_BACKGROUND = "background";
    public static final String APP_STATE_FOREGROUND = "foreground";
    public static final String APP_STATE_NOTRUNNING = "not_running";
    public static boolean mInitialized;
    Handler delayedBackgroundCheckHandler;
    /* access modifiers changed from: private */
    public ConfigManager mConfigManager;
    Context mContext;
    private String mCurrentActivityName;
    private WeakReference<Activity> mCurrentActivityReference;
    private Session mCurrentSession;
    AtomicInteger mInterruptionCount;
    private long mLastForegroundTime;
    AtomicLong mLastStoppedTime;
    private String mLaunchAction;
    private Uri mLaunchUri;
    private MessageManager mMessageManager;
    private final SharedPreferences mPreferences;
    boolean mUnitTesting;

    public AppStateManager(Context context, boolean unitTesting) {
        this.mCurrentSession = new Session();
        this.mCurrentActivityReference = null;
        this.delayedBackgroundCheckHandler = new Handler();
        this.mInterruptionCount = new AtomicInteger(0);
        this.mUnitTesting = false;
        this.mUnitTesting = unitTesting;
        this.mContext = context.getApplicationContext();
        this.mLastStoppedTime = new AtomicLong(getTime());
        this.mPreferences = context.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0);
    }

    public AppStateManager(Context context) {
        this(context, false);
    }

    public void init(int apiVersion) {
        if (apiVersion >= 14) {
            setupLifecycleCallbacks();
        }
    }

    public String getLaunchAction() {
        return this.mLaunchAction;
    }

    public Uri getLaunchUri() {
        return this.mLaunchUri;
    }

    public void setConfigManager(ConfigManager manager) {
        this.mConfigManager = manager;
    }

    public void setMessageManager(MessageManager manager) {
        this.mMessageManager = manager;
    }

    private long getTime() {
        if (this.mUnitTesting) {
            return System.currentTimeMillis();
        }
        return SystemClock.elapsedRealtime();
    }

    public void onActivityResumed(Activity activity) {
        String str;
        String str2;
        String str3 = null;
        try {
            this.mCurrentActivityName = getActivityName(activity);
            int i = this.mInterruptionCount.get();
            if (!mInitialized || !getSession().mo44847a()) {
                this.mInterruptionCount = new AtomicInteger(0);
            }
            if (activity != null) {
                ComponentName callingActivity = activity.getCallingActivity();
                if (callingActivity != null) {
                    str2 = callingActivity.getPackageName();
                } else {
                    str2 = null;
                }
                if (activity.getIntent() != null) {
                    str = activity.getIntent().getDataString();
                    if (this.mLaunchUri == null) {
                        this.mLaunchUri = activity.getIntent().getData();
                    }
                    if (this.mLaunchAction == null) {
                        this.mLaunchAction = activity.getIntent().getAction();
                    }
                    if (!(activity.getIntent().getExtras() == null || activity.getIntent().getExtras().getBundle("al_applink_data") == null)) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("al_applink_data", MPUtility.wrapExtras(activity.getIntent().getExtras().getBundle("al_applink_data")));
                        } catch (Exception e) {
                        }
                        str3 = jSONObject.toString();
                    }
                } else {
                    str = null;
                }
            } else {
                str = null;
                str2 = null;
            }
            this.mCurrentSession.mo44846a(this.mLastStoppedTime, getTime());
            if (!mInitialized) {
                mInitialized = true;
                logStateTransition("app_init", this.mCurrentActivityName, 0, 0, str, str3, str2, 0);
                this.mLastForegroundTime = getTime();
            } else if (isBackgrounded() && this.mLastStoppedTime.get() > 0) {
                logStateTransition("app_fore", this.mCurrentActivityName, this.mLastStoppedTime.get() - this.mLastForegroundTime, getTime() - this.mLastStoppedTime.get(), str, str3, str2, i);
                ConfigManager.log(LogLevel.DEBUG, "App foregrounded.");
                this.mLastForegroundTime = getTime();
            }
            if (this.mCurrentActivityReference != null) {
                this.mCurrentActivityReference.clear();
                this.mCurrentActivityReference = null;
            }
            this.mCurrentActivityReference = new WeakReference<>(activity);
            if (MParticle.getInstance().isAutoTrackingEnabled().booleanValue()) {
                MParticle.getInstance().logScreen(this.mCurrentActivityName);
            }
            MParticle.getInstance().getKitManager().onActivityResumed(activity);
        } catch (Exception e2) {
            if (BuildConfig.MP_DEBUG.booleanValue()) {
                ConfigManager.log(LogLevel.ERROR, "Failed while trying to track activity resume: " + e2.getMessage());
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        try {
            this.mPreferences.edit().putBoolean("mp::crashed_in_foreground", false).apply();
            this.mLastStoppedTime = new AtomicLong(getTime());
            if (this.mCurrentActivityReference != null && activity == this.mCurrentActivityReference.get()) {
                this.mCurrentActivityReference.clear();
                this.mCurrentActivityReference = null;
            }
            this.delayedBackgroundCheckHandler.postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (AppStateManager.this.isBackgrounded()) {
                            AppStateManager.this.checkSessionTimeout();
                            AppStateManager.this.logBackgrounded();
                        }
                    } catch (Exception e) {
                    }
                }
            }, 1000);
            if (MParticle.getInstance().isAutoTrackingEnabled().booleanValue()) {
                MParticle.getInstance().logScreen(new Builder(getActivityName(activity)).internalNavigationDirection(false).build());
            }
            MParticle.getInstance().getKitManager().onActivityPaused(activity);
        } catch (Exception e) {
            if (BuildConfig.MP_DEBUG.booleanValue()) {
                ConfigManager.log(LogLevel.ERROR, "Failed while trying to track activity pause: " + e.getMessage());
            }
        }
    }

    public void ensureActiveSession() {
        Session session = getSession();
        session.f3730d = System.currentTimeMillis();
        if (!session.mo44847a()) {
            newSession();
        } else {
            this.mMessageManager.mo44804a(getSession());
        }
    }

    /* access modifiers changed from: 0000 */
    public void logStateTransition(String transitionType, String currentActivity, long previousForegroundTime, long suspendedTime, String dataString, String launchParameters, String launchPackage, int interruptions) {
        if (this.mConfigManager.isEnabled()) {
            ensureActiveSession();
            this.mMessageManager.mo44795a(transitionType, currentActivity, dataString, launchParameters, launchPackage, previousForegroundTime, suspendedTime, interruptions);
        }
    }

    public void logStateTransition(String transitionType, String currentActivity) {
        logStateTransition(transitionType, currentActivity, 0, 0, null, null, null, 0);
    }

    private void newSession() {
        startSession();
        this.mMessageManager.mo44819e();
        ConfigManager.log(LogLevel.DEBUG, "Started new session");
        this.mMessageManager.mo44823j();
        enableLocationTracking();
        checkSessionTimeout();
    }

    private void enableLocationTracking() {
        if (this.mPreferences.contains("mp::location:provider")) {
            String string = this.mPreferences.getString("mp::location:provider", null);
            long j = this.mPreferences.getLong("mp::location:mintime", 0);
            long j2 = this.mPreferences.getLong("mp::location:mindistance", 0);
            if (string != null && j > 0 && j2 > 0) {
                MParticle.getInstance().enableLocationTracking(string, j, j2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void checkSessionTimeout() {
        this.delayedBackgroundCheckHandler.postDelayed(new Runnable() {
            public void run() {
                Session session = AppStateManager.this.getSession();
                if (0 != session.f3729c && AppStateManager.this.isBackgrounded() && session.mo44848a(AppStateManager.this.mConfigManager.getSessionTimeout()) && !MParticle.getInstance().Media().getAudioPlaying()) {
                    ConfigManager.log(LogLevel.DEBUG, "Session timed out");
                    AppStateManager.this.endSession();
                }
            }
        }, (long) this.mConfigManager.getSessionTimeout());
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        MParticle.getInstance().getKitManager().onActivityCreated(activity, savedInstanceState);
    }

    public void onActivityStarted(Activity activity) {
        MParticle.getInstance().getKitManager().onActivityStarted(activity);
    }

    public void onActivityStopped(Activity activity) {
        MParticle.getInstance().getKitManager().onActivityStopped(activity);
    }

    /* access modifiers changed from: private */
    public void logBackgrounded() {
        logStateTransition("app_back", this.mCurrentActivityName);
        this.mCurrentActivityName = null;
        ConfigManager.log(LogLevel.DEBUG, "App backgrounded.");
        this.mInterruptionCount.incrementAndGet();
    }

    @TargetApi(14)
    private void setupLifecycleCallbacks() {
        ((Application) this.mContext).registerActivityLifecycleCallbacks(new C4608f(this));
    }

    public boolean isBackgrounded() {
        return !mInitialized || (this.mCurrentActivityReference == null && getTime() - this.mLastStoppedTime.get() >= 1000);
    }

    private static String getActivityName(Activity activity) {
        return activity.getClass().getCanonicalName();
    }

    public String getCurrentActivityName() {
        return this.mCurrentActivityName;
    }

    public Session getSession() {
        return this.mCurrentSession;
    }

    public void endSession() {
        ConfigManager.log(LogLevel.DEBUG, "Ended session");
        this.mMessageManager.mo44813b(this.mCurrentSession);
        disableLocationTracking();
        this.mCurrentSession = new Session();
    }

    private void disableLocationTracking() {
        this.mPreferences.edit().remove("mp::location:provider").remove("mp::location:mintime").remove("mp::location:mindistance").apply();
        MParticle.getInstance().disableLocationTracking();
    }

    public void startSession() {
        this.mCurrentSession = new Session().mo44849b();
        enableLocationTracking();
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        MParticle.getInstance().getKitManager().onActivitySaveInstanceState(activity, outState);
    }

    public void onActivityDestroyed(Activity activity) {
        MParticle.getInstance().getKitManager().onActivityDestroyed(activity);
    }

    public WeakReference<Activity> getCurrentActivity() {
        return this.mCurrentActivityReference;
    }
}
