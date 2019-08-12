package com.airbnb.android.lib.payments.factories;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;

final class AutoValue_BillPriceQuoteRequestParams extends C$AutoValue_BillPriceQuoteRequestParams {
    public static final Creator<AutoValue_BillPriceQuoteRequestParams> CREATOR = new Creator<AutoValue_BillPriceQuoteRequestParams>() {
        public AutoValue_BillPriceQuoteRequestParams createFromParcel(Parcel in) {
            boolean z;
            String str;
            String str2 = null;
            boolean z2 = true;
            QuickPayClientType valueOf = QuickPayClientType.valueOf(in.readString());
            PaymentOption paymentOption = (PaymentOption) in.readParcelable(PaymentOption.class.getClassLoader());
            QuickPayParameters quickPayParameters = (QuickPayParameters) in.readParcelable(QuickPayParameters.class.getClassLoader());
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            if (in.readInt() != 1) {
                z2 = false;
            }
            String readString = in.readString();
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            if (in.readInt() == 0) {
                str2 = in.readString();
            }
            return new AutoValue_BillPriceQuoteRequestParams(valueOf, paymentOption, quickPayParameters, z, z2, readString, str, str2);
        }

        public AutoValue_BillPriceQuoteRequestParams[] newArray(int size) {
            return new AutoValue_BillPriceQuoteRequestParams[size];
        }
    };

    AutoValue_BillPriceQuoteRequestParams(QuickPayClientType clientType, PaymentOption paymentOption, QuickPayParameters quickPayParameters, boolean includeAirbnbCredit, boolean userAgreedToCurrencyMismatch, String displayCurrency, String zipRetry, String couponCode) {
        super(clientType, paymentOption, quickPayParameters, includeAirbnbCredit, userAgreedToCurrencyMismatch, displayCurrency, zipRetry, couponCode);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        dest.writeString(clientType().name());
        dest.writeParcelable(paymentOption(), flags);
        dest.writeParcelable(quickPayParameters(), flags);
        dest.writeInt(includeAirbnbCredit() ? 1 : 0);
        if (userAgreedToCurrencyMismatch()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        dest.writeString(displayCurrency());
        if (zipRetry() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(zipRetry());
        }
        if (couponCode() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(couponCode());
    }

    public int describeContents() {
        return 0;
    }
}
