package com.airbnb.android.lib.businesstravel.models;

import com.airbnb.android.lib.businesstravel.models.IntentPrediction.P5CustomizationContent;

/* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_IntentPrediction_P5CustomizationContent reason: invalid class name */
abstract class C$AutoValue_IntentPrediction_P5CustomizationContent extends P5CustomizationContent {
    private final BTMobileSignupPromotion mobileSignupPromotion;

    /* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_IntentPrediction_P5CustomizationContent$Builder */
    static final class Builder extends com.airbnb.android.lib.businesstravel.models.IntentPrediction.P5CustomizationContent.Builder {
        private BTMobileSignupPromotion mobileSignupPromotion;

        Builder() {
        }

        public com.airbnb.android.lib.businesstravel.models.IntentPrediction.P5CustomizationContent.Builder mobileSignupPromotion(BTMobileSignupPromotion mobileSignupPromotion2) {
            this.mobileSignupPromotion = mobileSignupPromotion2;
            return this;
        }

        public P5CustomizationContent build() {
            return new AutoValue_IntentPrediction_P5CustomizationContent(this.mobileSignupPromotion);
        }
    }

    C$AutoValue_IntentPrediction_P5CustomizationContent(BTMobileSignupPromotion mobileSignupPromotion2) {
        this.mobileSignupPromotion = mobileSignupPromotion2;
    }

    public BTMobileSignupPromotion mobileSignupPromotion() {
        return this.mobileSignupPromotion;
    }

    public String toString() {
        return "P5CustomizationContent{mobileSignupPromotion=" + this.mobileSignupPromotion + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof P5CustomizationContent)) {
            return false;
        }
        P5CustomizationContent that = (P5CustomizationContent) o;
        if (this.mobileSignupPromotion != null) {
            return this.mobileSignupPromotion.equals(that.mobileSignupPromotion());
        }
        if (that.mobileSignupPromotion() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.mobileSignupPromotion == null ? 0 : this.mobileSignupPromotion.hashCode());
    }
}
