package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideHostStatsJitneyLoggerFactory implements Factory<HostStatsJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideHostStatsJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideHostStatsJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public HostStatsJitneyLogger get() {
        return (HostStatsJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideHostStatsJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<HostStatsJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideHostStatsJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static HostStatsJitneyLogger proxyProvideHostStatsJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideHostStatsJitneyLogger(loggingContextFactory);
    }
}
