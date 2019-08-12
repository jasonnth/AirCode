package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_TripSecondaryGuest extends C$AutoValue_TripSecondaryGuest {
    public static final Creator<AutoValue_TripSecondaryGuest> CREATOR = new Creator<AutoValue_TripSecondaryGuest>() {
        public AutoValue_TripSecondaryGuest createFromParcel(Parcel in) {
            return new AutoValue_TripSecondaryGuest(in.readString(), in.readString(), in.readString());
        }

        public AutoValue_TripSecondaryGuest[] newArray(int size) {
            return new AutoValue_TripSecondaryGuest[size];
        }
    };

    AutoValue_TripSecondaryGuest(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName());
        dest.writeString(lastName());
        dest.writeString(email());
    }

    public int describeContents() {
        return 0;
    }
}
