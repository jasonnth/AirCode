package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.PropertyType;
import p032rx.functions.Func1;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$13 implements Func1 {
    private static final RoomsAndGuestsAdapter$$Lambda$13 instance = new RoomsAndGuestsAdapter$$Lambda$13();

    private RoomsAndGuestsAdapter$$Lambda$13() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return Integer.valueOf(((PropertyType) obj).titleId);
    }
}
