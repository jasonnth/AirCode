package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReferree implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("localized_credit_earned")
    protected double mLocalizedCreditEarned;
    @JsonProperty("localized_credit_pending")
    protected double mLocalizedCreditPending;
    @JsonProperty("referred_user_email")
    protected String mReferredUserEmail;
    @JsonProperty("referred_user_full_name")
    protected String mReferredUserFullName;
    @JsonProperty("referred_user_name")
    protected String mReferredUserName;
    @JsonProperty("referred_user_phone_number")
    protected String mReferredUserPhoneNumber;
    @JsonProperty("referred_user_photo_url")
    protected String mReferredUserPhotoUrl;
    @JsonProperty("referred_user_profile_photo_url")
    protected String mReferredUserProfilePhotoUrl;
    @JsonProperty("status")
    protected String mStatus;

    protected GenReferree(String referredUserFullName, String referredUserName, String referredUserEmail, String referredUserPhoneNumber, String status, String referredUserPhotoUrl, String referredUserProfilePhotoUrl, double localizedCreditEarned, double localizedCreditPending, long id) {
        this();
        this.mReferredUserFullName = referredUserFullName;
        this.mReferredUserName = referredUserName;
        this.mReferredUserEmail = referredUserEmail;
        this.mReferredUserPhoneNumber = referredUserPhoneNumber;
        this.mStatus = status;
        this.mReferredUserPhotoUrl = referredUserPhotoUrl;
        this.mReferredUserProfilePhotoUrl = referredUserProfilePhotoUrl;
        this.mLocalizedCreditEarned = localizedCreditEarned;
        this.mLocalizedCreditPending = localizedCreditPending;
        this.mId = id;
    }

    protected GenReferree() {
    }

    public String getReferredUserFullName() {
        return this.mReferredUserFullName;
    }

    @JsonProperty("referred_user_full_name")
    public void setReferredUserFullName(String value) {
        this.mReferredUserFullName = value;
    }

    public String getReferredUserName() {
        return this.mReferredUserName;
    }

    @JsonProperty("referred_user_name")
    public void setReferredUserName(String value) {
        this.mReferredUserName = value;
    }

    public String getReferredUserEmail() {
        return this.mReferredUserEmail;
    }

    @JsonProperty("referred_user_email")
    public void setReferredUserEmail(String value) {
        this.mReferredUserEmail = value;
    }

    public String getReferredUserPhoneNumber() {
        return this.mReferredUserPhoneNumber;
    }

    @JsonProperty("referred_user_phone_number")
    public void setReferredUserPhoneNumber(String value) {
        this.mReferredUserPhoneNumber = value;
    }

    public String getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(String value) {
        this.mStatus = value;
    }

    public String getReferredUserPhotoUrl() {
        return this.mReferredUserPhotoUrl;
    }

    @JsonProperty("referred_user_photo_url")
    public void setReferredUserPhotoUrl(String value) {
        this.mReferredUserPhotoUrl = value;
    }

    public String getReferredUserProfilePhotoUrl() {
        return this.mReferredUserProfilePhotoUrl;
    }

    @JsonProperty("referred_user_profile_photo_url")
    public void setReferredUserProfilePhotoUrl(String value) {
        this.mReferredUserProfilePhotoUrl = value;
    }

    public double getLocalizedCreditEarned() {
        return this.mLocalizedCreditEarned;
    }

    @JsonProperty("localized_credit_earned")
    public void setLocalizedCreditEarned(double value) {
        this.mLocalizedCreditEarned = value;
    }

    public double getLocalizedCreditPending() {
        return this.mLocalizedCreditPending;
    }

    @JsonProperty("localized_credit_pending")
    public void setLocalizedCreditPending(double value) {
        this.mLocalizedCreditPending = value;
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
        parcel.writeString(this.mReferredUserFullName);
        parcel.writeString(this.mReferredUserName);
        parcel.writeString(this.mReferredUserEmail);
        parcel.writeString(this.mReferredUserPhoneNumber);
        parcel.writeString(this.mStatus);
        parcel.writeString(this.mReferredUserPhotoUrl);
        parcel.writeString(this.mReferredUserProfilePhotoUrl);
        parcel.writeDouble(this.mLocalizedCreditEarned);
        parcel.writeDouble(this.mLocalizedCreditPending);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mReferredUserFullName = source.readString();
        this.mReferredUserName = source.readString();
        this.mReferredUserEmail = source.readString();
        this.mReferredUserPhoneNumber = source.readString();
        this.mStatus = source.readString();
        this.mReferredUserPhotoUrl = source.readString();
        this.mReferredUserProfilePhotoUrl = source.readString();
        this.mLocalizedCreditEarned = source.readDouble();
        this.mLocalizedCreditPending = source.readDouble();
        this.mId = source.readLong();
    }
}
