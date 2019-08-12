package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CheckInInformation;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ListingCheckInInformationResponse extends BaseResponse {
    @JsonProperty("listing_checkin_informations")
    public List<CheckInInformation> checkInInformation;
}
