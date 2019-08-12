package com.airbnb.android.login.requests;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UserLoginRequest_MembersInjector implements MembersInjector<UserLoginRequest> {
    static final /* synthetic */ boolean $assertionsDisabled = (!UserLoginRequest_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<Bus> busProvider;
    private final Provider<CurrencyFormatter> currencyFormatterProvider;

    public UserLoginRequest_MembersInjector(Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<Bus> busProvider2) {
        if ($assertionsDisabled || currencyFormatterProvider2 != null) {
            this.currencyFormatterProvider = currencyFormatterProvider2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                if ($assertionsDisabled || airbnbApiProvider2 != null) {
                    this.airbnbApiProvider = airbnbApiProvider2;
                    if ($assertionsDisabled || busProvider2 != null) {
                        this.busProvider = busProvider2;
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

    public static MembersInjector<UserLoginRequest> create(Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<Bus> busProvider2) {
        return new UserLoginRequest_MembersInjector(currencyFormatterProvider2, accountManagerProvider2, airbnbApiProvider2, busProvider2);
    }

    public void injectMembers(UserLoginRequest instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.currencyFormatter = (CurrencyFormatter) this.currencyFormatterProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.airbnbApi = (AirbnbApi) this.airbnbApiProvider.get();
        instance.bus = (Bus) this.busProvider.get();
    }

    public static void injectCurrencyFormatter(UserLoginRequest instance, Provider<CurrencyFormatter> currencyFormatterProvider2) {
        instance.currencyFormatter = (CurrencyFormatter) currencyFormatterProvider2.get();
    }

    public static void injectAccountManager(UserLoginRequest instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectAirbnbApi(UserLoginRequest instance, Provider<AirbnbApi> airbnbApiProvider2) {
        instance.airbnbApi = (AirbnbApi) airbnbApiProvider2.get();
    }

    public static void injectBus(UserLoginRequest instance, Provider<Bus> busProvider2) {
        instance.bus = (Bus) busProvider2.get();
    }
}
