package com.airbnb.android.lib.businesstravel.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_IntentPrediction_P5CustomizationContent extends C$AutoValue_IntentPrediction_P5CustomizationContent {
    public static final Creator<AutoValue_IntentPrediction_P5CustomizationContent> CREATOR = new Creator<AutoValue_IntentPrediction_P5CustomizationContent>() {
        public AutoValue_IntentPrediction_P5CustomizationContent createFromParcel(Parcel in) {
            return new AutoValue_IntentPrediction_P5CustomizationContent((BTMobileSignupPromotion) in.readParcelable(BTMobileSignupPromotion.class.getClassLoader()));
        }

        public AutoValue_IntentPrediction_P5CustomizationContent[] newArray(int size) {
            return new AutoValue_IntentPrediction_P5CustomizationContent[size];
        }
    };

    AutoValue_IntentPrediction_P5CustomizationContent(BTMobileSignupPromotion mobileSignupPromotion) {
        super(mobileSignupPromotion);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mobileSignupPromotion(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
