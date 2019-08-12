package com.airbnb.android.core.modules;

import com.airbnb.android.core.AnalyticsRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoreModule_ProvideAnalyticsRegistryFactory implements Factory<AnalyticsRegistry> {
    private static final CoreModule_ProvideAnalyticsRegistryFactory INSTANCE = new CoreModule_ProvideAnalyticsRegistryFactory();

    public AnalyticsRegistry get() {
        return (AnalyticsRegistry) Preconditions.checkNotNull(CoreModule.provideAnalyticsRegistry(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AnalyticsRegistry> create() {
        return INSTANCE;
    }

    public static AnalyticsRegistry proxyProvideAnalyticsRegistry() {
        return CoreModule.provideAnalyticsRegistry();
    }
}
