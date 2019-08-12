package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.TripTemplate;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TripTemplatesResponse extends BaseResponse {
    @JsonProperty("trip_templates")
    public List<TripTemplate> tripTemplates;
}
