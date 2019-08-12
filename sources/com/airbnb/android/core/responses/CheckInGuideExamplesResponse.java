package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CheckInGuide;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckInGuideExamplesResponse extends BaseResponse {
    @JsonProperty("check_in_guide_example")
    public CheckInGuideExamples example;

    public class CheckInGuideExamples {
        @JsonProperty("check_in_guide")
        public CheckInGuide guide;

        public CheckInGuideExamples() {
        }
    }
}
