package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.CommunityCommitmentJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideCommunityCommitmentJitneyLoggerFactory implements Factory<CommunityCommitmentJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideCommunityCommitmentJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideCommunityCommitmentJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public CommunityCommitmentJitneyLogger get() {
        return (CommunityCommitmentJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideCommunityCommitmentJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CommunityCommitmentJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideCommunityCommitmentJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static CommunityCommitmentJitneyLogger proxyProvideCommunityCommitmentJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideCommunityCommitmentJitneyLogger(loggingContextFactory);
    }
}
