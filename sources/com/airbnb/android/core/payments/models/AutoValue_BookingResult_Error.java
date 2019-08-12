package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_BookingResult_Error extends C$AutoValue_BookingResult_Error {
    public static final Creator<AutoValue_BookingResult_Error> CREATOR = new Creator<AutoValue_BookingResult_Error>() {
        public AutoValue_BookingResult_Error createFromParcel(Parcel in) {
            return new AutoValue_BookingResult_Error(in.readString(), in.readString(), in.readLong(), in.readLong());
        }

        public AutoValue_BookingResult_Error[] newArray(int size) {
            return new AutoValue_BookingResult_Error[size];
        }
    };

    AutoValue_BookingResult_Error(String msg, String field, long invalidateOption, long existingInstrumentId) {
        super(msg, field, invalidateOption, existingInstrumentId);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg());
        dest.writeString(field());
        dest.writeLong(invalidateOption());
        dest.writeLong(existingInstrumentId());
    }

    public int describeContents() {
        return 0;
    }
}
