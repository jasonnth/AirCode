package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.ReviewSearchJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideReviewSearchJitneyLoggerFactory implements Factory<ReviewSearchJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideReviewSearchJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideReviewSearchJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public ReviewSearchJitneyLogger get() {
        return (ReviewSearchJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideReviewSearchJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ReviewSearchJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideReviewSearchJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static ReviewSearchJitneyLogger proxyProvideReviewSearchJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideReviewSearchJitneyLogger(loggingContextFactory);
    }
}
