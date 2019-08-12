package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.enums.FilterSuggestionType;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenCategorizedFilters implements Parcelable {
    @JsonProperty("filter_bar_items")
    protected List<FilterSuggestionItem> mItems;
    @JsonProperty("group_name")
    protected String mTitle;
    @JsonProperty("type")
    protected FilterSuggestionType mType;

    protected GenCategorizedFilters(FilterSuggestionType type, List<FilterSuggestionItem> items, String title) {
        this();
        this.mType = type;
        this.mItems = items;
        this.mTitle = title;
    }

    protected GenCategorizedFilters() {
    }

    public FilterSuggestionType getType() {
        return this.mType;
    }

    public List<FilterSuggestionItem> getItems() {
        return this.mItems;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("group_name")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeSerializable(this.mType);
        parcel.writeTypedList(this.mItems);
        parcel.writeString(this.mTitle);
    }

    public void readFromParcel(Parcel source) {
        this.mType = (FilterSuggestionType) source.readSerializable();
        this.mItems = source.createTypedArrayList(FilterSuggestionItem.CREATOR);
        this.mTitle = source.readString();
    }
}
