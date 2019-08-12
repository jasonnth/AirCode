package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.Listing;
import p032rx.functions.Func1;

final /* synthetic */ class TextSetting$$Lambda$16 implements Func1 {
    private static final TextSetting$$Lambda$16 instance = new TextSetting$$Lambda$16();

    private TextSetting$$Lambda$16() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return TextSetting.lambda$static$0((Listing) obj);
    }
}
