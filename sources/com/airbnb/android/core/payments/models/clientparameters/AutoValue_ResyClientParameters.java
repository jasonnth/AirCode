package com.airbnb.android.core.payments.models.clientparameters;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.payments.models.BillProductType;

final class AutoValue_ResyClientParameters extends C$AutoValue_ResyClientParameters {
    public static final Creator<AutoValue_ResyClientParameters> CREATOR = new Creator<AutoValue_ResyClientParameters>() {
        public AutoValue_ResyClientParameters createFromParcel(Parcel in) {
            return new AutoValue_ResyClientParameters(BillProductType.valueOf(in.readString()), in.readLong(), in.readInt(), in.readLong());
        }

        public AutoValue_ResyClientParameters[] newArray(int size) {
            return new AutoValue_ResyClientParameters[size];
        }
    };

    AutoValue_ResyClientParameters(BillProductType productType, long reservationId, int numberOfGuests, long activityId) {
        super(productType, reservationId, numberOfGuests, activityId);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productType().name());
        dest.writeLong(reservationId());
        dest.writeInt(numberOfGuests());
        dest.writeLong(activityId());
    }

    public int describeContents() {
        return 0;
    }
}
