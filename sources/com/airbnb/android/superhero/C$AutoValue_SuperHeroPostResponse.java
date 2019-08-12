package com.airbnb.android.superhero;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.superhero.$AutoValue_SuperHeroPostResponse reason: invalid class name */
abstract class C$AutoValue_SuperHeroPostResponse extends SuperHeroPostResponse {
    private final String destination;
    private final long destination_type;
    private final SuperHeroMessage next_message;

    /* renamed from: com.airbnb.android.superhero.$AutoValue_SuperHeroPostResponse$Builder */
    static final class Builder extends com.airbnb.android.superhero.SuperHeroPostResponse.Builder {
        private String destination;
        private Long destination_type;
        private SuperHeroMessage next_message;

        Builder() {
        }

        public com.airbnb.android.superhero.SuperHeroPostResponse.Builder destination(String destination2) {
            this.destination = destination2;
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroPostResponse.Builder destination_type(long destination_type2) {
            this.destination_type = Long.valueOf(destination_type2);
            return this;
        }

        public com.airbnb.android.superhero.SuperHeroPostResponse.Builder next_message(SuperHeroMessage next_message2) {
            this.next_message = next_message2;
            return this;
        }

        public SuperHeroPostResponse build() {
            String missing = "";
            if (this.destination_type == null) {
                missing = missing + " destination_type";
            }
            if (missing.isEmpty()) {
                return new AutoValue_SuperHeroPostResponse(this.destination, this.destination_type.longValue(), this.next_message);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_SuperHeroPostResponse(String destination2, long destination_type2, SuperHeroMessage next_message2) {
        this.destination = destination2;
        this.destination_type = destination_type2;
        this.next_message = next_message2;
    }

    @JsonProperty
    public String destination() {
        return this.destination;
    }

    @JsonProperty
    public long destination_type() {
        return this.destination_type;
    }

    @JsonProperty
    public SuperHeroMessage next_message() {
        return this.next_message;
    }

    public String toString() {
        return "SuperHeroPostResponse{destination=" + this.destination + ", destination_type=" + this.destination_type + ", next_message=" + this.next_message + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SuperHeroPostResponse)) {
            return false;
        }
        SuperHeroPostResponse that = (SuperHeroPostResponse) o;
        if (this.destination != null ? this.destination.equals(that.destination()) : that.destination() == null) {
            if (this.destination_type == that.destination_type()) {
                if (this.next_message == null) {
                    if (that.next_message() == null) {
                        return true;
                    }
                } else if (this.next_message.equals(that.next_message())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((int) (((long) (((1 * 1000003) ^ (this.destination == null ? 0 : this.destination.hashCode())) * 1000003)) ^ ((this.destination_type >>> 32) ^ this.destination_type))) * 1000003;
        if (this.next_message != null) {
            i = this.next_message.hashCode();
        }
        return h ^ i;
    }
}
