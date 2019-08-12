package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingRegistrationAnswer implements Parcelable {
    @JsonProperty("explanation_text")
    protected List<String> mExplanationText;
    @JsonProperty("next_question_key")
    protected String mNextQuestionKey;
    @JsonProperty("text")
    protected String mText;
    @JsonProperty("value")
    protected String mValue;

    protected GenListingRegistrationAnswer(List<String> explanationText, String text, String value, String nextQuestionKey) {
        this();
        this.mExplanationText = explanationText;
        this.mText = text;
        this.mValue = value;
        this.mNextQuestionKey = nextQuestionKey;
    }

    protected GenListingRegistrationAnswer() {
    }

    public List<String> getExplanationText() {
        return this.mExplanationText;
    }

    @JsonProperty("explanation_text")
    public void setExplanationText(List<String> value) {
        this.mExplanationText = value;
    }

    public String getText() {
        return this.mText;
    }

    @JsonProperty("text")
    public void setText(String value) {
        this.mText = value;
    }

    public String getValue() {
        return this.mValue;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.mValue = value;
    }

    public String getNextQuestionKey() {
        return this.mNextQuestionKey;
    }

    @JsonProperty("next_question_key")
    public void setNextQuestionKey(String value) {
        this.mNextQuestionKey = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringList(this.mExplanationText);
        parcel.writeString(this.mText);
        parcel.writeString(this.mValue);
        parcel.writeString(this.mNextQuestionKey);
    }

    public void readFromParcel(Parcel source) {
        this.mExplanationText = source.createStringArrayList();
        this.mText = source.readString();
        this.mValue = source.readString();
        this.mNextQuestionKey = source.readString();
    }
}
