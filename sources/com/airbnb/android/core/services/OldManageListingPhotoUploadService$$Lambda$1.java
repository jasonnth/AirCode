package com.airbnb.android.core.services;

import com.airbnb.android.core.services.OldManageListingPhotoUploadService.OldManageListingPhotoUploadStartedEvent;

final /* synthetic */ class OldManageListingPhotoUploadService$$Lambda$1 implements Runnable {
    private final OldManageListingPhotoUploadService arg$1;

    private OldManageListingPhotoUploadService$$Lambda$1(OldManageListingPhotoUploadService oldManageListingPhotoUploadService) {
        this.arg$1 = oldManageListingPhotoUploadService;
    }

    public static Runnable lambdaFactory$(OldManageListingPhotoUploadService oldManageListingPhotoUploadService) {
        return new OldManageListingPhotoUploadService$$Lambda$1(oldManageListingPhotoUploadService);
    }

    public void run() {
        this.arg$1.mBus.post(new OldManageListingPhotoUploadStartedEvent());
    }
}
