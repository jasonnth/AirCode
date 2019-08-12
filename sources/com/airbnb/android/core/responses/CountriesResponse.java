package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Country;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CountriesResponse extends BaseResponse {
    @JsonProperty("countries")
    public List<Country> countries;

    public String getFirstCountryCode() {
        if (this.countries == null || this.countries.isEmpty()) {
            return "";
        }
        return ((Country) this.countries.get(0)).getAlpha_2();
    }
}
