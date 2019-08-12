package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPlaceReservation implements Parcelable {
    @JsonProperty("id")
    protected long mId;

    protected GenPlaceReservation(long id) {
        this();
        this.mId = id;
    }

    protected GenPlaceReservation() {
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
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mId = source.readLong();
    }
}
