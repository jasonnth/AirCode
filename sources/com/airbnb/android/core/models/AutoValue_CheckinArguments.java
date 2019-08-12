package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_CheckinArguments extends C$AutoValue_CheckinArguments {
    public static final Creator<AutoValue_CheckinArguments> CREATOR = new Creator<AutoValue_CheckinArguments>() {
        public AutoValue_CheckinArguments createFromParcel(Parcel in) {
            return new AutoValue_CheckinArguments(in.readLong());
        }

        public AutoValue_CheckinArguments[] newArray(int size) {
            return new AutoValue_CheckinArguments[size];
        }
    };

    AutoValue_CheckinArguments(long listingId) {
        super(listingId);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(listingId());
    }

    public int describeContents() {
        return 0;
    }
}
