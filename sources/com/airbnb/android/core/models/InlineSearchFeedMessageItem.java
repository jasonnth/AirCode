package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenInlineSearchFeedMessageItem;

public class InlineSearchFeedMessageItem extends GenInlineSearchFeedMessageItem {
    public static final Creator<InlineSearchFeedMessageItem> CREATOR = new Creator<InlineSearchFeedMessageItem>() {
        public InlineSearchFeedMessageItem[] newArray(int size) {
            return new InlineSearchFeedMessageItem[size];
        }

        public InlineSearchFeedMessageItem createFromParcel(Parcel source) {
            InlineSearchFeedMessageItem object = new InlineSearchFeedMessageItem();
            object.readFromParcel(source);
            return object;
        }
    };
}
