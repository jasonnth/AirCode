package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenMeetup implements Parcelable {
    @JsonProperty("future")
    protected boolean mFuture;
    @JsonProperty("guests_attending")
    protected int mGuestsAttending;
    @JsonProperty("reservation_id")
    protected long mReservationId;
    @JsonProperty("time_for_display")
    protected String mTimeForDisplay;

    protected GenMeetup(String timeForDisplay, boolean future, int guestsAttending, long reservationId) {
        this();
        this.mTimeForDisplay = timeForDisplay;
        this.mFuture = future;
        this.mGuestsAttending = guestsAttending;
        this.mReservationId = reservationId;
    }

    protected GenMeetup() {
    }

    public String getTimeForDisplay() {
        return this.mTimeForDisplay;
    }

    @JsonProperty("time_for_display")
    public void setTimeForDisplay(String value) {
        this.mTimeForDisplay = value;
    }

    public boolean isFuture() {
        return this.mFuture;
    }

    @JsonProperty("future")
    public void setFuture(boolean value) {
        this.mFuture = value;
    }

    public int getGuestsAttending() {
        return this.mGuestsAttending;
    }

    @JsonProperty("guests_attending")
    public void setGuestsAttending(int value) {
        this.mGuestsAttending = value;
    }

    public long getReservationId() {
        return this.mReservationId;
    }

    @JsonProperty("reservation_id")
    public void setReservationId(long value) {
        this.mReservationId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mTimeForDisplay);
        parcel.writeBooleanArray(new boolean[]{this.mFuture});
        parcel.writeInt(this.mGuestsAttending);
        parcel.writeLong(this.mReservationId);
    }

    public void readFromParcel(Parcel source) {
        this.mTimeForDisplay = source.readString();
        this.mFuture = source.createBooleanArray()[0];
        this.mGuestsAttending = source.readInt();
        this.mReservationId = source.readLong();
    }
}
