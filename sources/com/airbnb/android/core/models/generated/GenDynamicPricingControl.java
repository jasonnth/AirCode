package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenDynamicPricingControl implements Parcelable {
    @JsonProperty("desired_hosting_frequency")
    protected Integer mDesiredHostingFrequencyKey;
    @JsonProperty("hosting_frequency_options_version")
    protected int mHostingFrequencyVersionKey;
    @JsonProperty("is_enabled")
    protected boolean mIsEnabled;
    @JsonProperty("last_enabled_at")
    protected AirDateTime mLastEnabledAt;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("max_price")
    protected int mMaxPrice;
    @JsonProperty("min_price")
    protected int mMinPrice;
    @JsonProperty("suggested_max_price")
    protected int mSuggestedMaxPrice;
    @JsonProperty("suggested_min_price")
    protected int mSuggestedMinPrice;

    protected GenDynamicPricingControl(AirDateTime lastEnabledAt, Integer desiredHostingFrequencyKey, boolean isEnabled, int maxPrice, int minPrice, int suggestedMinPrice, int suggestedMaxPrice, int hostingFrequencyVersionKey, long listingId) {
        this();
        this.mLastEnabledAt = lastEnabledAt;
        this.mDesiredHostingFrequencyKey = desiredHostingFrequencyKey;
        this.mIsEnabled = isEnabled;
        this.mMaxPrice = maxPrice;
        this.mMinPrice = minPrice;
        this.mSuggestedMinPrice = suggestedMinPrice;
        this.mSuggestedMaxPrice = suggestedMaxPrice;
        this.mHostingFrequencyVersionKey = hostingFrequencyVersionKey;
        this.mListingId = listingId;
    }

    protected GenDynamicPricingControl() {
    }

    public AirDateTime getLastEnabledAt() {
        return this.mLastEnabledAt;
    }

    @JsonProperty("last_enabled_at")
    public void setLastEnabledAt(AirDateTime value) {
        this.mLastEnabledAt = value;
    }

    public Integer getDesiredHostingFrequencyKey() {
        return this.mDesiredHostingFrequencyKey;
    }

    @JsonProperty("desired_hosting_frequency")
    public void setDesiredHostingFrequencyKey(Integer value) {
        this.mDesiredHostingFrequencyKey = value;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    @JsonProperty("is_enabled")
    public void setIsEnabled(boolean value) {
        this.mIsEnabled = value;
    }

    public int getMaxPrice() {
        return this.mMaxPrice;
    }

    @JsonProperty("max_price")
    public void setMaxPrice(int value) {
        this.mMaxPrice = value;
    }

    public int getMinPrice() {
        return this.mMinPrice;
    }

    @JsonProperty("min_price")
    public void setMinPrice(int value) {
        this.mMinPrice = value;
    }

    public int getSuggestedMinPrice() {
        return this.mSuggestedMinPrice;
    }

    @JsonProperty("suggested_min_price")
    public void setSuggestedMinPrice(int value) {
        this.mSuggestedMinPrice = value;
    }

    public int getSuggestedMaxPrice() {
        return this.mSuggestedMaxPrice;
    }

    @JsonProperty("suggested_max_price")
    public void setSuggestedMaxPrice(int value) {
        this.mSuggestedMaxPrice = value;
    }

    public int getHostingFrequencyVersionKey() {
        return this.mHostingFrequencyVersionKey;
    }

    @JsonProperty("hosting_frequency_options_version")
    public void setHostingFrequencyVersionKey(int value) {
        this.mHostingFrequencyVersionKey = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mLastEnabledAt, 0);
        parcel.writeValue(this.mDesiredHostingFrequencyKey);
        parcel.writeBooleanArray(new boolean[]{this.mIsEnabled});
        parcel.writeInt(this.mMaxPrice);
        parcel.writeInt(this.mMinPrice);
        parcel.writeInt(this.mSuggestedMinPrice);
        parcel.writeInt(this.mSuggestedMaxPrice);
        parcel.writeInt(this.mHostingFrequencyVersionKey);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mLastEnabledAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mDesiredHostingFrequencyKey = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mIsEnabled = source.createBooleanArray()[0];
        this.mMaxPrice = source.readInt();
        this.mMinPrice = source.readInt();
        this.mSuggestedMinPrice = source.readInt();
        this.mSuggestedMaxPrice = source.readInt();
        this.mHostingFrequencyVersionKey = source.readInt();
        this.mListingId = source.readLong();
    }
}
