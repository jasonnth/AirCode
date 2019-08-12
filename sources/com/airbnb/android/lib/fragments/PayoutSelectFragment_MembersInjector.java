package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.AirbnbApi;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PayoutSelectFragment_MembersInjector implements MembersInjector<PayoutSelectFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PayoutSelectFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbApi> mAirbnbApiProvider;

    public PayoutSelectFragment_MembersInjector(Provider<AirbnbApi> mAirbnbApiProvider2) {
        if ($assertionsDisabled || mAirbnbApiProvider2 != null) {
            this.mAirbnbApiProvider = mAirbnbApiProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<PayoutSelectFragment> create(Provider<AirbnbApi> mAirbnbApiProvider2) {
        return new PayoutSelectFragment_MembersInjector(mAirbnbApiProvider2);
    }

    public void injectMembers(PayoutSelectFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAirbnbApi = (AirbnbApi) this.mAirbnbApiProvider.get();
    }

    public static void injectMAirbnbApi(PayoutSelectFragment instance, Provider<AirbnbApi> mAirbnbApiProvider2) {
        instance.mAirbnbApi = (AirbnbApi) mAirbnbApiProvider2.get();
    }
}
