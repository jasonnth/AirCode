package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCommercialHostInfo implements Parcelable {
    @JsonProperty("additional_info")
    protected String mAdditionalInfo;
    @JsonProperty("apt")
    protected String mApt;
    @JsonProperty("business_name")
    protected String mBusinessName;
    @JsonProperty("city")
    protected String mCity;
    @JsonProperty("country")
    protected String mCountry;
    @JsonProperty("email")
    protected String mEmail;
    @JsonProperty("host_type")
    protected String mHostType;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("legal_representatives")
    protected String mLegalRepresentatives;
    @JsonProperty("phone_number")
    protected String mPhoneNumber;
    @JsonProperty("registration_numbers")
    protected String mRegistrationNumbers;
    @JsonProperty("state")
    protected String mState;
    @JsonProperty("street")
    protected String mStreet;
    @JsonProperty("user_id")
    protected long mUserId;
    @JsonProperty("vat_number")
    protected String mVatNumber;
    @JsonProperty("zipcode")
    protected String mZipcode;

    protected GenCommercialHostInfo(String hostType, String businessName, String legalRepresentatives, String street, String apt, String city, String zipcode, String state, String country, String registrationNumbers, String vatNumber, String email, String phoneNumber, String additionalInfo, long id, long userId) {
        this();
        this.mHostType = hostType;
        this.mBusinessName = businessName;
        this.mLegalRepresentatives = legalRepresentatives;
        this.mStreet = street;
        this.mApt = apt;
        this.mCity = city;
        this.mZipcode = zipcode;
        this.mState = state;
        this.mCountry = country;
        this.mRegistrationNumbers = registrationNumbers;
        this.mVatNumber = vatNumber;
        this.mEmail = email;
        this.mPhoneNumber = phoneNumber;
        this.mAdditionalInfo = additionalInfo;
        this.mId = id;
        this.mUserId = userId;
    }

    protected GenCommercialHostInfo() {
    }

    public String getHostType() {
        return this.mHostType;
    }

    @JsonProperty("host_type")
    public void setHostType(String value) {
        this.mHostType = value;
    }

    public String getBusinessName() {
        return this.mBusinessName;
    }

    @JsonProperty("business_name")
    public void setBusinessName(String value) {
        this.mBusinessName = value;
    }

    public String getLegalRepresentatives() {
        return this.mLegalRepresentatives;
    }

    @JsonProperty("legal_representatives")
    public void setLegalRepresentatives(String value) {
        this.mLegalRepresentatives = value;
    }

    public String getStreet() {
        return this.mStreet;
    }

    @JsonProperty("street")
    public void setStreet(String value) {
        this.mStreet = value;
    }

    public String getApt() {
        return this.mApt;
    }

    @JsonProperty("apt")
    public void setApt(String value) {
        this.mApt = value;
    }

    public String getCity() {
        return this.mCity;
    }

    @JsonProperty("city")
    public void setCity(String value) {
        this.mCity = value;
    }

    public String getZipcode() {
        return this.mZipcode;
    }

    @JsonProperty("zipcode")
    public void setZipcode(String value) {
        this.mZipcode = value;
    }

    public String getState() {
        return this.mState;
    }

    @JsonProperty("state")
    public void setState(String value) {
        this.mState = value;
    }

    public String getCountry() {
        return this.mCountry;
    }

    @JsonProperty("country")
    public void setCountry(String value) {
        this.mCountry = value;
    }

    public String getRegistrationNumbers() {
        return this.mRegistrationNumbers;
    }

    @JsonProperty("registration_numbers")
    public void setRegistrationNumbers(String value) {
        this.mRegistrationNumbers = value;
    }

    public String getVatNumber() {
        return this.mVatNumber;
    }

    @JsonProperty("vat_number")
    public void setVatNumber(String value) {
        this.mVatNumber = value;
    }

    public String getEmail() {
        return this.mEmail;
    }

    @JsonProperty("email")
    public void setEmail(String value) {
        this.mEmail = value;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    @JsonProperty("phone_number")
    public void setPhoneNumber(String value) {
        this.mPhoneNumber = value;
    }

    public String getAdditionalInfo() {
        return this.mAdditionalInfo;
    }

    @JsonProperty("additional_info")
    public void setAdditionalInfo(String value) {
        this.mAdditionalInfo = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(long value) {
        this.mUserId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mHostType);
        parcel.writeString(this.mBusinessName);
        parcel.writeString(this.mLegalRepresentatives);
        parcel.writeString(this.mStreet);
        parcel.writeString(this.mApt);
        parcel.writeString(this.mCity);
        parcel.writeString(this.mZipcode);
        parcel.writeString(this.mState);
        parcel.writeString(this.mCountry);
        parcel.writeString(this.mRegistrationNumbers);
        parcel.writeString(this.mVatNumber);
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mPhoneNumber);
        parcel.writeString(this.mAdditionalInfo);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mHostType = source.readString();
        this.mBusinessName = source.readString();
        this.mLegalRepresentatives = source.readString();
        this.mStreet = source.readString();
        this.mApt = source.readString();
        this.mCity = source.readString();
        this.mZipcode = source.readString();
        this.mState = source.readString();
        this.mCountry = source.readString();
        this.mRegistrationNumbers = source.readString();
        this.mVatNumber = source.readString();
        this.mEmail = source.readString();
        this.mPhoneNumber = source.readString();
        this.mAdditionalInfo = source.readString();
        this.mId = source.readLong();
        this.mUserId = source.readLong();
    }
}
