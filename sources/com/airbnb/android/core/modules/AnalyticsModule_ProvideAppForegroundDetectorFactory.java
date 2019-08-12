package com.airbnb.android.core.modules;

import com.airbnb.android.core.AppForegroundDetector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AnalyticsModule_ProvideAppForegroundDetectorFactory implements Factory<AppForegroundDetector> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideAppForegroundDetectorFactory.class.desiredAssertionStatus());
    private final AnalyticsModule module;

    public AnalyticsModule_ProvideAppForegroundDetectorFactory(AnalyticsModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public AppForegroundDetector get() {
        return (AppForegroundDetector) Preconditions.checkNotNull(this.module.provideAppForegroundDetector(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AppForegroundDetector> create(AnalyticsModule module2) {
        return new AnalyticsModule_ProvideAppForegroundDetectorFactory(module2);
    }

    public static AppForegroundDetector proxyProvideAppForegroundDetector(AnalyticsModule instance) {
        return instance.provideAppForegroundDetector();
    }
}
