package com.airbnb.android.core.responses.businesstravel;

import com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BusinessTravelEmployeeResponse {
    public BusinessTravelEmployee businessTravelEmployee;
    @JsonProperty("business_travel_employees")
    public List<BusinessTravelEmployee> businessTravelEmployees;
}
