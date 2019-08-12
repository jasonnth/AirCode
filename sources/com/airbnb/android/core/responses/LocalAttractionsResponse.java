package com.airbnb.android.core.responses;

import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class LocalAttractionsResponse {
    @JsonProperty("local_attractions")
    public List<LocalAttraction> localAttractions;
}
