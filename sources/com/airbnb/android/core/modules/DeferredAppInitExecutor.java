package com.airbnb.android.core.modules;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import android.util.Log;
import com.airbnb.android.core.AppForegroundDetector;
import com.airbnb.android.core.AppForegroundDetector.AppForegroundListener;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import com.airbnb.android.core.PostInteractiveInitializer;
import com.airbnb.android.core.analytics.AppForegroundAnalytics;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.airbnb.android.core.deeplinks.DeepLinkReceiver;
import com.airbnb.android.core.monitor.ExecutorMonitor;
import com.airbnb.android.core.utils.ClientSessionValidator;
import com.airbnb.android.core.utils.ColdStartExperimentDeliverer;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.utils.AirbnbConstants;
import com.appboy.Appboy;
import com.appboy.AppboyLifecycleCallbackListener;
import com.appboy.support.AppboyLogger;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DeferredAppInitExecutor implements ActivityLifecycleCallbacks {
    private static final String ASYNCTASK_THREAD_POOL_EXECUTOR_TAG = "asynctask_thread_pool_executor";
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE;
    private static final BlockingQueue<Runnable> POOL_WORK_QUEUE = new LinkedBlockingQueue(128);
    private static final long POST_INITIALIZATION_TASK_DELAY_SECONDS = 1;
    public static final boolean SHOULD_PRELOAD_APP_BOY;
    public static final boolean SHOULD_PRELOAD_JODA;
    public static final boolean SHOULD_PRELOAD_SHARED_PREFERENCES;
    private static final String TAG = "DeferredAppInitExecutor";
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger count = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "DeferredAppInitExecutorTask #" + this.count.getAndIncrement());
        }
    };
    private static final Executor THREAD_POOL_EXECUTOR = AsyncTask.THREAD_POOL_EXECUTOR;
    private final Application application;
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 30, TimeUnit.SECONDS, POOL_WORK_QUEUE, THREAD_FACTORY);
    private Lazy<Set<PostApplicationCreatedInitializer>> lazyPostApplicationCreatedInitializers;
    private Lazy<Set<PostInteractiveInitializer>> lazyPostInteractiveInitializers;
    private final Set<Class<? extends Activity>> nonInteractiveActivities;
    private final AtomicBoolean scheduledColdStart = new AtomicBoolean(false);
    private final AtomicBoolean scheduledPostApplicationCreatedTasks = new AtomicBoolean(false);
    private final AtomicBoolean scheduledPostInitializeTasks = new AtomicBoolean(false);

    static {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (Math.random() < 0.5d) {
            z = true;
        } else {
            z = false;
        }
        SHOULD_PRELOAD_SHARED_PREFERENCES = z;
        if (Math.random() < 0.5d) {
            z2 = true;
        } else {
            z2 = false;
        }
        SHOULD_PRELOAD_JODA = z2;
        if (Math.random() >= 0.5d) {
            z3 = false;
        }
        SHOULD_PRELOAD_APP_BOY = z3;
    }

    public DeferredAppInitExecutor(Application application2, Collection<Class<? extends Activity>> nonInteractiveActivities2) {
        this.application = application2;
        this.nonInteractiveActivities = new HashSet(nonInteractiveActivities2);
    }

    private void setPostAppCreatedInitializers(Lazy<Set<PostApplicationCreatedInitializer>> lazyPostApplicationCreatedInitializers2) {
        this.lazyPostApplicationCreatedInitializers = lazyPostApplicationCreatedInitializers2;
    }

    private void setPostInteractiveInitializers(Lazy<Set<PostInteractiveInitializer>> lazyPostInteractiveInitializers2) {
        this.lazyPostInteractiveInitializers = lazyPostInteractiveInitializers2;
    }

    public void preloadResources(Context context) {
        if (SHOULD_PRELOAD_JODA) {
            this.executor.execute(DeferredAppInitExecutor$$Lambda$1.lambdaFactory$(context));
        }
        if (SHOULD_PRELOAD_SHARED_PREFERENCES) {
            this.executor.execute(DeferredAppInitExecutor$$Lambda$2.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$preloadResources$1(DeferredAppInitExecutor deferredAppInitExecutor) {
        ExecutorMonitor.watch(THREAD_POOL_EXECUTOR, ASYNCTASK_THREAD_POOL_EXECUTOR_TAG);
        deferredAppInitExecutor.application.getSharedPreferences(AirbnbConstants.AIRBNB_PREFS, 0);
        deferredAppInitExecutor.application.getSharedPreferences(AirbnbConstants.AIRBNB_GLOBAL_PREFS, 0);
        Trebuchet.getTrebuchetPrefs(deferredAppInitExecutor.application);
        Trebuchet.getLegacyTrebuchetPrefs(deferredAppInitExecutor.application);
        deferredAppInitExecutor.application.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0);
    }

    public void onApplicationCreate(Application application2, AppForegroundDetector appForegroundDetector, Lazy<ClientSessionValidator> clientSessionValidator, Lazy<AppForegroundAnalytics> appForegroundAnalytics, Lazy<Set<PostInteractiveInitializer>> lazyPostInteractiveInitializers2, Lazy<Set<PostApplicationCreatedInitializer>> lazyPostApplicationCreatedInitializers2, Lazy<AppLaunchAnalytics> appLaunchAnalytics, long onCreateTimeMillis) {
        application2.registerActivityLifecycleCallbacks(this);
        setPostInteractiveInitializers(lazyPostInteractiveInitializers2);
        setPostAppCreatedInitializers(lazyPostApplicationCreatedInitializers2);
        trackColdLaunchStart(onCreateTimeMillis, appLaunchAnalytics);
        registerReceiversAndListeners(application2, appForegroundDetector, clientSessionValidator, appForegroundAnalytics);
        if (!ColdStartExperimentDeliverer.isInTreatmentGroup()) {
            schedulePostApplicationCreatedInitialization();
        }
        this.scheduledColdStart.set(true);
        shutDownIfAllScheduled();
    }

    private void trackColdLaunchStart(long onCreateTimeMillis, Lazy<AppLaunchAnalytics> appLaunchAnalytics) {
        this.executor.execute(DeferredAppInitExecutor$$Lambda$3.lambdaFactory$(appLaunchAnalytics, onCreateTimeMillis));
    }

    private void registerReceiversAndListeners(Context context, AppForegroundDetector appForegroundDetector, Lazy<ClientSessionValidator> clientSessionValidator, Lazy<AppForegroundAnalytics> appForegroundAnalytics) {
        this.executor.execute(DeferredAppInitExecutor$$Lambda$4.lambdaFactory$());
        this.executor.execute(DeferredAppInitExecutor$$Lambda$5.lambdaFactory$(this, appForegroundDetector, clientSessionValidator, appForegroundAnalytics, context));
    }

    static /* synthetic */ void lambda$registerReceiversAndListeners$4(DeferredAppInitExecutor deferredAppInitExecutor, AppForegroundDetector appForegroundDetector, Lazy clientSessionValidator, Lazy appForegroundAnalytics, Context context) {
        appForegroundDetector.registerAppForegroundListener((AppForegroundListener) clientSessionValidator.get());
        appForegroundDetector.registerAppForegroundListener((AppForegroundListener) appForegroundAnalytics.get());
        LocalBroadcastManager.getInstance(deferredAppInitExecutor.application).registerReceiver(new DeepLinkReceiver(), new IntentFilter("com.airbnb.deeplinkdispatch.DEEPLINK_ACTION"));
        deferredAppInitExecutor.application.registerActivityLifecycleCallbacks(new AppboyLifecycleCallbackListener());
        AppboyLogger.setLogLevel(6);
        if (SHOULD_PRELOAD_APP_BOY) {
            Appboy.getInstance(context);
        }
    }

    public void schedulePostApplicationCreatedInitialization() {
        this.executor.execute(DeferredAppInitExecutor$$Lambda$6.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$schedulePostApplicationCreatedInitialization$6(DeferredAppInitExecutor deferredAppInitExecutor) {
        for (PostApplicationCreatedInitializer initializer : (Set) deferredAppInitExecutor.lazyPostApplicationCreatedInitializers.get()) {
            deferredAppInitExecutor.executor.execute(DeferredAppInitExecutor$$Lambda$10.lambdaFactory$(deferredAppInitExecutor, initializer));
        }
        deferredAppInitExecutor.scheduledPostApplicationCreatedTasks.set(true);
        deferredAppInitExecutor.shutDownIfAllScheduled();
    }

    static /* synthetic */ void lambda$null$5(DeferredAppInitExecutor deferredAppInitExecutor, PostApplicationCreatedInitializer initializer) {
        initializer.initialize();
        deferredAppInitExecutor.logInitialization(initializer);
    }

    private void schedulePostInitializeTasks() {
        this.executor.execute(DeferredAppInitExecutor$$Lambda$7.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$null$8(DeferredAppInitExecutor deferredAppInitExecutor) {
        for (PostInteractiveInitializer initializer : (Set) deferredAppInitExecutor.lazyPostInteractiveInitializers.get()) {
            deferredAppInitExecutor.executor.execute(DeferredAppInitExecutor$$Lambda$9.lambdaFactory$(deferredAppInitExecutor, initializer));
        }
        deferredAppInitExecutor.scheduledPostInitializeTasks.set(true);
        deferredAppInitExecutor.shutDownIfAllScheduled();
    }

    static /* synthetic */ void lambda$null$7(DeferredAppInitExecutor deferredAppInitExecutor, PostInteractiveInitializer initializer) {
        initializer.initialize();
        deferredAppInitExecutor.logInitialization(initializer);
    }

    private void shutDownIfAllScheduled() {
        if (this.scheduledColdStart.get() && this.scheduledPostApplicationCreatedTasks.get() && this.scheduledPostInitializeTasks.get()) {
            this.executor.shutdown();
        }
    }

    private void logInitialization(Object initalizer) {
        Log.v(TAG, "Initialized: " + initalizer.getClass().getSimpleName());
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        if (!this.nonInteractiveActivities.contains(activity.getClass())) {
            schedulePostInitializeTasks();
            this.application.unregisterActivityLifecycleCallbacks(this);
        }
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
