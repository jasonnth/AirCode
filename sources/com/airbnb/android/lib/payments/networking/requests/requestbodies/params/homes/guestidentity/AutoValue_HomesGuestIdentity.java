package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_HomesGuestIdentity extends C$AutoValue_HomesGuestIdentity {
    public static final Creator<AutoValue_HomesGuestIdentity> CREATOR = new Creator<AutoValue_HomesGuestIdentity>() {
        public AutoValue_HomesGuestIdentity createFromParcel(Parcel in) {
            boolean z = true;
            Integer valueOf = Integer.valueOf(in.readInt());
            String readString = in.readString();
            if (in.readInt() != 1) {
                z = false;
            }
            return new AutoValue_HomesGuestIdentity(valueOf, readString, z);
        }

        public AutoValue_HomesGuestIdentity[] newArray(int size) {
            return new AutoValue_HomesGuestIdentity[size];
        }
    };

    AutoValue_HomesGuestIdentity(Integer id, String idType, boolean isBooker) {
        super(id, idType, isBooker);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mo10931id().intValue());
        dest.writeString(idType());
        dest.writeInt(isBooker() ? 1 : 0);
    }

    public int describeContents() {
        return 0;
    }
}
