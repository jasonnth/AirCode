package com.airbnb.android.core.fragments;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.utils.ClientSessionValidator;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.erf.Erf;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AirFragment_MembersInjector implements MembersInjector<AirFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AirFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider;
    private final Provider<ClientSessionValidator> clientSessionValidatorProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<AirbnbApi> mAirbnbApiProvider;
    private final Provider<Bus> mBusProvider;
    private final Provider<CurrencyFormatter> mCurrencyHelperProvider;
    private final Provider<Erf> mErfProvider;
    private final Provider<MemoryUtils> mMemoryUtilsProvider;
    private final Provider<AirbnbPreferences> mPreferencesProvider;
    private final Provider<SharedPrefsHelper> mPrefsHelperProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;
    private final Provider<ResourceManager> resourceManagerProvider;
    private final Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider;
    private final Provider<WishListManager> wishListManagerProvider;

    public AirFragment_MembersInjector(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbPreferences> mPreferencesProvider2, Provider<Erf> mErfProvider2, Provider<SharedPrefsHelper> mPrefsHelperProvider2, Provider<MemoryUtils> mMemoryUtilsProvider2, Provider<Bus> mBusProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<ClientSessionValidator> clientSessionValidatorProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<ResourceManager> resourceManagerProvider2) {
        if ($assertionsDisabled || mAirbnbApiProvider2 != null) {
            this.mAirbnbApiProvider = mAirbnbApiProvider2;
            if ($assertionsDisabled || wishListManagerProvider2 != null) {
                this.wishListManagerProvider = wishListManagerProvider2;
                if ($assertionsDisabled || businessTravelAccountManagerProvider2 != null) {
                    this.businessTravelAccountManagerProvider = businessTravelAccountManagerProvider2;
                    if ($assertionsDisabled || mAccountManagerProvider2 != null) {
                        this.mAccountManagerProvider = mAccountManagerProvider2;
                        if ($assertionsDisabled || mPreferencesProvider2 != null) {
                            this.mPreferencesProvider = mPreferencesProvider2;
                            if ($assertionsDisabled || mErfProvider2 != null) {
                                this.mErfProvider = mErfProvider2;
                                if ($assertionsDisabled || mPrefsHelperProvider2 != null) {
                                    this.mPrefsHelperProvider = mPrefsHelperProvider2;
                                    if ($assertionsDisabled || mMemoryUtilsProvider2 != null) {
                                        this.mMemoryUtilsProvider = mMemoryUtilsProvider2;
                                        if ($assertionsDisabled || mBusProvider2 != null) {
                                            this.mBusProvider = mBusProvider2;
                                            if ($assertionsDisabled || mCurrencyHelperProvider2 != null) {
                                                this.mCurrencyHelperProvider = mCurrencyHelperProvider2;
                                                if ($assertionsDisabled || navigationAnalyticsProvider2 != null) {
                                                    this.navigationAnalyticsProvider = navigationAnalyticsProvider2;
                                                    if ($assertionsDisabled || airRequestInitializerProvider2 != null) {
                                                        this.airRequestInitializerProvider = airRequestInitializerProvider2;
                                                        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                                                            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                                                            if ($assertionsDisabled || clientSessionValidatorProvider2 != null) {
                                                                this.clientSessionValidatorProvider = clientSessionValidatorProvider2;
                                                                if ($assertionsDisabled || viewBreadcrumbManagerProvider2 != null) {
                                                                    this.viewBreadcrumbManagerProvider = viewBreadcrumbManagerProvider2;
                                                                    if ($assertionsDisabled || resourceManagerProvider2 != null) {
                                                                        this.resourceManagerProvider = resourceManagerProvider2;
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

    public static MembersInjector<AirFragment> create(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbPreferences> mPreferencesProvider2, Provider<Erf> mErfProvider2, Provider<SharedPrefsHelper> mPrefsHelperProvider2, Provider<MemoryUtils> mMemoryUtilsProvider2, Provider<Bus> mBusProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<ClientSessionValidator> clientSessionValidatorProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<ResourceManager> resourceManagerProvider2) {
        return new AirFragment_MembersInjector(mAirbnbApiProvider2, wishListManagerProvider2, businessTravelAccountManagerProvider2, mAccountManagerProvider2, mPreferencesProvider2, mErfProvider2, mPrefsHelperProvider2, mMemoryUtilsProvider2, mBusProvider2, mCurrencyHelperProvider2, navigationAnalyticsProvider2, airRequestInitializerProvider2, loggingContextFactoryProvider2, clientSessionValidatorProvider2, viewBreadcrumbManagerProvider2, resourceManagerProvider2);
    }

    public void injectMembers(AirFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAirbnbApi = (AirbnbApi) this.mAirbnbApiProvider.get();
        instance.wishListManager = (WishListManager) this.wishListManagerProvider.get();
        instance.businessTravelAccountManager = (BusinessTravelAccountManager) this.businessTravelAccountManagerProvider.get();
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.mPreferences = (AirbnbPreferences) this.mPreferencesProvider.get();
        instance.mErf = (Erf) this.mErfProvider.get();
        instance.mPrefsHelper = (SharedPrefsHelper) this.mPrefsHelperProvider.get();
        instance.mMemoryUtils = (MemoryUtils) this.mMemoryUtilsProvider.get();
        instance.mBus = (Bus) this.mBusProvider.get();
        instance.mCurrencyHelper = (CurrencyFormatter) this.mCurrencyHelperProvider.get();
        instance.navigationAnalytics = (NavigationLogging) this.navigationAnalyticsProvider.get();
        instance.airRequestInitializer = (AirRequestInitializer) this.airRequestInitializerProvider.get();
        instance.loggingContextFactory = (LoggingContextFactory) this.loggingContextFactoryProvider.get();
        instance.clientSessionValidator = (ClientSessionValidator) this.clientSessionValidatorProvider.get();
        instance.viewBreadcrumbManager = (ViewBreadcrumbManager) this.viewBreadcrumbManagerProvider.get();
        instance.resourceManager = (ResourceManager) this.resourceManagerProvider.get();
    }

    public static void injectMAirbnbApi(AirFragment instance, Provider<AirbnbApi> mAirbnbApiProvider2) {
        instance.mAirbnbApi = (AirbnbApi) mAirbnbApiProvider2.get();
    }

    public static void injectWishListManager(AirFragment instance, Provider<WishListManager> wishListManagerProvider2) {
        instance.wishListManager = (WishListManager) wishListManagerProvider2.get();
    }

    public static void injectBusinessTravelAccountManager(AirFragment instance, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2) {
        instance.businessTravelAccountManager = (BusinessTravelAccountManager) businessTravelAccountManagerProvider2.get();
    }

    public static void injectMAccountManager(AirFragment instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }

    public static void injectMPreferences(AirFragment instance, Provider<AirbnbPreferences> mPreferencesProvider2) {
        instance.mPreferences = (AirbnbPreferences) mPreferencesProvider2.get();
    }

    public static void injectMErf(AirFragment instance, Provider<Erf> mErfProvider2) {
        instance.mErf = (Erf) mErfProvider2.get();
    }

    public static void injectMPrefsHelper(AirFragment instance, Provider<SharedPrefsHelper> mPrefsHelperProvider2) {
        instance.mPrefsHelper = (SharedPrefsHelper) mPrefsHelperProvider2.get();
    }

    public static void injectMMemoryUtils(AirFragment instance, Provider<MemoryUtils> mMemoryUtilsProvider2) {
        instance.mMemoryUtils = (MemoryUtils) mMemoryUtilsProvider2.get();
    }

    public static void injectMBus(AirFragment instance, Provider<Bus> mBusProvider2) {
        instance.mBus = (Bus) mBusProvider2.get();
    }

    public static void injectMCurrencyHelper(AirFragment instance, Provider<CurrencyFormatter> mCurrencyHelperProvider2) {
        instance.mCurrencyHelper = (CurrencyFormatter) mCurrencyHelperProvider2.get();
    }

    public static void injectNavigationAnalytics(AirFragment instance, Provider<NavigationLogging> navigationAnalyticsProvider2) {
        instance.navigationAnalytics = (NavigationLogging) navigationAnalyticsProvider2.get();
    }

    public static void injectAirRequestInitializer(AirFragment instance, Provider<AirRequestInitializer> airRequestInitializerProvider2) {
        instance.airRequestInitializer = (AirRequestInitializer) airRequestInitializerProvider2.get();
    }

    public static void injectLoggingContextFactory(AirFragment instance, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        instance.loggingContextFactory = (LoggingContextFactory) loggingContextFactoryProvider2.get();
    }

    public static void injectClientSessionValidator(AirFragment instance, Provider<ClientSessionValidator> clientSessionValidatorProvider2) {
        instance.clientSessionValidator = (ClientSessionValidator) clientSessionValidatorProvider2.get();
    }

    public static void injectViewBreadcrumbManager(AirFragment instance, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2) {
        instance.viewBreadcrumbManager = (ViewBreadcrumbManager) viewBreadcrumbManagerProvider2.get();
    }

    public static void injectResourceManager(AirFragment instance, Provider<ResourceManager> resourceManagerProvider2) {
        instance.resourceManager = (ResourceManager) resourceManagerProvider2.get();
    }
}
