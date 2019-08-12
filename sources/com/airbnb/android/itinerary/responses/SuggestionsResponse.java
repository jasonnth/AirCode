package com.airbnb.android.itinerary.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.itinerary.data.models.Suggestion;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SuggestionsResponse extends BaseResponse {
    @JsonProperty("itinerary_recommendation_cards")
    public List<Suggestion> suggestions;
}
