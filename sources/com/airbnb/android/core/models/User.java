package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.deserializers.WrappedDeserializer;
import com.airbnb.android.core.deserializers.WrappedListDeserializer;
import com.airbnb.android.core.deserializers.WrappedObject;
import com.airbnb.android.core.models.generated.GenUser;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class User extends GenUser {
    public static final String COUNTRY_US = "US";
    public static final Creator<User> CREATOR = new Creator<User>() {
        public User[] newArray(int size) {
            return new User[size];
        }

        public User createFromParcel(Parcel source) {
            User object = new User();
            object.readFromParcel(source);
            return object;
        }
    };

    @WrappedObject("review")
    @JsonProperty("recent_review")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setReview(Review review) {
        this.mRecentReview = review;
    }

    @WrappedObject("recommendation")
    @JsonProperty("recent_recommendation")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setRecommendation(Recommendation recommendation) {
        this.mRecentRecommendation = recommendation;
    }

    public List<ProfilePhoneNumber> getPhoneNumbers() {
        if ((this.mPhoneNumbers == null || this.mPhoneNumbers.isEmpty()) && !TextUtils.isEmpty(this.mPhone)) {
            return ImmutableList.m1285of(new ProfilePhoneNumber(null, this.mPhone, this.mPhone, true, 0));
        }
        return this.mPhoneNumbers;
    }

    @JsonProperty("phone_numbers")
    @WrappedObject("phone_number")
    @JsonDeserialize(contentUsing = WrappedListDeserializer.class)
    public void setPhoneNumbers(List<ProfilePhoneNumber> profilePhoneNumbers) {
        this.mPhoneNumbers = profilePhoneNumbers;
    }

    public boolean hasVerifiedPhone() {
        if (getPhoneNumbers() == null) {
            return false;
        }
        return FluentIterable.from((Iterable<E>) getPhoneNumbers()).anyMatch(User$$Lambda$1.lambdaFactory$());
    }

    public void setRecentReview(Review review) {
        this.mRecentReview = review;
    }

    public void setBirthdate(AirDate date) {
        this.mBirthdate = date;
    }

    public boolean hasProfilePic() {
        return super.hasProfilePic() || (!TextUtils.isEmpty(this.mPictureUrl) && !this.mPictureUrl.contains("defaults/user_pic"));
    }

    public CharSequence getHiddenProfileName() {
        if (TextUtils.isEmpty(this.mFirstName)) {
            return "";
        }
        return this.mFirstName.substring(0, 1);
    }

    public String getPictureUrlForThumbnail() {
        return this.mPictureUrl;
    }

    public String getName() {
        if (TextUtils.isEmpty(this.mFirstName) && TextUtils.isEmpty(this.mLastName)) {
            return super.getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mFirstName);
        if (!TextUtils.isEmpty(this.mLastName)) {
            sb.append(' ').append(this.mLastName);
            if (this.mLastName.length() == 1) {
                sb.append('.');
            }
        }
        return sb.toString();
    }

    public void setCreatedAt(Long time) {
        setCreatedAt(new AirDateTime(time.longValue()));
    }

    public boolean hasPhoneNumber() {
        return !TextUtils.isEmpty(this.mPhone);
    }

    public boolean hasEmailAddress() {
        return !TextUtils.isEmpty(this.mEmailAddress);
    }

    public boolean isCountryOfResidenceUS() {
        return "US".equals(this.mCountryOfResidence);
    }

    public boolean isDeterminedCountryUS() {
        return "US".equals(this.mDeterminedCountry);
    }

    public String getDefaultCountryOfResidence() {
        return this.mCountryOfResidence != null ? this.mCountryOfResidence : Locale.getDefault().getCountry();
    }

    public boolean hasBirthdate() {
        return this.mBirthdate != null;
    }

    public boolean isHost() {
        return getListingsCount() > 0;
    }

    public boolean isPhoneNumberRegisteredUser() {
        return this.mSignupMethod == 5;
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
        if (!(obj instanceof User)) {
            return false;
        }
        if (this.mId != ((User) obj).mId) {
            return false;
        }
        return true;
    }

    public boolean hasVerifications() {
        return (getVerifications() == null || getVerificationLabels() == null || getVerificationLabels().size() != getVerifications().size()) ? false : true;
    }

    public boolean hasProvidedGovernmentID() {
        return !ListUtils.isEmpty((Collection<?>) getVerifications()) && (getVerifications().contains("jumio") || getVerifications().contains(AccountVerification.SCANID));
    }

    public String toString() {
        return getName() + " " + getId();
    }
}
