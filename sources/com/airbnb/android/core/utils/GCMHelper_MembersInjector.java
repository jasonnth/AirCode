package com.airbnb.android.core.utils;

import com.airbnb.android.core.AirbnbApi;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class GCMHelper_MembersInjector implements MembersInjector<GCMHelper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!GCMHelper_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbApi> mAirbnbApiProvider;

    public GCMHelper_MembersInjector(Provider<AirbnbApi> mAirbnbApiProvider2) {
        if ($assertionsDisabled || mAirbnbApiProvider2 != null) {
            this.mAirbnbApiProvider = mAirbnbApiProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<GCMHelper> create(Provider<AirbnbApi> mAirbnbApiProvider2) {
        return new GCMHelper_MembersInjector(mAirbnbApiProvider2);
    }

    public void injectMembers(GCMHelper instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAirbnbApi = (AirbnbApi) this.mAirbnbApiProvider.get();
    }

    public static void injectMAirbnbApi(GCMHelper instance, Provider<AirbnbApi> mAirbnbApiProvider2) {
        instance.mAirbnbApi = (AirbnbApi) mAirbnbApiProvider2.get();
    }
}
