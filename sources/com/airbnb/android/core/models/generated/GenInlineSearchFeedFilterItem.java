package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.enums.InlineSearchFeedFilterItemType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenInlineSearchFeedFilterItem implements Parcelable {
    @JsonProperty("action_text")
    protected String mActionText;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("text")
    protected String mText;
    @JsonProperty("type")
    protected InlineSearchFeedFilterItemType mType;

    protected GenInlineSearchFeedFilterItem(InlineSearchFeedFilterItemType type, String text, String actionText, long id) {
        this();
        this.mType = type;
        this.mText = text;
        this.mActionText = actionText;
        this.mId = id;
    }

    protected GenInlineSearchFeedFilterItem() {
    }

    public InlineSearchFeedFilterItemType getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(InlineSearchFeedFilterItemType value) {
        this.mType = value;
    }

    public String getText() {
        return this.mText;
    }

    @JsonProperty("text")
    public void setText(String value) {
        this.mText = value;
    }

    public String getActionText() {
        return this.mActionText;
    }

    @JsonProperty("action_text")
    public void setActionText(String value) {
        this.mActionText = value;
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
        parcel.writeSerializable(this.mType);
        parcel.writeString(this.mText);
        parcel.writeString(this.mActionText);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mType = (InlineSearchFeedFilterItemType) source.readSerializable();
        this.mText = source.readString();
        this.mActionText = source.readString();
        this.mId = source.readLong();
    }
}
