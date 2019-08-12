package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReferralStatusForMobile implements Parcelable {
    @JsonProperty("available_expiration")
    protected AirDate mAvailableCreditExpiration;
    @JsonProperty("available_credit")
    protected String mAvailableCreditLocalized;
    @JsonProperty("available_min_trip")
    protected String mAvailableCreditMinTripValueLocalizedRequirement;
    @JsonProperty("available_min_trip_usd")
    protected int mAvailableCreditMinTripValueUSDRequirement;
    @JsonProperty("available_credit_usd")
    protected int mAvailableCreditUSD;
    @JsonProperty("earned_count")
    protected int mEarnedCreditCount;
    @JsonProperty("earned_credit")
    protected String mEarnedCreditLocalized;
    @JsonProperty("earned_credit_usd")
    protected int mEarnedCreditUSD;
    @JsonProperty("link")
    protected String mLink;
    @JsonProperty("offer_min_trip")
    protected String mOfferMinTripValueRequirementLocalized;
    @JsonProperty("offer_receiver_credit")
    protected String mOfferReceiverCreditLocalized;
    @JsonProperty("offer_sender_credit")
    protected String mOfferSenderCreditLocalized;
    @JsonProperty("pending_count")
    protected int mPendingCreditCount;
    @JsonProperty("pending_credit")
    protected String mPendingCreditLocalized;
    @JsonProperty("pending_credit_usd")
    protected int mPendingCreditUSD;

    protected GenReferralStatusForMobile(AirDate availableCreditExpiration, String link, String offerReceiverCreditLocalized, String offerSenderCreditLocalized, String offerMinTripValueRequirementLocalized, String availableCreditLocalized, String availableCreditMinTripValueLocalizedRequirement, String earnedCreditLocalized, String pendingCreditLocalized, int availableCreditUSD, int availableCreditMinTripValueUSDRequirement, int earnedCreditCount, int earnedCreditUSD, int pendingCreditCount, int pendingCreditUSD) {
        this();
        this.mAvailableCreditExpiration = availableCreditExpiration;
        this.mLink = link;
        this.mOfferReceiverCreditLocalized = offerReceiverCreditLocalized;
        this.mOfferSenderCreditLocalized = offerSenderCreditLocalized;
        this.mOfferMinTripValueRequirementLocalized = offerMinTripValueRequirementLocalized;
        this.mAvailableCreditLocalized = availableCreditLocalized;
        this.mAvailableCreditMinTripValueLocalizedRequirement = availableCreditMinTripValueLocalizedRequirement;
        this.mEarnedCreditLocalized = earnedCreditLocalized;
        this.mPendingCreditLocalized = pendingCreditLocalized;
        this.mAvailableCreditUSD = availableCreditUSD;
        this.mAvailableCreditMinTripValueUSDRequirement = availableCreditMinTripValueUSDRequirement;
        this.mEarnedCreditCount = earnedCreditCount;
        this.mEarnedCreditUSD = earnedCreditUSD;
        this.mPendingCreditCount = pendingCreditCount;
        this.mPendingCreditUSD = pendingCreditUSD;
    }

    protected GenReferralStatusForMobile() {
    }

    public AirDate getAvailableCreditExpiration() {
        return this.mAvailableCreditExpiration;
    }

    @JsonProperty("available_expiration")
    public void setAvailableCreditExpiration(AirDate value) {
        this.mAvailableCreditExpiration = value;
    }

    public String getLink() {
        return this.mLink;
    }

    @JsonProperty("link")
    public void setLink(String value) {
        this.mLink = value;
    }

    public String getOfferReceiverCreditLocalized() {
        return this.mOfferReceiverCreditLocalized;
    }

    @JsonProperty("offer_receiver_credit")
    public void setOfferReceiverCreditLocalized(String value) {
        this.mOfferReceiverCreditLocalized = value;
    }

    public String getOfferSenderCreditLocalized() {
        return this.mOfferSenderCreditLocalized;
    }

    @JsonProperty("offer_sender_credit")
    public void setOfferSenderCreditLocalized(String value) {
        this.mOfferSenderCreditLocalized = value;
    }

    public String getOfferMinTripValueRequirementLocalized() {
        return this.mOfferMinTripValueRequirementLocalized;
    }

    @JsonProperty("offer_min_trip")
    public void setOfferMinTripValueRequirementLocalized(String value) {
        this.mOfferMinTripValueRequirementLocalized = value;
    }

    public String getAvailableCreditLocalized() {
        return this.mAvailableCreditLocalized;
    }

    @JsonProperty("available_credit")
    public void setAvailableCreditLocalized(String value) {
        this.mAvailableCreditLocalized = value;
    }

    public String getAvailableCreditMinTripValueLocalizedRequirement() {
        return this.mAvailableCreditMinTripValueLocalizedRequirement;
    }

    @JsonProperty("available_min_trip")
    public void setAvailableCreditMinTripValueLocalizedRequirement(String value) {
        this.mAvailableCreditMinTripValueLocalizedRequirement = value;
    }

    public String getEarnedCreditLocalized() {
        return this.mEarnedCreditLocalized;
    }

    @JsonProperty("earned_credit")
    public void setEarnedCreditLocalized(String value) {
        this.mEarnedCreditLocalized = value;
    }

    public String getPendingCreditLocalized() {
        return this.mPendingCreditLocalized;
    }

    @JsonProperty("pending_credit")
    public void setPendingCreditLocalized(String value) {
        this.mPendingCreditLocalized = value;
    }

    public int getAvailableCreditUSD() {
        return this.mAvailableCreditUSD;
    }

    @JsonProperty("available_credit_usd")
    public void setAvailableCreditUSD(int value) {
        this.mAvailableCreditUSD = value;
    }

    public int getAvailableCreditMinTripValueUSDRequirement() {
        return this.mAvailableCreditMinTripValueUSDRequirement;
    }

    @JsonProperty("available_min_trip_usd")
    public void setAvailableCreditMinTripValueUSDRequirement(int value) {
        this.mAvailableCreditMinTripValueUSDRequirement = value;
    }

    public int getEarnedCreditCount() {
        return this.mEarnedCreditCount;
    }

    @JsonProperty("earned_count")
    public void setEarnedCreditCount(int value) {
        this.mEarnedCreditCount = value;
    }

    public int getEarnedCreditUSD() {
        return this.mEarnedCreditUSD;
    }

    @JsonProperty("earned_credit_usd")
    public void setEarnedCreditUSD(int value) {
        this.mEarnedCreditUSD = value;
    }

    public int getPendingCreditCount() {
        return this.mPendingCreditCount;
    }

    @JsonProperty("pending_count")
    public void setPendingCreditCount(int value) {
        this.mPendingCreditCount = value;
    }

    public int getPendingCreditUSD() {
        return this.mPendingCreditUSD;
    }

    @JsonProperty("pending_credit_usd")
    public void setPendingCreditUSD(int value) {
        this.mPendingCreditUSD = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mAvailableCreditExpiration, 0);
        parcel.writeString(this.mLink);
        parcel.writeString(this.mOfferReceiverCreditLocalized);
        parcel.writeString(this.mOfferSenderCreditLocalized);
        parcel.writeString(this.mOfferMinTripValueRequirementLocalized);
        parcel.writeString(this.mAvailableCreditLocalized);
        parcel.writeString(this.mAvailableCreditMinTripValueLocalizedRequirement);
        parcel.writeString(this.mEarnedCreditLocalized);
        parcel.writeString(this.mPendingCreditLocalized);
        parcel.writeInt(this.mAvailableCreditUSD);
        parcel.writeInt(this.mAvailableCreditMinTripValueUSDRequirement);
        parcel.writeInt(this.mEarnedCreditCount);
        parcel.writeInt(this.mEarnedCreditUSD);
        parcel.writeInt(this.mPendingCreditCount);
        parcel.writeInt(this.mPendingCreditUSD);
    }

    public void readFromParcel(Parcel source) {
        this.mAvailableCreditExpiration = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mLink = source.readString();
        this.mOfferReceiverCreditLocalized = source.readString();
        this.mOfferSenderCreditLocalized = source.readString();
        this.mOfferMinTripValueRequirementLocalized = source.readString();
        this.mAvailableCreditLocalized = source.readString();
        this.mAvailableCreditMinTripValueLocalizedRequirement = source.readString();
        this.mEarnedCreditLocalized = source.readString();
        this.mPendingCreditLocalized = source.readString();
        this.mAvailableCreditUSD = source.readInt();
        this.mAvailableCreditMinTripValueUSDRequirement = source.readInt();
        this.mEarnedCreditCount = source.readInt();
        this.mEarnedCreditUSD = source.readInt();
        this.mPendingCreditCount = source.readInt();
        this.mPendingCreditUSD = source.readInt();
    }
}
