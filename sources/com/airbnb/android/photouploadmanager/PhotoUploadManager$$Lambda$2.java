package com.airbnb.android.photouploadmanager;

import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.google.common.base.Predicate;

final /* synthetic */ class PhotoUploadManager$$Lambda$2 implements Predicate {
    private final PhotoUpload arg$1;

    private PhotoUploadManager$$Lambda$2(PhotoUpload photoUpload) {
        this.arg$1 = photoUpload;
    }

    public static Predicate lambdaFactory$(PhotoUpload photoUpload) {
        return new PhotoUploadManager$$Lambda$2(photoUpload);
    }

    public boolean apply(Object obj) {
        return PhotoUploadManager.lambda$retryAllFailedUploadsForPhotoUploadTarget$1(this.arg$1, (PhotoUploadTransaction) obj);
    }
}