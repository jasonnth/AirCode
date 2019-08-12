package com.airbnb.android.itinerary.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_PlaceReservationObject reason: invalid class name */
abstract class C$AutoValue_PlaceReservationObject extends PlaceReservationObject {

    /* renamed from: id */
    private final String f1154id;
    private final String reservation;

    /* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_PlaceReservationObject$Builder */
    static final class Builder extends com.airbnb.android.itinerary.data.models.PlaceReservationObject.Builder {

        /* renamed from: id */
        private String f1155id;
        private String reservation;

        Builder() {
        }

        private Builder(PlaceReservationObject source) {
            this.f1155id = source.mo10312id();
            this.reservation = source.reservation();
        }

        /* renamed from: id */
        public com.airbnb.android.itinerary.data.models.PlaceReservationObject.Builder mo10317id(String id) {
            if (id == null) {
                throw new NullPointerException("Null id");
            }
            this.f1155id = id;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.PlaceReservationObject.Builder reservation(String reservation2) {
            this.reservation = reservation2;
            return this;
        }

        public PlaceReservationObject build() {
            String missing = "";
            if (this.f1155id == null) {
                missing = missing + " id";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PlaceReservationObject(this.f1155id, this.reservation);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_PlaceReservationObject(String id, String reservation2) {
        if (id == null) {
            throw new NullPointerException("Null id");
        }
        this.f1154id = id;
        this.reservation = reservation2;
    }

    @JsonProperty
    /* renamed from: id */
    public String mo10312id() {
        return this.f1154id;
    }

    @JsonProperty
    public String reservation() {
        return this.reservation;
    }

    public String toString() {
        return "PlaceReservationObject{id=" + this.f1154id + ", reservation=" + this.reservation + "}";
    }

    public com.airbnb.android.itinerary.data.models.PlaceReservationObject.Builder toBuilder() {
        return new Builder(this);
    }
}
