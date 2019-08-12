package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.HostStandardMetricsWarningThresholds;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHostStandardMetrics implements Parcelable {
    @JsonProperty("average_overall_rating_last_365")
    protected float mAverageOverallRating;
    @JsonProperty("average_overall_rating_standard_state")
    protected String mAverageOverallRatingStandardStateKey;
    @JsonProperty("average_overall_rating_standard_text")
    protected String mAverageOverallRatingStandardText;
    @JsonProperty("cancellations_last_365")
    protected int mCancellationsCount;
    @JsonProperty("commitment_rate_last_365")
    protected float mCommitmentRate;
    @JsonProperty("commitment_rate_standard_state")
    protected String mCommitmentRateStandardStateKey;
    @JsonProperty("commitment_rate_standard_text")
    protected String mCommitmentRateStandardText;
    @JsonProperty("inquiries")
    protected int mInquiriesCount;
    @JsonProperty("host_metrics_last_updated")
    protected AirDate mMetricsLastUpdated;
    @JsonProperty("replies_on_time")
    protected int mRepliesOnTime;
    @JsonProperty("reservations_last_365")
    protected int mReservationsCount;
    @JsonProperty("response_rate")
    protected float mResponseRate;
    @JsonProperty("response_rate_standard_state")
    protected String mResponseRateStandardStateKey;
    @JsonProperty("response_rate_standard_text")
    protected String mResponseRateStandardText;
    @JsonProperty("warning_thresholds")
    protected HostStandardMetricsWarningThresholds mWarningThresholds;

    protected GenHostStandardMetrics(AirDate metricsLastUpdated, HostStandardMetricsWarningThresholds warningThresholds, String responseRateStandardStateKey, String averageOverallRatingStandardStateKey, String commitmentRateStandardStateKey, String averageOverallRatingStandardText, String commitmentRateStandardText, String responseRateStandardText, float averageOverallRating, float commitmentRate, float responseRate, int reservationsCount, int cancellationsCount, int inquiriesCount, int repliesOnTime) {
        this();
        this.mMetricsLastUpdated = metricsLastUpdated;
        this.mWarningThresholds = warningThresholds;
        this.mResponseRateStandardStateKey = responseRateStandardStateKey;
        this.mAverageOverallRatingStandardStateKey = averageOverallRatingStandardStateKey;
        this.mCommitmentRateStandardStateKey = commitmentRateStandardStateKey;
        this.mAverageOverallRatingStandardText = averageOverallRatingStandardText;
        this.mCommitmentRateStandardText = commitmentRateStandardText;
        this.mResponseRateStandardText = responseRateStandardText;
        this.mAverageOverallRating = averageOverallRating;
        this.mCommitmentRate = commitmentRate;
        this.mResponseRate = responseRate;
        this.mReservationsCount = reservationsCount;
        this.mCancellationsCount = cancellationsCount;
        this.mInquiriesCount = inquiriesCount;
        this.mRepliesOnTime = repliesOnTime;
    }

    protected GenHostStandardMetrics() {
    }

    public AirDate getMetricsLastUpdated() {
        return this.mMetricsLastUpdated;
    }

    @JsonProperty("host_metrics_last_updated")
    public void setMetricsLastUpdated(AirDate value) {
        this.mMetricsLastUpdated = value;
    }

    public HostStandardMetricsWarningThresholds getWarningThresholds() {
        return this.mWarningThresholds;
    }

    @JsonProperty("warning_thresholds")
    public void setWarningThresholds(HostStandardMetricsWarningThresholds value) {
        this.mWarningThresholds = value;
    }

    public String getResponseRateStandardStateKey() {
        return this.mResponseRateStandardStateKey;
    }

    @JsonProperty("response_rate_standard_state")
    public void setResponseRateStandardStateKey(String value) {
        this.mResponseRateStandardStateKey = value;
    }

    public String getAverageOverallRatingStandardStateKey() {
        return this.mAverageOverallRatingStandardStateKey;
    }

    @JsonProperty("average_overall_rating_standard_state")
    public void setAverageOverallRatingStandardStateKey(String value) {
        this.mAverageOverallRatingStandardStateKey = value;
    }

    public String getCommitmentRateStandardStateKey() {
        return this.mCommitmentRateStandardStateKey;
    }

    @JsonProperty("commitment_rate_standard_state")
    public void setCommitmentRateStandardStateKey(String value) {
        this.mCommitmentRateStandardStateKey = value;
    }

    public String getAverageOverallRatingStandardText() {
        return this.mAverageOverallRatingStandardText;
    }

    @JsonProperty("average_overall_rating_standard_text")
    public void setAverageOverallRatingStandardText(String value) {
        this.mAverageOverallRatingStandardText = value;
    }

    public String getCommitmentRateStandardText() {
        return this.mCommitmentRateStandardText;
    }

    @JsonProperty("commitment_rate_standard_text")
    public void setCommitmentRateStandardText(String value) {
        this.mCommitmentRateStandardText = value;
    }

    public String getResponseRateStandardText() {
        return this.mResponseRateStandardText;
    }

    @JsonProperty("response_rate_standard_text")
    public void setResponseRateStandardText(String value) {
        this.mResponseRateStandardText = value;
    }

    public float getAverageOverallRating() {
        return this.mAverageOverallRating;
    }

    @JsonProperty("average_overall_rating_last_365")
    public void setAverageOverallRating(float value) {
        this.mAverageOverallRating = value;
    }

    public float getCommitmentRate() {
        return this.mCommitmentRate;
    }

    @JsonProperty("commitment_rate_last_365")
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

    public int getReservationsCount() {
        return this.mReservationsCount;
    }

    @JsonProperty("reservations_last_365")
    public void setReservationsCount(int value) {
        this.mReservationsCount = value;
    }

    public int getCancellationsCount() {
        return this.mCancellationsCount;
    }

    @JsonProperty("cancellations_last_365")
    public void setCancellationsCount(int value) {
        this.mCancellationsCount = value;
    }

    public int getInquiriesCount() {
        return this.mInquiriesCount;
    }

    @JsonProperty("inquiries")
    public void setInquiriesCount(int value) {
        this.mInquiriesCount = value;
    }

    public int getRepliesOnTime() {
        return this.mRepliesOnTime;
    }

    @JsonProperty("replies_on_time")
    public void setRepliesOnTime(int value) {
        this.mRepliesOnTime = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mMetricsLastUpdated, 0);
        parcel.writeParcelable(this.mWarningThresholds, 0);
        parcel.writeString(this.mResponseRateStandardStateKey);
        parcel.writeString(this.mAverageOverallRatingStandardStateKey);
        parcel.writeString(this.mCommitmentRateStandardStateKey);
        parcel.writeString(this.mAverageOverallRatingStandardText);
        parcel.writeString(this.mCommitmentRateStandardText);
        parcel.writeString(this.mResponseRateStandardText);
        parcel.writeFloat(this.mAverageOverallRating);
        parcel.writeFloat(this.mCommitmentRate);
        parcel.writeFloat(this.mResponseRate);
        parcel.writeInt(this.mReservationsCount);
        parcel.writeInt(this.mCancellationsCount);
        parcel.writeInt(this.mInquiriesCount);
        parcel.writeInt(this.mRepliesOnTime);
    }

    public void readFromParcel(Parcel source) {
        this.mMetricsLastUpdated = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mWarningThresholds = (HostStandardMetricsWarningThresholds) source.readParcelable(HostStandardMetricsWarningThresholds.class.getClassLoader());
        this.mResponseRateStandardStateKey = source.readString();
        this.mAverageOverallRatingStandardStateKey = source.readString();
        this.mCommitmentRateStandardStateKey = source.readString();
        this.mAverageOverallRatingStandardText = source.readString();
        this.mCommitmentRateStandardText = source.readString();
        this.mResponseRateStandardText = source.readString();
        this.mAverageOverallRating = source.readFloat();
        this.mCommitmentRate = source.readFloat();
        this.mResponseRate = source.readFloat();
        this.mReservationsCount = source.readInt();
        this.mCancellationsCount = source.readInt();
        this.mInquiriesCount = source.readInt();
        this.mRepliesOnTime = source.readInt();
    }
}
