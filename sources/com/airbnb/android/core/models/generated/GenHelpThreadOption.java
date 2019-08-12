package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.HelpThreadOnClickAction;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHelpThreadOption implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("label")
    protected String mLabel;
    @JsonProperty("on_click")
    protected HelpThreadOnClickAction mOnClick;
    @JsonProperty("value")
    protected String mValue;

    protected GenHelpThreadOption(HelpThreadOnClickAction onClick, String label, String value, long id) {
        this();
        this.mOnClick = onClick;
        this.mLabel = label;
        this.mValue = value;
        this.mId = id;
    }

    protected GenHelpThreadOption() {
    }

    public HelpThreadOnClickAction getOnClick() {
        return this.mOnClick;
    }

    @JsonProperty("on_click")
    public void setOnClick(HelpThreadOnClickAction value) {
        this.mOnClick = value;
    }

    public String getLabel() {
        return this.mLabel;
    }

    @JsonProperty("label")
    public void setLabel(String value) {
        this.mLabel = value;
    }

    public String getValue() {
        return this.mValue;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.mValue = value;
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
        parcel.writeParcelable(this.mOnClick, 0);
        parcel.writeString(this.mLabel);
        parcel.writeString(this.mValue);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mOnClick = (HelpThreadOnClickAction) source.readParcelable(HelpThreadOnClickAction.class.getClassLoader());
        this.mLabel = source.readString();
        this.mValue = source.readString();
        this.mId = source.readLong();
    }
}
