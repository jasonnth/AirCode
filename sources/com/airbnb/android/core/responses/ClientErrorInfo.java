package com.airbnb.android.core.responses;

import com.airbnb.android.core.airlock.models.Airlock;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientErrorInfo {
    @JsonProperty("airlock")
    public Airlock airlock;
}
