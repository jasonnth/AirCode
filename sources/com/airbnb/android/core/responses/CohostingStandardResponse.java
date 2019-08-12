package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.CohostingStandard;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CohostingStandardResponse {
    @JsonProperty("cohosting_stat")
    public CohostingStandard cohostingStandard;
}
