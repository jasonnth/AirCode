package com.airbnb.android.lib.payments.networking.createbill;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;

final class AutoValue_CreateBillParameters extends C$AutoValue_CreateBillParameters {
    public static final Creator<AutoValue_CreateBillParameters> CREATOR = new Creator<AutoValue_CreateBillParameters>() {
        public AutoValue_CreateBillParameters createFromParcel(Parcel in) {
            Boolean bool;
            String str;
            boolean z = true;
            String str2 = null;
            BillProductType valueOf = BillProductType.valueOf(in.readString());
            BillPriceQuote billPriceQuote = (BillPriceQuote) in.readParcelable(BillPriceQuote.class.getClassLoader());
            PaymentOption paymentOption = (PaymentOption) in.readParcelable(PaymentOption.class.getClassLoader());
            long readLong = in.readLong();
            String readString = in.readString();
            QuickPayParameters quickPayParameters = (QuickPayParameters) in.readParcelable(QuickPayParameters.class.getClassLoader());
            if (in.readInt() == 0) {
                if (in.readInt() != 1) {
                    z = false;
                }
                bool = Boolean.valueOf(z);
            } else {
                bool = null;
            }
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            if (in.readInt() == 0) {
                str2 = in.readString();
            }
            return new AutoValue_CreateBillParameters(valueOf, billPriceQuote, paymentOption, readLong, readString, quickPayParameters, bool, str, str2);
        }

        public AutoValue_CreateBillParameters[] newArray(int size) {
            return new AutoValue_CreateBillParameters[size];
        }
    };

    AutoValue_CreateBillParameters(BillProductType billProductType, BillPriceQuote billPriceQuote, PaymentOption selectedPaymentOption, long userId, String currency, QuickPayParameters quickPayParameters, Boolean shouldIncludeAirbnbCredit, String postalCode, String cvvNonce) {
        super(billProductType, billPriceQuote, selectedPaymentOption, userId, currency, quickPayParameters, shouldIncludeAirbnbCredit, postalCode, cvvNonce);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(billProductType().name());
        dest.writeParcelable(billPriceQuote(), flags);
        dest.writeParcelable(selectedPaymentOption(), flags);
        dest.writeLong(userId());
        dest.writeString(currency());
        dest.writeParcelable(quickPayParameters(), flags);
        if (shouldIncludeAirbnbCredit() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(shouldIncludeAirbnbCredit().booleanValue() ? 1 : 0);
        }
        if (postalCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(postalCode());
        }
        if (cvvNonce() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(cvvNonce());
    }

    public int describeContents() {
        return 0;
    }
}
