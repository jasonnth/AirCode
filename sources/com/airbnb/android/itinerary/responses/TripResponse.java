package com.airbnb.android.itinerary.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.itinerary.data.models.TripEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TripResponse extends BaseResponse {
    @JsonProperty("trip_schedule_cards")
    public List<TripEvent> tripEvents;
}
