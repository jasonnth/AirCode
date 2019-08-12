package com.airbnb.android.core.airlock.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_GenericAirlockFriction extends C$AutoValue_GenericAirlockFriction {
    public static final Creator<AutoValue_GenericAirlockFriction> CREATOR = new Creator<AutoValue_GenericAirlockFriction>() {
        public AutoValue_GenericAirlockFriction createFromParcel(Parcel in) {
            return new AutoValue_GenericAirlockFriction(in.readDouble());
        }

        public AutoValue_GenericAirlockFriction[] newArray(int size) {
            return new AutoValue_GenericAirlockFriction[size];
        }
    };

    AutoValue_GenericAirlockFriction(double version) {
        super(version);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(version());
    }

    public int describeContents() {
        return 0;
    }
}
