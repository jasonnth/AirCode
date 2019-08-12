package com.airbnb.android.core.beta.models.guidebook.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.beta.models.guidebook.PlaceHour;
import com.airbnb.android.core.models.PlaceActivityHours;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPlace implements Parcelable {
    @JsonProperty("address")
    protected String mAddress;
    @JsonProperty("airmoji")
    protected String mAirmoji;
    @JsonProperty("category")
    protected String mCategory;
    @JsonProperty("category_for_display")
    protected String mCategoryForDisplay;
    @JsonProperty("city")
    protected String mCity;
    @JsonProperty("country")
    protected String mCountry;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("lat")
    protected double mLat;
    @JsonProperty("lng")
    protected double mLng;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("num_ratings")
    protected int mNumRatings;
    @JsonProperty("open_hours")
    protected PlaceActivityHours mOpenHours;
    @JsonProperty("opening_hours")
    protected PlaceHour[] mOpeningHours;
    @JsonProperty("phone")
    protected String mPhone;
    @JsonProperty("price_level")
    protected int mPriceLevel;
    @JsonProperty("rating")
    protected double mRating;
    @JsonProperty("reference")
    protected String mReference;
    @JsonProperty("source")
    protected String mSource;
    @JsonProperty("state")
    protected String mState;
    @JsonProperty("street")
    protected String mStreet;
    @JsonProperty("street_number")
    protected String mStreetNumber;
    @JsonProperty("website")
    protected String mWebsite;
    @JsonProperty("zipcode")
    protected String mZipcode;

    protected GenPlace(PlaceActivityHours openHours, PlaceHour[] openingHours, String address, String airmoji, String category, String categoryForDisplay, String city, String country, String name, String phone, String reference, String source, String state, String street, String streetNumber, String website, String zipcode, double rating, double lat, double lng, int numRatings, int priceLevel, long id) {
        this();
        this.mOpenHours = openHours;
        this.mOpeningHours = openingHours;
        this.mAddress = address;
        this.mAirmoji = airmoji;
        this.mCategory = category;
        this.mCategoryForDisplay = categoryForDisplay;
        this.mCity = city;
        this.mCountry = country;
        this.mName = name;
        this.mPhone = phone;
        this.mReference = reference;
        this.mSource = source;
        this.mState = state;
        this.mStreet = street;
        this.mStreetNumber = streetNumber;
        this.mWebsite = website;
        this.mZipcode = zipcode;
        this.mRating = rating;
        this.mLat = lat;
        this.mLng = lng;
        this.mNumRatings = numRatings;
        this.mPriceLevel = priceLevel;
        this.mId = id;
    }

    protected GenPlace() {
    }

    public PlaceActivityHours getOpenHours() {
        return this.mOpenHours;
    }

    @JsonProperty("open_hours")
    public void setOpenHours(PlaceActivityHours value) {
        this.mOpenHours = value;
    }

    public PlaceHour[] getOpeningHours() {
        return this.mOpeningHours;
    }

    @JsonProperty("opening_hours")
    public void setOpeningHours(PlaceHour[] value) {
        this.mOpeningHours = value;
    }

    public String getAddress() {
        return this.mAddress;
    }

    @JsonProperty("address")
    public void setAddress(String value) {
        this.mAddress = value;
    }

    public String getAirmoji() {
        return this.mAirmoji;
    }

    @JsonProperty("airmoji")
    public void setAirmoji(String value) {
        this.mAirmoji = value;
    }

    public String getCategory() {
        return this.mCategory;
    }

    @JsonProperty("category")
    public void setCategory(String value) {
        this.mCategory = value;
    }

    public String getCategoryForDisplay() {
        return this.mCategoryForDisplay;
    }

    @JsonProperty("category_for_display")
    public void setCategoryForDisplay(String value) {
        this.mCategoryForDisplay = value;
    }

    public String getCity() {
        return this.mCity;
    }

    @JsonProperty("city")
    public void setCity(String value) {
        this.mCity = value;
    }

    public String getCountry() {
        return this.mCountry;
    }

    @JsonProperty("country")
    public void setCountry(String value) {
        this.mCountry = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getPhone() {
        return this.mPhone;
    }

    @JsonProperty("phone")
    public void setPhone(String value) {
        this.mPhone = value;
    }

    public String getReference() {
        return this.mReference;
    }

    @JsonProperty("reference")
    public void setReference(String value) {
        this.mReference = value;
    }

    public String getSource() {
        return this.mSource;
    }

    @JsonProperty("source")
    public void setSource(String value) {
        this.mSource = value;
    }

    public String getState() {
        return this.mState;
    }

    @JsonProperty("state")
    public void setState(String value) {
        this.mState = value;
    }

    public String getStreet() {
        return this.mStreet;
    }

    @JsonProperty("street")
    public void setStreet(String value) {
        this.mStreet = value;
    }

    public String getStreetNumber() {
        return this.mStreetNumber;
    }

    @JsonProperty("street_number")
    public void setStreetNumber(String value) {
        this.mStreetNumber = value;
    }

    public String getWebsite() {
        return this.mWebsite;
    }

    @JsonProperty("website")
    public void setWebsite(String value) {
        this.mWebsite = value;
    }

    public String getZipcode() {
        return this.mZipcode;
    }

    @JsonProperty("zipcode")
    public void setZipcode(String value) {
        this.mZipcode = value;
    }

    public double getRating() {
        return this.mRating;
    }

    @JsonProperty("rating")
    public void setRating(double value) {
        this.mRating = value;
    }

    public double getLat() {
        return this.mLat;
    }

    @JsonProperty("lat")
    public void setLat(double value) {
        this.mLat = value;
    }

    public double getLng() {
        return this.mLng;
    }

    @JsonProperty("lng")
    public void setLng(double value) {
        this.mLng = value;
    }

    public int getNumRatings() {
        return this.mNumRatings;
    }

    @JsonProperty("num_ratings")
    public void setNumRatings(int value) {
        this.mNumRatings = value;
    }

    public int getPriceLevel() {
        return this.mPriceLevel;
    }

    @JsonProperty("price_level")
    public void setPriceLevel(int value) {
        this.mPriceLevel = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mOpenHours, 0);
        parcel.writeTypedArray(this.mOpeningHours, 0);
        parcel.writeString(this.mAddress);
        parcel.writeString(this.mAirmoji);
        parcel.writeString(this.mCategory);
        parcel.writeString(this.mCategoryForDisplay);
        parcel.writeString(this.mCity);
        parcel.writeString(this.mCountry);
        parcel.writeString(this.mName);
        parcel.writeString(this.mPhone);
        parcel.writeString(this.mReference);
        parcel.writeString(this.mSource);
        parcel.writeString(this.mState);
        parcel.writeString(this.mStreet);
        parcel.writeString(this.mStreetNumber);
        parcel.writeString(this.mWebsite);
        parcel.writeString(this.mZipcode);
        parcel.writeDouble(this.mRating);
        parcel.writeDouble(this.mLat);
        parcel.writeDouble(this.mLng);
        parcel.writeInt(this.mNumRatings);
        parcel.writeInt(this.mPriceLevel);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mOpenHours = (PlaceActivityHours) source.readParcelable(PlaceActivityHours.class.getClassLoader());
        this.mOpeningHours = (PlaceHour[]) source.createTypedArray(PlaceHour.CREATOR);
        this.mAddress = source.readString();
        this.mAirmoji = source.readString();
        this.mCategory = source.readString();
        this.mCategoryForDisplay = source.readString();
        this.mCity = source.readString();
        this.mCountry = source.readString();
        this.mName = source.readString();
        this.mPhone = source.readString();
        this.mReference = source.readString();
        this.mSource = source.readString();
        this.mState = source.readString();
        this.mStreet = source.readString();
        this.mStreetNumber = source.readString();
        this.mWebsite = source.readString();
        this.mZipcode = source.readString();
        this.mRating = source.readDouble();
        this.mLat = source.readDouble();
        this.mLng = source.readDouble();
        this.mNumRatings = source.readInt();
        this.mPriceLevel = source.readInt();
        this.mId = source.readLong();
    }
}
