package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.enums.BedDetailType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenBedType implements Parcelable {
    @JsonProperty("quantity")
    protected int mQuantity;
    @JsonProperty("type")
    protected BedDetailType mType;

    protected GenBedType(BedDetailType type, int quantity) {
        this();
        this.mType = type;
        this.mQuantity = quantity;
    }

    protected GenBedType() {
    }

    public BedDetailType getType() {
        return this.mType;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(int value) {
        this.mQuantity = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeSerializable(this.mType);
        parcel.writeInt(this.mQuantity);
    }

    public void readFromParcel(Parcel source) {
        this.mType = (BedDetailType) source.readSerializable();
        this.mQuantity = source.readInt();
    }
}
