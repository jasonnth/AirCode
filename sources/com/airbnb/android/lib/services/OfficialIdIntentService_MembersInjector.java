package com.airbnb.android.lib.services;

import com.airbnb.android.core.AirbnbApi;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class OfficialIdIntentService_MembersInjector implements MembersInjector<OfficialIdIntentService> {
    static final /* synthetic */ boolean $assertionsDisabled = (!OfficialIdIntentService_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbApi> mAirbnbApiProvider;
    private final Provider<Bus> mBusProvider;

    public OfficialIdIntentService_MembersInjector(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<Bus> mBusProvider2) {
        if ($assertionsDisabled || mAirbnbApiProvider2 != null) {
            this.mAirbnbApiProvider = mAirbnbApiProvider2;
            if ($assertionsDisabled || mBusProvider2 != null) {
                this.mBusProvider = mBusProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<OfficialIdIntentService> create(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<Bus> mBusProvider2) {
        return new OfficialIdIntentService_MembersInjector(mAirbnbApiProvider2, mBusProvider2);
    }

    public void injectMembers(OfficialIdIntentService instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAirbnbApi = (AirbnbApi) this.mAirbnbApiProvider.get();
        instance.mBus = (Bus) this.mBusProvider.get();
    }

    public static void injectMAirbnbApi(OfficialIdIntentService instance, Provider<AirbnbApi> mAirbnbApiProvider2) {
        instance.mAirbnbApi = (AirbnbApi) mAirbnbApiProvider2.get();
    }

    public static void injectMBus(OfficialIdIntentService instance, Provider<Bus> mBusProvider2) {
        instance.mBus = (Bus) mBusProvider2.get();
    }
}
