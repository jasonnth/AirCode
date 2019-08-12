package com.airbnb.android.superhero;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.superhero.$AutoValue_SuperHeroAction reason: invalid class name */
abstract class C$AutoValue_SuperHeroAction extends SuperHeroAction {
    private final String destination;
    private final Long destination_type;

    /* renamed from: id */
    private final long f1361id;
    private final SuperHeroPostResponse post_response;
    private final Long super_hero_message_id;
    private final String text;

    /* renamed from: com.airbnb.android.superhero.$AutoValue_SuperHeroAction$Builder */
    static final class Builder extends com.airbnb.android.superhero.SuperHeroAction.Builder {
        private String destination;
        private Long destination_type;

        /* renamed from: id */
        private Long f1362id;
        private SuperHeroPostResponse post_response;
        private Long super_hero_message_id;
        private String text;

        Builder() {
        }

        public com.airbnb.android.superhero.SuperHeroAction.Builder destination(String destination2) {
            this.destination = destination2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroAction.Builder super_hero_message_id(Long super_hero_message_id2) {
            this.super_hero_message_id = super_hero_message_id2;
            return this;
        }

        /* renamed from: id */
        public com.airbnb.android.superhero.SuperHeroAction.Builder mo11521id(long id) {
            this.f1362id = Long.valueOf(id);
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroAction.Builder text(String text2) {
            this.text = text2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroAction.Builder destination_type(Long destination_type2) {
            this.destination_type = destination_type2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroAction.Builder post_response(SuperHeroPostResponse post_response2) {
            this.post_response = post_response2;
            return this;
        }

        public SuperHeroAction build() {
            String missing = "";
            if (this.f1362id == null) {
                missing = missing + " id";
            }
            if (missing.isEmpty()) {
                return new AutoValue_SuperHeroAction(this.destination, this.super_hero_message_id, this.f1362id.longValue(), this.text, this.destination_type, this.post_response);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_SuperHeroAction(String destination2, Long super_hero_message_id2, long id, String text2, Long destination_type2, SuperHeroPostResponse post_response2) {
        this.destination = destination2;
        this.super_hero_message_id = super_hero_message_id2;
        this.f1361id = id;
        this.text = text2;
        this.destination_type = destination_type2;
        this.post_response = post_response2;
    }

    @JsonProperty
    public String destination() {
        return this.destination;
    }

    @JsonProperty
    public Long super_hero_message_id() {
        return this.super_hero_message_id;
    }

    @JsonProperty
    /* renamed from: id */
    public long mo11513id() {
        return this.f1361id;
    }

    @JsonProperty
    public String text() {
        return this.text;
    }

    @JsonProperty
    public Long destination_type() {
        return this.destination_type;
    }

    @JsonProperty
    public SuperHeroPostResponse post_response() {
        return this.post_response;
    }

    public String toString() {
        return "SuperHeroAction{destination=" + this.destination + ", super_hero_message_id=" + this.super_hero_message_id + ", id=" + this.f1361id + ", text=" + this.text + ", destination_type=" + this.destination_type + ", post_response=" + this.post_response + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SuperHeroAction)) {
            return false;
        }
        SuperHeroAction that = (SuperHeroAction) o;
        if (this.destination != null ? this.destination.equals(that.destination()) : that.destination() == null) {
            if (this.super_hero_message_id != null ? this.super_hero_message_id.equals(that.super_hero_message_id()) : that.super_hero_message_id() == null) {
                if (this.f1361id == that.mo11513id() && (this.text != null ? this.text.equals(that.text()) : that.text() == null) && (this.destination_type != null ? this.destination_type.equals(that.destination_type()) : that.destination_type() == null)) {
                    if (this.post_response == null) {
                        if (that.post_response() == null) {
                            return true;
                        }
                    } else if (this.post_response.equals(that.post_response())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((int) (((long) (((((1 * 1000003) ^ (this.destination == null ? 0 : this.destination.hashCode())) * 1000003) ^ (this.super_hero_message_id == null ? 0 : this.super_hero_message_id.hashCode())) * 1000003)) ^ ((this.f1361id >>> 32) ^ this.f1361id))) * 1000003) ^ (this.text == null ? 0 : this.text.hashCode())) * 1000003) ^ (this.destination_type == null ? 0 : this.destination_type.hashCode())) * 1000003;
        if (this.post_response != null) {
            i = this.post_response.hashCode();
        }
        return h ^ i;
    }
}
