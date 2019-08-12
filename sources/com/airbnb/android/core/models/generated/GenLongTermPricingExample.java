package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenLongTermPricingExample implements Parcelable {
    @JsonProperty("discount")
    protected double mDiscount;
    @JsonProperty("launch_date")
    protected AirDate mLaunchDate;
    @JsonProperty("samples")
    protected int[] mSamples;
    @JsonProperty("start_date")
    protected AirDate mStartDate;
    @JsonProperty("total")
    protected int mTotal;

    protected GenLongTermPricingExample(AirDate startDate, AirDate launchDate, double discount, int total, int[] samples) {
        this();
        this.mStartDate = startDate;
        this.mLaunchDate = launchDate;
        this.mDiscount = discount;
        this.mTotal = total;
        this.mSamples = samples;
    }

    protected GenLongTermPricingExample() {
    }

    public AirDate getStartDate() {
        return this.mStartDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(AirDate value) {
        this.mStartDate = value;
    }

    public AirDate getLaunchDate() {
        return this.mLaunchDate;
    }

    @JsonProperty("launch_date")
    public void setLaunchDate(AirDate value) {
        this.mLaunchDate = value;
    }

    public double getDiscount() {
        return this.mDiscount;
    }

    @JsonProperty("discount")
    public void setDiscount(double value) {
        this.mDiscount = value;
    }

    public int getTotal() {
        return this.mTotal;
    }

    @JsonProperty("total")
    public void setTotal(int value) {
        this.mTotal = value;
    }

    public int[] getSamples() {
        return this.mSamples;
    }

    @JsonProperty("samples")
    public void setSamples(int[] value) {
        this.mSamples = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mLaunchDate, 0);
        parcel.writeDouble(this.mDiscount);
        parcel.writeInt(this.mTotal);
        parcel.writeIntArray(this.mSamples);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mLaunchDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mDiscount = source.readDouble();
        this.mTotal = source.readInt();
        this.mSamples = source.createIntArray();
    }
}
