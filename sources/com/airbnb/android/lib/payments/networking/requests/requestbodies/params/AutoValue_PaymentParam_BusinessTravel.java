package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_PaymentParam_BusinessTravel extends C$AutoValue_PaymentParam_BusinessTravel {
    public static final Creator<AutoValue_PaymentParam_BusinessTravel> CREATOR = new Creator<AutoValue_PaymentParam_BusinessTravel>() {
        public AutoValue_PaymentParam_BusinessTravel createFromParcel(Parcel in) {
            return new AutoValue_PaymentParam_BusinessTravel(in.readInt() == 0 ? Long.valueOf(in.readLong()) : null);
        }

        public AutoValue_PaymentParam_BusinessTravel[] newArray(int size) {
            return new AutoValue_PaymentParam_BusinessTravel[size];
        }
    };

    AutoValue_PaymentParam_BusinessTravel(Long businessEntityGroupId) {
        super(businessEntityGroupId);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (businessEntityGroupId() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeLong(businessEntityGroupId().longValue());
    }

    public int describeContents() {
        return 0;
    }
}
