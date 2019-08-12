package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.ProfilePhoneNumber;
import com.airbnb.android.core.models.Recommendation;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.ReviewRatingsAsGuest;
import com.airbnb.android.core.models.UserFlag;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenUser implements Parcelable {
    @JsonProperty("about")
    protected String mAbout;
    @JsonProperty("acceptance_rate")
    protected String mAcceptanceRate;
    @JsonProperty("age")
    protected String mAge;
    @JsonProperty("birthdate")
    protected AirDate mBirthdate;
    @JsonProperty("bt_auto_enroll_company_name")
    protected String mBtAutoEnrollCompanyName;
    @JsonProperty("completed_account_verifications_for_booking")
    protected boolean mCompletedAccountVerificationsForBooking;
    @JsonProperty("content_framework_articles")
    protected List<Article> mContentFrameworkArticles;
    @JsonProperty("content_framework_articles_count")
    protected int mContentFrameworkArticlesCount;
    @JsonProperty("country")
    protected String mCountry;
    @JsonProperty("country_of_residence")
    protected String mCountryOfResidence;
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("default_payin_gibraltar_instrument_id")
    protected long mDefaultPayinGibraltarInstrumentId;
    @JsonProperty("default_payout_gibraltar_instrument_id")
    protected long mDefaultPayoutGibraltarInstrumentId;
    @JsonProperty("determined_country")
    protected String mDeterminedCountry;
    @JsonProperty("eligible_for_nested_listings")
    protected boolean mEligibleForNestedListings;
    @JsonProperty("email")
    protected String mEmailAddress;
    @JsonProperty("first_name")
    protected String mFirstName;
    @JsonProperty("force_use_identity_flow_for_verified_id")
    protected boolean mForceIdentityFlow;
    @JsonProperty("full_name")
    protected String mFullName;
    @JsonProperty("gender")
    protected String mGender;
    @JsonProperty("government_id_dob")
    protected AirDate mGovernmentIdDob;
    @JsonProperty("groups")
    protected String mGroups;
    @JsonProperty("hard_suspended")
    protected boolean mHardSuspended;
    @JsonProperty("completed_account_verifications_for_profile_completion")
    protected boolean mHasCompletedAccountVerificationsForProfileCompletion;
    @JsonProperty("has_past_bookings")
    protected boolean mHasPastBookings;
    @JsonProperty("has_available_payout_info")
    protected boolean mHasPayoutInfo;
    @JsonProperty("has_profile_pic")
    protected boolean mHasProfilePic;
    @JsonProperty("has_valid_payin_gibraltar_instruments")
    protected boolean mHasValidPayinGibraltarInstruments;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("identity_verifications_exempt")
    protected boolean mIdentityVerificationsExempt;
    @JsonProperty("is_active_host")
    protected boolean mIsActiveHost;
    @JsonProperty("is_superhost")
    protected boolean mIsSuperhost;
    @JsonProperty("is_trip_host")
    protected boolean mIsTripHost;
    @JsonProperty("languages")
    protected List<String> mLanguages;
    @JsonProperty("last_name")
    protected String mLastName;
    @JsonProperty("listings_count")
    protected int mListingsCount;
    @JsonProperty("location")
    protected String mLocation;
    @JsonProperty("identity_v2_verified")
    protected boolean mManuallyVerified;
    @JsonProperty("identity_mt_verified")
    protected boolean mMtVerifiedId;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("phone")
    protected String mPhone;
    @JsonProperty("phone_numbers")
    protected List<ProfilePhoneNumber> mPhoneNumbers;
    @JsonProperty("picture_url")
    protected String mPictureUrl;
    @JsonProperty("picture_large_url")
    protected String mPictureUrlLarge;
    @JsonProperty("previous_country")
    protected String mPreviousCountry;
    @JsonProperty("profile_pic_url")
    protected String mProfilePicUrl;
    @JsonProperty("recent_recommendation")
    protected Recommendation mRecentRecommendation;
    @JsonProperty("recent_review")
    protected Review mRecentReview;
    @JsonProperty("recommendation_count")
    protected int mRecommendationCount;
    @JsonProperty("reservations_as_guest_count")
    protected int mReservationsAsGuestCount;
    @JsonProperty("response_rate")
    protected String mResponseRate;
    @JsonProperty("response_time")
    protected String mResponseTime;
    @JsonProperty("review_ratings_as_guest")
    protected ReviewRatingsAsGuest mReviewRatingsAsGuest;
    @JsonProperty("reviewee_count")
    protected int mRevieweeCount;
    @JsonProperty("reviews_count_as_guest")
    protected int mReviewsCountAsGuest;
    @JsonProperty("school")
    protected String mSchool;
    @JsonProperty("show_travel_for_work")
    protected boolean mShowTravelForWork;
    @JsonProperty("signup_method")
    protected int mSignupMethod;
    @JsonProperty("soft_suspended")
    protected boolean mSoftSuspended;
    @JsonProperty("suspended")
    protected boolean mSuspended;
    @JsonProperty("suspension_end_date")
    protected String mSuspensionEndDate;
    @JsonProperty("thumbnail_url")
    protected String mThumbnailUrl;
    @JsonProperty("timezone")
    protected String mTimezone;
    @JsonProperty("total_listings_count")
    protected int mTotalListingsCount;
    @JsonProperty("unscrubbed_about")
    protected String mUnscrubbedAbout;
    @JsonProperty("user_flag")
    protected UserFlag mUserFlag;
    @JsonProperty("verification_labels")
    protected List<String> mVerificationLabels;
    @JsonProperty("verifications")
    protected List<String> mVerifications;
    @JsonProperty("verified_id_replacement_exempt")
    protected boolean mVerifiedIDReplacementExempt;
    @JsonProperty("identity_verified")
    protected boolean mVerifiedId;
    @JsonProperty("work")
    protected String mWork;

    protected GenUser(AirDate birthdate, AirDate governmentIdDob, AirDateTime createdAt, List<Article> contentFrameworkArticles, List<ProfilePhoneNumber> phoneNumbers, List<String> verificationLabels, List<String> verifications, List<String> languages, Recommendation recentRecommendation, Review recentReview, ReviewRatingsAsGuest reviewRatingsAsGuest, String age, String btAutoEnrollCompanyName, String country, String emailAddress, String name, String firstName, String lastName, String fullName, String phone, String profilePicUrl, String pictureUrl, String pictureUrlLarge, String thumbnailUrl, String previousCountry, String responseRate, String responseTime, String acceptanceRate, String school, String groups, String work, String about, String location, String gender, String timezone, String countryOfResidence, String determinedCountry, String unscrubbedAbout, String suspensionEndDate, UserFlag userFlag, boolean verifiedId, boolean mtVerifiedId, boolean hasPayoutInfo, boolean hasProfilePic, boolean isSuperhost, boolean manuallyVerified, boolean identityVerificationsExempt, boolean verifiedIDReplacementExempt, boolean completedAccountVerificationsForBooking, boolean isTripHost, boolean forceIdentityFlow, boolean hardSuspended, boolean softSuspended, boolean suspended, boolean showTravelForWork, boolean hasCompletedAccountVerificationsForProfileCompletion, boolean hasValidPayinGibraltarInstruments, boolean hasPastBookings, boolean eligibleForNestedListings, boolean isActiveHost, int signupMethod, int recommendationCount, int listingsCount, int totalListingsCount, int revieweeCount, int reservationsAsGuestCount, int reviewsCountAsGuest, int contentFrameworkArticlesCount, long id, long defaultPayinGibraltarInstrumentId, long defaultPayoutGibraltarInstrumentId) {
        this();
        this.mBirthdate = birthdate;
        this.mGovernmentIdDob = governmentIdDob;
        this.mCreatedAt = createdAt;
        this.mContentFrameworkArticles = contentFrameworkArticles;
        this.mPhoneNumbers = phoneNumbers;
        this.mVerificationLabels = verificationLabels;
        this.mVerifications = verifications;
        this.mLanguages = languages;
        this.mRecentRecommendation = recentRecommendation;
        this.mRecentReview = recentReview;
        this.mReviewRatingsAsGuest = reviewRatingsAsGuest;
        this.mAge = age;
        this.mBtAutoEnrollCompanyName = btAutoEnrollCompanyName;
        this.mCountry = country;
        this.mEmailAddress = emailAddress;
        this.mName = name;
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mFullName = fullName;
        this.mPhone = phone;
        this.mProfilePicUrl = profilePicUrl;
        this.mPictureUrl = pictureUrl;
        this.mPictureUrlLarge = pictureUrlLarge;
        this.mThumbnailUrl = thumbnailUrl;
        this.mPreviousCountry = previousCountry;
        this.mResponseRate = responseRate;
        this.mResponseTime = responseTime;
        this.mAcceptanceRate = acceptanceRate;
        this.mSchool = school;
        this.mGroups = groups;
        this.mWork = work;
        this.mAbout = about;
        this.mLocation = location;
        this.mGender = gender;
        this.mTimezone = timezone;
        this.mCountryOfResidence = countryOfResidence;
        this.mDeterminedCountry = determinedCountry;
        this.mUnscrubbedAbout = unscrubbedAbout;
        this.mSuspensionEndDate = suspensionEndDate;
        this.mUserFlag = userFlag;
        this.mVerifiedId = verifiedId;
        this.mMtVerifiedId = mtVerifiedId;
        this.mHasPayoutInfo = hasPayoutInfo;
        this.mHasProfilePic = hasProfilePic;
        this.mIsSuperhost = isSuperhost;
        this.mManuallyVerified = manuallyVerified;
        this.mIdentityVerificationsExempt = identityVerificationsExempt;
        this.mVerifiedIDReplacementExempt = verifiedIDReplacementExempt;
        this.mCompletedAccountVerificationsForBooking = completedAccountVerificationsForBooking;
        this.mIsTripHost = isTripHost;
        this.mForceIdentityFlow = forceIdentityFlow;
        this.mHardSuspended = hardSuspended;
        this.mSoftSuspended = softSuspended;
        this.mSuspended = suspended;
        this.mShowTravelForWork = showTravelForWork;
        this.mHasCompletedAccountVerificationsForProfileCompletion = hasCompletedAccountVerificationsForProfileCompletion;
        this.mHasValidPayinGibraltarInstruments = hasValidPayinGibraltarInstruments;
        this.mHasPastBookings = hasPastBookings;
        this.mEligibleForNestedListings = eligibleForNestedListings;
        this.mIsActiveHost = isActiveHost;
        this.mSignupMethod = signupMethod;
        this.mRecommendationCount = recommendationCount;
        this.mListingsCount = listingsCount;
        this.mTotalListingsCount = totalListingsCount;
        this.mRevieweeCount = revieweeCount;
        this.mReservationsAsGuestCount = reservationsAsGuestCount;
        this.mReviewsCountAsGuest = reviewsCountAsGuest;
        this.mContentFrameworkArticlesCount = contentFrameworkArticlesCount;
        this.mId = id;
        this.mDefaultPayinGibraltarInstrumentId = defaultPayinGibraltarInstrumentId;
        this.mDefaultPayoutGibraltarInstrumentId = defaultPayoutGibraltarInstrumentId;
    }

    protected GenUser() {
    }

    public AirDate getBirthdate() {
        return this.mBirthdate;
    }

    @JsonProperty("birthdate")
    public void setBirthdate(AirDate value) {
        this.mBirthdate = value;
    }

    public AirDate getGovernmentIdDob() {
        return this.mGovernmentIdDob;
    }

    @JsonProperty("government_id_dob")
    public void setGovernmentIdDob(AirDate value) {
        this.mGovernmentIdDob = value;
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public List<Article> getContentFrameworkArticles() {
        return this.mContentFrameworkArticles;
    }

    @JsonProperty("content_framework_articles")
    public void setContentFrameworkArticles(List<Article> value) {
        this.mContentFrameworkArticles = value;
    }

    public List<ProfilePhoneNumber> getPhoneNumbers() {
        return this.mPhoneNumbers;
    }

    public List<String> getVerificationLabels() {
        return this.mVerificationLabels;
    }

    @JsonProperty("verification_labels")
    public void setVerificationLabels(List<String> value) {
        this.mVerificationLabels = value;
    }

    public List<String> getVerifications() {
        return this.mVerifications;
    }

    @JsonProperty("verifications")
    public void setVerifications(List<String> value) {
        this.mVerifications = value;
    }

    public List<String> getLanguages() {
        return this.mLanguages;
    }

    @JsonProperty("languages")
    public void setLanguages(List<String> value) {
        this.mLanguages = value;
    }

    public Recommendation getRecentRecommendation() {
        return this.mRecentRecommendation;
    }

    public Review getRecentReview() {
        return this.mRecentReview;
    }

    public ReviewRatingsAsGuest getReviewRatingsAsGuest() {
        return this.mReviewRatingsAsGuest;
    }

    @JsonProperty("review_ratings_as_guest")
    public void setReviewRatingsAsGuest(ReviewRatingsAsGuest value) {
        this.mReviewRatingsAsGuest = value;
    }

    public String getAge() {
        return this.mAge;
    }

    @JsonProperty("age")
    public void setAge(String value) {
        this.mAge = value;
    }

    public String getBtAutoEnrollCompanyName() {
        return this.mBtAutoEnrollCompanyName;
    }

    @JsonProperty("bt_auto_enroll_company_name")
    public void setBtAutoEnrollCompanyName(String value) {
        this.mBtAutoEnrollCompanyName = value;
    }

    public String getCountry() {
        return this.mCountry;
    }

    @JsonProperty("country")
    public void setCountry(String value) {
        this.mCountry = value;
    }

    public String getEmailAddress() {
        return this.mEmailAddress;
    }

    @JsonProperty("email")
    public void setEmailAddress(String value) {
        this.mEmailAddress = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String value) {
        this.mFirstName = value;
    }

    public String getLastName() {
        return this.mLastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String value) {
        this.mLastName = value;
    }

    public String getFullName() {
        return this.mFullName;
    }

    @JsonProperty("full_name")
    public void setFullName(String value) {
        this.mFullName = value;
    }

    public String getPhone() {
        return this.mPhone;
    }

    @JsonProperty("phone")
    public void setPhone(String value) {
        this.mPhone = value;
    }

    public String getProfilePicUrl() {
        return this.mProfilePicUrl;
    }

    @JsonProperty("profile_pic_url")
    public void setProfilePicUrl(String value) {
        this.mProfilePicUrl = value;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }

    @JsonProperty("picture_url")
    public void setPictureUrl(String value) {
        this.mPictureUrl = value;
    }

    public String getPictureUrlLarge() {
        return this.mPictureUrlLarge;
    }

    @JsonProperty("picture_large_url")
    public void setPictureUrlLarge(String value) {
        this.mPictureUrlLarge = value;
    }

    public String getThumbnailUrl() {
        return this.mThumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String value) {
        this.mThumbnailUrl = value;
    }

    public String getPreviousCountry() {
        return this.mPreviousCountry;
    }

    @JsonProperty("previous_country")
    public void setPreviousCountry(String value) {
        this.mPreviousCountry = value;
    }

    public String getResponseRate() {
        return this.mResponseRate;
    }

    @JsonProperty("response_rate")
    public void setResponseRate(String value) {
        this.mResponseRate = value;
    }

    public String getResponseTime() {
        return this.mResponseTime;
    }

    @JsonProperty("response_time")
    public void setResponseTime(String value) {
        this.mResponseTime = value;
    }

    public String getAcceptanceRate() {
        return this.mAcceptanceRate;
    }

    @JsonProperty("acceptance_rate")
    public void setAcceptanceRate(String value) {
        this.mAcceptanceRate = value;
    }

    public String getSchool() {
        return this.mSchool;
    }

    @JsonProperty("school")
    public void setSchool(String value) {
        this.mSchool = value;
    }

    public String getGroups() {
        return this.mGroups;
    }

    @JsonProperty("groups")
    public void setGroups(String value) {
        this.mGroups = value;
    }

    public String getWork() {
        return this.mWork;
    }

    @JsonProperty("work")
    public void setWork(String value) {
        this.mWork = value;
    }

    public String getAbout() {
        return this.mAbout;
    }

    @JsonProperty("about")
    public void setAbout(String value) {
        this.mAbout = value;
    }

    public String getLocation() {
        return this.mLocation;
    }

    @JsonProperty("location")
    public void setLocation(String value) {
        this.mLocation = value;
    }

    public String getGender() {
        return this.mGender;
    }

    @JsonProperty("gender")
    public void setGender(String value) {
        this.mGender = value;
    }

    public String getTimezone() {
        return this.mTimezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String value) {
        this.mTimezone = value;
    }

    public String getCountryOfResidence() {
        return this.mCountryOfResidence;
    }

    @JsonProperty("country_of_residence")
    public void setCountryOfResidence(String value) {
        this.mCountryOfResidence = value;
    }

    public String getDeterminedCountry() {
        return this.mDeterminedCountry;
    }

    @JsonProperty("determined_country")
    public void setDeterminedCountry(String value) {
        this.mDeterminedCountry = value;
    }

    public String getUnscrubbedAbout() {
        return this.mUnscrubbedAbout;
    }

    @JsonProperty("unscrubbed_about")
    public void setUnscrubbedAbout(String value) {
        this.mUnscrubbedAbout = value;
    }

    public String getSuspensionEndDate() {
        return this.mSuspensionEndDate;
    }

    @JsonProperty("suspension_end_date")
    public void setSuspensionEndDate(String value) {
        this.mSuspensionEndDate = value;
    }

    public UserFlag getUserFlag() {
        return this.mUserFlag;
    }

    @JsonProperty("user_flag")
    public void setUserFlag(UserFlag value) {
        this.mUserFlag = value;
    }

    public boolean isVerifiedId() {
        return this.mVerifiedId;
    }

    @JsonProperty("identity_verified")
    public void setVerifiedId(boolean value) {
        this.mVerifiedId = value;
    }

    public boolean isMtVerifiedId() {
        return this.mMtVerifiedId;
    }

    @JsonProperty("identity_mt_verified")
    public void setMtVerifiedId(boolean value) {
        this.mMtVerifiedId = value;
    }

    public boolean hasPayoutInfo() {
        return this.mHasPayoutInfo;
    }

    @JsonProperty("has_available_payout_info")
    public void setHasPayoutInfo(boolean value) {
        this.mHasPayoutInfo = value;
    }

    public boolean hasProfilePic() {
        return this.mHasProfilePic;
    }

    @JsonProperty("has_profile_pic")
    public void setHasProfilePic(boolean value) {
        this.mHasProfilePic = value;
    }

    public boolean isSuperhost() {
        return this.mIsSuperhost;
    }

    @JsonProperty("is_superhost")
    public void setIsSuperhost(boolean value) {
        this.mIsSuperhost = value;
    }

    public boolean isManuallyVerified() {
        return this.mManuallyVerified;
    }

    @JsonProperty("identity_v2_verified")
    public void setManuallyVerified(boolean value) {
        this.mManuallyVerified = value;
    }

    public boolean isIdentityVerificationsExempt() {
        return this.mIdentityVerificationsExempt;
    }

    @JsonProperty("identity_verifications_exempt")
    public void setIdentityVerificationsExempt(boolean value) {
        this.mIdentityVerificationsExempt = value;
    }

    public boolean isVerifiedIDReplacementExempt() {
        return this.mVerifiedIDReplacementExempt;
    }

    @JsonProperty("verified_id_replacement_exempt")
    public void setVerifiedIDReplacementExempt(boolean value) {
        this.mVerifiedIDReplacementExempt = value;
    }

    public boolean isCompletedAccountVerificationsForBooking() {
        return this.mCompletedAccountVerificationsForBooking;
    }

    @JsonProperty("completed_account_verifications_for_booking")
    public void setCompletedAccountVerificationsForBooking(boolean value) {
        this.mCompletedAccountVerificationsForBooking = value;
    }

    public boolean isTripHost() {
        return this.mIsTripHost;
    }

    @JsonProperty("is_trip_host")
    public void setIsTripHost(boolean value) {
        this.mIsTripHost = value;
    }

    public boolean isForceIdentityFlow() {
        return this.mForceIdentityFlow;
    }

    @JsonProperty("force_use_identity_flow_for_verified_id")
    public void setForceIdentityFlow(boolean value) {
        this.mForceIdentityFlow = value;
    }

    public boolean isHardSuspended() {
        return this.mHardSuspended;
    }

    @JsonProperty("hard_suspended")
    public void setHardSuspended(boolean value) {
        this.mHardSuspended = value;
    }

    public boolean isSoftSuspended() {
        return this.mSoftSuspended;
    }

    @JsonProperty("soft_suspended")
    public void setSoftSuspended(boolean value) {
        this.mSoftSuspended = value;
    }

    public boolean isSuspended() {
        return this.mSuspended;
    }

    @JsonProperty("suspended")
    public void setSuspended(boolean value) {
        this.mSuspended = value;
    }

    public boolean isShowTravelForWork() {
        return this.mShowTravelForWork;
    }

    @JsonProperty("show_travel_for_work")
    public void setShowTravelForWork(boolean value) {
        this.mShowTravelForWork = value;
    }

    public boolean hasCompletedAccountVerificationsForProfileCompletion() {
        return this.mHasCompletedAccountVerificationsForProfileCompletion;
    }

    @JsonProperty("completed_account_verifications_for_profile_completion")
    public void setHasCompletedAccountVerificationsForProfileCompletion(boolean value) {
        this.mHasCompletedAccountVerificationsForProfileCompletion = value;
    }

    public boolean hasValidPayinGibraltarInstruments() {
        return this.mHasValidPayinGibraltarInstruments;
    }

    @JsonProperty("has_valid_payin_gibraltar_instruments")
    public void setHasValidPayinGibraltarInstruments(boolean value) {
        this.mHasValidPayinGibraltarInstruments = value;
    }

    public boolean hasPastBookings() {
        return this.mHasPastBookings;
    }

    @JsonProperty("has_past_bookings")
    public void setHasPastBookings(boolean value) {
        this.mHasPastBookings = value;
    }

    public boolean isEligibleForNestedListings() {
        return this.mEligibleForNestedListings;
    }

    @JsonProperty("eligible_for_nested_listings")
    public void setEligibleForNestedListings(boolean value) {
        this.mEligibleForNestedListings = value;
    }

    public boolean isActiveHost() {
        return this.mIsActiveHost;
    }

    @JsonProperty("is_active_host")
    public void setIsActiveHost(boolean value) {
        this.mIsActiveHost = value;
    }

    public int getSignupMethod() {
        return this.mSignupMethod;
    }

    @JsonProperty("signup_method")
    public void setSignupMethod(int value) {
        this.mSignupMethod = value;
    }

    public int getRecommendationCount() {
        return this.mRecommendationCount;
    }

    @JsonProperty("recommendation_count")
    public void setRecommendationCount(int value) {
        this.mRecommendationCount = value;
    }

    public int getListingsCount() {
        return this.mListingsCount;
    }

    @JsonProperty("listings_count")
    public void setListingsCount(int value) {
        this.mListingsCount = value;
    }

    public int getTotalListingsCount() {
        return this.mTotalListingsCount;
    }

    @JsonProperty("total_listings_count")
    public void setTotalListingsCount(int value) {
        this.mTotalListingsCount = value;
    }

    public int getRevieweeCount() {
        return this.mRevieweeCount;
    }

    @JsonProperty("reviewee_count")
    public void setRevieweeCount(int value) {
        this.mRevieweeCount = value;
    }

    public int getReservationsAsGuestCount() {
        return this.mReservationsAsGuestCount;
    }

    @JsonProperty("reservations_as_guest_count")
    public void setReservationsAsGuestCount(int value) {
        this.mReservationsAsGuestCount = value;
    }

    public int getReviewsCountAsGuest() {
        return this.mReviewsCountAsGuest;
    }

    @JsonProperty("reviews_count_as_guest")
    public void setReviewsCountAsGuest(int value) {
        this.mReviewsCountAsGuest = value;
    }

    public int getContentFrameworkArticlesCount() {
        return this.mContentFrameworkArticlesCount;
    }

    @JsonProperty("content_framework_articles_count")
    public void setContentFrameworkArticlesCount(int value) {
        this.mContentFrameworkArticlesCount = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getDefaultPayinGibraltarInstrumentId() {
        return this.mDefaultPayinGibraltarInstrumentId;
    }

    @JsonProperty("default_payin_gibraltar_instrument_id")
    public void setDefaultPayinGibraltarInstrumentId(long value) {
        this.mDefaultPayinGibraltarInstrumentId = value;
    }

    public long getDefaultPayoutGibraltarInstrumentId() {
        return this.mDefaultPayoutGibraltarInstrumentId;
    }

    @JsonProperty("default_payout_gibraltar_instrument_id")
    public void setDefaultPayoutGibraltarInstrumentId(long value) {
        this.mDefaultPayoutGibraltarInstrumentId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mBirthdate, 0);
        parcel.writeParcelable(this.mGovernmentIdDob, 0);
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeTypedList(this.mContentFrameworkArticles);
        parcel.writeTypedList(this.mPhoneNumbers);
        parcel.writeStringList(this.mVerificationLabels);
        parcel.writeStringList(this.mVerifications);
        parcel.writeStringList(this.mLanguages);
        parcel.writeParcelable(this.mRecentRecommendation, 0);
        parcel.writeParcelable(this.mRecentReview, 0);
        parcel.writeParcelable(this.mReviewRatingsAsGuest, 0);
        parcel.writeString(this.mAge);
        parcel.writeString(this.mBtAutoEnrollCompanyName);
        parcel.writeString(this.mCountry);
        parcel.writeString(this.mEmailAddress);
        parcel.writeString(this.mName);
        parcel.writeString(this.mFirstName);
        parcel.writeString(this.mLastName);
        parcel.writeString(this.mFullName);
        parcel.writeString(this.mPhone);
        parcel.writeString(this.mProfilePicUrl);
        parcel.writeString(this.mPictureUrl);
        parcel.writeString(this.mPictureUrlLarge);
        parcel.writeString(this.mThumbnailUrl);
        parcel.writeString(this.mPreviousCountry);
        parcel.writeString(this.mResponseRate);
        parcel.writeString(this.mResponseTime);
        parcel.writeString(this.mAcceptanceRate);
        parcel.writeString(this.mSchool);
        parcel.writeString(this.mGroups);
        parcel.writeString(this.mWork);
        parcel.writeString(this.mAbout);
        parcel.writeString(this.mLocation);
        parcel.writeString(this.mGender);
        parcel.writeString(this.mTimezone);
        parcel.writeString(this.mCountryOfResidence);
        parcel.writeString(this.mDeterminedCountry);
        parcel.writeString(this.mUnscrubbedAbout);
        parcel.writeString(this.mSuspensionEndDate);
        parcel.writeParcelable(this.mUserFlag, 0);
        parcel.writeBooleanArray(new boolean[]{this.mVerifiedId, this.mMtVerifiedId, this.mHasPayoutInfo, this.mHasProfilePic, this.mIsSuperhost, this.mManuallyVerified, this.mIdentityVerificationsExempt, this.mVerifiedIDReplacementExempt, this.mCompletedAccountVerificationsForBooking, this.mIsTripHost, this.mForceIdentityFlow, this.mHardSuspended, this.mSoftSuspended, this.mSuspended, this.mShowTravelForWork, this.mHasCompletedAccountVerificationsForProfileCompletion, this.mHasValidPayinGibraltarInstruments, this.mHasPastBookings, this.mEligibleForNestedListings, this.mIsActiveHost});
        parcel.writeInt(this.mSignupMethod);
        parcel.writeInt(this.mRecommendationCount);
        parcel.writeInt(this.mListingsCount);
        parcel.writeInt(this.mTotalListingsCount);
        parcel.writeInt(this.mRevieweeCount);
        parcel.writeInt(this.mReservationsAsGuestCount);
        parcel.writeInt(this.mReviewsCountAsGuest);
        parcel.writeInt(this.mContentFrameworkArticlesCount);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mDefaultPayinGibraltarInstrumentId);
        parcel.writeLong(this.mDefaultPayoutGibraltarInstrumentId);
    }

    public void readFromParcel(Parcel source) {
        this.mBirthdate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mGovernmentIdDob = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mContentFrameworkArticles = source.createTypedArrayList(Article.CREATOR);
        this.mPhoneNumbers = source.createTypedArrayList(ProfilePhoneNumber.CREATOR);
        this.mVerificationLabels = source.createStringArrayList();
        this.mVerifications = source.createStringArrayList();
        this.mLanguages = source.createStringArrayList();
        this.mRecentRecommendation = (Recommendation) source.readParcelable(Recommendation.class.getClassLoader());
        this.mRecentReview = (Review) source.readParcelable(Review.class.getClassLoader());
        this.mReviewRatingsAsGuest = (ReviewRatingsAsGuest) source.readParcelable(ReviewRatingsAsGuest.class.getClassLoader());
        this.mAge = source.readString();
        this.mBtAutoEnrollCompanyName = source.readString();
        this.mCountry = source.readString();
        this.mEmailAddress = source.readString();
        this.mName = source.readString();
        this.mFirstName = source.readString();
        this.mLastName = source.readString();
        this.mFullName = source.readString();
        this.mPhone = source.readString();
        this.mProfilePicUrl = source.readString();
        this.mPictureUrl = source.readString();
        this.mPictureUrlLarge = source.readString();
        this.mThumbnailUrl = source.readString();
        this.mPreviousCountry = source.readString();
        this.mResponseRate = source.readString();
        this.mResponseTime = source.readString();
        this.mAcceptanceRate = source.readString();
        this.mSchool = source.readString();
        this.mGroups = source.readString();
        this.mWork = source.readString();
        this.mAbout = source.readString();
        this.mLocation = source.readString();
        this.mGender = source.readString();
        this.mTimezone = source.readString();
        this.mCountryOfResidence = source.readString();
        this.mDeterminedCountry = source.readString();
        this.mUnscrubbedAbout = source.readString();
        this.mSuspensionEndDate = source.readString();
        this.mUserFlag = (UserFlag) source.readParcelable(UserFlag.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mVerifiedId = bools[0];
        this.mMtVerifiedId = bools[1];
        this.mHasPayoutInfo = bools[2];
        this.mHasProfilePic = bools[3];
        this.mIsSuperhost = bools[4];
        this.mManuallyVerified = bools[5];
        this.mIdentityVerificationsExempt = bools[6];
        this.mVerifiedIDReplacementExempt = bools[7];
        this.mCompletedAccountVerificationsForBooking = bools[8];
        this.mIsTripHost = bools[9];
        this.mForceIdentityFlow = bools[10];
        this.mHardSuspended = bools[11];
        this.mSoftSuspended = bools[12];
        this.mSuspended = bools[13];
        this.mShowTravelForWork = bools[14];
        this.mHasCompletedAccountVerificationsForProfileCompletion = bools[15];
        this.mHasValidPayinGibraltarInstruments = bools[16];
        this.mHasPastBookings = bools[17];
        this.mEligibleForNestedListings = bools[18];
        this.mIsActiveHost = bools[19];
        this.mSignupMethod = source.readInt();
        this.mRecommendationCount = source.readInt();
        this.mListingsCount = source.readInt();
        this.mTotalListingsCount = source.readInt();
        this.mRevieweeCount = source.readInt();
        this.mReservationsAsGuestCount = source.readInt();
        this.mReviewsCountAsGuest = source.readInt();
        this.mContentFrameworkArticlesCount = source.readInt();
        this.mId = source.readLong();
        this.mDefaultPayinGibraltarInstrumentId = source.readLong();
        this.mDefaultPayoutGibraltarInstrumentId = source.readLong();
    }
}
