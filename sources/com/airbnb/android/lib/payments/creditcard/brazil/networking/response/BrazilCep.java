package com.airbnb.android.lib.payments.creditcard.brazil.networking.response;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class BrazilCep implements Parcelable {

    public static abstract class Builder {
        @JsonProperty("address")
        public abstract Builder address(String str);

        public abstract BrazilCep build();

        @JsonProperty("city")
        public abstract Builder city(String str);

        @JsonProperty("state")
        public abstract Builder state(String str);
    }

    @JsonProperty("address")
    public abstract String address();

    @JsonProperty("city")
    public abstract String city();

    @JsonProperty("state")
    public abstract String state();

    public static Builder builder() {
        return new Builder();
    }
}
