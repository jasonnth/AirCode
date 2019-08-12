package com.airbnb.android.photouploadmanager;

import com.airbnb.android.core.responses.PhotoUploadResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PhotoUploadService$$Lambda$1 implements Action1 {
    private final PhotoUploadService arg$1;

    private PhotoUploadService$$Lambda$1(PhotoUploadService photoUploadService) {
        this.arg$1 = photoUploadService;
    }

    public static Action1 lambdaFactory$(PhotoUploadService photoUploadService) {
        return new PhotoUploadService$$Lambda$1(photoUploadService);
    }

    public void call(Object obj) {
        this.arg$1.photoUploadManager.setPhotoUploadSuccessful((PhotoUploadResponse) obj);
    }
}
