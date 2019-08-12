package com.airbnb.android.core.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_AddressParts extends C$AutoValue_AddressParts {
    public static final Creator<AutoValue_AddressParts> CREATOR = new Creator<AutoValue_AddressParts>() {
        public AutoValue_AddressParts createFromParcel(Parcel in) {
            return new AutoValue_AddressParts(in.readString(), in.readString(), in.readString(), in.readString(), in.readString(), in.readString());
        }

        public AutoValue_AddressParts[] newArray(int size) {
            return new AutoValue_AddressParts[size];
        }
    };

    AutoValue_AddressParts(String street1, String street2, String city, String state, String zipCode, String countryCode) {
        super(street1, street2, city, state, zipCode, countryCode);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street1());
        dest.writeString(street2());
        dest.writeString(city());
        dest.writeString(state());
        dest.writeString(zipCode());
        dest.writeString(countryCode());
    }

    public int describeContents() {
        return 0;
    }
}
