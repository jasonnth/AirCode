package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.SearchFacetWithIntKey;
import com.airbnb.android.core.models.SearchFacetWithStringKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSearchFacets implements Parcelable {
    @JsonProperty("availability")
    protected List<SearchFacetWithStringKey> mAvailability;
    @JsonProperty("facilities_amenities")
    protected List<SearchFacetWithIntKey> mFacilitiesAmenities;
    @JsonProperty("house_rules_amenities")
    protected List<SearchFacetWithIntKey> mHouseRulesAmenities;
    @JsonProperty("other_amenities")
    protected List<SearchFacetWithIntKey> mOtherAmenities;
    @JsonProperty("room_type")
    protected List<SearchFacetWithStringKey> mRoomType;
    @JsonProperty("top_other_amenities")
    protected List<SearchFacetWithIntKey> mTopOtherAmenities;

    protected GenSearchFacets(List<SearchFacetWithIntKey> facilitiesAmenities, List<SearchFacetWithIntKey> houseRulesAmenities, List<SearchFacetWithIntKey> otherAmenities, List<SearchFacetWithIntKey> topOtherAmenities, List<SearchFacetWithStringKey> roomType, List<SearchFacetWithStringKey> availability) {
        this();
        this.mFacilitiesAmenities = facilitiesAmenities;
        this.mHouseRulesAmenities = houseRulesAmenities;
        this.mOtherAmenities = otherAmenities;
        this.mTopOtherAmenities = topOtherAmenities;
        this.mRoomType = roomType;
        this.mAvailability = availability;
    }

    protected GenSearchFacets() {
    }

    public List<SearchFacetWithIntKey> getFacilitiesAmenities() {
        return this.mFacilitiesAmenities;
    }

    @JsonProperty("facilities_amenities")
    public void setFacilitiesAmenities(List<SearchFacetWithIntKey> value) {
        this.mFacilitiesAmenities = value;
    }

    public List<SearchFacetWithIntKey> getHouseRulesAmenities() {
        return this.mHouseRulesAmenities;
    }

    @JsonProperty("house_rules_amenities")
    public void setHouseRulesAmenities(List<SearchFacetWithIntKey> value) {
        this.mHouseRulesAmenities = value;
    }

    public List<SearchFacetWithIntKey> getOtherAmenities() {
        return this.mOtherAmenities;
    }

    @JsonProperty("other_amenities")
    public void setOtherAmenities(List<SearchFacetWithIntKey> value) {
        this.mOtherAmenities = value;
    }

    public List<SearchFacetWithIntKey> getTopOtherAmenities() {
        return this.mTopOtherAmenities;
    }

    @JsonProperty("top_other_amenities")
    public void setTopOtherAmenities(List<SearchFacetWithIntKey> value) {
        this.mTopOtherAmenities = value;
    }

    public List<SearchFacetWithStringKey> getRoomType() {
        return this.mRoomType;
    }

    @JsonProperty("room_type")
    public void setRoomType(List<SearchFacetWithStringKey> value) {
        this.mRoomType = value;
    }

    public List<SearchFacetWithStringKey> getAvailability() {
        return this.mAvailability;
    }

    @JsonProperty("availability")
    public void setAvailability(List<SearchFacetWithStringKey> value) {
        this.mAvailability = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mFacilitiesAmenities);
        parcel.writeTypedList(this.mHouseRulesAmenities);
        parcel.writeTypedList(this.mOtherAmenities);
        parcel.writeTypedList(this.mTopOtherAmenities);
        parcel.writeTypedList(this.mRoomType);
        parcel.writeTypedList(this.mAvailability);
    }

    public void readFromParcel(Parcel source) {
        this.mFacilitiesAmenities = source.createTypedArrayList(SearchFacetWithIntKey.CREATOR);
        this.mHouseRulesAmenities = source.createTypedArrayList(SearchFacetWithIntKey.CREATOR);
        this.mOtherAmenities = source.createTypedArrayList(SearchFacetWithIntKey.CREATOR);
        this.mTopOtherAmenities = source.createTypedArrayList(SearchFacetWithIntKey.CREATOR);
        this.mRoomType = source.createTypedArrayList(SearchFacetWithStringKey.CREATOR);
        this.mAvailability = source.createTypedArrayList(SearchFacetWithStringKey.CREATOR);
    }
}
