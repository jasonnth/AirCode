package com.airbnb.android.lib.activities;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;
import com.airbnb.android.lib.payments.factories.PaymentOptionFactory;
import com.airbnb.android.superhero.SuperHeroTableOpenHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DebugMenuActivity_MembersInjector implements MembersInjector<DebugMenuActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DebugMenuActivity_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<ItineraryTableOpenHelper> itineraryTableOpenHelperProvider;
    private final Provider<MessagingRequestFactory> messagingRequestFactoryProvider;
    private final Provider<PaymentOptionFactory> paymentOptionFactoryProvider;
    private final Provider<SharedPrefsHelper> prefsHelperProvider;
    private final Provider<SuperHeroTableOpenHelper> superHeroTableOpenHelperProvider;

    public DebugMenuActivity_MembersInjector(Provider<AirbnbApi> airbnbApiProvider2, Provider<SharedPrefsHelper> prefsHelperProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<MessagingRequestFactory> messagingRequestFactoryProvider2, Provider<ItineraryTableOpenHelper> itineraryTableOpenHelperProvider2, Provider<SuperHeroTableOpenHelper> superHeroTableOpenHelperProvider2, Provider<PaymentOptionFactory> paymentOptionFactoryProvider2) {
        if ($assertionsDisabled || airbnbApiProvider2 != null) {
            this.airbnbApiProvider = airbnbApiProvider2;
            if ($assertionsDisabled || prefsHelperProvider2 != null) {
                this.prefsHelperProvider = prefsHelperProvider2;
                if ($assertionsDisabled || accountManagerProvider2 != null) {
                    this.accountManagerProvider = accountManagerProvider2;
                    if ($assertionsDisabled || debugSettingsProvider2 != null) {
                        this.debugSettingsProvider = debugSettingsProvider2;
                        if ($assertionsDisabled || messagingRequestFactoryProvider2 != null) {
                            this.messagingRequestFactoryProvider = messagingRequestFactoryProvider2;
                            if ($assertionsDisabled || itineraryTableOpenHelperProvider2 != null) {
                                this.itineraryTableOpenHelperProvider = itineraryTableOpenHelperProvider2;
                                if ($assertionsDisabled || superHeroTableOpenHelperProvider2 != null) {
                                    this.superHeroTableOpenHelperProvider = superHeroTableOpenHelperProvider2;
                                    if ($assertionsDisabled || paymentOptionFactoryProvider2 != null) {
                                        this.paymentOptionFactoryProvider = paymentOptionFactoryProvider2;
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

    public static MembersInjector<DebugMenuActivity> create(Provider<AirbnbApi> airbnbApiProvider2, Provider<SharedPrefsHelper> prefsHelperProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<MessagingRequestFactory> messagingRequestFactoryProvider2, Provider<ItineraryTableOpenHelper> itineraryTableOpenHelperProvider2, Provider<SuperHeroTableOpenHelper> superHeroTableOpenHelperProvider2, Provider<PaymentOptionFactory> paymentOptionFactoryProvider2) {
        return new DebugMenuActivity_MembersInjector(airbnbApiProvider2, prefsHelperProvider2, accountManagerProvider2, debugSettingsProvider2, messagingRequestFactoryProvider2, itineraryTableOpenHelperProvider2, superHeroTableOpenHelperProvider2, paymentOptionFactoryProvider2);
    }

    public void injectMembers(DebugMenuActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.airbnbApi = (AirbnbApi) this.airbnbApiProvider.get();
        instance.prefsHelper = (SharedPrefsHelper) this.prefsHelperProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.debugSettings = (DebugSettings) this.debugSettingsProvider.get();
        instance.messagingRequestFactory = (MessagingRequestFactory) this.messagingRequestFactoryProvider.get();
        instance.itineraryTableOpenHelper = (ItineraryTableOpenHelper) this.itineraryTableOpenHelperProvider.get();
        instance.superHeroTableOpenHelper = (SuperHeroTableOpenHelper) this.superHeroTableOpenHelperProvider.get();
        instance.paymentOptionFactory = (PaymentOptionFactory) this.paymentOptionFactoryProvider.get();
    }

    public static void injectAirbnbApi(DebugMenuActivity instance, Provider<AirbnbApi> airbnbApiProvider2) {
        instance.airbnbApi = (AirbnbApi) airbnbApiProvider2.get();
    }

    public static void injectPrefsHelper(DebugMenuActivity instance, Provider<SharedPrefsHelper> prefsHelperProvider2) {
        instance.prefsHelper = (SharedPrefsHelper) prefsHelperProvider2.get();
    }

    public static void injectAccountManager(DebugMenuActivity instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectDebugSettings(DebugMenuActivity instance, Provider<DebugSettings> debugSettingsProvider2) {
        instance.debugSettings = (DebugSettings) debugSettingsProvider2.get();
    }

    public static void injectMessagingRequestFactory(DebugMenuActivity instance, Provider<MessagingRequestFactory> messagingRequestFactoryProvider2) {
        instance.messagingRequestFactory = (MessagingRequestFactory) messagingRequestFactoryProvider2.get();
    }

    public static void injectItineraryTableOpenHelper(DebugMenuActivity instance, Provider<ItineraryTableOpenHelper> itineraryTableOpenHelperProvider2) {
        instance.itineraryTableOpenHelper = (ItineraryTableOpenHelper) itineraryTableOpenHelperProvider2.get();
    }

    public static void injectSuperHeroTableOpenHelper(DebugMenuActivity instance, Provider<SuperHeroTableOpenHelper> superHeroTableOpenHelperProvider2) {
        instance.superHeroTableOpenHelper = (SuperHeroTableOpenHelper) superHeroTableOpenHelperProvider2.get();
    }

    public static void injectPaymentOptionFactory(DebugMenuActivity instance, Provider<PaymentOptionFactory> paymentOptionFactoryProvider2) {
        instance.paymentOptionFactory = (PaymentOptionFactory) paymentOptionFactoryProvider2.get();
    }
}
