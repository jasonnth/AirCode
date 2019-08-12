package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReservationCancellationRefundBreakdown implements Parcelable {
    @JsonProperty("non_refundable_price")
    protected Price mNonRefundablePrice;
    @JsonProperty("refundable_price")
    protected Price mRefundablePrice;

    protected GenReservationCancellationRefundBreakdown(Price refundablePrice, Price nonRefundablePrice) {
        this();
        this.mRefundablePrice = refundablePrice;
        this.mNonRefundablePrice = nonRefundablePrice;
    }

    protected GenReservationCancellationRefundBreakdown() {
    }

    public Price getRefundablePrice() {
        return this.mRefundablePrice;
    }

    @JsonProperty("refundable_price")
    public void setRefundablePrice(Price value) {
        this.mRefundablePrice = value;
    }

    public Price getNonRefundablePrice() {
        return this.mNonRefundablePrice;
    }

    @JsonProperty("non_refundable_price")
    public void setNonRefundablePrice(Price value) {
        this.mNonRefundablePrice = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mRefundablePrice, 0);
        parcel.writeParcelable(this.mNonRefundablePrice, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mRefundablePrice = (Price) source.readParcelable(Price.class.getClassLoader());
        this.mNonRefundablePrice = (Price) source.readParcelable(Price.class.getClassLoader());
    }
}
