package com.airbnb.android.lib.china5a.phone;

import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.lib.china5a.VerificationResponse;
import com.airbnb.android.lib.china5a.VerificationStepModel;
import p032rx.Observable;

public interface PhoneVerificationModel extends VerificationStepModel {
    Observable<VerificationResponse<Object>> getRequestConfirmationCodeResponse();

    Observable<VerificationResponse<Object>> getVerifyConfirmationCodeResponse();

    void requestConfirmationCode(AirPhone airPhone);

    void verifyConfirmationCode(String str);
}
