package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ReservationObjectAdapter_MembersInjector implements MembersInjector<ReservationObjectAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ReservationObjectAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<CurrencyFormatter> currencyFormatterProvider;

    public ReservationObjectAdapter_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            if ($assertionsDisabled || currencyFormatterProvider2 != null) {
                this.currencyFormatterProvider = currencyFormatterProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ReservationObjectAdapter> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2) {
        return new ReservationObjectAdapter_MembersInjector(accountManagerProvider2, currencyFormatterProvider2);
    }

    public void injectMembers(ReservationObjectAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.currencyFormatter = (CurrencyFormatter) this.currencyFormatterProvider.get();
    }

    public static void injectAccountManager(ReservationObjectAdapter instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectCurrencyFormatter(ReservationObjectAdapter instance, Provider<CurrencyFormatter> currencyFormatterProvider2) {
        instance.currencyFormatter = (CurrencyFormatter) currencyFormatterProvider2.get();
    }
}
