package com.airbnb.android.core.fragments;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.graphql.GraphistClient;
import com.airbnb.android.core.utils.DebugSettings;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DLSCancellationPolicyFragment_MembersInjector implements MembersInjector<DLSCancellationPolicyFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DLSCancellationPolicyFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<GraphistClient> clientProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;
    private final Provider<PerformanceLogger> performanceLoggerProvider;

    public DLSCancellationPolicyFragment_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<GraphistClient> clientProvider2, Provider<PerformanceLogger> performanceLoggerProvider2) {
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
                            if ($assertionsDisabled || clientProvider2 != null) {
                                this.clientProvider = clientProvider2;
                                if ($assertionsDisabled || performanceLoggerProvider2 != null) {
                                    this.performanceLoggerProvider = performanceLoggerProvider2;
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

    public static MembersInjector<DLSCancellationPolicyFragment> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<GraphistClient> clientProvider2, Provider<PerformanceLogger> performanceLoggerProvider2) {
        return new DLSCancellationPolicyFragment_MembersInjector(mAccountManagerProvider2, debugSettingsProvider2, navigationAnalyticsProvider2, airRequestInitializerProvider2, loggingContextFactoryProvider2, clientProvider2, performanceLoggerProvider2);
    }

    public void injectMembers(DLSCancellationPolicyFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.debugSettings = (DebugSettings) this.debugSettingsProvider.get();
        instance.navigationAnalytics = (NavigationLogging) this.navigationAnalyticsProvider.get();
        instance.airRequestInitializer = (AirRequestInitializer) this.airRequestInitializerProvider.get();
        instance.loggingContextFactory = (LoggingContextFactory) this.loggingContextFactoryProvider.get();
        instance.client = (GraphistClient) this.clientProvider.get();
        instance.performanceLogger = (PerformanceLogger) this.performanceLoggerProvider.get();
    }

    public static void injectClient(DLSCancellationPolicyFragment instance, Provider<GraphistClient> clientProvider2) {
        instance.client = (GraphistClient) clientProvider2.get();
    }

    public static void injectPerformanceLogger(DLSCancellationPolicyFragment instance, Provider<PerformanceLogger> performanceLoggerProvider2) {
        instance.performanceLogger = (PerformanceLogger) performanceLoggerProvider2.get();
    }
}
