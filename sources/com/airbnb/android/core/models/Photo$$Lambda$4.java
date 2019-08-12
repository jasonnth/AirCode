package com.airbnb.android.core.models;

import com.airbnb.p027n2.primitives.imaging.ImageSize;
import com.google.common.base.Predicate;

final /* synthetic */ class Photo$$Lambda$4 implements Predicate {
    private static final Photo$$Lambda$4 instance = new Photo$$Lambda$4();

    private Photo$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return Photo.lambda$getBackupUrlForSize$4((ImageSize) obj);
    }
}
