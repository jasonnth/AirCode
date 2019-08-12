package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTravelCoupon implements Parcelable {
    @JsonProperty("code")
    protected String mCode;
    @JsonProperty("currency")
    protected String mCurrency;
    @JsonProperty("expires_after")
    protected AirDate mExpirationDate;
    @JsonProperty("formatted_localized_amount")
    protected String mFormattedLocalizedAmount;
    @JsonProperty("formatted_localized_min_trip_cost")
    protected String mFormattedLocalizedMinTripCost;
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("is_active")
    protected boolean mIsActive;
    @JsonProperty("max_savings_usd")
    protected int mMaxSavingsUsd;
    @JsonProperty("min_trip_cost")
    protected int mMinTripCost;
    @JsonProperty("one_time_only")
    protected boolean mOneTimeOnly;
    @JsonProperty("savings_amount")
    protected int mSavingsAmount;
    @JsonProperty("savings_percent")
    protected int mSavingsPercent;
    @JsonProperty("used")
    protected boolean mUsed;
    @JsonProperty("user_id")
    protected int mUserId;

    protected GenTravelCoupon(AirDate expirationDate, String code, String currency, String formattedLocalizedAmount, String formattedLocalizedMinTripCost, boolean oneTimeOnly, boolean used, boolean isActive, int id, int maxSavingsUsd, int minTripCost, int savingsAmount, int savingsPercent, int userId) {
        this();
        this.mExpirationDate = expirationDate;
        this.mCode = code;
        this.mCurrency = currency;
        this.mFormattedLocalizedAmount = formattedLocalizedAmount;
        this.mFormattedLocalizedMinTripCost = formattedLocalizedMinTripCost;
        this.mOneTimeOnly = oneTimeOnly;
        this.mUsed = used;
        this.mIsActive = isActive;
        this.mId = id;
        this.mMaxSavingsUsd = maxSavingsUsd;
        this.mMinTripCost = minTripCost;
        this.mSavingsAmount = savingsAmount;
        this.mSavingsPercent = savingsPercent;
        this.mUserId = userId;
    }

    protected GenTravelCoupon() {
    }

    public AirDate getExpirationDate() {
        return this.mExpirationDate;
    }

    @JsonProperty("expires_after")
    public void setExpirationDate(AirDate value) {
        this.mExpirationDate = value;
    }

    public String getCode() {
        return this.mCode;
    }

    @JsonProperty("code")
    public void setCode(String value) {
        this.mCode = value;
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    @JsonProperty("currency")
    public void setCurrency(String value) {
        this.mCurrency = value;
    }

    public String getFormattedLocalizedAmount() {
        return this.mFormattedLocalizedAmount;
    }

    @JsonProperty("formatted_localized_amount")
    public void setFormattedLocalizedAmount(String value) {
        this.mFormattedLocalizedAmount = value;
    }

    public String getFormattedLocalizedMinTripCost() {
        return this.mFormattedLocalizedMinTripCost;
    }

    @JsonProperty("formatted_localized_min_trip_cost")
    public void setFormattedLocalizedMinTripCost(String value) {
        this.mFormattedLocalizedMinTripCost = value;
    }

    public boolean isOneTimeOnly() {
        return this.mOneTimeOnly;
    }

    @JsonProperty("one_time_only")
    public void setOneTimeOnly(boolean value) {
        this.mOneTimeOnly = value;
    }

    public boolean isUsed() {
        return this.mUsed;
    }

    @JsonProperty("used")
    public void setUsed(boolean value) {
        this.mUsed = value;
    }

    public boolean isActive() {
        return this.mIsActive;
    }

    @JsonProperty("is_active")
    public void setIsActive(boolean value) {
        this.mIsActive = value;
    }

    public int getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(int value) {
        this.mId = value;
    }

    public int getMaxSavingsUsd() {
        return this.mMaxSavingsUsd;
    }

    @JsonProperty("max_savings_usd")
    public void setMaxSavingsUsd(int value) {
        this.mMaxSavingsUsd = value;
    }

    public int getMinTripCost() {
        return this.mMinTripCost;
    }

    @JsonProperty("min_trip_cost")
    public void setMinTripCost(int value) {
        this.mMinTripCost = value;
    }

    public int getSavingsAmount() {
        return this.mSavingsAmount;
    }

    @JsonProperty("savings_amount")
    public void setSavingsAmount(int value) {
        this.mSavingsAmount = value;
    }

    public int getSavingsPercent() {
        return this.mSavingsPercent;
    }

    @JsonProperty("savings_percent")
    public void setSavingsPercent(int value) {
        this.mSavingsPercent = value;
    }

    public int getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(int value) {
        this.mUserId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mExpirationDate, 0);
        parcel.writeString(this.mCode);
        parcel.writeString(this.mCurrency);
        parcel.writeString(this.mFormattedLocalizedAmount);
        parcel.writeString(this.mFormattedLocalizedMinTripCost);
        parcel.writeBooleanArray(new boolean[]{this.mOneTimeOnly, this.mUsed, this.mIsActive});
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mMaxSavingsUsd);
        parcel.writeInt(this.mMinTripCost);
        parcel.writeInt(this.mSavingsAmount);
        parcel.writeInt(this.mSavingsPercent);
        parcel.writeInt(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mExpirationDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCode = source.readString();
        this.mCurrency = source.readString();
        this.mFormattedLocalizedAmount = source.readString();
        this.mFormattedLocalizedMinTripCost = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mOneTimeOnly = bools[0];
        this.mUsed = bools[1];
        this.mIsActive = bools[2];
        this.mId = source.readInt();
        this.mMaxSavingsUsd = source.readInt();
        this.mMinTripCost = source.readInt();
        this.mSavingsAmount = source.readInt();
        this.mSavingsPercent = source.readInt();
        this.mUserId = source.readInt();
    }
}
