package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideMessagingJitneyLoggerFactory implements Factory<MessagingJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideMessagingJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideMessagingJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public MessagingJitneyLogger get() {
        return (MessagingJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideMessagingJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MessagingJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideMessagingJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static MessagingJitneyLogger proxyProvideMessagingJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideMessagingJitneyLogger(loggingContextFactory);
    }
}
