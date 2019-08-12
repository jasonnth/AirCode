package com.airbnb.android.core.models;

import com.airbnb.p027n2.primitives.imaging.ImageSize;
import com.google.common.base.Predicate;

final /* synthetic */ class Photo$$Lambda$1 implements Predicate {
    private final Photo arg$1;

    private Photo$$Lambda$1(Photo photo) {
        this.arg$1 = photo;
    }

    public static Predicate lambdaFactory$(Photo photo) {
        return new Photo$$Lambda$1(photo);
    }

    public boolean apply(Object obj) {
        return Photo.lambda$getBackupUrlForSize$1(this.arg$1, (ImageSize) obj);
    }
}
