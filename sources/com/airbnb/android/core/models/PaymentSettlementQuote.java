package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPaymentSettlementQuote;

public class PaymentSettlementQuote extends GenPaymentSettlementQuote {
    public static final Creator<PaymentSettlementQuote> CREATOR = new Creator<PaymentSettlementQuote>() {
        public PaymentSettlementQuote[] newArray(int size) {
            return new PaymentSettlementQuote[size];
        }

        public PaymentSettlementQuote createFromParcel(Parcel source) {
            PaymentSettlementQuote object = new PaymentSettlementQuote();
            object.readFromParcel(source);
            return object;
        }
    };
}
