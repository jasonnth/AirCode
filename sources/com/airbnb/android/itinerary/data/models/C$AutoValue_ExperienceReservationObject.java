package com.airbnb.android.itinerary.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_ExperienceReservationObject reason: invalid class name */
abstract class C$AutoValue_ExperienceReservationObject extends ExperienceReservationObject {

    /* renamed from: id */
    private final String f1150id;
    private final String reservation;

    /* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_ExperienceReservationObject$Builder */
    static final class Builder extends com.airbnb.android.itinerary.data.models.ExperienceReservationObject.Builder {

        /* renamed from: id */
        private String f1151id;
        private String reservation;

        Builder() {
        }

        private Builder(ExperienceReservationObject source) {
            this.f1151id = source.mo10298id();
            this.reservation = source.reservation();
        }

        /* renamed from: id */
        public com.airbnb.android.itinerary.data.models.ExperienceReservationObject.Builder mo10303id(String id) {
            if (id == null) {
                throw new NullPointerException("Null id");
            }
            this.f1151id = id;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.ExperienceReservationObject.Builder reservation(String reservation2) {
            this.reservation = reservation2;
            return this;
        }

        public ExperienceReservationObject build() {
            String missing = "";
            if (this.f1151id == null) {
                missing = missing + " id";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ExperienceReservationObject(this.f1151id, this.reservation);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ExperienceReservationObject(String id, String reservation2) {
        if (id == null) {
            throw new NullPointerException("Null id");
        }
        this.f1150id = id;
        this.reservation = reservation2;
    }

    @JsonProperty
    /* renamed from: id */
    public String mo10298id() {
        return this.f1150id;
    }

    @JsonProperty
    public String reservation() {
        return this.reservation;
    }

    public String toString() {
        return "ExperienceReservationObject{id=" + this.f1150id + ", reservation=" + this.reservation + "}";
    }

    public com.airbnb.android.itinerary.data.models.ExperienceReservationObject.Builder toBuilder() {
        return new Builder(this);
    }
}
