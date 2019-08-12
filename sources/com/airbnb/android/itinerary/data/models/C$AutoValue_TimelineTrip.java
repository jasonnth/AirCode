package com.airbnb.android.itinerary.data.models;

import com.airbnb.android.airdate.AirDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_TimelineTrip reason: invalid class name */
abstract class C$AutoValue_TimelineTrip extends TimelineTrip {
    private final ArrayList<String> bundle_photo_urls;
    private final String bundle_subtitle;
    private final String bundle_title;
    private final String confirmation_code;
    private final AirDateTime ends_at;
    private final AirDateTime expires_at;
    private final String pending_type;
    private final String picture;
    private final Boolean should_bundle;
    private final AirDateTime starts_at;
    private final String time_zone;
    private final String title;
    private final ArrayList<TripEvent> trip_schedule_cards;

    /* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_TimelineTrip$Builder */
    static final class Builder extends com.airbnb.android.itinerary.data.models.TimelineTrip.Builder {
        private ArrayList<String> bundle_photo_urls;
        private String bundle_subtitle;
        private String bundle_title;
        private String confirmation_code;
        private AirDateTime ends_at;
        private AirDateTime expires_at;
        private String pending_type;
        private String picture;
        private Boolean should_bundle;
        private AirDateTime starts_at;
        private String time_zone;
        private String title;
        private ArrayList<TripEvent> trip_schedule_cards;

        Builder() {
        }

        private Builder(TimelineTrip source) {
            this.confirmation_code = source.confirmation_code();
            this.starts_at = source.starts_at();
            this.ends_at = source.ends_at();
            this.expires_at = source.expires_at();
            this.time_zone = source.time_zone();
            this.title = source.title();
            this.bundle_title = source.bundle_title();
            this.bundle_subtitle = source.bundle_subtitle();
            this.bundle_photo_urls = source.bundle_photo_urls();
            this.picture = source.picture();
            this.pending_type = source.pending_type();
            this.trip_schedule_cards = source.trip_schedule_cards();
            this.should_bundle = source.should_bundle();
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder confirmation_code(String confirmation_code2) {
            if (confirmation_code2 == null) {
                throw new NullPointerException("Null confirmation_code");
            }
            this.confirmation_code = confirmation_code2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder starts_at(AirDateTime starts_at2) {
            this.starts_at = starts_at2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder ends_at(AirDateTime ends_at2) {
            this.ends_at = ends_at2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder expires_at(AirDateTime expires_at2) {
            this.expires_at = expires_at2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder time_zone(String time_zone2) {
            this.time_zone = time_zone2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder title(String title2) {
            this.title = title2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder bundle_title(String bundle_title2) {
            this.bundle_title = bundle_title2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder bundle_subtitle(String bundle_subtitle2) {
            this.bundle_subtitle = bundle_subtitle2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder bundle_photo_urls(ArrayList<String> bundle_photo_urls2) {
            this.bundle_photo_urls = bundle_photo_urls2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder picture(String picture2) {
            this.picture = picture2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder pending_type(String pending_type2) {
            this.pending_type = pending_type2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder trip_schedule_cards(ArrayList<TripEvent> trip_schedule_cards2) {
            this.trip_schedule_cards = trip_schedule_cards2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder should_bundle(Boolean should_bundle2) {
            this.should_bundle = should_bundle2;
            return this;
        }

        public TimelineTrip build() {
            String missing = "";
            if (this.confirmation_code == null) {
                missing = missing + " confirmation_code";
            }
            if (missing.isEmpty()) {
                return new AutoValue_TimelineTrip(this.confirmation_code, this.starts_at, this.ends_at, this.expires_at, this.time_zone, this.title, this.bundle_title, this.bundle_subtitle, this.bundle_photo_urls, this.picture, this.pending_type, this.trip_schedule_cards, this.should_bundle);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_TimelineTrip(String confirmation_code2, AirDateTime starts_at2, AirDateTime ends_at2, AirDateTime expires_at2, String time_zone2, String title2, String bundle_title2, String bundle_subtitle2, ArrayList<String> bundle_photo_urls2, String picture2, String pending_type2, ArrayList<TripEvent> trip_schedule_cards2, Boolean should_bundle2) {
        if (confirmation_code2 == null) {
            throw new NullPointerException("Null confirmation_code");
        }
        this.confirmation_code = confirmation_code2;
        this.starts_at = starts_at2;
        this.ends_at = ends_at2;
        this.expires_at = expires_at2;
        this.time_zone = time_zone2;
        this.title = title2;
        this.bundle_title = bundle_title2;
        this.bundle_subtitle = bundle_subtitle2;
        this.bundle_photo_urls = bundle_photo_urls2;
        this.picture = picture2;
        this.pending_type = pending_type2;
        this.trip_schedule_cards = trip_schedule_cards2;
        this.should_bundle = should_bundle2;
    }

    @JsonProperty
    public String confirmation_code() {
        return this.confirmation_code;
    }

    @JsonProperty
    public AirDateTime starts_at() {
        return this.starts_at;
    }

    @JsonProperty
    public AirDateTime ends_at() {
        return this.ends_at;
    }

    @JsonProperty
    public AirDateTime expires_at() {
        return this.expires_at;
    }

    @JsonProperty
    public String time_zone() {
        return this.time_zone;
    }

    @JsonProperty
    public String title() {
        return this.title;
    }

    @JsonProperty
    public String bundle_title() {
        return this.bundle_title;
    }

    @JsonProperty
    public String bundle_subtitle() {
        return this.bundle_subtitle;
    }

    @JsonProperty
    public ArrayList<String> bundle_photo_urls() {
        return this.bundle_photo_urls;
    }

    @JsonProperty
    public String picture() {
        return this.picture;
    }

    @JsonProperty
    public String pending_type() {
        return this.pending_type;
    }

    @JsonProperty
    public ArrayList<TripEvent> trip_schedule_cards() {
        return this.trip_schedule_cards;
    }

    @JsonProperty
    public Boolean should_bundle() {
        return this.should_bundle;
    }

    public String toString() {
        return "TimelineTrip{confirmation_code=" + this.confirmation_code + ", starts_at=" + this.starts_at + ", ends_at=" + this.ends_at + ", expires_at=" + this.expires_at + ", time_zone=" + this.time_zone + ", title=" + this.title + ", bundle_title=" + this.bundle_title + ", bundle_subtitle=" + this.bundle_subtitle + ", bundle_photo_urls=" + this.bundle_photo_urls + ", picture=" + this.picture + ", pending_type=" + this.pending_type + ", trip_schedule_cards=" + this.trip_schedule_cards + ", should_bundle=" + this.should_bundle + "}";
    }

    public com.airbnb.android.itinerary.data.models.TimelineTrip.Builder toBuilder() {
        return new Builder(this);
    }
}
