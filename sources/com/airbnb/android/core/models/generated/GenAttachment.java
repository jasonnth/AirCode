package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAttachment implements Parcelable {
    @JsonProperty("attachable_id")
    protected long mAttachableId;
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("size")
    protected int mSize;
    @JsonProperty("thumb_url")
    protected String mThumbUrl;
    @JsonProperty("type")
    protected String mType;
    @JsonProperty("updated_at")
    protected AirDateTime mUpdatedAt;
    @JsonProperty("url")
    protected String mUrl;

    protected GenAttachment(AirDateTime createdAt, AirDateTime updatedAt, String name, String thumbUrl, String type, String url, int size, long attachableId, long id) {
        this();
        this.mCreatedAt = createdAt;
        this.mUpdatedAt = updatedAt;
        this.mName = name;
        this.mThumbUrl = thumbUrl;
        this.mType = type;
        this.mUrl = url;
        this.mSize = size;
        this.mAttachableId = attachableId;
        this.mId = id;
    }

    protected GenAttachment() {
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public AirDateTime getUpdatedAt() {
        return this.mUpdatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(AirDateTime value) {
        this.mUpdatedAt = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getThumbUrl() {
        return this.mThumbUrl;
    }

    @JsonProperty("thumb_url")
    public void setThumbUrl(String value) {
        this.mThumbUrl = value;
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public String getUrl() {
        return this.mUrl;
    }

    @JsonProperty("url")
    public void setUrl(String value) {
        this.mUrl = value;
    }

    public int getSize() {
        return this.mSize;
    }

    @JsonProperty("size")
    public void setSize(int value) {
        this.mSize = value;
    }

    public long getAttachableId() {
        return this.mAttachableId;
    }

    @JsonProperty("attachable_id")
    public void setAttachableId(long value) {
        this.mAttachableId = value;
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
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeParcelable(this.mUpdatedAt, 0);
        parcel.writeString(this.mName);
        parcel.writeString(this.mThumbUrl);
        parcel.writeString(this.mType);
        parcel.writeString(this.mUrl);
        parcel.writeInt(this.mSize);
        parcel.writeLong(this.mAttachableId);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mUpdatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mName = source.readString();
        this.mThumbUrl = source.readString();
        this.mType = source.readString();
        this.mUrl = source.readString();
        this.mSize = source.readInt();
        this.mAttachableId = source.readLong();
        this.mId = source.readLong();
    }
}
