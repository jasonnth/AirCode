package com.airbnb.android.lib.businesstravel.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.lib.businesstravel.models.IntentPrediction.P5CustomizationContent;

final class AutoValue_BusinessTravelIntentPrediction extends C$AutoValue_BusinessTravelIntentPrediction {
    public static final Creator<AutoValue_BusinessTravelIntentPrediction> CREATOR = new Creator<AutoValue_BusinessTravelIntentPrediction>() {
        public AutoValue_BusinessTravelIntentPrediction createFromParcel(Parcel in) {
            return new AutoValue_BusinessTravelIntentPrediction(IntentPredictionType.valueOf(in.readString()), in.readInt(), (P5CustomizationContent) in.readParcelable(P5CustomizationContent.class.getClassLoader()));
        }

        public AutoValue_BusinessTravelIntentPrediction[] newArray(int size) {
            return new AutoValue_BusinessTravelIntentPrediction[size];
        }
    };

    AutoValue_BusinessTravelIntentPrediction(IntentPredictionType intentPredictionType, int priority, P5CustomizationContent p5CustomizationContent) {
        super(intentPredictionType, priority, p5CustomizationContent);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(intentPredictionType().name());
        dest.writeInt(priority());
        dest.writeParcelable(p5CustomizationContent(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
