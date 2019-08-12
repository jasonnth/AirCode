package com.airbnb.android.core.requests;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AuthorizeServiceRequest_MembersInjector implements MembersInjector<AuthorizeServiceRequest> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AuthorizeServiceRequest_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AirbnbApi> airbnbApiProvider;

    public AuthorizeServiceRequest_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            if ($assertionsDisabled || airbnbApiProvider2 != null) {
                this.airbnbApiProvider = airbnbApiProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<AuthorizeServiceRequest> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2) {
        return new AuthorizeServiceRequest_MembersInjector(accountManagerProvider2, airbnbApiProvider2);
    }

    public void injectMembers(AuthorizeServiceRequest instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.airbnbApi = (AirbnbApi) this.airbnbApiProvider.get();
    }

    public static void injectAccountManager(AuthorizeServiceRequest instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectAirbnbApi(AuthorizeServiceRequest instance, Provider<AirbnbApi> airbnbApiProvider2) {
        instance.airbnbApi = (AirbnbApi) airbnbApiProvider2.get();
    }
}