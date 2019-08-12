package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.SpecialOffer;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSpecialOffer implements Parcelable {
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("expires_at")
    protected AirDateTime mExpiresAt;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("is_expired")
    protected boolean mIsExpired;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("listing_name")
    protected String mListingName;
    @JsonProperty("message")
    protected String mMessage;
    @JsonProperty("native_currency")
    protected String mNativeCurrency;
    @JsonProperty("nights")
    protected int mNights;
    @JsonProperty("number_of_guests")
    protected int mNumberOfGuests;
    @JsonProperty("is_preapproval")
    protected boolean mPreApproval;
    @JsonProperty("price")
    protected double mPrice;
    @JsonProperty("price_native")
    protected int mPriceNative;
    @JsonProperty("special_offer")
    protected SpecialOffer mSpecialOffer;
    @JsonProperty("start_date")
    protected AirDate mStartDate;

    protected GenSpecialOffer(AirDate startDate, AirDateTime createdAt, AirDateTime expiresAt, SpecialOffer specialOffer, String message, String nativeCurrency, String listingName, boolean preApproval, boolean isExpired, double price, int priceNative, int nights, int numberOfGuests, long id, long listingId) {
        this();
        this.mStartDate = startDate;
        this.mCreatedAt = createdAt;
        this.mExpiresAt = expiresAt;
        this.mSpecialOffer = specialOffer;
        this.mMessage = message;
        this.mNativeCurrency = nativeCurrency;
        this.mListingName = listingName;
        this.mPreApproval = preApproval;
        this.mIsExpired = isExpired;
        this.mPrice = price;
        this.mPriceNative = priceNative;
        this.mNights = nights;
        this.mNumberOfGuests = numberOfGuests;
        this.mId = id;
        this.mListingId = listingId;
    }

    protected GenSpecialOffer() {
    }

    public AirDate getStartDate() {
        return this.mStartDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(AirDate value) {
        this.mStartDate = value;
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public AirDateTime getExpiresAt() {
        return this.mExpiresAt;
    }

    @JsonProperty("expires_at")
    public void setExpiresAt(AirDateTime value) {
        this.mExpiresAt = value;
    }

    public SpecialOffer getSpecialOffer() {
        return this.mSpecialOffer;
    }

    @JsonProperty("special_offer")
    public void setSpecialOffer(SpecialOffer value) {
        this.mSpecialOffer = value;
    }

    public String getMessage() {
        return this.mMessage;
    }

    @JsonProperty("message")
    public void setMessage(String value) {
        this.mMessage = value;
    }

    public String getNativeCurrency() {
        return this.mNativeCurrency;
    }

    @JsonProperty("native_currency")
    public void setNativeCurrency(String value) {
        this.mNativeCurrency = value;
    }

    public String getListingName() {
        return this.mListingName;
    }

    @JsonProperty("listing_name")
    public void setListingName(String value) {
        this.mListingName = value;
    }

    public boolean isPreApproval() {
        return this.mPreApproval;
    }

    @JsonProperty("is_preapproval")
    public void setPreApproval(boolean value) {
        this.mPreApproval = value;
    }

    public boolean isExpired() {
        return this.mIsExpired;
    }

    @JsonProperty("is_expired")
    public void setIsExpired(boolean value) {
        this.mIsExpired = value;
    }

    public double getPrice() {
        return this.mPrice;
    }

    @JsonProperty("price")
    public void setPrice(double value) {
        this.mPrice = value;
    }

    public int getPriceNative() {
        return this.mPriceNative;
    }

    @JsonProperty("price_native")
    public void setPriceNative(int value) {
        this.mPriceNative = value;
    }

    public int getNights() {
        return this.mNights;
    }

    @JsonProperty("nights")
    public void setNights(int value) {
        this.mNights = value;
    }

    public int getNumberOfGuests() {
        return this.mNumberOfGuests;
    }

    @JsonProperty("number_of_guests")
    public void setNumberOfGuests(int value) {
        this.mNumberOfGuests = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeParcelable(this.mExpiresAt, 0);
        parcel.writeParcelable(this.mSpecialOffer, 0);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mNativeCurrency);
        parcel.writeString(this.mListingName);
        parcel.writeBooleanArray(new boolean[]{this.mPreApproval, this.mIsExpired});
        parcel.writeDouble(this.mPrice);
        parcel.writeInt(this.mPriceNative);
        parcel.writeInt(this.mNights);
        parcel.writeInt(this.mNumberOfGuests);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mExpiresAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mSpecialOffer = (SpecialOffer) source.readParcelable(SpecialOffer.class.getClassLoader());
        this.mMessage = source.readString();
        this.mNativeCurrency = source.readString();
        this.mListingName = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mPreApproval = bools[0];
        this.mIsExpired = bools[1];
        this.mPrice = source.readDouble();
        this.mPriceNative = source.readInt();
        this.mNights = source.readInt();
        this.mNumberOfGuests = source.readInt();
        this.mId = source.readLong();
        this.mListingId = source.readLong();
    }
}
