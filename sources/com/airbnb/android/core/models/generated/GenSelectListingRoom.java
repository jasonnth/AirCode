package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.SelectRoomMedia;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSelectListingRoom implements Parcelable {
    @JsonProperty("layout_number")
    protected int mLayoutNumber;
    @JsonProperty("layout_type")
    protected int mLayoutType;
    @JsonProperty("media")
    protected List<SelectRoomMedia> mMedia;
    @JsonProperty("room_id")
    protected long mRoomId;
    @JsonProperty("room_number")
    protected int mRoomNumber;
    @JsonProperty("room_type")
    protected int mRoomType;

    protected GenSelectListingRoom(List<SelectRoomMedia> media, int layoutType, int layoutNumber, int roomType, int roomNumber, long roomId) {
        this();
        this.mMedia = media;
        this.mLayoutType = layoutType;
        this.mLayoutNumber = layoutNumber;
        this.mRoomType = roomType;
        this.mRoomNumber = roomNumber;
        this.mRoomId = roomId;
    }

    protected GenSelectListingRoom() {
    }

    public List<SelectRoomMedia> getMedia() {
        return this.mMedia;
    }

    @JsonProperty("media")
    public void setMedia(List<SelectRoomMedia> value) {
        this.mMedia = value;
    }

    public int getLayoutType() {
        return this.mLayoutType;
    }

    @JsonProperty("layout_type")
    public void setLayoutType(int value) {
        this.mLayoutType = value;
    }

    public int getLayoutNumber() {
        return this.mLayoutNumber;
    }

    @JsonProperty("layout_number")
    public void setLayoutNumber(int value) {
        this.mLayoutNumber = value;
    }

    public int getRoomType() {
        return this.mRoomType;
    }

    @JsonProperty("room_type")
    public void setRoomType(int value) {
        this.mRoomType = value;
    }

    public int getRoomNumber() {
        return this.mRoomNumber;
    }

    @JsonProperty("room_number")
    public void setRoomNumber(int value) {
        this.mRoomNumber = value;
    }

    public long getRoomId() {
        return this.mRoomId;
    }

    @JsonProperty("room_id")
    public void setRoomId(long value) {
        this.mRoomId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mMedia);
        parcel.writeInt(this.mLayoutType);
        parcel.writeInt(this.mLayoutNumber);
        parcel.writeInt(this.mRoomType);
        parcel.writeInt(this.mRoomNumber);
        parcel.writeLong(this.mRoomId);
    }

    public void readFromParcel(Parcel source) {
        this.mMedia = source.createTypedArrayList(SelectRoomMedia.CREATOR);
        this.mLayoutType = source.readInt();
        this.mLayoutNumber = source.readInt();
        this.mRoomType = source.readInt();
        this.mRoomNumber = source.readInt();
        this.mRoomId = source.readLong();
    }
}
