package com.airbnb.android.p011p3;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.activities.AirActivity_MembersInjector;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.controllers.GoogleAppIndexingController;
import com.airbnb.android.core.dls.DLSJitneyLogger;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListener;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.erf.Erf;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* renamed from: com.airbnb.android.p3.P3Activity_MembersInjector */
public final class P3Activity_MembersInjector implements MembersInjector<P3Activity> {
    static final /* synthetic */ boolean $assertionsDisabled = (!P3Activity_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<AppRaterController> appRaterControllerProvider;
    private final Provider<Bus> busProvider;
    private final Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider;
    private final Provider<CurrencyFormatter> currencyFormatterProvider;
    private final Provider<DLSJitneyLogger> dLSloggerProvider;
    private final Provider<Erf> erfProvider;
    private final Provider<GoogleAppIndexingController> googleAppIndexingControllerProvider;
    private final Provider<IdentityJitneyLogger> identityJitneyLoggerProvider;
    private final Provider<AppLaunchAnalytics> launchAnalyticsProvider;
    private final Provider<LocalPushNotificationManager> localPushNotificationManagerProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;
    private final Provider<PerformanceLogger> performanceLoggerProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;
    private final Provider<AirReactInstanceManager> reactInstanceManagerProvider;
    private final Provider<ResourceManager> resourceManagerProvider;
    private final Provider<ShakeFeedbackSensorListener> shakeHelperProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;
    private final Provider<SuperHeroManager> superHeroManagerProvider;
    private final Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider;
    private final Provider<WishListManager> wishListManagerProvider;

    public P3Activity_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<Bus> busProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<Erf> erfProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<ShakeFeedbackSensorListener> shakeHelperProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<SuperHeroManager> superHeroManagerProvider2, Provider<AirReactInstanceManager> reactInstanceManagerProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<AppLaunchAnalytics> launchAnalyticsProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<DLSJitneyLogger> dLSloggerProvider2, Provider<GoogleAppIndexingController> googleAppIndexingControllerProvider2, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<LocalPushNotificationManager> localPushNotificationManagerProvider2, Provider<PerformanceLogger> performanceLoggerProvider2, Provider<AppRaterController> appRaterControllerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<IdentityJitneyLogger> identityJitneyLoggerProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
                if ($assertionsDisabled || busProvider2 != null) {
                    this.busProvider = busProvider2;
                    if ($assertionsDisabled || currencyFormatterProvider2 != null) {
                        this.currencyFormatterProvider = currencyFormatterProvider2;
                        if ($assertionsDisabled || erfProvider2 != null) {
                            this.erfProvider = erfProvider2;
                            if ($assertionsDisabled || navigationAnalyticsProvider2 != null) {
                                this.navigationAnalyticsProvider = navigationAnalyticsProvider2;
                                if ($assertionsDisabled || preferencesProvider2 != null) {
                                    this.preferencesProvider = preferencesProvider2;
                                    if ($assertionsDisabled || shakeHelperProvider2 != null) {
                                        this.shakeHelperProvider = shakeHelperProvider2;
                                        if ($assertionsDisabled || airRequestInitializerProvider2 != null) {
                                            this.airRequestInitializerProvider = airRequestInitializerProvider2;
                                            if ($assertionsDisabled || superHeroManagerProvider2 != null) {
                                                this.superHeroManagerProvider = superHeroManagerProvider2;
                                                if ($assertionsDisabled || reactInstanceManagerProvider2 != null) {
                                                    this.reactInstanceManagerProvider = reactInstanceManagerProvider2;
                                                    if ($assertionsDisabled || viewBreadcrumbManagerProvider2 != null) {
                                                        this.viewBreadcrumbManagerProvider = viewBreadcrumbManagerProvider2;
                                                        if ($assertionsDisabled || launchAnalyticsProvider2 != null) {
                                                            this.launchAnalyticsProvider = launchAnalyticsProvider2;
                                                            if ($assertionsDisabled || resourceManagerProvider2 != null) {
                                                                this.resourceManagerProvider = resourceManagerProvider2;
                                                                if ($assertionsDisabled || airbnbApiProvider2 != null) {
                                                                    this.airbnbApiProvider = airbnbApiProvider2;
                                                                    if ($assertionsDisabled || wishListManagerProvider2 != null) {
                                                                        this.wishListManagerProvider = wishListManagerProvider2;
                                                                        if ($assertionsDisabled || dLSloggerProvider2 != null) {
                                                                            this.dLSloggerProvider = dLSloggerProvider2;
                                                                            if ($assertionsDisabled || googleAppIndexingControllerProvider2 != null) {
                                                                                this.googleAppIndexingControllerProvider = googleAppIndexingControllerProvider2;
                                                                                if ($assertionsDisabled || businessTravelAccountManagerProvider2 != null) {
                                                                                    this.businessTravelAccountManagerProvider = businessTravelAccountManagerProvider2;
                                                                                    if ($assertionsDisabled || localPushNotificationManagerProvider2 != null) {
                                                                                        this.localPushNotificationManagerProvider = localPushNotificationManagerProvider2;
                                                                                        if ($assertionsDisabled || performanceLoggerProvider2 != null) {
                                                                                            this.performanceLoggerProvider = performanceLoggerProvider2;
                                                                                            if ($assertionsDisabled || appRaterControllerProvider2 != null) {
                                                                                                this.appRaterControllerProvider = appRaterControllerProvider2;
                                                                                                if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                                                                                                    this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                                                                                                    if ($assertionsDisabled || identityJitneyLoggerProvider2 != null) {
                                                                                                        this.identityJitneyLoggerProvider = identityJitneyLoggerProvider2;
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

    public static MembersInjector<P3Activity> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<Bus> busProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<Erf> erfProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<ShakeFeedbackSensorListener> shakeHelperProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<SuperHeroManager> superHeroManagerProvider2, Provider<AirReactInstanceManager> reactInstanceManagerProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<AppLaunchAnalytics> launchAnalyticsProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<DLSJitneyLogger> dLSloggerProvider2, Provider<GoogleAppIndexingController> googleAppIndexingControllerProvider2, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<LocalPushNotificationManager> localPushNotificationManagerProvider2, Provider<PerformanceLogger> performanceLoggerProvider2, Provider<AppRaterController> appRaterControllerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<IdentityJitneyLogger> identityJitneyLoggerProvider2) {
        return new P3Activity_MembersInjector(accountManagerProvider2, sharedPrefsHelperProvider2, busProvider2, currencyFormatterProvider2, erfProvider2, navigationAnalyticsProvider2, preferencesProvider2, shakeHelperProvider2, airRequestInitializerProvider2, superHeroManagerProvider2, reactInstanceManagerProvider2, viewBreadcrumbManagerProvider2, launchAnalyticsProvider2, resourceManagerProvider2, airbnbApiProvider2, wishListManagerProvider2, dLSloggerProvider2, googleAppIndexingControllerProvider2, businessTravelAccountManagerProvider2, localPushNotificationManagerProvider2, performanceLoggerProvider2, appRaterControllerProvider2, loggingContextFactoryProvider2, identityJitneyLoggerProvider2);
    }

    public void injectMembers(P3Activity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        AirActivity_MembersInjector.injectAccountManager(instance, this.accountManagerProvider);
        AirActivity_MembersInjector.injectSharedPrefsHelper(instance, this.sharedPrefsHelperProvider);
        AirActivity_MembersInjector.injectBus(instance, this.busProvider);
        AirActivity_MembersInjector.injectCurrencyFormatter(instance, this.currencyFormatterProvider);
        AirActivity_MembersInjector.injectErf(instance, this.erfProvider);
        AirActivity_MembersInjector.injectNavigationAnalytics(instance, this.navigationAnalyticsProvider);
        AirActivity_MembersInjector.injectPreferences(instance, this.preferencesProvider);
        AirActivity_MembersInjector.injectShakeHelper(instance, this.shakeHelperProvider);
        AirActivity_MembersInjector.injectAirRequestInitializer(instance, this.airRequestInitializerProvider);
        AirActivity_MembersInjector.injectSuperHeroManager(instance, this.superHeroManagerProvider);
        AirActivity_MembersInjector.injectReactInstanceManager(instance, this.reactInstanceManagerProvider);
        AirActivity_MembersInjector.injectViewBreadcrumbManager(instance, this.viewBreadcrumbManagerProvider);
        AirActivity_MembersInjector.injectLaunchAnalytics(instance, this.launchAnalyticsProvider);
        AirActivity_MembersInjector.injectResourceManager(instance, this.resourceManagerProvider);
        AirActivity_MembersInjector.injectAirbnbApi(instance, this.airbnbApiProvider);
        AirActivity_MembersInjector.injectWishListManager(instance, this.wishListManagerProvider);
        AirActivity_MembersInjector.injectDLSlogger(instance, this.dLSloggerProvider);
        instance.googleAppIndexingController = (GoogleAppIndexingController) this.googleAppIndexingControllerProvider.get();
        instance.businessTravelAccountManager = (BusinessTravelAccountManager) this.businessTravelAccountManagerProvider.get();
        instance.localPushNotificationManager = (LocalPushNotificationManager) this.localPushNotificationManagerProvider.get();
        instance.performanceLogger = (PerformanceLogger) this.performanceLoggerProvider.get();
        instance.appRaterController = (AppRaterController) this.appRaterControllerProvider.get();
        instance.loggingContextFactory = (LoggingContextFactory) this.loggingContextFactoryProvider.get();
        instance.identityJitneyLogger = (IdentityJitneyLogger) this.identityJitneyLoggerProvider.get();
    }

    public static void injectGoogleAppIndexingController(P3Activity instance, Provider<GoogleAppIndexingController> googleAppIndexingControllerProvider2) {
        instance.googleAppIndexingController = (GoogleAppIndexingController) googleAppIndexingControllerProvider2.get();
    }

    public static void injectBusinessTravelAccountManager(P3Activity instance, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2) {
        instance.businessTravelAccountManager = (BusinessTravelAccountManager) businessTravelAccountManagerProvider2.get();
    }

    public static void injectLocalPushNotificationManager(P3Activity instance, Provider<LocalPushNotificationManager> localPushNotificationManagerProvider2) {
        instance.localPushNotificationManager = (LocalPushNotificationManager) localPushNotificationManagerProvider2.get();
    }

    public static void injectPerformanceLogger(P3Activity instance, Provider<PerformanceLogger> performanceLoggerProvider2) {
        instance.performanceLogger = (PerformanceLogger) performanceLoggerProvider2.get();
    }

    public static void injectAppRaterController(P3Activity instance, Provider<AppRaterController> appRaterControllerProvider2) {
        instance.appRaterController = (AppRaterController) appRaterControllerProvider2.get();
    }

    public static void injectLoggingContextFactory(P3Activity instance, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        instance.loggingContextFactory = (LoggingContextFactory) loggingContextFactoryProvider2.get();
    }

    public static void injectIdentityJitneyLogger(P3Activity instance, Provider<IdentityJitneyLogger> identityJitneyLoggerProvider2) {
        instance.identityJitneyLogger = (IdentityJitneyLogger) identityJitneyLoggerProvider2.get();
    }
}
