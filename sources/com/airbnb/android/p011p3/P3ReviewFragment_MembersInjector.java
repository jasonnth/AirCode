package com.airbnb.android.p011p3;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.analytics.ReviewSearchJitneyLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.fragments.AirDialogFragment_MembersInjector;
import com.airbnb.android.core.utils.DebugSettings;
import dagger.MembersInjector;
import javax.inject.Provider;

/* renamed from: com.airbnb.android.p3.P3ReviewFragment_MembersInjector */
public final class P3ReviewFragment_MembersInjector implements MembersInjector<P3ReviewFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!P3ReviewFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<ReviewSearchJitneyLogger> jitneyLoggerProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;

    public P3ReviewFragment_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<ReviewSearchJitneyLogger> jitneyLoggerProvider2) {
        if ($assertionsDisabled || mAccountManagerProvider2 != null) {
            this.mAccountManagerProvider = mAccountManagerProvider2;
            if ($assertionsDisabled || debugSettingsProvider2 != null) {
                this.debugSettingsProvider = debugSettingsProvider2;
                if ($assertionsDisabled || navigationAnalyticsProvider2 != null) {
                    this.navigationAnalyticsProvider = navigationAnalyticsProvider2;
                    if ($assertionsDisabled || airRequestInitializerProvider2 != null) {
                        this.airRequestInitializerProvider = airRequestInitializerProvider2;
                        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                            if ($assertionsDisabled || jitneyLoggerProvider2 != null) {
                                this.jitneyLoggerProvider = jitneyLoggerProvider2;
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

    public static MembersInjector<P3ReviewFragment> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<ReviewSearchJitneyLogger> jitneyLoggerProvider2) {
        return new P3ReviewFragment_MembersInjector(mAccountManagerProvider2, debugSettingsProvider2, navigationAnalyticsProvider2, airRequestInitializerProvider2, loggingContextFactoryProvider2, jitneyLoggerProvider2);
    }

    public void injectMembers(P3ReviewFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        AirDialogFragment_MembersInjector.injectMAccountManager(instance, this.mAccountManagerProvider);
        AirDialogFragment_MembersInjector.injectDebugSettings(instance, this.debugSettingsProvider);
        AirDialogFragment_MembersInjector.injectNavigationAnalytics(instance, this.navigationAnalyticsProvider);
        AirDialogFragment_MembersInjector.injectAirRequestInitializer(instance, this.airRequestInitializerProvider);
        AirDialogFragment_MembersInjector.injectLoggingContextFactory(instance, this.loggingContextFactoryProvider);
        instance.jitneyLogger = (ReviewSearchJitneyLogger) this.jitneyLoggerProvider.get();
    }

    public static void injectJitneyLogger(P3ReviewFragment instance, Provider<ReviewSearchJitneyLogger> jitneyLoggerProvider2) {
        instance.jitneyLogger = (ReviewSearchJitneyLogger) jitneyLoggerProvider2.get();
    }
}
