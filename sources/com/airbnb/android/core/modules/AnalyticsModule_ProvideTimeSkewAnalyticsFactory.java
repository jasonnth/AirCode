package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.analytics.TimeSkewAnalytics;
import com.airbnb.android.core.services.NetworkTimeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideTimeSkewAnalyticsFactory implements Factory<TimeSkewAnalytics> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideTimeSkewAnalyticsFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> airbnbPreferencesProvider;
    private final AnalyticsModule module;
    private final Provider<NetworkTimeProvider> networkTimeProvider;

    public AnalyticsModule_ProvideTimeSkewAnalyticsFactory(AnalyticsModule module2, Provider<AirbnbPreferences> airbnbPreferencesProvider2, Provider<NetworkTimeProvider> networkTimeProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || airbnbPreferencesProvider2 != null) {
                this.airbnbPreferencesProvider = airbnbPreferencesProvider2;
                if ($assertionsDisabled || networkTimeProvider2 != null) {
                    this.networkTimeProvider = networkTimeProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public TimeSkewAnalytics get() {
        return (TimeSkewAnalytics) Preconditions.checkNotNull(this.module.provideTimeSkewAnalytics((AirbnbPreferences) this.airbnbPreferencesProvider.get(), (NetworkTimeProvider) this.networkTimeProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<TimeSkewAnalytics> create(AnalyticsModule module2, Provider<AirbnbPreferences> airbnbPreferencesProvider2, Provider<NetworkTimeProvider> networkTimeProvider2) {
        return new AnalyticsModule_ProvideTimeSkewAnalyticsFactory(module2, airbnbPreferencesProvider2, networkTimeProvider2);
    }

    public static TimeSkewAnalytics proxyProvideTimeSkewAnalytics(AnalyticsModule instance, AirbnbPreferences airbnbPreferences, NetworkTimeProvider networkTimeProvider2) {
        return instance.provideTimeSkewAnalytics(airbnbPreferences, networkTimeProvider2);
    }
}
