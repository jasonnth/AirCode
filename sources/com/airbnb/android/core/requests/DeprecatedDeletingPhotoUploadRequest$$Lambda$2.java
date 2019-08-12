package com.airbnb.android.core.requests;

import com.google.common.base.Function;
import java.io.File;

final /* synthetic */ class DeprecatedDeletingPhotoUploadRequest$$Lambda$2 implements Function {
    private final DeprecatedDeletingPhotoUploadRequest arg$1;

    private DeprecatedDeletingPhotoUploadRequest$$Lambda$2(DeprecatedDeletingPhotoUploadRequest deprecatedDeletingPhotoUploadRequest) {
        this.arg$1 = deprecatedDeletingPhotoUploadRequest;
    }

    public static Function lambdaFactory$(DeprecatedDeletingPhotoUploadRequest deprecatedDeletingPhotoUploadRequest) {
        return new DeprecatedDeletingPhotoUploadRequest$$Lambda$2(deprecatedDeletingPhotoUploadRequest);
    }

    public Object apply(Object obj) {
        return this.arg$1.createPhotoPart((File) obj);
    }
}
