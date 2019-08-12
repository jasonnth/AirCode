package com.airbnb.android.core.models;

import com.airbnb.p027n2.primitives.imaging.ImageSize;
import java.util.Comparator;

final /* synthetic */ class Photo$$Lambda$2 implements Comparator {
    private final ImageSize arg$1;

    private Photo$$Lambda$2(ImageSize imageSize) {
        this.arg$1 = imageSize;
    }

    public static Comparator lambdaFactory$(ImageSize imageSize) {
        return new Photo$$Lambda$2(imageSize);
    }

    public int compare(Object obj, Object obj2) {
        return Photo.lambda$getBackupUrlForSize$2(this.arg$1, (ImageSize) obj, (ImageSize) obj2);
    }
}
