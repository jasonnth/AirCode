package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.PartialListing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReviewRole;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.UserFlag;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReview implements Parcelable {
    @JsonProperty("accuracy")
    protected Integer mAccuracyRating;
    @JsonProperty("author")
    protected User mAuthor;
    @JsonProperty("author_id")
    protected long mAuthorId;
    @JsonProperty("rating")
    protected Integer mAverageRating;
    @JsonProperty("checkin")
    protected Integer mCheckinRating;
    @JsonProperty("cleanliness")
    protected Integer mCleanlinessRating;
    @JsonProperty("communication")
    protected Integer mCommunicationRating;
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("has_reviewer_submitted_a_previous_review")
    protected boolean mHasReviewerSubmittedAPreviousReview;
    @JsonProperty("has_translation")
    protected boolean mHasTranslation;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("language")
    protected String mLanguage;
    @JsonProperty("location")
    protected Integer mLocationRating;
    @JsonProperty("listing")
    protected PartialListing mPartialListing;
    @JsonProperty("private_feedback")
    protected String mPrivateFeedback;
    @JsonProperty("private_feedback_two")
    protected String mPrivateFeedbackTwo;
    @JsonProperty("comments")
    protected String mPublicFeedback;
    @JsonProperty("recipient")
    protected User mRecipient;
    @JsonProperty("recipient_id")
    protected long mRecipientId;
    @JsonProperty("recommend")
    protected Boolean mRecommended;
    @JsonProperty("reservation")
    protected Reservation mReservation;
    @JsonProperty("respect_house_rules")
    protected Integer mRespectHouseRulesRating;
    @JsonProperty("response")
    protected String mResponse;
    @JsonProperty("role")
    protected ReviewRole mReviewRole;
    @JsonProperty("has_submitted")
    protected boolean mSubmitted;
    @JsonProperty("translation")
    protected String mTranslation;
    @JsonProperty("twin_completed")
    protected boolean mTwinCompleted;
    @JsonProperty("user_flag")
    protected UserFlag mUserFlag;
    @JsonProperty("value")
    protected Integer mValueRating;

    protected GenReview(AirDateTime createdAt, Boolean recommended, Integer averageRating, Integer accuracyRating, Integer checkinRating, Integer locationRating, Integer communicationRating, Integer cleanlinessRating, Integer respectHouseRulesRating, Integer valueRating, PartialListing partialListing, Reservation reservation, ReviewRole reviewRole, String publicFeedback, String privateFeedback, String privateFeedbackTwo, String response, String translation, String language, User author, User recipient, UserFlag userFlag, boolean submitted, boolean twinCompleted, boolean hasReviewerSubmittedAPreviousReview, boolean hasTranslation, long id, long authorId, long recipientId) {
        this();
        this.mCreatedAt = createdAt;
        this.mRecommended = recommended;
        this.mAverageRating = averageRating;
        this.mAccuracyRating = accuracyRating;
        this.mCheckinRating = checkinRating;
        this.mLocationRating = locationRating;
        this.mCommunicationRating = communicationRating;
        this.mCleanlinessRating = cleanlinessRating;
        this.mRespectHouseRulesRating = respectHouseRulesRating;
        this.mValueRating = valueRating;
        this.mPartialListing = partialListing;
        this.mReservation = reservation;
        this.mReviewRole = reviewRole;
        this.mPublicFeedback = publicFeedback;
        this.mPrivateFeedback = privateFeedback;
        this.mPrivateFeedbackTwo = privateFeedbackTwo;
        this.mResponse = response;
        this.mTranslation = translation;
        this.mLanguage = language;
        this.mAuthor = author;
        this.mRecipient = recipient;
        this.mUserFlag = userFlag;
        this.mSubmitted = submitted;
        this.mTwinCompleted = twinCompleted;
        this.mHasReviewerSubmittedAPreviousReview = hasReviewerSubmittedAPreviousReview;
        this.mHasTranslation = hasTranslation;
        this.mId = id;
        this.mAuthorId = authorId;
        this.mRecipientId = recipientId;
    }

    protected GenReview() {
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public Boolean isRecommended() {
        return this.mRecommended;
    }

    @JsonProperty("recommend")
    public void setRecommended(Boolean value) {
        this.mRecommended = value;
    }

    public Integer getAverageRating() {
        return this.mAverageRating;
    }

    @JsonProperty("rating")
    public void setAverageRating(Integer value) {
        this.mAverageRating = value;
    }

    public Integer getAccuracyRating() {
        return this.mAccuracyRating;
    }

    @JsonProperty("accuracy")
    public void setAccuracyRating(Integer value) {
        this.mAccuracyRating = value;
    }

    public Integer getCheckinRating() {
        return this.mCheckinRating;
    }

    @JsonProperty("checkin")
    public void setCheckinRating(Integer value) {
        this.mCheckinRating = value;
    }

    public Integer getLocationRating() {
        return this.mLocationRating;
    }

    @JsonProperty("location")
    public void setLocationRating(Integer value) {
        this.mLocationRating = value;
    }

    public Integer getCommunicationRating() {
        return this.mCommunicationRating;
    }

    @JsonProperty("communication")
    public void setCommunicationRating(Integer value) {
        this.mCommunicationRating = value;
    }

    public Integer getCleanlinessRating() {
        return this.mCleanlinessRating;
    }

    @JsonProperty("cleanliness")
    public void setCleanlinessRating(Integer value) {
        this.mCleanlinessRating = value;
    }

    public Integer getRespectHouseRulesRating() {
        return this.mRespectHouseRulesRating;
    }

    @JsonProperty("respect_house_rules")
    public void setRespectHouseRulesRating(Integer value) {
        this.mRespectHouseRulesRating = value;
    }

    public Integer getValueRating() {
        return this.mValueRating;
    }

    @JsonProperty("value")
    public void setValueRating(Integer value) {
        this.mValueRating = value;
    }

    public PartialListing getPartialListing() {
        return this.mPartialListing;
    }

    public Reservation getReservation() {
        return this.mReservation;
    }

    public ReviewRole getReviewRole() {
        return this.mReviewRole;
    }

    public String getPublicFeedback() {
        return this.mPublicFeedback;
    }

    @JsonProperty("comments")
    public void setPublicFeedback(String value) {
        this.mPublicFeedback = value;
    }

    public String getPrivateFeedback() {
        return this.mPrivateFeedback;
    }

    @JsonProperty("private_feedback")
    public void setPrivateFeedback(String value) {
        this.mPrivateFeedback = value;
    }

    public String getPrivateFeedbackTwo() {
        return this.mPrivateFeedbackTwo;
    }

    public String getResponse() {
        return this.mResponse;
    }

    @JsonProperty("response")
    public void setResponse(String value) {
        this.mResponse = value;
    }

    public String getTranslation() {
        return this.mTranslation;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    @JsonProperty("language")
    public void setLanguage(String value) {
        this.mLanguage = value;
    }

    public User getAuthor() {
        return this.mAuthor;
    }

    @JsonProperty("author")
    public void setAuthor(User value) {
        this.mAuthor = value;
    }

    public User getRecipient() {
        return this.mRecipient;
    }

    @JsonProperty("recipient")
    public void setRecipient(User value) {
        this.mRecipient = value;
    }

    public UserFlag getUserFlag() {
        return this.mUserFlag;
    }

    @JsonProperty("user_flag")
    public void setUserFlag(UserFlag value) {
        this.mUserFlag = value;
    }

    public boolean isSubmitted() {
        return this.mSubmitted;
    }

    @JsonProperty("has_submitted")
    public void setSubmitted(boolean value) {
        this.mSubmitted = value;
    }

    public boolean isTwinCompleted() {
        return this.mTwinCompleted;
    }

    @JsonProperty("twin_completed")
    public void setTwinCompleted(boolean value) {
        this.mTwinCompleted = value;
    }

    public boolean hasReviewerSubmittedAPreviousReview() {
        return this.mHasReviewerSubmittedAPreviousReview;
    }

    @JsonProperty("has_reviewer_submitted_a_previous_review")
    public void setHasReviewerSubmittedAPreviousReview(boolean value) {
        this.mHasReviewerSubmittedAPreviousReview = value;
    }

    public boolean hasTranslation() {
        return this.mHasTranslation;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getAuthorId() {
        return this.mAuthorId;
    }

    @JsonProperty("author_id")
    public void setAuthorId(long value) {
        this.mAuthorId = value;
    }

    public long getRecipientId() {
        return this.mRecipientId;
    }

    @JsonProperty("recipient_id")
    public void setRecipientId(long value) {
        this.mRecipientId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeValue(this.mRecommended);
        parcel.writeValue(this.mAverageRating);
        parcel.writeValue(this.mAccuracyRating);
        parcel.writeValue(this.mCheckinRating);
        parcel.writeValue(this.mLocationRating);
        parcel.writeValue(this.mCommunicationRating);
        parcel.writeValue(this.mCleanlinessRating);
        parcel.writeValue(this.mRespectHouseRulesRating);
        parcel.writeValue(this.mValueRating);
        parcel.writeParcelable(this.mPartialListing, 0);
        parcel.writeParcelable(this.mReservation, 0);
        parcel.writeParcelable(this.mReviewRole, 0);
        parcel.writeString(this.mPublicFeedback);
        parcel.writeString(this.mPrivateFeedback);
        parcel.writeString(this.mPrivateFeedbackTwo);
        parcel.writeString(this.mResponse);
        parcel.writeString(this.mTranslation);
        parcel.writeString(this.mLanguage);
        parcel.writeParcelable(this.mAuthor, 0);
        parcel.writeParcelable(this.mRecipient, 0);
        parcel.writeParcelable(this.mUserFlag, 0);
        parcel.writeBooleanArray(new boolean[]{this.mSubmitted, this.mTwinCompleted, this.mHasReviewerSubmittedAPreviousReview, this.mHasTranslation});
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mAuthorId);
        parcel.writeLong(this.mRecipientId);
    }

    public void readFromParcel(Parcel source) {
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mRecommended = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mAverageRating = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mAccuracyRating = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mCheckinRating = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mLocationRating = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mCommunicationRating = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mCleanlinessRating = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mRespectHouseRulesRating = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mValueRating = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mPartialListing = (PartialListing) source.readParcelable(PartialListing.class.getClassLoader());
        this.mReservation = (Reservation) source.readParcelable(Reservation.class.getClassLoader());
        this.mReviewRole = (ReviewRole) source.readParcelable(ReviewRole.class.getClassLoader());
        this.mPublicFeedback = source.readString();
        this.mPrivateFeedback = source.readString();
        this.mPrivateFeedbackTwo = source.readString();
        this.mResponse = source.readString();
        this.mTranslation = source.readString();
        this.mLanguage = source.readString();
        this.mAuthor = (User) source.readParcelable(User.class.getClassLoader());
        this.mRecipient = (User) source.readParcelable(User.class.getClassLoader());
        this.mUserFlag = (UserFlag) source.readParcelable(UserFlag.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mSubmitted = bools[0];
        this.mTwinCompleted = bools[1];
        this.mHasReviewerSubmittedAPreviousReview = bools[2];
        this.mHasTranslation = bools[3];
        this.mId = source.readLong();
        this.mAuthorId = source.readLong();
        this.mRecipientId = source.readLong();
    }
}
