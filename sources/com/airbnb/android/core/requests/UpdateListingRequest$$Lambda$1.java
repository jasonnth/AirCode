package com.airbnb.android.core.requests;

import com.airbnb.android.core.models.Photo;
import com.google.common.base.Function;

final /* synthetic */ class UpdateListingRequest$$Lambda$1 implements Function {
    private static final UpdateListingRequest$$Lambda$1 instance = new UpdateListingRequest$$Lambda$1();

    private UpdateListingRequest$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((Photo) obj).getId());
    }
}
