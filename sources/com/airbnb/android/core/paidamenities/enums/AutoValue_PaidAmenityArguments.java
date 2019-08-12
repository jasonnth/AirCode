package com.airbnb.android.core.paidamenities.enums;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_PaidAmenityArguments extends C$AutoValue_PaidAmenityArguments {
    public static final Creator<AutoValue_PaidAmenityArguments> CREATOR = new Creator<AutoValue_PaidAmenityArguments>() {
        public AutoValue_PaidAmenityArguments createFromParcel(Parcel in) {
            boolean z = true;
            long readLong = in.readLong();
            long readLong2 = in.readLong();
            String readString = in.readString();
            if (in.readInt() != 1) {
                z = false;
            }
            return new AutoValue_PaidAmenityArguments(readLong, readLong2, readString, z);
        }

        public AutoValue_PaidAmenityArguments[] newArray(int size) {
            return new AutoValue_PaidAmenityArguments[size];
        }
    };

    AutoValue_PaidAmenityArguments(long threadId, long listingId, String confirmationCode, boolean hasPaidAmenityOrders) {
        super(threadId, listingId, confirmationCode, hasPaidAmenityOrders);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(threadId());
        dest.writeLong(listingId());
        dest.writeString(confirmationCode());
        dest.writeInt(hasPaidAmenityOrders() ? 1 : 0);
    }

    public int describeContents() {
        return 0;
    }
}
