package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenUserFlagDetail implements Parcelable {
    @JsonProperty("reason_detail_localized")
    protected String mReasonDetailLocalized;
    @JsonProperty("reason_name")
    protected String mReasonName;
    @JsonProperty("reason_name_localized")
    protected String mReasonNameLocalized;
    @JsonProperty("show_other_note")
    protected boolean mShowOtherNote;

    protected GenUserFlagDetail(String reasonDetailLocalized, String reasonName, String reasonNameLocalized, boolean showOtherNote) {
        this();
        this.mReasonDetailLocalized = reasonDetailLocalized;
        this.mReasonName = reasonName;
        this.mReasonNameLocalized = reasonNameLocalized;
        this.mShowOtherNote = showOtherNote;
    }

    protected GenUserFlagDetail() {
    }

    public String getReasonDetailLocalized() {
        return this.mReasonDetailLocalized;
    }

    @JsonProperty("reason_detail_localized")
    public void setReasonDetailLocalized(String value) {
        this.mReasonDetailLocalized = value;
    }

    public String getReasonName() {
        return this.mReasonName;
    }

    @JsonProperty("reason_name")
    public void setReasonName(String value) {
        this.mReasonName = value;
    }

    public String getReasonNameLocalized() {
        return this.mReasonNameLocalized;
    }

    @JsonProperty("reason_name_localized")
    public void setReasonNameLocalized(String value) {
        this.mReasonNameLocalized = value;
    }

    public boolean isShowOtherNote() {
        return this.mShowOtherNote;
    }

    @JsonProperty("show_other_note")
    public void setShowOtherNote(boolean value) {
        this.mShowOtherNote = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mReasonDetailLocalized);
        parcel.writeString(this.mReasonName);
        parcel.writeString(this.mReasonNameLocalized);
        parcel.writeBooleanArray(new boolean[]{this.mShowOtherNote});
    }

    public void readFromParcel(Parcel source) {
        this.mReasonDetailLocalized = source.readString();
        this.mReasonName = source.readString();
        this.mReasonNameLocalized = source.readString();
        this.mShowOtherNote = source.createBooleanArray()[0];
    }
}
