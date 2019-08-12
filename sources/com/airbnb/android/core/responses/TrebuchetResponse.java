package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.Trebuchet;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TrebuchetResponse {
    @JsonProperty("trebuchets")
    public List<Trebuchet> trebuchetsList;
}
