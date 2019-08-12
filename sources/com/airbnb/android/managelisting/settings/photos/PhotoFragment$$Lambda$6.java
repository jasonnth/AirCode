package com.airbnb.android.managelisting.settings.photos;

import com.airbnb.android.core.models.Photo;
import com.google.common.base.Predicate;

final /* synthetic */ class PhotoFragment$$Lambda$6 implements Predicate {
    private final long arg$1;

    private PhotoFragment$$Lambda$6(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new PhotoFragment$$Lambda$6(j);
    }

    public boolean apply(Object obj) {
        return PhotoFragment.lambda$getPhoto$3(this.arg$1, (Photo) obj);
    }
}
