package com.airbnb.android.core.models;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.deserializers.WrappedDeserializer;
import com.airbnb.android.core.deserializers.WrappedObject;
import com.airbnb.android.core.enums.BathroomType;
import com.airbnb.android.core.enums.CheckInGuideStatus;
import com.airbnb.android.core.enums.InstantBookAdvanceNotice;
import com.airbnb.android.core.enums.InstantBookVisibility;
import com.airbnb.android.core.enums.ListYourSpacePricingMode;
import com.airbnb.android.core.enums.ListingStatus;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.enums.ReadyForSelectStatus;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.interfaces.Photoable;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaQuestion;
import com.airbnb.android.core.models.generated.GenListing;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.core.utils.ListingReviewsUtil;
import com.airbnb.android.core.utils.LocationUtil;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.primitives.imaging.ImageSize;
import com.airbnb.p027n2.utils.LatLng;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Listing extends GenListing implements Photoable {
    public static final Creator<Listing> CREATOR = new Creator<Listing>() {
        public Listing[] newArray(int size) {
            return new Listing[size];
        }

        public Listing createFromParcel(Parcel source) {
            Listing object = new Listing();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final String LISTING_DESCRIPTION_AUTHOR_HOST = "human";
    private boolean houseRulesVisited;
    private Boolean mHasPets = null;
    private List<ListingRoom> memoizedSortedRooms;
    private Photo photo;

    public boolean isListed() {
        return getStatus() == ListingStatus.Listed;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        super.setStatus(ListingStatus.fromKey(status));
    }

    public int getAutoPricingDaily() {
        if (this.mAutoPricing == null) {
            return 0;
        }
        return this.mAutoPricing.getDaily();
    }

    public int getAutoPricingWeekly() {
        if (this.mAutoPricing == null) {
            return 0;
        }
        return this.mAutoPricing.getWeekly();
    }

    public int getAutoPricingMonthly() {
        if (this.mAutoPricing == null) {
            return 0;
        }
        return this.mAutoPricing.getMonthly();
    }

    public double getAutoMonthlyFactor() {
        if (this.mAutoPricing == null) {
            return 0.0d;
        }
        return this.mAutoPricing.getMonthlyDiscount();
    }

    public double getAutoWeeklyFactor() {
        if (this.mAutoPricing == null) {
            return 0.0d;
        }
        return this.mAutoPricing.getWeeklyDiscount();
    }

    public int getAutoEarningForecast() {
        if (this.mAutoPricing == null) {
            return 0;
        }
        return this.mAutoPricing.getExtraEarningForecast();
    }

    public boolean hasHouseRules() {
        return !TextUtils.isEmpty(getAdditionalHouseRules()) || (getGuestControls() != null && (!ListUtils.isEmpty((Collection<?>) getGuestControls().getStructuredHouseRules()) || getGuestControls().hasHouseRulesSet()));
    }

    public boolean getHouseRulesVisited() {
        return this.houseRulesVisited || hasHouseRules();
    }

    public void setHouseRulesVisited(boolean visited) {
        this.houseRulesVisited = visited;
    }

    public boolean shouldHideLegalInfo() {
        return "FR".equalsIgnoreCase(getCountryCode());
    }

    public String getLegacyLocalLawsUrl(Context context, User currentUser) {
        return getLocalLawsBuilder(context, currentUser).toString();
    }

    public String getStyledLocalLawsUrl(Context context, User currentUser) {
        return getLocalLawsBuilder(context, currentUser).append("&enable_styling=true").toString();
    }

    private StringBuilder getLocalLawsBuilder(Context context, User currentUser) {
        StringBuilder builder = new StringBuilder("https://www.airbnb.com/local_laws/0?state=").append(getState()).append("&country=").append(getCountryCode());
        if (!TextUtils.isEmpty(getCity())) {
            builder.append("&city=").append(getCity());
        }
        builder.append("&host_country=").append(currentUser.getCountry());
        builder.append("&force_radical_transparency=true");
        return builder;
    }

    @Deprecated
    public boolean isInstantBookable() {
        return super.isInstantBookable();
    }

    @Deprecated
    public String getNativeCurrency() {
        return super.getNativeCurrency();
    }

    @Deprecated
    public String getPriceFormatted() {
        return super.getPriceFormatted();
    }

    @Deprecated
    public double getPrice() {
        return super.getPrice();
    }

    @Deprecated
    public int getListingPriceNative() {
        return super.getListingPriceNative();
    }

    @Deprecated
    public int getPriceNative() {
        return super.getPriceNative();
    }

    public PriceFactor getMonthlyPriceFactor() {
        if (this.mMonthlyPriceFactor == null) {
            this.mMonthlyPriceFactor = new PriceFactor();
        }
        return this.mMonthlyPriceFactor;
    }

    public PriceFactor getWeeklyPriceFactor() {
        if (this.mWeeklyPriceFactor == null) {
            this.mWeeklyPriceFactor = new PriceFactor();
        }
        return this.mWeeklyPriceFactor;
    }

    public List<ListingExpectation> getListingExpectations() {
        if (this.mListingExpectations == null) {
            this.mListingExpectations = new ArrayList();
        }
        return this.mListingExpectations;
    }

    public List<ListingExpectation> getLocalizedListingExpectations() {
        if (this.mLocalizedListingExpectations == null) {
            this.mLocalizedListingExpectations = new ArrayList();
        }
        return this.mLocalizedListingExpectations;
    }

    public DemandCounts getMostRecentDemand() {
        int demandCountsSize = getDemandCounts().size();
        if (demandCountsSize > 0) {
            return (DemandCounts) getDemandCounts().get(demandCountsSize - 1);
        }
        return null;
    }

    @JsonProperty("special_offer")
    public void setSpecialOffer(SpecialOffer specialOffer) {
        if (specialOffer != null) {
            if (specialOffer.getId() <= 0) {
                specialOffer = specialOffer.getSpecialOffer();
            }
            this.mSpecialOffer = specialOffer;
        }
    }

    @JsonProperty("ap_pricing")
    public void setAutoPricing(AutoPricing value) {
        if (value == null) {
            this.mAutoPricing = new AutoPricing();
        } else {
            this.mAutoPricing = value;
        }
    }

    @JsonProperty("bathroom_type")
    public void setBathroomType(String serverKey) {
        this.mBathroomType = BathroomType.getTypeFromKeyOrDefault(serverKey);
    }

    public void setBathroomType(BathroomType bathroomType) {
        this.mBathroomType = bathroomType;
    }

    @JsonProperty("list_your_space_pricing_mode")
    public void setListYourSpacePricingMode(int serverKey) {
        this.mListYourSpacePricingMode = ListYourSpacePricingMode.fromServerKey(serverKey);
    }

    public List<String> getPictureUrls() {
        return this.mPictureUrls != null ? this.mPictureUrls : new ArrayList();
    }

    @WrappedObject("listing_wireless_info")
    @JsonProperty("wireless_info")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setWirelessInfo(ListingWirelessInfo wirelessInfo) {
        this.mWirelessInfo = wirelessInfo;
    }

    public String getThumbnailUrl() {
        return ImageUtils.parseListingThumbnailUrl(this.mThumbnailUrl);
    }

    public List<String> getThumbnailUrls() {
        return this.mThumbnailUrls != null ? this.mThumbnailUrls : new ArrayList();
    }

    public boolean hasLongtermPricing() {
        if (!getMonthlyPriceFactor().hasSetDiscountsBefore() || !getWeeklyPriceFactor().hasSetDiscountsBefore()) {
            if (getListingWeeklyPriceNative() == 0 && getListingMonthlyPriceNative() == 0) {
                return false;
            }
            return true;
        } else if (getMonthlyPriceFactor().hasDiscount() || getWeeklyPriceFactor().hasDiscount()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getHasSetLocation() {
        return this.mLocationExact || this.mUserDefinedLocation;
    }

    public String getXlPictureUrl() {
        if (this.mXlPictureUrl != null) {
            return this.mXlPictureUrl;
        }
        if (this.mXlPictureUrls == null || this.mXlPictureUrls.isEmpty()) {
            return null;
        }
        return (String) getXlPictureUrls().get(0);
    }

    public Photo getPhoto() {
        if (this.photo != null) {
            return this.photo;
        }
        if (!ListUtils.isEmpty((Collection<?>) this.mPhotos)) {
            this.photo = (Photo) this.mPhotos.get(0);
        } else {
            this.photo = new Photo();
            this.photo.setId(this.mId);
            this.photo.setPreviewEncodedPng(this.mPreviewEncodedPng);
            this.photo.setSmallUrl(this.mThumbnailUrl);
            this.photo.setLargeUrl(this.mPictureUrl);
            this.photo.setXLargeUrl(this.mXlPictureUrl);
            this.photo.setScrimColor(this.mScrimColor);
        }
        return this.photo;
    }

    public ImmutableList<Photo> getSortedPhotos() {
        return FluentIterable.from((Iterable<E>) getPhotos()).toSortedList(Photo.ORDER_COMPARATOR);
    }

    public boolean hasTranslatedDescription() {
        return (getListingDescriptionAuthorType() == null || LISTING_DESCRIPTION_AUTHOR_HOST.equals(getListingDescriptionAuthorType()) || getTranslatedSectionedDescription() == null) ? false : true;
    }

    public String getName() {
        String name = "";
        if (getSectionedDescription() != null) {
            name = getSectionedDescription().getName();
        }
        if (TextUtils.isEmpty(name)) {
            name = super.getName();
        }
        return SanitizeUtils.emptyIfNull(name).trim();
    }

    public String getAccess() {
        if (getSectionedDescription() == null || TextUtils.isEmpty(getSectionedDescription().getAccess())) {
            return super.getAccess();
        }
        return getSectionedDescription().getAccess();
    }

    public String getSpace() {
        if (getSectionedDescription() == null || TextUtils.isEmpty(getSectionedDescription().getSpace())) {
            return super.getSpace();
        }
        return getSectionedDescription().getSpace();
    }

    public String getSummary() {
        if (getSectionedDescription() == null || TextUtils.isEmpty(getSectionedDescription().getSummary())) {
            return super.getSummary();
        }
        return getSectionedDescription().getSummary();
    }

    public String getInteraction() {
        if (getSectionedDescription() == null || TextUtils.isEmpty(getSectionedDescription().getInteraction())) {
            return super.getInteraction();
        }
        return getSectionedDescription().getInteraction();
    }

    public String getNeighborhoodOverview() {
        if (getSectionedDescription() == null || TextUtils.isEmpty(getSectionedDescription().getNeighborhoodOverview())) {
            return super.getNeighborhoodOverview();
        }
        return getSectionedDescription().getNeighborhoodOverview();
    }

    public String getTransit() {
        if (getSectionedDescription() == null || TextUtils.isEmpty(getSectionedDescription().getTransit())) {
            return super.getTransit();
        }
        return getSectionedDescription().getTransit();
    }

    public String getNotes() {
        if (getSectionedDescription() == null || TextUtils.isEmpty(getSectionedDescription().getNotes())) {
            return super.getNotes();
        }
        return getSectionedDescription().getNotes();
    }

    public String getHouseRules() {
        if (getSectionedDescription() == null || TextUtils.isEmpty(getSectionedDescription().getHouseRules())) {
            return super.getHouseRules();
        }
        return getSectionedDescription().getHouseRules();
    }

    public int getTotalHosts() {
        if (getHosts() != null) {
            return getHosts().size();
        }
        return 1;
    }

    public boolean isCohosted() {
        return getTotalHosts() > 1;
    }

    public String getLocation(Context context) {
        if (!TextUtils.isEmpty(super.getLocation())) {
            return super.getLocation();
        }
        if (TextUtils.isEmpty(getCountry())) {
            return getCity();
        }
        return context.getString(C0716R.string.listing_location_city_country, new Object[]{getCity(), getCountry()});
    }

    public boolean hasPets() {
        if (this.mHasPets != null) {
            return this.mHasPets.booleanValue();
        }
        HashSet<Amenity> amenities = new HashSet<>(getAmenities());
        this.mHasPets = Boolean.valueOf(amenities.contains(Amenity.HasPets) || amenities.contains(Amenity.HasPetCats) || amenities.contains(Amenity.HasPetDogs) || amenities.contains(Amenity.HasPetOther));
        return this.mHasPets.booleanValue();
    }

    public boolean belongsToUser(User user) {
        return user != null && user.getId() == getUserId();
    }

    public boolean canUserHost(User user) {
        return belongsToUser(user) || !(user == null || getHosts() == null || !getHosts().contains(user));
    }

    public String getListingNativeCurrency() {
        return this.mListingNativeCurrency;
    }

    public List<User> getHosts() {
        if (this.mHosts == null) {
            this.mHosts = new ArrayList();
            if (getHost() != null) {
                this.mHosts.add(getHost());
            }
        }
        return this.mHosts;
    }

    public User getPrimaryHost() {
        return this.mPrimaryHost != null ? this.mPrimaryHost : this.mHost;
    }

    public boolean hasPrimaryHost() {
        return getPrimaryHost() != null;
    }

    static /* synthetic */ boolean lambda$getAdditionalHosts$0(Listing listing, User host) {
        return !host.equals(listing.getPrimaryHost());
    }

    public List<User> getAdditionalHosts() {
        return FluentIterable.from((Iterable<E>) getHosts()).filter(Listing$$Lambda$1.lambdaFactory$(this)).toList();
    }

    public boolean isSuperHosted() {
        if (this.mIsSuperhost != null) {
            return this.mIsSuperhost.booleanValue();
        }
        return getPrimaryHost().isSuperhost() && getPrimaryHost().equals(getHost());
    }

    public boolean hasBeenListed() {
        return super.hasBeenListed();
    }

    @JsonIgnore
    public String getFormattedAddress() {
        return LocationUtil.formatAddress(getStreetAddress(), getApartment(), getCity(), getState(), getZipCode(), getCountry());
    }

    public String getNumberAmenitiesAsString(String defaultString) {
        return ListUtils.isEmpty((Collection<?>) this.mAmenities) ? defaultString : String.valueOf(this.mAmenities.size());
    }

    public void setWhoCanBookInstantly(InstantBookVisibility visibility) {
        setInstantBookingVisibility(visibility.serverDescKey);
    }

    public InstantBookAdvanceNotice getInstantBookAdvanceNotice() {
        if (this.mInstantBookLeadTimeHours == null) {
            return InstantBookAdvanceNotice.DEFAULT;
        }
        return InstantBookAdvanceNotice.getTypeFromKey(this.mInstantBookLeadTimeHours.intValue());
    }

    public InstantBookVisibility getInstantBookVisibility() {
        return InstantBookVisibility.getTypeFromKey(getInstantBookingVisibility());
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
        if (!(obj instanceof Listing)) {
            return false;
        }
        if (this.mId != ((Listing) obj).mId) {
            return false;
        }
        return true;
    }

    @WrappedObject("user")
    @JsonProperty("user")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setHost(User user) {
        this.mHost = user;
    }

    @JsonProperty("scrim_color")
    public void setScrimColor(String scrimColor) {
        if (TextUtils.isEmpty(scrimColor)) {
            this.mScrimColor = 0;
        } else {
            this.mScrimColor = Color.parseColor(scrimColor);
        }
    }

    public boolean isSnoozed() {
        return getSnoozeMode() != null;
    }

    public CharSequence getNumReviewsText(Context context) {
        return ListingReviewsUtil.getNumReviewsText(context, getReviewsCount());
    }

    public String getRoomType(Context context) {
        SpaceType type = getSpaceType();
        if (type != null) {
            return context.getString(type.titleId);
        }
        return this.mRoomType;
    }

    public SpaceType getSpaceType() {
        return SpaceType.getTypeFromKey(this.mRoomTypeKey);
    }

    public CheckInGuideStatus getCheckInGuideStatusEnum() {
        return CheckInGuideStatus.getTypeFromKeyOrDefault(this.mCheckInGuideStatus);
    }

    public ReadyForSelectStatus getReadyForSelectStatusEnum() {
        return ReadyForSelectStatus.fromKeyWithDefault(this.mReadyForSelectStatus, ReadyForSelectStatus.Marketplace);
    }

    public boolean isSmartPricingExtended() {
        return isSmartPricingAvailable() && super.isSmartPricingExtended();
    }

    @Deprecated
    public PricingQuote getPricingQuote() {
        return super.getPricingQuote();
    }

    public String getDescriptionLocale() {
        return SanitizeUtils.emptyIfNull(super.getDescriptionLocale());
    }

    public boolean hasExtraGuestFee() {
        return this.mExtraGuestPrice > 0;
    }

    public List<ListingRoom> getSortedRooms() {
        if (this.memoizedSortedRooms == null) {
            this.memoizedSortedRooms = ListingRoom.sortedRooms(ListUtils.ensureNotNull(this.mListingRooms));
        }
        return this.memoizedSortedRooms;
    }

    public void setListingRooms(List<ListingRoom> value) {
        super.setListingRooms(value);
        this.memoizedSortedRooms = null;
    }

    public float getReviewStarRatingAccuracy() {
        return getReviewRatingAccuracy() / 2.0f;
    }

    public float getReviewStarRatingCheckin() {
        return getReviewRatingCheckin() / 2.0f;
    }

    public float getReviewStarRatingLocation() {
        return getReviewRatingLocation() / 2.0f;
    }

    public float getReviewStarRatingCleanliness() {
        return getReviewRatingCleanliness() / 2.0f;
    }

    public float getReviewStarRatingCommunication() {
        return getReviewRatingCommunication() / 2.0f;
    }

    public float getReviewStarRatingValue() {
        return getReviewRatingValue() / 2.0f;
    }

    public int[] getAmenityIdsArray() {
        return Ints.toArray(FluentIterable.from((Iterable<E>) getAmenities()).transform(Listing$$Lambda$2.lambdaFactory$()).toList());
    }

    public List<Amenity> getAmenities() {
        return this.mAmenities == null ? Collections.emptyList() : this.mAmenities;
    }

    @JsonProperty("amenities_ids")
    public void setAmenities(List<Integer> amenityIds) {
        if (amenityIds == null) {
            amenityIds = Collections.emptyList();
        }
        this.mAmenityIds = amenityIds;
        this.mAmenities = FluentIterable.from((Iterable<E>) this.mAmenityIds).transform(Listing$$Lambda$3.lambdaFactory$()).filter(Predicates.notNull()).toList();
    }

    public void trimPhotos(int numPhotosToKeep) {
        trimList(this.mPhotos, numPhotosToKeep);
        trimList(this.mPictureUrls, numPhotosToKeep);
        trimList(this.mThumbnailUrls, numPhotosToKeep);
        trimList(this.mXlPictureUrls, numPhotosToKeep);
    }

    private static void trimList(List<?> list, int trimToSize) {
        if (list != null && list.size() > trimToSize) {
            list.subList(trimToSize, list.size()).clear();
        }
    }

    public void setPropertyType(PropertyType type) {
        setPropertyTypeId(type.serverDescKey);
    }

    public PropertyType getPropertyTypeEnum() {
        return PropertyType.getTypeFromKey(this.mPropertyTypeId);
    }

    public Incentive getIncentive(String incentiveName) {
        if (ListUtils.isEmpty((Collection<?>) this.mIncentives)) {
            return null;
        }
        for (Incentive i : this.mIncentives) {
            if (i.getName().equals(incentiveName)) {
                return i;
            }
        }
        return null;
    }

    public Incentive getIBTrialIncentive() {
        return getIncentive(Incentive.IB_PROMO);
    }

    public String toString() {
        return "Listing{id=" + this.mId + ", name='" + this.mName + '\'' + '}';
    }

    public LatLng getLatLng() {
        return LatLng.builder().lat(getLatitude()).lng(getLongitude()).build();
    }

    public com.google.android.gms.maps.model.LatLng getAndroidLatLng() {
        return new com.google.android.gms.maps.model.LatLng(getLatitude(), getLongitude());
    }

    public void trimPhotos() {
        trimPhotos(ImageSize.LandscapeLarge);
    }

    public void trimPhotos(ImageSize sizeToKeep) {
        this.mThumbnailUrls = null;
        this.mPictureUrls = null;
        this.mXlPictureUrls = null;
        this.mXlPictureUrl = null;
        this.mThumbnailUrl = null;
        if (this.mPhotos != null) {
            for (Photo photo2 : this.mPhotos) {
                photo2.retainSize(sizeToKeep);
            }
        }
    }

    public void trimForHomeCard() {
        trimPhotos();
        if (this.mPrimaryHost != null) {
            this.mPrimaryHost.setThumbnailUrl(null);
        }
        if (this.mHost != null) {
            this.mHost.setThumbnailUrl(null);
        }
        setPublicAddress(null);
        setAccess(null);
        setDescription(null);
        setNeighborhoodOverview(null);
        setNotes(null);
        setSpace(null);
        setSummary(null);
        setTransit(null);
    }

    private ListingPersonaAnswer getPersonaAnswer(ListingPersonaQuestion question) {
        List<ListingPersonaInput> inputs = this.mListingPersonaInputs;
        if (inputs != null) {
            for (ListingPersonaInput input : inputs) {
                if (input.getQuestion() == question) {
                    return input.getAnswer();
                }
            }
        }
        return null;
    }

    public ListingPersonaAnswer getOccupancyPersonaAnswer() {
        return getPersonaAnswer(ListingPersonaQuestion.OCCUPANCY_QUESTION);
    }

    public ListingPersonaAnswer getExperiencePersonaAnswer() {
        return getPersonaAnswer(ListingPersonaQuestion.EXPERIENCE_QUESTION);
    }

    public void setPersonaAnswer(ListingPersonaInput personaInput) {
        if (this.mListingPersonaInputs != null) {
            this.mListingPersonaInputs = FluentIterable.from((Iterable<E>) this.mListingPersonaInputs).filter(Listing$$Lambda$4.lambdaFactory$(personaInput)).append((E[]) new ListingPersonaInput[]{personaInput}).toList();
        }
    }

    static /* synthetic */ boolean lambda$setPersonaAnswer$1(ListingPersonaInput personaInput, ListingPersonaInput input) {
        return input.getQuestion() != personaInput.getQuestion();
    }
}
