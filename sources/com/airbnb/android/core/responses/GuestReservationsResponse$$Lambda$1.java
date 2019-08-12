package com.airbnb.android.core.responses;

import android.text.TextUtils;
import com.airbnb.android.core.models.Reservation;
import com.google.common.base.Predicate;

final /* synthetic */ class GuestReservationsResponse$$Lambda$1 implements Predicate {
    private final String arg$1;

    private GuestReservationsResponse$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new GuestReservationsResponse$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return TextUtils.equals(this.arg$1, ((Reservation) obj).getListing().getCity());
    }
}
