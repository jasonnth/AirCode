package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.ReservationAlteration.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReservationAlteration implements Parcelable {
    @JsonProperty("base_price")
    protected int mBasePrice;
    @JsonProperty("check_in")
    protected AirDate mCheckIn;
    @JsonProperty("check_out")
    protected AirDate mCheckOut;
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("extras_price")
    protected int mExtrasPrice;
    @JsonProperty("guest_price_difference")
    protected CurrencyAmount mGuestPriceDifference;
    @JsonProperty("guests")
    protected int mGuests;
    @JsonProperty("host_price_difference")
    protected CurrencyAmount mHostPriceDifference;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("initiated_by")
    protected String mInitiatedBy;
    @JsonProperty("initiator_id")
    protected long mInitiatorId;
    @JsonProperty("listing")
    protected Listing mListing;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("new_host_payout")
    protected CurrencyAmount mNewHostPayout;
    @JsonProperty("original_host_payout")
    protected CurrencyAmount mOriginalHostPayout;
    @JsonProperty("payment_option_to_charge")
    protected PaymentOption mPaymentOptionToCharge;
    @JsonProperty("possible")
    protected boolean mPossible;
    @JsonProperty("pricing_quote")
    protected PricingQuote mPricingQuote;
    @JsonProperty("problem")
    protected String mProblem;
    @JsonProperty("reservation_pricing_quote")
    protected PricingQuote mReservationPricingQuote;
    @JsonProperty("response_at")
    protected AirDateTime mResponseAt;
    @JsonProperty("status")
    protected Status mStatus;
    @JsonProperty("status_string")
    protected String mStatusString;
    @JsonProperty("updated_at")
    protected AirDateTime mUpdatedAt;

    protected GenReservationAlteration(AirDate checkIn, AirDate checkOut, AirDateTime createdAt, AirDateTime responseAt, AirDateTime updatedAt, CurrencyAmount guestPriceDifference, CurrencyAmount hostPriceDifference, CurrencyAmount newHostPayout, CurrencyAmount originalHostPayout, Listing listing, PaymentOption paymentOptionToCharge, PricingQuote pricingQuote, PricingQuote reservationPricingQuote, Status status, String initiatedBy, String statusString, String problem, boolean possible, int basePrice, int guests, int extrasPrice, long id, long initiatorId, long listingId) {
        this();
        this.mCheckIn = checkIn;
        this.mCheckOut = checkOut;
        this.mCreatedAt = createdAt;
        this.mResponseAt = responseAt;
        this.mUpdatedAt = updatedAt;
        this.mGuestPriceDifference = guestPriceDifference;
        this.mHostPriceDifference = hostPriceDifference;
        this.mNewHostPayout = newHostPayout;
        this.mOriginalHostPayout = originalHostPayout;
        this.mListing = listing;
        this.mPaymentOptionToCharge = paymentOptionToCharge;
        this.mPricingQuote = pricingQuote;
        this.mReservationPricingQuote = reservationPricingQuote;
        this.mStatus = status;
        this.mInitiatedBy = initiatedBy;
        this.mStatusString = statusString;
        this.mProblem = problem;
        this.mPossible = possible;
        this.mBasePrice = basePrice;
        this.mGuests = guests;
        this.mExtrasPrice = extrasPrice;
        this.mId = id;
        this.mInitiatorId = initiatorId;
        this.mListingId = listingId;
    }

    protected GenReservationAlteration() {
    }

    public AirDate getCheckIn() {
        return this.mCheckIn;
    }

    @JsonProperty("check_in")
    public void setCheckIn(AirDate value) {
        this.mCheckIn = value;
    }

    public AirDate getCheckOut() {
        return this.mCheckOut;
    }

    @JsonProperty("check_out")
    public void setCheckOut(AirDate value) {
        this.mCheckOut = value;
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public AirDateTime getResponseAt() {
        return this.mResponseAt;
    }

    @JsonProperty("response_at")
    public void setResponseAt(AirDateTime value) {
        this.mResponseAt = value;
    }

    public AirDateTime getUpdatedAt() {
        return this.mUpdatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(AirDateTime value) {
        this.mUpdatedAt = value;
    }

    public CurrencyAmount getGuestPriceDifference() {
        return this.mGuestPriceDifference;
    }

    @JsonProperty("guest_price_difference")
    public void setGuestPriceDifference(CurrencyAmount value) {
        this.mGuestPriceDifference = value;
    }

    public CurrencyAmount getHostPriceDifference() {
        return this.mHostPriceDifference;
    }

    @JsonProperty("host_price_difference")
    public void setHostPriceDifference(CurrencyAmount value) {
        this.mHostPriceDifference = value;
    }

    public CurrencyAmount getNewHostPayout() {
        return this.mNewHostPayout;
    }

    @JsonProperty("new_host_payout")
    public void setNewHostPayout(CurrencyAmount value) {
        this.mNewHostPayout = value;
    }

    public CurrencyAmount getOriginalHostPayout() {
        return this.mOriginalHostPayout;
    }

    @JsonProperty("original_host_payout")
    public void setOriginalHostPayout(CurrencyAmount value) {
        this.mOriginalHostPayout = value;
    }

    public Listing getListing() {
        return this.mListing;
    }

    @JsonProperty("listing")
    public void setListing(Listing value) {
        this.mListing = value;
    }

    public PaymentOption getPaymentOptionToCharge() {
        return this.mPaymentOptionToCharge;
    }

    @JsonProperty("payment_option_to_charge")
    public void setPaymentOptionToCharge(PaymentOption value) {
        this.mPaymentOptionToCharge = value;
    }

    public PricingQuote getPricingQuote() {
        return this.mPricingQuote;
    }

    @JsonProperty("pricing_quote")
    public void setPricingQuote(PricingQuote value) {
        this.mPricingQuote = value;
    }

    public PricingQuote getReservationPricingQuote() {
        return this.mReservationPricingQuote;
    }

    @JsonProperty("reservation_pricing_quote")
    public void setReservationPricingQuote(PricingQuote value) {
        this.mReservationPricingQuote = value;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public String getInitiatedBy() {
        return this.mInitiatedBy;
    }

    @JsonProperty("initiated_by")
    public void setInitiatedBy(String value) {
        this.mInitiatedBy = value;
    }

    public String getStatusString() {
        return this.mStatusString;
    }

    @JsonProperty("status_string")
    public void setStatusString(String value) {
        this.mStatusString = value;
    }

    public String getProblem() {
        return this.mProblem;
    }

    @JsonProperty("problem")
    public void setProblem(String value) {
        this.mProblem = value;
    }

    public boolean isPossible() {
        return this.mPossible;
    }

    @JsonProperty("possible")
    public void setPossible(boolean value) {
        this.mPossible = value;
    }

    public int getBasePrice() {
        return this.mBasePrice;
    }

    @JsonProperty("base_price")
    public void setBasePrice(int value) {
        this.mBasePrice = value;
    }

    public int getGuests() {
        return this.mGuests;
    }

    @JsonProperty("guests")
    public void setGuests(int value) {
        this.mGuests = value;
    }

    public int getExtrasPrice() {
        return this.mExtrasPrice;
    }

    @JsonProperty("extras_price")
    public void setExtrasPrice(int value) {
        this.mExtrasPrice = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getInitiatorId() {
        return this.mInitiatorId;
    }

    @JsonProperty("initiator_id")
    public void setInitiatorId(long value) {
        this.mInitiatorId = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCheckIn, 0);
        parcel.writeParcelable(this.mCheckOut, 0);
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeParcelable(this.mResponseAt, 0);
        parcel.writeParcelable(this.mUpdatedAt, 0);
        parcel.writeParcelable(this.mGuestPriceDifference, 0);
        parcel.writeParcelable(this.mHostPriceDifference, 0);
        parcel.writeParcelable(this.mNewHostPayout, 0);
        parcel.writeParcelable(this.mOriginalHostPayout, 0);
        parcel.writeParcelable(this.mListing, 0);
        parcel.writeParcelable(this.mPaymentOptionToCharge, 0);
        parcel.writeParcelable(this.mPricingQuote, 0);
        parcel.writeParcelable(this.mReservationPricingQuote, 0);
        parcel.writeSerializable(this.mStatus);
        parcel.writeString(this.mInitiatedBy);
        parcel.writeString(this.mStatusString);
        parcel.writeString(this.mProblem);
        parcel.writeBooleanArray(new boolean[]{this.mPossible});
        parcel.writeInt(this.mBasePrice);
        parcel.writeInt(this.mGuests);
        parcel.writeInt(this.mExtrasPrice);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mInitiatorId);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mCheckIn = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCheckOut = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mResponseAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mUpdatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mGuestPriceDifference = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mHostPriceDifference = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mNewHostPayout = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mOriginalHostPayout = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mListing = (Listing) source.readParcelable(Listing.class.getClassLoader());
        this.mPaymentOptionToCharge = (PaymentOption) source.readParcelable(PaymentOption.class.getClassLoader());
        this.mPricingQuote = (PricingQuote) source.readParcelable(PricingQuote.class.getClassLoader());
        this.mReservationPricingQuote = (PricingQuote) source.readParcelable(PricingQuote.class.getClassLoader());
        this.mStatus = (Status) source.readSerializable();
        this.mInitiatedBy = source.readString();
        this.mStatusString = source.readString();
        this.mProblem = source.readString();
        this.mPossible = source.createBooleanArray()[0];
        this.mBasePrice = source.readInt();
        this.mGuests = source.readInt();
        this.mExtrasPrice = source.readInt();
        this.mId = source.readLong();
        this.mInitiatorId = source.readLong();
        this.mListingId = source.readLong();
    }
}
