package com.airbnb.android.listyourspacedls;

import com.airbnb.android.core.responses.PhotoUploadResponse;
import com.airbnb.android.photouploadmanager.PhotoUploadListenerUtil.SuccessListener;

final /* synthetic */ class LYSDataController$$Lambda$1 implements SuccessListener {
    private final LYSDataController arg$1;

    private LYSDataController$$Lambda$1(LYSDataController lYSDataController) {
        this.arg$1 = lYSDataController;
    }

    public static SuccessListener lambdaFactory$(LYSDataController lYSDataController) {
        return new LYSDataController$$Lambda$1(lYSDataController);
    }

    public void uploadSuccess(PhotoUploadResponse photoUploadResponse) {
        LYSDataController.lambda$new$0(this.arg$1, photoUploadResponse);
    }
}
