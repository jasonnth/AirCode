package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAutoPricing implements Parcelable {
    @JsonProperty("cleaning")
    protected int mCleaning;
    @JsonProperty("daily")
    protected int mDaily;
    @JsonProperty("extra_earning_forecast")
    protected int mExtraEarningForecast;
    @JsonProperty("monthly")
    protected int mMonthly;
    @JsonProperty("monthly_discount")
    protected double mMonthlyDiscount;
    @JsonProperty("weekly")
    protected int mWeekly;
    @JsonProperty("weekly_discount")
    protected double mWeeklyDiscount;

    protected GenAutoPricing(double weeklyDiscount, double monthlyDiscount, int daily, int weekly, int monthly, int cleaning, int extraEarningForecast) {
        this();
        this.mWeeklyDiscount = weeklyDiscount;
        this.mMonthlyDiscount = monthlyDiscount;
        this.mDaily = daily;
        this.mWeekly = weekly;
        this.mMonthly = monthly;
        this.mCleaning = cleaning;
        this.mExtraEarningForecast = extraEarningForecast;
    }

    protected GenAutoPricing() {
    }

    public double getWeeklyDiscount() {
        return this.mWeeklyDiscount;
    }

    @JsonProperty("weekly_discount")
    public void setWeeklyDiscount(double value) {
        this.mWeeklyDiscount = value;
    }

    public double getMonthlyDiscount() {
        return this.mMonthlyDiscount;
    }

    @JsonProperty("monthly_discount")
    public void setMonthlyDiscount(double value) {
        this.mMonthlyDiscount = value;
    }

    public int getDaily() {
        return this.mDaily;
    }

    @JsonProperty("daily")
    public void setDaily(int value) {
        this.mDaily = value;
    }

    public int getWeekly() {
        return this.mWeekly;
    }

    @JsonProperty("weekly")
    public void setWeekly(int value) {
        this.mWeekly = value;
    }

    public int getMonthly() {
        return this.mMonthly;
    }

    @JsonProperty("monthly")
    public void setMonthly(int value) {
        this.mMonthly = value;
    }

    public int getCleaning() {
        return this.mCleaning;
    }

    @JsonProperty("cleaning")
    public void setCleaning(int value) {
        this.mCleaning = value;
    }

    public int getExtraEarningForecast() {
        return this.mExtraEarningForecast;
    }

    @JsonProperty("extra_earning_forecast")
    public void setExtraEarningForecast(int value) {
        this.mExtraEarningForecast = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeDouble(this.mWeeklyDiscount);
        parcel.writeDouble(this.mMonthlyDiscount);
        parcel.writeInt(this.mDaily);
        parcel.writeInt(this.mWeekly);
        parcel.writeInt(this.mMonthly);
        parcel.writeInt(this.mCleaning);
        parcel.writeInt(this.mExtraEarningForecast);
    }

    public void readFromParcel(Parcel source) {
        this.mWeeklyDiscount = source.readDouble();
        this.mMonthlyDiscount = source.readDouble();
        this.mDaily = source.readInt();
        this.mWeekly = source.readInt();
        this.mMonthly = source.readInt();
        this.mCleaning = source.readInt();
        this.mExtraEarningForecast = source.readInt();
    }
}
