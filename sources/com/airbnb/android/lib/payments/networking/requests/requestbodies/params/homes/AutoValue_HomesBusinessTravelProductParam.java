package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_HomesBusinessTravelProductParam extends C$AutoValue_HomesBusinessTravelProductParam {
    public static final Creator<AutoValue_HomesBusinessTravelProductParam> CREATOR = new Creator<AutoValue_HomesBusinessTravelProductParam>() {
        public AutoValue_HomesBusinessTravelProductParam createFromParcel(Parcel in) {
            return new AutoValue_HomesBusinessTravelProductParam(in.readInt() == 0 ? in.readString() : null);
        }

        public AutoValue_HomesBusinessTravelProductParam[] newArray(int size) {
            return new AutoValue_HomesBusinessTravelProductParam[size];
        }
    };

    AutoValue_HomesBusinessTravelProductParam(String businessTripNotes) {
        super(businessTripNotes);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (businessTripNotes() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(businessTripNotes());
    }

    public int describeContents() {
        return 0;
    }
}
