package com.airbnb.android.payout.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_ExtraPayoutAttribute extends C$AutoValue_ExtraPayoutAttribute {
    public static final Creator<AutoValue_ExtraPayoutAttribute> CREATOR = new Creator<AutoValue_ExtraPayoutAttribute>() {
        public AutoValue_ExtraPayoutAttribute createFromParcel(Parcel in) {
            return new AutoValue_ExtraPayoutAttribute(in.readString(), in.readString());
        }

        public AutoValue_ExtraPayoutAttribute[] newArray(int size) {
            return new AutoValue_ExtraPayoutAttribute[size];
        }
    };

    AutoValue_ExtraPayoutAttribute(String key, String value) {
        super(key, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key());
        dest.writeString(value());
    }

    public int describeContents() {
        return 0;
    }
}
