package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHostStandardMetricsWarningThresholds implements Parcelable {
    @JsonProperty("average_overall_rating")
    protected float mAverageOverallRating;
    @JsonProperty("commitment_rate")
    protected float mCommitmentRate;
    @JsonProperty("response_rate")
    protected float mResponseRate;

    protected GenHostStandardMetricsWarningThresholds(float averageOverallRating, float commitmentRate, float responseRate) {
        this();
        this.mAverageOverallRating = averageOverallRating;
        this.mCommitmentRate = commitmentRate;
        this.mResponseRate = responseRate;
    }

    protected GenHostStandardMetricsWarningThresholds() {
    }

    public float getAverageOverallRating() {
        return this.mAverageOverallRating;
    }

    @JsonProperty("average_overall_rating")
    public void setAverageOverallRating(float value) {
        this.mAverageOverallRating = value;
    }

    public float getCommitmentRate() {
        return this.mCommitmentRate;
    }

    @JsonProperty("commitment_rate")
    public void setCommitmentRate(float value) {
        this.mCommitmentRate = value;
    }

    public float getResponseRate() {
        return this.mResponseRate;
    }

    @JsonProperty("response_rate")
    public void setResponseRate(float value) {
        this.mResponseRate = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeFloat(this.mAverageOverallRating);
        parcel.writeFloat(this.mCommitmentRate);
        parcel.writeFloat(this.mResponseRate);
    }

    public void readFromParcel(Parcel source) {
        this.mAverageOverallRating = source.readFloat();
        this.mCommitmentRate = source.readFloat();
        this.mResponseRate = source.readFloat();
    }
}
