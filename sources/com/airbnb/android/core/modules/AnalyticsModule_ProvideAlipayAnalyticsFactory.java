package com.airbnb.android.core.modules;

import com.airbnb.android.core.analytics.AlipayAnalytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AnalyticsModule_ProvideAlipayAnalyticsFactory implements Factory<AlipayAnalytics> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideAlipayAnalyticsFactory.class.desiredAssertionStatus());
    private final AnalyticsModule module;

    public AnalyticsModule_ProvideAlipayAnalyticsFactory(AnalyticsModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public AlipayAnalytics get() {
        return (AlipayAnalytics) Preconditions.checkNotNull(this.module.provideAlipayAnalytics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AlipayAnalytics> create(AnalyticsModule module2) {
        return new AnalyticsModule_ProvideAlipayAnalyticsFactory(module2);
    }

    public static AlipayAnalytics proxyProvideAlipayAnalytics(AnalyticsModule instance) {
        return instance.provideAlipayAnalytics();
    }
}
