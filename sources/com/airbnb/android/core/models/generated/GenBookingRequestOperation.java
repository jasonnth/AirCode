package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.payments.models.RedirectSettings;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenBookingRequestOperation implements Parcelable {
    @JsonProperty("redirect_settings")
    protected RedirectSettings mRedirectSettings;
    @JsonProperty("reservation")
    protected Reservation mReservation;

    protected GenBookingRequestOperation(RedirectSettings redirectSettings, Reservation reservation) {
        this();
        this.mRedirectSettings = redirectSettings;
        this.mReservation = reservation;
    }

    protected GenBookingRequestOperation() {
    }

    public RedirectSettings getRedirectSettings() {
        return this.mRedirectSettings;
    }

    @JsonProperty("redirect_settings")
    public void setRedirectSettings(RedirectSettings value) {
        this.mRedirectSettings = value;
    }

    public Reservation getReservation() {
        return this.mReservation;
    }

    @JsonProperty("reservation")
    public void setReservation(Reservation value) {
        this.mReservation = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mRedirectSettings, 0);
        parcel.writeParcelable(this.mReservation, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mRedirectSettings = (RedirectSettings) source.readParcelable(RedirectSettings.class.getClassLoader());
        this.mReservation = (Reservation) source.readParcelable(Reservation.class.getClassLoader());
    }
}
