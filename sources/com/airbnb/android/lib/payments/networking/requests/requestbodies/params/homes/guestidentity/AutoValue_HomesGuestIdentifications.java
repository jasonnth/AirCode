package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

final class AutoValue_HomesGuestIdentifications extends C$AutoValue_HomesGuestIdentifications {
    public static final Creator<AutoValue_HomesGuestIdentifications> CREATOR = new Creator<AutoValue_HomesGuestIdentifications>() {
        public AutoValue_HomesGuestIdentifications createFromParcel(Parcel in) {
            return new AutoValue_HomesGuestIdentifications(in.readArrayList(HomesGuestIdentity.class.getClassLoader()));
        }

        public AutoValue_HomesGuestIdentifications[] newArray(int size) {
            return new AutoValue_HomesGuestIdentifications[size];
        }
    };

    AutoValue_HomesGuestIdentifications(List<HomesGuestIdentity> identifications) {
        super(identifications);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(identifications());
    }

    public int describeContents() {
        return 0;
    }
}
