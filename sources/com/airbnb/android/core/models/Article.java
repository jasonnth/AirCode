package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenArticle;
import com.google.common.collect.FluentIterable;

public class Article extends GenArticle {
    public static final Creator<Article> CREATOR = new Creator<Article>() {
        public Article[] newArray(int size) {
            return new Article[size];
        }

        public Article createFromParcel(Parcel source) {
            Article object = new Article();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum Type {
        Simple("simple_article");
        
        private final String key;

        private Type(String key2) {
            this.key = key2;
        }

        public static Type forKey(String key2) {
            return (Type) FluentIterable.m1283of(values()).firstMatch(Article$Type$$Lambda$1.lambdaFactory$(key2)).orNull();
        }
    }

    public Type getType() {
        return Type.forKey(getTypeStr());
    }

    public String getAuthorPictureUrl() {
        if (getAuthorObject() != null) {
            return getAuthorObject().getPictureUrl();
        }
        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mId != ((Article) o).mId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }
}
