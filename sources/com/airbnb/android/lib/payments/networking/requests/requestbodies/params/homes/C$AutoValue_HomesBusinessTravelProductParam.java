package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.$AutoValue_HomesBusinessTravelProductParam reason: invalid class name */
abstract class C$AutoValue_HomesBusinessTravelProductParam extends HomesBusinessTravelProductParam {
    private final String businessTripNotes;

    /* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.$AutoValue_HomesBusinessTravelProductParam$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesBusinessTravelProductParam.Builder {
        private String businessTripNotes;

        Builder() {
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesBusinessTravelProductParam.Builder businessTripNotes(String businessTripNotes2) {
            this.businessTripNotes = businessTripNotes2;
            return this;
        }

        public HomesBusinessTravelProductParam build() {
            return new AutoValue_HomesBusinessTravelProductParam(this.businessTripNotes);
        }
    }

    C$AutoValue_HomesBusinessTravelProductParam(String businessTripNotes2) {
        this.businessTripNotes = businessTripNotes2;
    }

    @JsonProperty("trip_notes")
    public String businessTripNotes() {
        return this.businessTripNotes;
    }

    public String toString() {
        return "HomesBusinessTravelProductParam{businessTripNotes=" + this.businessTripNotes + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomesBusinessTravelProductParam)) {
            return false;
        }
        HomesBusinessTravelProductParam that = (HomesBusinessTravelProductParam) o;
        if (this.businessTripNotes != null) {
            return this.businessTripNotes.equals(that.businessTripNotes());
        }
        if (that.businessTripNotes() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.businessTripNotes == null ? 0 : this.businessTripNotes.hashCode());
    }
}
