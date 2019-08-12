package com.airbnb.android.core.models;

import com.airbnb.p027n2.primitives.imaging.ImageSize;
import com.google.common.base.Predicate;

final /* synthetic */ class Photo$$Lambda$3 implements Predicate {
    private static final Photo$$Lambda$3 instance = new Photo$$Lambda$3();

    private Photo$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((ImageSize) obj).portrait;
    }
}
