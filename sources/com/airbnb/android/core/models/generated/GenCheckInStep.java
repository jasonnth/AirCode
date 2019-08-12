package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.CheckInStepAttachment;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCheckInStep implements Parcelable {
    @JsonProperty("attachment")
    protected CheckInStepAttachment mAttachment;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("localized_note")
    protected String mLocalizedNote;
    @JsonProperty("note")
    protected String mNote;

    protected GenCheckInStep(CheckInStepAttachment attachment, String localizedNote, String note, long id) {
        this();
        this.mAttachment = attachment;
        this.mLocalizedNote = localizedNote;
        this.mNote = note;
        this.mId = id;
    }

    protected GenCheckInStep() {
    }

    public CheckInStepAttachment getAttachment() {
        return this.mAttachment;
    }

    @JsonProperty("attachment")
    public void setAttachment(CheckInStepAttachment value) {
        this.mAttachment = value;
    }

    public String getLocalizedNote() {
        return this.mLocalizedNote;
    }

    @JsonProperty("localized_note")
    public void setLocalizedNote(String value) {
        this.mLocalizedNote = value;
    }

    public String getNote() {
        return this.mNote;
    }

    @JsonProperty("note")
    public void setNote(String value) {
        this.mNote = value;
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
        parcel.writeParcelable(this.mAttachment, 0);
        parcel.writeString(this.mLocalizedNote);
        parcel.writeString(this.mNote);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mAttachment = (CheckInStepAttachment) source.readParcelable(CheckInStepAttachment.class.getClassLoader());
        this.mLocalizedNote = source.readString();
        this.mNote = source.readString();
        this.mId = source.readLong();
    }
}
