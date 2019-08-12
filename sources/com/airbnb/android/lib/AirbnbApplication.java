package com.airbnb.android.lib;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.JodaTimeInitializer;
import com.airbnb.android.core.LogAirInitializer;
import com.airbnb.android.core.adtracking.GoogleAdvertisingIdProvider;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.airbnb.android.core.modules.CoreModule;
import com.airbnb.android.core.modules.DeferredAppInitExecutor;
import com.airbnb.android.core.modules.NetworkModule;
import com.airbnb.android.core.net.OkHttpInitializer;
import com.airbnb.android.core.utils.ColdStartExperimentDeliverer;
import com.airbnb.android.lib.activities.EntryActivity;
import com.airbnb.android.lib.coldstart.PreloadExecutor.Builder;
import com.airbnb.android.lib.coldstart.preloader.AirActivityPreloader;
import com.airbnb.android.lib.coldstart.preloader.AirActivityWishListPreloader;
import com.airbnb.android.lib.coldstart.preloader.EntryActivityPreloader;
import com.airbnb.android.lib.coldstart.preloader.ExperimentPreloader;
import com.airbnb.android.lib.coldstart.preloader.HomeActivityPreloader;
import com.airbnb.android.react.ReactNativeUtils;
import com.airbnb.p027n2.N2Component;
import com.bumptech.glide.Glide;
import java.util.Collections;

public class AirbnbApplication {
    private static final String TAG = AirbnbApplication.class.getSimpleName();
    protected static AirbnbApplicationFacade instance;
    protected final Application application;
    protected AirbnbGraph component;
    DeferredAppInitExecutor deferredAppInitExecutor;
    LogAirInitializer logAirInitializer;
    protected final OkHttpInitializer okHttpInitializer;
    protected final StethoInitializer stethoInitializer;

    public AirbnbApplication(Application application2, OkHttpInitializer okHttpInitializer2, StethoInitializer stethoInitializer2) {
        this.application = application2;
        this.okHttpInitializer = okHttpInitializer2;
        this.stethoInitializer = stethoInitializer2;
        this.deferredAppInitExecutor = new DeferredAppInitExecutor(application2, Collections.singletonList(EntryActivity.class));
    }

    public void onCreate() {
        long onCreateTimeMillis = System.currentTimeMillis();
        instance = new AirbnbApplicationFacade(this);
        this.deferredAppInitExecutor.preloadResources(this.application);
        ReactNativeUtils.initializeReactAnnotationsToPreventSamsungLollipopCrash();
        CoreApplication.init(instance);
        this.stethoInitializer.call(this.application);
        buildComponentAndInject();
        this.logAirInitializer.init();
        BugsnagInitializer.init(this.component.loggingContextFactory(), this, this.application, onCreateTimeMillis, this.component.viewBreadcrumbManager());
        this.application.registerActivityLifecycleCallbacks(this.component.appForegroundDetector());
        JodaTimeInitializer.initalize(this.application);
        ColdStartExperimentDeliverer.init();
        this.deferredAppInitExecutor.onApplicationCreate(this.application, component().appForegroundDetector(), component().clientSessionValidator(), component().appForegroundAnalytics(), component().needsPostInteractiveInitialization(), this.component.needsPostApplicationCreatedInitialization(), component().appLaunchAnalytics(), onCreateTimeMillis);
        if (ColdStartExperimentDeliverer.isInTreatmentGroup()) {
            initializeWithOptimization();
        } else {
            initialize();
        }
        GoogleAdvertisingIdProvider.init(this.application);
        MParticleAnalytics.start(this.application);
    }

    private void initialize() {
        ReactNativeUtils.safeInitialize(this.application, this.component.reactInstanceManager(), this.component.loggingContextFactory(), this.component.sharedPrefsHelper());
    }

    private void initializeWithOptimization() {
        new Builder().addPreloaders(new EntryActivityPreloader(this.component), new AirActivityPreloader(this.component)).addPreloaders(new AirActivityWishListPreloader(this.component), new HomeActivityPreloader(this.component)).addPreloaders(new ExperimentPreloader(this.component)).delayedTask(AirbnbApplication$$Lambda$1.lambdaFactory$(this)).build().execute();
    }

    public boolean isTestApplication() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void buildComponentAndInject() {
        this.component = DaggerAirbnbComponent.builder().coreModule(new CoreModule(this.application)).networkModule(new NetworkModule(this.okHttpInitializer)).build();
        this.component.inject(this);
        ((N2Component.Builder) this.component.n2ComponentProvider().get()).build().createN2Context();
    }

    public AirbnbGraph component() {
        return this.component;
    }

    public static AirbnbApplicationFacade instance() {
        return instance;
    }

    public static AirbnbApplicationFacade instance(Context context) {
        return instance;
    }

    public static Application appContext() {
        return instance.application();
    }

    public static Application appContext(Context context) {
        return instance.application();
    }

    public void onTrimMemory(int level) {
        Log.w(TAG, "========= onTrimMemory warning received, level = " + level + " =========");
        Glide.get(this.application).trimMemory(level);
    }
}
