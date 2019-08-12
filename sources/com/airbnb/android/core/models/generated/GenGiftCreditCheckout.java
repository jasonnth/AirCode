package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGiftCreditCheckout implements Parcelable {
    @JsonProperty("payment_id")
    protected long mPaymentId;
    @JsonProperty("sender_id")
    protected long mSenderId;

    protected GenGiftCreditCheckout(long paymentId, long senderId) {
        this();
        this.mPaymentId = paymentId;
        this.mSenderId = senderId;
    }

    protected GenGiftCreditCheckout() {
    }

    public long getPaymentId() {
        return this.mPaymentId;
    }

    @JsonProperty("payment_id")
    public void setPaymentId(long value) {
        this.mPaymentId = value;
    }

    public long getSenderId() {
        return this.mSenderId;
    }

    @JsonProperty("sender_id")
    public void setSenderId(long value) {
        this.mSenderId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(this.mPaymentId);
        parcel.writeLong(this.mSenderId);
    }

    public void readFromParcel(Parcel source) {
        this.mPaymentId = source.readLong();
        this.mSenderId = source.readLong();
    }
}
