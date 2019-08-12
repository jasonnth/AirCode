package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.MParticleCustomerIdResponse;
import com.airbnb.android.core.utils.DateHelper;
import java.lang.reflect.Type;

public class MParticleCustomerIdRequest extends BaseRequestV2<MParticleCustomerIdResponse> {
    public static MParticleCustomerIdRequest newRequestForMParticleCustomerId() {
        return new MParticleCustomerIdRequest();
    }

    public String getPath() {
        return "mparticle_users";
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_YEAR_MILLIS;
    }

    public long getCacheOnlyTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return MParticleCustomerIdResponse.class;
    }
}
