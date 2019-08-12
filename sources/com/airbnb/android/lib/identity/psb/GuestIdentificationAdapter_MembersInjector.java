package com.airbnb.android.lib.identity.psb;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class GuestIdentificationAdapter_MembersInjector implements MembersInjector<GuestIdentificationAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!GuestIdentificationAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;

    public GuestIdentificationAdapter_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<GuestIdentificationAdapter> create(Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new GuestIdentificationAdapter_MembersInjector(accountManagerProvider2);
    }

    public void injectMembers(GuestIdentificationAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
    }

    public static void injectAccountManager(GuestIdentificationAdapter instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }
}
