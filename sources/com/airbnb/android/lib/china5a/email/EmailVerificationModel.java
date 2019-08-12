package com.airbnb.android.lib.china5a.email;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.lib.china5a.VerificationResponse;
import com.airbnb.android.lib.china5a.VerificationStepModel;
import p032rx.Observable;

public interface EmailVerificationModel extends VerificationStepModel {
    void confirmEmail(String str);

    Observable<VerificationResponse<BaseResponse>> getConfirmEmailResponse();

    Observable<VerificationResponse<AccountVerificationsResponse>> getPollingVerificationResultResponse();

    void startPollingVerificationStatus();

    void stopPollingVerificationStatus();
}
