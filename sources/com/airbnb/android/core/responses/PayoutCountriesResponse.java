package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class PayoutCountriesResponse extends BaseResponse {
    public ArrayList<String> countryCodes;
    public ArrayList<String> countryNames;
    @JsonProperty("countries")
    public List<Country> rawCountries;

    public static class Country {
        @JsonProperty("code")
        public String country_code;
        @JsonProperty("name")
        public String country_name;
    }

    public ArrayList<String> getSupportedCountries() {
        return this.countryCodes;
    }

    public ArrayList<String> getSupportedCountriesNames() {
        return this.countryNames;
    }
}
