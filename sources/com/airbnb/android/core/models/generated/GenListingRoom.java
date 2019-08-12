package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.BedType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingRoom implements Parcelable {
    @JsonProperty("beds")
    protected List<BedType> mBedTypes;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("room_number")
    protected int mRoomNumber;

    protected GenListingRoom(List<BedType> bedTypes, int roomNumber, long id) {
        this();
        this.mBedTypes = bedTypes;
        this.mRoomNumber = roomNumber;
        this.mId = id;
    }

    protected GenListingRoom() {
    }

    public List<BedType> getBedTypes() {
        return this.mBedTypes;
    }

    @JsonProperty("beds")
    public void setBedTypes(List<BedType> value) {
        this.mBedTypes = value;
    }

    public int getRoomNumber() {
        return this.mRoomNumber;
    }

    @JsonProperty("room_number")
    public void setRoomNumber(int value) {
        this.mRoomNumber = value;
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
        parcel.writeTypedList(this.mBedTypes);
        parcel.writeInt(this.mRoomNumber);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mBedTypes = source.createTypedArrayList(BedType.CREATOR);
        this.mRoomNumber = source.readInt();
        this.mId = source.readLong();
    }
}
