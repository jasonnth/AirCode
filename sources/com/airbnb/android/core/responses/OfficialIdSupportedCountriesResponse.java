package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

public class OfficialIdSupportedCountriesResponse extends BaseResponse {
    @JsonProperty("countries")
    public List<Country> countries;

    public static class Country implements Serializable {
        private static final long serialVersionUID = 1;
        @JsonProperty("code")
        public String code;
        @JsonProperty("identifications")
        public List<Identification> identifications;

        public static class Identification implements Serializable {
            private static final long serialVersionUID = 1;
            @JsonProperty("type")
            public String type;

            public String toString() {
                return this.type;
            }
        }
    }
}
