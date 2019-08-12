package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenNestedBusyDetail;

public class NestedBusyDetail extends GenNestedBusyDetail {
    public static final Creator<NestedBusyDetail> CREATOR = new Creator<NestedBusyDetail>() {
        public NestedBusyDetail[] newArray(int size) {
            return new NestedBusyDetail[size];
        }

        public NestedBusyDetail createFromParcel(Parcel source) {
            NestedBusyDetail object = new NestedBusyDetail();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String NESTED_BUSY_TYPE_EXTERNAL_CALENDAR = "external_calendar";
    public static final String NESTED_BUSY_TYPE_RESERVATION = "reservation";
    public static final String NESTED_BUSY_TYPE_TURNOVER_DAYS = "turnover_days";

    public static boolean isSameNestedBusyEvent(NestedBusyDetail nestedBusy1, NestedBusyDetail nestedBusy2) {
        if (!nestedBusy1.getType().equals(nestedBusy2.getType())) {
            return false;
        }
        if (nestedBusy1.getReservationId() != null && nestedBusy2.getReservationId() != null) {
            return nestedBusy1.getReservationId().equals(nestedBusy2.getReservationId());
        }
        if (nestedBusy1.getNestedListing().getId() == nestedBusy2.getNestedListing().getId()) {
            return true;
        }
        return false;
    }
}
