package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.NestedListing;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenNestedBusyDetail implements Parcelable {
    @JsonProperty("nested_listing")
    protected NestedListing mNestedListing;
    @JsonProperty("reservation_id")
    protected Long mReservationId;
    @JsonProperty("type")
    protected String mType;

    protected GenNestedBusyDetail(Long reservationId, NestedListing nestedListing, String type) {
        this();
        this.mReservationId = reservationId;
        this.mNestedListing = nestedListing;
        this.mType = type;
    }

    protected GenNestedBusyDetail() {
    }

    public Long getReservationId() {
        return this.mReservationId;
    }

    @JsonProperty("reservation_id")
    public void setReservationId(Long value) {
        this.mReservationId = value;
    }

    public NestedListing getNestedListing() {
        return this.mNestedListing;
    }

    @JsonProperty("nested_listing")
    public void setNestedListing(NestedListing value) {
        this.mNestedListing = value;
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
        parcel.writeValue(this.mReservationId);
        parcel.writeParcelable(this.mNestedListing, 0);
        parcel.writeString(this.mType);
    }

    public void readFromParcel(Parcel source) {
        this.mReservationId = (Long) source.readValue(Long.class.getClassLoader());
        this.mNestedListing = (NestedListing) source.readParcelable(NestedListing.class.getClassLoader());
        this.mType = source.readString();
    }
}
