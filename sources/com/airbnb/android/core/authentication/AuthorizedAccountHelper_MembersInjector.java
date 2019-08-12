package com.airbnb.android.core.authentication;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AuthorizedAccountHelper_MembersInjector implements MembersInjector<AuthorizedAccountHelper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AuthorizedAccountHelper_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public AuthorizedAccountHelper_MembersInjector(Provider<AirbnbApi> airbnbApiProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || airbnbApiProvider2 != null) {
            this.airbnbApiProvider = airbnbApiProvider2;
            if ($assertionsDisabled || preferencesProvider2 != null) {
                this.preferencesProvider = preferencesProvider2;
                if ($assertionsDisabled || accountManagerProvider2 != null) {
                    this.accountManagerProvider = accountManagerProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<AuthorizedAccountHelper> create(Provider<AirbnbApi> airbnbApiProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new AuthorizedAccountHelper_MembersInjector(airbnbApiProvider2, preferencesProvider2, accountManagerProvider2);
    }

    public void injectMembers(AuthorizedAccountHelper instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.airbnbApi = (AirbnbApi) this.airbnbApiProvider.get();
        instance.preferences = (AirbnbPreferences) this.preferencesProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
    }

    public static void injectAirbnbApi(AuthorizedAccountHelper instance, Provider<AirbnbApi> airbnbApiProvider2) {
        instance.airbnbApi = (AirbnbApi) airbnbApiProvider2.get();
    }

    public static void injectPreferences(AuthorizedAccountHelper instance, Provider<AirbnbPreferences> preferencesProvider2) {
        instance.preferences = (AirbnbPreferences) preferencesProvider2.get();
    }

    public static void injectAccountManager(AuthorizedAccountHelper instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }
}
