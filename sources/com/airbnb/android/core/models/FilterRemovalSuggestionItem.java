package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.FilterRemovalSuggestionType;
import com.airbnb.android.core.models.generated.GenFilterRemovalSuggestionItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FilterRemovalSuggestionItem extends GenFilterRemovalSuggestionItem {
    public static final Creator<FilterRemovalSuggestionItem> CREATOR = new Creator<FilterRemovalSuggestionItem>() {
        public FilterRemovalSuggestionItem[] newArray(int size) {
            return new FilterRemovalSuggestionItem[size];
        }

        public FilterRemovalSuggestionItem createFromParcel(Parcel source) {
            FilterRemovalSuggestionItem object = new FilterRemovalSuggestionItem();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("type")
    public void setType(String type) {
        this.mType = FilterRemovalSuggestionType.fromType(type);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GenFilterRemovalSuggestionItem that = (GenFilterRemovalSuggestionItem) o;
        if (this.mId != that.getId()) {
            return false;
        }
        if (this.mType != that.getType()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.mType != null ? this.mType.hashCode() : 0) * 31) + this.mId;
    }
}
