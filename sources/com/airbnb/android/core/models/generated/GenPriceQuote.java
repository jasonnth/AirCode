package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPriceQuote implements Parcelable {
    @JsonProperty("bill_price_quote")
    protected BillPriceQuote mBillPriceQuote;

    protected GenPriceQuote(BillPriceQuote billPriceQuote) {
        this();
        this.mBillPriceQuote = billPriceQuote;
    }

    protected GenPriceQuote() {
    }

    public BillPriceQuote getBillPriceQuote() {
        return this.mBillPriceQuote;
    }

    @JsonProperty("bill_price_quote")
    public void setBillPriceQuote(BillPriceQuote value) {
        this.mBillPriceQuote = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mBillPriceQuote, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mBillPriceQuote = (BillPriceQuote) source.readParcelable(BillPriceQuote.class.getClassLoader());
    }
}
