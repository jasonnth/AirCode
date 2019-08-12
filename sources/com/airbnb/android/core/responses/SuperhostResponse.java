package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SuperhostData;
import com.airbnb.android.core.models.SuperhostMetric;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SuperhostResponse extends BaseResponse {
    @JsonProperty("superhost")
    public ResponseWrapper mResponseWrapper;

    public static class ResponseWrapper extends SuperhostData {
        @JsonProperty("commitment_rate")
        public int mCommitmentRate;
        @JsonProperty("five_star_review_rate")
        public int mFiveStarReviewRate;
        @JsonProperty("is_enabled_for_superhost")
        public boolean mIsEnabled;
        @JsonProperty("review_rate")
        public int mReviewRate;
        @JsonProperty("thresholds")
        public Thresholds mThresholds;
        @JsonProperty("trips_as_host_last_year")
        public int mTripsAsHostLastYear;
        @JsonProperty("yearly_response_rate")
        public int mYearlyResponseRate;

        public SuperhostData getSuperhostData() {
            setInquiriesRepliesCount(this.mInquiriesRepliesCounts);
            setTripsAsHostLastYearData(SuperhostMetric.create(this.mTripsAsHostLastYear, this.mThresholds.mTripsAsHostLastYear));
            setFiveStarReviewRateData(SuperhostMetric.create(this.mFiveStarReviewRate, this.mThresholds.mFiveStarReviewRate));
            setCommitmentRateData(SuperhostMetric.create(this.mCommitmentRate, this.mThresholds.mCommitmentRate));
            setYearlyResponseRateData(SuperhostMetric.create(this.mYearlyResponseRate, this.mThresholds.mYearlyResponseRate));
            setReviewRateData(SuperhostMetric.create(this.mReviewRate, this.mThresholds.mReviewRate));
            return this;
        }
    }

    public static class Thresholds {
        @JsonProperty("commitment_rate")
        public int mCommitmentRate;
        @JsonProperty("five_star_review_rate")
        public int mFiveStarReviewRate;
        @JsonProperty("review_rate")
        public int mReviewRate;
        @JsonProperty("trips_as_host_last_year")
        public int mTripsAsHostLastYear;
        @JsonProperty("yearly_response_rate")
        public int mYearlyResponseRate;
    }

    public SuperhostData getSuperhostData() {
        if (this.mResponseWrapper == null) {
            return null;
        }
        return this.mResponseWrapper.getSuperhostData();
    }
}
