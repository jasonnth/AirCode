package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.TripTemplate.Type;
import com.airbnb.android.core.models.TripTemplateCurrency;
import com.airbnb.android.core.models.TripTemplateHostProfile;
import com.airbnb.android.core.models.TripTemplateMarket;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenTripTemplate implements Parcelable {
    @JsonProperty("base_price")
    protected int mBasePrice;
    @JsonProperty("categories")
    protected List<String> mCategories;
    @JsonProperty("currency")
    protected TripTemplateCurrency mCurrency;
    @JsonProperty("display_text")
    protected String mDisplayText;
    @JsonProperty("experience_host_profile")
    protected TripTemplateHostProfile mHostProfile;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("is_social_good")
    protected boolean mIsSocialGood;
    @JsonProperty("is_sold_out")
    protected boolean mIsSoldOut;
    @JsonProperty("lat")
    protected double mLatitude;
    @JsonProperty("lng")
    protected double mLongitude;
    @JsonProperty("market")
    protected TripTemplateMarket mMarket;
    @JsonProperty("poster_pictures")
    protected List<Photo> mPosterPictures;
    @JsonProperty("product_type")
    protected Type mProductType;
    @JsonProperty("review_count")
    protected int mReviewCount;
    @JsonProperty("star_rating")
    protected float mStarRating;
    @JsonProperty("trip_tags")
    protected String mTripTags;

    protected GenTripTemplate(List<Photo> posterPictures, List<String> categories, String tripTags, String displayText, TripTemplateCurrency currency, TripTemplateHostProfile hostProfile, TripTemplateMarket market, Type productType, boolean isSocialGood, boolean isSoldOut, double latitude, double longitude, float starRating, int reviewCount, int basePrice, long id) {
        this();
        this.mPosterPictures = posterPictures;
        this.mCategories = categories;
        this.mTripTags = tripTags;
        this.mDisplayText = displayText;
        this.mCurrency = currency;
        this.mHostProfile = hostProfile;
        this.mMarket = market;
        this.mProductType = productType;
        this.mIsSocialGood = isSocialGood;
        this.mIsSoldOut = isSoldOut;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mStarRating = starRating;
        this.mReviewCount = reviewCount;
        this.mBasePrice = basePrice;
        this.mId = id;
    }

    protected GenTripTemplate() {
    }

    public List<Photo> getPosterPictures() {
        return this.mPosterPictures;
    }

    @JsonProperty("poster_pictures")
    public void setPosterPictures(List<Photo> value) {
        this.mPosterPictures = value;
    }

    public List<String> getCategories() {
        return this.mCategories;
    }

    @JsonProperty("categories")
    public void setCategories(List<String> value) {
        this.mCategories = value;
    }

    public String getTripTags() {
        return this.mTripTags;
    }

    @JsonProperty("trip_tags")
    public void setTripTags(String value) {
        this.mTripTags = value;
    }

    public String getDisplayText() {
        return this.mDisplayText;
    }

    @JsonProperty("display_text")
    public void setDisplayText(String value) {
        this.mDisplayText = value;
    }

    public TripTemplateCurrency getCurrency() {
        return this.mCurrency;
    }

    @JsonProperty("currency")
    public void setCurrency(TripTemplateCurrency value) {
        this.mCurrency = value;
    }

    public TripTemplateHostProfile getHostProfile() {
        return this.mHostProfile;
    }

    @JsonProperty("experience_host_profile")
    public void setHostProfile(TripTemplateHostProfile value) {
        this.mHostProfile = value;
    }

    public TripTemplateMarket getMarket() {
        return this.mMarket;
    }

    @JsonProperty("market")
    public void setMarket(TripTemplateMarket value) {
        this.mMarket = value;
    }

    public Type getProductType() {
        return this.mProductType;
    }

    public boolean isSocialGood() {
        return this.mIsSocialGood;
    }

    @JsonProperty("is_social_good")
    public void setIsSocialGood(boolean value) {
        this.mIsSocialGood = value;
    }

    public boolean isSoldOut() {
        return this.mIsSoldOut;
    }

    @JsonProperty("is_sold_out")
    public void setIsSoldOut(boolean value) {
        this.mIsSoldOut = value;
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

    public float getStarRating() {
        return this.mStarRating;
    }

    @JsonProperty("star_rating")
    public void setStarRating(float value) {
        this.mStarRating = value;
    }

    public int getReviewCount() {
        return this.mReviewCount;
    }

    @JsonProperty("review_count")
    public void setReviewCount(int value) {
        this.mReviewCount = value;
    }

    public int getBasePrice() {
        return this.mBasePrice;
    }

    @JsonProperty("base_price")
    public void setBasePrice(int value) {
        this.mBasePrice = value;
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
        parcel.writeTypedList(this.mPosterPictures);
        parcel.writeStringList(this.mCategories);
        parcel.writeString(this.mTripTags);
        parcel.writeString(this.mDisplayText);
        parcel.writeParcelable(this.mCurrency, 0);
        parcel.writeParcelable(this.mHostProfile, 0);
        parcel.writeParcelable(this.mMarket, 0);
        parcel.writeSerializable(this.mProductType);
        parcel.writeBooleanArray(new boolean[]{this.mIsSocialGood, this.mIsSoldOut});
        parcel.writeDouble(this.mLatitude);
        parcel.writeDouble(this.mLongitude);
        parcel.writeFloat(this.mStarRating);
        parcel.writeInt(this.mReviewCount);
        parcel.writeInt(this.mBasePrice);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mPosterPictures = source.createTypedArrayList(Photo.CREATOR);
        this.mCategories = source.createStringArrayList();
        this.mTripTags = source.readString();
        this.mDisplayText = source.readString();
        this.mCurrency = (TripTemplateCurrency) source.readParcelable(TripTemplateCurrency.class.getClassLoader());
        this.mHostProfile = (TripTemplateHostProfile) source.readParcelable(TripTemplateHostProfile.class.getClassLoader());
        this.mMarket = (TripTemplateMarket) source.readParcelable(TripTemplateMarket.class.getClassLoader());
        this.mProductType = (Type) source.readSerializable();
        boolean[] bools = source.createBooleanArray();
        this.mIsSocialGood = bools[0];
        this.mIsSoldOut = bools[1];
        this.mLatitude = source.readDouble();
        this.mLongitude = source.readDouble();
        this.mStarRating = source.readFloat();
        this.mReviewCount = source.readInt();
        this.mBasePrice = source.readInt();
        this.mId = source.readLong();
    }
}
