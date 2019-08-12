package com.airbnb.android.itinerary.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_TripEventSecondaryAction reason: invalid class name */
abstract class C$AutoValue_TripEventSecondaryAction extends TripEventSecondaryAction {
    private final String buttonText;
    private final SecondaryActionButtonType buttonType;
    private final String destination;
    private final String destinationOptions;
    private final String title;
    private final SecondaryActionType type;

    /* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_TripEventSecondaryAction$Builder */
    static final class Builder extends com.airbnb.android.itinerary.data.models.TripEventSecondaryAction.Builder {
        private String buttonText;
        private SecondaryActionButtonType buttonType;
        private String destination;
        private String destinationOptions;
        private String title;
        private SecondaryActionType type;

        Builder() {
        }

        private Builder(TripEventSecondaryAction source) {
            this.buttonText = source.buttonText();
            this.buttonType = source.buttonType();
            this.destination = source.destination();
            this.destinationOptions = source.destinationOptions();
            this.title = source.title();
            this.type = source.type();
        }

        public com.airbnb.android.itinerary.data.models.TripEventSecondaryAction.Builder buttonText(String buttonText2) {
            this.buttonText = buttonText2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEventSecondaryAction.Builder buttonType(SecondaryActionButtonType buttonType2) {
            this.buttonType = buttonType2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEventSecondaryAction.Builder destination(String destination2) {
            this.destination = destination2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEventSecondaryAction.Builder destinationOptions(String destinationOptions2) {
            this.destinationOptions = destinationOptions2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEventSecondaryAction.Builder title(String title2) {
            this.title = title2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.TripEventSecondaryAction.Builder type(SecondaryActionType type2) {
            this.type = type2;
            return this;
        }

        public TripEventSecondaryAction build() {
            return new AutoValue_TripEventSecondaryAction(this.buttonText, this.buttonType, this.destination, this.destinationOptions, this.title, this.type);
        }
    }

    C$AutoValue_TripEventSecondaryAction(String buttonText2, SecondaryActionButtonType buttonType2, String destination2, String destinationOptions2, String title2, SecondaryActionType type2) {
        this.buttonText = buttonText2;
        this.buttonType = buttonType2;
        this.destination = destination2;
        this.destinationOptions = destinationOptions2;
        this.title = title2;
        this.type = type2;
    }

    @JsonProperty("button_text")
    public String buttonText() {
        return this.buttonText;
    }

    @JsonProperty("button_type")
    public SecondaryActionButtonType buttonType() {
        return this.buttonType;
    }

    @JsonProperty
    public String destination() {
        return this.destination;
    }

    @JsonProperty("destination_options")
    public String destinationOptions() {
        return this.destinationOptions;
    }

    @JsonProperty
    public String title() {
        return this.title;
    }

    @JsonProperty
    public SecondaryActionType type() {
        return this.type;
    }

    public String toString() {
        return "TripEventSecondaryAction{buttonText=" + this.buttonText + ", buttonType=" + this.buttonType + ", destination=" + this.destination + ", destinationOptions=" + this.destinationOptions + ", title=" + this.title + ", type=" + this.type + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TripEventSecondaryAction)) {
            return false;
        }
        TripEventSecondaryAction that = (TripEventSecondaryAction) o;
        if (this.buttonText != null ? this.buttonText.equals(that.buttonText()) : that.buttonText() == null) {
            if (this.buttonType != null ? this.buttonType.equals(that.buttonType()) : that.buttonType() == null) {
                if (this.destination != null ? this.destination.equals(that.destination()) : that.destination() == null) {
                    if (this.destinationOptions != null ? this.destinationOptions.equals(that.destinationOptions()) : that.destinationOptions() == null) {
                        if (this.title != null ? this.title.equals(that.title()) : that.title() == null) {
                            if (this.type == null) {
                                if (that.type() == null) {
                                    return true;
                                }
                            } else if (this.type.equals(that.type())) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((1 * 1000003) ^ (this.buttonText == null ? 0 : this.buttonText.hashCode())) * 1000003) ^ (this.buttonType == null ? 0 : this.buttonType.hashCode())) * 1000003) ^ (this.destination == null ? 0 : this.destination.hashCode())) * 1000003) ^ (this.destinationOptions == null ? 0 : this.destinationOptions.hashCode())) * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003;
        if (this.type != null) {
            i = this.type.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.itinerary.data.models.TripEventSecondaryAction.Builder toBuilder() {
        return new Builder(this);
    }
}
