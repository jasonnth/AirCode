package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.AlipayAuthCodeParamsResponse;
import java.lang.reflect.Type;

public class AlipayAuthCodeParamsRequest extends BaseRequestV2<AlipayAuthCodeParamsResponse> {
    public Type successResponseType() {
        return AlipayAuthCodeParamsResponse.class;
    }

    public String getPath() {
        return "alipay_sdk_preparations";
    }
}
