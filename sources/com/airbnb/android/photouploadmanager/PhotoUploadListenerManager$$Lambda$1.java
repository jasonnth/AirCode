package com.airbnb.android.photouploadmanager;

import com.airbnb.android.core.requests.photos.PhotoUpload;
import p032rx.functions.Action1;

final /* synthetic */ class PhotoUploadListenerManager$$Lambda$1 implements Action1 {
    private final long arg$1;
    private final PhotoUpload arg$2;

    private PhotoUploadListenerManager$$Lambda$1(long j, PhotoUpload photoUpload) {
        this.arg$1 = j;
        this.arg$2 = photoUpload;
    }

    public static Action1 lambdaFactory$(long j, PhotoUpload photoUpload) {
        return new PhotoUploadListenerManager$$Lambda$1(j, photoUpload);
    }

    public void call(Object obj) {
        ((PhotoUploadListener) obj).uploadPending(this.arg$1, this.arg$2);
    }
}
