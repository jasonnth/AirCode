package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class HostReservationObjectAdapter_MembersInjector implements MembersInjector<HostReservationObjectAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!HostReservationObjectAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<CalendarStore> calendarStoreProvider;
    private final Provider<CurrencyFormatter> currencyFormatterProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public HostReservationObjectAdapter_MembersInjector(Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<CalendarStore> calendarStoreProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        if ($assertionsDisabled || currencyFormatterProvider2 != null) {
            this.currencyFormatterProvider = currencyFormatterProvider2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                if ($assertionsDisabled || calendarStoreProvider2 != null) {
                    this.calendarStoreProvider = calendarStoreProvider2;
                    if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                        this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
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

    public static MembersInjector<HostReservationObjectAdapter> create(Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<CalendarStore> calendarStoreProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        return new HostReservationObjectAdapter_MembersInjector(currencyFormatterProvider2, accountManagerProvider2, calendarStoreProvider2, sharedPrefsHelperProvider2);
    }

    public void injectMembers(HostReservationObjectAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.currencyFormatter = (CurrencyFormatter) this.currencyFormatterProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.calendarStore = (CalendarStore) this.calendarStoreProvider.get();
        instance.sharedPrefsHelper = (SharedPrefsHelper) this.sharedPrefsHelperProvider.get();
    }

    public static void injectCurrencyFormatter(HostReservationObjectAdapter instance, Provider<CurrencyFormatter> currencyFormatterProvider2) {
        instance.currencyFormatter = (CurrencyFormatter) currencyFormatterProvider2.get();
    }

    public static void injectAccountManager(HostReservationObjectAdapter instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectCalendarStore(HostReservationObjectAdapter instance, Provider<CalendarStore> calendarStoreProvider2) {
        instance.calendarStore = (CalendarStore) calendarStoreProvider2.get();
    }

    public static void injectSharedPrefsHelper(HostReservationObjectAdapter instance, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        instance.sharedPrefsHelper = (SharedPrefsHelper) sharedPrefsHelperProvider2.get();
    }
}
