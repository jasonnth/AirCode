package com.airbnb.android.core.requests.payments;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.LegacyPaymentOptionResponse;
import java.lang.reflect.Type;

public class LegacyPaymentOptionRequest extends BaseRequestV2<LegacyPaymentOptionResponse> {
    private final long gibraltarId;

    public static LegacyPaymentOptionRequest forGibraltarId(long gibraltarId2) {
        return new LegacyPaymentOptionRequest(gibraltarId2);
    }

    private LegacyPaymentOptionRequest(long gibraltarId2) {
        this.gibraltarId = gibraltarId2;
    }

    public Type successResponseType() {
        return LegacyPaymentOptionResponse.class;
    }

    public String getPath() {
        return "payment_options/g" + this.gibraltarId;
    }
}
