package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class HomesGuestIdentifications implements Parcelable {

    public static abstract class Builder {
        public abstract HomesGuestIdentifications build();

        public abstract Builder identifications(List<HomesGuestIdentity> list);
    }

    @JsonProperty("identifications")
    public abstract List<HomesGuestIdentity> identifications();

    public static Builder builder() {
        return new Builder();
    }
}
