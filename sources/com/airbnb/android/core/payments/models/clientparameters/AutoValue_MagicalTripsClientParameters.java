package com.airbnb.android.core.payments.models.clientparameters;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.TripSecondaryGuest;
import com.airbnb.android.core.payments.models.BillProductType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

final class AutoValue_MagicalTripsClientParameters extends C$AutoValue_MagicalTripsClientParameters {
    public static final Creator<AutoValue_MagicalTripsClientParameters> CREATOR = new Creator<AutoValue_MagicalTripsClientParameters>() {
        public AutoValue_MagicalTripsClientParameters createFromParcel(Parcel in) {
            Long l;
            BillProductType valueOf = BillProductType.valueOf(in.readString());
            long readLong = in.readLong();
            int readInt = in.readInt();
            ArrayList readArrayList = in.readArrayList(TripSecondaryGuest.class.getClassLoader());
            HashMap readHashMap = in.readHashMap(String.class.getClassLoader());
            if (in.readInt() == 0) {
                l = Long.valueOf(in.readLong());
            } else {
                l = null;
            }
            return new AutoValue_MagicalTripsClientParameters(valueOf, readLong, readInt, readArrayList, readHashMap, l);
        }

        public AutoValue_MagicalTripsClientParameters[] newArray(int size) {
            return new AutoValue_MagicalTripsClientParameters[size];
        }
    };

    AutoValue_MagicalTripsClientParameters(BillProductType productType, long templateId, int guestCount, ArrayList<TripSecondaryGuest> secondaryGuests, Map<String, String> guestAddress, Long travelPurpose) {
        super(productType, templateId, guestCount, secondaryGuests, guestAddress, travelPurpose);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productType().name());
        dest.writeLong(templateId());
        dest.writeInt(guestCount());
        dest.writeList(secondaryGuests());
        dest.writeMap(guestAddress());
        if (travelPurpose() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeLong(travelPurpose().longValue());
    }

    public int describeContents() {
        return 0;
    }
}
