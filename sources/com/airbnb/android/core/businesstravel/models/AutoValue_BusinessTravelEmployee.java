package com.airbnb.android.core.businesstravel.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_BusinessTravelEmployee extends C$AutoValue_BusinessTravelEmployee {
    public static final Creator<AutoValue_BusinessTravelEmployee> CREATOR = new Creator<AutoValue_BusinessTravelEmployee>() {
        public AutoValue_BusinessTravelEmployee createFromParcel(Parcel in) {
            boolean z;
            boolean z2;
            boolean z3 = false;
            String readString = in.readString();
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            if (in.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (in.readInt() == 1) {
                z3 = true;
            }
            return new AutoValue_BusinessTravelEmployee(readString, z, z2, z3, in.readLong(), in.readLong(), in.readLong());
        }

        public AutoValue_BusinessTravelEmployee[] newArray(int size) {
            return new AutoValue_BusinessTravelEmployee[size];
        }
    };

    AutoValue_BusinessTravelEmployee(String email, boolean thirdPartyBookable, boolean verified, boolean admin, long id, long userId, long businessEntityId) {
        super(email, thirdPartyBookable, verified, admin, id, userId, businessEntityId);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 1;
        dest.writeString(getEmail());
        dest.writeInt(isThirdPartyBookable() ? 1 : 0);
        if (isVerified()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (!isAdmin()) {
            i2 = 0;
        }
        dest.writeInt(i2);
        dest.writeLong(getId());
        dest.writeLong(getUserId());
        dest.writeLong(getBusinessEntityId());
    }

    public int describeContents() {
        return 0;
    }
}
