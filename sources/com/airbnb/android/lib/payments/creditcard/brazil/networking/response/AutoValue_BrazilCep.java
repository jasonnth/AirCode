package com.airbnb.android.lib.payments.creditcard.brazil.networking.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_BrazilCep extends C$AutoValue_BrazilCep {
    public static final Creator<AutoValue_BrazilCep> CREATOR = new Creator<AutoValue_BrazilCep>() {
        public AutoValue_BrazilCep createFromParcel(Parcel in) {
            return new AutoValue_BrazilCep(in.readString(), in.readString(), in.readString());
        }

        public AutoValue_BrazilCep[] newArray(int size) {
            return new AutoValue_BrazilCep[size];
        }
    };

    AutoValue_BrazilCep(String address, String city, String state) {
        super(address, city, state);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address());
        dest.writeString(city());
        dest.writeString(state());
    }

    public int describeContents() {
        return 0;
    }
}
