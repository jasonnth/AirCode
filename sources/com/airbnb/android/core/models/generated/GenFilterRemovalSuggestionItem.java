package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.enums.FilterRemovalSuggestionType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenFilterRemovalSuggestionItem implements Parcelable {
    @JsonProperty("count")
    protected int mCount;
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("type")
    protected FilterRemovalSuggestionType mType;

    protected GenFilterRemovalSuggestionItem(FilterRemovalSuggestionType type, int id, int count) {
        this();
        this.mType = type;
        this.mId = id;
        this.mCount = count;
    }

    protected GenFilterRemovalSuggestionItem() {
    }

    public FilterRemovalSuggestionType getType() {
        return this.mType;
    }

    public int getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(int value) {
        this.mId = value;
    }

    public int getCount() {
        return this.mCount;
    }

    @JsonProperty("count")
    public void setCount(int value) {
        this.mCount = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeSerializable(this.mType);
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mCount);
    }

    public void readFromParcel(Parcel source) {
        this.mType = (FilterRemovalSuggestionType) source.readSerializable();
        this.mId = source.readInt();
        this.mCount = source.readInt();
    }
}
