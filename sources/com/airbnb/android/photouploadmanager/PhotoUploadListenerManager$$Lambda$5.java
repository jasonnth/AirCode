package com.airbnb.android.photouploadmanager;

import p032rx.functions.Action1;

final /* synthetic */ class PhotoUploadListenerManager$$Lambda$5 implements Action1 {
    private final long arg$1;

    private PhotoUploadListenerManager$$Lambda$5(long j) {
        this.arg$1 = j;
    }

    public static Action1 lambdaFactory$(long j) {
        return new PhotoUploadListenerManager$$Lambda$5(j);
    }

    public void call(Object obj) {
        ((PhotoUploadListener) obj).retryAllUploads(this.arg$1);
    }
}
