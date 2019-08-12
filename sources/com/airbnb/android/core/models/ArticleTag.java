package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenArticleTag;

public class ArticleTag extends GenArticleTag {
    public static final Creator<ArticleTag> CREATOR = new Creator<ArticleTag>() {
        public ArticleTag[] newArray(int size) {
            return new ArticleTag[size];
        }

        public ArticleTag createFromParcel(Parcel source) {
            ArticleTag object = new ArticleTag();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mId != ((GenArticleTag) o).getId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mId;
    }
}
