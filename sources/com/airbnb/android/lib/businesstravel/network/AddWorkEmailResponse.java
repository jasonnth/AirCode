package com.airbnb.android.lib.businesstravel.network;

import com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddWorkEmailResponse {
    @JsonProperty("business_travel_employee")
    public BusinessTravelEmployee businessTravelEmployee;
}
