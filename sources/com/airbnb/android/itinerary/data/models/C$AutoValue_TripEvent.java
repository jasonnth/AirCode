package com.airbnb.android.itinerary.data.models;

import com.airbnb.android.airdate.AirDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_TripEvent reason: invalid class name */
abstract class C$AutoValue_TripEvent extends TripEvent {
    private final String card_subtitle;
    private final String card_title;
    private final TripEventCardType card_type;
    private final String category;
    private final String confirmation_code;
    private final AirDateTime ends_at;
    private final String header;

    /* renamed from: id */
    private final Long f1158id;
    private final String picture;
    private final String primary_key;
    private final String schedule_confirmation_code;
    private final ArrayList<TripEventSecondaryAction> secondary_actions;
    private final AirDateTime starts_at;
    private final String time_zone;

    /* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_TripEvent$Builder */
    static final class Builder extends com.airbnb.android.itinerary.data.models.TripEvent.Builder {
        private String card_subtitle;
        private String card_title;
        private TripEventCardType card_type;
        private String category;
        private String confirmation_code;
        private AirDateTime ends_at;
        private String header;

        /* renamed from: id */
        private Long f1159id;
        private String picture;
        private String primary_key;
        private String schedule_confirmation_code;
        private ArrayList<TripEventSecondaryAction> secondary_actions;
        private AirDateTime starts_at;
        private String time_zone;

        Builder() {
        }

        private Builder(TripEvent source) {
            this.primary_key = source.primary_key();
            this.schedule_confirmation_code = source.schedule_confirmation_code();
            this.f1159id = source.mo10245id();
            this.card_type = source.card_type();
            this.category = source.category();
            this.confirmation_code = source.confirmation_code();
            this.picture = source.picture();
            this.starts_at = source.starts_at();
            this.ends_at = source.ends_at();
            this.time_zone = source.time_zone();
            this.header = source.header();
            this.card_title = source.card_title();
            this.card_subtitle = source.card_subtitle();
            this.secondary_actions = source.secondary_actions();
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder primary_key(String primary_key2) {
            if (primary_key2 == null) {
                throw new NullPointerException("Null primary_key");
            }
            this.primary_key = primary_key2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder schedule_confirmation_code(String schedule_confirmation_code2) {
            this.schedule_confirmation_code = schedule_confirmation_code2;
            return this;
        }

        /* renamed from: id */
        public com.airbnb.android.itinerary.data.models.TripEvent.Builder mo10371id(Long id) {
            this.f1159id = id;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder card_type(TripEventCardType card_type2) {
            this.card_type = card_type2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder category(String category2) {
            this.category = category2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder confirmation_code(String confirmation_code2) {
            this.confirmation_code = confirmation_code2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder picture(String picture2) {
            this.picture = picture2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder starts_at(AirDateTime starts_at2) {
            this.starts_at = starts_at2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder ends_at(AirDateTime ends_at2) {
            this.ends_at = ends_at2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder time_zone(String time_zone2) {
            this.time_zone = time_zone2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder header(String header2) {
            this.header = header2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder card_title(String card_title2) {
            this.card_title = card_title2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder card_subtitle(String card_subtitle2) {
            this.card_subtitle = card_subtitle2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEvent.Builder secondary_actions(ArrayList<TripEventSecondaryAction> secondary_actions2) {
            this.secondary_actions = secondary_actions2;
            return this;
        }

        public TripEvent build() {
            String missing = "";
            if (this.primary_key == null) {
                missing = missing + " primary_key";
            }
            if (missing.isEmpty()) {
                return new AutoValue_TripEvent(this.primary_key, this.schedule_confirmation_code, this.f1159id, this.card_type, this.category, this.confirmation_code, this.picture, this.starts_at, this.ends_at, this.time_zone, this.header, this.card_title, this.card_subtitle, this.secondary_actions);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_TripEvent(String primary_key2, String schedule_confirmation_code2, Long id, TripEventCardType card_type2, String category2, String confirmation_code2, String picture2, AirDateTime starts_at2, AirDateTime ends_at2, String time_zone2, String header2, String card_title2, String card_subtitle2, ArrayList<TripEventSecondaryAction> secondary_actions2) {
        if (primary_key2 == null) {
            throw new NullPointerException("Null primary_key");
        }
        this.primary_key = primary_key2;
        this.schedule_confirmation_code = schedule_confirmation_code2;
        this.f1158id = id;
        this.card_type = card_type2;
        this.category = category2;
        this.confirmation_code = confirmation_code2;
        this.picture = picture2;
        this.starts_at = starts_at2;
        this.ends_at = ends_at2;
        this.time_zone = time_zone2;
        this.header = header2;
        this.card_title = card_title2;
        this.card_subtitle = card_subtitle2;
        this.secondary_actions = secondary_actions2;
    }

    @JsonProperty
    public String primary_key() {
        return this.primary_key;
    }

    @JsonProperty
    public String schedule_confirmation_code() {
        return this.schedule_confirmation_code;
    }

    @JsonProperty
    /* renamed from: id */
    public Long mo10245id() {
        return this.f1158id;
    }

    @JsonProperty
    public TripEventCardType card_type() {
        return this.card_type;
    }

    @JsonProperty
    public String category() {
        return this.category;
    }

    @JsonProperty
    public String confirmation_code() {
        return this.confirmation_code;
    }

    @JsonProperty
    public String picture() {
        return this.picture;
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
    public String time_zone() {
        return this.time_zone;
    }

    @JsonProperty
    public String header() {
        return this.header;
    }

    @JsonProperty
    public String card_title() {
        return this.card_title;
    }

    @JsonProperty
    public String card_subtitle() {
        return this.card_subtitle;
    }

    @JsonProperty
    public ArrayList<TripEventSecondaryAction> secondary_actions() {
        return this.secondary_actions;
    }

    public String toString() {
        return "TripEvent{primary_key=" + this.primary_key + ", schedule_confirmation_code=" + this.schedule_confirmation_code + ", id=" + this.f1158id + ", card_type=" + this.card_type + ", category=" + this.category + ", confirmation_code=" + this.confirmation_code + ", picture=" + this.picture + ", starts_at=" + this.starts_at + ", ends_at=" + this.ends_at + ", time_zone=" + this.time_zone + ", header=" + this.header + ", card_title=" + this.card_title + ", card_subtitle=" + this.card_subtitle + ", secondary_actions=" + this.secondary_actions + "}";
    }

    public com.airbnb.android.itinerary.data.models.TripEvent.Builder toBuilder() {
        return new Builder(this);
    }
}
