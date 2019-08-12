package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingLongTermDiscountValues implements Parcelable {
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("monthly_discount_value_native")
    protected int mMonthlyDiscountValueNative;
    @JsonProperty("weekly_discount_value_native")
    protected int mWeeklyDiscountValueNative;

    protected GenListingLongTermDiscountValues(int monthlyDiscountValueNative, int weeklyDiscountValueNative, long listingId) {
        this();
        this.mMonthlyDiscountValueNative = monthlyDiscountValueNative;
        this.mWeeklyDiscountValueNative = weeklyDiscountValueNative;
        this.mListingId = listingId;
    }

    protected GenListingLongTermDiscountValues() {
    }

    public int getMonthlyDiscountValueNative() {
        return this.mMonthlyDiscountValueNative;
    }

    @JsonProperty("monthly_discount_value_native")
    public void setMonthlyDiscountValueNative(int value) {
        this.mMonthlyDiscountValueNative = value;
    }

    public int getWeeklyDiscountValueNative() {
        return this.mWeeklyDiscountValueNative;
    }

    @JsonProperty("weekly_discount_value_native")
    public void setWeeklyDiscountValueNative(int value) {
        this.mWeeklyDiscountValueNative = value;
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
        parcel.writeInt(this.mMonthlyDiscountValueNative);
        parcel.writeInt(this.mWeeklyDiscountValueNative);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mMonthlyDiscountValueNative = source.readInt();
        this.mWeeklyDiscountValueNative = source.readInt();
        this.mListingId = source.readLong();
    }
}
