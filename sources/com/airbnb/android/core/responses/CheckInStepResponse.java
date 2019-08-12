package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CheckInStep;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckInStepResponse extends BaseResponse {
    @JsonProperty("check_in_guide_step")
    public CheckInStep step;
}
