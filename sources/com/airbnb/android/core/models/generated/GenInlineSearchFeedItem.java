package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.enums.InlineSearchFeedItemType;
import com.airbnb.android.core.models.InlineSearchFeedFilterItem;
import com.airbnb.android.core.models.InlineSearchFeedMessageItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenInlineSearchFeedItem implements Parcelable {
    @JsonProperty("filter")
    protected InlineSearchFeedFilterItem mFilter;
    @JsonProperty("message")
    protected InlineSearchFeedMessageItem mMessage;
    @JsonProperty("position")
    protected int mPosition;
    @JsonProperty("type")
    protected InlineSearchFeedItemType mType;

    protected GenInlineSearchFeedItem(InlineSearchFeedFilterItem filter, InlineSearchFeedItemType type, InlineSearchFeedMessageItem message, int position) {
        this();
        this.mFilter = filter;
        this.mType = type;
        this.mMessage = message;
        this.mPosition = position;
    }

    protected GenInlineSearchFeedItem() {
    }

    public InlineSearchFeedFilterItem getFilter() {
        return this.mFilter;
    }

    @JsonProperty("filter")
    public void setFilter(InlineSearchFeedFilterItem value) {
        this.mFilter = value;
    }

    public InlineSearchFeedItemType getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(InlineSearchFeedItemType value) {
        this.mType = value;
    }

    public InlineSearchFeedMessageItem getMessage() {
        return this.mMessage;
    }

    @JsonProperty("message")
    public void setMessage(InlineSearchFeedMessageItem value) {
        this.mMessage = value;
    }

    public int getPosition() {
        return this.mPosition;
    }

    @JsonProperty("position")
    public void setPosition(int value) {
        this.mPosition = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mFilter, 0);
        parcel.writeSerializable(this.mType);
        parcel.writeParcelable(this.mMessage, 0);
        parcel.writeInt(this.mPosition);
    }

    public void readFromParcel(Parcel source) {
        this.mFilter = (InlineSearchFeedFilterItem) source.readParcelable(InlineSearchFeedFilterItem.class.getClassLoader());
        this.mType = (InlineSearchFeedItemType) source.readSerializable();
        this.mMessage = (InlineSearchFeedMessageItem) source.readParcelable(InlineSearchFeedMessageItem.class.getClassLoader());
        this.mPosition = source.readInt();
    }
}
