package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenOldPricingQuote implements Parcelable {
    @JsonProperty("base_price_native")
    protected int mBasePriceNative;
    @JsonProperty("cleaning_fee_native")
    protected int mCleaningFeeNative;
    @JsonProperty("localized_nightly_price")
    protected int mLocalizedNightlyPrice;
    @JsonProperty("nightly_price")
    protected int mNightlyPrice;
    @JsonProperty("service_fee_native")
    protected int mServiceFeeNative;
    @JsonProperty("subtotal_price_native")
    protected int mSubtotalPriceNative;
    @JsonProperty("tax_amount_native")
    protected int mTaxAmountNative;
    @JsonProperty("total_price_native")
    protected int mTotalPriceNative;

    protected GenOldPricingQuote(int totalPriceNative, int subtotalPriceNative, int taxAmountNative, int serviceFeeNative, int cleaningFeeNative, int basePriceNative, int nightlyPrice, int localizedNightlyPrice) {
        this();
        this.mTotalPriceNative = totalPriceNative;
        this.mSubtotalPriceNative = subtotalPriceNative;
        this.mTaxAmountNative = taxAmountNative;
        this.mServiceFeeNative = serviceFeeNative;
        this.mCleaningFeeNative = cleaningFeeNative;
        this.mBasePriceNative = basePriceNative;
        this.mNightlyPrice = nightlyPrice;
        this.mLocalizedNightlyPrice = localizedNightlyPrice;
    }

    protected GenOldPricingQuote() {
    }

    public int getTotalPriceNative() {
        return this.mTotalPriceNative;
    }

    @JsonProperty("total_price_native")
    public void setTotalPriceNative(int value) {
        this.mTotalPriceNative = value;
    }

    public int getSubtotalPriceNative() {
        return this.mSubtotalPriceNative;
    }

    @JsonProperty("subtotal_price_native")
    public void setSubtotalPriceNative(int value) {
        this.mSubtotalPriceNative = value;
    }

    public int getTaxAmountNative() {
        return this.mTaxAmountNative;
    }

    @JsonProperty("tax_amount_native")
    public void setTaxAmountNative(int value) {
        this.mTaxAmountNative = value;
    }

    public int getServiceFeeNative() {
        return this.mServiceFeeNative;
    }

    @JsonProperty("service_fee_native")
    public void setServiceFeeNative(int value) {
        this.mServiceFeeNative = value;
    }

    public int getCleaningFeeNative() {
        return this.mCleaningFeeNative;
    }

    @JsonProperty("cleaning_fee_native")
    public void setCleaningFeeNative(int value) {
        this.mCleaningFeeNative = value;
    }

    public int getBasePriceNative() {
        return this.mBasePriceNative;
    }

    @JsonProperty("base_price_native")
    public void setBasePriceNative(int value) {
        this.mBasePriceNative = value;
    }

    public int getNightlyPrice() {
        return this.mNightlyPrice;
    }

    @JsonProperty("nightly_price")
    public void setNightlyPrice(int value) {
        this.mNightlyPrice = value;
    }

    public int getLocalizedNightlyPrice() {
        return this.mLocalizedNightlyPrice;
    }

    @JsonProperty("localized_nightly_price")
    public void setLocalizedNightlyPrice(int value) {
        this.mLocalizedNightlyPrice = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mTotalPriceNative);
        parcel.writeInt(this.mSubtotalPriceNative);
        parcel.writeInt(this.mTaxAmountNative);
        parcel.writeInt(this.mServiceFeeNative);
        parcel.writeInt(this.mCleaningFeeNative);
        parcel.writeInt(this.mBasePriceNative);
        parcel.writeInt(this.mNightlyPrice);
        parcel.writeInt(this.mLocalizedNightlyPrice);
    }

    public void readFromParcel(Parcel source) {
        this.mTotalPriceNative = source.readInt();
        this.mSubtotalPriceNative = source.readInt();
        this.mTaxAmountNative = source.readInt();
        this.mServiceFeeNative = source.readInt();
        this.mCleaningFeeNative = source.readInt();
        this.mBasePriceNative = source.readInt();
        this.mNightlyPrice = source.readInt();
        this.mLocalizedNightlyPrice = source.readInt();
    }
}
