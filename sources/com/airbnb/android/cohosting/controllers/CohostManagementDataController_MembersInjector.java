package com.airbnb.android.cohosting.controllers;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CohostManagementDataController_MembersInjector implements MembersInjector<CohostManagementDataController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CohostManagementDataController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;

    public CohostManagementDataController_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<CohostManagementDataController> create(Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new CohostManagementDataController_MembersInjector(accountManagerProvider2);
    }

    public void injectMembers(CohostManagementDataController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
    }

    public static void injectAccountManager(CohostManagementDataController instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }
}
