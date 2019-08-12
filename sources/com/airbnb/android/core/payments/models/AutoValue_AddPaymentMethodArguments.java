package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_AddPaymentMethodArguments extends C$AutoValue_AddPaymentMethodArguments {
    public static final Creator<AutoValue_AddPaymentMethodArguments> CREATOR = new Creator<AutoValue_AddPaymentMethodArguments>() {
        public AutoValue_AddPaymentMethodArguments createFromParcel(Parcel in) {
            return new AutoValue_AddPaymentMethodArguments(AddPaymentMethodClientType.valueOf(in.readString()));
        }

        public AutoValue_AddPaymentMethodArguments[] newArray(int size) {
            return new AutoValue_AddPaymentMethodArguments[size];
        }
    };

    AutoValue_AddPaymentMethodArguments(AddPaymentMethodClientType clientType) {
        super(clientType);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getClientType().name());
    }

    public int describeContents() {
        return 0;
    }
}
