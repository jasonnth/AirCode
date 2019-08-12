package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenFilterSuggestionItem;

public class FilterSuggestionItem extends GenFilterSuggestionItem {
    public static final Creator<FilterSuggestionItem> CREATOR = new Creator<FilterSuggestionItem>() {
        public FilterSuggestionItem[] newArray(int size) {
            return new FilterSuggestionItem[size];
        }

        public FilterSuggestionItem createFromParcel(Parcel source) {
            FilterSuggestionItem object = new FilterSuggestionItem();
            object.readFromParcel(source);
            return object;
        }
    };
    private boolean selected;

    public void setSelected(boolean selected2) {
        this.selected = selected2;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GenFilterSuggestionItem that = (GenFilterSuggestionItem) o;
        if (this.mDisplayText != null) {
            return this.mDisplayText.equals(that.getDisplayText());
        }
        if (that.getDisplayText() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.mDisplayText != null) {
            return this.mDisplayText.hashCode();
        }
        return 0;
    }
}
