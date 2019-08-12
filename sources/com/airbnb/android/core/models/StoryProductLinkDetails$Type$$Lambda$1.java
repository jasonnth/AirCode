package com.airbnb.android.core.models;

import com.airbnb.android.core.models.StoryProductLinkDetails.Type;
import com.google.common.base.Predicate;

final /* synthetic */ class StoryProductLinkDetails$Type$$Lambda$1 implements Predicate {
    private final String arg$1;

    private StoryProductLinkDetails$Type$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new StoryProductLinkDetails$Type$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((Type) obj).serverKey.equals(this.arg$1);
    }
}
