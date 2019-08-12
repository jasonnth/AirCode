package com.airbnb.android.core.utils;

import com.airbnb.android.core.AirbnbApi;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class JPushHelper_MembersInjector implements MembersInjector<JPushHelper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!JPushHelper_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbApi> airbnbApiProvider;

    public JPushHelper_MembersInjector(Provider<AirbnbApi> airbnbApiProvider2) {
        if ($assertionsDisabled || airbnbApiProvider2 != null) {
            this.airbnbApiProvider = airbnbApiProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<JPushHelper> create(Provider<AirbnbApi> airbnbApiProvider2) {
        return new JPushHelper_MembersInjector(airbnbApiProvider2);
    }

    public void injectMembers(JPushHelper instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.airbnbApi = (AirbnbApi) this.airbnbApiProvider.get();
    }

    public static void injectAirbnbApi(JPushHelper instance, Provider<AirbnbApi> airbnbApiProvider2) {
        instance.airbnbApi = (AirbnbApi) airbnbApiProvider2.get();
    }
}
