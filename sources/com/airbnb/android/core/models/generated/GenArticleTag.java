package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Article;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenArticleTag implements Parcelable {
    @JsonProperty("articles")
    protected List<Article> mArticles;
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("tag_text")
    protected String mText;
    @JsonProperty("tag_type")
    protected String mTypeStr;

    protected GenArticleTag(List<Article> articles, String text, String typeStr, int id) {
        this();
        this.mArticles = articles;
        this.mText = text;
        this.mTypeStr = typeStr;
        this.mId = id;
    }

    protected GenArticleTag() {
    }

    public List<Article> getArticles() {
        return this.mArticles;
    }

    @JsonProperty("articles")
    public void setArticles(List<Article> value) {
        this.mArticles = value;
    }

    public String getText() {
        return this.mText;
    }

    @JsonProperty("tag_text")
    public void setText(String value) {
        this.mText = value;
    }

    public String getTypeStr() {
        return this.mTypeStr;
    }

    @JsonProperty("tag_type")
    public void setTypeStr(String value) {
        this.mTypeStr = value;
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
        parcel.writeTypedList(this.mArticles);
        parcel.writeString(this.mText);
        parcel.writeString(this.mTypeStr);
        parcel.writeInt(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mArticles = source.createTypedArrayList(Article.CREATOR);
        this.mText = source.readString();
        this.mTypeStr = source.readString();
        this.mId = source.readInt();
    }
}
