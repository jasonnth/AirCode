package com.airbnb.android.listing.adapters;

import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerItem;
import com.google.common.base.Function;

final /* synthetic */ class PhotoManagerAdapter$$Lambda$4 implements Function {
    private static final PhotoManagerAdapter$$Lambda$4 instance = new PhotoManagerAdapter$$Lambda$4();

    private PhotoManagerAdapter$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((PhotoRearrangerItem) obj).f2705id);
    }
}
