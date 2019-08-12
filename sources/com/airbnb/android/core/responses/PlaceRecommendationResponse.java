package com.airbnb.android.core.responses;

import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceRecommendationResponse {
    @JsonProperty("place_recommendation")
    public LocalAttraction localAttraction;
}
