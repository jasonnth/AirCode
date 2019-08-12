package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_PaymentParam extends C$AutoValue_PaymentParam {
    public static final Creator<AutoValue_PaymentParam> CREATOR = new Creator<AutoValue_PaymentParam>() {
        public AutoValue_PaymentParam createFromParcel(Parcel in) {
            String str;
            Boolean bool;
            String str2;
            boolean z = true;
            String str3 = null;
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            ExistingGibraltarInstrument existingGibraltarInstrument = (ExistingGibraltarInstrument) in.readParcelable(ExistingGibraltarInstrument.class.getClassLoader());
            if (in.readInt() == 0) {
                if (in.readInt() != 1) {
                    z = false;
                }
                bool = Boolean.valueOf(z);
            } else {
                bool = null;
            }
            AirbnbCredit airbnbCredit = (AirbnbCredit) in.readParcelable(AirbnbCredit.class.getClassLoader());
            if (in.readInt() == 0) {
                str2 = in.readString();
            } else {
                str2 = null;
            }
            if (in.readInt() == 0) {
                str3 = in.readString();
            }
            return new AutoValue_PaymentParam(str, existingGibraltarInstrument, bool, airbnbCredit, str2, str3, (InstrumentParams) in.readParcelable(InstrumentParams.class.getClassLoader()), (C0895BusinessTravel) in.readParcelable(C0895BusinessTravel.class.getClassLoader()), in.readString());
        }

        public AutoValue_PaymentParam[] newArray(int size) {
            return new AutoValue_PaymentParam[size];
        }
    };

    AutoValue_PaymentParam(String method, ExistingGibraltarInstrument existingGibraltarInstrument, Boolean userAgreedToCurrencyMismatch, AirbnbCredit airbnbCredit, String country, String zipRetry, InstrumentParams instrumentParams, C0895BusinessTravel businessTravel, String displayCurrency) {
        super(method, existingGibraltarInstrument, userAgreedToCurrencyMismatch, airbnbCredit, country, zipRetry, instrumentParams, businessTravel, displayCurrency);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (method() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(method());
        }
        dest.writeParcelable(existingGibraltarInstrument(), flags);
        if (userAgreedToCurrencyMismatch() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(userAgreedToCurrencyMismatch().booleanValue() ? 1 : 0);
        }
        dest.writeParcelable(airbnbCredit(), flags);
        if (country() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(country());
        }
        if (zipRetry() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(zipRetry());
        }
        dest.writeParcelable(instrumentParams(), flags);
        dest.writeParcelable(businessTravel(), flags);
        dest.writeString(displayCurrency());
    }

    public int describeContents() {
        return 0;
    }
}
