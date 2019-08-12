package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideBusinessTravelJitneyLoggerFactory implements Factory<BusinessTravelJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideBusinessTravelJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideBusinessTravelJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public BusinessTravelJitneyLogger get() {
        return (BusinessTravelJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideBusinessTravelJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<BusinessTravelJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideBusinessTravelJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static BusinessTravelJitneyLogger proxyProvideBusinessTravelJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideBusinessTravelJitneyLogger(loggingContextFactory);
    }
}
