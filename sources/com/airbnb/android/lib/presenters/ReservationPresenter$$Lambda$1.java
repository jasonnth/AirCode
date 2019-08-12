package com.airbnb.android.lib.presenters;

import com.airbnb.android.core.models.Reservation;
import com.google.common.base.Predicate;
import java.util.List;

final /* synthetic */ class ReservationPresenter$$Lambda$1 implements Predicate {
    private final List arg$1;

    private ReservationPresenter$$Lambda$1(List list) {
        this.arg$1 = list;
    }

    public static Predicate lambdaFactory$(List list) {
        return new ReservationPresenter$$Lambda$1(list);
    }

    public boolean apply(Object obj) {
        return ReservationPresenter.lambda$filterBySelectedListings$0(this.arg$1, (Reservation) obj);
    }
}
