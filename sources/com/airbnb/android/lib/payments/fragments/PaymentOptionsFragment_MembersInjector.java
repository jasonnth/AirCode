package com.airbnb.android.lib.payments.fragments;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.fragments.AirFragment_MembersInjector;
import com.airbnb.android.core.utils.ClientSessionValidator;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.lib.payments.braintree.BraintreeFactory;
import com.airbnb.android.lib.payments.factories.PaymentOptionFactory;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapterFactory;
import com.airbnb.erf.Erf;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PaymentOptionsFragment_MembersInjector implements MembersInjector<PaymentOptionsFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PaymentOptionsFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<BraintreeFactory> braintreeFactoryProvider;
    private final Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider;
    private final Provider<ClientSessionValidator> clientSessionValidatorProvider;
    private final Provider<QuickPayJitneyLogger> jitneyLoggerProvider;
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
    private final Provider<PaymentOptionFactory> paymentOptionFactoryProvider;
    private final Provider<PaymentOptionsAdapterFactory> paymentOptionsAdapterFactoryProvider;
    private final Provider<ResourceManager> resourceManagerProvider;
    private final Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider;
    private final Provider<WishListManager> wishListManagerProvider;

    public PaymentOptionsFragment_MembersInjector(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbPreferences> mPreferencesProvider2, Provider<Erf> mErfProvider2, Provider<SharedPrefsHelper> mPrefsHelperProvider2, Provider<MemoryUtils> mMemoryUtilsProvider2, Provider<Bus> mBusProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<ClientSessionValidator> clientSessionValidatorProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<QuickPayJitneyLogger> jitneyLoggerProvider2, Provider<PaymentOptionFactory> paymentOptionFactoryProvider2, Provider<PaymentOptionsAdapterFactory> paymentOptionsAdapterFactoryProvider2, Provider<BraintreeFactory> braintreeFactoryProvider2) {
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
                                                                        if ($assertionsDisabled || jitneyLoggerProvider2 != null) {
                                                                            this.jitneyLoggerProvider = jitneyLoggerProvider2;
                                                                            if ($assertionsDisabled || paymentOptionFactoryProvider2 != null) {
                                                                                this.paymentOptionFactoryProvider = paymentOptionFactoryProvider2;
                                                                                if ($assertionsDisabled || paymentOptionsAdapterFactoryProvider2 != null) {
                                                                                    this.paymentOptionsAdapterFactoryProvider = paymentOptionsAdapterFactoryProvider2;
                                                                                    if ($assertionsDisabled || braintreeFactoryProvider2 != null) {
                                                                                        this.braintreeFactoryProvider = braintreeFactoryProvider2;
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

    public static MembersInjector<PaymentOptionsFragment> create(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbPreferences> mPreferencesProvider2, Provider<Erf> mErfProvider2, Provider<SharedPrefsHelper> mPrefsHelperProvider2, Provider<MemoryUtils> mMemoryUtilsProvider2, Provider<Bus> mBusProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<ClientSessionValidator> clientSessionValidatorProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<QuickPayJitneyLogger> jitneyLoggerProvider2, Provider<PaymentOptionFactory> paymentOptionFactoryProvider2, Provider<PaymentOptionsAdapterFactory> paymentOptionsAdapterFactoryProvider2, Provider<BraintreeFactory> braintreeFactoryProvider2) {
        return new PaymentOptionsFragment_MembersInjector(mAirbnbApiProvider2, wishListManagerProvider2, businessTravelAccountManagerProvider2, mAccountManagerProvider2, mPreferencesProvider2, mErfProvider2, mPrefsHelperProvider2, mMemoryUtilsProvider2, mBusProvider2, mCurrencyHelperProvider2, navigationAnalyticsProvider2, airRequestInitializerProvider2, loggingContextFactoryProvider2, clientSessionValidatorProvider2, viewBreadcrumbManagerProvider2, resourceManagerProvider2, jitneyLoggerProvider2, paymentOptionFactoryProvider2, paymentOptionsAdapterFactoryProvider2, braintreeFactoryProvider2);
    }

    public void injectMembers(PaymentOptionsFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        AirFragment_MembersInjector.injectMAirbnbApi(instance, this.mAirbnbApiProvider);
        AirFragment_MembersInjector.injectWishListManager(instance, this.wishListManagerProvider);
        AirFragment_MembersInjector.injectBusinessTravelAccountManager(instance, this.businessTravelAccountManagerProvider);
        AirFragment_MembersInjector.injectMAccountManager(instance, this.mAccountManagerProvider);
        AirFragment_MembersInjector.injectMPreferences(instance, this.mPreferencesProvider);
        AirFragment_MembersInjector.injectMErf(instance, this.mErfProvider);
        AirFragment_MembersInjector.injectMPrefsHelper(instance, this.mPrefsHelperProvider);
        AirFragment_MembersInjector.injectMMemoryUtils(instance, this.mMemoryUtilsProvider);
        AirFragment_MembersInjector.injectMBus(instance, this.mBusProvider);
        AirFragment_MembersInjector.injectMCurrencyHelper(instance, this.mCurrencyHelperProvider);
        AirFragment_MembersInjector.injectNavigationAnalytics(instance, this.navigationAnalyticsProvider);
        AirFragment_MembersInjector.injectAirRequestInitializer(instance, this.airRequestInitializerProvider);
        AirFragment_MembersInjector.injectLoggingContextFactory(instance, this.loggingContextFactoryProvider);
        AirFragment_MembersInjector.injectClientSessionValidator(instance, this.clientSessionValidatorProvider);
        AirFragment_MembersInjector.injectViewBreadcrumbManager(instance, this.viewBreadcrumbManagerProvider);
        AirFragment_MembersInjector.injectResourceManager(instance, this.resourceManagerProvider);
        instance.jitneyLogger = (QuickPayJitneyLogger) this.jitneyLoggerProvider.get();
        instance.paymentOptionFactory = (PaymentOptionFactory) this.paymentOptionFactoryProvider.get();
        instance.paymentOptionsAdapterFactory = (PaymentOptionsAdapterFactory) this.paymentOptionsAdapterFactoryProvider.get();
        instance.braintreeFactory = (BraintreeFactory) this.braintreeFactoryProvider.get();
    }

    public static void injectJitneyLogger(PaymentOptionsFragment instance, Provider<QuickPayJitneyLogger> jitneyLoggerProvider2) {
        instance.jitneyLogger = (QuickPayJitneyLogger) jitneyLoggerProvider2.get();
    }

    public static void injectPaymentOptionFactory(PaymentOptionsFragment instance, Provider<PaymentOptionFactory> paymentOptionFactoryProvider2) {
        instance.paymentOptionFactory = (PaymentOptionFactory) paymentOptionFactoryProvider2.get();
    }

    public static void injectPaymentOptionsAdapterFactory(PaymentOptionsFragment instance, Provider<PaymentOptionsAdapterFactory> paymentOptionsAdapterFactoryProvider2) {
        instance.paymentOptionsAdapterFactory = (PaymentOptionsAdapterFactory) paymentOptionsAdapterFactoryProvider2.get();
    }

    public static void injectBraintreeFactory(PaymentOptionsFragment instance, Provider<BraintreeFactory> braintreeFactoryProvider2) {
        instance.braintreeFactory = (BraintreeFactory) braintreeFactoryProvider2.get();
    }
}
