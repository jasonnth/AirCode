package com.airbnb.android.core.activities;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.dls.DLSJitneyLogger;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListener;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.erf.Erf;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AirActivity_MembersInjector implements MembersInjector<AirActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AirActivity_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<Bus> busProvider;
    private final Provider<CurrencyFormatter> currencyFormatterProvider;
    private final Provider<DLSJitneyLogger> dLSloggerProvider;
    private final Provider<Erf> erfProvider;
    private final Provider<AppLaunchAnalytics> launchAnalyticsProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;
    private final Provider<AirReactInstanceManager> reactInstanceManagerProvider;
    private final Provider<ResourceManager> resourceManagerProvider;
    private final Provider<ShakeFeedbackSensorListener> shakeHelperProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;
    private final Provider<SuperHeroManager> superHeroManagerProvider;
    private final Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider;
    private final Provider<WishListManager> wishListManagerProvider;

    public AirActivity_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<Bus> busProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<Erf> erfProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<ShakeFeedbackSensorListener> shakeHelperProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<SuperHeroManager> superHeroManagerProvider2, Provider<AirReactInstanceManager> reactInstanceManagerProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<AppLaunchAnalytics> launchAnalyticsProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<DLSJitneyLogger> dLSloggerProvider2) {
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

    public static MembersInjector<AirActivity> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<Bus> busProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<Erf> erfProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<ShakeFeedbackSensorListener> shakeHelperProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<SuperHeroManager> superHeroManagerProvider2, Provider<AirReactInstanceManager> reactInstanceManagerProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<AppLaunchAnalytics> launchAnalyticsProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<DLSJitneyLogger> dLSloggerProvider2) {
        return new AirActivity_MembersInjector(accountManagerProvider2, sharedPrefsHelperProvider2, busProvider2, currencyFormatterProvider2, erfProvider2, navigationAnalyticsProvider2, preferencesProvider2, shakeHelperProvider2, airRequestInitializerProvider2, superHeroManagerProvider2, reactInstanceManagerProvider2, viewBreadcrumbManagerProvider2, launchAnalyticsProvider2, resourceManagerProvider2, airbnbApiProvider2, wishListManagerProvider2, dLSloggerProvider2);
    }

    public void injectMembers(AirActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.sharedPrefsHelper = (SharedPrefsHelper) this.sharedPrefsHelperProvider.get();
        instance.bus = (Bus) this.busProvider.get();
        instance.currencyFormatter = (CurrencyFormatter) this.currencyFormatterProvider.get();
        instance.erf = (Erf) this.erfProvider.get();
        instance.navigationAnalytics = (NavigationLogging) this.navigationAnalyticsProvider.get();
        instance.preferences = (AirbnbPreferences) this.preferencesProvider.get();
        instance.shakeHelper = (ShakeFeedbackSensorListener) this.shakeHelperProvider.get();
        instance.airRequestInitializer = (AirRequestInitializer) this.airRequestInitializerProvider.get();
        instance.superHeroManager = (SuperHeroManager) this.superHeroManagerProvider.get();
        instance.reactInstanceManager = DoubleCheck.lazy(this.reactInstanceManagerProvider);
        instance.viewBreadcrumbManager = (ViewBreadcrumbManager) this.viewBreadcrumbManagerProvider.get();
        instance.launchAnalytics = (AppLaunchAnalytics) this.launchAnalyticsProvider.get();
        instance.resourceManager = (ResourceManager) this.resourceManagerProvider.get();
        instance.airbnbApi = (AirbnbApi) this.airbnbApiProvider.get();
        instance.wishListManager = (WishListManager) this.wishListManagerProvider.get();
        instance.DLSlogger = (DLSJitneyLogger) this.dLSloggerProvider.get();
    }

    public static void injectAccountManager(AirActivity instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectSharedPrefsHelper(AirActivity instance, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        instance.sharedPrefsHelper = (SharedPrefsHelper) sharedPrefsHelperProvider2.get();
    }

    public static void injectBus(AirActivity instance, Provider<Bus> busProvider2) {
        instance.bus = (Bus) busProvider2.get();
    }

    public static void injectCurrencyFormatter(AirActivity instance, Provider<CurrencyFormatter> currencyFormatterProvider2) {
        instance.currencyFormatter = (CurrencyFormatter) currencyFormatterProvider2.get();
    }

    public static void injectErf(AirActivity instance, Provider<Erf> erfProvider2) {
        instance.erf = (Erf) erfProvider2.get();
    }

    public static void injectNavigationAnalytics(AirActivity instance, Provider<NavigationLogging> navigationAnalyticsProvider2) {
        instance.navigationAnalytics = (NavigationLogging) navigationAnalyticsProvider2.get();
    }

    public static void injectPreferences(AirActivity instance, Provider<AirbnbPreferences> preferencesProvider2) {
        instance.preferences = (AirbnbPreferences) preferencesProvider2.get();
    }

    public static void injectShakeHelper(AirActivity instance, Provider<ShakeFeedbackSensorListener> shakeHelperProvider2) {
        instance.shakeHelper = (ShakeFeedbackSensorListener) shakeHelperProvider2.get();
    }

    public static void injectAirRequestInitializer(AirActivity instance, Provider<AirRequestInitializer> airRequestInitializerProvider2) {
        instance.airRequestInitializer = (AirRequestInitializer) airRequestInitializerProvider2.get();
    }

    public static void injectSuperHeroManager(AirActivity instance, Provider<SuperHeroManager> superHeroManagerProvider2) {
        instance.superHeroManager = (SuperHeroManager) superHeroManagerProvider2.get();
    }

    public static void injectReactInstanceManager(AirActivity instance, Provider<AirReactInstanceManager> reactInstanceManagerProvider2) {
        instance.reactInstanceManager = DoubleCheck.lazy(reactInstanceManagerProvider2);
    }

    public static void injectViewBreadcrumbManager(AirActivity instance, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2) {
        instance.viewBreadcrumbManager = (ViewBreadcrumbManager) viewBreadcrumbManagerProvider2.get();
    }

    public static void injectLaunchAnalytics(AirActivity instance, Provider<AppLaunchAnalytics> launchAnalyticsProvider2) {
        instance.launchAnalytics = (AppLaunchAnalytics) launchAnalyticsProvider2.get();
    }

    public static void injectResourceManager(AirActivity instance, Provider<ResourceManager> resourceManagerProvider2) {
        instance.resourceManager = (ResourceManager) resourceManagerProvider2.get();
    }

    public static void injectAirbnbApi(AirActivity instance, Provider<AirbnbApi> airbnbApiProvider2) {
        instance.airbnbApi = (AirbnbApi) airbnbApiProvider2.get();
    }

    public static void injectWishListManager(AirActivity instance, Provider<WishListManager> wishListManagerProvider2) {
        instance.wishListManager = (WishListManager) wishListManagerProvider2.get();
    }

    public static void injectDLSlogger(AirActivity instance, Provider<DLSJitneyLogger> DLSloggerProvider) {
        instance.DLSlogger = (DLSJitneyLogger) DLSloggerProvider.get();
    }
}
