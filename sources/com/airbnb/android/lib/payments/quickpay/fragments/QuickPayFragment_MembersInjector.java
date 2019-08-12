package com.airbnb.android.lib.payments.quickpay.fragments;

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
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.security.ThreatMetrixClient;
import com.airbnb.android.core.utils.ClientSessionValidator;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.lib.payments.braintree.BraintreeFactory;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestFactory;
import com.airbnb.android.lib.payments.factories.PaymentOptionFactory;
import com.airbnb.android.lib.payments.quickpay.adapters.QuickPayAdapterFactory;
import com.airbnb.android.lib.payments.quickpay.paymentredirect.PaymentRedirectCoordinator;
import com.airbnb.erf.Erf;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class QuickPayFragment_MembersInjector implements MembersInjector<QuickPayFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<QuickPayAdapterFactory> adapterFactoryProvider;
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<AirlockErrorHandler> airlockErrorHandlerProvider;
    private final Provider<BillPriceQuoteRequestFactory> billPriceQuoteRequestFactoryProvider;
    private final Provider<BraintreeFactory> braintreeFactoryProvider;
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
    private final Provider<PaymentOptionFactory> paymentOptionFactoryProvider;
    private final Provider<PaymentRedirectCoordinator> paymentRedirectCoordinatorProvider;
    private final Provider<QuickPayJitneyLogger> quickPayJitneyLoggerProvider;
    private final Provider<ResourceManager> resourceManagerProvider;
    private final Provider<ThreatMetrixClient> threatMetrixClientProvider;
    private final Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider;
    private final Provider<WishListManager> wishListManagerProvider;

    public QuickPayFragment_MembersInjector(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbPreferences> mPreferencesProvider2, Provider<Erf> mErfProvider2, Provider<SharedPrefsHelper> mPrefsHelperProvider2, Provider<MemoryUtils> mMemoryUtilsProvider2, Provider<Bus> mBusProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<ClientSessionValidator> clientSessionValidatorProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<QuickPayAdapterFactory> adapterFactoryProvider2, Provider<BillPriceQuoteRequestFactory> billPriceQuoteRequestFactoryProvider2, Provider<QuickPayJitneyLogger> quickPayJitneyLoggerProvider2, Provider<BraintreeFactory> braintreeFactoryProvider2, Provider<PaymentOptionFactory> paymentOptionFactoryProvider2, Provider<PaymentRedirectCoordinator> paymentRedirectCoordinatorProvider2, Provider<ThreatMetrixClient> threatMetrixClientProvider2, Provider<AirlockErrorHandler> airlockErrorHandlerProvider2) {
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
                                                                        if ($assertionsDisabled || adapterFactoryProvider2 != null) {
                                                                            this.adapterFactoryProvider = adapterFactoryProvider2;
                                                                            if ($assertionsDisabled || billPriceQuoteRequestFactoryProvider2 != null) {
                                                                                this.billPriceQuoteRequestFactoryProvider = billPriceQuoteRequestFactoryProvider2;
                                                                                if ($assertionsDisabled || quickPayJitneyLoggerProvider2 != null) {
                                                                                    this.quickPayJitneyLoggerProvider = quickPayJitneyLoggerProvider2;
                                                                                    if ($assertionsDisabled || braintreeFactoryProvider2 != null) {
                                                                                        this.braintreeFactoryProvider = braintreeFactoryProvider2;
                                                                                        if ($assertionsDisabled || paymentOptionFactoryProvider2 != null) {
                                                                                            this.paymentOptionFactoryProvider = paymentOptionFactoryProvider2;
                                                                                            if ($assertionsDisabled || paymentRedirectCoordinatorProvider2 != null) {
                                                                                                this.paymentRedirectCoordinatorProvider = paymentRedirectCoordinatorProvider2;
                                                                                                if ($assertionsDisabled || threatMetrixClientProvider2 != null) {
                                                                                                    this.threatMetrixClientProvider = threatMetrixClientProvider2;
                                                                                                    if ($assertionsDisabled || airlockErrorHandlerProvider2 != null) {
                                                                                                        this.airlockErrorHandlerProvider = airlockErrorHandlerProvider2;
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

    public static MembersInjector<QuickPayFragment> create(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbPreferences> mPreferencesProvider2, Provider<Erf> mErfProvider2, Provider<SharedPrefsHelper> mPrefsHelperProvider2, Provider<MemoryUtils> mMemoryUtilsProvider2, Provider<Bus> mBusProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<ClientSessionValidator> clientSessionValidatorProvider2, Provider<ViewBreadcrumbManager> viewBreadcrumbManagerProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<QuickPayAdapterFactory> adapterFactoryProvider2, Provider<BillPriceQuoteRequestFactory> billPriceQuoteRequestFactoryProvider2, Provider<QuickPayJitneyLogger> quickPayJitneyLoggerProvider2, Provider<BraintreeFactory> braintreeFactoryProvider2, Provider<PaymentOptionFactory> paymentOptionFactoryProvider2, Provider<PaymentRedirectCoordinator> paymentRedirectCoordinatorProvider2, Provider<ThreatMetrixClient> threatMetrixClientProvider2, Provider<AirlockErrorHandler> airlockErrorHandlerProvider2) {
        return new QuickPayFragment_MembersInjector(mAirbnbApiProvider2, wishListManagerProvider2, businessTravelAccountManagerProvider2, mAccountManagerProvider2, mPreferencesProvider2, mErfProvider2, mPrefsHelperProvider2, mMemoryUtilsProvider2, mBusProvider2, mCurrencyHelperProvider2, navigationAnalyticsProvider2, airRequestInitializerProvider2, loggingContextFactoryProvider2, clientSessionValidatorProvider2, viewBreadcrumbManagerProvider2, resourceManagerProvider2, adapterFactoryProvider2, billPriceQuoteRequestFactoryProvider2, quickPayJitneyLoggerProvider2, braintreeFactoryProvider2, paymentOptionFactoryProvider2, paymentRedirectCoordinatorProvider2, threatMetrixClientProvider2, airlockErrorHandlerProvider2);
    }

    public void injectMembers(QuickPayFragment instance) {
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
        instance.adapterFactory = (QuickPayAdapterFactory) this.adapterFactoryProvider.get();
        instance.billPriceQuoteRequestFactory = (BillPriceQuoteRequestFactory) this.billPriceQuoteRequestFactoryProvider.get();
        instance.quickPayJitneyLogger = (QuickPayJitneyLogger) this.quickPayJitneyLoggerProvider.get();
        instance.braintreeFactory = (BraintreeFactory) this.braintreeFactoryProvider.get();
        instance.paymentOptionFactory = (PaymentOptionFactory) this.paymentOptionFactoryProvider.get();
        instance.paymentRedirectCoordinator = (PaymentRedirectCoordinator) this.paymentRedirectCoordinatorProvider.get();
        instance.threatMetrixClient = (ThreatMetrixClient) this.threatMetrixClientProvider.get();
        instance.airlockErrorHandler = (AirlockErrorHandler) this.airlockErrorHandlerProvider.get();
    }

    public static void injectAdapterFactory(QuickPayFragment instance, Provider<QuickPayAdapterFactory> adapterFactoryProvider2) {
        instance.adapterFactory = (QuickPayAdapterFactory) adapterFactoryProvider2.get();
    }

    public static void injectBillPriceQuoteRequestFactory(QuickPayFragment instance, Provider<BillPriceQuoteRequestFactory> billPriceQuoteRequestFactoryProvider2) {
        instance.billPriceQuoteRequestFactory = (BillPriceQuoteRequestFactory) billPriceQuoteRequestFactoryProvider2.get();
    }

    public static void injectQuickPayJitneyLogger(QuickPayFragment instance, Provider<QuickPayJitneyLogger> quickPayJitneyLoggerProvider2) {
        instance.quickPayJitneyLogger = (QuickPayJitneyLogger) quickPayJitneyLoggerProvider2.get();
    }

    public static void injectBraintreeFactory(QuickPayFragment instance, Provider<BraintreeFactory> braintreeFactoryProvider2) {
        instance.braintreeFactory = (BraintreeFactory) braintreeFactoryProvider2.get();
    }

    public static void injectPaymentOptionFactory(QuickPayFragment instance, Provider<PaymentOptionFactory> paymentOptionFactoryProvider2) {
        instance.paymentOptionFactory = (PaymentOptionFactory) paymentOptionFactoryProvider2.get();
    }

    public static void injectPaymentRedirectCoordinator(QuickPayFragment instance, Provider<PaymentRedirectCoordinator> paymentRedirectCoordinatorProvider2) {
        instance.paymentRedirectCoordinator = (PaymentRedirectCoordinator) paymentRedirectCoordinatorProvider2.get();
    }

    public static void injectThreatMetrixClient(QuickPayFragment instance, Provider<ThreatMetrixClient> threatMetrixClientProvider2) {
        instance.threatMetrixClient = (ThreatMetrixClient) threatMetrixClientProvider2.get();
    }

    public static void injectAirlockErrorHandler(QuickPayFragment instance, Provider<AirlockErrorHandler> airlockErrorHandlerProvider2) {
        instance.airlockErrorHandler = (AirlockErrorHandler) airlockErrorHandlerProvider2.get();
    }
}
