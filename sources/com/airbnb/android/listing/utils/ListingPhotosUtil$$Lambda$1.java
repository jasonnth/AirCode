package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.Photo;
import com.google.common.base.Predicate;

final /* synthetic */ class ListingPhotosUtil$$Lambda$1 implements Predicate {
    private final Photo arg$1;

    private ListingPhotosUtil$$Lambda$1(Photo photo) {
        this.arg$1 = photo;
    }

    public static Predicate lambdaFactory$(Photo photo) {
        return new ListingPhotosUtil$$Lambda$1(photo);
    }

    public boolean apply(Object obj) {
        return ListingPhotosUtil.lambda$addPhoto$0(this.arg$1, (Photo) obj);
    }
}
