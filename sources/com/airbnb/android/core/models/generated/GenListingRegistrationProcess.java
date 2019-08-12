package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ListingRegistration;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.core.models.ListingRegistrationSubmission;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingRegistrationProcess implements Parcelable {
    @JsonProperty("content")
    protected ListingRegistrationContent mContent;
    @JsonProperty("input_groups")
    protected List<ListingRegistrationProcessInputGroup> mInputGroups;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("listing_registration")
    protected ListingRegistration mListingRegistration;
    @JsonProperty("open_submission")
    protected ListingRegistrationSubmission mOpenSubmission;
    @JsonProperty("regulatory_body")
    protected String mRegulatoryBody;
    @JsonProperty("required_before_publish")
    protected boolean mRequiredBeforePublish;
    @JsonProperty("required_for_exemption")
    protected List<String> mRequiredForExemption;

    protected GenListingRegistrationProcess(List<ListingRegistrationProcessInputGroup> inputGroups, List<String> requiredForExemption, ListingRegistration listingRegistration, ListingRegistrationContent content, ListingRegistrationSubmission openSubmission, String regulatoryBody, boolean requiredBeforePublish, long listingId) {
        this();
        this.mInputGroups = inputGroups;
        this.mRequiredForExemption = requiredForExemption;
        this.mListingRegistration = listingRegistration;
        this.mContent = content;
        this.mOpenSubmission = openSubmission;
        this.mRegulatoryBody = regulatoryBody;
        this.mRequiredBeforePublish = requiredBeforePublish;
        this.mListingId = listingId;
    }

    protected GenListingRegistrationProcess() {
    }

    public List<ListingRegistrationProcessInputGroup> getInputGroups() {
        return this.mInputGroups;
    }

    @JsonProperty("input_groups")
    public void setInputGroups(List<ListingRegistrationProcessInputGroup> value) {
        this.mInputGroups = value;
    }

    public List<String> getRequiredForExemption() {
        return this.mRequiredForExemption;
    }

    @JsonProperty("required_for_exemption")
    public void setRequiredForExemption(List<String> value) {
        this.mRequiredForExemption = value;
    }

    public ListingRegistration getListingRegistration() {
        return this.mListingRegistration;
    }

    @JsonProperty("listing_registration")
    public void setListingRegistration(ListingRegistration value) {
        this.mListingRegistration = value;
    }

    public ListingRegistrationContent getContent() {
        return this.mContent;
    }

    @JsonProperty("content")
    public void setContent(ListingRegistrationContent value) {
        this.mContent = value;
    }

    public ListingRegistrationSubmission getOpenSubmission() {
        return this.mOpenSubmission;
    }

    @JsonProperty("open_submission")
    public void setOpenSubmission(ListingRegistrationSubmission value) {
        this.mOpenSubmission = value;
    }

    public String getRegulatoryBody() {
        return this.mRegulatoryBody;
    }

    @JsonProperty("regulatory_body")
    public void setRegulatoryBody(String value) {
        this.mRegulatoryBody = value;
    }

    public boolean isRequiredBeforePublish() {
        return this.mRequiredBeforePublish;
    }

    @JsonProperty("required_before_publish")
    public void setRequiredBeforePublish(boolean value) {
        this.mRequiredBeforePublish = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mInputGroups);
        parcel.writeStringList(this.mRequiredForExemption);
        parcel.writeParcelable(this.mListingRegistration, 0);
        parcel.writeParcelable(this.mContent, 0);
        parcel.writeParcelable(this.mOpenSubmission, 0);
        parcel.writeString(this.mRegulatoryBody);
        parcel.writeBooleanArray(new boolean[]{this.mRequiredBeforePublish});
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mInputGroups = source.createTypedArrayList(ListingRegistrationProcessInputGroup.CREATOR);
        this.mRequiredForExemption = source.createStringArrayList();
        this.mListingRegistration = (ListingRegistration) source.readParcelable(ListingRegistration.class.getClassLoader());
        this.mContent = (ListingRegistrationContent) source.readParcelable(ListingRegistrationContent.class.getClassLoader());
        this.mOpenSubmission = (ListingRegistrationSubmission) source.readParcelable(ListingRegistrationSubmission.class.getClassLoader());
        this.mRegulatoryBody = source.readString();
        this.mRequiredBeforePublish = source.createBooleanArray()[0];
        this.mListingId = source.readLong();
    }
}
