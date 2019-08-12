package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.SeeAllInfoQuery;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenExploreSeeAllInfo implements Parcelable {
    @JsonProperty("query")
    protected SeeAllInfoQuery mQuery;
    @JsonProperty("search_session_id")
    protected String mSearchSessionId;
    @JsonProperty("tab_id")
    protected String mTabId;
    @JsonProperty("tag")
    protected String mTag;

    protected GenExploreSeeAllInfo(SeeAllInfoQuery query, String tabId, String tag, String searchSessionId) {
        this();
        this.mQuery = query;
        this.mTabId = tabId;
        this.mTag = tag;
        this.mSearchSessionId = searchSessionId;
    }

    protected GenExploreSeeAllInfo() {
    }

    public SeeAllInfoQuery getQuery() {
        return this.mQuery;
    }

    @JsonProperty("query")
    public void setQuery(SeeAllInfoQuery value) {
        this.mQuery = value;
    }

    public String getTabId() {
        return this.mTabId;
    }

    @JsonProperty("tab_id")
    public void setTabId(String value) {
        this.mTabId = value;
    }

    public String getTag() {
        return this.mTag;
    }

    @JsonProperty("tag")
    public void setTag(String value) {
        this.mTag = value;
    }

    public String getSearchSessionId() {
        return this.mSearchSessionId;
    }

    @JsonProperty("search_session_id")
    public void setSearchSessionId(String value) {
        this.mSearchSessionId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mQuery, 0);
        parcel.writeString(this.mTabId);
        parcel.writeString(this.mTag);
        parcel.writeString(this.mSearchSessionId);
    }

    public void readFromParcel(Parcel source) {
        this.mQuery = (SeeAllInfoQuery) source.readParcelable(SeeAllInfoQuery.class.getClassLoader());
        this.mTabId = source.readString();
        this.mTag = source.readString();
        this.mSearchSessionId = source.readString();
    }
}
