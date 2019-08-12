package com.airbnb.android.core.beta.models.guidebook.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPlaceHour implements Parcelable {
    @JsonProperty("close")
    protected String mClose;
    @JsonProperty("day")
    protected String mDay;
    @JsonProperty("open")
    protected String mOpen;

    protected GenPlaceHour(String day, String open, String close) {
        this();
        this.mDay = day;
        this.mOpen = open;
        this.mClose = close;
    }

    protected GenPlaceHour() {
    }

    public String getDay() {
        return this.mDay;
    }

    @JsonProperty("day")
    public void setDay(String value) {
        this.mDay = value;
    }

    public String getOpen() {
        return this.mOpen;
    }

    @JsonProperty("open")
    public void setOpen(String value) {
        this.mOpen = value;
    }

    public String getClose() {
        return this.mClose;
    }

    @JsonProperty("close")
    public void setClose(String value) {
        this.mClose = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mDay);
        parcel.writeString(this.mOpen);
        parcel.writeString(this.mClose);
    }

    public void readFromParcel(Parcel source) {
        this.mDay = source.readString();
        this.mOpen = source.readString();
        this.mClose = source.readString();
    }
}
