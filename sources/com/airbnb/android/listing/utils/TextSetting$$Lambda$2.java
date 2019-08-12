package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.Listing;
import p032rx.functions.Func1;

final /* synthetic */ class TextSetting$$Lambda$2 implements Func1 {
    private static final TextSetting$$Lambda$2 instance = new TextSetting$$Lambda$2();

    private TextSetting$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((Listing) obj).getSummary();
    }
}
