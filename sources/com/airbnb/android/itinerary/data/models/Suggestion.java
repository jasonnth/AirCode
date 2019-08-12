package com.airbnb.android.itinerary.data.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(builder = Builder.class)
@JsonSerialize
public abstract class Suggestion implements Parcelable {

    public static abstract class Builder {
        public abstract Suggestion build();

        @JsonProperty
        /* renamed from: id */
        public abstract Builder mo10328id(long j);

        @JsonProperty
        public abstract Builder pictureUrl(String str);

        @JsonProperty
        public abstract Builder title(String str);

        @JsonProperty
        public abstract Builder type(SuggestionType suggestionType);
    }

    @JsonProperty
    /* renamed from: id */
    public abstract long mo10321id();

    @JsonProperty("picture_url")
    public abstract String pictureUrl();

    @JsonProperty("title")
    public abstract String title();

    public abstract Builder toBuilder();

    @JsonProperty
    public abstract SuggestionType type();

    public static Builder builder() {
        return new Builder();
    }
}
