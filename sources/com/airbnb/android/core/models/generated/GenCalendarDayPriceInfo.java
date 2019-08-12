package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.DynamicPricingExplanation;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenCalendarDayPriceInfo implements Parcelable {
    @JsonProperty("demand_based_pricing_overridden")
    protected boolean mDemandBasedPricingOverridden;
    @JsonProperty("dynamic_pricing_explanations")
    protected List<DynamicPricingExplanation> mDynamicPricingExplanations;
    @JsonProperty("local_currency")
    protected String mLocalCurrency;
    @JsonProperty("local_price")
    protected int mLocalPrice;
    @JsonProperty("native_currency")
    protected String mNativeCurrency;
    @JsonProperty("native_demand_based_price")
    protected int mNativeDemandBasedPrice;
    @JsonProperty("native_price")
    protected int mNativePrice;
    @JsonProperty("native_suggested_price")
    protected int mNativeSuggestedPrice;
    @JsonProperty("native_suggested_price_levels")
    protected List<Integer> mNativeSuggestedPriceLevels;
    @JsonProperty("type")
    protected String mTypeStr;

    protected GenCalendarDayPriceInfo(List<DynamicPricingExplanation> dynamicPricingExplanations, List<Integer> nativeSuggestedPriceLevels, String localCurrency, String nativeCurrency, String typeStr, boolean demandBasedPricingOverridden, int localPrice, int nativePrice, int nativeDemandBasedPrice, int nativeSuggestedPrice) {
        this();
        this.mDynamicPricingExplanations = dynamicPricingExplanations;
        this.mNativeSuggestedPriceLevels = nativeSuggestedPriceLevels;
        this.mLocalCurrency = localCurrency;
        this.mNativeCurrency = nativeCurrency;
        this.mTypeStr = typeStr;
        this.mDemandBasedPricingOverridden = demandBasedPricingOverridden;
        this.mLocalPrice = localPrice;
        this.mNativePrice = nativePrice;
        this.mNativeDemandBasedPrice = nativeDemandBasedPrice;
        this.mNativeSuggestedPrice = nativeSuggestedPrice;
    }

    protected GenCalendarDayPriceInfo() {
    }

    public List<DynamicPricingExplanation> getDynamicPricingExplanations() {
        return this.mDynamicPricingExplanations;
    }

    @JsonProperty("dynamic_pricing_explanations")
    public void setDynamicPricingExplanations(List<DynamicPricingExplanation> value) {
        this.mDynamicPricingExplanations = value;
    }

    public List<Integer> getNativeSuggestedPriceLevels() {
        return this.mNativeSuggestedPriceLevels;
    }

    @JsonProperty("native_suggested_price_levels")
    public void setNativeSuggestedPriceLevels(List<Integer> value) {
        this.mNativeSuggestedPriceLevels = value;
    }

    public String getLocalCurrency() {
        return this.mLocalCurrency;
    }

    @JsonProperty("local_currency")
    public void setLocalCurrency(String value) {
        this.mLocalCurrency = value;
    }

    public String getNativeCurrency() {
        return this.mNativeCurrency;
    }

    @JsonProperty("native_currency")
    public void setNativeCurrency(String value) {
        this.mNativeCurrency = value;
    }

    public String getTypeStr() {
        return this.mTypeStr;
    }

    @JsonProperty("type")
    public void setTypeStr(String value) {
        this.mTypeStr = value;
    }

    public boolean isDemandBasedPricingOverridden() {
        return this.mDemandBasedPricingOverridden;
    }

    @JsonProperty("demand_based_pricing_overridden")
    public void setDemandBasedPricingOverridden(boolean value) {
        this.mDemandBasedPricingOverridden = value;
    }

    public int getLocalPrice() {
        return this.mLocalPrice;
    }

    @JsonProperty("local_price")
    public void setLocalPrice(int value) {
        this.mLocalPrice = value;
    }

    public int getNativePrice() {
        return this.mNativePrice;
    }

    @JsonProperty("native_price")
    public void setNativePrice(int value) {
        this.mNativePrice = value;
    }

    public int getNativeDemandBasedPrice() {
        return this.mNativeDemandBasedPrice;
    }

    @JsonProperty("native_demand_based_price")
    public void setNativeDemandBasedPrice(int value) {
        this.mNativeDemandBasedPrice = value;
    }

    public int getNativeSuggestedPrice() {
        return this.mNativeSuggestedPrice;
    }

    @JsonProperty("native_suggested_price")
    public void setNativeSuggestedPrice(int value) {
        this.mNativeSuggestedPrice = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mDynamicPricingExplanations);
        parcel.writeValue(this.mNativeSuggestedPriceLevels);
        parcel.writeString(this.mLocalCurrency);
        parcel.writeString(this.mNativeCurrency);
        parcel.writeString(this.mTypeStr);
        parcel.writeBooleanArray(new boolean[]{this.mDemandBasedPricingOverridden});
        parcel.writeInt(this.mLocalPrice);
        parcel.writeInt(this.mNativePrice);
        parcel.writeInt(this.mNativeDemandBasedPrice);
        parcel.writeInt(this.mNativeSuggestedPrice);
    }

    public void readFromParcel(Parcel source) {
        this.mDynamicPricingExplanations = source.createTypedArrayList(DynamicPricingExplanation.CREATOR);
        this.mNativeSuggestedPriceLevels = (List) source.readValue(null);
        this.mLocalCurrency = source.readString();
        this.mNativeCurrency = source.readString();
        this.mTypeStr = source.readString();
        this.mDemandBasedPricingOverridden = source.createBooleanArray()[0];
        this.mLocalPrice = source.readInt();
        this.mNativePrice = source.readInt();
        this.mNativeDemandBasedPrice = source.readInt();
        this.mNativeSuggestedPrice = source.readInt();
    }
}
