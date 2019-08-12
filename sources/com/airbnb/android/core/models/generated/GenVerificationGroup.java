package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Verification;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenVerificationGroup implements Parcelable {
    @JsonProperty("items")
    protected List<Verification> mItems;
    @JsonProperty("type")
    protected String mType;

    protected GenVerificationGroup(List<Verification> items, String type) {
        this();
        this.mItems = items;
        this.mType = type;
    }

    protected GenVerificationGroup() {
    }

    public List<Verification> getItems() {
        return this.mItems;
    }

    @JsonProperty("items")
    public void setItems(List<Verification> value) {
        this.mItems = value;
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mItems);
        parcel.writeString(this.mType);
    }

    public void readFromParcel(Parcel source) {
        this.mItems = source.createTypedArrayList(Verification.CREATOR);
        this.mType = source.readString();
    }
}
