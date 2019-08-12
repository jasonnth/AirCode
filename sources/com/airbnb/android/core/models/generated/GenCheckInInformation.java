package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ListingAmenityInformation;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCheckInInformation implements Parcelable {
    @JsonProperty("amenity")
    protected ListingAmenityInformation mAmenity;
    @JsonProperty("group_id")
    protected int mGroupId;
    @JsonProperty("instruction")
    protected String mInstruction;
    @JsonProperty("instruction_visible")
    protected Boolean mIsInstructionVisible;
    @JsonProperty("option_available")
    protected Boolean mIsOptionAvailable;
    @JsonProperty("localized_description")
    protected String mLocalizedDescription;
    @JsonProperty("localized_group_name")
    protected String mLocalizedGroupName;
    @JsonProperty("localized_instruction_button_label")
    protected String mLocalizedInstructionButtonLabel;
    @JsonProperty("localized_instruction_hint")
    protected String mLocalizedInstructionHint;
    @JsonProperty("localized_instruction_subtitle")
    protected String mLocalizedInstructionSubtitle;

    protected GenCheckInInformation(Boolean isInstructionVisible, Boolean isOptionAvailable, ListingAmenityInformation amenity, String localizedDescription, String localizedInstructionSubtitle, String localizedInstructionHint, String localizedInstructionButtonLabel, String localizedGroupName, String instruction, int groupId) {
        this();
        this.mIsInstructionVisible = isInstructionVisible;
        this.mIsOptionAvailable = isOptionAvailable;
        this.mAmenity = amenity;
        this.mLocalizedDescription = localizedDescription;
        this.mLocalizedInstructionSubtitle = localizedInstructionSubtitle;
        this.mLocalizedInstructionHint = localizedInstructionHint;
        this.mLocalizedInstructionButtonLabel = localizedInstructionButtonLabel;
        this.mLocalizedGroupName = localizedGroupName;
        this.mInstruction = instruction;
        this.mGroupId = groupId;
    }

    protected GenCheckInInformation() {
    }

    public Boolean isIsInstructionVisible() {
        return this.mIsInstructionVisible;
    }

    @JsonProperty("instruction_visible")
    public void setIsInstructionVisible(Boolean value) {
        this.mIsInstructionVisible = value;
    }

    public Boolean isIsOptionAvailable() {
        return this.mIsOptionAvailable;
    }

    @JsonProperty("option_available")
    public void setIsOptionAvailable(Boolean value) {
        this.mIsOptionAvailable = value;
    }

    public ListingAmenityInformation getAmenity() {
        return this.mAmenity;
    }

    @JsonProperty("amenity")
    public void setAmenity(ListingAmenityInformation value) {
        this.mAmenity = value;
    }

    public String getLocalizedDescription() {
        return this.mLocalizedDescription;
    }

    @JsonProperty("localized_description")
    public void setLocalizedDescription(String value) {
        this.mLocalizedDescription = value;
    }

    public String getLocalizedInstructionSubtitle() {
        return this.mLocalizedInstructionSubtitle;
    }

    @JsonProperty("localized_instruction_subtitle")
    public void setLocalizedInstructionSubtitle(String value) {
        this.mLocalizedInstructionSubtitle = value;
    }

    public String getLocalizedInstructionHint() {
        return this.mLocalizedInstructionHint;
    }

    @JsonProperty("localized_instruction_hint")
    public void setLocalizedInstructionHint(String value) {
        this.mLocalizedInstructionHint = value;
    }

    public String getLocalizedInstructionButtonLabel() {
        return this.mLocalizedInstructionButtonLabel;
    }

    @JsonProperty("localized_instruction_button_label")
    public void setLocalizedInstructionButtonLabel(String value) {
        this.mLocalizedInstructionButtonLabel = value;
    }

    public String getLocalizedGroupName() {
        return this.mLocalizedGroupName;
    }

    @JsonProperty("localized_group_name")
    public void setLocalizedGroupName(String value) {
        this.mLocalizedGroupName = value;
    }

    public String getInstruction() {
        return this.mInstruction;
    }

    @JsonProperty("instruction")
    public void setInstruction(String value) {
        this.mInstruction = value;
    }

    public int getGroupId() {
        return this.mGroupId;
    }

    @JsonProperty("group_id")
    public void setGroupId(int value) {
        this.mGroupId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mIsInstructionVisible);
        parcel.writeValue(this.mIsOptionAvailable);
        parcel.writeParcelable(this.mAmenity, 0);
        parcel.writeString(this.mLocalizedDescription);
        parcel.writeString(this.mLocalizedInstructionSubtitle);
        parcel.writeString(this.mLocalizedInstructionHint);
        parcel.writeString(this.mLocalizedInstructionButtonLabel);
        parcel.writeString(this.mLocalizedGroupName);
        parcel.writeString(this.mInstruction);
        parcel.writeInt(this.mGroupId);
    }

    public void readFromParcel(Parcel source) {
        this.mIsInstructionVisible = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mIsOptionAvailable = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mAmenity = (ListingAmenityInformation) source.readParcelable(ListingAmenityInformation.class.getClassLoader());
        this.mLocalizedDescription = source.readString();
        this.mLocalizedInstructionSubtitle = source.readString();
        this.mLocalizedInstructionHint = source.readString();
        this.mLocalizedInstructionButtonLabel = source.readString();
        this.mLocalizedGroupName = source.readString();
        this.mInstruction = source.readString();
        this.mGroupId = source.readInt();
    }
}
