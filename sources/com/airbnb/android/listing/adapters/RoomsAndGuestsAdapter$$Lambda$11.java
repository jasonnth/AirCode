package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.SpaceType;
import p032rx.functions.Func1;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$11 implements Func1 {
    private static final RoomsAndGuestsAdapter$$Lambda$11 instance = new RoomsAndGuestsAdapter$$Lambda$11();

    private RoomsAndGuestsAdapter$$Lambda$11() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return Integer.valueOf(((SpaceType) obj).titleId);
    }
}
