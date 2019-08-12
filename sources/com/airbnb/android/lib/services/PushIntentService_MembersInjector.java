package com.airbnb.android.lib.services;

import com.airbnb.android.core.AppForegroundDetector;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PushIntentService_MembersInjector implements MembersInjector<PushIntentService> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PushIntentService_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AppForegroundDetector> appForegroundDetectorProvider;
    private final Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider;
    private final Provider<Bus> busProvider;
    private final Provider<CalendarStore> calendarStoreProvider;
    private final Provider<MessagingRequestFactory> messagingRequestFactoryProvider;

    public PushIntentService_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<CalendarStore> calendarStoreProvider2, Provider<Bus> busProvider2, Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider2, Provider<MessagingRequestFactory> messagingRequestFactoryProvider2, Provider<AppForegroundDetector> appForegroundDetectorProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            if ($assertionsDisabled || calendarStoreProvider2 != null) {
                this.calendarStoreProvider = calendarStoreProvider2;
                if ($assertionsDisabled || busProvider2 != null) {
                    this.busProvider = busProvider2;
                    if ($assertionsDisabled || appLaunchAnalyticsProvider2 != null) {
                        this.appLaunchAnalyticsProvider = appLaunchAnalyticsProvider2;
                        if ($assertionsDisabled || messagingRequestFactoryProvider2 != null) {
                            this.messagingRequestFactoryProvider = messagingRequestFactoryProvider2;
                            if ($assertionsDisabled || appForegroundDetectorProvider2 != null) {
                                this.appForegroundDetectorProvider = appForegroundDetectorProvider2;
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

    public static MembersInjector<PushIntentService> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<CalendarStore> calendarStoreProvider2, Provider<Bus> busProvider2, Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider2, Provider<MessagingRequestFactory> messagingRequestFactoryProvider2, Provider<AppForegroundDetector> appForegroundDetectorProvider2) {
        return new PushIntentService_MembersInjector(accountManagerProvider2, calendarStoreProvider2, busProvider2, appLaunchAnalyticsProvider2, messagingRequestFactoryProvider2, appForegroundDetectorProvider2);
    }

    public void injectMembers(PushIntentService instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.calendarStore = (CalendarStore) this.calendarStoreProvider.get();
        instance.bus = (Bus) this.busProvider.get();
        instance.appLaunchAnalytics = (AppLaunchAnalytics) this.appLaunchAnalyticsProvider.get();
        instance.messagingRequestFactory = (MessagingRequestFactory) this.messagingRequestFactoryProvider.get();
        instance.appForegroundDetector = (AppForegroundDetector) this.appForegroundDetectorProvider.get();
    }

    public static void injectAccountManager(PushIntentService instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectCalendarStore(PushIntentService instance, Provider<CalendarStore> calendarStoreProvider2) {
        instance.calendarStore = (CalendarStore) calendarStoreProvider2.get();
    }

    public static void injectBus(PushIntentService instance, Provider<Bus> busProvider2) {
        instance.bus = (Bus) busProvider2.get();
    }

    public static void injectAppLaunchAnalytics(PushIntentService instance, Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider2) {
        instance.appLaunchAnalytics = (AppLaunchAnalytics) appLaunchAnalyticsProvider2.get();
    }

    public static void injectMessagingRequestFactory(PushIntentService instance, Provider<MessagingRequestFactory> messagingRequestFactoryProvider2) {
        instance.messagingRequestFactory = (MessagingRequestFactory) messagingRequestFactoryProvider2.get();
    }

    public static void injectAppForegroundDetector(PushIntentService instance, Provider<AppForegroundDetector> appForegroundDetectorProvider2) {
        instance.appForegroundDetector = (AppForegroundDetector) appForegroundDetectorProvider2.get();
    }
}
