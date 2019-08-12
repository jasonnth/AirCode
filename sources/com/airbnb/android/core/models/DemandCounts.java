package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.generated.GenDemandCounts;

public class DemandCounts extends GenDemandCounts {
    public static final Creator<DemandCounts> CREATOR = new Creator<DemandCounts>() {
        public DemandCounts[] newArray(int size) {
            return new DemandCounts[size];
        }

        public DemandCounts createFromParcel(Parcel source) {
            DemandCounts object = new DemandCounts();
            object.readFromParcel(source);
            return object;
        }
    };

    public DemandCounts() {
        setDate(AirDate.today());
        setPageViews(0);
        setBookings(0);
        setInquiries(0);
    }
}
