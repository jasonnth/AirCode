package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReferralOffer implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("localized_credit_guest")
    protected double mLocalizedCreditGuest;
    @JsonProperty("localized_credit_host")
    protected double mLocalizedCreditHost;
    @JsonProperty("localized_credit_sign_up")
    protected double mLocalizedCreditSign_up;
    @JsonProperty("localized_credit_total")
    protected double mLocalizedCreditTotal;

    protected GenReferralOffer(double localizedCreditGuest, double localizedCreditHost, double localizedCreditSign_up, double localizedCreditTotal, long id) {
        this();
        this.mLocalizedCreditGuest = localizedCreditGuest;
        this.mLocalizedCreditHost = localizedCreditHost;
        this.mLocalizedCreditSign_up = localizedCreditSign_up;
        this.mLocalizedCreditTotal = localizedCreditTotal;
        this.mId = id;
    }

    protected GenReferralOffer() {
    }

    public double getLocalizedCreditGuest() {
        return this.mLocalizedCreditGuest;
    }

    @JsonProperty("localized_credit_guest")
    public void setLocalizedCreditGuest(double value) {
        this.mLocalizedCreditGuest = value;
    }

    public double getLocalizedCreditHost() {
        return this.mLocalizedCreditHost;
    }

    @JsonProperty("localized_credit_host")
    public void setLocalizedCreditHost(double value) {
        this.mLocalizedCreditHost = value;
    }

    public double getLocalizedCreditSign_up() {
        return this.mLocalizedCreditSign_up;
    }

    @JsonProperty("localized_credit_sign_up")
    public void setLocalizedCreditSign_up(double value) {
        this.mLocalizedCreditSign_up = value;
    }

    public double getLocalizedCreditTotal() {
        return this.mLocalizedCreditTotal;
    }

    @JsonProperty("localized_credit_total")
    public void setLocalizedCreditTotal(double value) {
        this.mLocalizedCreditTotal = value;
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
        parcel.writeDouble(this.mLocalizedCreditGuest);
        parcel.writeDouble(this.mLocalizedCreditHost);
        parcel.writeDouble(this.mLocalizedCreditSign_up);
        parcel.writeDouble(this.mLocalizedCreditTotal);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mLocalizedCreditGuest = source.readDouble();
        this.mLocalizedCreditHost = source.readDouble();
        this.mLocalizedCreditSign_up = source.readDouble();
        this.mLocalizedCreditTotal = source.readDouble();
        this.mId = source.readLong();
    }
}
