package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.PassportInformation;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetSavedPassportsResponse {
    @JsonProperty("passports")
    public List<PassportInformation> passports;
}
