package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ParcelableBigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCurrencyAmount implements Parcelable {
    @JsonProperty("amount")
    protected ParcelableBigDecimal mAmount;
    @JsonProperty("amount_formatted")
    protected String mAmountFormatted;
    @JsonProperty("amount_micros")
    protected long mAmountMicros;
    @JsonProperty("currency")
    protected String mCurrency;
    @JsonProperty("is_micros_accuracy")
    protected boolean mIsMicrosAccuracy;

    protected GenCurrencyAmount(ParcelableBigDecimal amount, String amountFormatted, String currency, boolean isMicrosAccuracy, long amountMicros) {
        this();
        this.mAmount = amount;
        this.mAmountFormatted = amountFormatted;
        this.mCurrency = currency;
        this.mIsMicrosAccuracy = isMicrosAccuracy;
        this.mAmountMicros = amountMicros;
    }

    protected GenCurrencyAmount() {
    }

    public ParcelableBigDecimal getAmount() {
        return this.mAmount;
    }

    @JsonProperty("amount")
    public void setAmount(ParcelableBigDecimal value) {
        this.mAmount = value;
    }

    public String getAmountFormatted() {
        return this.mAmountFormatted;
    }

    @JsonProperty("amount_formatted")
    public void setAmountFormatted(String value) {
        this.mAmountFormatted = value;
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    @JsonProperty("currency")
    public void setCurrency(String value) {
        this.mCurrency = value;
    }

    public boolean isMicrosAccuracy() {
        return this.mIsMicrosAccuracy;
    }

    @JsonProperty("is_micros_accuracy")
    public void setIsMicrosAccuracy(boolean value) {
        this.mIsMicrosAccuracy = value;
    }

    public long getAmountMicros() {
        return this.mAmountMicros;
    }

    @JsonProperty("amount_micros")
    public void setAmountMicros(long value) {
        this.mAmountMicros = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mAmount, 0);
        parcel.writeString(this.mAmountFormatted);
        parcel.writeString(this.mCurrency);
        parcel.writeBooleanArray(new boolean[]{this.mIsMicrosAccuracy});
        parcel.writeLong(this.mAmountMicros);
    }

    public void readFromParcel(Parcel source) {
        this.mAmount = (ParcelableBigDecimal) source.readParcelable(ParcelableBigDecimal.class.getClassLoader());
        this.mAmountFormatted = source.readString();
        this.mCurrency = source.readString();
        this.mIsMicrosAccuracy = source.createBooleanArray()[0];
        this.mAmountMicros = source.readLong();
    }
}
