package com.airbnb.android.photouploadmanager;

import com.airbnb.android.core.requests.photos.PhotoUploadTarget;
import com.google.common.base.Predicate;

final /* synthetic */ class PhotoUploadManager$$Lambda$1 implements Predicate {
    private final long arg$1;
    private final PhotoUploadTarget arg$2;

    private PhotoUploadManager$$Lambda$1(long j, PhotoUploadTarget photoUploadTarget) {
        this.arg$1 = j;
        this.arg$2 = photoUploadTarget;
    }

    public static Predicate lambdaFactory$(long j, PhotoUploadTarget photoUploadTarget) {
        return new PhotoUploadManager$$Lambda$1(j, photoUploadTarget);
    }

    public boolean apply(Object obj) {
        return PhotoUploadManager.lambda$getOrderedOutgoingItems$0(this.arg$1, this.arg$2, (PhotoUploadTransaction) obj);
    }
}
