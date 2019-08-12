package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.UserFlag;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenArticleComment implements Parcelable {
    @JsonProperty("author")
    protected User mAuthor;
    @JsonProperty("content")
    protected String mContent;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("like_count")
    protected Integer mLikeCount;
    @JsonProperty("liked")
    protected boolean mLiked;
    @JsonProperty("parent_comment")
    protected ArticleComment mParentComment;
    @JsonProperty("published_at_human_readable")
    protected String mPublishedAtHumanReadable;
    @JsonProperty("user_flag")
    protected UserFlag mUserFlag;

    protected GenArticleComment(ArticleComment parentComment, Integer likeCount, String content, String publishedAtHumanReadable, User author, UserFlag userFlag, boolean liked, long id) {
        this();
        this.mParentComment = parentComment;
        this.mLikeCount = likeCount;
        this.mContent = content;
        this.mPublishedAtHumanReadable = publishedAtHumanReadable;
        this.mAuthor = author;
        this.mUserFlag = userFlag;
        this.mLiked = liked;
        this.mId = id;
    }

    protected GenArticleComment() {
    }

    public ArticleComment getParentComment() {
        return this.mParentComment;
    }

    @JsonProperty("parent_comment")
    public void setParentComment(ArticleComment value) {
        this.mParentComment = value;
    }

    public Integer getLikeCount() {
        return this.mLikeCount;
    }

    @JsonProperty("like_count")
    public void setLikeCount(Integer value) {
        this.mLikeCount = value;
    }

    public String getContent() {
        return this.mContent;
    }

    @JsonProperty("content")
    public void setContent(String value) {
        this.mContent = value;
    }

    public String getPublishedAtHumanReadable() {
        return this.mPublishedAtHumanReadable;
    }

    @JsonProperty("published_at_human_readable")
    public void setPublishedAtHumanReadable(String value) {
        this.mPublishedAtHumanReadable = value;
    }

    public User getAuthor() {
        return this.mAuthor;
    }

    @JsonProperty("author")
    public void setAuthor(User value) {
        this.mAuthor = value;
    }

    public UserFlag getUserFlag() {
        return this.mUserFlag;
    }

    @JsonProperty("user_flag")
    public void setUserFlag(UserFlag value) {
        this.mUserFlag = value;
    }

    public boolean isLiked() {
        return this.mLiked;
    }

    @JsonProperty("liked")
    public void setLiked(boolean value) {
        this.mLiked = value;
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
        parcel.writeParcelable(this.mParentComment, 0);
        parcel.writeValue(this.mLikeCount);
        parcel.writeString(this.mContent);
        parcel.writeString(this.mPublishedAtHumanReadable);
        parcel.writeParcelable(this.mAuthor, 0);
        parcel.writeParcelable(this.mUserFlag, 0);
        parcel.writeBooleanArray(new boolean[]{this.mLiked});
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mParentComment = (ArticleComment) source.readParcelable(ArticleComment.class.getClassLoader());
        this.mLikeCount = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mContent = source.readString();
        this.mPublishedAtHumanReadable = source.readString();
        this.mAuthor = (User) source.readParcelable(User.class.getClassLoader());
        this.mUserFlag = (UserFlag) source.readParcelable(UserFlag.class.getClassLoader());
        this.mLiked = source.createBooleanArray()[0];
        this.mId = source.readLong();
    }
}
