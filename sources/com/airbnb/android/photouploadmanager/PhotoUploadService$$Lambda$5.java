package com.airbnb.android.photouploadmanager;

import p032rx.functions.Action1;

final /* synthetic */ class PhotoUploadService$$Lambda$5 implements Action1 {
    private final PhotoUploadService arg$1;

    private PhotoUploadService$$Lambda$5(PhotoUploadService photoUploadService) {
        this.arg$1 = photoUploadService;
    }

    public static Action1 lambdaFactory$(PhotoUploadService photoUploadService) {
        return new PhotoUploadService$$Lambda$5(photoUploadService);
    }

    public void call(Object obj) {
        this.arg$1.notifyUploadComplete();
    }
}
