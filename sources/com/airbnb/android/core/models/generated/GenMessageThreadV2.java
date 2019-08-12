package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.PostV2;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenMessageThreadV2 implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("posts")
    protected List<PostV2> mPosts;

    protected GenMessageThreadV2(List<PostV2> posts, long id) {
        this();
        this.mPosts = posts;
        this.mId = id;
    }

    protected GenMessageThreadV2() {
    }

    public List<PostV2> getPosts() {
        return this.mPosts;
    }

    @JsonProperty("posts")
    public void setPosts(List<PostV2> value) {
        this.mPosts = value;
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
        parcel.writeTypedList(this.mPosts);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mPosts = source.createTypedArrayList(PostV2.CREATOR);
        this.mId = source.readLong();
    }
}
