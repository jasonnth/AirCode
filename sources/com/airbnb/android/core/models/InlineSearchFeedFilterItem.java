package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.InlineSearchFeedFilterItemType;
import com.airbnb.android.core.models.generated.GenInlineSearchFeedFilterItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InlineSearchFeedFilterItem extends GenInlineSearchFeedFilterItem {
    public static final Creator<InlineSearchFeedFilterItem> CREATOR = new Creator<InlineSearchFeedFilterItem>() {
        public InlineSearchFeedFilterItem[] newArray(int size) {
            return new InlineSearchFeedFilterItem[size];
        }

        public InlineSearchFeedFilterItem createFromParcel(Parcel source) {
            InlineSearchFeedFilterItem object = new InlineSearchFeedFilterItem();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("type")
    public void setType(String type) {
        this.mType = InlineSearchFeedFilterItemType.fromType(type);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InlineSearchFeedFilterItem that = (InlineSearchFeedFilterItem) o;
        if (this.mId != that.mId) {
            return false;
        }
        if (this.mType != that.mType) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.mType != null ? this.mType.hashCode() : 0) * 31) + ((int) (this.mId ^ (this.mId >>> 32)));
    }
}
