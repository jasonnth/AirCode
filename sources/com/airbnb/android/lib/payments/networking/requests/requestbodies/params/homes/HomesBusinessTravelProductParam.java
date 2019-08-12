package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class HomesBusinessTravelProductParam implements Parcelable {

    public static abstract class Builder {
        public abstract HomesBusinessTravelProductParam build();

        public abstract Builder businessTripNotes(String str);
    }

    @JsonProperty("trip_notes")
    public abstract String businessTripNotes();

    public static Builder builder() {
        return new Builder();
    }
}
