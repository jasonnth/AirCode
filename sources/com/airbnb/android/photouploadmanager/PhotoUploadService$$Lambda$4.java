package com.airbnb.android.photouploadmanager;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.requests.photos.PhotoUploadRequest;
import p032rx.functions.Action1;

final /* synthetic */ class PhotoUploadService$$Lambda$4 implements Action1 {
    private final PhotoUploadService arg$1;

    private PhotoUploadService$$Lambda$4(PhotoUploadService photoUploadService) {
        this.arg$1 = photoUploadService;
    }

    public static Action1 lambdaFactory$(PhotoUploadService photoUploadService) {
        return new PhotoUploadService$$Lambda$4(photoUploadService);
    }

    public void call(Object obj) {
        this.arg$1.photoUploadManager.setPhotoUploadFailed((PhotoUploadRequest) ((AirRequestNetworkException) obj).request(), (AirRequestNetworkException) obj);
    }
}
