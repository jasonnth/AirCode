package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaQuestion;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingPersonaInput implements Parcelable {
    @JsonProperty("answer")
    protected ListingPersonaAnswer mAnswer;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("question")
    protected ListingPersonaQuestion mQuestion;

    protected GenListingPersonaInput(ListingPersonaAnswer answer, ListingPersonaQuestion question, long id) {
        this();
        this.mAnswer = answer;
        this.mQuestion = question;
        this.mId = id;
    }

    protected GenListingPersonaInput() {
    }

    public ListingPersonaAnswer getAnswer() {
        return this.mAnswer;
    }

    public ListingPersonaQuestion getQuestion() {
        return this.mQuestion;
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
        parcel.writeParcelable(this.mAnswer, 0);
        parcel.writeParcelable(this.mQuestion, 0);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mAnswer = (ListingPersonaAnswer) source.readParcelable(ListingPersonaAnswer.class.getClassLoader());
        this.mQuestion = (ListingPersonaQuestion) source.readParcelable(ListingPersonaQuestion.class.getClassLoader());
        this.mId = source.readLong();
    }
}
