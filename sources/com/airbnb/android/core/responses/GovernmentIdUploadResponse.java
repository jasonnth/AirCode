package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GovernmentIdUploadResponse extends BaseResponse {
    @JsonProperty("government_id_result_id")
    public long resultId;
}
