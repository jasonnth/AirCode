package com.mparticle.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import com.mparticle.MPEvent;
import com.mparticle.MParticle.IdentityType;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.commerce.CommerceEvent;
import com.mparticle.internal.PushRegistrationHelper.PushRegistration;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;

public class KitFrameworkWrapper implements KitManager {
    private static volatile boolean kitsLoaded = false;
    private static C4607e kitsLoadedListener;
    private Queue<C4594a> attributeQueue;
    private Queue eventQueue;
    private volatile boolean frameworkLoadAttempted = false;
    private final AppStateManager mAppStateManager;
    private final ConfigManager mConfigManager;
    private final Context mContext;
    private KitManager mKitManager;
    private final ReportingManager mReportingManager;
    private volatile boolean registerForPush = false;
    private volatile boolean shouldCheckForDeepLink = false;

    /* renamed from: com.mparticle.internal.KitFrameworkWrapper$a */
    static class C4594a {

        /* renamed from: a */
        final String f3692a;

        /* renamed from: b */
        final Object f3693b;

        /* renamed from: c */
        final boolean f3694c;

        C4594a(String str, Object obj) {
            this.f3692a = str;
            this.f3693b = obj;
            this.f3694c = false;
        }

        C4594a(String str) {
            this.f3692a = str;
            this.f3693b = null;
            this.f3694c = true;
        }
    }

    public KitFrameworkWrapper(Context context, ReportingManager reportingManager, ConfigManager configManager, AppStateManager appStateManager) {
        this.mContext = context;
        this.mReportingManager = reportingManager;
        this.mConfigManager = configManager;
        this.mAppStateManager = appStateManager;
        kitsLoaded = false;
    }

    public void loadKitLibrary() {
        if (!this.frameworkLoadAttempted) {
            ConfigManager.log(LogLevel.DEBUG, "Loading Kit Framework.");
            this.frameworkLoadAttempted = true;
            try {
                this.mKitManager = (KitManager) Class.forName("com.mparticle.kits.KitManagerImpl").getConstructor(new Class[]{Context.class, ReportingManager.class, ConfigManager.class, AppStateManager.class}).newInstance(new Object[]{this.mContext, this.mReportingManager, this.mConfigManager, this.mAppStateManager});
                JSONArray latestKitConfiguration = this.mConfigManager.getLatestKitConfiguration();
                ConfigManager.log(LogLevel.DEBUG, "Kit Framework loaded.");
                if (latestKitConfiguration != null) {
                    ConfigManager.log(LogLevel.DEBUG, "Restoring previous Kit configuration.");
                    updateKits(latestKitConfiguration);
                }
            } catch (Exception e) {
                ConfigManager.log(LogLevel.DEBUG, "No Kit Framework detected.");
                disableQueuing();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean getFrameworkLoadAttempted() {
        return this.frameworkLoadAttempted;
    }

    /* access modifiers changed from: 0000 */
    public Queue getEventQueue() {
        return this.eventQueue;
    }

    /* access modifiers changed from: 0000 */
    public Queue<C4594a> getAttributeQueue() {
        return this.attributeQueue;
    }

    /* access modifiers changed from: 0000 */
    public void setKitManager(KitManager manager) {
        this.mKitManager = manager;
    }

    public static boolean getKitsLoaded() {
        return kitsLoaded;
    }

    public static void setKitsLoadedListener(C4607e listener) {
        if (kitsLoaded) {
            listener.mo44540a();
        } else {
            kitsLoadedListener = listener;
        }
    }

    /* access modifiers changed from: 0000 */
    public void setKitsLoaded(boolean kitsLoaded2) {
        kitsLoaded = kitsLoaded2;
        if (kitsLoadedListener != null) {
            kitsLoadedListener.mo44540a();
            kitsLoadedListener = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public synchronized void disableQueuing() {
        setKitsLoaded(true);
        if (this.eventQueue != null) {
            this.eventQueue.clear();
            this.eventQueue = null;
            ConfigManager.log(LogLevel.DEBUG, "Kit initialization complete. Disabling event queueing.");
        }
        if (this.attributeQueue != null) {
            this.attributeQueue.clear();
            this.attributeQueue = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void replayEvents() {
        if (this.mKitManager != null) {
            if (this.registerForPush) {
                PushRegistration latestPushRegistration = PushRegistrationHelper.getLatestPushRegistration(this.mContext);
                if (latestPushRegistration != null) {
                    this.mKitManager.onPushRegistration(latestPushRegistration.instanceId, latestPushRegistration.senderId);
                }
            }
            if (this.shouldCheckForDeepLink) {
                this.mKitManager.checkForDeepLink();
            }
            if (this.eventQueue != null && this.eventQueue.size() > 0) {
                ConfigManager.log(LogLevel.DEBUG, "Replaying events after receiving first kit configuration.");
                for (Object next : this.eventQueue) {
                    if (next instanceof MPEvent) {
                        MPEvent mPEvent = (MPEvent) next;
                        if (mPEvent.isScreenEvent()) {
                            this.mKitManager.logScreen(mPEvent);
                        } else {
                            this.mKitManager.logEvent(mPEvent);
                        }
                    } else if (next instanceof CommerceEvent) {
                        this.mKitManager.logCommerceEvent((CommerceEvent) next);
                    }
                }
            }
            if (this.attributeQueue != null && this.attributeQueue.size() > 0) {
                ConfigManager.log(LogLevel.DEBUG, "Replaying user attributes after receiving first kit configuration.");
                for (C4594a aVar : this.attributeQueue) {
                    if (aVar.f3694c) {
                        this.mKitManager.removeUserAttribute(aVar.f3692a);
                    } else if (aVar.f3693b == null) {
                        this.mKitManager.setUserAttribute(aVar.f3692a, null);
                    } else if (aVar.f3693b instanceof String) {
                        this.mKitManager.setUserAttribute(aVar.f3692a, (String) aVar.f3693b);
                    } else if (aVar.f3693b instanceof List) {
                        this.mKitManager.setUserAttributeList(aVar.f3692a, (List) aVar.f3693b);
                    }
                }
            }
        }
    }

    public synchronized void replayAndDisableQueue() {
        setKitsLoaded(true);
        replayEvents();
        disableQueuing();
    }

    /* access modifiers changed from: 0000 */
    public synchronized boolean queueEvent(Object event) {
        boolean z = false;
        synchronized (this) {
            if (!getKitsLoaded()) {
                if (this.eventQueue == null) {
                    this.eventQueue = new ConcurrentLinkedQueue();
                }
                if (this.eventQueue.size() < 10) {
                    ConfigManager.log(LogLevel.DEBUG, "Queuing Kit event while waiting for initial configuration.");
                    this.eventQueue.add(event);
                }
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public boolean queueAttribute(String key, Object value) {
        return queueAttribute(new C4594a(key, value));
    }

    /* access modifiers changed from: 0000 */
    public boolean queueAttribute(String key) {
        return queueAttribute(new C4594a(key));
    }

    /* access modifiers changed from: 0000 */
    public synchronized boolean queueAttribute(C4594a change) {
        boolean z;
        if (getKitsLoaded()) {
            z = false;
        } else {
            if (this.attributeQueue == null) {
                this.attributeQueue = new ConcurrentLinkedQueue();
            }
            this.attributeQueue.add(change);
            z = true;
        }
        return z;
    }

    public WeakReference<Activity> getCurrentActivity() {
        return this.mAppStateManager.getCurrentActivity();
    }

    public void logEvent(MPEvent event) {
        if (!queueEvent(event) && this.mKitManager != null) {
            this.mKitManager.logEvent(event);
        }
    }

    public void logCommerceEvent(CommerceEvent event) {
        if (!queueEvent(event) && this.mKitManager != null) {
            this.mKitManager.logCommerceEvent(event);
        }
    }

    public void logScreen(MPEvent screenEvent) {
        if (!queueEvent(screenEvent) && this.mKitManager != null) {
            this.mKitManager.logScreen(screenEvent);
        }
    }

    public void leaveBreadcrumb(String breadcrumb) {
        if (this.mKitManager != null) {
            this.mKitManager.leaveBreadcrumb(breadcrumb);
        }
    }

    public void logError(String message, Map<String, String> eventData) {
        if (this.mKitManager != null) {
            this.mKitManager.logError(message, eventData);
        }
    }

    public void logNetworkPerformance(String url, long startTime, String method, long length, long bytesSent, long bytesReceived, String requestString, int responseCode) {
        if (this.mKitManager != null) {
            this.mKitManager.logNetworkPerformance(url, startTime, method, length, bytesSent, bytesReceived, requestString, responseCode);
        }
    }

    public void checkForDeepLink() {
        if (this.mKitManager == null || !getKitsLoaded()) {
            this.shouldCheckForDeepLink = true;
        } else {
            this.mKitManager.checkForDeepLink();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean getShouldCheckForDeepLink() {
        return this.shouldCheckForDeepLink;
    }

    public void logException(Exception exception, Map<String, String> eventData, String message) {
        if (this.mKitManager != null) {
            this.mKitManager.logException(exception, eventData, message);
        }
    }

    public void setLocation(Location location) {
        if (this.mKitManager != null) {
            this.mKitManager.setLocation(location);
        }
    }

    public void logout() {
        if (this.mKitManager != null) {
            this.mKitManager.logout();
        }
    }

    public void setUserAttribute(String key, String value) {
        if (!queueAttribute(key, value) && this.mKitManager != null) {
            this.mKitManager.setUserAttribute(key, value);
        }
    }

    public void setUserAttributeList(String key, List<String> value) {
        if (!queueAttribute(key, value) && this.mKitManager != null) {
            this.mKitManager.setUserAttributeList(key, value);
        }
    }

    public void removeUserAttribute(String key) {
        if (!queueAttribute(key) && this.mKitManager != null) {
            this.mKitManager.removeUserAttribute(key);
        }
    }

    public void setUserIdentity(String id, IdentityType identityType) {
        if (this.mKitManager != null) {
            this.mKitManager.setUserIdentity(id, identityType);
        }
    }

    public void removeUserIdentity(IdentityType id) {
        if (this.mKitManager != null) {
            this.mKitManager.removeUserIdentity(id);
        }
    }

    public void setOptOut(boolean optOutStatus) {
        if (this.mKitManager != null) {
            this.mKitManager.setOptOut(optOutStatus);
        }
    }

    public Uri getSurveyUrl(int kitId, Map<String, String> userAttributes, Map<String, List<String>> userAttributeLists) {
        if (this.mKitManager != null) {
            return this.mKitManager.getSurveyUrl(kitId, userAttributes, userAttributeLists);
        }
        return null;
    }

    public boolean onMessageReceived(Context context, Intent intent) {
        if (this.mKitManager != null) {
            return this.mKitManager.onMessageReceived(context, intent);
        }
        return false;
    }

    public boolean onPushRegistration(String instanceId, String senderId) {
        if (!getKitsLoaded() || this.mKitManager == null) {
            this.registerForPush = true;
        } else {
            this.mKitManager.onPushRegistration(instanceId, senderId);
        }
        return false;
    }

    public boolean isKitActive(int kitId) {
        if (this.mKitManager != null) {
            return this.mKitManager.isKitActive(kitId);
        }
        return false;
    }

    public Object getKitInstance(int kitId) {
        if (this.mKitManager != null) {
            return this.mKitManager.getKitInstance(kitId);
        }
        return null;
    }

    public Set<Integer> getSupportedKits() {
        if (this.mKitManager != null) {
            return this.mKitManager.getSupportedKits();
        }
        return null;
    }

    public void updateKits(JSONArray kitConfiguration) {
        if (this.mKitManager != null) {
            this.mKitManager.updateKits(kitConfiguration);
        }
    }

    public String getActiveModuleIds() {
        if (this.mKitManager != null) {
            return this.mKitManager.getActiveModuleIds();
        }
        return null;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (this.mKitManager != null) {
            this.mKitManager.onActivityCreated(activity, savedInstanceState);
        }
    }

    public void onActivityStarted(Activity activity) {
        if (this.mKitManager != null) {
            this.mKitManager.onActivityStarted(activity);
        }
    }

    public void onActivityResumed(Activity activity) {
        if (this.mKitManager != null) {
            this.mKitManager.onActivityResumed(activity);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.mKitManager != null) {
            this.mKitManager.onActivityPaused(activity);
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.mKitManager != null) {
            this.mKitManager.onActivityStopped(activity);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if (this.mKitManager != null) {
            this.mKitManager.onActivitySaveInstanceState(activity, outState);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.mKitManager != null) {
            this.mKitManager.onActivityDestroyed(activity);
        }
    }
}
