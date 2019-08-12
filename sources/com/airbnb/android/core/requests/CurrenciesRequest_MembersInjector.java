package com.airbnb.android.core.requests;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.utils.CurrencyFormatter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CurrenciesRequest_MembersInjector implements MembersInjector<CurrenciesRequest> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CurrenciesRequest_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CurrencyFormatter> currencyFormatterProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public CurrenciesRequest_MembersInjector(Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || currencyFormatterProvider2 != null) {
            this.currencyFormatterProvider = currencyFormatterProvider2;
            if ($assertionsDisabled || preferencesProvider2 != null) {
                this.preferencesProvider = preferencesProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<CurrenciesRequest> create(Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<AirbnbPreferences> preferencesProvider2) {
        return new CurrenciesRequest_MembersInjector(currencyFormatterProvider2, preferencesProvider2);
    }

    public void injectMembers(CurrenciesRequest instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.currencyFormatter = (CurrencyFormatter) this.currencyFormatterProvider.get();
        instance.preferences = (AirbnbPreferences) this.preferencesProvider.get();
    }

    public static void injectCurrencyFormatter(CurrenciesRequest instance, Provider<CurrencyFormatter> currencyFormatterProvider2) {
        instance.currencyFormatter = (CurrencyFormatter) currencyFormatterProvider2.get();
    }

    public static void injectPreferences(CurrenciesRequest instance, Provider<AirbnbPreferences> preferencesProvider2) {
        instance.preferences = (AirbnbPreferences) preferencesProvider2.get();
    }
}
