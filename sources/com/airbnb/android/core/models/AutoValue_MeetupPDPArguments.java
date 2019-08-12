package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_MeetupPDPArguments extends C$AutoValue_MeetupPDPArguments {
    public static final Creator<AutoValue_MeetupPDPArguments> CREATOR = new Creator<AutoValue_MeetupPDPArguments>() {
        public AutoValue_MeetupPDPArguments createFromParcel(Parcel in) {
            return new AutoValue_MeetupPDPArguments(Long.valueOf(in.readLong()));
        }

        public AutoValue_MeetupPDPArguments[] newArray(int size) {
            return new AutoValue_MeetupPDPArguments[size];
        }
    };

    AutoValue_MeetupPDPArguments(Long id) {
        super(id);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mo9028id().longValue());
    }

    public int describeContents() {
        return 0;
    }
}
