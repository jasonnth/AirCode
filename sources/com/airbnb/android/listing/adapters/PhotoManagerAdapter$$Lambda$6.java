package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.Photo;
import com.google.common.base.Function;

final /* synthetic */ class PhotoManagerAdapter$$Lambda$6 implements Function {
    private static final PhotoManagerAdapter$$Lambda$6 instance = new PhotoManagerAdapter$$Lambda$6();

    private PhotoManagerAdapter$$Lambda$6() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return PhotoManagerAdapter.createPhotoModel((Photo) obj);
    }
}
