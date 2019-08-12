package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCohostingStandard implements Parcelable {
    @JsonProperty("decline_num_current")
    protected long mDeclineNumCurrent;
    @JsonProperty("decline_num_max")
    protected long mDeclineNumMax;
    @JsonProperty("decline_num_bar")
    protected long mDeclineNumThreshold;
    @JsonProperty("inquiry_num_total")
    protected long mInquiryNumTotal;
    @JsonProperty("last_request_declined")
    protected Boolean mLastRequestDeclined;
    @JsonProperty("last_updated")
    protected AirDateTime mLastUpdated;
    @JsonProperty("overall_cohost_rating_current")
    protected long mOverallCohostRatingCurrent;
    @JsonProperty("overall_cohost_rating_max")
    protected long mOverallCohostRatingMax;
    @JsonProperty("overall_cohost_rating_bar")
    protected long mOverallCohostRatingThreshold;
    @JsonProperty("response_num_current")
    protected long mResponseNumCurrent;
    @JsonProperty("response_rate_current")
    protected long mResponseRateCurrent;
    @JsonProperty("response_rate_max")
    protected long mResponseRateMax;
    @JsonProperty("response_rate_bar")
    protected long mResponseRateThreshold;

    protected GenCohostingStandard(AirDateTime lastUpdated, Boolean lastRequestDeclined, long inquiryNumTotal, long responseNumCurrent, long responseRateThreshold, long responseRateCurrent, long responseRateMax, long declineNumThreshold, long declineNumCurrent, long declineNumMax, long overallCohostRatingThreshold, long overallCohostRatingCurrent, long overallCohostRatingMax) {
        this();
        this.mLastUpdated = lastUpdated;
        this.mLastRequestDeclined = lastRequestDeclined;
        this.mInquiryNumTotal = inquiryNumTotal;
        this.mResponseNumCurrent = responseNumCurrent;
        this.mResponseRateThreshold = responseRateThreshold;
        this.mResponseRateCurrent = responseRateCurrent;
        this.mResponseRateMax = responseRateMax;
        this.mDeclineNumThreshold = declineNumThreshold;
        this.mDeclineNumCurrent = declineNumCurrent;
        this.mDeclineNumMax = declineNumMax;
        this.mOverallCohostRatingThreshold = overallCohostRatingThreshold;
        this.mOverallCohostRatingCurrent = overallCohostRatingCurrent;
        this.mOverallCohostRatingMax = overallCohostRatingMax;
    }

    protected GenCohostingStandard() {
    }

    public AirDateTime getLastUpdated() {
        return this.mLastUpdated;
    }

    @JsonProperty("last_updated")
    public void setLastUpdated(AirDateTime value) {
        this.mLastUpdated = value;
    }

    public Boolean isLastRequestDeclined() {
        return this.mLastRequestDeclined;
    }

    @JsonProperty("last_request_declined")
    public void setLastRequestDeclined(Boolean value) {
        this.mLastRequestDeclined = value;
    }

    public long getInquiryNumTotal() {
        return this.mInquiryNumTotal;
    }

    @JsonProperty("inquiry_num_total")
    public void setInquiryNumTotal(long value) {
        this.mInquiryNumTotal = value;
    }

    public long getResponseNumCurrent() {
        return this.mResponseNumCurrent;
    }

    @JsonProperty("response_num_current")
    public void setResponseNumCurrent(long value) {
        this.mResponseNumCurrent = value;
    }

    public long getResponseRateThreshold() {
        return this.mResponseRateThreshold;
    }

    @JsonProperty("response_rate_bar")
    public void setResponseRateThreshold(long value) {
        this.mResponseRateThreshold = value;
    }

    public long getResponseRateCurrent() {
        return this.mResponseRateCurrent;
    }

    @JsonProperty("response_rate_current")
    public void setResponseRateCurrent(long value) {
        this.mResponseRateCurrent = value;
    }

    public long getResponseRateMax() {
        return this.mResponseRateMax;
    }

    @JsonProperty("response_rate_max")
    public void setResponseRateMax(long value) {
        this.mResponseRateMax = value;
    }

    public long getDeclineNumThreshold() {
        return this.mDeclineNumThreshold;
    }

    @JsonProperty("decline_num_bar")
    public void setDeclineNumThreshold(long value) {
        this.mDeclineNumThreshold = value;
    }

    public long getDeclineNumCurrent() {
        return this.mDeclineNumCurrent;
    }

    @JsonProperty("decline_num_current")
    public void setDeclineNumCurrent(long value) {
        this.mDeclineNumCurrent = value;
    }

    public long getDeclineNumMax() {
        return this.mDeclineNumMax;
    }

    @JsonProperty("decline_num_max")
    public void setDeclineNumMax(long value) {
        this.mDeclineNumMax = value;
    }

    public long getOverallCohostRatingThreshold() {
        return this.mOverallCohostRatingThreshold;
    }

    @JsonProperty("overall_cohost_rating_bar")
    public void setOverallCohostRatingThreshold(long value) {
        this.mOverallCohostRatingThreshold = value;
    }

    public long getOverallCohostRatingCurrent() {
        return this.mOverallCohostRatingCurrent;
    }

    @JsonProperty("overall_cohost_rating_current")
    public void setOverallCohostRatingCurrent(long value) {
        this.mOverallCohostRatingCurrent = value;
    }

    public long getOverallCohostRatingMax() {
        return this.mOverallCohostRatingMax;
    }

    @JsonProperty("overall_cohost_rating_max")
    public void setOverallCohostRatingMax(long value) {
        this.mOverallCohostRatingMax = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mLastUpdated, 0);
        parcel.writeValue(this.mLastRequestDeclined);
        parcel.writeLong(this.mInquiryNumTotal);
        parcel.writeLong(this.mResponseNumCurrent);
        parcel.writeLong(this.mResponseRateThreshold);
        parcel.writeLong(this.mResponseRateCurrent);
        parcel.writeLong(this.mResponseRateMax);
        parcel.writeLong(this.mDeclineNumThreshold);
        parcel.writeLong(this.mDeclineNumCurrent);
        parcel.writeLong(this.mDeclineNumMax);
        parcel.writeLong(this.mOverallCohostRatingThreshold);
        parcel.writeLong(this.mOverallCohostRatingCurrent);
        parcel.writeLong(this.mOverallCohostRatingMax);
    }

    public void readFromParcel(Parcel source) {
        this.mLastUpdated = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mLastRequestDeclined = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mInquiryNumTotal = source.readLong();
        this.mResponseNumCurrent = source.readLong();
        this.mResponseRateThreshold = source.readLong();
        this.mResponseRateCurrent = source.readLong();
        this.mResponseRateMax = source.readLong();
        this.mDeclineNumThreshold = source.readLong();
        this.mDeclineNumCurrent = source.readLong();
        this.mDeclineNumMax = source.readLong();
        this.mOverallCohostRatingThreshold = source.readLong();
        this.mOverallCohostRatingCurrent = source.readLong();
        this.mOverallCohostRatingMax = source.readLong();
    }
}
