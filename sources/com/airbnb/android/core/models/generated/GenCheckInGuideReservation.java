package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCheckInGuideReservation implements Parcelable {
    @JsonProperty("confirmation_code")
    protected String mConfirmationCode;
    @JsonProperty("end_date")
    protected AirDate mEndDate;
    @JsonProperty("start_date")
    protected AirDate mStartDate;

    protected GenCheckInGuideReservation(AirDate startDate, AirDate endDate, String confirmationCode) {
        this();
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mConfirmationCode = confirmationCode;
    }

    protected GenCheckInGuideReservation() {
    }

    public AirDate getStartDate() {
        return this.mStartDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(AirDate value) {
        this.mStartDate = value;
    }

    public AirDate getEndDate() {
        return this.mEndDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(AirDate value) {
        this.mEndDate = value;
    }

    public String getConfirmationCode() {
        return this.mConfirmationCode;
    }

    @JsonProperty("confirmation_code")
    public void setConfirmationCode(String value) {
        this.mConfirmationCode = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mEndDate, 0);
        parcel.writeString(this.mConfirmationCode);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mEndDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mConfirmationCode = source.readString();
    }
}
