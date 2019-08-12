package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHostReferralsInviteContact implements Parcelable {
    @JsonProperty("invitation_source")
    protected String mInvitationSource;
    @JsonProperty("referred_email")
    protected String mReferredEmail;
    @JsonProperty("referred_phone")
    protected String mReferredPhone;
    @JsonProperty("referred_user_name")
    protected String mReferredUserName;

    protected GenHostReferralsInviteContact(String referredEmail, String referredPhone, String referredUserName, String invitationSource) {
        this();
        this.mReferredEmail = referredEmail;
        this.mReferredPhone = referredPhone;
        this.mReferredUserName = referredUserName;
        this.mInvitationSource = invitationSource;
    }

    protected GenHostReferralsInviteContact() {
    }

    public String getReferredEmail() {
        return this.mReferredEmail;
    }

    @JsonProperty("referred_email")
    public void setReferredEmail(String value) {
        this.mReferredEmail = value;
    }

    public String getReferredPhone() {
        return this.mReferredPhone;
    }

    @JsonProperty("referred_phone")
    public void setReferredPhone(String value) {
        this.mReferredPhone = value;
    }

    public String getReferredUserName() {
        return this.mReferredUserName;
    }

    @JsonProperty("referred_user_name")
    public void setReferredUserName(String value) {
        this.mReferredUserName = value;
    }

    public String getInvitationSource() {
        return this.mInvitationSource;
    }

    @JsonProperty("invitation_source")
    public void setInvitationSource(String value) {
        this.mInvitationSource = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mReferredEmail);
        parcel.writeString(this.mReferredPhone);
        parcel.writeString(this.mReferredUserName);
        parcel.writeString(this.mInvitationSource);
    }

    public void readFromParcel(Parcel source) {
        this.mReferredEmail = source.readString();
        this.mReferredPhone = source.readString();
        this.mReferredUserName = source.readString();
        this.mInvitationSource = source.readString();
    }
}
