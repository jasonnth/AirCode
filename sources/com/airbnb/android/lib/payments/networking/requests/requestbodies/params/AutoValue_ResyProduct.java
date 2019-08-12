package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_ResyProduct extends C$AutoValue_ResyProduct {
    public static final Creator<AutoValue_ResyProduct> CREATOR = new Creator<AutoValue_ResyProduct>() {
        public AutoValue_ResyProduct createFromParcel(Parcel in) {
            return new AutoValue_ResyProduct(in.readString(), in.readLong(), in.readInt(), in.readLong());
        }

        public AutoValue_ResyProduct[] newArray(int size) {
            return new AutoValue_ResyProduct[size];
        }
    };

    AutoValue_ResyProduct(String productType, long resyReservationId, int numberOfGuests, long activityId) {
        super(productType, resyReservationId, numberOfGuests, activityId);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productType());
        dest.writeLong(resyReservationId());
        dest.writeInt(numberOfGuests());
        dest.writeLong(activityId());
    }

    public int describeContents() {
        return 0;
    }
}
