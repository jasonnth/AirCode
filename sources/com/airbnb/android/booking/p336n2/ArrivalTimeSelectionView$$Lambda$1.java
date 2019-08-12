package com.airbnb.android.booking.p336n2;

import com.google.common.base.Predicate;

/* renamed from: com.airbnb.android.booking.n2.ArrivalTimeSelectionView$$Lambda$1 */
final /* synthetic */ class ArrivalTimeSelectionView$$Lambda$1 implements Predicate {
    private final String arg$1;

    private ArrivalTimeSelectionView$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new ArrivalTimeSelectionView$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((ArrivalTimeSelectionViewItem) obj).getValue().equalsIgnoreCase(this.arg$1);
    }
}
