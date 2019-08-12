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
public abstract class SuperHeroPostResponse implements Parcelable {

    public static abstract class Builder {
        @JsonProperty
        public abstract SuperHeroPostResponse build();

        @JsonProperty
        public abstract Builder destination(String str);

        @JsonProperty
        public abstract Builder destination_type(long j);

        @JsonProperty
        public abstract Builder next_message(SuperHeroMessage superHeroMessage);
    }

    @JsonProperty
    public abstract String destination();

    @JsonProperty
    public abstract long destination_type();

    @JsonProperty
    public abstract SuperHeroMessage next_message();

    public Bundle toBundle() {
        return ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString(ShareConstants.DESTINATION, destination())).putLong("destinationType", destination_type())).putParcelable("nextMessage", next_message())).toBundle();
    }

    public DestinationType destinationTypeEnum() {
        return DestinationType.fromLong(destination_type());
    }

    public static Builder builder() {
        return new Builder();
    }
}
