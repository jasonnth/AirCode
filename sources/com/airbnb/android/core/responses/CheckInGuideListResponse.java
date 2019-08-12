package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CheckInGuide;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class CheckInGuideListResponse extends BaseResponse {
    @JsonProperty("check_in_guides")
    public ArrayList<CheckInGuide> guides;
}
