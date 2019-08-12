package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCohostInvitation implements Parcelable {
    @JsonProperty("cohosting_contract")
    protected CohostingContract mCohostingContract;
    @JsonProperty("expired_at")
    protected AirDateTime mExpirationTime;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("invitee_email")
    protected String mInviteeEmail;
    @JsonProperty("invitee_identifier")
    protected String mInviteeIdentifier;
    @JsonProperty("invitee_identifier_type")
    protected String mInviteeIdentifierType;
    @JsonProperty("inviter")
    protected User mInviter;
    @JsonProperty("is_cohost")
    protected Boolean mIsCohost;
    @JsonProperty("is_expired")
    protected Boolean mIsExpired;
    @JsonProperty("listing")
    protected Listing mListing;
    @JsonProperty("minutes_until_expiration")
    protected long mMinutesUntilExpiration;
    @JsonProperty("status")
    protected int mStatus;

    protected GenCohostInvitation(AirDateTime expirationTime, Boolean isExpired, Boolean isCohost, CohostingContract cohostingContract, Listing listing, String inviteeIdentifierType, String inviteeIdentifier, String inviteeEmail, User inviter, int status, long id, long minutesUntilExpiration) {
        this();
        this.mExpirationTime = expirationTime;
        this.mIsExpired = isExpired;
        this.mIsCohost = isCohost;
        this.mCohostingContract = cohostingContract;
        this.mListing = listing;
        this.mInviteeIdentifierType = inviteeIdentifierType;
        this.mInviteeIdentifier = inviteeIdentifier;
        this.mInviteeEmail = inviteeEmail;
        this.mInviter = inviter;
        this.mStatus = status;
        this.mId = id;
        this.mMinutesUntilExpiration = minutesUntilExpiration;
    }

    protected GenCohostInvitation() {
    }

    public AirDateTime getExpirationTime() {
        return this.mExpirationTime;
    }

    @JsonProperty("expired_at")
    public void setExpirationTime(AirDateTime value) {
        this.mExpirationTime = value;
    }

    public Boolean isIsExpired() {
        return this.mIsExpired;
    }

    @JsonProperty("is_expired")
    public void setIsExpired(Boolean value) {
        this.mIsExpired = value;
    }

    public Boolean isIsCohost() {
        return this.mIsCohost;
    }

    @JsonProperty("is_cohost")
    public void setIsCohost(Boolean value) {
        this.mIsCohost = value;
    }

    public CohostingContract getCohostingContract() {
        return this.mCohostingContract;
    }

    @JsonProperty("cohosting_contract")
    public void setCohostingContract(CohostingContract value) {
        this.mCohostingContract = value;
    }

    public Listing getListing() {
        return this.mListing;
    }

    @JsonProperty("listing")
    public void setListing(Listing value) {
        this.mListing = value;
    }

    public String getInviteeIdentifierType() {
        return this.mInviteeIdentifierType;
    }

    @JsonProperty("invitee_identifier_type")
    public void setInviteeIdentifierType(String value) {
        this.mInviteeIdentifierType = value;
    }

    public String getInviteeIdentifier() {
        return this.mInviteeIdentifier;
    }

    @JsonProperty("invitee_identifier")
    public void setInviteeIdentifier(String value) {
        this.mInviteeIdentifier = value;
    }

    public String getInviteeEmail() {
        return this.mInviteeEmail;
    }

    @JsonProperty("invitee_email")
    public void setInviteeEmail(String value) {
        this.mInviteeEmail = value;
    }

    public User getInviter() {
        return this.mInviter;
    }

    @JsonProperty("inviter")
    public void setInviter(User value) {
        this.mInviter = value;
    }

    public int getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(int value) {
        this.mStatus = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getMinutesUntilExpiration() {
        return this.mMinutesUntilExpiration;
    }

    @JsonProperty("minutes_until_expiration")
    public void setMinutesUntilExpiration(long value) {
        this.mMinutesUntilExpiration = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mExpirationTime, 0);
        parcel.writeValue(this.mIsExpired);
        parcel.writeValue(this.mIsCohost);
        parcel.writeParcelable(this.mCohostingContract, 0);
        parcel.writeParcelable(this.mListing, 0);
        parcel.writeString(this.mInviteeIdentifierType);
        parcel.writeString(this.mInviteeIdentifier);
        parcel.writeString(this.mInviteeEmail);
        parcel.writeParcelable(this.mInviter, 0);
        parcel.writeInt(this.mStatus);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mMinutesUntilExpiration);
    }

    public void readFromParcel(Parcel source) {
        this.mExpirationTime = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mIsExpired = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mIsCohost = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mCohostingContract = (CohostingContract) source.readParcelable(CohostingContract.class.getClassLoader());
        this.mListing = (Listing) source.readParcelable(Listing.class.getClassLoader());
        this.mInviteeIdentifierType = source.readString();
        this.mInviteeIdentifier = source.readString();
        this.mInviteeEmail = source.readString();
        this.mInviter = (User) source.readParcelable(User.class.getClassLoader());
        this.mStatus = source.readInt();
        this.mId = source.readLong();
        this.mMinutesUntilExpiration = source.readLong();
    }
}
