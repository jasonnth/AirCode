package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideIdentityJitneyEventLoggerFactory implements Factory<IdentityJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideIdentityJitneyEventLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<ObjectMapper> objectMapperProvider;

    public AnalyticsModule_ProvideIdentityJitneyEventLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<ObjectMapper> objectMapperProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            if ($assertionsDisabled || objectMapperProvider2 != null) {
                this.objectMapperProvider = objectMapperProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public IdentityJitneyLogger get() {
        return (IdentityJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideIdentityJitneyEventLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get(), (ObjectMapper) this.objectMapperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<IdentityJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<ObjectMapper> objectMapperProvider2) {
        return new AnalyticsModule_ProvideIdentityJitneyEventLoggerFactory(loggingContextFactoryProvider2, objectMapperProvider2);
    }

    public static IdentityJitneyLogger proxyProvideIdentityJitneyEventLogger(LoggingContextFactory loggingContextFactory, ObjectMapper objectMapper) {
        return AnalyticsModule.provideIdentityJitneyEventLogger(loggingContextFactory, objectMapper);
    }
}
