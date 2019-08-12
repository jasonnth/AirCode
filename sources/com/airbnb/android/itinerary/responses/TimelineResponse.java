package com.airbnb.android.itinerary.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.TimelineMetadata;
import com.airbnb.android.itinerary.data.models.TimelineTrip;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TimelineResponse extends BaseResponse {
    @JsonProperty("metadata")
    public TimelineMetadata metadata;
    @JsonProperty("trip_schedules")
    public List<TimelineTrip> timelineTrips;
}
