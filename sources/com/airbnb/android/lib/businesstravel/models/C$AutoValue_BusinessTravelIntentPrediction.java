package com.airbnb.android.lib.businesstravel.models;

import com.airbnb.android.lib.businesstravel.models.IntentPrediction.P5CustomizationContent;
import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_BusinessTravelIntentPrediction reason: invalid class name */
abstract class C$AutoValue_BusinessTravelIntentPrediction extends BusinessTravelIntentPrediction {
    private final IntentPredictionType intentPredictionType;
    private final P5CustomizationContent p5CustomizationContent;
    private final int priority;

    /* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_BusinessTravelIntentPrediction$Builder */
    static final class Builder extends com.airbnb.android.lib.businesstravel.models.BusinessTravelIntentPrediction.Builder {
        private IntentPredictionType intentPredictionType;
        private P5CustomizationContent p5CustomizationContent;
        private Integer priority;

        Builder() {
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelIntentPrediction.Builder intentPredictionType(IntentPredictionType intentPredictionType2) {
            if (intentPredictionType2 == null) {
                throw new NullPointerException("Null intentPredictionType");
            }
            this.intentPredictionType = intentPredictionType2;
            return this;
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelIntentPrediction.Builder priority(int priority2) {
            this.priority = Integer.valueOf(priority2);
            return this;
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelIntentPrediction.Builder p5CustomizationContent(P5CustomizationContent p5CustomizationContent2) {
            this.p5CustomizationContent = p5CustomizationContent2;
            return this;
        }

        public BusinessTravelIntentPrediction build() {
            String missing = "";
            if (this.intentPredictionType == null) {
                missing = missing + " intentPredictionType";
            }
            if (this.priority == null) {
                missing = missing + " priority";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BusinessTravelIntentPrediction(this.intentPredictionType, this.priority.intValue(), this.p5CustomizationContent);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BusinessTravelIntentPrediction(IntentPredictionType intentPredictionType2, int priority2, P5CustomizationContent p5CustomizationContent2) {
        if (intentPredictionType2 == null) {
            throw new NullPointerException("Null intentPredictionType");
        }
        this.intentPredictionType = intentPredictionType2;
        this.priority = priority2;
        this.p5CustomizationContent = p5CustomizationContent2;
    }

    @JsonProperty("intent")
    public IntentPredictionType intentPredictionType() {
        return this.intentPredictionType;
    }

    @JsonProperty("priority")
    public int priority() {
        return this.priority;
    }

    @JsonProperty("p5_customization_content")
    public P5CustomizationContent p5CustomizationContent() {
        return this.p5CustomizationContent;
    }

    public String toString() {
        return "BusinessTravelIntentPrediction{intentPredictionType=" + this.intentPredictionType + ", priority=" + this.priority + ", p5CustomizationContent=" + this.p5CustomizationContent + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BusinessTravelIntentPrediction)) {
            return false;
        }
        BusinessTravelIntentPrediction that = (BusinessTravelIntentPrediction) o;
        if (this.intentPredictionType.equals(that.intentPredictionType()) && this.priority == that.priority()) {
            if (this.p5CustomizationContent == null) {
                if (that.p5CustomizationContent() == null) {
                    return true;
                }
            } else if (this.p5CustomizationContent.equals(that.p5CustomizationContent())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.intentPredictionType.hashCode()) * 1000003) ^ this.priority) * 1000003) ^ (this.p5CustomizationContent == null ? 0 : this.p5CustomizationContent.hashCode());
    }
}
