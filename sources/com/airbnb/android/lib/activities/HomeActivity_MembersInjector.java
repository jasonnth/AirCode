package com.airbnb.android.lib.activities;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.activities.AirActivity_MembersInjector;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.controllers.ExperimentConfigController;
import com.airbnb.android.core.dls.DLSJitneyLogger;
import com.airbnb.android.core.host.ListingPromoController;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.location.LocationClientFacade;
import com.airbnb.android.core.net.LowBandwidthManager;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListener;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.profile_completion.ProfileCompletionManager;
import com.airbnb.erf.Erf;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class HomeActivity_MembersInjector implements MembersInjector<HomeActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = (!HomeActivity_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<AppRaterController> appRaterControllerProvider;
    private final Provider<BottomBarController> bottomBarControllerProvider;
    private final Provider<Bus> busProvider;
    private final Provider<CurrencyFormatter> currencyFormatterAndCurrencyHelperProvider;
    private final Provider<DLSJitneyLogger> dLSloggerProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<Erf> erfProvider;
    private final Provider<ExperimentConfigController> experimentConfigControllerProvider;
    private final Provider<IdentityJitneyLogger> identityJitneyLoggerProvider;
    private final Provider<ItineraryManager> itineraryManagerProvider;
    private final Provider<AppLaunchAnalytics> launchAnalyticsProvider;
    private final Provider<AppLaunchUtils> launchUtilsProvider;
    private final Provider<ListingPromoController> listingPromoControllerProvider;
    private final Provider<LocalPushNotificationManager> localPushNotificationManagerProvider;
    private final Provider<LocationClientFacade> locationHelperProvider;
    private final Provider<LowBandwidthManager> lowBandwidthUtilsProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;
    private final Provider<ProfileCompletionManager> profileCompletionManagerProvider;
    private final Provider<AirReactInstanceManager> reactInstanceManagerProvider;
    private final Provider<ResourceManager> resourceManagerProvider;
    private final Provider<ShakeFeedbackSensorListener> shakeHelperProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;
    private final Provider<SuperHeroManager> superHeroManagerProvider;
    private final Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider;
    private final Provider<WishListManager> wishListManagerProvider;

    public HomeActivity_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<Bus> busProvider2, Provider<CurrencyFormatter> currencyFormatterAndCurrencyHelperProvider2, Provider<Erf> erfProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<ShakeFeedbackSensorListener> shakeHelperProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<SuperHeroManager> superHeroManagerProvider2, Provider<AirReactInstanceManager> reactInstanceManagerProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<AppLaunchAnalytics> launchAnalyticsProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<DLSJitneyLogger> dLSloggerProvider2, Provider<ExperimentConfigController> experimentConfigControllerProvider2, Provider<AppLaunchUtils> launchUtilsProvider2, Provider<LocationClientFacade> locationHelperProvider2, Provider<LocalPushNotificationManager> localPushNotificationManagerProvider2, Provider<BottomBarController> bottomBarControllerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<AppRaterController> appRaterControllerProvider2, Provider<ProfileCompletionManager> profileCompletionManagerProvider2, Provider<ItineraryManager> itineraryManagerProvider2, Provider<IdentityJitneyLogger> identityJitneyLoggerProvider2, Provider<ListingPromoController> listingPromoControllerProvider2, Provider<LowBandwidthManager> lowBandwidthUtilsProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
                if ($assertionsDisabled || busProvider2 != null) {
                    this.busProvider = busProvider2;
                    if ($assertionsDisabled || currencyFormatterAndCurrencyHelperProvider2 != null) {
                        this.currencyFormatterAndCurrencyHelperProvider = currencyFormatterAndCurrencyHelperProvider2;
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
                                                                            if ($assertionsDisabled || experimentConfigControllerProvider2 != null) {
                                                                                this.experimentConfigControllerProvider = experimentConfigControllerProvider2;
                                                                                if ($assertionsDisabled || launchUtilsProvider2 != null) {
                                                                                    this.launchUtilsProvider = launchUtilsProvider2;
                                                                                    if ($assertionsDisabled || locationHelperProvider2 != null) {
                                                                                        this.locationHelperProvider = locationHelperProvider2;
                                                                                        if ($assertionsDisabled || localPushNotificationManagerProvider2 != null) {
                                                                                            this.localPushNotificationManagerProvider = localPushNotificationManagerProvider2;
                                                                                            if ($assertionsDisabled || bottomBarControllerProvider2 != null) {
                                                                                                this.bottomBarControllerProvider = bottomBarControllerProvider2;
                                                                                                if ($assertionsDisabled || debugSettingsProvider2 != null) {
                                                                                                    this.debugSettingsProvider = debugSettingsProvider2;
                                                                                                    if ($assertionsDisabled || appRaterControllerProvider2 != null) {
                                                                                                        this.appRaterControllerProvider = appRaterControllerProvider2;
                                                                                                        if ($assertionsDisabled || profileCompletionManagerProvider2 != null) {
                                                                                                            this.profileCompletionManagerProvider = profileCompletionManagerProvider2;
                                                                                                            if ($assertionsDisabled || itineraryManagerProvider2 != null) {
                                                                                                                this.itineraryManagerProvider = itineraryManagerProvider2;
                                                                                                                if ($assertionsDisabled || identityJitneyLoggerProvider2 != null) {
                                                                                                                    this.identityJitneyLoggerProvider = identityJitneyLoggerProvider2;
                                                                                                                    if ($assertionsDisabled || listingPromoControllerProvider2 != null) {
                                                                                                                        this.listingPromoControllerProvider = listingPromoControllerProvider2;
                                                                                                                        if ($assertionsDisabled || lowBandwidthUtilsProvider2 != null) {
                                                                                                                            this.lowBandwidthUtilsProvider = lowBandwidthUtilsProvider2;
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

    public static MembersInjector<HomeActivity> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<Bus> busProvider2, Provider<CurrencyFormatter> currencyFormatterAndCurrencyHelperProvider2, Provider<Erf> erfProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<ShakeFeedbackSensorListener> shakeHelperProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<SuperHeroManager> superHeroManagerProvider2, Provider<AirReactInstanceManager> reactInstanceManagerProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<AppLaunchAnalytics> launchAnalyticsProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<DLSJitneyLogger> dLSloggerProvider2, Provider<ExperimentConfigController> experimentConfigControllerProvider2, Provider<AppLaunchUtils> launchUtilsProvider2, Provider<LocationClientFacade> locationHelperProvider2, Provider<LocalPushNotificationManager> localPushNotificationManagerProvider2, Provider<BottomBarController> bottomBarControllerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<AppRaterController> appRaterControllerProvider2, Provider<ProfileCompletionManager> profileCompletionManagerProvider2, Provider<ItineraryManager> itineraryManagerProvider2, Provider<IdentityJitneyLogger> identityJitneyLoggerProvider2, Provider<ListingPromoController> listingPromoControllerProvider2, Provider<LowBandwidthManager> lowBandwidthUtilsProvider2) {
        return new HomeActivity_MembersInjector(accountManagerProvider2, sharedPrefsHelperProvider2, busProvider2, currencyFormatterAndCurrencyHelperProvider2, erfProvider2, navigationAnalyticsProvider2, preferencesProvider2, shakeHelperProvider2, airRequestInitializerProvider2, superHeroManagerProvider2, reactInstanceManagerProvider2, viewBreadcrumbManagerProvider2, launchAnalyticsProvider2, resourceManagerProvider2, airbnbApiProvider2, wishListManagerProvider2, dLSloggerProvider2, experimentConfigControllerProvider2, launchUtilsProvider2, locationHelperProvider2, localPushNotificationManagerProvider2, bottomBarControllerProvider2, debugSettingsProvider2, appRaterControllerProvider2, profileCompletionManagerProvider2, itineraryManagerProvider2, identityJitneyLoggerProvider2, listingPromoControllerProvider2, lowBandwidthUtilsProvider2);
    }

    public void injectMembers(HomeActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        AirActivity_MembersInjector.injectAccountManager(instance, this.accountManagerProvider);
        AirActivity_MembersInjector.injectSharedPrefsHelper(instance, this.sharedPrefsHelperProvider);
        AirActivity_MembersInjector.injectBus(instance, this.busProvider);
        AirActivity_MembersInjector.injectCurrencyFormatter(instance, this.currencyFormatterAndCurrencyHelperProvider);
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
        instance.experimentConfigController = (ExperimentConfigController) this.experimentConfigControllerProvider.get();
        instance.launchUtils = (AppLaunchUtils) this.launchUtilsProvider.get();
        instance.locationHelper = (LocationClientFacade) this.locationHelperProvider.get();
        instance.localPushNotificationManager = (LocalPushNotificationManager) this.localPushNotificationManagerProvider.get();
        instance.bottomBarController = (BottomBarController) this.bottomBarControllerProvider.get();
        instance.debugSettings = (DebugSettings) this.debugSettingsProvider.get();
        instance.appRaterController = (AppRaterController) this.appRaterControllerProvider.get();
        instance.profileCompletionManager = (ProfileCompletionManager) this.profileCompletionManagerProvider.get();
        instance.itineraryManager = (ItineraryManager) this.itineraryManagerProvider.get();
        instance.identityJitneyLogger = (IdentityJitneyLogger) this.identityJitneyLoggerProvider.get();
        instance.listingPromoController = (ListingPromoController) this.listingPromoControllerProvider.get();
        instance.currencyHelper = DoubleCheck.lazy(this.currencyFormatterAndCurrencyHelperProvider);
        instance.lowBandwidthUtils = DoubleCheck.lazy(this.lowBandwidthUtilsProvider);
    }

    public static void injectExperimentConfigController(HomeActivity instance, Provider<ExperimentConfigController> experimentConfigControllerProvider2) {
        instance.experimentConfigController = (ExperimentConfigController) experimentConfigControllerProvider2.get();
    }

    public static void injectLaunchUtils(HomeActivity instance, Provider<AppLaunchUtils> launchUtilsProvider2) {
        instance.launchUtils = (AppLaunchUtils) launchUtilsProvider2.get();
    }

    public static void injectLocationHelper(HomeActivity instance, Provider<LocationClientFacade> locationHelperProvider2) {
        instance.locationHelper = (LocationClientFacade) locationHelperProvider2.get();
    }

    public static void injectLocalPushNotificationManager(HomeActivity instance, Provider<LocalPushNotificationManager> localPushNotificationManagerProvider2) {
        instance.localPushNotificationManager = (LocalPushNotificationManager) localPushNotificationManagerProvider2.get();
    }

    public static void injectBottomBarController(HomeActivity instance, Provider<BottomBarController> bottomBarControllerProvider2) {
        instance.bottomBarController = (BottomBarController) bottomBarControllerProvider2.get();
    }

    public static void injectDebugSettings(HomeActivity instance, Provider<DebugSettings> debugSettingsProvider2) {
        instance.debugSettings = (DebugSettings) debugSettingsProvider2.get();
    }

    public static void injectAppRaterController(HomeActivity instance, Provider<AppRaterController> appRaterControllerProvider2) {
        instance.appRaterController = (AppRaterController) appRaterControllerProvider2.get();
    }

    public static void injectProfileCompletionManager(HomeActivity instance, Provider<ProfileCompletionManager> profileCompletionManagerProvider2) {
        instance.profileCompletionManager = (ProfileCompletionManager) profileCompletionManagerProvider2.get();
    }

    public static void injectItineraryManager(HomeActivity instance, Provider<ItineraryManager> itineraryManagerProvider2) {
        instance.itineraryManager = (ItineraryManager) itineraryManagerProvider2.get();
    }

    public static void injectIdentityJitneyLogger(HomeActivity instance, Provider<IdentityJitneyLogger> identityJitneyLoggerProvider2) {
        instance.identityJitneyLogger = (IdentityJitneyLogger) identityJitneyLoggerProvider2.get();
    }

    public static void injectListingPromoController(HomeActivity instance, Provider<ListingPromoController> listingPromoControllerProvider2) {
        instance.listingPromoController = (ListingPromoController) listingPromoControllerProvider2.get();
    }

    public static void injectCurrencyHelper(HomeActivity instance, Provider<CurrencyFormatter> currencyHelperProvider) {
        instance.currencyHelper = DoubleCheck.lazy(currencyHelperProvider);
    }

    public static void injectLowBandwidthUtils(HomeActivity instance, Provider<LowBandwidthManager> lowBandwidthUtilsProvider2) {
        instance.lowBandwidthUtils = DoubleCheck.lazy(lowBandwidthUtilsProvider2);
    }
}
