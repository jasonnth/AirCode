package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCheckInAmenityInformation implements Parcelable {
    @JsonProperty("amenity_id")
    protected long mAmenityId;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("instruction")
    protected String mInstruction;
    @JsonProperty("listing_id")
    protected long mListingId;

    protected GenCheckInAmenityInformation(String instruction, long id, long listingId, long amenityId) {
        this();
        this.mInstruction = instruction;
        this.mId = id;
        this.mListingId = listingId;
        this.mAmenityId = amenityId;
    }

    protected GenCheckInAmenityInformation() {
    }

    public String getInstruction() {
        return this.mInstruction;
    }

    @JsonProperty("instruction")
    public void setInstruction(String value) {
        this.mInstruction = value;
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

    public long getAmenityId() {
        return this.mAmenityId;
    }

    @JsonProperty("amenity_id")
    public void setAmenityId(long value) {
        this.mAmenityId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mInstruction);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mListingId);
        parcel.writeLong(this.mAmenityId);
    }

    public void readFromParcel(Parcel source) {
        this.mInstruction = source.readString();
        this.mId = source.readLong();
        this.mListingId = source.readLong();
        this.mAmenityId = source.readLong();
    }
}
