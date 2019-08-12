package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ListingRegistrationHelpLink;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingRegistrationProcessInputGroup implements Parcelable {
    @JsonProperty("group_help_link")
    protected ListingRegistrationHelpLink mGroupHelpLink;
    @JsonProperty("group_id")
    protected String mGroupId;
    @JsonProperty("group_subtitles")
    protected List<String> mGroupSubtitles;
    @JsonProperty("group_summary_title")
    protected String mGroupSummaryTitle;
    @JsonProperty("group_title")
    protected String mGroupTitle;
    @JsonProperty("questions")
    protected List<ListingRegistrationQuestion> mQuestions;
    @JsonProperty("root_questions")
    protected List<String> mRootQuestions;

    protected GenListingRegistrationProcessInputGroup(List<ListingRegistrationQuestion> questions, List<String> groupSubtitles, List<String> rootQuestions, ListingRegistrationHelpLink groupHelpLink, String groupId, String groupTitle, String groupSummaryTitle) {
        this();
        this.mQuestions = questions;
        this.mGroupSubtitles = groupSubtitles;
        this.mRootQuestions = rootQuestions;
        this.mGroupHelpLink = groupHelpLink;
        this.mGroupId = groupId;
        this.mGroupTitle = groupTitle;
        this.mGroupSummaryTitle = groupSummaryTitle;
    }

    protected GenListingRegistrationProcessInputGroup() {
    }

    public List<ListingRegistrationQuestion> getQuestions() {
        return this.mQuestions;
    }

    @JsonProperty("questions")
    public void setQuestions(List<ListingRegistrationQuestion> value) {
        this.mQuestions = value;
    }

    public List<String> getGroupSubtitles() {
        return this.mGroupSubtitles;
    }

    @JsonProperty("group_subtitles")
    public void setGroupSubtitles(List<String> value) {
        this.mGroupSubtitles = value;
    }

    public List<String> getRootQuestions() {
        return this.mRootQuestions;
    }

    @JsonProperty("root_questions")
    public void setRootQuestions(List<String> value) {
        this.mRootQuestions = value;
    }

    public ListingRegistrationHelpLink getGroupHelpLink() {
        return this.mGroupHelpLink;
    }

    @JsonProperty("group_help_link")
    public void setGroupHelpLink(ListingRegistrationHelpLink value) {
        this.mGroupHelpLink = value;
    }

    public String getGroupId() {
        return this.mGroupId;
    }

    @JsonProperty("group_id")
    public void setGroupId(String value) {
        this.mGroupId = value;
    }

    public String getGroupTitle() {
        return this.mGroupTitle;
    }

    @JsonProperty("group_title")
    public void setGroupTitle(String value) {
        this.mGroupTitle = value;
    }

    public String getGroupSummaryTitle() {
        return this.mGroupSummaryTitle;
    }

    @JsonProperty("group_summary_title")
    public void setGroupSummaryTitle(String value) {
        this.mGroupSummaryTitle = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mQuestions);
        parcel.writeStringList(this.mGroupSubtitles);
        parcel.writeStringList(this.mRootQuestions);
        parcel.writeParcelable(this.mGroupHelpLink, 0);
        parcel.writeString(this.mGroupId);
        parcel.writeString(this.mGroupTitle);
        parcel.writeString(this.mGroupSummaryTitle);
    }

    public void readFromParcel(Parcel source) {
        this.mQuestions = source.createTypedArrayList(ListingRegistrationQuestion.CREATOR);
        this.mGroupSubtitles = source.createStringArrayList();
        this.mRootQuestions = source.createStringArrayList();
        this.mGroupHelpLink = (ListingRegistrationHelpLink) source.readParcelable(ListingRegistrationHelpLink.class.getClassLoader());
        this.mGroupId = source.readString();
        this.mGroupTitle = source.readString();
        this.mGroupSummaryTitle = source.readString();
    }
}
