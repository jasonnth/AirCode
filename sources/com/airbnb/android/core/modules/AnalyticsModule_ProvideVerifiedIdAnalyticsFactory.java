package com.airbnb.android.core.modules;

import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AnalyticsModule_ProvideVerifiedIdAnalyticsFactory implements Factory<VerifiedIdAnalytics> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideVerifiedIdAnalyticsFactory.class.desiredAssertionStatus());
    private final AnalyticsModule module;

    public AnalyticsModule_ProvideVerifiedIdAnalyticsFactory(AnalyticsModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public VerifiedIdAnalytics get() {
        return (VerifiedIdAnalytics) Preconditions.checkNotNull(this.module.provideVerifiedIdAnalytics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<VerifiedIdAnalytics> create(AnalyticsModule module2) {
        return new AnalyticsModule_ProvideVerifiedIdAnalyticsFactory(module2);
    }

    public static VerifiedIdAnalytics proxyProvideVerifiedIdAnalytics(AnalyticsModule instance) {
        return instance.provideVerifiedIdAnalytics();
    }
}
