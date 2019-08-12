package com.airbnb.android.core.models;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.BugsnagWrapper;

public enum ReviewRole implements Parcelable {
    Host(TripRole.ROLE_KEY_HOST),
    Guest(TripRole.ROLE_KEY_GUEST),
    Unknown("");
    
    public static final Creator<ReviewRole> CREATOR = null;
    public final String apiString;

    static {
        CREATOR = new Creator<ReviewRole>() {
            public ReviewRole createFromParcel(Parcel source) {
                return ReviewRole.values()[source.readInt()];
            }

            public ReviewRole[] newArray(int size) {
                return new ReviewRole[size];
            }
        };
    }

    private ReviewRole(String role) {
        this.apiString = role;
    }

    @SuppressLint({"DefaultLocale"})
    public static ReviewRole findRole(String roleString) {
        ReviewRole[] values;
        for (ReviewRole role : values()) {
            if (role.apiString.equalsIgnoreCase(roleString)) {
                return role;
            }
        }
        BugsnagWrapper.notify((Throwable) new IllegalStateException("ReviewRole returned Unknown for string " + roleString));
        return Unknown;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
