package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.TripSecondaryGuest;
import java.util.ArrayList;
import java.util.List;

final class AutoValue_TripsProductParam extends C$AutoValue_TripsProductParam {
    public static final Creator<AutoValue_TripsProductParam> CREATOR = new Creator<AutoValue_TripsProductParam>() {
        public AutoValue_TripsProductParam createFromParcel(Parcel in) {
            String str;
            String readString = in.readString();
            long readLong = in.readLong();
            int readInt = in.readInt();
            ArrayList readArrayList = in.readArrayList(TripSecondaryGuest.class.getClassLoader());
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            return new AutoValue_TripsProductParam(readString, readLong, readInt, readArrayList, str);
        }

        public AutoValue_TripsProductParam[] newArray(int size) {
            return new AutoValue_TripsProductParam[size];
        }
    };

    AutoValue_TripsProductParam(String productType, long templateId, int numberOfGuests, List<TripSecondaryGuest> secondaryGuestInfos, String couponCode) {
        super(productType, templateId, numberOfGuests, secondaryGuestInfos, couponCode);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productType());
        dest.writeLong(templateId());
        dest.writeInt(numberOfGuests());
        dest.writeList(secondaryGuestInfos());
        if (couponCode() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(couponCode());
    }

    public int describeContents() {
        return 0;
    }
}
