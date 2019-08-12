package com.airbnb.android.core.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class CheckinArguments implements Parcelable {

    public static abstract class Builder {
        public abstract CheckinArguments build();

        @JsonProperty("checkin_listing_id")
        public abstract Builder listingId(long j);
    }

    public abstract long listingId();

    public static Builder builder() {
        return new Builder();
    }
}
