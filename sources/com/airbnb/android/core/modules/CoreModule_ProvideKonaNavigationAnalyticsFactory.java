package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideKonaNavigationAnalyticsFactory implements Factory<NavigationLogging> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideKonaNavigationAnalyticsFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final CoreModule module;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public CoreModule_ProvideKonaNavigationAnalyticsFactory(CoreModule module2, Provider<Context> contextProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || debugSettingsProvider2 != null) {
                    this.debugSettingsProvider = debugSettingsProvider2;
                    if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                        this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
                        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
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

    public NavigationLogging get() {
        return (NavigationLogging) Preconditions.checkNotNull(this.module.provideKonaNavigationAnalytics((Context) this.contextProvider.get(), (DebugSettings) this.debugSettingsProvider.get(), (SharedPrefsHelper) this.sharedPrefsHelperProvider.get(), (LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<NavigationLogging> create(CoreModule module2, Provider<Context> contextProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new CoreModule_ProvideKonaNavigationAnalyticsFactory(module2, contextProvider2, debugSettingsProvider2, sharedPrefsHelperProvider2, loggingContextFactoryProvider2);
    }

    public static NavigationLogging proxyProvideKonaNavigationAnalytics(CoreModule instance, Context context, DebugSettings debugSettings, SharedPrefsHelper sharedPrefsHelper, LoggingContextFactory loggingContextFactory) {
        return instance.provideKonaNavigationAnalytics(context, debugSettings, sharedPrefsHelper, loggingContextFactory);
    }
}
