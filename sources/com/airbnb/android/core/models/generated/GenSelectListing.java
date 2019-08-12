package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.SelectListingRoom;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSelectListing implements Parcelable {
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("rooms")
    protected List<SelectListingRoom> mRooms;
    @JsonProperty("summary")
    protected String mSummary;

    protected GenSelectListing(List<SelectListingRoom> rooms, String name, String summary) {
        this();
        this.mRooms = rooms;
        this.mName = name;
        this.mSummary = summary;
    }

    protected GenSelectListing() {
    }

    public List<SelectListingRoom> getRooms() {
        return this.mRooms;
    }

    @JsonProperty("rooms")
    public void setRooms(List<SelectListingRoom> value) {
        this.mRooms = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getSummary() {
        return this.mSummary;
    }

    @JsonProperty("summary")
    public void setSummary(String value) {
        this.mSummary = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mRooms);
        parcel.writeString(this.mName);
        parcel.writeString(this.mSummary);
    }

    public void readFromParcel(Parcel source) {
        this.mRooms = source.createTypedArrayList(SelectListingRoom.CREATOR);
        this.mName = source.readString();
        this.mSummary = source.readString();
    }
}
