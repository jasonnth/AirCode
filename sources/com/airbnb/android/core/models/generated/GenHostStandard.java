package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHostStandard implements Parcelable {
    @JsonProperty("can_reactivate")
    protected boolean mCanReactivate;
    @JsonProperty("reactivation_body_text")
    protected String mReactivationBodyText;
    @JsonProperty("reactivation_help_link")
    protected String mReactivationHelpLink;
    @JsonProperty("reactivation_learn_more_text")
    protected String mReactivationHelpText;
    @JsonProperty("suspension_type")
    protected String mSuspensionType;

    protected GenHostStandard(String reactivationBodyText, String reactivationHelpText, String reactivationHelpLink, String suspensionType, boolean canReactivate) {
        this();
        this.mReactivationBodyText = reactivationBodyText;
        this.mReactivationHelpText = reactivationHelpText;
        this.mReactivationHelpLink = reactivationHelpLink;
        this.mSuspensionType = suspensionType;
        this.mCanReactivate = canReactivate;
    }

    protected GenHostStandard() {
    }

    public String getReactivationBodyText() {
        return this.mReactivationBodyText;
    }

    @JsonProperty("reactivation_body_text")
    public void setReactivationBodyText(String value) {
        this.mReactivationBodyText = value;
    }

    public String getReactivationHelpText() {
        return this.mReactivationHelpText;
    }

    @JsonProperty("reactivation_learn_more_text")
    public void setReactivationHelpText(String value) {
        this.mReactivationHelpText = value;
    }

    public String getReactivationHelpLink() {
        return this.mReactivationHelpLink;
    }

    @JsonProperty("reactivation_help_link")
    public void setReactivationHelpLink(String value) {
        this.mReactivationHelpLink = value;
    }

    public String getSuspensionType() {
        return this.mSuspensionType;
    }

    @JsonProperty("suspension_type")
    public void setSuspensionType(String value) {
        this.mSuspensionType = value;
    }

    public boolean isCanReactivate() {
        return this.mCanReactivate;
    }

    @JsonProperty("can_reactivate")
    public void setCanReactivate(boolean value) {
        this.mCanReactivate = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mReactivationBodyText);
        parcel.writeString(this.mReactivationHelpText);
        parcel.writeString(this.mReactivationHelpLink);
        parcel.writeString(this.mSuspensionType);
        parcel.writeBooleanArray(new boolean[]{this.mCanReactivate});
    }

    public void readFromParcel(Parcel source) {
        this.mReactivationBodyText = source.readString();
        this.mReactivationHelpText = source.readString();
        this.mReactivationHelpLink = source.readString();
        this.mSuspensionType = source.readString();
        this.mCanReactivate = source.createBooleanArray()[0];
    }
}
