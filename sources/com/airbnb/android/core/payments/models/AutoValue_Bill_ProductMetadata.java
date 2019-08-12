package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_Bill_ProductMetadata extends C$AutoValue_Bill_ProductMetadata {
    public static final Creator<AutoValue_Bill_ProductMetadata> CREATOR = new Creator<AutoValue_Bill_ProductMetadata>() {
        public AutoValue_Bill_ProductMetadata createFromParcel(Parcel in) {
            return new AutoValue_Bill_ProductMetadata(in.readLong());
        }

        public AutoValue_Bill_ProductMetadata[] newArray(int size) {
            return new AutoValue_Bill_ProductMetadata[size];
        }
    };

    AutoValue_Bill_ProductMetadata(long placeReservationId) {
        super(placeReservationId);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(placeReservationId());
    }

    public int describeContents() {
        return 0;
    }
}
