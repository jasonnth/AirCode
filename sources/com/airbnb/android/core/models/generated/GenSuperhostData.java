package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.InquiriesRepliesCounts;
import com.airbnb.android.core.models.SuperhostMetric;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSuperhostData implements Parcelable {
    @JsonProperty("commitment_rate_data")
    protected SuperhostMetric mCommitmentRateData;
    @JsonProperty("current_assessment_window_end_date")
    protected AirDate mCurrentAssessmentWindowEndDate;
    @JsonProperty("current_assessment_window_start_date")
    protected AirDate mCurrentAssessmentWindowStartDate;
    @JsonProperty("is_enabled_for_superhost")
    protected boolean mEnabled;
    @JsonProperty("five_star_review_rate_data")
    protected SuperhostMetric mFiveStarReviewRateData;
    @JsonProperty("in_eligibility_assessment_window")
    protected boolean mInEligibilityAssessmentWindow;
    @JsonProperty("yearly_replies_and_responses_count")
    protected InquiriesRepliesCounts mInquiriesRepliesCounts;
    @JsonProperty("meeting_requirements")
    protected boolean mMeetingRequirements;
    @JsonProperty("next_eligibility_date")
    protected AirDate mNextEligibilityDate;
    @JsonProperty("review_rate_data")
    protected SuperhostMetric mReviewRateData;
    @JsonProperty("superhost")
    protected boolean mSuperhost;
    @JsonProperty("trips_as_host_last_year_data")
    protected SuperhostMetric mTripsAsHostLastYearData;
    @JsonProperty("yearly_response_rate_data")
    protected SuperhostMetric mYearlyResponseRateData;

    protected GenSuperhostData(AirDate nextEligibilityDate, AirDate currentAssessmentWindowStartDate, AirDate currentAssessmentWindowEndDate, InquiriesRepliesCounts inquiriesRepliesCounts, SuperhostMetric fiveStarReviewRateData, SuperhostMetric commitmentRateData, SuperhostMetric yearlyResponseRateData, SuperhostMetric tripsAsHostLastYearData, SuperhostMetric reviewRateData, boolean superhost, boolean enabled, boolean meetingRequirements, boolean inEligibilityAssessmentWindow) {
        this();
        this.mNextEligibilityDate = nextEligibilityDate;
        this.mCurrentAssessmentWindowStartDate = currentAssessmentWindowStartDate;
        this.mCurrentAssessmentWindowEndDate = currentAssessmentWindowEndDate;
        this.mInquiriesRepliesCounts = inquiriesRepliesCounts;
        this.mFiveStarReviewRateData = fiveStarReviewRateData;
        this.mCommitmentRateData = commitmentRateData;
        this.mYearlyResponseRateData = yearlyResponseRateData;
        this.mTripsAsHostLastYearData = tripsAsHostLastYearData;
        this.mReviewRateData = reviewRateData;
        this.mSuperhost = superhost;
        this.mEnabled = enabled;
        this.mMeetingRequirements = meetingRequirements;
        this.mInEligibilityAssessmentWindow = inEligibilityAssessmentWindow;
    }

    protected GenSuperhostData() {
    }

    public AirDate getNextEligibilityDate() {
        return this.mNextEligibilityDate;
    }

    public AirDate getCurrentAssessmentWindowStartDate() {
        return this.mCurrentAssessmentWindowStartDate;
    }

    @JsonProperty("current_assessment_window_start_date")
    public void setCurrentAssessmentWindowStartDate(AirDate value) {
        this.mCurrentAssessmentWindowStartDate = value;
    }

    public AirDate getCurrentAssessmentWindowEndDate() {
        return this.mCurrentAssessmentWindowEndDate;
    }

    @JsonProperty("current_assessment_window_end_date")
    public void setCurrentAssessmentWindowEndDate(AirDate value) {
        this.mCurrentAssessmentWindowEndDate = value;
    }

    public InquiriesRepliesCounts getInquiriesRepliesCounts() {
        return this.mInquiriesRepliesCounts;
    }

    public SuperhostMetric getFiveStarReviewRateData() {
        return this.mFiveStarReviewRateData;
    }

    public SuperhostMetric getCommitmentRateData() {
        return this.mCommitmentRateData;
    }

    public SuperhostMetric getYearlyResponseRateData() {
        return this.mYearlyResponseRateData;
    }

    public SuperhostMetric getTripsAsHostLastYearData() {
        return this.mTripsAsHostLastYearData;
    }

    public SuperhostMetric getReviewRateData() {
        return this.mReviewRateData;
    }

    public boolean isSuperhost() {
        return this.mSuperhost;
    }

    @JsonProperty("superhost")
    public void setSuperhost(boolean value) {
        this.mSuperhost = value;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    @JsonProperty("is_enabled_for_superhost")
    public void setEnabled(boolean value) {
        this.mEnabled = value;
    }

    public boolean isMeetingRequirements() {
        return this.mMeetingRequirements;
    }

    @JsonProperty("meeting_requirements")
    public void setMeetingRequirements(boolean value) {
        this.mMeetingRequirements = value;
    }

    public boolean isInEligibilityAssessmentWindow() {
        return this.mInEligibilityAssessmentWindow;
    }

    @JsonProperty("in_eligibility_assessment_window")
    public void setInEligibilityAssessmentWindow(boolean value) {
        this.mInEligibilityAssessmentWindow = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mNextEligibilityDate, 0);
        parcel.writeParcelable(this.mCurrentAssessmentWindowStartDate, 0);
        parcel.writeParcelable(this.mCurrentAssessmentWindowEndDate, 0);
        parcel.writeParcelable(this.mInquiriesRepliesCounts, 0);
        parcel.writeParcelable(this.mFiveStarReviewRateData, 0);
        parcel.writeParcelable(this.mCommitmentRateData, 0);
        parcel.writeParcelable(this.mYearlyResponseRateData, 0);
        parcel.writeParcelable(this.mTripsAsHostLastYearData, 0);
        parcel.writeParcelable(this.mReviewRateData, 0);
        parcel.writeBooleanArray(new boolean[]{this.mSuperhost, this.mEnabled, this.mMeetingRequirements, this.mInEligibilityAssessmentWindow});
    }

    public void readFromParcel(Parcel source) {
        this.mNextEligibilityDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCurrentAssessmentWindowStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCurrentAssessmentWindowEndDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mInquiriesRepliesCounts = (InquiriesRepliesCounts) source.readParcelable(InquiriesRepliesCounts.class.getClassLoader());
        this.mFiveStarReviewRateData = (SuperhostMetric) source.readParcelable(SuperhostMetric.class.getClassLoader());
        this.mCommitmentRateData = (SuperhostMetric) source.readParcelable(SuperhostMetric.class.getClassLoader());
        this.mYearlyResponseRateData = (SuperhostMetric) source.readParcelable(SuperhostMetric.class.getClassLoader());
        this.mTripsAsHostLastYearData = (SuperhostMetric) source.readParcelable(SuperhostMetric.class.getClassLoader());
        this.mReviewRateData = (SuperhostMetric) source.readParcelable(SuperhostMetric.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mSuperhost = bools[0];
        this.mEnabled = bools[1];
        this.mMeetingRequirements = bools[2];
        this.mInEligibilityAssessmentWindow = bools[3];
    }
}
