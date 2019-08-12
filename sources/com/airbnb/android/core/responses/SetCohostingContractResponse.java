package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.CohostingContract;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SetCohostingContractResponse {
    @JsonProperty("cohosting_contract")
    public CohostingContract cohostingContract;
}
