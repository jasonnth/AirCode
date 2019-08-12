package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SecurityCheck;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PutSecurityCheckResponse extends BaseResponse {
    @JsonProperty("security_check")
    public SecurityCheck securityCheck;
}
