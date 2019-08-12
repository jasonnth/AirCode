package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.GuestControls;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GuestControlsResponse extends BaseResponse {
    @JsonProperty("guest_control")
    public GuestControls guestControls;
}
