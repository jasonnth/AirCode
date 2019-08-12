package com.airbnb.android.core.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class TripSecondaryGuest implements Parcelable {

    public static abstract class Builder {
        public abstract TripSecondaryGuest build();

        @JsonProperty("email")
        public abstract Builder email(String str);

        @JsonProperty("first_name")
        public abstract Builder firstName(String str);

        @JsonProperty("last_name")
        public abstract Builder lastName(String str);
    }

    @JsonProperty("email")
    public abstract String email();

    @JsonProperty("first_name")
    public abstract String firstName();

    @JsonProperty("last_name")
    public abstract String lastName();

    public static Builder builder() {
        return new Builder();
    }
}
