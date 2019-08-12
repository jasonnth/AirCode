package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReservationUser;

public class ReservationUser extends GenReservationUser {
    public static final Creator<ReservationUser> CREATOR = new Creator<ReservationUser>() {
        public ReservationUser[] newArray(int size) {
            return new ReservationUser[size];
        }

        public ReservationUser createFromParcel(Parcel source) {
            ReservationUser object = new ReservationUser();
            object.readFromParcel(source);
            return object;
        }
    };
    static final int ROLE_FRIEND = 1;
    public static final int ROLE_GUEST = 0;
    static final String TYPE_GRAY_USER = "GrayUser";
    static final String TYPE_USER = "User";

    public int getRole() {
        return super.getRole();
    }

    public void setRole(int value) {
        super.setRole(value);
    }

    public String getTripMemberType() {
        return super.getTripMemberType();
    }

    public String toString() {
        return getId() + " " + getEmail() + " " + getName();
    }
}
