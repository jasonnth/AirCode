package com.airbnb.android.core.payments.models.clientparameters;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.payments.models.BillProductType;

final class AutoValue_PaidAmenityClientParameters extends C$AutoValue_PaidAmenityClientParameters {
    public static final Creator<AutoValue_PaidAmenityClientParameters> CREATOR = new Creator<AutoValue_PaidAmenityClientParameters>() {
        public AutoValue_PaidAmenityClientParameters createFromParcel(Parcel in) {
            return new AutoValue_PaidAmenityClientParameters(BillProductType.valueOf(in.readString()), in.readLong(), in.readInt(), in.readString());
        }

        public AutoValue_PaidAmenityClientParameters[] newArray(int size) {
            return new AutoValue_PaidAmenityClientParameters[size];
        }
    };

    AutoValue_PaidAmenityClientParameters(BillProductType productType, long paidAmenityId, int numberOfUnits, String reservationConfirmationCode) {
        super(productType, paidAmenityId, numberOfUnits, reservationConfirmationCode);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productType().name());
        dest.writeLong(paidAmenityId());
        dest.writeInt(numberOfUnits());
        dest.writeString(reservationConfirmationCode());
    }

    public int describeContents() {
        return 0;
    }
}
