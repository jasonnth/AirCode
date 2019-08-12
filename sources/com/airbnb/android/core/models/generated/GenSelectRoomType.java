package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSelectRoomType implements Parcelable {
    @JsonProperty("highlights")
    protected List<String> mHighlights;
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("name")
    protected String mName;

    protected GenSelectRoomType(List<String> highlights, String name, int id) {
        this();
        this.mHighlights = highlights;
        this.mName = name;
        this.mId = id;
    }

    protected GenSelectRoomType() {
    }

    public List<String> getHighlights() {
        return this.mHighlights;
    }

    @JsonProperty("highlights")
    public void setHighlights(List<String> value) {
        this.mHighlights = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public int getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(int value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringList(this.mHighlights);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mHighlights = source.createStringArrayList();
        this.mName = source.readString();
        this.mId = source.readInt();
    }
}
