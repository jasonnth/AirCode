package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenWishListedArticle;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.core.wishlists.WishListableType;
import java.util.Collections;
import java.util.List;

public class WishListedArticle extends GenWishListedArticle implements WishListItem {
    public static final Creator<WishListedArticle> CREATOR = new Creator<WishListedArticle>() {
        public WishListedArticle[] newArray(int size) {
            return new WishListedArticle[size];
        }

        public WishListedArticle createFromParcel(Parcel source) {
            WishListedArticle object = new WishListedArticle();
            object.readFromParcel(source);
            return object;
        }
    };

    public WishListableType getItemType() {
        return WishListableType.StoryArticle;
    }

    public long getItemId() {
        return getArticleId();
    }

    public boolean isAvailable() {
        return true;
    }

    public List<Long> getUpVotes() {
        return Collections.emptyList();
    }

    public List<Long> getDownVotes() {
        return Collections.emptyList();
    }
}
