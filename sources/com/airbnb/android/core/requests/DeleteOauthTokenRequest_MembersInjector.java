package com.airbnb.android.core.requests;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DeleteOauthTokenRequest_MembersInjector implements MembersInjector<DeleteOauthTokenRequest> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DeleteOauthTokenRequest_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;

    public DeleteOauthTokenRequest_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<DeleteOauthTokenRequest> create(Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new DeleteOauthTokenRequest_MembersInjector(accountManagerProvider2);
    }

    public void injectMembers(DeleteOauthTokenRequest instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
    }

    public static void injectAccountManager(DeleteOauthTokenRequest instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }
}
