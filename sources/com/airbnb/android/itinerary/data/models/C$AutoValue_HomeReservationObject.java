package com.airbnb.android.itinerary.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_HomeReservationObject reason: invalid class name */
abstract class C$AutoValue_HomeReservationObject extends HomeReservationObject {

    /* renamed from: id */
    private final String f1152id;
    private final String reservation;

    /* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_HomeReservationObject$Builder */
    static final class Builder extends com.airbnb.android.itinerary.data.models.HomeReservationObject.Builder {

        /* renamed from: id */
        private String f1153id;
        private String reservation;

        Builder() {
        }

        private Builder(HomeReservationObject source) {
            this.f1153id = source.mo10305id();
            this.reservation = source.reservation();
        }

        /* renamed from: id */
        public com.airbnb.android.itinerary.data.models.HomeReservationObject.Builder mo10310id(String id) {
            if (id == null) {
                throw new NullPointerException("Null id");
            }
            this.f1153id = id;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.HomeReservationObject.Builder reservation(String reservation2) {
            this.reservation = reservation2;
            return this;
        }

        public HomeReservationObject build() {
            String missing = "";
            if (this.f1153id == null) {
                missing = missing + " id";
            }
            if (missing.isEmpty()) {
                return new AutoValue_HomeReservationObject(this.f1153id, this.reservation);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_HomeReservationObject(String id, String reservation2) {
        if (id == null) {
            throw new NullPointerException("Null id");
        }
        this.f1152id = id;
        this.reservation = reservation2;
    }

    @JsonProperty
    /* renamed from: id */
    public String mo10305id() {
        return this.f1152id;
    }

    @JsonProperty
    public String reservation() {
        return this.reservation;
    }

    public String toString() {
        return "HomeReservationObject{id=" + this.f1152id + ", reservation=" + this.reservation + "}";
    }

    public com.airbnb.android.itinerary.data.models.HomeReservationObject.Builder toBuilder() {
        return new Builder(this);
    }
}
