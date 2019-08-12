package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReservationSummary;
import com.airbnb.android.core.paidamenities.enums.PaidAmenityOrderStatus;
import com.google.common.collect.FluentIterable;
import java.util.EnumSet;

public class ReservationSummary extends GenReservationSummary {
    public static final Creator<ReservationSummary> CREATOR = new Creator<ReservationSummary>() {
        public ReservationSummary[] newArray(int size) {
            return new ReservationSummary[size];
        }

        public ReservationSummary createFromParcel(Parcel source) {
            ReservationSummary object = new ReservationSummary();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean hasPaidAmenityOrders() {
        return this.mPaidAmenityOrders != null && !this.mPaidAmenityOrders.isEmpty();
    }

    public boolean hasPaidAmenityOrderWithStatus(EnumSet<PaidAmenityOrderStatus> statuses) {
        if (hasPaidAmenityOrders()) {
            return FluentIterable.from((Iterable<E>) this.mPaidAmenityOrders).anyMatch(ReservationSummary$$Lambda$1.lambdaFactory$(statuses));
        }
        return false;
    }
}
