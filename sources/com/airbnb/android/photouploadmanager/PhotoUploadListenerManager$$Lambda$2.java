package com.airbnb.android.photouploadmanager;

import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.airbnb.android.core.responses.PhotoUploadResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PhotoUploadListenerManager$$Lambda$2 implements Action1 {
    private final long arg$1;
    private final PhotoUpload arg$2;
    private final PhotoUploadResponse arg$3;

    private PhotoUploadListenerManager$$Lambda$2(long j, PhotoUpload photoUpload, PhotoUploadResponse photoUploadResponse) {
        this.arg$1 = j;
        this.arg$2 = photoUpload;
        this.arg$3 = photoUploadResponse;
    }

    public static Action1 lambdaFactory$(long j, PhotoUpload photoUpload, PhotoUploadResponse photoUploadResponse) {
        return new PhotoUploadListenerManager$$Lambda$2(j, photoUpload, photoUploadResponse);
    }

    public void call(Object obj) {
        ((PhotoUploadListener) obj).uploadSucceded(this.arg$1, this.arg$2, this.arg$3);
    }
}
