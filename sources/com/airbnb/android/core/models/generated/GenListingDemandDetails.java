package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingDemandDetails implements Parcelable {
    @JsonProperty("booking_rate")
    protected float mBookingRate;
    @JsonProperty("bookings")
    protected int mBookings;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("page_views")
    protected int mPageViews;

    protected GenListingDemandDetails(String name, float bookingRate, int pageViews, int bookings, long id) {
        this();
        this.mName = name;
        this.mBookingRate = bookingRate;
        this.mPageViews = pageViews;
        this.mBookings = bookings;
        this.mId = id;
    }

    protected GenListingDemandDetails() {
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public float getBookingRate() {
        return this.mBookingRate;
    }

    @JsonProperty("booking_rate")
    public void setBookingRate(float value) {
        this.mBookingRate = value;
    }

    public int getPageViews() {
        return this.mPageViews;
    }

    @JsonProperty("page_views")
    public void setPageViews(int value) {
        this.mPageViews = value;
    }

    public int getBookings() {
        return this.mBookings;
    }

    @JsonProperty("bookings")
    public void setBookings(int value) {
        this.mBookings = value;
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
        parcel.writeString(this.mName);
        parcel.writeFloat(this.mBookingRate);
        parcel.writeInt(this.mPageViews);
        parcel.writeInt(this.mBookings);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mBookingRate = source.readFloat();
        this.mPageViews = source.readInt();
        this.mBookings = source.readInt();
        this.mId = source.readLong();
    }
}
