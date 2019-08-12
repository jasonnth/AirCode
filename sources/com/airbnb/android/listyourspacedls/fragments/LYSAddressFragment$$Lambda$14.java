package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.ListingRoomResponse;
import com.google.common.base.Function;

final /* synthetic */ class LYSAddressFragment$$Lambda$14 implements Function {
    private static final LYSAddressFragment$$Lambda$14 instance = new LYSAddressFragment$$Lambda$14();

    private LYSAddressFragment$$Lambda$14() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((ListingRoomResponse) obj).listingRoom;
    }
}
