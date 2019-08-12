package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCalendarDayExternalCalendar implements Parcelable {
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("notes")
    protected String mNotes;

    protected GenCalendarDayExternalCalendar(String name, String notes) {
        this();
        this.mName = name;
        this.mNotes = notes;
    }

    protected GenCalendarDayExternalCalendar() {
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getNotes() {
        return this.mNotes;
    }

    @JsonProperty("notes")
    public void setNotes(String value) {
        this.mNotes = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mNotes);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mNotes = source.readString();
    }
}
