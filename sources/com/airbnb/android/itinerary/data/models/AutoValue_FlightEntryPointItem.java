package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_FlightEntryPointItem extends C$AutoValue_FlightEntryPointItem {
    public static final Creator<AutoValue_FlightEntryPointItem> CREATOR = new Creator<AutoValue_FlightEntryPointItem>() {
        public AutoValue_FlightEntryPointItem createFromParcel(Parcel in) {
            return new AutoValue_FlightEntryPointItem(in.readString(), in.readString(), in.readString(), in.readString());
        }

        public AutoValue_FlightEntryPointItem[] newArray(int size) {
            return new AutoValue_FlightEntryPointItem[size];
        }
    };

    AutoValue_FlightEntryPointItem(String id, String title, String acceptText, String dismissText) {
        super(id, title, acceptText, dismissText);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mo57128id());
        dest.writeString(title());
        dest.writeString(acceptText());
        dest.writeString(dismissText());
    }

    public int describeContents() {
        return 0;
    }
}
