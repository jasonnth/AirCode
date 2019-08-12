package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CancellationPolicy;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCancellationPolicyResponse extends BaseResponse {
    @JsonProperty("cancellation_policy")
    public CancellationPolicy cancellationPolicy;
}
