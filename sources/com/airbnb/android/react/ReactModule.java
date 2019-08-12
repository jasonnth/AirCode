package com.airbnb.android.react;

import android.app.Application;
import android.content.Context;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.constants.ActivityConstants;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.models.CheckinArguments;
import com.airbnb.android.core.models.MeetupPDPArguments;
import com.airbnb.android.core.models.ReactAuthenticatedWebViewArguments;
import com.airbnb.android.core.net.NetworkMonitor;
import com.airbnb.android.core.paidamenities.enums.PaidAmenityArguments;
import com.airbnb.android.core.payments.models.AddPaymentMethodArguments;
import com.airbnb.android.core.payments.models.QuickPayArguments;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.ColdStartExperimentDeliverer;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.sharing.models.ShareArguments;
import com.facebook.react.modules.network.ReactCookieJarContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.otto.Bus;
import java.util.Arrays;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ReactModule {
    public ReactNativeModulesProvider provideReactNativeModuleFactory(AirbnbAccountManager airbnbAccountManager, ExperimentsProvider experimentsProvider, CurrencyFormatter currencyFormatter, Bus bus, OkHttpClient okHttpClient, ReactNavigationCoordinator coordinator, WishListManager wishListManager, SuperHeroManager superHeroManager, Retrofit retrofit, WishListLogger wishListLogger, NetworkMonitor networkMonitor, ItineraryManager itineraryManager, AirlockErrorHandler airlockErrorHandler) {
        return new ReactNativeModulesProvider(airbnbAccountManager, experimentsProvider, currencyFormatter, bus, okHttpClient, coordinator, wishListManager, superHeroManager, retrofit, wishListLogger, networkMonitor, itineraryManager, airlockErrorHandler);
    }

    public AirReactInstanceManager provideReactInstanceManager(Context context, Application application, ReactNativeModulesProvider reactNativeModulesProvider, ReactNavigationCoordinator coordinator, PerformanceLogger logger, LoggingContextFactory loggingContextFactory, SharedPrefsHelper sharedPrefsHelper) {
        if (ColdStartExperimentDeliverer.isInTreatmentGroup()) {
            return _provideReactInstanceManagerWithInitialization(context, application, reactNativeModulesProvider, coordinator, logger, loggingContextFactory, sharedPrefsHelper);
        }
        return _provideReactInstanceManager(context, reactNativeModulesProvider, coordinator, logger);
    }

    /* access modifiers changed from: protected */
    public AirReactInstanceManager _provideReactInstanceManager(Context context, ReactNativeModulesProvider reactNativeModulesProvider, ReactNavigationCoordinator coordinator, PerformanceLogger logger) {
        return new AirReactInstanceManagerImpl(context, reactNativeModulesProvider, coordinator, logger);
    }

    /* access modifiers changed from: protected */
    public AirReactInstanceManager _provideReactInstanceManagerWithInitialization(Context context, Application application, ReactNativeModulesProvider reactNativeModulesProvider, ReactNavigationCoordinator coordinator, PerformanceLogger logger, LoggingContextFactory loggingContextFactory, SharedPrefsHelper sharedPrefsHelper) {
        AirReactInstanceManager reactInstanceManager = new AirReactInstanceManagerImpl(context, reactNativeModulesProvider, coordinator, logger);
        ReactNativeUtils.safeInitialize(application, reactInstanceManager, loggingContextFactory, sharedPrefsHelper);
        return reactInstanceManager;
    }

    public ReactNavigationCoordinator provideReactNativeCoordinator(ObjectMapper objectMapper) {
        return _provideReactNavigationCoordinator(objectMapper);
    }

    /* access modifiers changed from: protected */
    public ReactNavigationCoordinator _provideReactNavigationCoordinator(ObjectMapper objectMapper) {
        return new ReactNavigationCoordinator(Arrays.asList(new ReactExposedActivityParams[]{new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_HELP_CENTER, Activities.helpCenter()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_VERIFICATIONS, Activities.checkAndLaunchVerification()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_USER_PROFILE, Activities.userProfile()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_MANAGE_GUESTS, Activities.manageGuests()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_EXPERIENCES_QUICK_PAY, Activities.quickPay(), QuickPayArguments.class), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_GIFT_CARDS_QUICK_PAY, Activities.quickPay(), QuickPayArguments.class), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_ADD_PAYMENT_METHODS, Activities.addPaymentMethod(), AddPaymentMethodArguments.class), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_MEETUP_PDP, Activities.placeActivityPDP(), MeetupPDPArguments.class), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_SHARING, Activities.share(), ShareArguments.class), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_SHARE_TRIP_TO_WECHAT, Activities.shareTripToWeChat()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_SEARCH_STORIES_RESULT, Activities.searchStoriesResult()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_AUTHENTICATED_WEB_VIEW, Activities.reactAuthenticatedWebView(), ReactAuthenticatedWebViewArguments.class), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_GUEST_CANCELLATION, Activities.dlsCancelReservation()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_HOST_CANCELLATION, Activities.hostCancelReservation()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_HOST_RESERVATION_OBJECT, Activities.hostReservationObject()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_TRIP_SUPPORT, Activities.helpThread()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_PAID_AMENITY, Activities.paidAmenityRouting(), PaidAmenityArguments.class), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_SELECT_CONTACT, Activities.selectContact()), new ReactExposedActivityParams(objectMapper, ActivityConstants.ACTIVITY_CHECKIN_GUIDE, Activities.viewCheckin(), CheckinArguments.class)}));
    }

    public CookieJar provideReactCookieJarContainer() {
        return new ReactCookieJarContainer();
    }

    public ReactDeepLinkRegistry provideReactDeepLinkRegistry(ReactDeepLinkParser deepLinkParser) {
        return new ReactDeepLinkRegistry(deepLinkParser.parseJSON());
    }

    public ReactDeepLinkParser provideReactDeepLinkParser(Context context, ObjectMapper objectMapper) {
        return new ReactDeepLinkParser(context, objectMapper);
    }
}
