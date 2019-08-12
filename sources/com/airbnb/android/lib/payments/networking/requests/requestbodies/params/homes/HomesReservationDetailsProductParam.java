package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class HomesReservationDetailsProductParam implements Parcelable {

    public static abstract class Builder {
        public abstract HomesReservationDetailsProductParam build();

        public abstract Builder messageToHost(String str);
    }

    @JsonProperty("message_to_host")
    public abstract String messageToHost();

    public static Builder builder() {
        return new Builder();
    }
}
