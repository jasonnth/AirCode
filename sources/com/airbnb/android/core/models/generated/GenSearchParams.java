package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.p008mt.models.PrimaryCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSearchParams implements Parcelable {
    @JsonProperty("adults")
    protected int mAdults;
    @JsonProperty("amenities")
    protected List<Amenity> mAmenities;
    @JsonProperty("amenities_to_filter_out")
    protected List<Amenity> mAmenitiesToFilterOut;
    @JsonProperty("btsr")
    protected Boolean mBusinessTravelReady;
    @JsonProperty("checkin")
    protected AirDate mCheckin;
    @JsonProperty("checkout")
    protected AirDate mCheckout;
    @JsonProperty("children")
    protected int mChildren;
    @JsonProperty("currency")
    protected String mCurrency;
    @JsonProperty("entire_place")
    protected boolean mEntirePlace;
    @JsonProperty("experience_categories")
    protected List<PrimaryCategory> mExperienceCategories;
    @JsonProperty("guests")
    protected int mGuests;
    @JsonProperty("infants")
    protected int mInfants;
    @JsonProperty("ib")
    protected Boolean mInstantBookOnly;
    @JsonProperty("keywords")
    protected List<String> mKeywords;
    @JsonProperty("languages")
    protected List<Integer> mLanguages;
    @JsonProperty("location")
    protected String mLocation;
    @JsonProperty("price_max")
    protected int mMaxPrice;
    @JsonProperty("price_min")
    protected int mMinPrice;
    @JsonProperty("ne_lat")
    protected double mNeLat;
    @JsonProperty("ne_lng")
    protected double mNeLng;
    @JsonProperty("neighborhoods")
    protected List<String> mNeighborhoods;
    @JsonProperty("min_bathrooms")
    protected int mNumBathrooms;
    @JsonProperty("min_bedrooms")
    protected int mNumBedrooms;
    @JsonProperty("min_beds")
    protected int mNumBeds;
    @JsonProperty("pets")
    protected boolean mPets;
    @JsonProperty("place_id")
    protected String mPlaceId;
    @JsonProperty("private_room")
    protected boolean mPrivateRoom;
    @JsonProperty("property_type_id")
    protected List<PropertyType> mPropertyTypes;
    @JsonProperty("room_types")
    protected List<C6120RoomType> mRoomTypes;
    @JsonProperty("search_by_map")
    protected boolean mSearchByMap;
    @JsonProperty("share_room")
    protected boolean mShareRoom;
    @JsonProperty("superhost")
    protected boolean mSuperhost;
    @JsonProperty("sw_lat")
    protected double mSwLat;
    @JsonProperty("sw_lng")
    protected double mSwLng;
    @JsonProperty("tab_id")
    protected String mTabId;
    @JsonProperty("trip_purpose")
    protected TripPurpose mTripPurpose;
    @JsonProperty("updated_at")
    protected AirDateTime mUpdatedAt;

    protected GenSearchParams(AirDate checkin, AirDate checkout, AirDateTime updatedAt, Boolean instantBookOnly, Boolean businessTravelReady, List<Amenity> amenities, List<Amenity> amenitiesToFilterOut, List<Integer> languages, List<PrimaryCategory> experienceCategories, List<PropertyType> propertyTypes, List<C6120RoomType> roomTypes, List<String> neighborhoods, List<String> keywords, String location, String currency, String tabId, String placeId, TripPurpose tripPurpose, boolean searchByMap, boolean privateRoom, boolean shareRoom, boolean entirePlace, boolean pets, boolean superhost, double swLat, double swLng, double neLat, double neLng, int guests, int adults, int children, int infants, int minPrice, int maxPrice, int numBeds, int numBedrooms, int numBathrooms) {
        this();
        this.mCheckin = checkin;
        this.mCheckout = checkout;
        this.mUpdatedAt = updatedAt;
        this.mInstantBookOnly = instantBookOnly;
        this.mBusinessTravelReady = businessTravelReady;
        this.mAmenities = amenities;
        this.mAmenitiesToFilterOut = amenitiesToFilterOut;
        this.mLanguages = languages;
        this.mExperienceCategories = experienceCategories;
        this.mPropertyTypes = propertyTypes;
        this.mRoomTypes = roomTypes;
        this.mNeighborhoods = neighborhoods;
        this.mKeywords = keywords;
        this.mLocation = location;
        this.mCurrency = currency;
        this.mTabId = tabId;
        this.mPlaceId = placeId;
        this.mTripPurpose = tripPurpose;
        this.mSearchByMap = searchByMap;
        this.mPrivateRoom = privateRoom;
        this.mShareRoom = shareRoom;
        this.mEntirePlace = entirePlace;
        this.mPets = pets;
        this.mSuperhost = superhost;
        this.mSwLat = swLat;
        this.mSwLng = swLng;
        this.mNeLat = neLat;
        this.mNeLng = neLng;
        this.mGuests = guests;
        this.mAdults = adults;
        this.mChildren = children;
        this.mInfants = infants;
        this.mMinPrice = minPrice;
        this.mMaxPrice = maxPrice;
        this.mNumBeds = numBeds;
        this.mNumBedrooms = numBedrooms;
        this.mNumBathrooms = numBathrooms;
    }

    protected GenSearchParams() {
    }

    public AirDate getCheckin() {
        return this.mCheckin;
    }

    @JsonProperty("checkin")
    public void setCheckin(AirDate value) {
        this.mCheckin = value;
    }

    public AirDate getCheckout() {
        return this.mCheckout;
    }

    @JsonProperty("checkout")
    public void setCheckout(AirDate value) {
        this.mCheckout = value;
    }

    public AirDateTime getUpdatedAt() {
        return this.mUpdatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(AirDateTime value) {
        this.mUpdatedAt = value;
    }

    public Boolean isInstantBookOnly() {
        return this.mInstantBookOnly;
    }

    @JsonProperty("ib")
    public void setInstantBookOnly(Boolean value) {
        this.mInstantBookOnly = value;
    }

    public Boolean isBusinessTravelReady() {
        return this.mBusinessTravelReady;
    }

    @JsonProperty("btsr")
    public void setBusinessTravelReady(Boolean value) {
        this.mBusinessTravelReady = value;
    }

    public List<Amenity> getAmenities() {
        return this.mAmenities;
    }

    public List<Amenity> getAmenitiesToFilterOut() {
        return this.mAmenitiesToFilterOut;
    }

    public List<Integer> getLanguages() {
        return this.mLanguages;
    }

    @JsonProperty("languages")
    public void setLanguages(List<Integer> value) {
        this.mLanguages = value;
    }

    public List<PrimaryCategory> getExperienceCategories() {
        return this.mExperienceCategories;
    }

    @JsonProperty("experience_categories")
    public void setExperienceCategories(List<PrimaryCategory> value) {
        this.mExperienceCategories = value;
    }

    public List<PropertyType> getPropertyTypes() {
        return this.mPropertyTypes;
    }

    public List<C6120RoomType> getRoomTypes() {
        return this.mRoomTypes;
    }

    public List<String> getNeighborhoods() {
        return this.mNeighborhoods;
    }

    @JsonProperty("neighborhoods")
    public void setNeighborhoods(List<String> value) {
        this.mNeighborhoods = value;
    }

    public List<String> getKeywords() {
        return this.mKeywords;
    }

    @JsonProperty("keywords")
    public void setKeywords(List<String> value) {
        this.mKeywords = value;
    }

    public String getLocation() {
        return this.mLocation;
    }

    @JsonProperty("location")
    public void setLocation(String value) {
        this.mLocation = value;
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    @JsonProperty("currency")
    public void setCurrency(String value) {
        this.mCurrency = value;
    }

    public String getTabId() {
        return this.mTabId;
    }

    @JsonProperty("tab_id")
    public void setTabId(String value) {
        this.mTabId = value;
    }

    public String getPlaceId() {
        return this.mPlaceId;
    }

    @JsonProperty("place_id")
    public void setPlaceId(String value) {
        this.mPlaceId = value;
    }

    public TripPurpose getTripPurpose() {
        return this.mTripPurpose;
    }

    public boolean isSearchByMap() {
        return this.mSearchByMap;
    }

    @JsonProperty("search_by_map")
    public void setSearchByMap(boolean value) {
        this.mSearchByMap = value;
    }

    public boolean isPrivateRoom() {
        return this.mPrivateRoom;
    }

    @JsonProperty("private_room")
    public void setPrivateRoom(boolean value) {
        this.mPrivateRoom = value;
    }

    public boolean isShareRoom() {
        return this.mShareRoom;
    }

    @JsonProperty("share_room")
    public void setShareRoom(boolean value) {
        this.mShareRoom = value;
    }

    public boolean isEntirePlace() {
        return this.mEntirePlace;
    }

    @JsonProperty("entire_place")
    public void setEntirePlace(boolean value) {
        this.mEntirePlace = value;
    }

    public boolean isPets() {
        return this.mPets;
    }

    @JsonProperty("pets")
    public void setPets(boolean value) {
        this.mPets = value;
    }

    public boolean isSuperhost() {
        return this.mSuperhost;
    }

    @JsonProperty("superhost")
    public void setSuperhost(boolean value) {
        this.mSuperhost = value;
    }

    public double getSwLat() {
        return this.mSwLat;
    }

    @JsonProperty("sw_lat")
    public void setSwLat(double value) {
        this.mSwLat = value;
    }

    public double getSwLng() {
        return this.mSwLng;
    }

    @JsonProperty("sw_lng")
    public void setSwLng(double value) {
        this.mSwLng = value;
    }

    public double getNeLat() {
        return this.mNeLat;
    }

    @JsonProperty("ne_lat")
    public void setNeLat(double value) {
        this.mNeLat = value;
    }

    public double getNeLng() {
        return this.mNeLng;
    }

    @JsonProperty("ne_lng")
    public void setNeLng(double value) {
        this.mNeLng = value;
    }

    public int getGuests() {
        return this.mGuests;
    }

    @JsonProperty("guests")
    public void setGuests(int value) {
        this.mGuests = value;
    }

    public int getAdults() {
        return this.mAdults;
    }

    @JsonProperty("adults")
    public void setAdults(int value) {
        this.mAdults = value;
    }

    public int getChildren() {
        return this.mChildren;
    }

    @JsonProperty("children")
    public void setChildren(int value) {
        this.mChildren = value;
    }

    public int getInfants() {
        return this.mInfants;
    }

    @JsonProperty("infants")
    public void setInfants(int value) {
        this.mInfants = value;
    }

    public int getMinPrice() {
        return this.mMinPrice;
    }

    @JsonProperty("price_min")
    public void setMinPrice(int value) {
        this.mMinPrice = value;
    }

    public int getMaxPrice() {
        return this.mMaxPrice;
    }

    @JsonProperty("price_max")
    public void setMaxPrice(int value) {
        this.mMaxPrice = value;
    }

    public int getNumBeds() {
        return this.mNumBeds;
    }

    @JsonProperty("min_beds")
    public void setNumBeds(int value) {
        this.mNumBeds = value;
    }

    public int getNumBedrooms() {
        return this.mNumBedrooms;
    }

    @JsonProperty("min_bedrooms")
    public void setNumBedrooms(int value) {
        this.mNumBedrooms = value;
    }

    public int getNumBathrooms() {
        return this.mNumBathrooms;
    }

    @JsonProperty("min_bathrooms")
    public void setNumBathrooms(int value) {
        this.mNumBathrooms = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCheckin, 0);
        parcel.writeParcelable(this.mCheckout, 0);
        parcel.writeParcelable(this.mUpdatedAt, 0);
        parcel.writeValue(this.mInstantBookOnly);
        parcel.writeValue(this.mBusinessTravelReady);
        parcel.writeTypedList(this.mAmenities);
        parcel.writeTypedList(this.mAmenitiesToFilterOut);
        parcel.writeValue(this.mLanguages);
        parcel.writeTypedList(this.mExperienceCategories);
        parcel.writeTypedList(this.mPropertyTypes);
        parcel.writeTypedList(this.mRoomTypes);
        parcel.writeStringList(this.mNeighborhoods);
        parcel.writeStringList(this.mKeywords);
        parcel.writeString(this.mLocation);
        parcel.writeString(this.mCurrency);
        parcel.writeString(this.mTabId);
        parcel.writeString(this.mPlaceId);
        parcel.writeParcelable(this.mTripPurpose, 0);
        parcel.writeBooleanArray(new boolean[]{this.mSearchByMap, this.mPrivateRoom, this.mShareRoom, this.mEntirePlace, this.mPets, this.mSuperhost});
        parcel.writeDouble(this.mSwLat);
        parcel.writeDouble(this.mSwLng);
        parcel.writeDouble(this.mNeLat);
        parcel.writeDouble(this.mNeLng);
        parcel.writeInt(this.mGuests);
        parcel.writeInt(this.mAdults);
        parcel.writeInt(this.mChildren);
        parcel.writeInt(this.mInfants);
        parcel.writeInt(this.mMinPrice);
        parcel.writeInt(this.mMaxPrice);
        parcel.writeInt(this.mNumBeds);
        parcel.writeInt(this.mNumBedrooms);
        parcel.writeInt(this.mNumBathrooms);
    }

    public void readFromParcel(Parcel source) {
        this.mCheckin = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCheckout = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mUpdatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mInstantBookOnly = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mBusinessTravelReady = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mAmenities = source.createTypedArrayList(Amenity.CREATOR);
        this.mAmenitiesToFilterOut = source.createTypedArrayList(Amenity.CREATOR);
        this.mLanguages = (List) source.readValue(null);
        this.mExperienceCategories = source.createTypedArrayList(PrimaryCategory.CREATOR);
        this.mPropertyTypes = source.createTypedArrayList(PropertyType.CREATOR);
        this.mRoomTypes = source.createTypedArrayList(C6120RoomType.CREATOR);
        this.mNeighborhoods = source.createStringArrayList();
        this.mKeywords = source.createStringArrayList();
        this.mLocation = source.readString();
        this.mCurrency = source.readString();
        this.mTabId = source.readString();
        this.mPlaceId = source.readString();
        this.mTripPurpose = (TripPurpose) source.readParcelable(TripPurpose.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mSearchByMap = bools[0];
        this.mPrivateRoom = bools[1];
        this.mShareRoom = bools[2];
        this.mEntirePlace = bools[3];
        this.mPets = bools[4];
        this.mSuperhost = bools[5];
        this.mSwLat = source.readDouble();
        this.mSwLng = source.readDouble();
        this.mNeLat = source.readDouble();
        this.mNeLng = source.readDouble();
        this.mGuests = source.readInt();
        this.mAdults = source.readInt();
        this.mChildren = source.readInt();
        this.mInfants = source.readInt();
        this.mMinPrice = source.readInt();
        this.mMaxPrice = source.readInt();
        this.mNumBeds = source.readInt();
        this.mNumBedrooms = source.readInt();
        this.mNumBathrooms = source.readInt();
    }
}
