package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.analytics.FindTweenAnalytics;

public enum SearchMethod implements Parcelable {
    AutoComplete("autocomplete", "use_autocomplete_result"),
    CurrentLocation(FindTweenAnalytics.SEARCH_TYPE_CURRENT_LOCATION, "use_current_location"),
    InputFromKeyboard("type", "use_typed_location"),
    SavedSearch("history", "use_saved_search");
    
    public static final Creator<SearchMethod> CREATOR = null;
    private final String action;
    private final String type;

    static {
        CREATOR = new Creator<SearchMethod>() {
            public SearchMethod createFromParcel(Parcel source) {
                return SearchMethod.values()[source.readInt()];
            }

            public SearchMethod[] newArray(int size) {
                return new SearchMethod[size];
            }
        };
    }

    private SearchMethod(String type2, String action2) {
        this.type = type2;
        this.action = action2;
    }

    public String getType() {
        return this.type;
    }

    public String getAction() {
        return this.action;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
