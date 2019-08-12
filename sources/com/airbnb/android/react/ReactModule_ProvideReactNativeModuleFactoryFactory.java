package com.airbnb.android.react;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.net.NetworkMonitor;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListManager;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public final class ReactModule_ProvideReactNativeModuleFactoryFactory implements Factory<ReactNativeModulesProvider> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ReactModule_ProvideReactNativeModuleFactoryFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> airbnbAccountManagerProvider;
    private final Provider<AirlockErrorHandler> airlockErrorHandlerProvider;
    private final Provider<Bus> busProvider;
    private final Provider<ReactNavigationCoordinator> coordinatorProvider;
    private final Provider<CurrencyFormatter> currencyFormatterProvider;
    private final Provider<ExperimentsProvider> experimentsProvider;
    private final Provider<ItineraryManager> itineraryManagerProvider;
    private final ReactModule module;
    private final Provider<NetworkMonitor> networkMonitorProvider;
    private final Provider<OkHttpClient> okHttpClientProvider;
    private final Provider<Retrofit> retrofitProvider;
    private final Provider<SuperHeroManager> superHeroManagerProvider;
    private final Provider<WishListLogger> wishListLoggerProvider;
    private final Provider<WishListManager> wishListManagerProvider;

    public ReactModule_ProvideReactNativeModuleFactoryFactory(ReactModule module2, Provider<AirbnbAccountManager> airbnbAccountManagerProvider2, Provider<ExperimentsProvider> experimentsProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<Bus> busProvider2, Provider<OkHttpClient> okHttpClientProvider2, Provider<ReactNavigationCoordinator> coordinatorProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<SuperHeroManager> superHeroManagerProvider2, Provider<Retrofit> retrofitProvider2, Provider<WishListLogger> wishListLoggerProvider2, Provider<NetworkMonitor> networkMonitorProvider2, Provider<ItineraryManager> itineraryManagerProvider2, Provider<AirlockErrorHandler> airlockErrorHandlerProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || airbnbAccountManagerProvider2 != null) {
                this.airbnbAccountManagerProvider = airbnbAccountManagerProvider2;
                if ($assertionsDisabled || experimentsProvider2 != null) {
                    this.experimentsProvider = experimentsProvider2;
                    if ($assertionsDisabled || currencyFormatterProvider2 != null) {
                        this.currencyFormatterProvider = currencyFormatterProvider2;
                        if ($assertionsDisabled || busProvider2 != null) {
                            this.busProvider = busProvider2;
                            if ($assertionsDisabled || okHttpClientProvider2 != null) {
                                this.okHttpClientProvider = okHttpClientProvider2;
                                if ($assertionsDisabled || coordinatorProvider2 != null) {
                                    this.coordinatorProvider = coordinatorProvider2;
                                    if ($assertionsDisabled || wishListManagerProvider2 != null) {
                                        this.wishListManagerProvider = wishListManagerProvider2;
                                        if ($assertionsDisabled || superHeroManagerProvider2 != null) {
                                            this.superHeroManagerProvider = superHeroManagerProvider2;
                                            if ($assertionsDisabled || retrofitProvider2 != null) {
                                                this.retrofitProvider = retrofitProvider2;
                                                if ($assertionsDisabled || wishListLoggerProvider2 != null) {
                                                    this.wishListLoggerProvider = wishListLoggerProvider2;
                                                    if ($assertionsDisabled || networkMonitorProvider2 != null) {
                                                        this.networkMonitorProvider = networkMonitorProvider2;
                                                        if ($assertionsDisabled || itineraryManagerProvider2 != null) {
                                                            this.itineraryManagerProvider = itineraryManagerProvider2;
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

    public ReactNativeModulesProvider get() {
        return (ReactNativeModulesProvider) Preconditions.checkNotNull(this.module.provideReactNativeModuleFactory((AirbnbAccountManager) this.airbnbAccountManagerProvider.get(), (ExperimentsProvider) this.experimentsProvider.get(), (CurrencyFormatter) this.currencyFormatterProvider.get(), (Bus) this.busProvider.get(), (OkHttpClient) this.okHttpClientProvider.get(), (ReactNavigationCoordinator) this.coordinatorProvider.get(), (WishListManager) this.wishListManagerProvider.get(), (SuperHeroManager) this.superHeroManagerProvider.get(), (Retrofit) this.retrofitProvider.get(), (WishListLogger) this.wishListLoggerProvider.get(), (NetworkMonitor) this.networkMonitorProvider.get(), (ItineraryManager) this.itineraryManagerProvider.get(), (AirlockErrorHandler) this.airlockErrorHandlerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ReactNativeModulesProvider> create(ReactModule module2, Provider<AirbnbAccountManager> airbnbAccountManagerProvider2, Provider<ExperimentsProvider> experimentsProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<Bus> busProvider2, Provider<OkHttpClient> okHttpClientProvider2, Provider<ReactNavigationCoordinator> coordinatorProvider2, Provider<WishListManager> wishListManagerProvider2, Provider<SuperHeroManager> superHeroManagerProvider2, Provider<Retrofit> retrofitProvider2, Provider<WishListLogger> wishListLoggerProvider2, Provider<NetworkMonitor> networkMonitorProvider2, Provider<ItineraryManager> itineraryManagerProvider2, Provider<AirlockErrorHandler> airlockErrorHandlerProvider2) {
        return new ReactModule_ProvideReactNativeModuleFactoryFactory(module2, airbnbAccountManagerProvider2, experimentsProvider2, currencyFormatterProvider2, busProvider2, okHttpClientProvider2, coordinatorProvider2, wishListManagerProvider2, superHeroManagerProvider2, retrofitProvider2, wishListLoggerProvider2, networkMonitorProvider2, itineraryManagerProvider2, airlockErrorHandlerProvider2);
    }
}
