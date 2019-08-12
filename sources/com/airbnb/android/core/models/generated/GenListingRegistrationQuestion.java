package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.enums.ListingRegistrationInputType;
import com.airbnb.android.core.models.ListingRegistrationAnswer;
import com.airbnb.android.core.models.ListingRegistrationHelpLink;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingRegistrationQuestion implements Parcelable {
    @JsonProperty("answers")
    protected List<ListingRegistrationAnswer> mAnswers;
    @JsonProperty("conditional_on")
    protected String mConditionalOn;
    @JsonProperty("help_link")
    protected ListingRegistrationHelpLink mHelpLink;
    @JsonProperty("help_texts")
    protected List<String> mHelpTexts;
    @JsonProperty("help_title")
    protected String mHelpTitle;
    @JsonProperty("input_answer")
    protected String mInputAnswer;
    @JsonProperty("input_key")
    protected String mInputKey;
    @JsonProperty("input_type")
    protected ListingRegistrationInputType mInputType;
    @JsonProperty("is_required")
    protected Boolean mIsRequired;
    @JsonProperty("question_subtitles")
    protected List<String> mQuestionSubtitles;
    @JsonProperty("question_text")
    protected String mQuestionText;
    @JsonProperty("question_text_short")
    protected String mQuestionTextShort;

    protected GenListingRegistrationQuestion(Boolean isRequired, List<ListingRegistrationAnswer> answers, List<String> helpTexts, List<String> questionSubtitles, ListingRegistrationHelpLink helpLink, ListingRegistrationInputType inputType, String conditionalOn, String helpTitle, String inputAnswer, String inputKey, String questionText, String questionTextShort) {
        this();
        this.mIsRequired = isRequired;
        this.mAnswers = answers;
        this.mHelpTexts = helpTexts;
        this.mQuestionSubtitles = questionSubtitles;
        this.mHelpLink = helpLink;
        this.mInputType = inputType;
        this.mConditionalOn = conditionalOn;
        this.mHelpTitle = helpTitle;
        this.mInputAnswer = inputAnswer;
        this.mInputKey = inputKey;
        this.mQuestionText = questionText;
        this.mQuestionTextShort = questionTextShort;
    }

    protected GenListingRegistrationQuestion() {
    }

    public Boolean isIsRequired() {
        return this.mIsRequired;
    }

    @JsonProperty("is_required")
    public void setIsRequired(Boolean value) {
        this.mIsRequired = value;
    }

    public List<ListingRegistrationAnswer> getAnswers() {
        return this.mAnswers;
    }

    @JsonProperty("answers")
    public void setAnswers(List<ListingRegistrationAnswer> value) {
        this.mAnswers = value;
    }

    public List<String> getHelpTexts() {
        return this.mHelpTexts;
    }

    @JsonProperty("help_texts")
    public void setHelpTexts(List<String> value) {
        this.mHelpTexts = value;
    }

    public List<String> getQuestionSubtitles() {
        return this.mQuestionSubtitles;
    }

    @JsonProperty("question_subtitles")
    public void setQuestionSubtitles(List<String> value) {
        this.mQuestionSubtitles = value;
    }

    public ListingRegistrationHelpLink getHelpLink() {
        return this.mHelpLink;
    }

    @JsonProperty("help_link")
    public void setHelpLink(ListingRegistrationHelpLink value) {
        this.mHelpLink = value;
    }

    public ListingRegistrationInputType getInputType() {
        return this.mInputType;
    }

    @JsonProperty("input_type")
    public void setInputType(ListingRegistrationInputType value) {
        this.mInputType = value;
    }

    public String getConditionalOn() {
        return this.mConditionalOn;
    }

    @JsonProperty("conditional_on")
    public void setConditionalOn(String value) {
        this.mConditionalOn = value;
    }

    public String getHelpTitle() {
        return this.mHelpTitle;
    }

    @JsonProperty("help_title")
    public void setHelpTitle(String value) {
        this.mHelpTitle = value;
    }

    public String getInputAnswer() {
        return this.mInputAnswer;
    }

    @JsonProperty("input_answer")
    public void setInputAnswer(String value) {
        this.mInputAnswer = value;
    }

    public String getInputKey() {
        return this.mInputKey;
    }

    @JsonProperty("input_key")
    public void setInputKey(String value) {
        this.mInputKey = value;
    }

    public String getQuestionText() {
        return this.mQuestionText;
    }

    @JsonProperty("question_text")
    public void setQuestionText(String value) {
        this.mQuestionText = value;
    }

    public String getQuestionTextShort() {
        return this.mQuestionTextShort;
    }

    @JsonProperty("question_text_short")
    public void setQuestionTextShort(String value) {
        this.mQuestionTextShort = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mIsRequired);
        parcel.writeTypedList(this.mAnswers);
        parcel.writeStringList(this.mHelpTexts);
        parcel.writeStringList(this.mQuestionSubtitles);
        parcel.writeParcelable(this.mHelpLink, 0);
        parcel.writeParcelable(this.mInputType, 0);
        parcel.writeString(this.mConditionalOn);
        parcel.writeString(this.mHelpTitle);
        parcel.writeString(this.mInputAnswer);
        parcel.writeString(this.mInputKey);
        parcel.writeString(this.mQuestionText);
        parcel.writeString(this.mQuestionTextShort);
    }

    public void readFromParcel(Parcel source) {
        this.mIsRequired = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mAnswers = source.createTypedArrayList(ListingRegistrationAnswer.CREATOR);
        this.mHelpTexts = source.createStringArrayList();
        this.mQuestionSubtitles = source.createStringArrayList();
        this.mHelpLink = (ListingRegistrationHelpLink) source.readParcelable(ListingRegistrationHelpLink.class.getClassLoader());
        this.mInputType = (ListingRegistrationInputType) source.readParcelable(ListingRegistrationInputType.class.getClassLoader());
        this.mConditionalOn = source.readString();
        this.mHelpTitle = source.readString();
        this.mInputAnswer = source.readString();
        this.mInputKey = source.readString();
        this.mQuestionText = source.readString();
        this.mQuestionTextShort = source.readString();
    }
}
