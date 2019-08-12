package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingAmenityInformation implements Parcelable {
    @JsonProperty("id")
    protected int mAmenityId;
    @JsonProperty("icon")
    protected String mIcon;
    @JsonProperty("hosting_amenity_id")
    protected Long mListingAmenityId;
    @JsonProperty("name")
    protected String mName;

    protected GenListingAmenityInformation(Long listingAmenityId, String name, String icon, int amenityId) {
        this();
        this.mListingAmenityId = listingAmenityId;
        this.mName = name;
        this.mIcon = icon;
        this.mAmenityId = amenityId;
    }

    protected GenListingAmenityInformation() {
    }

    public Long getListingAmenityId() {
        return this.mListingAmenityId;
    }

    @JsonProperty("hosting_amenity_id")
    public void setListingAmenityId(Long value) {
        this.mListingAmenityId = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getIcon() {
        return this.mIcon;
    }

    @JsonProperty("icon")
    public void setIcon(String value) {
        this.mIcon = value;
    }

    public int getAmenityId() {
        return this.mAmenityId;
    }

    @JsonProperty("id")
    public void setAmenityId(int value) {
        this.mAmenityId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mListingAmenityId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mIcon);
        parcel.writeInt(this.mAmenityId);
    }

    public void readFromParcel(Parcel source) {
        this.mListingAmenityId = (Long) source.readValue(Long.class.getClassLoader());
        this.mName = source.readString();
        this.mIcon = source.readString();
        this.mAmenityId = source.readInt();
    }
}
