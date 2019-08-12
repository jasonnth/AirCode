package com.airbnb.android.core.beta.models.guidebook.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenLocalAttraction implements Parcelable {
    @JsonProperty("category")
    protected String mCategory;
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("latitude")
    protected double mLatitude;
    @JsonProperty("longitude")
    protected double mLongitude;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("photo")
    protected String mPhoto;
    @JsonProperty("pin")
    protected String mPin;
    @JsonProperty("place")
    protected Place mPlace;
    @JsonProperty("price")
    protected int mPrice;
    @JsonProperty("resource_id")
    protected long mResourceId;
    @JsonProperty("resource_type")
    protected String mResourceType;

    protected GenLocalAttraction(Place place, String name, String description, String resourceType, String pin, String photo, String category, double latitude, double longitude, int price, long resourceId) {
        this();
        this.mPlace = place;
        this.mName = name;
        this.mDescription = description;
        this.mResourceType = resourceType;
        this.mPin = pin;
        this.mPhoto = photo;
        this.mCategory = category;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mPrice = price;
        this.mResourceId = resourceId;
    }

    protected GenLocalAttraction() {
    }

    public Place getPlace() {
        return this.mPlace;
    }

    @JsonProperty("place")
    public void setPlace(Place value) {
        this.mPlace = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getResourceType() {
        return this.mResourceType;
    }

    @JsonProperty("resource_type")
    public void setResourceType(String value) {
        this.mResourceType = value;
    }

    public String getPin() {
        return this.mPin;
    }

    @JsonProperty("pin")
    public void setPin(String value) {
        this.mPin = value;
    }

    public String getPhoto() {
        return this.mPhoto;
    }

    @JsonProperty("photo")
    public void setPhoto(String value) {
        this.mPhoto = value;
    }

    public String getCategory() {
        return this.mCategory;
    }

    @JsonProperty("category")
    public void setCategory(String value) {
        this.mCategory = value;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(double value) {
        this.mLatitude = value;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(double value) {
        this.mLongitude = value;
    }

    public int getPrice() {
        return this.mPrice;
    }

    @JsonProperty("price")
    public void setPrice(int value) {
        this.mPrice = value;
    }

    public long getResourceId() {
        return this.mResourceId;
    }

    @JsonProperty("resource_id")
    public void setResourceId(long value) {
        this.mResourceId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mPlace, 0);
        parcel.writeString(this.mName);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mResourceType);
        parcel.writeString(this.mPin);
        parcel.writeString(this.mPhoto);
        parcel.writeString(this.mCategory);
        parcel.writeDouble(this.mLatitude);
        parcel.writeDouble(this.mLongitude);
        parcel.writeInt(this.mPrice);
        parcel.writeLong(this.mResourceId);
    }

    public void readFromParcel(Parcel source) {
        this.mPlace = (Place) source.readParcelable(Place.class.getClassLoader());
        this.mName = source.readString();
        this.mDescription = source.readString();
        this.mResourceType = source.readString();
        this.mPin = source.readString();
        this.mPhoto = source.readString();
        this.mCategory = source.readString();
        this.mLatitude = source.readDouble();
        this.mLongitude = source.readDouble();
        this.mPrice = source.readInt();
        this.mResourceId = source.readLong();
    }
}
