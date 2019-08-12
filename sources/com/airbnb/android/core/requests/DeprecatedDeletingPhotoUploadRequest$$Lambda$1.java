package com.airbnb.android.core.requests;

import com.google.common.base.Function;
import java.io.File;

final /* synthetic */ class DeprecatedDeletingPhotoUploadRequest$$Lambda$1 implements Function {
    private static final DeprecatedDeletingPhotoUploadRequest$$Lambda$1 instance = new DeprecatedDeletingPhotoUploadRequest$$Lambda$1();

    private DeprecatedDeletingPhotoUploadRequest$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new File((String) obj);
    }
}
