package com.airbnb.android.host_referrals;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.host_referrals.logging.HostReferralLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class HostReferralsModule_ProvideHostReferralsLoggerFactory implements Factory<HostReferralLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!HostReferralsModule_ProvideHostReferralsLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public HostReferralsModule_ProvideHostReferralsLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public HostReferralLogger get() {
        return (HostReferralLogger) Preconditions.checkNotNull(HostReferralsModule.provideHostReferralsLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<HostReferralLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new HostReferralsModule_ProvideHostReferralsLoggerFactory(loggingContextFactoryProvider2);
    }
}
