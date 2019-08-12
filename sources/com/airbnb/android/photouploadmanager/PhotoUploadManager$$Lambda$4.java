package com.airbnb.android.photouploadmanager;

import com.google.common.base.Predicate;

final /* synthetic */ class PhotoUploadManager$$Lambda$4 implements Predicate {
    private final long arg$1;

    private PhotoUploadManager$$Lambda$4(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new PhotoUploadManager$$Lambda$4(j);
    }

    public boolean apply(Object obj) {
        return PhotoUploadManager.lambda$removeFailedUpload$3(this.arg$1, (PhotoUploadTransaction) obj);
    }
}
