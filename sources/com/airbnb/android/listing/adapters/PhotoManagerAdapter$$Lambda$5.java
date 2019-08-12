package com.airbnb.android.listing.adapters;

import com.airbnb.android.photouploadmanager.PhotoUploadTransaction;
import com.google.common.base.Function;

final /* synthetic */ class PhotoManagerAdapter$$Lambda$5 implements Function {
    private static final PhotoManagerAdapter$$Lambda$5 instance = new PhotoManagerAdapter$$Lambda$5();

    private PhotoManagerAdapter$$Lambda$5() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return PhotoManagerAdapter.createPhotoModel((PhotoUploadTransaction) obj);
    }
}
