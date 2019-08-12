package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.utils.geocoder.AddressComponentType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCuratedLocation implements Parcelable {
    @JsonProperty("subtitle")
    protected String mDescription;
    @JsonProperty("title")
    protected String mLocation;
    @JsonProperty("place_id")
    protected String mPlaceId;
    @JsonProperty("type")
    protected AddressComponentType mType;

    protected GenCuratedLocation(AddressComponentType type, String placeId, String location, String description) {
        this();
        this.mType = type;
        this.mPlaceId = placeId;
        this.mLocation = location;
        this.mDescription = description;
    }

    protected GenCuratedLocation() {
    }

    public AddressComponentType getType() {
        return this.mType;
    }

    public String getPlaceId() {
        return this.mPlaceId;
    }

    @JsonProperty("place_id")
    public void setPlaceId(String value) {
        this.mPlaceId = value;
    }

    public String getLocation() {
        return this.mLocation;
    }

    @JsonProperty("title")
    public void setLocation(String value) {
        this.mLocation = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("subtitle")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mType, 0);
        parcel.writeString(this.mPlaceId);
        parcel.writeString(this.mLocation);
        parcel.writeString(this.mDescription);
    }

    public void readFromParcel(Parcel source) {
        this.mType = (AddressComponentType) source.readParcelable(AddressComponentType.class.getClassLoader());
        this.mPlaceId = source.readString();
        this.mLocation = source.readString();
        this.mDescription = source.readString();
    }
}
