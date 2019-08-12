package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.CohostingInvitationJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideCohostingInvitationJitneyLoggerFactory implements Factory<CohostingInvitationJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideCohostingInvitationJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideCohostingInvitationJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public CohostingInvitationJitneyLogger get() {
        return (CohostingInvitationJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideCohostingInvitationJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CohostingInvitationJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideCohostingInvitationJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static CohostingInvitationJitneyLogger proxyProvideCohostingInvitationJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideCohostingInvitationJitneyLogger(loggingContextFactory);
    }
}
