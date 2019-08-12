package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CheckInGuide;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckInGuideResponse extends BaseResponse {
    @JsonProperty("check_in_guide")
    public CheckInGuide guide;
}
