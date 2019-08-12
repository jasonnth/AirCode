package com.airbnb.android.p011p3;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.fragments.AirDialogFragment_MembersInjector;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.wishlists.WishListManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* renamed from: com.airbnb.android.p3.P3Fragment_MembersInjector */
public final class P3Fragment_MembersInjector implements MembersInjector<P3Fragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!P3Fragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;
    private final Provider<WishListManager> wishListManagerProvider;

    public P3Fragment_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<WishListManager> wishListManagerProvider2) {
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
                            if ($assertionsDisabled || businessTravelAccountManagerProvider2 != null) {
                                this.businessTravelAccountManagerProvider = businessTravelAccountManagerProvider2;
                                if ($assertionsDisabled || wishListManagerProvider2 != null) {
                                    this.wishListManagerProvider = wishListManagerProvider2;
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

    public static MembersInjector<P3Fragment> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<WishListManager> wishListManagerProvider2) {
        return new P3Fragment_MembersInjector(mAccountManagerProvider2, debugSettingsProvider2, navigationAnalyticsProvider2, airRequestInitializerProvider2, loggingContextFactoryProvider2, businessTravelAccountManagerProvider2, wishListManagerProvider2);
    }

    public void injectMembers(P3Fragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        AirDialogFragment_MembersInjector.injectMAccountManager(instance, this.mAccountManagerProvider);
        AirDialogFragment_MembersInjector.injectDebugSettings(instance, this.debugSettingsProvider);
        AirDialogFragment_MembersInjector.injectNavigationAnalytics(instance, this.navigationAnalyticsProvider);
        AirDialogFragment_MembersInjector.injectAirRequestInitializer(instance, this.airRequestInitializerProvider);
        AirDialogFragment_MembersInjector.injectLoggingContextFactory(instance, this.loggingContextFactoryProvider);
        instance.businessTravelAccountManager = (BusinessTravelAccountManager) this.businessTravelAccountManagerProvider.get();
        instance.wishListManager = (WishListManager) this.wishListManagerProvider.get();
    }

    public static void injectBusinessTravelAccountManager(P3Fragment instance, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2) {
        instance.businessTravelAccountManager = (BusinessTravelAccountManager) businessTravelAccountManagerProvider2.get();
    }

    public static void injectWishListManager(P3Fragment instance, Provider<WishListManager> wishListManagerProvider2) {
        instance.wishListManager = (WishListManager) wishListManagerProvider2.get();
    }
}
