package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.VerificationGroup;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenVerificationGroups implements Parcelable {
    @JsonProperty("account_activation")
    protected VerificationGroup mAccountActivation;
    @JsonProperty("basic")
    protected VerificationGroup mBasic;
    @JsonProperty("offline")
    protected VerificationGroup mOffline;
    @JsonProperty("online")
    protected VerificationGroup mOnline;

    protected GenVerificationGroups(VerificationGroup basic, VerificationGroup online, VerificationGroup offline, VerificationGroup accountActivation) {
        this();
        this.mBasic = basic;
        this.mOnline = online;
        this.mOffline = offline;
        this.mAccountActivation = accountActivation;
    }

    protected GenVerificationGroups() {
    }

    public VerificationGroup getBasic() {
        return this.mBasic;
    }

    @JsonProperty("basic")
    public void setBasic(VerificationGroup value) {
        this.mBasic = value;
    }

    public VerificationGroup getOnline() {
        return this.mOnline;
    }

    @JsonProperty("online")
    public void setOnline(VerificationGroup value) {
        this.mOnline = value;
    }

    public VerificationGroup getOffline() {
        return this.mOffline;
    }

    @JsonProperty("offline")
    public void setOffline(VerificationGroup value) {
        this.mOffline = value;
    }

    public VerificationGroup getAccountActivation() {
        return this.mAccountActivation;
    }

    @JsonProperty("account_activation")
    public void setAccountActivation(VerificationGroup value) {
        this.mAccountActivation = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mBasic, 0);
        parcel.writeParcelable(this.mOnline, 0);
        parcel.writeParcelable(this.mOffline, 0);
        parcel.writeParcelable(this.mAccountActivation, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mBasic = (VerificationGroup) source.readParcelable(VerificationGroup.class.getClassLoader());
        this.mOnline = (VerificationGroup) source.readParcelable(VerificationGroup.class.getClassLoader());
        this.mOffline = (VerificationGroup) source.readParcelable(VerificationGroup.class.getClassLoader());
        this.mAccountActivation = (VerificationGroup) source.readParcelable(VerificationGroup.class.getClassLoader());
    }
}
