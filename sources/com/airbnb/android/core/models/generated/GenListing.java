package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.BathroomType;
import com.airbnb.android.core.enums.ListYourSpacePricingMode;
import com.airbnb.android.core.enums.ListingStatus;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.AutoPricing;
import com.airbnb.android.core.models.C5990Guidebook;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.models.CommercialHostInfo;
import com.airbnb.android.core.models.DemandCounts;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.airbnb.android.core.models.Incentive;
import com.airbnb.android.core.models.ListingExpectation;
import com.airbnb.android.core.models.ListingOccupancyInfo;
import com.airbnb.android.core.models.ListingPersonaInput;
import com.airbnb.android.core.models.ListingReviewScores;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.airbnb.android.core.models.LocalizedCancellationPolicy;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.PreBookingQuestion;
import com.airbnb.android.core.models.PriceFactor;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.SectionedListingDescription;
import com.airbnb.android.core.models.SnoozeMode;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.UserFlag;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListing implements Parcelable {
    @JsonProperty("access")
    protected String mAccess;
    @JsonProperty("additional_house_rules")
    protected String mAdditionalHouseRules;
    @JsonProperty("address")
    protected String mAddress;
    @JsonProperty("non_existent_server_key_for_client_side_use_only")
    protected List<Amenity> mAmenities;
    @JsonProperty("amenities_ids")
    protected List<Integer> mAmenityIds;
    @JsonProperty("apt")
    protected String mApartment;
    @JsonProperty("ap_pricing")
    protected AutoPricing mAutoPricing;
    @JsonProperty("available_cancellation_policies")
    protected List<LocalizedCancellationPolicy> mAvailableCancellationPolicies;
    @JsonProperty("bathroom_type")
    protected BathroomType mBathroomType;
    @JsonProperty("bathrooms")
    protected float mBathrooms;
    @JsonProperty("beds")
    protected int mBedCount;
    @JsonProperty("bed_type")
    protected String mBedType;
    @JsonProperty("bed_type_category")
    protected String mBedTypeCategory;
    @JsonProperty("bedrooms")
    protected int mBedrooms;
    @JsonProperty("booking_custom_questions")
    protected List<String> mBookingCustomQuestions;
    @JsonProperty("booking_standard_questions")
    protected List<PreBookingQuestion> mBookingStandardQuestions;
    @JsonProperty("cancel_policy_short_str")
    protected String mCancellationPolicy;
    @JsonProperty("cancellation_policy")
    protected String mCancellationPolicyKey;
    @JsonProperty("check_in_guide_status")
    protected Integer mCheckInGuideStatus;
    @JsonProperty("check_in_information")
    protected List<CheckInInformation> mCheckInInformation;
    @JsonProperty("check_in_time")
    protected Integer mCheckInTime;
    @JsonProperty("check_in_time_end")
    protected String mCheckInTimeEnd;
    @JsonProperty("check_in_time_start")
    protected String mCheckInTimeStart;
    @JsonProperty("check_out_time")
    protected Integer mCheckOutTime;
    @JsonProperty("city")
    protected String mCity;
    @JsonProperty("cleaning_fee_native")
    protected int mCleaningFee;
    @JsonProperty("homes_collections_application")
    protected HomeCollectionApplication mCollectionsApplication;
    @JsonProperty("commercial_host_info")
    protected CommercialHostInfo mCommercialHostInfo;
    @JsonProperty("country")
    protected String mCountry;
    @JsonProperty("country_code")
    protected String mCountryCode;
    @JsonProperty("demand_counts")
    protected List<DemandCounts> mDemandCounts;
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("description_locale")
    protected String mDescriptionLocale;
    @JsonProperty("directions")
    protected String mDirections;
    @JsonProperty("distance")
    protected String mDistance;
    @JsonProperty("dynamic_pricing_controls")
    protected DynamicPricingControl mDynamicPricingControls;
    @JsonProperty("price_for_extra_person_native")
    protected int mExtraGuestPrice;
    @JsonProperty("force_mobile_legal_modal")
    protected boolean mForceMobileLegalModal;
    @JsonProperty("fully_refundable")
    protected boolean mFullyRefundable;
    @JsonProperty("guest_controls")
    protected GuestControls mGuestControls;
    @JsonProperty("guests_included")
    protected int mGuestsIncluded;
    @JsonProperty("has_agreed_to_legal_terms")
    protected boolean mHasAgreedToLegalTerms;
    @JsonProperty("has_availability")
    protected boolean mHasAvailability;
    @JsonProperty("has_ever_been_available")
    protected boolean mHasBeenListed;
    @JsonProperty("has_closed_instant_book_ftue")
    protected boolean mHasClosedInstantBookFtue;
    @JsonProperty("has_paid_amenities")
    protected boolean mHasPaidAmenities;
    @JsonProperty("user")
    protected User mHost;
    @JsonProperty("host_check_in_time_phrase_for_p4")
    protected String mHostCheckInTimePhrase;
    @JsonProperty("host_guidebook")
    protected C5990Guidebook mHostGuidebook;
    @JsonProperty("host_languages")
    protected List<String> mHostLanguages;
    @JsonProperty("host_thumbnail_url")
    protected String mHostThumbnailUrl;
    @JsonProperty("hosts")
    protected List<User> mHosts;
    @JsonProperty("house_manual")
    protected String mHouseManual;
    @JsonProperty("house_rules")
    protected String mHouseRules;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("in_toto_area")
    protected boolean mInTotoArea;
    @JsonProperty("incentives")
    protected List<Incentive> mIncentives;
    @JsonProperty("instant_book_eligible")
    protected boolean mInstantBookEligible;
    @JsonProperty("instant_book_enabled")
    protected boolean mInstantBookEnabled;
    @JsonProperty("instant_book_lead_time_hours")
    protected Integer mInstantBookLeadTimeHours;
    @JsonProperty("instant_book_welcome_message")
    protected String mInstantBookWelcomeMessage;
    @JsonProperty("instant_bookable")
    protected boolean mInstantBookable;
    @JsonProperty("instant_booking_allowed_category")
    protected String mInstantBookingAllowedCategory;
    @JsonProperty("instant_booking_visibility")
    protected String mInstantBookingVisibility;
    @JsonProperty("interaction")
    protected String mInteraction;
    @JsonProperty("is_address_editable")
    protected boolean mIsAddressEditable;
    @JsonProperty("is_business_travel_ready")
    protected boolean mIsBusinessTravelReady;
    @JsonProperty("instant_booking_visibility_set")
    protected boolean mIsInstantBookingVisibilitySet;
    @JsonProperty("is_location_editable")
    protected boolean mIsLocationEditable;
    @JsonProperty("is_new_listing")
    protected Boolean mIsNewListing;
    @JsonProperty("is_price_monthly")
    protected boolean mIsPriceMonthly;
    @JsonProperty("is_superhost")
    protected Boolean mIsSuperhost;
    @JsonProperty("lat")
    protected double mLatitude;
    @JsonProperty("license")
    protected String mLicense;
    @JsonProperty("list_your_space_last_finished_step_id")
    protected String mListYourSpaceLastFinishedStepId;
    @JsonProperty("list_your_space_pricing_mode")
    protected ListYourSpacePricingMode mListYourSpacePricingMode;
    @JsonProperty("listing_cleaning_fee_native")
    protected Integer mListingCleaningFeeNative;
    @JsonProperty("listing_currency")
    protected String mListingCurrency;
    @JsonProperty("initial_description_author_type_for_guidebooks")
    protected String mListingDescriptionAuthorType;
    @JsonProperty("listing_expectations")
    protected List<ListingExpectation> mListingExpectations;
    @JsonProperty("listing_monthly_price_native")
    protected int mListingMonthlyPriceNative;
    @JsonProperty("listing_native_currency")
    protected String mListingNativeCurrency;
    @JsonProperty("listing_persona_responses")
    protected List<ListingPersonaInput> mListingPersonaInputs;
    @JsonProperty("listing_price")
    protected int mListingPrice;
    @JsonProperty("listing_price_for_extra_person_native")
    protected int mListingPriceForExtraPersonNative;
    @JsonProperty("listing_price_native")
    protected int mListingPriceNative;
    @JsonProperty("listing_rooms")
    protected List<ListingRoom> mListingRooms;
    @JsonProperty("listing_security_deposit_native")
    protected Integer mListingSecurityDepositNative;
    @JsonProperty("listing_weekend_price_native")
    protected int mListingWeekendPriceNative;
    @JsonProperty("listing_weekly_price_native")
    protected int mListingWeeklyPriceNative;
    @JsonProperty("localized_additional_house_rules")
    protected String mLocalizedAdditionalHouseRules;
    @JsonProperty("localized_additional_house_rules_with_google_translate")
    protected String mLocalizedAdditionalHouseRulesWithGoogleTranslate;
    @JsonProperty("localized_check_in_time_window")
    protected String mLocalizedCheckInTimeWindow;
    @JsonProperty("localized_check_out_time")
    protected String mLocalizedCheckOutTime;
    @JsonProperty("localized_city")
    protected String mLocalizedCity;
    @JsonProperty("localized_listing_expectations")
    protected List<ListingExpectation> mLocalizedListingExpectations;
    @JsonProperty("localized_wireless_info_description")
    protected String mLocalizedWirelessInfoDescription;
    @JsonProperty("smart_location")
    protected String mLocation;
    @JsonProperty("is_location_exact")
    protected boolean mLocationExact;
    @JsonProperty("lng")
    protected double mLongitude;
    @JsonProperty("max_nights")
    protected int mMaxNights;
    @JsonProperty("min_nights")
    protected int mMinNights;
    @JsonProperty("monthly_price_factor")
    protected PriceFactor mMonthlyPriceFactor;
    @JsonProperty("monthly_price_native")
    protected int mMonthlyPriceNative;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("name_or_placeholder_name")
    protected String mNameOrPlaceholderName;
    @JsonProperty("native_currency")
    protected String mNativeCurrency;
    @JsonProperty("neighborhood")
    protected String mNeighborhood;
    @JsonProperty("neighborhood_id")
    protected long mNeighborhoodId;
    @JsonProperty("neighborhood_overview")
    protected String mNeighborhoodOverview;
    @JsonProperty("notes")
    protected String mNotes;
    @JsonProperty("listing_occupancy_info")
    protected ListingOccupancyInfo mOccupancyInfo;
    @JsonProperty("page_views")
    protected int mPageViews;
    @JsonProperty("person_capacity")
    protected int mPersonCapacity;
    @JsonProperty("photos")
    protected List<Photo> mPhotos;
    @JsonProperty("picture_count")
    protected int mPictureCount;
    @JsonProperty("picture_url")
    protected String mPictureUrl;
    @JsonProperty("picture_urls")
    protected List<String> mPictureUrls;
    @JsonProperty("preview_encoded_png")
    protected String mPreviewEncodedPng;
    @JsonProperty("price")
    protected double mPrice;
    @JsonProperty("price_formatted")
    protected String mPriceFormatted;
    @JsonProperty("price_native")
    protected int mPriceNative;
    @JsonProperty("pricing_quote")
    protected PricingQuote mPricingQuote;
    @JsonProperty("primary_host")
    protected User mPrimaryHost;
    @JsonProperty("photography_status")
    protected String mProPhotoStatus;
    @JsonProperty("property_type")
    protected String mPropertyType;
    @JsonProperty("property_type_id")
    protected int mPropertyTypeId;
    @JsonProperty("public_address")
    protected String mPublicAddress;
    @JsonProperty("rated_reviews_count")
    protected int mRatedReviewsCount;
    @JsonProperty("ready_for_select_status")
    protected int mReadyForSelectStatus;
    @JsonProperty("remarketing_ids")
    protected List<Long> mRemarketingIds;
    @JsonProperty("requires_license")
    protected boolean mRequiresLicense;
    @JsonProperty("review_rating_accuracy")
    protected float mReviewRatingAccuracy;
    @JsonProperty("review_rating_checkin")
    protected float mReviewRatingCheckin;
    @JsonProperty("review_rating_cleanliness")
    protected float mReviewRatingCleanliness;
    @JsonProperty("review_rating_communication")
    protected float mReviewRatingCommunication;
    @JsonProperty("review_rating_location")
    protected float mReviewRatingLocation;
    @JsonProperty("star_rating_overall")
    protected float mReviewRatingOverall;
    @JsonProperty("review_rating_value")
    protected float mReviewRatingValue;
    @JsonProperty("review_scores")
    protected ListingReviewScores mReviewScores;
    @JsonProperty("reviews_count")
    protected int mReviewsCount;
    @JsonProperty("room_type")
    protected String mRoomType;
    @JsonProperty("room_type_category")
    protected String mRoomTypeKey;
    @JsonProperty("scrim_color")
    protected int mScrimColor;
    @JsonProperty("sectioned_description")
    protected SectionedListingDescription mSectionedDescription;
    @JsonProperty("security_deposit_native")
    protected int mSecurityDeposit;
    @JsonProperty("smart_pricing_available")
    protected boolean mSmartPricingAvailable;
    @JsonProperty("smart_pricing_extended")
    protected boolean mSmartPricingExtended;
    @JsonProperty("snooze_mode")
    protected SnoozeMode mSnoozeMode;
    @JsonProperty("space")
    protected String mSpace;
    @JsonProperty("space_type")
    protected String mSpaceTypeDescription;
    @JsonProperty("special_offer")
    protected SpecialOffer mSpecialOffer;
    @JsonProperty("square_feet")
    protected String mSquareFeet;
    @JsonProperty("star_rating")
    protected float mStarRating;
    @JsonProperty("state")
    protected String mState;
    @JsonProperty("status")
    protected ListingStatus mStatus;
    @JsonProperty("steps_remaining")
    protected int mStepsRemaining;
    @JsonProperty("street")
    protected String mStreetAddress;
    @JsonProperty("summary")
    protected String mSummary;
    @JsonProperty("thumbnail_url")
    protected String mThumbnailUrl;
    @JsonProperty("thumbnail_urls")
    protected List<String> mThumbnailUrls;
    @JsonProperty("time_zone_name")
    protected String mTimeZoneName;
    @JsonProperty("toto_opt_in")
    protected Boolean mTotoOptIn;
    @JsonProperty("transit")
    protected String mTransit;
    @JsonProperty("alternate_sectioned_description_for_guidebooks")
    protected SectionedListingDescription mTranslatedSectionedDescription;
    @JsonProperty("unlisted_at")
    protected AirDate mUnlistedAt;
    @JsonProperty("unscrubbed_name")
    protected String mUnscrubbedName;
    @JsonProperty("unscrubbed_summary")
    protected String mUnscrubbedSummary;
    @JsonProperty("user_defined_location")
    protected boolean mUserDefinedLocation;
    @JsonProperty("user_flag")
    protected UserFlag mUserFlag;
    @JsonProperty("user_id")
    protected long mUserId;
    @JsonProperty("viewed_at")
    protected long mViewedAt;
    @JsonProperty("weekend_price_native")
    protected int mWeekendPrice;
    @JsonProperty("weekly_price_factor")
    protected PriceFactor mWeeklyPriceFactor;
    @JsonProperty("weekly_price_native")
    protected int mWeeklyPriceNative;
    @JsonProperty("wireless_info")
    protected ListingWirelessInfo mWirelessInfo;
    @JsonProperty("xl_picture_url")
    protected String mXlPictureUrl;
    @JsonProperty("xl_picture_urls")
    protected List<String> mXlPictureUrls;
    @JsonProperty("zipcode")
    protected String mZipCode;

    protected GenListing(AirDate unlistedAt, AutoPricing autoPricing, BathroomType bathroomType, Boolean isNewListing, Boolean totoOptIn, Boolean isSuperhost, CommercialHostInfo commercialHostInfo, DynamicPricingControl dynamicPricingControls, GuestControls guestControls, C5990Guidebook hostGuidebook, HomeCollectionApplication collectionsApplication, Integer checkInTime, Integer checkOutTime, Integer checkInGuideStatus, Integer instantBookLeadTimeHours, Integer listingSecurityDepositNative, Integer listingCleaningFeeNative, List<Amenity> amenities, List<CheckInInformation> checkInInformation, List<DemandCounts> demandCounts, List<Incentive> incentives, List<Integer> amenityIds, List<ListingExpectation> listingExpectations, List<ListingExpectation> localizedListingExpectations, List<ListingPersonaInput> listingPersonaInputs, List<ListingRoom> listingRooms, List<LocalizedCancellationPolicy> availableCancellationPolicies, List<Long> remarketingIds, List<Photo> photos, List<PreBookingQuestion> bookingStandardQuestions, List<String> bookingCustomQuestions, List<String> pictureUrls, List<String> thumbnailUrls, List<String> xlPictureUrls, List<String> hostLanguages, List<User> hosts, ListYourSpacePricingMode listYourSpacePricingMode, ListingOccupancyInfo occupancyInfo, ListingReviewScores reviewScores, ListingStatus status, ListingWirelessInfo wirelessInfo, PriceFactor monthlyPriceFactor, PriceFactor weeklyPriceFactor, PricingQuote pricingQuote, SectionedListingDescription sectionedDescription, SectionedListingDescription translatedSectionedDescription, SnoozeMode snoozeMode, SpecialOffer specialOffer, String access, String address, String additionalHouseRules, String apartment, String streetAddress, String bedType, String bedTypeCategory, String cancellationPolicy, String cancellationPolicyKey, String city, String checkInTimeStart, String checkInTimeEnd, String country, String countryCode, String description, String descriptionLocale, String directions, String hostCheckInTimePhrase, String houseRules, String houseManual, String location, String listingCurrency, String listingNativeCurrency, String instantBookingVisibility, String instantBookingAllowedCategory, String interaction, String listingDescriptionAuthorType, String name, String nameOrPlaceholderName, String unscrubbedName, String proPhotoStatus, String publicAddress, String nativeCurrency, String neighborhood, String neighborhoodOverview, String notes, String pictureUrl, String xlPictureUrl, String propertyType, String priceFormatted, String roomType, String roomTypeKey, String space, String spaceTypeDescription, String state, String summary, String unscrubbedSummary, String squareFeet, String thumbnailUrl, String previewEncodedPng, String transit, String zipCode, String distance, String license, String timeZoneName, String instantBookWelcomeMessage, String localizedWirelessInfoDescription, String localizedCheckInTimeWindow, String localizedCheckOutTime, String localizedCity, String localizedAdditionalHouseRules, String localizedAdditionalHouseRulesWithGoogleTranslate, String listYourSpaceLastFinishedStepId, String hostThumbnailUrl, User host, User primaryHost, UserFlag userFlag, boolean hasAvailability, boolean hasBeenListed, boolean hasClosedInstantBookFtue, boolean instantBookable, boolean instantBookEligible, boolean userDefinedLocation, boolean locationExact, boolean isPriceMonthly, boolean hasAgreedToLegalTerms, boolean forceMobileLegalModal, boolean requiresLicense, boolean inTotoArea, boolean instantBookEnabled, boolean smartPricingAvailable, boolean smartPricingExtended, boolean isInstantBookingVisibilitySet, boolean isBusinessTravelReady, boolean hasPaidAmenities, boolean isAddressEditable, boolean isLocationEditable, boolean fullyRefundable, double latitude, double longitude, double price, float bathrooms, float starRating, float reviewRatingAccuracy, float reviewRatingCheckin, float reviewRatingCleanliness, float reviewRatingCommunication, float reviewRatingLocation, float reviewRatingOverall, float reviewRatingValue, int bedrooms, int bedCount, int cleaningFee, int extraGuestPrice, int guestsIncluded, int listingPrice, int listingPriceNative, int listingWeeklyPriceNative, int listingMonthlyPriceNative, int listingWeekendPriceNative, int listingPriceForExtraPersonNative, int minNights, int maxNights, int monthlyPriceNative, int personCapacity, int pictureCount, int ratedReviewsCount, int priceNative, int propertyTypeId, int readyForSelectStatus, int reviewsCount, int pageViews, int securityDeposit, int weeklyPriceNative, int weekendPrice, int stepsRemaining, int scrimColor, long id, long userId, long neighborhoodId, long viewedAt) {
        this();
        this.mUnlistedAt = unlistedAt;
        this.mAutoPricing = autoPricing;
        this.mBathroomType = bathroomType;
        this.mIsNewListing = isNewListing;
        this.mTotoOptIn = totoOptIn;
        this.mIsSuperhost = isSuperhost;
        this.mCommercialHostInfo = commercialHostInfo;
        this.mDynamicPricingControls = dynamicPricingControls;
        this.mGuestControls = guestControls;
        this.mHostGuidebook = hostGuidebook;
        this.mCollectionsApplication = collectionsApplication;
        this.mCheckInTime = checkInTime;
        this.mCheckOutTime = checkOutTime;
        this.mCheckInGuideStatus = checkInGuideStatus;
        this.mInstantBookLeadTimeHours = instantBookLeadTimeHours;
        this.mListingSecurityDepositNative = listingSecurityDepositNative;
        this.mListingCleaningFeeNative = listingCleaningFeeNative;
        this.mAmenities = amenities;
        this.mCheckInInformation = checkInInformation;
        this.mDemandCounts = demandCounts;
        this.mIncentives = incentives;
        this.mAmenityIds = amenityIds;
        this.mListingExpectations = listingExpectations;
        this.mLocalizedListingExpectations = localizedListingExpectations;
        this.mListingPersonaInputs = listingPersonaInputs;
        this.mListingRooms = listingRooms;
        this.mAvailableCancellationPolicies = availableCancellationPolicies;
        this.mRemarketingIds = remarketingIds;
        this.mPhotos = photos;
        this.mBookingStandardQuestions = bookingStandardQuestions;
        this.mBookingCustomQuestions = bookingCustomQuestions;
        this.mPictureUrls = pictureUrls;
        this.mThumbnailUrls = thumbnailUrls;
        this.mXlPictureUrls = xlPictureUrls;
        this.mHostLanguages = hostLanguages;
        this.mHosts = hosts;
        this.mListYourSpacePricingMode = listYourSpacePricingMode;
        this.mOccupancyInfo = occupancyInfo;
        this.mReviewScores = reviewScores;
        this.mStatus = status;
        this.mWirelessInfo = wirelessInfo;
        this.mMonthlyPriceFactor = monthlyPriceFactor;
        this.mWeeklyPriceFactor = weeklyPriceFactor;
        this.mPricingQuote = pricingQuote;
        this.mSectionedDescription = sectionedDescription;
        this.mTranslatedSectionedDescription = translatedSectionedDescription;
        this.mSnoozeMode = snoozeMode;
        this.mSpecialOffer = specialOffer;
        this.mAccess = access;
        this.mAddress = address;
        this.mAdditionalHouseRules = additionalHouseRules;
        this.mApartment = apartment;
        this.mStreetAddress = streetAddress;
        this.mBedType = bedType;
        this.mBedTypeCategory = bedTypeCategory;
        this.mCancellationPolicy = cancellationPolicy;
        this.mCancellationPolicyKey = cancellationPolicyKey;
        this.mCity = city;
        this.mCheckInTimeStart = checkInTimeStart;
        this.mCheckInTimeEnd = checkInTimeEnd;
        this.mCountry = country;
        this.mCountryCode = countryCode;
        this.mDescription = description;
        this.mDescriptionLocale = descriptionLocale;
        this.mDirections = directions;
        this.mHostCheckInTimePhrase = hostCheckInTimePhrase;
        this.mHouseRules = houseRules;
        this.mHouseManual = houseManual;
        this.mLocation = location;
        this.mListingCurrency = listingCurrency;
        this.mListingNativeCurrency = listingNativeCurrency;
        this.mInstantBookingVisibility = instantBookingVisibility;
        this.mInstantBookingAllowedCategory = instantBookingAllowedCategory;
        this.mInteraction = interaction;
        this.mListingDescriptionAuthorType = listingDescriptionAuthorType;
        this.mName = name;
        this.mNameOrPlaceholderName = nameOrPlaceholderName;
        this.mUnscrubbedName = unscrubbedName;
        this.mProPhotoStatus = proPhotoStatus;
        this.mPublicAddress = publicAddress;
        this.mNativeCurrency = nativeCurrency;
        this.mNeighborhood = neighborhood;
        this.mNeighborhoodOverview = neighborhoodOverview;
        this.mNotes = notes;
        this.mPictureUrl = pictureUrl;
        this.mXlPictureUrl = xlPictureUrl;
        this.mPropertyType = propertyType;
        this.mPriceFormatted = priceFormatted;
        this.mRoomType = roomType;
        this.mRoomTypeKey = roomTypeKey;
        this.mSpace = space;
        this.mSpaceTypeDescription = spaceTypeDescription;
        this.mState = state;
        this.mSummary = summary;
        this.mUnscrubbedSummary = unscrubbedSummary;
        this.mSquareFeet = squareFeet;
        this.mThumbnailUrl = thumbnailUrl;
        this.mPreviewEncodedPng = previewEncodedPng;
        this.mTransit = transit;
        this.mZipCode = zipCode;
        this.mDistance = distance;
        this.mLicense = license;
        this.mTimeZoneName = timeZoneName;
        this.mInstantBookWelcomeMessage = instantBookWelcomeMessage;
        this.mLocalizedWirelessInfoDescription = localizedWirelessInfoDescription;
        this.mLocalizedCheckInTimeWindow = localizedCheckInTimeWindow;
        this.mLocalizedCheckOutTime = localizedCheckOutTime;
        this.mLocalizedCity = localizedCity;
        this.mLocalizedAdditionalHouseRules = localizedAdditionalHouseRules;
        this.mLocalizedAdditionalHouseRulesWithGoogleTranslate = localizedAdditionalHouseRulesWithGoogleTranslate;
        this.mListYourSpaceLastFinishedStepId = listYourSpaceLastFinishedStepId;
        this.mHostThumbnailUrl = hostThumbnailUrl;
        this.mHost = host;
        this.mPrimaryHost = primaryHost;
        this.mUserFlag = userFlag;
        this.mHasAvailability = hasAvailability;
        this.mHasBeenListed = hasBeenListed;
        this.mHasClosedInstantBookFtue = hasClosedInstantBookFtue;
        this.mInstantBookable = instantBookable;
        this.mInstantBookEligible = instantBookEligible;
        this.mUserDefinedLocation = userDefinedLocation;
        this.mLocationExact = locationExact;
        this.mIsPriceMonthly = isPriceMonthly;
        this.mHasAgreedToLegalTerms = hasAgreedToLegalTerms;
        this.mForceMobileLegalModal = forceMobileLegalModal;
        this.mRequiresLicense = requiresLicense;
        this.mInTotoArea = inTotoArea;
        this.mInstantBookEnabled = instantBookEnabled;
        this.mSmartPricingAvailable = smartPricingAvailable;
        this.mSmartPricingExtended = smartPricingExtended;
        this.mIsInstantBookingVisibilitySet = isInstantBookingVisibilitySet;
        this.mIsBusinessTravelReady = isBusinessTravelReady;
        this.mHasPaidAmenities = hasPaidAmenities;
        this.mIsAddressEditable = isAddressEditable;
        this.mIsLocationEditable = isLocationEditable;
        this.mFullyRefundable = fullyRefundable;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mPrice = price;
        this.mBathrooms = bathrooms;
        this.mStarRating = starRating;
        this.mReviewRatingAccuracy = reviewRatingAccuracy;
        this.mReviewRatingCheckin = reviewRatingCheckin;
        this.mReviewRatingCleanliness = reviewRatingCleanliness;
        this.mReviewRatingCommunication = reviewRatingCommunication;
        this.mReviewRatingLocation = reviewRatingLocation;
        this.mReviewRatingOverall = reviewRatingOverall;
        this.mReviewRatingValue = reviewRatingValue;
        this.mBedrooms = bedrooms;
        this.mBedCount = bedCount;
        this.mCleaningFee = cleaningFee;
        this.mExtraGuestPrice = extraGuestPrice;
        this.mGuestsIncluded = guestsIncluded;
        this.mListingPrice = listingPrice;
        this.mListingPriceNative = listingPriceNative;
        this.mListingWeeklyPriceNative = listingWeeklyPriceNative;
        this.mListingMonthlyPriceNative = listingMonthlyPriceNative;
        this.mListingWeekendPriceNative = listingWeekendPriceNative;
        this.mListingPriceForExtraPersonNative = listingPriceForExtraPersonNative;
        this.mMinNights = minNights;
        this.mMaxNights = maxNights;
        this.mMonthlyPriceNative = monthlyPriceNative;
        this.mPersonCapacity = personCapacity;
        this.mPictureCount = pictureCount;
        this.mRatedReviewsCount = ratedReviewsCount;
        this.mPriceNative = priceNative;
        this.mPropertyTypeId = propertyTypeId;
        this.mReadyForSelectStatus = readyForSelectStatus;
        this.mReviewsCount = reviewsCount;
        this.mPageViews = pageViews;
        this.mSecurityDeposit = securityDeposit;
        this.mWeeklyPriceNative = weeklyPriceNative;
        this.mWeekendPrice = weekendPrice;
        this.mStepsRemaining = stepsRemaining;
        this.mScrimColor = scrimColor;
        this.mId = id;
        this.mUserId = userId;
        this.mNeighborhoodId = neighborhoodId;
        this.mViewedAt = viewedAt;
    }

    protected GenListing() {
    }

    public AirDate getUnlistedAt() {
        return this.mUnlistedAt;
    }

    @JsonProperty("unlisted_at")
    public void setUnlistedAt(AirDate value) {
        this.mUnlistedAt = value;
    }

    public AutoPricing getAutoPricing() {
        return this.mAutoPricing;
    }

    @JsonProperty("ap_pricing")
    public void setAutoPricing(AutoPricing value) {
        this.mAutoPricing = value;
    }

    public BathroomType getBathroomType() {
        return this.mBathroomType;
    }

    public Boolean isIsNewListing() {
        return this.mIsNewListing;
    }

    @JsonProperty("is_new_listing")
    public void setIsNewListing(Boolean value) {
        this.mIsNewListing = value;
    }

    public Boolean isTotoOptIn() {
        return this.mTotoOptIn;
    }

    @JsonProperty("toto_opt_in")
    public void setTotoOptIn(Boolean value) {
        this.mTotoOptIn = value;
    }

    public Boolean isIsSuperhost() {
        return this.mIsSuperhost;
    }

    @JsonProperty("is_superhost")
    public void setIsSuperhost(Boolean value) {
        this.mIsSuperhost = value;
    }

    public CommercialHostInfo getCommercialHostInfo() {
        return this.mCommercialHostInfo;
    }

    @JsonProperty("commercial_host_info")
    public void setCommercialHostInfo(CommercialHostInfo value) {
        this.mCommercialHostInfo = value;
    }

    public DynamicPricingControl getDynamicPricingControls() {
        return this.mDynamicPricingControls;
    }

    @JsonProperty("dynamic_pricing_controls")
    public void setDynamicPricingControls(DynamicPricingControl value) {
        this.mDynamicPricingControls = value;
    }

    public GuestControls getGuestControls() {
        return this.mGuestControls;
    }

    @JsonProperty("guest_controls")
    public void setGuestControls(GuestControls value) {
        this.mGuestControls = value;
    }

    public C5990Guidebook getHostGuidebook() {
        return this.mHostGuidebook;
    }

    @JsonProperty("host_guidebook")
    public void setHostGuidebook(C5990Guidebook value) {
        this.mHostGuidebook = value;
    }

    public HomeCollectionApplication getCollectionsApplication() {
        return this.mCollectionsApplication;
    }

    @JsonProperty("homes_collections_application")
    public void setCollectionsApplication(HomeCollectionApplication value) {
        this.mCollectionsApplication = value;
    }

    public Integer getCheckInTime() {
        return this.mCheckInTime;
    }

    @JsonProperty("check_in_time")
    public void setCheckInTime(Integer value) {
        this.mCheckInTime = value;
    }

    public Integer getCheckOutTime() {
        return this.mCheckOutTime;
    }

    @JsonProperty("check_out_time")
    public void setCheckOutTime(Integer value) {
        this.mCheckOutTime = value;
    }

    public Integer getCheckInGuideStatus() {
        return this.mCheckInGuideStatus;
    }

    @JsonProperty("check_in_guide_status")
    public void setCheckInGuideStatus(Integer value) {
        this.mCheckInGuideStatus = value;
    }

    public Integer getInstantBookLeadTimeHours() {
        return this.mInstantBookLeadTimeHours;
    }

    @JsonProperty("instant_book_lead_time_hours")
    public void setInstantBookLeadTimeHours(Integer value) {
        this.mInstantBookLeadTimeHours = value;
    }

    public Integer getListingSecurityDepositNative() {
        return this.mListingSecurityDepositNative;
    }

    @JsonProperty("listing_security_deposit_native")
    public void setListingSecurityDepositNative(Integer value) {
        this.mListingSecurityDepositNative = value;
    }

    public Integer getListingCleaningFeeNative() {
        return this.mListingCleaningFeeNative;
    }

    @JsonProperty("listing_cleaning_fee_native")
    public void setListingCleaningFeeNative(Integer value) {
        this.mListingCleaningFeeNative = value;
    }

    public List<Amenity> getAmenities() {
        return this.mAmenities;
    }

    public List<CheckInInformation> getCheckInInformation() {
        return this.mCheckInInformation;
    }

    @JsonProperty("check_in_information")
    public void setCheckInInformation(List<CheckInInformation> value) {
        this.mCheckInInformation = value;
    }

    public List<DemandCounts> getDemandCounts() {
        return this.mDemandCounts;
    }

    @JsonProperty("demand_counts")
    public void setDemandCounts(List<DemandCounts> value) {
        this.mDemandCounts = value;
    }

    public List<Incentive> getIncentives() {
        return this.mIncentives;
    }

    @JsonProperty("incentives")
    public void setIncentives(List<Incentive> value) {
        this.mIncentives = value;
    }

    public List<Integer> getAmenityIds() {
        return this.mAmenityIds;
    }

    public List<ListingExpectation> getListingExpectations() {
        return this.mListingExpectations;
    }

    @JsonProperty("listing_expectations")
    public void setListingExpectations(List<ListingExpectation> value) {
        this.mListingExpectations = value;
    }

    public List<ListingExpectation> getLocalizedListingExpectations() {
        return this.mLocalizedListingExpectations;
    }

    @JsonProperty("localized_listing_expectations")
    public void setLocalizedListingExpectations(List<ListingExpectation> value) {
        this.mLocalizedListingExpectations = value;
    }

    public List<ListingPersonaInput> getListingPersonaInputs() {
        return this.mListingPersonaInputs;
    }

    @JsonProperty("listing_persona_responses")
    public void setListingPersonaInputs(List<ListingPersonaInput> value) {
        this.mListingPersonaInputs = value;
    }

    public List<ListingRoom> getListingRooms() {
        return this.mListingRooms;
    }

    @JsonProperty("listing_rooms")
    public void setListingRooms(List<ListingRoom> value) {
        this.mListingRooms = value;
    }

    public List<LocalizedCancellationPolicy> getAvailableCancellationPolicies() {
        return this.mAvailableCancellationPolicies;
    }

    @JsonProperty("available_cancellation_policies")
    public void setAvailableCancellationPolicies(List<LocalizedCancellationPolicy> value) {
        this.mAvailableCancellationPolicies = value;
    }

    public List<Long> getRemarketingIds() {
        return this.mRemarketingIds;
    }

    @JsonProperty("remarketing_ids")
    public void setRemarketingIds(List<Long> value) {
        this.mRemarketingIds = value;
    }

    public List<Photo> getPhotos() {
        return this.mPhotos;
    }

    @JsonProperty("photos")
    public void setPhotos(List<Photo> value) {
        this.mPhotos = value;
    }

    public List<PreBookingQuestion> getBookingStandardQuestions() {
        return this.mBookingStandardQuestions;
    }

    @JsonProperty("booking_standard_questions")
    public void setBookingStandardQuestions(List<PreBookingQuestion> value) {
        this.mBookingStandardQuestions = value;
    }

    public List<String> getBookingCustomQuestions() {
        return this.mBookingCustomQuestions;
    }

    @JsonProperty("booking_custom_questions")
    public void setBookingCustomQuestions(List<String> value) {
        this.mBookingCustomQuestions = value;
    }

    public List<String> getPictureUrls() {
        return this.mPictureUrls;
    }

    @JsonProperty("picture_urls")
    public void setPictureUrls(List<String> value) {
        this.mPictureUrls = value;
    }

    public List<String> getThumbnailUrls() {
        return this.mThumbnailUrls;
    }

    @JsonProperty("thumbnail_urls")
    public void setThumbnailUrls(List<String> value) {
        this.mThumbnailUrls = value;
    }

    public List<String> getXlPictureUrls() {
        return this.mXlPictureUrls;
    }

    @JsonProperty("xl_picture_urls")
    public void setXlPictureUrls(List<String> value) {
        this.mXlPictureUrls = value;
    }

    public List<String> getHostLanguages() {
        return this.mHostLanguages;
    }

    @JsonProperty("host_languages")
    public void setHostLanguages(List<String> value) {
        this.mHostLanguages = value;
    }

    public List<User> getHosts() {
        return this.mHosts;
    }

    @JsonProperty("hosts")
    public void setHosts(List<User> value) {
        this.mHosts = value;
    }

    public ListYourSpacePricingMode getListYourSpacePricingMode() {
        return this.mListYourSpacePricingMode;
    }

    public ListingOccupancyInfo getOccupancyInfo() {
        return this.mOccupancyInfo;
    }

    @JsonProperty("listing_occupancy_info")
    public void setOccupancyInfo(ListingOccupancyInfo value) {
        this.mOccupancyInfo = value;
    }

    public ListingReviewScores getReviewScores() {
        return this.mReviewScores;
    }

    @JsonProperty("review_scores")
    public void setReviewScores(ListingReviewScores value) {
        this.mReviewScores = value;
    }

    public ListingStatus getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(ListingStatus value) {
        this.mStatus = value;
    }

    public ListingWirelessInfo getWirelessInfo() {
        return this.mWirelessInfo;
    }

    public PriceFactor getMonthlyPriceFactor() {
        return this.mMonthlyPriceFactor;
    }

    @JsonProperty("monthly_price_factor")
    public void setMonthlyPriceFactor(PriceFactor value) {
        this.mMonthlyPriceFactor = value;
    }

    public PriceFactor getWeeklyPriceFactor() {
        return this.mWeeklyPriceFactor;
    }

    @JsonProperty("weekly_price_factor")
    public void setWeeklyPriceFactor(PriceFactor value) {
        this.mWeeklyPriceFactor = value;
    }

    public PricingQuote getPricingQuote() {
        return this.mPricingQuote;
    }

    @JsonProperty("pricing_quote")
    public void setPricingQuote(PricingQuote value) {
        this.mPricingQuote = value;
    }

    public SectionedListingDescription getSectionedDescription() {
        return this.mSectionedDescription;
    }

    @JsonProperty("sectioned_description")
    public void setSectionedDescription(SectionedListingDescription value) {
        this.mSectionedDescription = value;
    }

    public SectionedListingDescription getTranslatedSectionedDescription() {
        return this.mTranslatedSectionedDescription;
    }

    @JsonProperty("alternate_sectioned_description_for_guidebooks")
    public void setTranslatedSectionedDescription(SectionedListingDescription value) {
        this.mTranslatedSectionedDescription = value;
    }

    public SnoozeMode getSnoozeMode() {
        return this.mSnoozeMode;
    }

    @JsonProperty("snooze_mode")
    public void setSnoozeMode(SnoozeMode value) {
        this.mSnoozeMode = value;
    }

    public SpecialOffer getSpecialOffer() {
        return this.mSpecialOffer;
    }

    @JsonProperty("special_offer")
    public void setSpecialOffer(SpecialOffer value) {
        this.mSpecialOffer = value;
    }

    public String getAccess() {
        return this.mAccess;
    }

    @JsonProperty("access")
    public void setAccess(String value) {
        this.mAccess = value;
    }

    public String getAddress() {
        return this.mAddress;
    }

    @JsonProperty("address")
    public void setAddress(String value) {
        this.mAddress = value;
    }

    public String getAdditionalHouseRules() {
        return this.mAdditionalHouseRules;
    }

    @JsonProperty("additional_house_rules")
    public void setAdditionalHouseRules(String value) {
        this.mAdditionalHouseRules = value;
    }

    public String getApartment() {
        return this.mApartment;
    }

    @JsonProperty("apt")
    public void setApartment(String value) {
        this.mApartment = value;
    }

    public String getStreetAddress() {
        return this.mStreetAddress;
    }

    @JsonProperty("street")
    public void setStreetAddress(String value) {
        this.mStreetAddress = value;
    }

    public String getBedType() {
        return this.mBedType;
    }

    @JsonProperty("bed_type")
    public void setBedType(String value) {
        this.mBedType = value;
    }

    public String getBedTypeCategory() {
        return this.mBedTypeCategory;
    }

    @JsonProperty("bed_type_category")
    public void setBedTypeCategory(String value) {
        this.mBedTypeCategory = value;
    }

    public String getCancellationPolicy() {
        return this.mCancellationPolicy;
    }

    @JsonProperty("cancel_policy_short_str")
    public void setCancellationPolicy(String value) {
        this.mCancellationPolicy = value;
    }

    public String getCancellationPolicyKey() {
        return this.mCancellationPolicyKey;
    }

    @JsonProperty("cancellation_policy")
    public void setCancellationPolicyKey(String value) {
        this.mCancellationPolicyKey = value;
    }

    public String getCity() {
        return this.mCity;
    }

    @JsonProperty("city")
    public void setCity(String value) {
        this.mCity = value;
    }

    public String getCheckInTimeStart() {
        return this.mCheckInTimeStart;
    }

    @JsonProperty("check_in_time_start")
    public void setCheckInTimeStart(String value) {
        this.mCheckInTimeStart = value;
    }

    public String getCheckInTimeEnd() {
        return this.mCheckInTimeEnd;
    }

    @JsonProperty("check_in_time_end")
    public void setCheckInTimeEnd(String value) {
        this.mCheckInTimeEnd = value;
    }

    public String getCountry() {
        return this.mCountry;
    }

    @JsonProperty("country")
    public void setCountry(String value) {
        this.mCountry = value;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(String value) {
        this.mCountryCode = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getDescriptionLocale() {
        return this.mDescriptionLocale;
    }

    @JsonProperty("description_locale")
    public void setDescriptionLocale(String value) {
        this.mDescriptionLocale = value;
    }

    public String getDirections() {
        return this.mDirections;
    }

    @JsonProperty("directions")
    public void setDirections(String value) {
        this.mDirections = value;
    }

    public String getHostCheckInTimePhrase() {
        return this.mHostCheckInTimePhrase;
    }

    @JsonProperty("host_check_in_time_phrase_for_p4")
    public void setHostCheckInTimePhrase(String value) {
        this.mHostCheckInTimePhrase = value;
    }

    public String getHouseRules() {
        return this.mHouseRules;
    }

    @JsonProperty("house_rules")
    public void setHouseRules(String value) {
        this.mHouseRules = value;
    }

    public String getHouseManual() {
        return this.mHouseManual;
    }

    @JsonProperty("house_manual")
    public void setHouseManual(String value) {
        this.mHouseManual = value;
    }

    public String getLocation() {
        return this.mLocation;
    }

    @JsonProperty("smart_location")
    public void setLocation(String value) {
        this.mLocation = value;
    }

    public String getListingCurrency() {
        return this.mListingCurrency;
    }

    @JsonProperty("listing_currency")
    public void setListingCurrency(String value) {
        this.mListingCurrency = value;
    }

    public String getListingNativeCurrency() {
        return this.mListingNativeCurrency;
    }

    @JsonProperty("listing_native_currency")
    public void setListingNativeCurrency(String value) {
        this.mListingNativeCurrency = value;
    }

    public String getInstantBookingVisibility() {
        return this.mInstantBookingVisibility;
    }

    @JsonProperty("instant_booking_visibility")
    public void setInstantBookingVisibility(String value) {
        this.mInstantBookingVisibility = value;
    }

    public String getInstantBookingAllowedCategory() {
        return this.mInstantBookingAllowedCategory;
    }

    @JsonProperty("instant_booking_allowed_category")
    public void setInstantBookingAllowedCategory(String value) {
        this.mInstantBookingAllowedCategory = value;
    }

    public String getInteraction() {
        return this.mInteraction;
    }

    @JsonProperty("interaction")
    public void setInteraction(String value) {
        this.mInteraction = value;
    }

    public String getListingDescriptionAuthorType() {
        return this.mListingDescriptionAuthorType;
    }

    @JsonProperty("initial_description_author_type_for_guidebooks")
    public void setListingDescriptionAuthorType(String value) {
        this.mListingDescriptionAuthorType = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getNameOrPlaceholderName() {
        return this.mNameOrPlaceholderName;
    }

    @JsonProperty("name_or_placeholder_name")
    public void setNameOrPlaceholderName(String value) {
        this.mNameOrPlaceholderName = value;
    }

    public String getUnscrubbedName() {
        return this.mUnscrubbedName;
    }

    @JsonProperty("unscrubbed_name")
    public void setUnscrubbedName(String value) {
        this.mUnscrubbedName = value;
    }

    public String getProPhotoStatus() {
        return this.mProPhotoStatus;
    }

    @JsonProperty("photography_status")
    public void setProPhotoStatus(String value) {
        this.mProPhotoStatus = value;
    }

    public String getPublicAddress() {
        return this.mPublicAddress;
    }

    @JsonProperty("public_address")
    public void setPublicAddress(String value) {
        this.mPublicAddress = value;
    }

    public String getNativeCurrency() {
        return this.mNativeCurrency;
    }

    @JsonProperty("native_currency")
    public void setNativeCurrency(String value) {
        this.mNativeCurrency = value;
    }

    public String getNeighborhood() {
        return this.mNeighborhood;
    }

    @JsonProperty("neighborhood")
    public void setNeighborhood(String value) {
        this.mNeighborhood = value;
    }

    public String getNeighborhoodOverview() {
        return this.mNeighborhoodOverview;
    }

    @JsonProperty("neighborhood_overview")
    public void setNeighborhoodOverview(String value) {
        this.mNeighborhoodOverview = value;
    }

    public String getNotes() {
        return this.mNotes;
    }

    @JsonProperty("notes")
    public void setNotes(String value) {
        this.mNotes = value;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }

    @JsonProperty("picture_url")
    public void setPictureUrl(String value) {
        this.mPictureUrl = value;
    }

    public String getXlPictureUrl() {
        return this.mXlPictureUrl;
    }

    @JsonProperty("xl_picture_url")
    public void setXlPictureUrl(String value) {
        this.mXlPictureUrl = value;
    }

    public String getPropertyType() {
        return this.mPropertyType;
    }

    @JsonProperty("property_type")
    public void setPropertyType(String value) {
        this.mPropertyType = value;
    }

    public String getPriceFormatted() {
        return this.mPriceFormatted;
    }

    @JsonProperty("price_formatted")
    public void setPriceFormatted(String value) {
        this.mPriceFormatted = value;
    }

    public String getRoomType() {
        return this.mRoomType;
    }

    @JsonProperty("room_type")
    public void setRoomType(String value) {
        this.mRoomType = value;
    }

    public String getRoomTypeKey() {
        return this.mRoomTypeKey;
    }

    @JsonProperty("room_type_category")
    public void setRoomTypeKey(String value) {
        this.mRoomTypeKey = value;
    }

    public String getSpace() {
        return this.mSpace;
    }

    @JsonProperty("space")
    public void setSpace(String value) {
        this.mSpace = value;
    }

    public String getSpaceTypeDescription() {
        return this.mSpaceTypeDescription;
    }

    @JsonProperty("space_type")
    public void setSpaceTypeDescription(String value) {
        this.mSpaceTypeDescription = value;
    }

    public String getState() {
        return this.mState;
    }

    @JsonProperty("state")
    public void setState(String value) {
        this.mState = value;
    }

    public String getSummary() {
        return this.mSummary;
    }

    @JsonProperty("summary")
    public void setSummary(String value) {
        this.mSummary = value;
    }

    public String getUnscrubbedSummary() {
        return this.mUnscrubbedSummary;
    }

    @JsonProperty("unscrubbed_summary")
    public void setUnscrubbedSummary(String value) {
        this.mUnscrubbedSummary = value;
    }

    public String getSquareFeet() {
        return this.mSquareFeet;
    }

    @JsonProperty("square_feet")
    public void setSquareFeet(String value) {
        this.mSquareFeet = value;
    }

    public String getThumbnailUrl() {
        return this.mThumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String value) {
        this.mThumbnailUrl = value;
    }

    public String getPreviewEncodedPng() {
        return this.mPreviewEncodedPng;
    }

    @JsonProperty("preview_encoded_png")
    public void setPreviewEncodedPng(String value) {
        this.mPreviewEncodedPng = value;
    }

    public String getTransit() {
        return this.mTransit;
    }

    @JsonProperty("transit")
    public void setTransit(String value) {
        this.mTransit = value;
    }

    public String getZipCode() {
        return this.mZipCode;
    }

    @JsonProperty("zipcode")
    public void setZipCode(String value) {
        this.mZipCode = value;
    }

    public String getDistance() {
        return this.mDistance;
    }

    @JsonProperty("distance")
    public void setDistance(String value) {
        this.mDistance = value;
    }

    public String getLicense() {
        return this.mLicense;
    }

    @JsonProperty("license")
    public void setLicense(String value) {
        this.mLicense = value;
    }

    public String getTimeZoneName() {
        return this.mTimeZoneName;
    }

    @JsonProperty("time_zone_name")
    public void setTimeZoneName(String value) {
        this.mTimeZoneName = value;
    }

    public String getInstantBookWelcomeMessage() {
        return this.mInstantBookWelcomeMessage;
    }

    @JsonProperty("instant_book_welcome_message")
    public void setInstantBookWelcomeMessage(String value) {
        this.mInstantBookWelcomeMessage = value;
    }

    public String getLocalizedWirelessInfoDescription() {
        return this.mLocalizedWirelessInfoDescription;
    }

    @JsonProperty("localized_wireless_info_description")
    public void setLocalizedWirelessInfoDescription(String value) {
        this.mLocalizedWirelessInfoDescription = value;
    }

    public String getLocalizedCheckInTimeWindow() {
        return this.mLocalizedCheckInTimeWindow;
    }

    @JsonProperty("localized_check_in_time_window")
    public void setLocalizedCheckInTimeWindow(String value) {
        this.mLocalizedCheckInTimeWindow = value;
    }

    public String getLocalizedCheckOutTime() {
        return this.mLocalizedCheckOutTime;
    }

    @JsonProperty("localized_check_out_time")
    public void setLocalizedCheckOutTime(String value) {
        this.mLocalizedCheckOutTime = value;
    }

    public String getLocalizedCity() {
        return this.mLocalizedCity;
    }

    @JsonProperty("localized_city")
    public void setLocalizedCity(String value) {
        this.mLocalizedCity = value;
    }

    public String getLocalizedAdditionalHouseRules() {
        return this.mLocalizedAdditionalHouseRules;
    }

    @JsonProperty("localized_additional_house_rules")
    public void setLocalizedAdditionalHouseRules(String value) {
        this.mLocalizedAdditionalHouseRules = value;
    }

    public String getLocalizedAdditionalHouseRulesWithGoogleTranslate() {
        return this.mLocalizedAdditionalHouseRulesWithGoogleTranslate;
    }

    @JsonProperty("localized_additional_house_rules_with_google_translate")
    public void setLocalizedAdditionalHouseRulesWithGoogleTranslate(String value) {
        this.mLocalizedAdditionalHouseRulesWithGoogleTranslate = value;
    }

    public String getListYourSpaceLastFinishedStepId() {
        return this.mListYourSpaceLastFinishedStepId;
    }

    @JsonProperty("list_your_space_last_finished_step_id")
    public void setListYourSpaceLastFinishedStepId(String value) {
        this.mListYourSpaceLastFinishedStepId = value;
    }

    public String getHostThumbnailUrl() {
        return this.mHostThumbnailUrl;
    }

    @JsonProperty("host_thumbnail_url")
    public void setHostThumbnailUrl(String value) {
        this.mHostThumbnailUrl = value;
    }

    public User getHost() {
        return this.mHost;
    }

    public User getPrimaryHost() {
        return this.mPrimaryHost;
    }

    @JsonProperty("primary_host")
    public void setPrimaryHost(User value) {
        this.mPrimaryHost = value;
    }

    public UserFlag getUserFlag() {
        return this.mUserFlag;
    }

    @JsonProperty("user_flag")
    public void setUserFlag(UserFlag value) {
        this.mUserFlag = value;
    }

    public boolean hasAvailability() {
        return this.mHasAvailability;
    }

    @JsonProperty("has_availability")
    public void setHasAvailability(boolean value) {
        this.mHasAvailability = value;
    }

    public boolean hasBeenListed() {
        return this.mHasBeenListed;
    }

    @JsonProperty("has_ever_been_available")
    public void setHasBeenListed(boolean value) {
        this.mHasBeenListed = value;
    }

    public boolean hasClosedInstantBookFtue() {
        return this.mHasClosedInstantBookFtue;
    }

    @JsonProperty("has_closed_instant_book_ftue")
    public void setHasClosedInstantBookFtue(boolean value) {
        this.mHasClosedInstantBookFtue = value;
    }

    public boolean isInstantBookable() {
        return this.mInstantBookable;
    }

    @JsonProperty("instant_bookable")
    public void setInstantBookable(boolean value) {
        this.mInstantBookable = value;
    }

    public boolean isInstantBookEligible() {
        return this.mInstantBookEligible;
    }

    @JsonProperty("instant_book_eligible")
    public void setInstantBookEligible(boolean value) {
        this.mInstantBookEligible = value;
    }

    public boolean isUserDefinedLocation() {
        return this.mUserDefinedLocation;
    }

    @JsonProperty("user_defined_location")
    public void setUserDefinedLocation(boolean value) {
        this.mUserDefinedLocation = value;
    }

    public boolean isLocationExact() {
        return this.mLocationExact;
    }

    @JsonProperty("is_location_exact")
    public void setLocationExact(boolean value) {
        this.mLocationExact = value;
    }

    public boolean isPriceMonthly() {
        return this.mIsPriceMonthly;
    }

    @JsonProperty("is_price_monthly")
    public void setIsPriceMonthly(boolean value) {
        this.mIsPriceMonthly = value;
    }

    public boolean hasAgreedToLegalTerms() {
        return this.mHasAgreedToLegalTerms;
    }

    @JsonProperty("has_agreed_to_legal_terms")
    public void setHasAgreedToLegalTerms(boolean value) {
        this.mHasAgreedToLegalTerms = value;
    }

    public boolean isForceMobileLegalModal() {
        return this.mForceMobileLegalModal;
    }

    @JsonProperty("force_mobile_legal_modal")
    public void setForceMobileLegalModal(boolean value) {
        this.mForceMobileLegalModal = value;
    }

    public boolean isRequiresLicense() {
        return this.mRequiresLicense;
    }

    @JsonProperty("requires_license")
    public void setRequiresLicense(boolean value) {
        this.mRequiresLicense = value;
    }

    public boolean isInTotoArea() {
        return this.mInTotoArea;
    }

    @JsonProperty("in_toto_area")
    public void setInTotoArea(boolean value) {
        this.mInTotoArea = value;
    }

    public boolean isInstantBookEnabled() {
        return this.mInstantBookEnabled;
    }

    @JsonProperty("instant_book_enabled")
    public void setInstantBookEnabled(boolean value) {
        this.mInstantBookEnabled = value;
    }

    public boolean isSmartPricingAvailable() {
        return this.mSmartPricingAvailable;
    }

    @JsonProperty("smart_pricing_available")
    public void setSmartPricingAvailable(boolean value) {
        this.mSmartPricingAvailable = value;
    }

    public boolean isSmartPricingExtended() {
        return this.mSmartPricingExtended;
    }

    @JsonProperty("smart_pricing_extended")
    public void setSmartPricingExtended(boolean value) {
        this.mSmartPricingExtended = value;
    }

    public boolean isInstantBookingVisibilitySet() {
        return this.mIsInstantBookingVisibilitySet;
    }

    @JsonProperty("instant_booking_visibility_set")
    public void setIsInstantBookingVisibilitySet(boolean value) {
        this.mIsInstantBookingVisibilitySet = value;
    }

    public boolean isBusinessTravelReady() {
        return this.mIsBusinessTravelReady;
    }

    @JsonProperty("is_business_travel_ready")
    public void setIsBusinessTravelReady(boolean value) {
        this.mIsBusinessTravelReady = value;
    }

    public boolean hasPaidAmenities() {
        return this.mHasPaidAmenities;
    }

    @JsonProperty("has_paid_amenities")
    public void setHasPaidAmenities(boolean value) {
        this.mHasPaidAmenities = value;
    }

    public boolean isAddressEditable() {
        return this.mIsAddressEditable;
    }

    @JsonProperty("is_address_editable")
    public void setIsAddressEditable(boolean value) {
        this.mIsAddressEditable = value;
    }

    public boolean isLocationEditable() {
        return this.mIsLocationEditable;
    }

    @JsonProperty("is_location_editable")
    public void setIsLocationEditable(boolean value) {
        this.mIsLocationEditable = value;
    }

    public boolean isFullyRefundable() {
        return this.mFullyRefundable;
    }

    @JsonProperty("fully_refundable")
    public void setFullyRefundable(boolean value) {
        this.mFullyRefundable = value;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    @JsonProperty("lat")
    public void setLatitude(double value) {
        this.mLatitude = value;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    @JsonProperty("lng")
    public void setLongitude(double value) {
        this.mLongitude = value;
    }

    public double getPrice() {
        return this.mPrice;
    }

    @JsonProperty("price")
    public void setPrice(double value) {
        this.mPrice = value;
    }

    public float getBathrooms() {
        return this.mBathrooms;
    }

    @JsonProperty("bathrooms")
    public void setBathrooms(float value) {
        this.mBathrooms = value;
    }

    public float getStarRating() {
        return this.mStarRating;
    }

    @JsonProperty("star_rating")
    public void setStarRating(float value) {
        this.mStarRating = value;
    }

    public float getReviewRatingAccuracy() {
        return this.mReviewRatingAccuracy;
    }

    @JsonProperty("review_rating_accuracy")
    public void setReviewRatingAccuracy(float value) {
        this.mReviewRatingAccuracy = value;
    }

    public float getReviewRatingCheckin() {
        return this.mReviewRatingCheckin;
    }

    @JsonProperty("review_rating_checkin")
    public void setReviewRatingCheckin(float value) {
        this.mReviewRatingCheckin = value;
    }

    public float getReviewRatingCleanliness() {
        return this.mReviewRatingCleanliness;
    }

    @JsonProperty("review_rating_cleanliness")
    public void setReviewRatingCleanliness(float value) {
        this.mReviewRatingCleanliness = value;
    }

    public float getReviewRatingCommunication() {
        return this.mReviewRatingCommunication;
    }

    @JsonProperty("review_rating_communication")
    public void setReviewRatingCommunication(float value) {
        this.mReviewRatingCommunication = value;
    }

    public float getReviewRatingLocation() {
        return this.mReviewRatingLocation;
    }

    @JsonProperty("review_rating_location")
    public void setReviewRatingLocation(float value) {
        this.mReviewRatingLocation = value;
    }

    public float getReviewRatingOverall() {
        return this.mReviewRatingOverall;
    }

    @JsonProperty("star_rating_overall")
    public void setReviewRatingOverall(float value) {
        this.mReviewRatingOverall = value;
    }

    public float getReviewRatingValue() {
        return this.mReviewRatingValue;
    }

    @JsonProperty("review_rating_value")
    public void setReviewRatingValue(float value) {
        this.mReviewRatingValue = value;
    }

    public int getBedrooms() {
        return this.mBedrooms;
    }

    @JsonProperty("bedrooms")
    public void setBedrooms(int value) {
        this.mBedrooms = value;
    }

    public int getBedCount() {
        return this.mBedCount;
    }

    @JsonProperty("beds")
    public void setBedCount(int value) {
        this.mBedCount = value;
    }

    public int getCleaningFee() {
        return this.mCleaningFee;
    }

    @JsonProperty("cleaning_fee_native")
    public void setCleaningFee(int value) {
        this.mCleaningFee = value;
    }

    public int getExtraGuestPrice() {
        return this.mExtraGuestPrice;
    }

    @JsonProperty("price_for_extra_person_native")
    public void setExtraGuestPrice(int value) {
        this.mExtraGuestPrice = value;
    }

    public int getGuestsIncluded() {
        return this.mGuestsIncluded;
    }

    @JsonProperty("guests_included")
    public void setGuestsIncluded(int value) {
        this.mGuestsIncluded = value;
    }

    public int getListingPrice() {
        return this.mListingPrice;
    }

    @JsonProperty("listing_price")
    public void setListingPrice(int value) {
        this.mListingPrice = value;
    }

    public int getListingPriceNative() {
        return this.mListingPriceNative;
    }

    @JsonProperty("listing_price_native")
    public void setListingPriceNative(int value) {
        this.mListingPriceNative = value;
    }

    public int getListingWeeklyPriceNative() {
        return this.mListingWeeklyPriceNative;
    }

    @JsonProperty("listing_weekly_price_native")
    public void setListingWeeklyPriceNative(int value) {
        this.mListingWeeklyPriceNative = value;
    }

    public int getListingMonthlyPriceNative() {
        return this.mListingMonthlyPriceNative;
    }

    @JsonProperty("listing_monthly_price_native")
    public void setListingMonthlyPriceNative(int value) {
        this.mListingMonthlyPriceNative = value;
    }

    public int getListingWeekendPriceNative() {
        return this.mListingWeekendPriceNative;
    }

    @JsonProperty("listing_weekend_price_native")
    public void setListingWeekendPriceNative(int value) {
        this.mListingWeekendPriceNative = value;
    }

    public int getListingPriceForExtraPersonNative() {
        return this.mListingPriceForExtraPersonNative;
    }

    @JsonProperty("listing_price_for_extra_person_native")
    public void setListingPriceForExtraPersonNative(int value) {
        this.mListingPriceForExtraPersonNative = value;
    }

    public int getMinNights() {
        return this.mMinNights;
    }

    @JsonProperty("min_nights")
    public void setMinNights(int value) {
        this.mMinNights = value;
    }

    public int getMaxNights() {
        return this.mMaxNights;
    }

    @JsonProperty("max_nights")
    public void setMaxNights(int value) {
        this.mMaxNights = value;
    }

    public int getMonthlyPriceNative() {
        return this.mMonthlyPriceNative;
    }

    @JsonProperty("monthly_price_native")
    public void setMonthlyPriceNative(int value) {
        this.mMonthlyPriceNative = value;
    }

    public int getPersonCapacity() {
        return this.mPersonCapacity;
    }

    @JsonProperty("person_capacity")
    public void setPersonCapacity(int value) {
        this.mPersonCapacity = value;
    }

    public int getPictureCount() {
        return this.mPictureCount;
    }

    @JsonProperty("picture_count")
    public void setPictureCount(int value) {
        this.mPictureCount = value;
    }

    public int getRatedReviewsCount() {
        return this.mRatedReviewsCount;
    }

    @JsonProperty("rated_reviews_count")
    public void setRatedReviewsCount(int value) {
        this.mRatedReviewsCount = value;
    }

    public int getPriceNative() {
        return this.mPriceNative;
    }

    @JsonProperty("price_native")
    public void setPriceNative(int value) {
        this.mPriceNative = value;
    }

    public int getPropertyTypeId() {
        return this.mPropertyTypeId;
    }

    @JsonProperty("property_type_id")
    public void setPropertyTypeId(int value) {
        this.mPropertyTypeId = value;
    }

    public int getReadyForSelectStatus() {
        return this.mReadyForSelectStatus;
    }

    @JsonProperty("ready_for_select_status")
    public void setReadyForSelectStatus(int value) {
        this.mReadyForSelectStatus = value;
    }

    public int getReviewsCount() {
        return this.mReviewsCount;
    }

    @JsonProperty("reviews_count")
    public void setReviewsCount(int value) {
        this.mReviewsCount = value;
    }

    public int getPageViews() {
        return this.mPageViews;
    }

    @JsonProperty("page_views")
    public void setPageViews(int value) {
        this.mPageViews = value;
    }

    public int getSecurityDeposit() {
        return this.mSecurityDeposit;
    }

    @JsonProperty("security_deposit_native")
    public void setSecurityDeposit(int value) {
        this.mSecurityDeposit = value;
    }

    public int getWeeklyPriceNative() {
        return this.mWeeklyPriceNative;
    }

    @JsonProperty("weekly_price_native")
    public void setWeeklyPriceNative(int value) {
        this.mWeeklyPriceNative = value;
    }

    public int getWeekendPrice() {
        return this.mWeekendPrice;
    }

    @JsonProperty("weekend_price_native")
    public void setWeekendPrice(int value) {
        this.mWeekendPrice = value;
    }

    public int getStepsRemaining() {
        return this.mStepsRemaining;
    }

    @JsonProperty("steps_remaining")
    public void setStepsRemaining(int value) {
        this.mStepsRemaining = value;
    }

    public int getScrimColor() {
        return this.mScrimColor;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(long value) {
        this.mUserId = value;
    }

    public long getNeighborhoodId() {
        return this.mNeighborhoodId;
    }

    @JsonProperty("neighborhood_id")
    public void setNeighborhoodId(long value) {
        this.mNeighborhoodId = value;
    }

    public long getViewedAt() {
        return this.mViewedAt;
    }

    @JsonProperty("viewed_at")
    public void setViewedAt(long value) {
        this.mViewedAt = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mUnlistedAt, 0);
        parcel.writeParcelable(this.mAutoPricing, 0);
        parcel.writeSerializable(this.mBathroomType);
        parcel.writeValue(this.mIsNewListing);
        parcel.writeValue(this.mTotoOptIn);
        parcel.writeValue(this.mIsSuperhost);
        parcel.writeParcelable(this.mCommercialHostInfo, 0);
        parcel.writeParcelable(this.mDynamicPricingControls, 0);
        parcel.writeParcelable(this.mGuestControls, 0);
        parcel.writeParcelable(this.mHostGuidebook, 0);
        parcel.writeParcelable(this.mCollectionsApplication, 0);
        parcel.writeValue(this.mCheckInTime);
        parcel.writeValue(this.mCheckOutTime);
        parcel.writeValue(this.mCheckInGuideStatus);
        parcel.writeValue(this.mInstantBookLeadTimeHours);
        parcel.writeValue(this.mListingSecurityDepositNative);
        parcel.writeValue(this.mListingCleaningFeeNative);
        parcel.writeTypedList(this.mAmenities);
        parcel.writeTypedList(this.mCheckInInformation);
        parcel.writeTypedList(this.mDemandCounts);
        parcel.writeTypedList(this.mIncentives);
        parcel.writeValue(this.mAmenityIds);
        parcel.writeTypedList(this.mListingExpectations);
        parcel.writeTypedList(this.mLocalizedListingExpectations);
        parcel.writeTypedList(this.mListingPersonaInputs);
        parcel.writeTypedList(this.mListingRooms);
        parcel.writeTypedList(this.mAvailableCancellationPolicies);
        parcel.writeValue(this.mRemarketingIds);
        parcel.writeTypedList(this.mPhotos);
        parcel.writeTypedList(this.mBookingStandardQuestions);
        parcel.writeStringList(this.mBookingCustomQuestions);
        parcel.writeStringList(this.mPictureUrls);
        parcel.writeStringList(this.mThumbnailUrls);
        parcel.writeStringList(this.mXlPictureUrls);
        parcel.writeStringList(this.mHostLanguages);
        parcel.writeTypedList(this.mHosts);
        parcel.writeParcelable(this.mListYourSpacePricingMode, 0);
        parcel.writeParcelable(this.mOccupancyInfo, 0);
        parcel.writeParcelable(this.mReviewScores, 0);
        parcel.writeParcelable(this.mStatus, 0);
        parcel.writeParcelable(this.mWirelessInfo, 0);
        parcel.writeParcelable(this.mMonthlyPriceFactor, 0);
        parcel.writeParcelable(this.mWeeklyPriceFactor, 0);
        parcel.writeParcelable(this.mPricingQuote, 0);
        parcel.writeParcelable(this.mSectionedDescription, 0);
        parcel.writeParcelable(this.mTranslatedSectionedDescription, 0);
        parcel.writeParcelable(this.mSnoozeMode, 0);
        parcel.writeParcelable(this.mSpecialOffer, 0);
        parcel.writeString(this.mAccess);
        parcel.writeString(this.mAddress);
        parcel.writeString(this.mAdditionalHouseRules);
        parcel.writeString(this.mApartment);
        parcel.writeString(this.mStreetAddress);
        parcel.writeString(this.mBedType);
        parcel.writeString(this.mBedTypeCategory);
        parcel.writeString(this.mCancellationPolicy);
        parcel.writeString(this.mCancellationPolicyKey);
        parcel.writeString(this.mCity);
        parcel.writeString(this.mCheckInTimeStart);
        parcel.writeString(this.mCheckInTimeEnd);
        parcel.writeString(this.mCountry);
        parcel.writeString(this.mCountryCode);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mDescriptionLocale);
        parcel.writeString(this.mDirections);
        parcel.writeString(this.mHostCheckInTimePhrase);
        parcel.writeString(this.mHouseRules);
        parcel.writeString(this.mHouseManual);
        parcel.writeString(this.mLocation);
        parcel.writeString(this.mListingCurrency);
        parcel.writeString(this.mListingNativeCurrency);
        parcel.writeString(this.mInstantBookingVisibility);
        parcel.writeString(this.mInstantBookingAllowedCategory);
        parcel.writeString(this.mInteraction);
        parcel.writeString(this.mListingDescriptionAuthorType);
        parcel.writeString(this.mName);
        parcel.writeString(this.mNameOrPlaceholderName);
        parcel.writeString(this.mUnscrubbedName);
        parcel.writeString(this.mProPhotoStatus);
        parcel.writeString(this.mPublicAddress);
        parcel.writeString(this.mNativeCurrency);
        parcel.writeString(this.mNeighborhood);
        parcel.writeString(this.mNeighborhoodOverview);
        parcel.writeString(this.mNotes);
        parcel.writeString(this.mPictureUrl);
        parcel.writeString(this.mXlPictureUrl);
        parcel.writeString(this.mPropertyType);
        parcel.writeString(this.mPriceFormatted);
        parcel.writeString(this.mRoomType);
        parcel.writeString(this.mRoomTypeKey);
        parcel.writeString(this.mSpace);
        parcel.writeString(this.mSpaceTypeDescription);
        parcel.writeString(this.mState);
        parcel.writeString(this.mSummary);
        parcel.writeString(this.mUnscrubbedSummary);
        parcel.writeString(this.mSquareFeet);
        parcel.writeString(this.mThumbnailUrl);
        parcel.writeString(this.mPreviewEncodedPng);
        parcel.writeString(this.mTransit);
        parcel.writeString(this.mZipCode);
        parcel.writeString(this.mDistance);
        parcel.writeString(this.mLicense);
        parcel.writeString(this.mTimeZoneName);
        parcel.writeString(this.mInstantBookWelcomeMessage);
        parcel.writeString(this.mLocalizedWirelessInfoDescription);
        parcel.writeString(this.mLocalizedCheckInTimeWindow);
        parcel.writeString(this.mLocalizedCheckOutTime);
        parcel.writeString(this.mLocalizedCity);
        parcel.writeString(this.mLocalizedAdditionalHouseRules);
        parcel.writeString(this.mLocalizedAdditionalHouseRulesWithGoogleTranslate);
        parcel.writeString(this.mListYourSpaceLastFinishedStepId);
        parcel.writeString(this.mHostThumbnailUrl);
        parcel.writeParcelable(this.mHost, 0);
        parcel.writeParcelable(this.mPrimaryHost, 0);
        parcel.writeParcelable(this.mUserFlag, 0);
        parcel.writeBooleanArray(new boolean[]{this.mHasAvailability, this.mHasBeenListed, this.mHasClosedInstantBookFtue, this.mInstantBookable, this.mInstantBookEligible, this.mUserDefinedLocation, this.mLocationExact, this.mIsPriceMonthly, this.mHasAgreedToLegalTerms, this.mForceMobileLegalModal, this.mRequiresLicense, this.mInTotoArea, this.mInstantBookEnabled, this.mSmartPricingAvailable, this.mSmartPricingExtended, this.mIsInstantBookingVisibilitySet, this.mIsBusinessTravelReady, this.mHasPaidAmenities, this.mIsAddressEditable, this.mIsLocationEditable, this.mFullyRefundable});
        parcel.writeDouble(this.mLatitude);
        parcel.writeDouble(this.mLongitude);
        parcel.writeDouble(this.mPrice);
        parcel.writeFloat(this.mBathrooms);
        parcel.writeFloat(this.mStarRating);
        parcel.writeFloat(this.mReviewRatingAccuracy);
        parcel.writeFloat(this.mReviewRatingCheckin);
        parcel.writeFloat(this.mReviewRatingCleanliness);
        parcel.writeFloat(this.mReviewRatingCommunication);
        parcel.writeFloat(this.mReviewRatingLocation);
        parcel.writeFloat(this.mReviewRatingOverall);
        parcel.writeFloat(this.mReviewRatingValue);
        parcel.writeInt(this.mBedrooms);
        parcel.writeInt(this.mBedCount);
        parcel.writeInt(this.mCleaningFee);
        parcel.writeInt(this.mExtraGuestPrice);
        parcel.writeInt(this.mGuestsIncluded);
        parcel.writeInt(this.mListingPrice);
        parcel.writeInt(this.mListingPriceNative);
        parcel.writeInt(this.mListingWeeklyPriceNative);
        parcel.writeInt(this.mListingMonthlyPriceNative);
        parcel.writeInt(this.mListingWeekendPriceNative);
        parcel.writeInt(this.mListingPriceForExtraPersonNative);
        parcel.writeInt(this.mMinNights);
        parcel.writeInt(this.mMaxNights);
        parcel.writeInt(this.mMonthlyPriceNative);
        parcel.writeInt(this.mPersonCapacity);
        parcel.writeInt(this.mPictureCount);
        parcel.writeInt(this.mRatedReviewsCount);
        parcel.writeInt(this.mPriceNative);
        parcel.writeInt(this.mPropertyTypeId);
        parcel.writeInt(this.mReadyForSelectStatus);
        parcel.writeInt(this.mReviewsCount);
        parcel.writeInt(this.mPageViews);
        parcel.writeInt(this.mSecurityDeposit);
        parcel.writeInt(this.mWeeklyPriceNative);
        parcel.writeInt(this.mWeekendPrice);
        parcel.writeInt(this.mStepsRemaining);
        parcel.writeInt(this.mScrimColor);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mUserId);
        parcel.writeLong(this.mNeighborhoodId);
        parcel.writeLong(this.mViewedAt);
    }

    public void readFromParcel(Parcel source) {
        this.mUnlistedAt = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mAutoPricing = (AutoPricing) source.readParcelable(AutoPricing.class.getClassLoader());
        this.mBathroomType = (BathroomType) source.readSerializable();
        this.mIsNewListing = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mTotoOptIn = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mIsSuperhost = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mCommercialHostInfo = (CommercialHostInfo) source.readParcelable(CommercialHostInfo.class.getClassLoader());
        this.mDynamicPricingControls = (DynamicPricingControl) source.readParcelable(DynamicPricingControl.class.getClassLoader());
        this.mGuestControls = (GuestControls) source.readParcelable(GuestControls.class.getClassLoader());
        this.mHostGuidebook = (C5990Guidebook) source.readParcelable(C5990Guidebook.class.getClassLoader());
        this.mCollectionsApplication = (HomeCollectionApplication) source.readParcelable(HomeCollectionApplication.class.getClassLoader());
        this.mCheckInTime = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mCheckOutTime = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mCheckInGuideStatus = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mInstantBookLeadTimeHours = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mListingSecurityDepositNative = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mListingCleaningFeeNative = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mAmenities = source.createTypedArrayList(Amenity.CREATOR);
        this.mCheckInInformation = source.createTypedArrayList(CheckInInformation.CREATOR);
        this.mDemandCounts = source.createTypedArrayList(DemandCounts.CREATOR);
        this.mIncentives = source.createTypedArrayList(Incentive.CREATOR);
        this.mAmenityIds = (List) source.readValue(null);
        this.mListingExpectations = source.createTypedArrayList(ListingExpectation.CREATOR);
        this.mLocalizedListingExpectations = source.createTypedArrayList(ListingExpectation.CREATOR);
        this.mListingPersonaInputs = source.createTypedArrayList(ListingPersonaInput.CREATOR);
        this.mListingRooms = source.createTypedArrayList(ListingRoom.CREATOR);
        this.mAvailableCancellationPolicies = source.createTypedArrayList(LocalizedCancellationPolicy.CREATOR);
        this.mRemarketingIds = (List) source.readValue(null);
        this.mPhotos = source.createTypedArrayList(Photo.CREATOR);
        this.mBookingStandardQuestions = source.createTypedArrayList(PreBookingQuestion.CREATOR);
        this.mBookingCustomQuestions = source.createStringArrayList();
        this.mPictureUrls = source.createStringArrayList();
        this.mThumbnailUrls = source.createStringArrayList();
        this.mXlPictureUrls = source.createStringArrayList();
        this.mHostLanguages = source.createStringArrayList();
        this.mHosts = source.createTypedArrayList(User.CREATOR);
        this.mListYourSpacePricingMode = (ListYourSpacePricingMode) source.readParcelable(ListYourSpacePricingMode.class.getClassLoader());
        this.mOccupancyInfo = (ListingOccupancyInfo) source.readParcelable(ListingOccupancyInfo.class.getClassLoader());
        this.mReviewScores = (ListingReviewScores) source.readParcelable(ListingReviewScores.class.getClassLoader());
        this.mStatus = (ListingStatus) source.readParcelable(ListingStatus.class.getClassLoader());
        this.mWirelessInfo = (ListingWirelessInfo) source.readParcelable(ListingWirelessInfo.class.getClassLoader());
        this.mMonthlyPriceFactor = (PriceFactor) source.readParcelable(PriceFactor.class.getClassLoader());
        this.mWeeklyPriceFactor = (PriceFactor) source.readParcelable(PriceFactor.class.getClassLoader());
        this.mPricingQuote = (PricingQuote) source.readParcelable(PricingQuote.class.getClassLoader());
        this.mSectionedDescription = (SectionedListingDescription) source.readParcelable(SectionedListingDescription.class.getClassLoader());
        this.mTranslatedSectionedDescription = (SectionedListingDescription) source.readParcelable(SectionedListingDescription.class.getClassLoader());
        this.mSnoozeMode = (SnoozeMode) source.readParcelable(SnoozeMode.class.getClassLoader());
        this.mSpecialOffer = (SpecialOffer) source.readParcelable(SpecialOffer.class.getClassLoader());
        this.mAccess = source.readString();
        this.mAddress = source.readString();
        this.mAdditionalHouseRules = source.readString();
        this.mApartment = source.readString();
        this.mStreetAddress = source.readString();
        this.mBedType = source.readString();
        this.mBedTypeCategory = source.readString();
        this.mCancellationPolicy = source.readString();
        this.mCancellationPolicyKey = source.readString();
        this.mCity = source.readString();
        this.mCheckInTimeStart = source.readString();
        this.mCheckInTimeEnd = source.readString();
        this.mCountry = source.readString();
        this.mCountryCode = source.readString();
        this.mDescription = source.readString();
        this.mDescriptionLocale = source.readString();
        this.mDirections = source.readString();
        this.mHostCheckInTimePhrase = source.readString();
        this.mHouseRules = source.readString();
        this.mHouseManual = source.readString();
        this.mLocation = source.readString();
        this.mListingCurrency = source.readString();
        this.mListingNativeCurrency = source.readString();
        this.mInstantBookingVisibility = source.readString();
        this.mInstantBookingAllowedCategory = source.readString();
        this.mInteraction = source.readString();
        this.mListingDescriptionAuthorType = source.readString();
        this.mName = source.readString();
        this.mNameOrPlaceholderName = source.readString();
        this.mUnscrubbedName = source.readString();
        this.mProPhotoStatus = source.readString();
        this.mPublicAddress = source.readString();
        this.mNativeCurrency = source.readString();
        this.mNeighborhood = source.readString();
        this.mNeighborhoodOverview = source.readString();
        this.mNotes = source.readString();
        this.mPictureUrl = source.readString();
        this.mXlPictureUrl = source.readString();
        this.mPropertyType = source.readString();
        this.mPriceFormatted = source.readString();
        this.mRoomType = source.readString();
        this.mRoomTypeKey = source.readString();
        this.mSpace = source.readString();
        this.mSpaceTypeDescription = source.readString();
        this.mState = source.readString();
        this.mSummary = source.readString();
        this.mUnscrubbedSummary = source.readString();
        this.mSquareFeet = source.readString();
        this.mThumbnailUrl = source.readString();
        this.mPreviewEncodedPng = source.readString();
        this.mTransit = source.readString();
        this.mZipCode = source.readString();
        this.mDistance = source.readString();
        this.mLicense = source.readString();
        this.mTimeZoneName = source.readString();
        this.mInstantBookWelcomeMessage = source.readString();
        this.mLocalizedWirelessInfoDescription = source.readString();
        this.mLocalizedCheckInTimeWindow = source.readString();
        this.mLocalizedCheckOutTime = source.readString();
        this.mLocalizedCity = source.readString();
        this.mLocalizedAdditionalHouseRules = source.readString();
        this.mLocalizedAdditionalHouseRulesWithGoogleTranslate = source.readString();
        this.mListYourSpaceLastFinishedStepId = source.readString();
        this.mHostThumbnailUrl = source.readString();
        this.mHost = (User) source.readParcelable(User.class.getClassLoader());
        this.mPrimaryHost = (User) source.readParcelable(User.class.getClassLoader());
        this.mUserFlag = (UserFlag) source.readParcelable(UserFlag.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mHasAvailability = bools[0];
        this.mHasBeenListed = bools[1];
        this.mHasClosedInstantBookFtue = bools[2];
        this.mInstantBookable = bools[3];
        this.mInstantBookEligible = bools[4];
        this.mUserDefinedLocation = bools[5];
        this.mLocationExact = bools[6];
        this.mIsPriceMonthly = bools[7];
        this.mHasAgreedToLegalTerms = bools[8];
        this.mForceMobileLegalModal = bools[9];
        this.mRequiresLicense = bools[10];
        this.mInTotoArea = bools[11];
        this.mInstantBookEnabled = bools[12];
        this.mSmartPricingAvailable = bools[13];
        this.mSmartPricingExtended = bools[14];
        this.mIsInstantBookingVisibilitySet = bools[15];
        this.mIsBusinessTravelReady = bools[16];
        this.mHasPaidAmenities = bools[17];
        this.mIsAddressEditable = bools[18];
        this.mIsLocationEditable = bools[19];
        this.mFullyRefundable = bools[20];
        this.mLatitude = source.readDouble();
        this.mLongitude = source.readDouble();
        this.mPrice = source.readDouble();
        this.mBathrooms = source.readFloat();
        this.mStarRating = source.readFloat();
        this.mReviewRatingAccuracy = source.readFloat();
        this.mReviewRatingCheckin = source.readFloat();
        this.mReviewRatingCleanliness = source.readFloat();
        this.mReviewRatingCommunication = source.readFloat();
        this.mReviewRatingLocation = source.readFloat();
        this.mReviewRatingOverall = source.readFloat();
        this.mReviewRatingValue = source.readFloat();
        this.mBedrooms = source.readInt();
        this.mBedCount = source.readInt();
        this.mCleaningFee = source.readInt();
        this.mExtraGuestPrice = source.readInt();
        this.mGuestsIncluded = source.readInt();
        this.mListingPrice = source.readInt();
        this.mListingPriceNative = source.readInt();
        this.mListingWeeklyPriceNative = source.readInt();
        this.mListingMonthlyPriceNative = source.readInt();
        this.mListingWeekendPriceNative = source.readInt();
        this.mListingPriceForExtraPersonNative = source.readInt();
        this.mMinNights = source.readInt();
        this.mMaxNights = source.readInt();
        this.mMonthlyPriceNative = source.readInt();
        this.mPersonCapacity = source.readInt();
        this.mPictureCount = source.readInt();
        this.mRatedReviewsCount = source.readInt();
        this.mPriceNative = source.readInt();
        this.mPropertyTypeId = source.readInt();
        this.mReadyForSelectStatus = source.readInt();
        this.mReviewsCount = source.readInt();
        this.mPageViews = source.readInt();
        this.mSecurityDeposit = source.readInt();
        this.mWeeklyPriceNative = source.readInt();
        this.mWeekendPrice = source.readInt();
        this.mStepsRemaining = source.readInt();
        this.mScrimColor = source.readInt();
        this.mId = source.readLong();
        this.mUserId = source.readLong();
        this.mNeighborhoodId = source.readLong();
        this.mViewedAt = source.readLong();
    }
}
