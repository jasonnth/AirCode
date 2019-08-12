package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.CurrencyAmount;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPaidAmenity implements Parcelable {
    @JsonProperty("amenity_type")
    protected String mAmenityType;
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("disclosure_text")
    protected String mDisclosure_text;
    @JsonProperty("disclosure_url")
    protected String mDisclosure_url;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("localized_total_price")
    protected CurrencyAmount mLocalizedTotalPrice;
    @JsonProperty("title")
    protected String mTitle;

    protected GenPaidAmenity(CurrencyAmount localizedTotalPrice, String title, String description, String amenityType, String disclosure_url, String disclosure_text, long id) {
        this();
        this.mLocalizedTotalPrice = localizedTotalPrice;
        this.mTitle = title;
        this.mDescription = description;
        this.mAmenityType = amenityType;
        this.mDisclosure_url = disclosure_url;
        this.mDisclosure_text = disclosure_text;
        this.mId = id;
    }

    protected GenPaidAmenity() {
    }

    public CurrencyAmount getLocalizedTotalPrice() {
        return this.mLocalizedTotalPrice;
    }

    @JsonProperty("localized_total_price")
    public void setLocalizedTotalPrice(CurrencyAmount value) {
        this.mLocalizedTotalPrice = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getAmenityType() {
        return this.mAmenityType;
    }

    @JsonProperty("amenity_type")
    public void setAmenityType(String value) {
        this.mAmenityType = value;
    }

    public String getDisclosure_url() {
        return this.mDisclosure_url;
    }

    @JsonProperty("disclosure_url")
    public void setDisclosure_url(String value) {
        this.mDisclosure_url = value;
    }

    public String getDisclosure_text() {
        return this.mDisclosure_text;
    }

    @JsonProperty("disclosure_text")
    public void setDisclosure_text(String value) {
        this.mDisclosure_text = value;
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
        parcel.writeParcelable(this.mLocalizedTotalPrice, 0);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mAmenityType);
        parcel.writeString(this.mDisclosure_url);
        parcel.writeString(this.mDisclosure_text);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mLocalizedTotalPrice = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mTitle = source.readString();
        this.mDescription = source.readString();
        this.mAmenityType = source.readString();
        this.mDisclosure_url = source.readString();
        this.mDisclosure_text = source.readString();
        this.mId = source.readLong();
    }
}
