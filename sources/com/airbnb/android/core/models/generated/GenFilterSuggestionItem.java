package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.FilterSuggestionFilters;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenFilterSuggestionItem implements Parcelable {
    @JsonProperty("display_text")
    protected String mDisplayText;
    @JsonProperty("filters")
    protected FilterSuggestionFilters mFilters;

    protected GenFilterSuggestionItem(FilterSuggestionFilters filters, String displayText) {
        this();
        this.mFilters = filters;
        this.mDisplayText = displayText;
    }

    protected GenFilterSuggestionItem() {
    }

    public FilterSuggestionFilters getFilters() {
        return this.mFilters;
    }

    @JsonProperty("filters")
    public void setFilters(FilterSuggestionFilters value) {
        this.mFilters = value;
    }

    public String getDisplayText() {
        return this.mDisplayText;
    }

    @JsonProperty("display_text")
    public void setDisplayText(String value) {
        this.mDisplayText = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mFilters, 0);
        parcel.writeString(this.mDisplayText);
    }

    public void readFromParcel(Parcel source) {
        this.mFilters = (FilterSuggestionFilters) source.readParcelable(FilterSuggestionFilters.class.getClassLoader());
        this.mDisplayText = source.readString();
    }
}
