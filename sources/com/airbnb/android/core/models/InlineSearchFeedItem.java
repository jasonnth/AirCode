package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.InlineSearchFeedItemType;
import com.airbnb.android.core.models.generated.GenInlineSearchFeedItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InlineSearchFeedItem extends GenInlineSearchFeedItem {
    public static final Creator<InlineSearchFeedItem> CREATOR = new Creator<InlineSearchFeedItem>() {
        public InlineSearchFeedItem[] newArray(int size) {
            return new InlineSearchFeedItem[size];
        }

        public InlineSearchFeedItem createFromParcel(Parcel source) {
            InlineSearchFeedItem object = new InlineSearchFeedItem();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("type")
    public void setType(String type) {
        this.mType = InlineSearchFeedItemType.fromType(type);
    }
}
