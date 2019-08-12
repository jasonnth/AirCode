package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvidePerformanceLoggerFactory implements Factory<PerformanceLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvidePerformanceLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final CoreModule module;
    private final Provider<SharedPrefsHelper> prefsHelperProvider;

    public CoreModule_ProvidePerformanceLoggerFactory(CoreModule module2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<SharedPrefsHelper> prefsHelperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                if ($assertionsDisabled || prefsHelperProvider2 != null) {
                    this.prefsHelperProvider = prefsHelperProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public PerformanceLogger get() {
        return (PerformanceLogger) Preconditions.checkNotNull(this.module.providePerformanceLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get(), (SharedPrefsHelper) this.prefsHelperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PerformanceLogger> create(CoreModule module2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<SharedPrefsHelper> prefsHelperProvider2) {
        return new CoreModule_ProvidePerformanceLoggerFactory(module2, loggingContextFactoryProvider2, prefsHelperProvider2);
    }
}
