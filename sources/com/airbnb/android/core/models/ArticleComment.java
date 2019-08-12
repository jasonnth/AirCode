package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenArticleComment;

public class ArticleComment extends GenArticleComment {
    public static final Creator<ArticleComment> CREATOR = new Creator<ArticleComment>() {
        public ArticleComment[] newArray(int size) {
            return new ArticleComment[size];
        }

        public ArticleComment createFromParcel(Parcel source) {
            ArticleComment object = new ArticleComment();
            object.readFromParcel(source);
            return object;
        }
    };
}
