package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTripTemplateHost implements Parcelable {
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("first_name")
    protected String mFirstName;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("profile_pic_path")
    protected String mPictureUrl;

    protected GenTripTemplateHost(AirDateTime createdAt, String pictureUrl, String firstName, long id) {
        this();
        this.mCreatedAt = createdAt;
        this.mPictureUrl = pictureUrl;
        this.mFirstName = firstName;
        this.mId = id;
    }

    protected GenTripTemplateHost() {
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }

    @JsonProperty("profile_pic_path")
    public void setPictureUrl(String value) {
        this.mPictureUrl = value;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String value) {
        this.mFirstName = value;
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
        parcel.writeString(this.mPictureUrl);
        parcel.writeString(this.mFirstName);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mPictureUrl = source.readString();
        this.mFirstName = source.readString();
        this.mId = source.readLong();
    }
}
