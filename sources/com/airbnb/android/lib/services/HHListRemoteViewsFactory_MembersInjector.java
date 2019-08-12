package com.airbnb.android.lib.services;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class HHListRemoteViewsFactory_MembersInjector implements MembersInjector<HHListRemoteViewsFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!HHListRemoteViewsFactory_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;

    public HHListRemoteViewsFactory_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2) {
        if ($assertionsDisabled || mAccountManagerProvider2 != null) {
            this.mAccountManagerProvider = mAccountManagerProvider2;
            if ($assertionsDisabled || airbnbApiProvider2 != null) {
                this.airbnbApiProvider = airbnbApiProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<HHListRemoteViewsFactory> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2) {
        return new HHListRemoteViewsFactory_MembersInjector(mAccountManagerProvider2, airbnbApiProvider2);
    }

    public void injectMembers(HHListRemoteViewsFactory instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.airbnbApi = (AirbnbApi) this.airbnbApiProvider.get();
    }

    public static void injectMAccountManager(HHListRemoteViewsFactory instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }

    public static void injectAirbnbApi(HHListRemoteViewsFactory instance, Provider<AirbnbApi> airbnbApiProvider2) {
        instance.airbnbApi = (AirbnbApi) airbnbApiProvider2.get();
    }
}
