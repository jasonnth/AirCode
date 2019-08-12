package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_PaymentParam_ExistingGibraltarInstrument extends C$AutoValue_PaymentParam_ExistingGibraltarInstrument {
    public static final Creator<AutoValue_PaymentParam_ExistingGibraltarInstrument> CREATOR = new Creator<AutoValue_PaymentParam_ExistingGibraltarInstrument>() {
        public AutoValue_PaymentParam_ExistingGibraltarInstrument createFromParcel(Parcel in) {
            return new AutoValue_PaymentParam_ExistingGibraltarInstrument(in.readInt() == 0 ? Long.valueOf(in.readLong()) : null);
        }

        public AutoValue_PaymentParam_ExistingGibraltarInstrument[] newArray(int size) {
            return new AutoValue_PaymentParam_ExistingGibraltarInstrument[size];
        }
    };

    AutoValue_PaymentParam_ExistingGibraltarInstrument(Long id) {
        super(id);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (mo10855id() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeLong(mo10855id().longValue());
    }

    public int describeContents() {
        return 0;
    }
}
