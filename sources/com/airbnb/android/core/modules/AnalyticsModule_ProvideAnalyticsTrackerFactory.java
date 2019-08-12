package com.airbnb.android.core.modules;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideAnalyticsTrackerFactory implements Factory<Tracker> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideAnalyticsTrackerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final AnalyticsModule module;

    public AnalyticsModule_ProvideAnalyticsTrackerFactory(AnalyticsModule module2, Provider<Context> contextProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public Tracker get() {
        return (Tracker) Preconditions.checkNotNull(this.module.provideAnalyticsTracker((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Tracker> create(AnalyticsModule module2, Provider<Context> contextProvider2) {
        return new AnalyticsModule_ProvideAnalyticsTrackerFactory(module2, contextProvider2);
    }

    public static Tracker proxyProvideAnalyticsTracker(AnalyticsModule instance, Context context) {
        return instance.provideAnalyticsTracker(context);
    }
}
