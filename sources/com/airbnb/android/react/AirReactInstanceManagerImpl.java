package com.airbnb.android.react;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.view.View;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.react.maps.MapsPackage;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManager.ReactInstanceEventListener;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.uimanager.UIManagerModule;
import com.horcrux.svg.RNSvgPackage;
import com.rt2zz.reactnativecontacts.ReactNativeContacts;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import p005cn.jpush.android.JPushConstants.PushService;
import p032rx.Completable;
import p032rx.Subscription;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.functions.Action0;

public class AirReactInstanceManagerImpl implements AirReactInstanceManager {
    private static final int INITIALIZE_TIMEOUT_DEBUG_SECONDS = 60;
    private static final int INITIALIZE_TIMEOUT_PRODUCTION_SECONDS = 5;
    private static final int INITIALIZE_TIMEOUT_SECONDS = (BuildHelper.isDevelopmentBuild() ? 60 : 5);
    private static final String INIT_EVENT_NAME = "react_native_js_init";
    private static final String SCREEN_PRELOAD_EVENT = "AirbnbNavigation.preloadScreen";
    private final Map<String, String> durations = new ConcurrentHashMap();
    private final ReactFirstRenderIdlingResource firstRenderIdlingResources = new ReactFirstRenderIdlingResource();
    private final PerformanceLogger logger;
    /* access modifiers changed from: private */
    public final ReactInstanceManager reactInstanceManager;
    private final Map<String, Long> startTimes = new ConcurrentHashMap();

    /* access modifiers changed from: private */
    public void logMarker(String name) {
        long time = System.currentTimeMillis();
        if (ReactMarkerConstants.CREATE_REACT_CONTEXT_START.equals(name)) {
            this.logger.markStart(INIT_EVENT_NAME);
        }
        String name2 = name.replaceAll("_[a-zA-Z]+$", "");
        if (!this.startTimes.containsKey(name2) || this.durations.containsKey(name2)) {
            this.startTimes.put(name2, Long.valueOf(time));
        } else {
            long start = ((Long) this.startTimes.get(name2)).longValue();
            this.durations.put(name2, String.format("%s", new Object[]{Long.valueOf(time - start)}));
        }
        if (this.durations.containsKey("CREATE_REACT_CONTEXT") && this.durations.containsKey("RUN_JS_BUNDLE")) {
            this.logger.markCompleted(INIT_EVENT_NAME, this.durations, C2445NativeMeasurementType.ActionDuration, PushService.PARAM_APP);
        }
    }

    public AirReactInstanceManagerImpl(Context context, ReactNativeModulesProvider reactNativeModulesProvider, ReactNavigationCoordinator coordinator, PerformanceLogger logger2) {
        this.logger = logger2;
        ReactMarker.setMarkerListener(AirReactInstanceManagerImpl$$Lambda$1.lambdaFactory$(this));
        Application application = (Application) context.getApplicationContext();
        Fresco.initialize(context);
        this.reactInstanceManager = ReactInstanceManager.builder().setApplication(application).addPackage(new MapsPackage()).addPackage(new ReactVideoPackage()).addPackage(new RNSvgPackage()).addPackage(new ReactNativeContacts()).addPackage(new AirbnbReactPackage(reactNativeModulesProvider, coordinator)).setBundleAssetName("index.js").setJSMainModuleName("index").setUseDeveloperSupport(shouldUseDeveloperSupport(context)).setNativeModuleCallExceptionHandler(new ReactModuleCallExceptionHandler()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).manuallyEnableDevSupport().build();
        enableAnimations(this.reactInstanceManager);
    }

    private void enableAnimations(ReactInstanceManager reactInstanceManager2) {
        reactInstanceManager2.addReactInstanceEventListener(AirReactInstanceManagerImpl$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$enableAnimations$1(ReactContext context) {
        UIManagerModule uiManager = (UIManagerModule) context.getNativeModule(UIManagerModule.class);
        if (uiManager != null) {
            uiManager.setLayoutAnimationEnabledExperimental(true);
        }
    }

    private boolean shouldUseDeveloperSupport(Context context) {
        if (!BuildHelper.isDevelopmentBuild()) {
            return false;
        }
        if (VERSION.SDK_INT < 23 || Settings.canDrawOverlays(context)) {
            return true;
        }
        return false;
    }

    public void preloadScreen(String screenName) {
        emitEventOrQueue(SCREEN_PRELOAD_EVENT, screenName);
    }

    private void emitEventOrQueue(final String name, final Object data) {
        ReactContext reactContext = this.reactInstanceManager.getCurrentReactContext();
        if (reactContext == null || !reactContext.hasActiveCatalystInstance()) {
            this.reactInstanceManager.addReactInstanceEventListener(new ReactInstanceEventListener() {
                public void onReactContextInitialized(ReactContext context) {
                    AirReactInstanceManagerImpl.this.reactInstanceManager.removeReactInstanceEventListener(this);
                    ReactNativeUtils.maybeEmitEvent(context, name, data);
                }
            });
        } else {
            ReactNativeUtils.maybeEmitEvent(reactContext, name, data);
        }
    }

    public void createReactContextInBackground() {
        this.reactInstanceManager.createReactContextInBackground();
    }

    public void onHostPause(Activity activity) {
        this.reactInstanceManager.onHostPause(activity);
    }

    public void onHostResume(Activity activity, Object backBtnHandler) {
        this.reactInstanceManager.onHostResume(activity, (DefaultHardwareBackBtnHandler) backBtnHandler);
    }

    public void onHostDestroy(Activity activity) {
        boolean enabled = this.reactInstanceManager.getDevSupportManager().getDevSupportEnabled();
        this.reactInstanceManager.onHostDestroy(activity);
        this.reactInstanceManager.getDevSupportManager().setDevSupportEnabled(enabled);
    }

    public void onBackPressed() {
        this.reactInstanceManager.onBackPressed();
    }

    public Context getCurrentReactContext() {
        return this.reactInstanceManager.getCurrentReactContext();
    }

    public void startReactApplication(View reactRootView, String moduleName, Bundle props) {
        ((ReactRootView) reactRootView).startReactApplication(this.reactInstanceManager, moduleName, props);
    }

    public void showDevOptionsDialog() {
        this.reactInstanceManager.showDevOptionsDialog();
    }

    public void addReactInstanceEventListener(Action0 action) {
        this.reactInstanceManager.addReactInstanceEventListener(AirReactInstanceManagerImpl$$Lambda$3.lambdaFactory$(Completable.timer((long) INITIALIZE_TIMEOUT_SECONDS, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(action), action));
    }

    static /* synthetic */ void lambda$addReactInstanceEventListener$2(Subscription subscription, Action0 action, ReactContext context) {
        subscription.unsubscribe();
        action.call();
    }

    public boolean isSuccessfullyInitialized() {
        return this.reactInstanceManager.getCurrentReactContext() != null;
    }

    public void setDevSupportEnabled(boolean enabled) {
        this.reactInstanceManager.getDevSupportManager().setDevSupportEnabled(enabled);
    }

    public void signalWaitingForFirstRender(Activity activity) {
        this.firstRenderIdlingResources.signalWaitingForFirstRender(activity);
    }

    public void signalFirstRenderComplete(Activity activity) {
        this.firstRenderIdlingResources.signalFirstRenderComplete(activity);
    }

    public CountingIdlingResource getFirstRenderIdlingResources() {
        return this.firstRenderIdlingResources.getIdlingResource();
    }
}
