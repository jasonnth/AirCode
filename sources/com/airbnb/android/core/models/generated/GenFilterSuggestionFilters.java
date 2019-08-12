package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.C6120RoomType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenFilterSuggestionFilters implements Parcelable {
    @JsonProperty("hosting_amenities")
    protected List<Amenity> mAmenities;
    @JsonProperty("ib")
    protected Boolean mInstantBookOnly;
    @JsonProperty("price_max")
    protected int mMaxPrice;
    @JsonProperty("price_min")
    protected int mMinPrice;
    @JsonProperty("min_bedrooms")
    protected int mNumBedrooms;
    @JsonProperty("min_beds")
    protected int mNumBeds;
    @JsonProperty("room_types")
    protected List<C6120RoomType> mRoomTypes;

    protected GenFilterSuggestionFilters(Boolean instantBookOnly, List<Amenity> amenities, List<C6120RoomType> roomTypes, int minPrice, int maxPrice, int numBeds, int numBedrooms) {
        this();
        this.mInstantBookOnly = instantBookOnly;
        this.mAmenities = amenities;
        this.mRoomTypes = roomTypes;
        this.mMinPrice = minPrice;
        this.mMaxPrice = maxPrice;
        this.mNumBeds = numBeds;
        this.mNumBedrooms = numBedrooms;
    }

    protected GenFilterSuggestionFilters() {
    }

    public Boolean isInstantBookOnly() {
        return this.mInstantBookOnly;
    }

    @JsonProperty("ib")
    public void setInstantBookOnly(Boolean value) {
        this.mInstantBookOnly = value;
    }

    public List<Amenity> getAmenities() {
        return this.mAmenities;
    }

    public List<C6120RoomType> getRoomTypes() {
        return this.mRoomTypes;
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mInstantBookOnly);
        parcel.writeTypedList(this.mAmenities);
        parcel.writeTypedList(this.mRoomTypes);
        parcel.writeInt(this.mMinPrice);
        parcel.writeInt(this.mMaxPrice);
        parcel.writeInt(this.mNumBeds);
        parcel.writeInt(this.mNumBedrooms);
    }

    public void readFromParcel(Parcel source) {
        this.mInstantBookOnly = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mAmenities = source.createTypedArrayList(Amenity.CREATOR);
        this.mRoomTypes = source.createTypedArrayList(C6120RoomType.CREATOR);
        this.mMinPrice = source.readInt();
        this.mMaxPrice = source.readInt();
        this.mNumBeds = source.readInt();
        this.mNumBedrooms = source.readInt();
    }
}
