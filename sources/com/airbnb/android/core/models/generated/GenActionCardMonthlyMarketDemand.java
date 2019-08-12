package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenActionCardMonthlyMarketDemand implements Parcelable {
    @JsonProperty("available_listings")
    protected float mAvailableListings;
    @JsonProperty("bookings")
    protected float mBookings;
    @JsonProperty("date")
    protected AirDate mDate;
    @JsonProperty("inquiries")
    protected float mInquiries;
    @JsonProperty("page_views")
    protected float mPageViews;

    protected GenActionCardMonthlyMarketDemand(AirDate date, float pageViews, float inquiries, float bookings, float availableListings) {
        this();
        this.mDate = date;
        this.mPageViews = pageViews;
        this.mInquiries = inquiries;
        this.mBookings = bookings;
        this.mAvailableListings = availableListings;
    }

    protected GenActionCardMonthlyMarketDemand() {
    }

    public AirDate getDate() {
        return this.mDate;
    }

    @JsonProperty("date")
    public void setDate(AirDate value) {
        this.mDate = value;
    }

    public float getPageViews() {
        return this.mPageViews;
    }

    @JsonProperty("page_views")
    public void setPageViews(float value) {
        this.mPageViews = value;
    }

    public float getInquiries() {
        return this.mInquiries;
    }

    @JsonProperty("inquiries")
    public void setInquiries(float value) {
        this.mInquiries = value;
    }

    public float getBookings() {
        return this.mBookings;
    }

    @JsonProperty("bookings")
    public void setBookings(float value) {
        this.mBookings = value;
    }

    public float getAvailableListings() {
        return this.mAvailableListings;
    }

    @JsonProperty("available_listings")
    public void setAvailableListings(float value) {
        this.mAvailableListings = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mDate, 0);
        parcel.writeFloat(this.mPageViews);
        parcel.writeFloat(this.mInquiries);
        parcel.writeFloat(this.mBookings);
        parcel.writeFloat(this.mAvailableListings);
    }

    public void readFromParcel(Parcel source) {
        this.mDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mPageViews = source.readFloat();
        this.mInquiries = source.readFloat();
        this.mBookings = source.readFloat();
        this.mAvailableListings = source.readFloat();
    }
}
