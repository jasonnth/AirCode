package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.PhotoUploadResponse;
import com.airbnb.android.photouploadmanager.PhotoUploadListenerUtil.SuccessListener;

final /* synthetic */ class ManageListingDataController$$Lambda$1 implements SuccessListener {
    private final ManageListingDataController arg$1;

    private ManageListingDataController$$Lambda$1(ManageListingDataController manageListingDataController) {
        this.arg$1 = manageListingDataController;
    }

    public static SuccessListener lambdaFactory$(ManageListingDataController manageListingDataController) {
        return new ManageListingDataController$$Lambda$1(manageListingDataController);
    }

    public void uploadSuccess(PhotoUploadResponse photoUploadResponse) {
        ManageListingDataController.lambda$new$0(this.arg$1, photoUploadResponse);
    }
}
