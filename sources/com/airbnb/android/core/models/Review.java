package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.deserializers.WrappedDeserializer;
import com.airbnb.android.core.deserializers.WrappedObject;
import com.airbnb.android.core.models.generated.GenReview;
import com.airbnb.android.core.responses.TranslatedReview;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Review extends GenReview {
    public static final Creator<Review> CREATOR = new Creator<Review>() {
        public Review[] newArray(int size) {
            return new Review[size];
        }

        public Review createFromParcel(Parcel source) {
            Review object = new Review();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final int DAYS_TO_WRITE_REVIEW = 14;

    public enum RatingType implements Parcelable {
        Cleanliness,
        Communication,
        HouseRuleObservance,
        Accuracy,
        CheckIn,
        Location,
        Value,
        Recommend,
        Overall;
        
        public static final Creator<RatingType> CREATOR = null;

        static {
            CREATOR = new Creator<RatingType>() {
                public RatingType createFromParcel(Parcel source) {
                    return RatingType.values()[source.readInt()];
                }

                public RatingType[] newArray(int size) {
                    return new RatingType[size];
                }
            };
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
        }
    }

    public Integer getRatingValue(RatingType ratingType) {
        switch (ratingType) {
            case Overall:
                return getAverageRating();
            case Accuracy:
                return getAccuracyRating();
            case CheckIn:
                return getCheckinRating();
            case Cleanliness:
                return getCleanlinessRating();
            case Communication:
                return getCommunicationRating();
            case HouseRuleObservance:
                return getRespectHouseRulesRating();
            case Location:
                return getLocationRating();
            case Value:
                return getValueRating();
            default:
                return null;
        }
    }

    private void updateAverageRating() {
        float sum = 0.0f;
        int ratingsUsed = 0;
        for (RatingType ratingType : getRatingTypes()) {
            if (getRatingValue(ratingType) != null) {
                sum += (float) getRatingValue(ratingType).intValue();
                ratingsUsed++;
            }
        }
        setAverageRating(Integer.valueOf(Math.round(ratingsUsed == 0 ? 0.0f : sum / ((float) ratingsUsed))));
    }

    public void setRatingValue(RatingType ratingType, Integer value) {
        switch (ratingType) {
            case Overall:
                setAverageRating(value);
                break;
            case Accuracy:
                setAccuracyRating(value);
                break;
            case CheckIn:
                setCheckinRating(value);
                break;
            case Cleanliness:
                setCleanlinessRating(value);
                break;
            case Communication:
                setCommunicationRating(value);
                break;
            case HouseRuleObservance:
                setRespectHouseRulesRating(value);
                break;
            case Location:
                setLocationRating(value);
                break;
            case Value:
                setValueRating(value);
                break;
        }
        if (isOverallRatingComputed()) {
            updateAverageRating();
        }
    }

    public ArrayList<RatingType> getRatingTypes() {
        switch (this.mReviewRole) {
            case Guest:
                return new ArrayList<RatingType>() {
                    private static final long serialVersionUID = -7500019902718577017L;

                    {
                        add(RatingType.Overall);
                        add(RatingType.Accuracy);
                        add(RatingType.Cleanliness);
                        add(RatingType.CheckIn);
                        add(RatingType.Communication);
                        add(RatingType.Location);
                        add(RatingType.Value);
                        add(RatingType.Recommend);
                    }
                };
            case Host:
                return new ArrayList<RatingType>() {
                    private static final long serialVersionUID = -7396197613873568633L;

                    {
                        add(RatingType.Cleanliness);
                        add(RatingType.Communication);
                        add(RatingType.HouseRuleObservance);
                        add(RatingType.Recommend);
                    }
                };
            default:
                return null;
        }
    }

    public boolean isPending() {
        return !isSubmitted() && !AirDateTime.now().isAfter(getExpirationTime());
    }

    public AirDateTime getExpirationTime() {
        return getCreatedAt().plusDays(14);
    }

    public boolean isOverallRatingComputed() {
        return isHostReviewingGuest();
    }

    public boolean isGuestReviewingHost() {
        return this.mReviewRole == ReviewRole.Guest;
    }

    public boolean isHostReviewingGuest() {
        return this.mReviewRole == ReviewRole.Host;
    }

    public boolean shouldShowFlagged() {
        return this.mUserFlag != null && !this.mUserFlag.isRedacted();
    }

    public boolean hasListingInfo() {
        return (this.mPartialListing == null || this.mPartialListing.getName() == null || this.mPartialListing.getListingId() <= 0) ? false : true;
    }

    public String getListingName() {
        if (this.mPartialListing != null && !TextUtils.isEmpty(this.mPartialListing.getName())) {
            return this.mPartialListing.getName();
        }
        if (this.mReservation != null && this.mReservation.getListing() != null) {
            return this.mReservation.getListing().getName();
        }
        throw new IllegalStateException("no listing info associated with this review");
    }

    public long getListingId() {
        if (this.mPartialListing != null && this.mPartialListing.getListingId() > 0) {
            return this.mPartialListing.getListingId();
        }
        if (this.mReservation != null && this.mReservation.getListing() != null && this.mReservation.getListing().getId() > 0) {
            return this.mReservation.getListing().getId();
        }
        throw new IllegalStateException("no listing id associated with this review");
    }

    public void setPrivateFeedback(String privateFeedback) {
        this.mPrivateFeedback = privateFeedback;
    }

    public void setPrivateFeedbackTwo(String privateFeedbackTwo) {
        this.mPrivateFeedbackTwo = privateFeedbackTwo;
    }

    public String getCombinedPrivateFeedback() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(getPrivateFeedback())) {
            sb.append(getPrivateFeedback());
        }
        if (!TextUtils.isEmpty(getPrivateFeedbackTwo())) {
            if (!TextUtils.isEmpty(sb.toString())) {
                sb.append("\n\n");
            }
            sb.append(getPrivateFeedbackTwo());
        }
        return sb.toString();
    }

    @JsonProperty("reviewer_id")
    public void setReviewerId(long reviewerId) {
        setAuthorId(reviewerId);
    }

    @JsonProperty("reviewee_id")
    public void setRevieweeId(long revieweeId) {
        setRecipientId(revieweeId);
    }

    @WrappedObject("user")
    @JsonProperty("reviewer")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setReviewer(User user) {
        setAuthor(user);
    }

    @WrappedObject("user")
    @JsonProperty("reviewee")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setReviewee(User user) {
        setRecipient(user);
    }

    @JsonProperty("role")
    public void setReviewRole(String roleString) {
        this.mReviewRole = ReviewRole.findRole(roleString);
    }

    @WrappedObject("listing")
    @JsonProperty("listing")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setPartialListing(PartialListing partialListing) {
        this.mPartialListing = partialListing;
    }

    @WrappedObject("reservation")
    @JsonProperty("reservation")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setReservation(Reservation reservation) {
        this.mReservation = reservation;
    }

    public String getFormattedCreationDate(SimpleDateFormat dateFormat) {
        return (this.mCreatedAt == null || dateFormat == null) ? "" : this.mCreatedAt.formatDate(dateFormat);
    }

    public void setTranslation(TranslatedReview translatedReview) {
        if (translatedReview != null) {
            this.mHasTranslation = translatedReview.isTranslated;
            if (this.mHasTranslation) {
                this.mTranslation = translatedReview.translatedReview;
            }
        }
    }

    public int hashCode() {
        return ((int) (this.mId ^ (this.mId >>> 32))) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Review)) {
            return false;
        }
        if (this.mId != ((GenReview) obj).getId()) {
            return false;
        }
        return true;
    }
}
