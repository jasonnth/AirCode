package com.airbnb.android.lib.businesstravel.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.C1092As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.C1093Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(include = C1092As.PROPERTY, property = "intent", use = C1093Id.NAME, visible = true)
@JsonSubTypes({@Type(name = "business_travel", value = BusinessTravelIntentPrediction.class)})
public abstract class IntentPrediction implements Parcelable {

    @JsonDeserialize(builder = Builder.class)
    public static abstract class P5CustomizationContent implements Parcelable {

        public static abstract class Builder {
            public abstract P5CustomizationContent build();

            @JsonProperty("mobile_signup_promotion")
            public abstract Builder mobileSignupPromotion(BTMobileSignupPromotion bTMobileSignupPromotion);
        }

        public abstract BTMobileSignupPromotion mobileSignupPromotion();

        public static Builder builder() {
            return new Builder();
        }
    }

    @JsonProperty("intent")
    public abstract IntentPredictionType intentPredictionType();

    @JsonProperty("priority")
    public abstract int priority();
}
