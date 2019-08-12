package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_PaymentParam_AirbnbCredit extends C$AutoValue_PaymentParam_AirbnbCredit {
    public static final Creator<AutoValue_PaymentParam_AirbnbCredit> CREATOR = new Creator<AutoValue_PaymentParam_AirbnbCredit>() {
        public AutoValue_PaymentParam_AirbnbCredit createFromParcel(Parcel in) {
            boolean z = true;
            if (in.readInt() != 1) {
                z = false;
            }
            return new AutoValue_PaymentParam_AirbnbCredit(z);
        }

        public AutoValue_PaymentParam_AirbnbCredit[] newArray(int size) {
            return new AutoValue_PaymentParam_AirbnbCredit[size];
        }
    };

    AutoValue_PaymentParam_AirbnbCredit(boolean includeAirbnbCredit) {
        super(includeAirbnbCredit);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(includeAirbnbCredit() ? 1 : 0);
    }

    public int describeContents() {
        return 0;
    }
}
