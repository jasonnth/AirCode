package com.airbnb.android.react;

import android.app.Application;
import android.content.Context;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ReactModule_ProvideReactInstanceManagerFactory implements Factory<AirReactInstanceManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ReactModule_ProvideReactInstanceManagerFactory.class.desiredAssertionStatus());
    private final Provider<Application> applicationProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ReactNavigationCoordinator> coordinatorProvider;
    private final Provider<PerformanceLogger> loggerProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final ReactModule module;
    private final Provider<ReactNativeModulesProvider> reactNativeModulesProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public ReactModule_ProvideReactInstanceManagerFactory(ReactModule module2, Provider<Context> contextProvider2, Provider<Application> applicationProvider2, Provider<ReactNativeModulesProvider> reactNativeModulesProvider2, Provider<ReactNavigationCoordinator> coordinatorProvider2, Provider<PerformanceLogger> loggerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || applicationProvider2 != null) {
                    this.applicationProvider = applicationProvider2;
                    if ($assertionsDisabled || reactNativeModulesProvider2 != null) {
                        this.reactNativeModulesProvider = reactNativeModulesProvider2;
                        if ($assertionsDisabled || coordinatorProvider2 != null) {
                            this.coordinatorProvider = coordinatorProvider2;
                            if ($assertionsDisabled || loggerProvider2 != null) {
                                this.loggerProvider = loggerProvider2;
                                if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                                    this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                                    if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                                        this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
                                        return;
                                    }
                                    throw new AssertionError();
                                }
                                throw new AssertionError();
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public AirReactInstanceManager get() {
        return (AirReactInstanceManager) Preconditions.checkNotNull(this.module.provideReactInstanceManager((Context) this.contextProvider.get(), (Application) this.applicationProvider.get(), (ReactNativeModulesProvider) this.reactNativeModulesProvider.get(), (ReactNavigationCoordinator) this.coordinatorProvider.get(), (PerformanceLogger) this.loggerProvider.get(), (LoggingContextFactory) this.loggingContextFactoryProvider.get(), (SharedPrefsHelper) this.sharedPrefsHelperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AirReactInstanceManager> create(ReactModule module2, Provider<Context> contextProvider2, Provider<Application> applicationProvider2, Provider<ReactNativeModulesProvider> reactNativeModulesProvider2, Provider<ReactNavigationCoordinator> coordinatorProvider2, Provider<PerformanceLogger> loggerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        return new ReactModule_ProvideReactInstanceManagerFactory(module2, contextProvider2, applicationProvider2, reactNativeModulesProvider2, coordinatorProvider2, loggerProvider2, loggingContextFactoryProvider2, sharedPrefsHelperProvider2);
    }
}
