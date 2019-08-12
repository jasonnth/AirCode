package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_PaymentParam_InstrumentParams extends C$AutoValue_PaymentParam_InstrumentParams {
    public static final Creator<AutoValue_PaymentParam_InstrumentParams> CREATOR = new Creator<AutoValue_PaymentParam_InstrumentParams>() {
        public AutoValue_PaymentParam_InstrumentParams createFromParcel(Parcel in) {
            return new AutoValue_PaymentParam_InstrumentParams(in.readInt() == 0 ? in.readString() : null);
        }

        public AutoValue_PaymentParam_InstrumentParams[] newArray(int size) {
            return new AutoValue_PaymentParam_InstrumentParams[size];
        }
    };

    AutoValue_PaymentParam_InstrumentParams(String paymentMethodCseCvvPayload) {
        super(paymentMethodCseCvvPayload);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (paymentMethodCseCvvPayload() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(paymentMethodCseCvvPayload());
    }

    public int describeContents() {
        return 0;
    }
}
