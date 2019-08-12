package com.airbnb.android.listing.adapters;

import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerItem;
import com.google.common.base.Predicate;

final /* synthetic */ class PhotoManagerAdapter$$Lambda$3 implements Predicate {
    private static final PhotoManagerAdapter$$Lambda$3 instance = new PhotoManagerAdapter$$Lambda$3();

    private PhotoManagerAdapter$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PhotoManagerAdapter.lambda$getPhotoOrder$0((PhotoRearrangerItem) obj);
    }
}
