package com.airbnb.android.core.services;

import java.util.List;

final /* synthetic */ class OldManageListingPhotoUploadService$$Lambda$2 implements Runnable {
    private final OldManageListingPhotoUploadService arg$1;
    private final List arg$2;

    private OldManageListingPhotoUploadService$$Lambda$2(OldManageListingPhotoUploadService oldManageListingPhotoUploadService, List list) {
        this.arg$1 = oldManageListingPhotoUploadService;
        this.arg$2 = list;
    }

    public static Runnable lambdaFactory$(OldManageListingPhotoUploadService oldManageListingPhotoUploadService, List list) {
        return new OldManageListingPhotoUploadService$$Lambda$2(oldManageListingPhotoUploadService, list);
    }

    public void run() {
        OldManageListingPhotoUploadService.lambda$onHandleIntent$1(this.arg$1, this.arg$2);
    }
}
