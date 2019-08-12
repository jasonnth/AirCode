package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.HelpThreadDisplayElement;
import com.airbnb.android.core.models.HelpThreadOption;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenHelpThreadNode implements Parcelable {
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("help_options")
    protected List<HelpThreadOption> mHelpOptions;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("is_current")
    protected boolean mIsCurrent;
    @JsonProperty("is_invisible_node")
    protected boolean mIsInvisibleNode;
    @JsonProperty("selected_option")
    protected String mSelectedOption;
    @JsonProperty("things_to_display")
    protected List<HelpThreadDisplayElement> mThingsToDisplay;
    @JsonProperty("updated_at")
    protected AirDateTime mUpdatedAt;

    protected GenHelpThreadNode(AirDateTime createdAt, AirDateTime updatedAt, List<HelpThreadDisplayElement> thingsToDisplay, List<HelpThreadOption> helpOptions, String selectedOption, boolean isCurrent, boolean isInvisibleNode, long id) {
        this();
        this.mCreatedAt = createdAt;
        this.mUpdatedAt = updatedAt;
        this.mThingsToDisplay = thingsToDisplay;
        this.mHelpOptions = helpOptions;
        this.mSelectedOption = selectedOption;
        this.mIsCurrent = isCurrent;
        this.mIsInvisibleNode = isInvisibleNode;
        this.mId = id;
    }

    protected GenHelpThreadNode() {
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public AirDateTime getUpdatedAt() {
        return this.mUpdatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(AirDateTime value) {
        this.mUpdatedAt = value;
    }

    public List<HelpThreadDisplayElement> getThingsToDisplay() {
        return this.mThingsToDisplay;
    }

    @JsonProperty("things_to_display")
    public void setThingsToDisplay(List<HelpThreadDisplayElement> value) {
        this.mThingsToDisplay = value;
    }

    public List<HelpThreadOption> getHelpOptions() {
        return this.mHelpOptions;
    }

    @JsonProperty("help_options")
    public void setHelpOptions(List<HelpThreadOption> value) {
        this.mHelpOptions = value;
    }

    public String getSelectedOption() {
        return this.mSelectedOption;
    }

    @JsonProperty("selected_option")
    public void setSelectedOption(String value) {
        this.mSelectedOption = value;
    }

    public boolean isCurrent() {
        return this.mIsCurrent;
    }

    @JsonProperty("is_current")
    public void setIsCurrent(boolean value) {
        this.mIsCurrent = value;
    }

    public boolean isInvisibleNode() {
        return this.mIsInvisibleNode;
    }

    @JsonProperty("is_invisible_node")
    public void setIsInvisibleNode(boolean value) {
        this.mIsInvisibleNode = value;
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
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeParcelable(this.mUpdatedAt, 0);
        parcel.writeTypedList(this.mThingsToDisplay);
        parcel.writeTypedList(this.mHelpOptions);
        parcel.writeString(this.mSelectedOption);
        parcel.writeBooleanArray(new boolean[]{this.mIsCurrent, this.mIsInvisibleNode});
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mUpdatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mThingsToDisplay = source.createTypedArrayList(HelpThreadDisplayElement.CREATOR);
        this.mHelpOptions = source.createTypedArrayList(HelpThreadOption.CREATOR);
        this.mSelectedOption = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mIsCurrent = bools[0];
        this.mIsInvisibleNode = bools[1];
        this.mId = source.readLong();
    }
}
