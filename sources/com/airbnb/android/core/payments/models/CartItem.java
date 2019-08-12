package com.airbnb.android.core.payments.models;

import android.os.Parcelable;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class CartItem implements Parcelable {

    public static abstract class Builder {
        public abstract CartItem build();

        @JsonProperty("description")
        public abstract Builder description(String str);

        @JsonProperty("billing_parameters")
        public abstract Builder quickPayParameters(QuickPayParameters quickPayParameters);

        @JsonProperty("thumbnail_url")
        public abstract Builder thumbnailUrl(String str);

        @JsonProperty("title")
        public abstract Builder title(String str);
    }

    public abstract String description();

    public abstract QuickPayParameters quickPayParameters();

    public abstract String thumbnailUrl();

    public abstract String title();

    public static Builder builder() {
        return new Builder();
    }
}
