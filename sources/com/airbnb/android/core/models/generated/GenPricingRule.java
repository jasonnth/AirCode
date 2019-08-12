package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPricingRule implements Parcelable {
    @JsonProperty("price_change")
    protected Integer mPriceChange;
    @JsonProperty("price_change_type")
    protected String mPriceChangeType;
    @JsonProperty("rule_type")
    protected String mRuleType;
    @JsonProperty("threshold_one")
    protected Integer mThresholdOne;
    @JsonProperty("threshold_three")
    protected Integer mThresholdThree;
    @JsonProperty("threshold_two")
    protected Integer mThresholdTwo;

    protected GenPricingRule(Integer priceChange, Integer thresholdOne, Integer thresholdTwo, Integer thresholdThree, String ruleType, String priceChangeType) {
        this();
        this.mPriceChange = priceChange;
        this.mThresholdOne = thresholdOne;
        this.mThresholdTwo = thresholdTwo;
        this.mThresholdThree = thresholdThree;
        this.mRuleType = ruleType;
        this.mPriceChangeType = priceChangeType;
    }

    protected GenPricingRule() {
    }

    public Integer getPriceChange() {
        return this.mPriceChange;
    }

    @JsonProperty("price_change")
    public void setPriceChange(Integer value) {
        this.mPriceChange = value;
    }

    public Integer getThresholdOne() {
        return this.mThresholdOne;
    }

    @JsonProperty("threshold_one")
    public void setThresholdOne(Integer value) {
        this.mThresholdOne = value;
    }

    public Integer getThresholdTwo() {
        return this.mThresholdTwo;
    }

    @JsonProperty("threshold_two")
    public void setThresholdTwo(Integer value) {
        this.mThresholdTwo = value;
    }

    public Integer getThresholdThree() {
        return this.mThresholdThree;
    }

    @JsonProperty("threshold_three")
    public void setThresholdThree(Integer value) {
        this.mThresholdThree = value;
    }

    public String getRuleType() {
        return this.mRuleType;
    }

    @JsonProperty("rule_type")
    public void setRuleType(String value) {
        this.mRuleType = value;
    }

    public String getPriceChangeType() {
        return this.mPriceChangeType;
    }

    @JsonProperty("price_change_type")
    public void setPriceChangeType(String value) {
        this.mPriceChangeType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mPriceChange);
        parcel.writeValue(this.mThresholdOne);
        parcel.writeValue(this.mThresholdTwo);
        parcel.writeValue(this.mThresholdThree);
        parcel.writeString(this.mRuleType);
        parcel.writeString(this.mPriceChangeType);
    }

    public void readFromParcel(Parcel source) {
        this.mPriceChange = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mThresholdOne = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mThresholdTwo = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mThresholdThree = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mRuleType = source.readString();
        this.mPriceChangeType = source.readString();
    }
}
