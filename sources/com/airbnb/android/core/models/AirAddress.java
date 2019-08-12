package com.airbnb.android.core.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AirAddress implements Parcelable {

    public static abstract class Builder {
        public abstract AirAddress build();

        @JsonProperty("locality")
        public abstract Builder city(String str);

        @JsonProperty("country")
        public abstract Builder country(String str);

        public abstract Builder countryCode(String str);

        public abstract Builder latitude(Double d);

        public abstract Builder longitude(Double d);

        @JsonProperty("postal_code")
        public abstract Builder postalCode(String str);

        @JsonProperty("region")
        public abstract Builder state(String str);

        @JsonProperty("street_address1")
        public abstract Builder streetAddressOne(String str);

        @JsonProperty("street_address2")
        public abstract Builder streetAddressTwo(String str);
    }

    @JsonProperty("locality")
    public abstract String city();

    @JsonProperty("country")
    public abstract String country();

    public abstract String countryCode();

    public abstract Double latitude();

    public abstract Double longitude();

    @JsonProperty("postal_code")
    public abstract String postalCode();

    @JsonProperty("region")
    public abstract String state();

    @JsonProperty("street_address1")
    public abstract String streetAddressOne();

    @JsonProperty("street_address2")
    public abstract String streetAddressTwo();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder();
    }
}
