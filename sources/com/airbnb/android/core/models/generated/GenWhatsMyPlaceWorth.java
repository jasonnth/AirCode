package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.C6120RoomType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenWhatsMyPlaceWorth implements Parcelable {
    @JsonProperty("localized_location")
    protected String mLocalizedLocation;
    @JsonProperty("localized_price")
    protected float mLocalizedPrice;
    @JsonProperty("localized_price_formatted")
    protected String mLocalizedPriceFormatted;
    @JsonProperty("room_type")
    protected C6120RoomType mRoomType;

    protected GenWhatsMyPlaceWorth(C6120RoomType roomType, String localizedLocation, String localizedPriceFormatted, float localizedPrice) {
        this();
        this.mRoomType = roomType;
        this.mLocalizedLocation = localizedLocation;
        this.mLocalizedPriceFormatted = localizedPriceFormatted;
        this.mLocalizedPrice = localizedPrice;
    }

    protected GenWhatsMyPlaceWorth() {
    }

    public C6120RoomType getRoomType() {
        return this.mRoomType;
    }

    public String getLocalizedLocation() {
        return this.mLocalizedLocation;
    }

    @JsonProperty("localized_location")
    public void setLocalizedLocation(String value) {
        this.mLocalizedLocation = value;
    }

    public String getLocalizedPriceFormatted() {
        return this.mLocalizedPriceFormatted;
    }

    @JsonProperty("localized_price_formatted")
    public void setLocalizedPriceFormatted(String value) {
        this.mLocalizedPriceFormatted = value;
    }

    public float getLocalizedPrice() {
        return this.mLocalizedPrice;
    }

    @JsonProperty("localized_price")
    public void setLocalizedPrice(float value) {
        this.mLocalizedPrice = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mRoomType, 0);
        parcel.writeString(this.mLocalizedLocation);
        parcel.writeString(this.mLocalizedPriceFormatted);
        parcel.writeFloat(this.mLocalizedPrice);
    }

    public void readFromParcel(Parcel source) {
        this.mRoomType = (C6120RoomType) source.readParcelable(C6120RoomType.class.getClassLoader());
        this.mLocalizedLocation = source.readString();
        this.mLocalizedPriceFormatted = source.readString();
        this.mLocalizedPrice = source.readFloat();
    }
}
