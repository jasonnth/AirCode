package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.UserTravelStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenForYouMetaData implements Parcelable {
    @JsonProperty("user_travel_status")
    protected UserTravelStatus mUserTravelStatus;

    protected GenForYouMetaData(UserTravelStatus userTravelStatus) {
        this();
        this.mUserTravelStatus = userTravelStatus;
    }

    protected GenForYouMetaData() {
    }

    public UserTravelStatus getUserTravelStatus() {
        return this.mUserTravelStatus;
    }

    @JsonProperty("user_travel_status")
    public void setUserTravelStatus(UserTravelStatus value) {
        this.mUserTravelStatus = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mUserTravelStatus, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mUserTravelStatus = (UserTravelStatus) source.readParcelable(UserTravelStatus.class.getClassLoader());
    }
}
