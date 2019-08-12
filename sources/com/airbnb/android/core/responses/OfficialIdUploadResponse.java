package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OfficialIdUploadResponse extends BaseResponse {
    private static final String STATUS_SUCCESS = "success";
    @JsonProperty("scan_reference")
    public String scanReference;
    @JsonProperty("status")
    public String status;

    public boolean isSuccess() {
        return "success".equals(this.status);
    }
}
