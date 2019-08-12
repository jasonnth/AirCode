package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenNestedListing implements Parcelable {
    @JsonProperty("active")
    protected boolean mActive;
    @JsonProperty("child_listing_ids")
    protected List<Long> mChildListingIds;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("parent_listing_id")
    protected Long mParentListingId;
    @JsonProperty("room_type")
    protected String mRoomType;
    @JsonProperty("thumbnail_url")
    protected String mThumbnailUrl;
    @JsonProperty("zipcode")
    protected String mZipCode;

    protected GenNestedListing(List<Long> childListingIds, Long parentListingId, String name, String roomType, String thumbnailUrl, String zipCode, boolean active, long id) {
        this();
        this.mChildListingIds = childListingIds;
        this.mParentListingId = parentListingId;
        this.mName = name;
        this.mRoomType = roomType;
        this.mThumbnailUrl = thumbnailUrl;
        this.mZipCode = zipCode;
        this.mActive = active;
        this.mId = id;
    }

    protected GenNestedListing() {
    }

    public List<Long> getChildListingIds() {
        return this.mChildListingIds;
    }

    @JsonProperty("child_listing_ids")
    public void setChildListingIds(List<Long> value) {
        this.mChildListingIds = value;
    }

    public Long getParentListingId() {
        return this.mParentListingId;
    }

    @JsonProperty("parent_listing_id")
    public void setParentListingId(Long value) {
        this.mParentListingId = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getRoomType() {
        return this.mRoomType;
    }

    public String getThumbnailUrl() {
        return this.mThumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String value) {
        this.mThumbnailUrl = value;
    }

    public String getZipCode() {
        return this.mZipCode;
    }

    @JsonProperty("zipcode")
    public void setZipCode(String value) {
        this.mZipCode = value;
    }

    public boolean isActive() {
        return this.mActive;
    }

    @JsonProperty("active")
    public void setActive(boolean value) {
        this.mActive = value;
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
        parcel.writeValue(this.mChildListingIds);
        parcel.writeValue(this.mParentListingId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mRoomType);
        parcel.writeString(this.mThumbnailUrl);
        parcel.writeString(this.mZipCode);
        parcel.writeBooleanArray(new boolean[]{this.mActive});
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mChildListingIds = (List) source.readValue(null);
        this.mParentListingId = (Long) source.readValue(Long.class.getClassLoader());
        this.mName = source.readString();
        this.mRoomType = source.readString();
        this.mThumbnailUrl = source.readString();
        this.mZipCode = source.readString();
        this.mActive = source.createBooleanArray()[0];
        this.mId = source.readLong();
    }
}
