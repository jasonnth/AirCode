package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenExploreMetaData implements Parcelable {
    @JsonProperty("federated_search_id")
    protected String mFederatedSearchId;
    @JsonProperty("federated_search_session_id")
    protected String mFederatedSearchSessionId;
    @JsonProperty("current_tab_id")
    protected String mLandingTabId;

    protected GenExploreMetaData(String landingTabId, String federatedSearchId, String federatedSearchSessionId) {
        this();
        this.mLandingTabId = landingTabId;
        this.mFederatedSearchId = federatedSearchId;
        this.mFederatedSearchSessionId = federatedSearchSessionId;
    }

    protected GenExploreMetaData() {
    }

    public String getLandingTabId() {
        return this.mLandingTabId;
    }

    @JsonProperty("current_tab_id")
    public void setLandingTabId(String value) {
        this.mLandingTabId = value;
    }

    public String getFederatedSearchId() {
        return this.mFederatedSearchId;
    }

    @JsonProperty("federated_search_id")
    public void setFederatedSearchId(String value) {
        this.mFederatedSearchId = value;
    }

    public String getFederatedSearchSessionId() {
        return this.mFederatedSearchSessionId;
    }

    @JsonProperty("federated_search_session_id")
    public void setFederatedSearchSessionId(String value) {
        this.mFederatedSearchSessionId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mLandingTabId);
        parcel.writeString(this.mFederatedSearchId);
        parcel.writeString(this.mFederatedSearchSessionId);
    }

    public void readFromParcel(Parcel source) {
        this.mLandingTabId = source.readString();
        this.mFederatedSearchId = source.readString();
        this.mFederatedSearchSessionId = source.readString();
    }
}
