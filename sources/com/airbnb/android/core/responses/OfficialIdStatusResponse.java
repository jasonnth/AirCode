package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OfficialIdStatusResponse extends BaseResponse {
    public static final String ERROR = "ERROR";

    /* renamed from: OK */
    public static final String f1090OK = "OK";
    @JsonProperty("status")
    public String status;
}
