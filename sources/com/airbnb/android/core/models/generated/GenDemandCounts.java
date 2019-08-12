package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenDemandCounts implements Parcelable {
    @JsonProperty("bookings")
    protected int mBookings;
    @JsonProperty("date")
    protected AirDate mDate;
    @JsonProperty("inquiries")
    protected int mInquiries;
    @JsonProperty("page_views")
    protected int mPageViews;

    protected GenDemandCounts(AirDate date, int pageViews, int inquiries, int bookings) {
        this();
        this.mDate = date;
        this.mPageViews = pageViews;
        this.mInquiries = inquiries;
        this.mBookings = bookings;
    }

    protected GenDemandCounts() {
    }

    public AirDate getDate() {
        return this.mDate;
    }

    @JsonProperty("date")
    public void setDate(AirDate value) {
        this.mDate = value;
    }

    public int getPageViews() {
        return this.mPageViews;
    }

    @JsonProperty("page_views")
    public void setPageViews(int value) {
        this.mPageViews = value;
    }

    public int getInquiries() {
        return this.mInquiries;
    }

    @JsonProperty("inquiries")
    public void setInquiries(int value) {
        this.mInquiries = value;
    }

    public int getBookings() {
        return this.mBookings;
    }

    @JsonProperty("bookings")
    public void setBookings(int value) {
        this.mBookings = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mDate, 0);
        parcel.writeInt(this.mPageViews);
        parcel.writeInt(this.mInquiries);
        parcel.writeInt(this.mBookings);
    }

    public void readFromParcel(Parcel source) {
        this.mDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mPageViews = source.readInt();
        this.mInquiries = source.readInt();
        this.mBookings = source.readInt();
    }
}
