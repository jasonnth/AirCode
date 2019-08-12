package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ListingRegistrationContentObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingRegistrationContentSection implements Parcelable {
    @JsonProperty("body")
    protected List<ListingRegistrationContentObject> mBody;
    @JsonProperty("footer")
    protected ListingRegistrationContentObject mFooter;
    @JsonProperty("help_texts")
    protected List<String> mHelpTexts;
    @JsonProperty("question_subtitles")
    protected List<String> mQuestionSubtitles;
    @JsonProperty("section_call_to_action")
    protected String mSection_call_to_action;
    @JsonProperty("subtitles")
    protected List<ListingRegistrationContentObject> mSubtitles;
    @JsonProperty("title")
    protected String mTitle;

    protected GenListingRegistrationContentSection(List<ListingRegistrationContentObject> body, List<ListingRegistrationContentObject> subtitles, List<String> helpTexts, List<String> questionSubtitles, ListingRegistrationContentObject footer, String section_call_to_action, String title) {
        this();
        this.mBody = body;
        this.mSubtitles = subtitles;
        this.mHelpTexts = helpTexts;
        this.mQuestionSubtitles = questionSubtitles;
        this.mFooter = footer;
        this.mSection_call_to_action = section_call_to_action;
        this.mTitle = title;
    }

    protected GenListingRegistrationContentSection() {
    }

    public List<ListingRegistrationContentObject> getBody() {
        return this.mBody;
    }

    @JsonProperty("body")
    public void setBody(List<ListingRegistrationContentObject> value) {
        this.mBody = value;
    }

    public List<ListingRegistrationContentObject> getSubtitles() {
        return this.mSubtitles;
    }

    @JsonProperty("subtitles")
    public void setSubtitles(List<ListingRegistrationContentObject> value) {
        this.mSubtitles = value;
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

    public ListingRegistrationContentObject getFooter() {
        return this.mFooter;
    }

    @JsonProperty("footer")
    public void setFooter(ListingRegistrationContentObject value) {
        this.mFooter = value;
    }

    public String getSection_call_to_action() {
        return this.mSection_call_to_action;
    }

    @JsonProperty("section_call_to_action")
    public void setSection_call_to_action(String value) {
        this.mSection_call_to_action = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mBody);
        parcel.writeTypedList(this.mSubtitles);
        parcel.writeStringList(this.mHelpTexts);
        parcel.writeStringList(this.mQuestionSubtitles);
        parcel.writeParcelable(this.mFooter, 0);
        parcel.writeString(this.mSection_call_to_action);
        parcel.writeString(this.mTitle);
    }

    public void readFromParcel(Parcel source) {
        this.mBody = source.createTypedArrayList(ListingRegistrationContentObject.CREATOR);
        this.mSubtitles = source.createTypedArrayList(ListingRegistrationContentObject.CREATOR);
        this.mHelpTexts = source.createStringArrayList();
        this.mQuestionSubtitles = source.createStringArrayList();
        this.mFooter = (ListingRegistrationContentObject) source.readParcelable(ListingRegistrationContentObject.class.getClassLoader());
        this.mSection_call_to_action = source.readString();
        this.mTitle = source.readString();
    }
}
