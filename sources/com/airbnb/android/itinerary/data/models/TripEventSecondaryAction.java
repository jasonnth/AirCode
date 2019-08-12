package com.airbnb.android.itinerary.data.models;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.utils.BundleBuilder;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(builder = Builder.class)
@JsonSerialize
public abstract class TripEventSecondaryAction implements Parcelable {

    public static abstract class Builder {
        @JsonProperty
        public abstract TripEventSecondaryAction build();

        @JsonProperty
        public abstract Builder buttonText(String str);

        @JsonProperty
        public abstract Builder buttonType(SecondaryActionButtonType secondaryActionButtonType);

        @JsonProperty
        public abstract Builder destination(String str);

        @JsonProperty
        public abstract Builder destinationOptions(String str);

        @JsonProperty
        public abstract Builder title(String str);

        @JsonProperty
        public abstract Builder type(SecondaryActionType secondaryActionType);
    }

    @JsonProperty("button_text")
    public abstract String buttonText();

    @JsonProperty("button_type")
    public abstract SecondaryActionButtonType buttonType();

    @JsonProperty
    public abstract String destination();

    @JsonProperty("destination_options")
    public abstract String destinationOptions();

    @JsonProperty
    public abstract String title();

    public abstract Builder toBuilder();

    @JsonProperty
    public abstract SecondaryActionType type();

    public Bundle toBundle() {
        return ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("buttonText", buttonText())).putParcelable("buttonType", buttonType())).putString(ShareConstants.DESTINATION, destination())).putString("destinationOptions", destinationOptions())).putString("title", title())).putParcelable("type", type())).toBundle();
    }

    public static Builder builder() {
        return new Builder();
    }
}
