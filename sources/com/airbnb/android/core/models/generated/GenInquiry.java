package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenInquiry implements Parcelable {
    @JsonProperty("end_date")
    protected AirDate mEndDate;
    @JsonProperty("expires_at")
    protected AirDateTime mExpiresAt;
    @JsonProperty("guest")
    protected User mGuest;
    @JsonProperty("inquiry_post")
    protected Post mInquiryPost;
    @JsonProperty("inquiry_price_native")
    protected int mInquiryPriceNative;
    @JsonProperty("inquiry_reservation")
    protected Reservation mInquiryReservation;
    @JsonProperty("listing")
    protected Listing mListing;
    @JsonProperty("nights")
    protected int mNights;
    @JsonProperty("number_of_guests")
    protected int mNumberOfGuests;
    @JsonProperty("pricing_quote")
    protected PricingQuote mPricingQuote;
    @JsonProperty("requires_response")
    protected boolean mRequiresResponse;
    @JsonProperty("reservation_status")
    protected ReservationStatus mReservationStatus;
    @JsonProperty("inquiry_special_offer")
    protected SpecialOffer mSpecialOffer;
    @JsonProperty("start_date")
    protected AirDate mStartDate;
    @JsonProperty("id")
    protected long mThreadId;
    @JsonProperty("total_price_host_dashboard")
    protected String mTotalPriceHostDashboard;

    protected GenInquiry(AirDate startDate, AirDate endDate, AirDateTime expiresAt, Listing listing, Post inquiryPost, PricingQuote pricingQuote, Reservation inquiryReservation, ReservationStatus reservationStatus, SpecialOffer specialOffer, String totalPriceHostDashboard, User guest, boolean requiresResponse, int inquiryPriceNative, int nights, int numberOfGuests, long threadId) {
        this();
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mExpiresAt = expiresAt;
        this.mListing = listing;
        this.mInquiryPost = inquiryPost;
        this.mPricingQuote = pricingQuote;
        this.mInquiryReservation = inquiryReservation;
        this.mReservationStatus = reservationStatus;
        this.mSpecialOffer = specialOffer;
        this.mTotalPriceHostDashboard = totalPriceHostDashboard;
        this.mGuest = guest;
        this.mRequiresResponse = requiresResponse;
        this.mInquiryPriceNative = inquiryPriceNative;
        this.mNights = nights;
        this.mNumberOfGuests = numberOfGuests;
        this.mThreadId = threadId;
    }

    protected GenInquiry() {
    }

    public AirDate getStartDate() {
        return this.mStartDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(AirDate value) {
        this.mStartDate = value;
    }

    public AirDate getEndDate() {
        return this.mEndDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(AirDate value) {
        this.mEndDate = value;
    }

    public AirDateTime getExpiresAt() {
        return this.mExpiresAt;
    }

    @JsonProperty("expires_at")
    public void setExpiresAt(AirDateTime value) {
        this.mExpiresAt = value;
    }

    public Listing getListing() {
        return this.mListing;
    }

    @JsonProperty("listing")
    public void setListing(Listing value) {
        this.mListing = value;
    }

    public Post getInquiryPost() {
        return this.mInquiryPost;
    }

    @JsonProperty("inquiry_post")
    public void setInquiryPost(Post value) {
        this.mInquiryPost = value;
    }

    public PricingQuote getPricingQuote() {
        return this.mPricingQuote;
    }

    @JsonProperty("pricing_quote")
    public void setPricingQuote(PricingQuote value) {
        this.mPricingQuote = value;
    }

    public Reservation getInquiryReservation() {
        return this.mInquiryReservation;
    }

    @JsonProperty("inquiry_reservation")
    public void setInquiryReservation(Reservation value) {
        this.mInquiryReservation = value;
    }

    public ReservationStatus getReservationStatus() {
        return this.mReservationStatus;
    }

    public SpecialOffer getSpecialOffer() {
        return this.mSpecialOffer;
    }

    @JsonProperty("inquiry_special_offer")
    public void setSpecialOffer(SpecialOffer value) {
        this.mSpecialOffer = value;
    }

    public String getTotalPriceHostDashboard() {
        return this.mTotalPriceHostDashboard;
    }

    @JsonProperty("total_price_host_dashboard")
    public void setTotalPriceHostDashboard(String value) {
        this.mTotalPriceHostDashboard = value;
    }

    public User getGuest() {
        return this.mGuest;
    }

    @JsonProperty("guest")
    public void setGuest(User value) {
        this.mGuest = value;
    }

    public boolean isRequiresResponse() {
        return this.mRequiresResponse;
    }

    @JsonProperty("requires_response")
    public void setRequiresResponse(boolean value) {
        this.mRequiresResponse = value;
    }

    public int getInquiryPriceNative() {
        return this.mInquiryPriceNative;
    }

    @JsonProperty("inquiry_price_native")
    public void setInquiryPriceNative(int value) {
        this.mInquiryPriceNative = value;
    }

    public int getNights() {
        return this.mNights;
    }

    @JsonProperty("nights")
    public void setNights(int value) {
        this.mNights = value;
    }

    public int getNumberOfGuests() {
        return this.mNumberOfGuests;
    }

    @JsonProperty("number_of_guests")
    public void setNumberOfGuests(int value) {
        this.mNumberOfGuests = value;
    }

    public long getThreadId() {
        return this.mThreadId;
    }

    @JsonProperty("id")
    public void setThreadId(long value) {
        this.mThreadId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mEndDate, 0);
        parcel.writeParcelable(this.mExpiresAt, 0);
        parcel.writeParcelable(this.mListing, 0);
        parcel.writeParcelable(this.mInquiryPost, 0);
        parcel.writeParcelable(this.mPricingQuote, 0);
        parcel.writeParcelable(this.mInquiryReservation, 0);
        parcel.writeParcelable(this.mReservationStatus, 0);
        parcel.writeParcelable(this.mSpecialOffer, 0);
        parcel.writeString(this.mTotalPriceHostDashboard);
        parcel.writeParcelable(this.mGuest, 0);
        parcel.writeBooleanArray(new boolean[]{this.mRequiresResponse});
        parcel.writeInt(this.mInquiryPriceNative);
        parcel.writeInt(this.mNights);
        parcel.writeInt(this.mNumberOfGuests);
        parcel.writeLong(this.mThreadId);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mEndDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mExpiresAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mListing = (Listing) source.readParcelable(Listing.class.getClassLoader());
        this.mInquiryPost = (Post) source.readParcelable(Post.class.getClassLoader());
        this.mPricingQuote = (PricingQuote) source.readParcelable(PricingQuote.class.getClassLoader());
        this.mInquiryReservation = (Reservation) source.readParcelable(Reservation.class.getClassLoader());
        this.mReservationStatus = (ReservationStatus) source.readParcelable(ReservationStatus.class.getClassLoader());
        this.mSpecialOffer = (SpecialOffer) source.readParcelable(SpecialOffer.class.getClassLoader());
        this.mTotalPriceHostDashboard = source.readString();
        this.mGuest = (User) source.readParcelable(User.class.getClassLoader());
        this.mRequiresResponse = source.createBooleanArray()[0];
        this.mInquiryPriceNative = source.readInt();
        this.mNights = source.readInt();
        this.mNumberOfGuests = source.readInt();
        this.mThreadId = source.readLong();
    }
}
