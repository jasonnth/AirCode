package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAdvanceNoticeSetting implements Parcelable {
    @JsonProperty("allow_request_to_book")
    protected int mAllowRequestToBook;
    @JsonProperty("hours")
    protected int mHours;

    protected GenAdvanceNoticeSetting(int hours, int allowRequestToBook) {
        this();
        this.mHours = hours;
        this.mAllowRequestToBook = allowRequestToBook;
    }

    protected GenAdvanceNoticeSetting() {
    }

    public int getHours() {
        return this.mHours;
    }

    @JsonProperty("hours")
    public void setHours(int value) {
        this.mHours = value;
    }

    public int getAllowRequestToBook() {
        return this.mAllowRequestToBook;
    }

    @JsonProperty("allow_request_to_book")
    public void setAllowRequestToBook(int value) {
        this.mAllowRequestToBook = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mHours);
        parcel.writeInt(this.mAllowRequestToBook);
    }

    public void readFromParcel(Parcel source) {
        this.mHours = source.readInt();
        this.mAllowRequestToBook = source.readInt();
    }
}
