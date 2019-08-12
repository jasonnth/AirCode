package com.airbnb.android.core.businesstravel.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_BusinessEntityGroup extends C$AutoValue_BusinessEntityGroup {
    public static final Creator<AutoValue_BusinessEntityGroup> CREATOR = new Creator<AutoValue_BusinessEntityGroup>() {
        public AutoValue_BusinessEntityGroup createFromParcel(Parcel in) {
            return new AutoValue_BusinessEntityGroup(in.readString(), in.readLong());
        }

        public AutoValue_BusinessEntityGroup[] newArray(int size) {
            return new AutoValue_BusinessEntityGroup[size];
        }
    };

    AutoValue_BusinessEntityGroup(String paymentMethodDisplayName, long id) {
        super(paymentMethodDisplayName, id);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getPaymentMethodDisplayName());
        dest.writeLong(getId());
    }

    public int describeContents() {
        return 0;
    }
}
