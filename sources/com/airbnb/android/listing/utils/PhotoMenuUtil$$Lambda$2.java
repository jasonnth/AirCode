package com.airbnb.android.listing.utils;

import com.airbnb.android.core.views.OptionsMenuFactory.Listener;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;

final /* synthetic */ class PhotoMenuUtil$$Lambda$2 implements Listener {
    private final PhotoUploadManager arg$1;
    private final long arg$2;

    private PhotoMenuUtil$$Lambda$2(PhotoUploadManager photoUploadManager, long j) {
        this.arg$1 = photoUploadManager;
        this.arg$2 = j;
    }

    public static Listener lambdaFactory$(PhotoUploadManager photoUploadManager, long j) {
        return new PhotoMenuUtil$$Lambda$2(photoUploadManager, j);
    }

    public void itemSelected(Object obj) {
        PhotoMenuUtil.actionSelected(this.arg$1, (Action) obj, this.arg$2);
    }
}
