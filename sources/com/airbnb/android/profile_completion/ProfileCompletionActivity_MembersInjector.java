package com.airbnb.android.profile_completion;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.activities.AirActivity_MembersInjector;
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
import com.airbnb.android.profile_completion.analytics.ProfileCompletionJitneyLogger;
import com.airbnb.erf.Erf;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ProfileCompletionActivity_MembersInjector implements MembersInjector<ProfileCompletionActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ProfileCompletionActivity_MembersInjector.class.desiredAssertionStatus());
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
    private final Provider<ProfileCompletionJitneyLogger> profileCompletionJitneyLoggerProvider;
    private final Provider<ProfileCompletionManager> profileCompletionManagerProvider;
    private final Provider<AirReactInstanceManager> reactInstanceManagerProvider;
    private final Provider<ResourceManager> resourceManagerProvider;
    private final Provider<ShakeFeedbackSensorListener> shakeHelperProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;
    private final Provider<SuperHeroManager> superHeroManagerProvider;
    private final Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider;
    private final Provider<WishListManager> wishListManagerProvider;

    public ProfileCompletionActivity_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<Bus> busProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<Erf> erfProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<ShakeFeedbackSensorListener> shakeHelperProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<SuperHeroManager> superHeroManagerProvider2, Provider<AirReactInstanceManager> reactInstanceManagerProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<AppLaunchAnalytics> launchAnalyticsProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<DLSJitneyLogger> dLSloggerProvider2, Provider<ProfileCompletionManager> profileCompletionManagerProvider2, Provider<ProfileCompletionJitneyLogger> profileCompletionJitneyLoggerProvider2) {
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
                                                                            if ($assertionsDisabled || profileCompletionManagerProvider2 != null) {
                                                                                this.profileCompletionManagerProvider = profileCompletionManagerProvider2;
                                                                                if ($assertionsDisabled || profileCompletionJitneyLoggerProvider2 != null) {
                                                                                    this.profileCompletionJitneyLoggerProvider = profileCompletionJitneyLoggerProvider2;
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

    public static MembersInjector<ProfileCompletionActivity> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<Bus> busProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<Erf> erfProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<ShakeFeedbackSensorListener> shakeHelperProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<SuperHeroManager> superHeroManagerProvider2, Provider<AirReactInstanceManager> reactInstanceManagerProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<AppLaunchAnalytics> launchAnalyticsProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<DLSJitneyLogger> dLSloggerProvider2, Provider<ProfileCompletionManager> profileCompletionManagerProvider2, Provider<ProfileCompletionJitneyLogger> profileCompletionJitneyLoggerProvider2) {
        return new ProfileCompletionActivity_MembersInjector(accountManagerProvider2, sharedPrefsHelperProvider2, busProvider2, currencyFormatterProvider2, erfProvider2, navigationAnalyticsProvider2, preferencesProvider2, shakeHelperProvider2, airRequestInitializerProvider2, superHeroManagerProvider2, reactInstanceManagerProvider2, viewBreadcrumbManagerProvider2, launchAnalyticsProvider2, resourceManagerProvider2, airbnbApiProvider2, wishListManagerProvider2, dLSloggerProvider2, profileCompletionManagerProvider2, profileCompletionJitneyLoggerProvider2);
    }

    public void injectMembers(ProfileCompletionActivity instance) {
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
        instance.profileCompletionManager = (ProfileCompletionManager) this.profileCompletionManagerProvider.get();
        instance.profileCompletionJitneyLogger = (ProfileCompletionJitneyLogger) this.profileCompletionJitneyLoggerProvider.get();
    }

    public static void injectProfileCompletionManager(ProfileCompletionActivity instance, Provider<ProfileCompletionManager> profileCompletionManagerProvider2) {
        instance.profileCompletionManager = (ProfileCompletionManager) profileCompletionManagerProvider2.get();
    }

    public static void injectProfileCompletionJitneyLogger(ProfileCompletionActivity instance, Provider<ProfileCompletionJitneyLogger> profileCompletionJitneyLoggerProvider2) {
        instance.profileCompletionJitneyLogger = (ProfileCompletionJitneyLogger) profileCompletionJitneyLoggerProvider2.get();
    }
}
