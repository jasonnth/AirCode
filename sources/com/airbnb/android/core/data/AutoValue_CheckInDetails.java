package com.airbnb.android.core.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_CheckInDetails extends C$AutoValue_CheckInDetails {
    public static final Creator<AutoValue_CheckInDetails> CREATOR = new Creator<AutoValue_CheckInDetails>() {
        public AutoValue_CheckInDetails createFromParcel(Parcel in) {
            String str;
            String str2;
            String str3 = null;
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            if (in.readInt() == 0) {
                str2 = in.readString();
            } else {
                str2 = null;
            }
            if (in.readInt() == 0) {
                str3 = in.readString();
            }
            return new AutoValue_CheckInDetails(str, str2, str3);
        }

        public AutoValue_CheckInDetails[] newArray(int size) {
            return new AutoValue_CheckInDetails[size];
        }
    };

    AutoValue_CheckInDetails(String checkIn, String latestCheckIn, String checkOut) {
        super(checkIn, latestCheckIn, checkOut);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (checkIn() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(checkIn());
        }
        if (latestCheckIn() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(latestCheckIn());
        }
        if (checkOut() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(checkOut());
    }

    public int describeContents() {
        return 0;
    }
}
