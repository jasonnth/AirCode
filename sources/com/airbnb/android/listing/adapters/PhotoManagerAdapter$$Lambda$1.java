package com.airbnb.android.listing.adapters;

import com.airbnb.android.photouploadmanager.PhotoUploadListenerUtil.CatchAllListener;

final /* synthetic */ class PhotoManagerAdapter$$Lambda$1 implements CatchAllListener {
    private final PhotoManagerAdapter arg$1;

    private PhotoManagerAdapter$$Lambda$1(PhotoManagerAdapter photoManagerAdapter) {
        this.arg$1 = photoManagerAdapter;
    }

    public static CatchAllListener lambdaFactory$(PhotoManagerAdapter photoManagerAdapter) {
        return new PhotoManagerAdapter$$Lambda$1(photoManagerAdapter);
    }

    public void uploadEvent() {
        this.arg$1.updatePhotos();
    }
}
