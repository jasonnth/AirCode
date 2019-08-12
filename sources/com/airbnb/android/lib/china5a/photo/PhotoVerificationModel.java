package com.airbnb.android.lib.china5a.photo;

import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.core.responses.UserWrapperResponse;
import com.airbnb.android.lib.china5a.VerificationResponse;
import com.airbnb.android.lib.china5a.VerificationStepModel;
import p032rx.Observable;

public interface PhotoVerificationModel extends VerificationStepModel {
    Observable<VerificationResponse<UserWrapperResponse>> getUploadPhotoResponse(boolean z);

    Observable<VerificationResponse<AccountVerificationsResponse>> getVerifyPhotoResponse();

    void uploadPhoto(String str, boolean z);

    void verifyPhoto();
}
