package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.SelectLayoutType;
import com.airbnb.android.core.models.SelectRoomType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSelectRoomDescription implements Parcelable {
    @JsonProperty("layout_types")
    protected List<SelectLayoutType> mLayoutTypes;
    @JsonProperty("room_types")
    protected List<SelectRoomType> mRoomTypes;

    protected GenSelectRoomDescription(List<SelectLayoutType> layoutTypes, List<SelectRoomType> roomTypes) {
        this();
        this.mLayoutTypes = layoutTypes;
        this.mRoomTypes = roomTypes;
    }

    protected GenSelectRoomDescription() {
    }

    public List<SelectLayoutType> getLayoutTypes() {
        return this.mLayoutTypes;
    }

    @JsonProperty("layout_types")
    public void setLayoutTypes(List<SelectLayoutType> value) {
        this.mLayoutTypes = value;
    }

    public List<SelectRoomType> getRoomTypes() {
        return this.mRoomTypes;
    }

    @JsonProperty("room_types")
    public void setRoomTypes(List<SelectRoomType> value) {
        this.mRoomTypes = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mLayoutTypes);
        parcel.writeTypedList(this.mRoomTypes);
    }

    public void readFromParcel(Parcel source) {
        this.mLayoutTypes = source.createTypedArrayList(SelectLayoutType.CREATOR);
        this.mRoomTypes = source.createTypedArrayList(SelectRoomType.CREATOR);
    }
}
