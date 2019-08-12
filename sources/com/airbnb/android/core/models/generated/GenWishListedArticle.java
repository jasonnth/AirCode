package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Article;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenWishListedArticle implements Parcelable {
    @JsonProperty("article")
    protected Article mArticle;
    @JsonProperty("article_id")
    protected long mArticleId;
    @JsonProperty("collection_id")
    protected long mCollectionId;
    @JsonProperty("id")
    protected long mId;

    protected GenWishListedArticle(Article article, long id, long articleId, long collectionId) {
        this();
        this.mArticle = article;
        this.mId = id;
        this.mArticleId = articleId;
        this.mCollectionId = collectionId;
    }

    protected GenWishListedArticle() {
    }

    public Article getArticle() {
        return this.mArticle;
    }

    @JsonProperty("article")
    public void setArticle(Article value) {
        this.mArticle = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getArticleId() {
        return this.mArticleId;
    }

    @JsonProperty("article_id")
    public void setArticleId(long value) {
        this.mArticleId = value;
    }

    public long getCollectionId() {
        return this.mCollectionId;
    }

    @JsonProperty("collection_id")
    public void setCollectionId(long value) {
        this.mCollectionId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mArticle, 0);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mArticleId);
        parcel.writeLong(this.mCollectionId);
    }

    public void readFromParcel(Parcel source) {
        this.mArticle = (Article) source.readParcelable(Article.class.getClassLoader());
        this.mId = source.readLong();
        this.mArticleId = source.readLong();
        this.mCollectionId = source.readLong();
    }
}
