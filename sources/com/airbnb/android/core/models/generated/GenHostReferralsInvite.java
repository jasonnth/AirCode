package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.HostReferralsInviteContact;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenHostReferralsInvite implements Parcelable {
    @JsonProperty("client_type")
    protected String mClientType;
    @JsonProperty("contacts")
    protected List<HostReferralsInviteContact> mHostReferralsInviteContacts;

    protected GenHostReferralsInvite(List<HostReferralsInviteContact> hostReferralsInviteContacts, String clientType) {
        this();
        this.mHostReferralsInviteContacts = hostReferralsInviteContacts;
        this.mClientType = clientType;
    }

    protected GenHostReferralsInvite() {
    }

    public List<HostReferralsInviteContact> getHostReferralsInviteContacts() {
        return this.mHostReferralsInviteContacts;
    }

    @JsonProperty("contacts")
    public void setHostReferralsInviteContacts(List<HostReferralsInviteContact> value) {
        this.mHostReferralsInviteContacts = value;
    }

    public String getClientType() {
        return this.mClientType;
    }

    @JsonProperty("client_type")
    public void setClientType(String value) {
        this.mClientType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mHostReferralsInviteContacts);
        parcel.writeString(this.mClientType);
    }

    public void readFromParcel(Parcel source) {
        this.mHostReferralsInviteContacts = source.createTypedArrayList(HostReferralsInviteContact.CREATOR);
        this.mClientType = source.readString();
    }
}
