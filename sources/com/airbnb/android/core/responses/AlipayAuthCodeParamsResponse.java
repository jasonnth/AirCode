package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class AlipayAuthCodeParamsResponse extends BaseResponse {
    @JsonProperty("alipay_sdk_preparations")
    public ArrayList<AlipayPrepareSDKResponse> responseList;

    public static class AlipayPrepareSDKResponse {
        @JsonProperty("params")
        public String params;

        public AlipayPrepareSDKResponse(@JsonProperty("params") String params2) {
            this.params = params2;
        }
    }
}
