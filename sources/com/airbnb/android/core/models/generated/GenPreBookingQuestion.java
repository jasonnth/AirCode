package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPreBookingQuestion implements Parcelable {
    @JsonProperty("checked")
    protected boolean mChecked;
    @JsonProperty("question")
    protected String mQuestion;
    @JsonProperty("type")
    protected String mType;

    protected GenPreBookingQuestion(String question, String type, boolean checked) {
        this();
        this.mQuestion = question;
        this.mType = type;
        this.mChecked = checked;
    }

    protected GenPreBookingQuestion() {
    }

    public String getQuestion() {
        return this.mQuestion;
    }

    @JsonProperty("question")
    public void setQuestion(String value) {
        this.mQuestion = value;
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    @JsonProperty("checked")
    public void setChecked(boolean value) {
        this.mChecked = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mQuestion);
        parcel.writeString(this.mType);
        parcel.writeBooleanArray(new boolean[]{this.mChecked});
    }

    public void readFromParcel(Parcel source) {
        this.mQuestion = source.readString();
        this.mType = source.readString();
        this.mChecked = source.createBooleanArray()[0];
    }
}
