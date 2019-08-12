package com.airbnb.android.superhero;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.utils.BundleBuilder;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(builder = Builder.class)
@JsonSerialize
public abstract class SuperHeroAction implements Parcelable {

    public static abstract class Builder {
        @JsonProperty
        public abstract SuperHeroAction build();

        @JsonProperty
        public abstract Builder destination(String str);

        @JsonProperty
        public abstract Builder destination_type(Long l);

        @JsonProperty
        /* renamed from: id */
        public abstract Builder mo11521id(long j);

        @JsonProperty
        public abstract Builder post_response(SuperHeroPostResponse superHeroPostResponse);

        @JsonProperty
        public abstract Builder super_hero_message_id(Long l);

        @JsonProperty
        public abstract Builder text(String str);
    }

    @JsonProperty
    public abstract String destination();

    @JsonProperty
    public abstract Long destination_type();

    @JsonProperty
    /* renamed from: id */
    public abstract long mo11513id();

    @JsonProperty
    public abstract SuperHeroPostResponse post_response();

    @JsonProperty
    public abstract Long super_hero_message_id();

    @JsonProperty
    public abstract String text();

    public Bundle toBundle() {
        return ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString(ShareConstants.DESTINATION, destination())).putLong("destinationType", destination_type().longValue())).putString("text", text())).putString("heroActionId", Long.toString(mo11513id()))).putParcelable("postResponse", post_response())).toBundle();
    }

    public DestinationType destinationTypeEnum() {
        return DestinationType.fromLong(destination_type().longValue());
    }

    public static Builder builder() {
        return new Builder();
    }
}
