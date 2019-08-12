package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.TravelDestination;
import com.airbnb.android.core.models.TravelDestinationsMetaData;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TravelDestinationsResponse extends BaseResponse {
    @JsonProperty("travel_destinations")
    public List<TravelDestination> destinations;
    @JsonProperty("metadata")
    public TravelDestinationsMetaData metaData;
}
