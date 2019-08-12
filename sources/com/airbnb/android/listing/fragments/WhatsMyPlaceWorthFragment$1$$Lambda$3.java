package com.airbnb.android.listing.fragments;

import com.airbnb.android.core.enums.SpaceType;
import p032rx.functions.Func1;

final /* synthetic */ class WhatsMyPlaceWorthFragment$1$$Lambda$3 implements Func1 {
    private static final WhatsMyPlaceWorthFragment$1$$Lambda$3 instance = new WhatsMyPlaceWorthFragment$1$$Lambda$3();

    private WhatsMyPlaceWorthFragment$1$$Lambda$3() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return Integer.valueOf(((SpaceType) obj).titleId);
    }
}
