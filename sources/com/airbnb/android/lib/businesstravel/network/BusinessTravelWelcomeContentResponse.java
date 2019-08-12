package com.airbnb.android.lib.businesstravel.network;

import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessTravelWelcomeContentResponse {
    @JsonProperty("airbnb_for_work_signup_landing_content")
    public BusinessTravelWelcomeData businessTravelWelcomeData;
}
