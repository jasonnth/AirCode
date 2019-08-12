package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.SelectListing;
import p032rx.functions.Func1;

final /* synthetic */ class TextSetting$$Lambda$3 implements Func1 {
    private static final TextSetting$$Lambda$3 instance = new TextSetting$$Lambda$3();

    private TextSetting$$Lambda$3() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((SelectListing) obj).getSummary();
    }
}
