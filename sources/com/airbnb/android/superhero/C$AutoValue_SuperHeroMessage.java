package com.airbnb.android.superhero;

import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/* renamed from: com.airbnb.android.superhero.$AutoValue_SuperHeroMessage reason: invalid class name */
abstract class C$AutoValue_SuperHeroMessage extends SuperHeroMessage {
    private final String dismiss_text;
    private final AirDateTime ends_at;
    private final ArrayList<SuperHeroAction> hero_actions;
    private final String hero_type_string;

    /* renamed from: id */
    private final long f1363id;
    private final Double lat;
    private final Double lng;
    private final ArrayList<String> messages;
    private final Long radius;
    private final boolean should_takeover;
    private final AirDateTime starts_at;
    private final long status;
    private final Long trigger_type;

    /* renamed from: com.airbnb.android.superhero.$AutoValue_SuperHeroMessage$Builder */
    static final class Builder extends com.airbnb.android.superhero.SuperHeroMessage.Builder {
        private String dismiss_text;
        private AirDateTime ends_at;
        private ArrayList<SuperHeroAction> hero_actions;
        private String hero_type_string;

        /* renamed from: id */
        private Long f1364id;
        private Double lat;
        private Double lng;
        private ArrayList<String> messages;
        private Long radius;
        private Boolean should_takeover;
        private AirDateTime starts_at;
        private Long status;
        private Long trigger_type;

        Builder() {
        }

        private Builder(SuperHeroMessage source) {
            this.f1364id = Long.valueOf(source.mo11531id());
            this.starts_at = source.starts_at();
            this.ends_at = source.ends_at();
            this.lat = source.lat();
            this.lng = source.lng();
            this.radius = source.radius();
            this.dismiss_text = source.dismiss_text();
            this.hero_type_string = source.hero_type_string();
            this.messages = source.messages();
            this.should_takeover = Boolean.valueOf(source.should_takeover());
            this.hero_actions = source.hero_actions();
            this.status = Long.valueOf(source.status());
            this.trigger_type = source.trigger_type();
        }

        /* renamed from: id */
        public com.airbnb.android.superhero.SuperHeroMessage.Builder mo11547id(long id) {
            this.f1364id = Long.valueOf(id);
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder starts_at(AirDateTime starts_at2) {
            this.starts_at = starts_at2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder ends_at(AirDateTime ends_at2) {
            this.ends_at = ends_at2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder lat(Double lat2) {
            this.lat = lat2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder lng(Double lng2) {
            this.lng = lng2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder radius(Long radius2) {
            this.radius = radius2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder dismiss_text(String dismiss_text2) {
            this.dismiss_text = dismiss_text2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder hero_type_string(String hero_type_string2) {
            this.hero_type_string = hero_type_string2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder messages(ArrayList<String> messages2) {
            if (messages2 == null) {
                throw new NullPointerException("Null messages");
            }
            this.messages = messages2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder should_takeover(boolean should_takeover2) {
            this.should_takeover = Boolean.valueOf(should_takeover2);
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder hero_actions(ArrayList<SuperHeroAction> hero_actions2) {
            this.hero_actions = hero_actions2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder status(long status2) {
            this.status = Long.valueOf(status2);
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroMessage.Builder trigger_type(Long trigger_type2) {
            this.trigger_type = trigger_type2;
            return this;
        }

        public SuperHeroMessage build() {
            String missing = "";
            if (this.f1364id == null) {
                missing = missing + " id";
            }
            if (this.messages == null) {
                missing = missing + " messages";
            }
            if (this.should_takeover == null) {
                missing = missing + " should_takeover";
            }
            if (this.status == null) {
                missing = missing + " status";
            }
            if (missing.isEmpty()) {
                return new AutoValue_SuperHeroMessage(this.f1364id.longValue(), this.starts_at, this.ends_at, this.lat, this.lng, this.radius, this.dismiss_text, this.hero_type_string, this.messages, this.should_takeover.booleanValue(), this.hero_actions, this.status.longValue(), this.trigger_type);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_SuperHeroMessage(long id, AirDateTime starts_at2, AirDateTime ends_at2, Double lat2, Double lng2, Long radius2, String dismiss_text2, String hero_type_string2, ArrayList<String> messages2, boolean should_takeover2, ArrayList<SuperHeroAction> hero_actions2, long status2, Long trigger_type2) {
        this.f1363id = id;
        this.starts_at = starts_at2;
        this.ends_at = ends_at2;
        this.lat = lat2;
        this.lng = lng2;
        this.radius = radius2;
        this.dismiss_text = dismiss_text2;
        this.hero_type_string = hero_type_string2;
        if (messages2 == null) {
            throw new NullPointerException("Null messages");
        }
        this.messages = messages2;
        this.should_takeover = should_takeover2;
        this.hero_actions = hero_actions2;
        this.status = status2;
        this.trigger_type = trigger_type2;
    }

    @JsonProperty
    /* renamed from: id */
    public long mo11531id() {
        return this.f1363id;
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
    public Double lat() {
        return this.lat;
    }

    @JsonProperty
    public Double lng() {
        return this.lng;
    }

    @JsonProperty
    public Long radius() {
        return this.radius;
    }

    @JsonProperty
    public String dismiss_text() {
        return this.dismiss_text;
    }

    @JsonProperty
    public String hero_type_string() {
        return this.hero_type_string;
    }

    @JsonProperty
    public ArrayList<String> messages() {
        return this.messages;
    }

    @JsonProperty
    public boolean should_takeover() {
        return this.should_takeover;
    }

    @JsonProperty
    public ArrayList<SuperHeroAction> hero_actions() {
        return this.hero_actions;
    }

    @JsonProperty
    public long status() {
        return this.status;
    }

    @JsonProperty
    public Long trigger_type() {
        return this.trigger_type;
    }

    public String toString() {
        return "SuperHeroMessage{id=" + this.f1363id + ", starts_at=" + this.starts_at + ", ends_at=" + this.ends_at + ", lat=" + this.lat + ", lng=" + this.lng + ", radius=" + this.radius + ", dismiss_text=" + this.dismiss_text + ", hero_type_string=" + this.hero_type_string + ", messages=" + this.messages + ", should_takeover=" + this.should_takeover + ", hero_actions=" + this.hero_actions + ", status=" + this.status + ", trigger_type=" + this.trigger_type + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SuperHeroMessage)) {
            return false;
        }
        SuperHeroMessage that = (SuperHeroMessage) o;
        if (this.f1363id == that.mo11531id() && (this.starts_at != null ? this.starts_at.equals(that.starts_at()) : that.starts_at() == null) && (this.ends_at != null ? this.ends_at.equals(that.ends_at()) : that.ends_at() == null) && (this.lat != null ? this.lat.equals(that.lat()) : that.lat() == null) && (this.lng != null ? this.lng.equals(that.lng()) : that.lng() == null) && (this.radius != null ? this.radius.equals(that.radius()) : that.radius() == null) && (this.dismiss_text != null ? this.dismiss_text.equals(that.dismiss_text()) : that.dismiss_text() == null) && (this.hero_type_string != null ? this.hero_type_string.equals(that.hero_type_string()) : that.hero_type_string() == null) && this.messages.equals(that.messages()) && this.should_takeover == that.should_takeover() && (this.hero_actions != null ? this.hero_actions.equals(that.hero_actions()) : that.hero_actions() == null) && this.status == that.status()) {
            if (this.trigger_type == null) {
                if (that.trigger_type() == null) {
                    return true;
                }
            } else if (this.trigger_type.equals(that.trigger_type())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((int) (((long) (((((((((((((((((((((((int) (((long) (1 * 1000003)) ^ ((this.f1363id >>> 32) ^ this.f1363id))) * 1000003) ^ (this.starts_at == null ? 0 : this.starts_at.hashCode())) * 1000003) ^ (this.ends_at == null ? 0 : this.ends_at.hashCode())) * 1000003) ^ (this.lat == null ? 0 : this.lat.hashCode())) * 1000003) ^ (this.lng == null ? 0 : this.lng.hashCode())) * 1000003) ^ (this.radius == null ? 0 : this.radius.hashCode())) * 1000003) ^ (this.dismiss_text == null ? 0 : this.dismiss_text.hashCode())) * 1000003) ^ (this.hero_type_string == null ? 0 : this.hero_type_string.hashCode())) * 1000003) ^ this.messages.hashCode()) * 1000003) ^ (this.should_takeover ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE)) * 1000003) ^ (this.hero_actions == null ? 0 : this.hero_actions.hashCode())) * 1000003)) ^ ((this.status >>> 32) ^ this.status))) * 1000003;
        if (this.trigger_type != null) {
            i = this.trigger_type.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.superhero.SuperHeroMessage.Builder toBuilder() {
        return new Builder(this);
    }
}
