package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.CurrencyAmount;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPaymentSettlementQuote implements Parcelable {
    @JsonProperty("charge_amount")
    protected CurrencyAmount mChargeAmount;
    @JsonProperty("fx_fee_rate")
    protected String mFxFeeRate;
    @JsonProperty("settlement_currency_unit")
    protected CurrencyAmount mSettlementCurrencyUnit;
    @JsonProperty("target_currency_unit")
    protected CurrencyAmount mTargetCurrencyUnit;

    protected GenPaymentSettlementQuote(CurrencyAmount chargeAmount, CurrencyAmount settlementCurrencyUnit, CurrencyAmount targetCurrencyUnit, String fxFeeRate) {
        this();
        this.mChargeAmount = chargeAmount;
        this.mSettlementCurrencyUnit = settlementCurrencyUnit;
        this.mTargetCurrencyUnit = targetCurrencyUnit;
        this.mFxFeeRate = fxFeeRate;
    }

    protected GenPaymentSettlementQuote() {
    }

    public CurrencyAmount getChargeAmount() {
        return this.mChargeAmount;
    }

    @JsonProperty("charge_amount")
    public void setChargeAmount(CurrencyAmount value) {
        this.mChargeAmount = value;
    }

    public CurrencyAmount getSettlementCurrencyUnit() {
        return this.mSettlementCurrencyUnit;
    }

    @JsonProperty("settlement_currency_unit")
    public void setSettlementCurrencyUnit(CurrencyAmount value) {
        this.mSettlementCurrencyUnit = value;
    }

    public CurrencyAmount getTargetCurrencyUnit() {
        return this.mTargetCurrencyUnit;
    }

    @JsonProperty("target_currency_unit")
    public void setTargetCurrencyUnit(CurrencyAmount value) {
        this.mTargetCurrencyUnit = value;
    }

    public String getFxFeeRate() {
        return this.mFxFeeRate;
    }

    @JsonProperty("fx_fee_rate")
    public void setFxFeeRate(String value) {
        this.mFxFeeRate = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mChargeAmount, 0);
        parcel.writeParcelable(this.mSettlementCurrencyUnit, 0);
        parcel.writeParcelable(this.mTargetCurrencyUnit, 0);
        parcel.writeString(this.mFxFeeRate);
    }

    public void readFromParcel(Parcel source) {
        this.mChargeAmount = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mSettlementCurrencyUnit = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mTargetCurrencyUnit = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mFxFeeRate = source.readString();
    }
}
