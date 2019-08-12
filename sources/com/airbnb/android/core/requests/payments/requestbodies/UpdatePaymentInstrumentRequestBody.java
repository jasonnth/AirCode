package com.airbnb.android.core.requests.payments.requestbodies;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class UpdatePaymentInstrumentRequestBody {

    public static class AlipayVerificationBody extends UpdatePaymentInstrumentRequestBody {
        @JsonProperty("verification_code")
        String verificationCode;

        public AlipayVerificationBody(String verificationCode2) {
            this.verificationCode = verificationCode2;
        }
    }
}
