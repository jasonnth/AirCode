package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.lib.fragments.EndpointSelectorDialogFragment.EndpointAdapter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class EndpointSelectorDialogFragment_EndpointAdapter_MembersInjector implements MembersInjector<EndpointAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!EndpointSelectorDialogFragment_EndpointAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbApi> mAirbnbApiProvider;

    public EndpointSelectorDialogFragment_EndpointAdapter_MembersInjector(Provider<AirbnbApi> mAirbnbApiProvider2) {
        if ($assertionsDisabled || mAirbnbApiProvider2 != null) {
            this.mAirbnbApiProvider = mAirbnbApiProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<EndpointAdapter> create(Provider<AirbnbApi> mAirbnbApiProvider2) {
        return new EndpointSelectorDialogFragment_EndpointAdapter_MembersInjector(mAirbnbApiProvider2);
    }

    public void injectMembers(EndpointAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAirbnbApi = (AirbnbApi) this.mAirbnbApiProvider.get();
    }

    public static void injectMAirbnbApi(EndpointAdapter instance, Provider<AirbnbApi> mAirbnbApiProvider2) {
        instance.mAirbnbApi = (AirbnbApi) mAirbnbApiProvider2.get();
    }
}
