package com.airbnb.android.core.requests;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class GetActiveAccountRequest_MembersInjector implements MembersInjector<GetActiveAccountRequest> {
    static final /* synthetic */ boolean $assertionsDisabled = (!GetActiveAccountRequest_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<CurrencyFormatter> currencyFormatterProvider;

    public GetActiveAccountRequest_MembersInjector(Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || currencyFormatterProvider2 != null) {
            this.currencyFormatterProvider = currencyFormatterProvider2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<GetActiveAccountRequest> create(Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new GetActiveAccountRequest_MembersInjector(currencyFormatterProvider2, accountManagerProvider2);
    }

    public void injectMembers(GetActiveAccountRequest instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.currencyFormatter = (CurrencyFormatter) this.currencyFormatterProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
    }

    public static void injectCurrencyFormatter(GetActiveAccountRequest instance, Provider<CurrencyFormatter> currencyFormatterProvider2) {
        instance.currencyFormatter = (CurrencyFormatter) currencyFormatterProvider2.get();
    }

    public static void injectAccountManager(GetActiveAccountRequest instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }
}
