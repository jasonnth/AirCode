package com.airbnb.android.lib.businesstravel.models;

import com.airbnb.android.lib.businesstravel.models.IntentPrediction.P5CustomizationContent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class BusinessTravelIntentPrediction extends IntentPrediction {

    public static abstract class Builder {
        public abstract BusinessTravelIntentPrediction build();

        @JsonProperty("intent")
        public abstract Builder intentPredictionType(IntentPredictionType intentPredictionType);

        @JsonProperty("p5_customization_content")
        public abstract Builder p5CustomizationContent(P5CustomizationContent p5CustomizationContent);

        @JsonProperty("priority")
        public abstract Builder priority(int i);
    }

    @JsonProperty("p5_customization_content")
    public abstract P5CustomizationContent p5CustomizationContent();

    public static Builder builder() {
        return new Builder();
    }

    public BTMobileSignupPromotion getBTMobileSignupPromotion() {
        if (hasBTMobileSignupPromotion()) {
            return p5CustomizationContent().mobileSignupPromotion();
        }
        return null;
    }

    private boolean hasBTMobileSignupPromotion() {
        return (p5CustomizationContent() == null || p5CustomizationContent().mobileSignupPromotion() == null) ? false : true;
    }
}
