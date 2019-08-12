package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCohostInquiry implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("partial_address_native")
    protected String mPartialAddressNative;
    @JsonProperty("person_capacity")
    protected String mPersonCapacity;
    @JsonProperty("room_type")
    protected String mRoomType;

    protected GenCohostInquiry(String roomType, String personCapacity, String partialAddressNative, long id) {
        this();
        this.mRoomType = roomType;
        this.mPersonCapacity = personCapacity;
        this.mPartialAddressNative = partialAddressNative;
        this.mId = id;
    }

    protected GenCohostInquiry() {
    }

    public String getRoomType() {
        return this.mRoomType;
    }

    @JsonProperty("room_type")
    public void setRoomType(String value) {
        this.mRoomType = value;
    }

    public String getPersonCapacity() {
        return this.mPersonCapacity;
    }

    @JsonProperty("person_capacity")
    public void setPersonCapacity(String value) {
        this.mPersonCapacity = value;
    }

    public String getPartialAddressNative() {
        return this.mPartialAddressNative;
    }

    @JsonProperty("partial_address_native")
    public void setPartialAddressNative(String value) {
        this.mPartialAddressNative = value;
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
        parcel.writeString(this.mRoomType);
        parcel.writeString(this.mPersonCapacity);
        parcel.writeString(this.mPartialAddressNative);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mRoomType = source.readString();
        this.mPersonCapacity = source.readString();
        this.mPartialAddressNative = source.readString();
        this.mId = source.readLong();
    }
}
