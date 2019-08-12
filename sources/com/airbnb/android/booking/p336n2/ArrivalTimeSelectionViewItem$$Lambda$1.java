package com.airbnb.android.booking.p336n2;

import com.airbnb.android.core.models.CheckinTimeSelectionOptions;
import com.google.common.base.Function;

/* renamed from: com.airbnb.android.booking.n2.ArrivalTimeSelectionViewItem$$Lambda$1 */
final /* synthetic */ class ArrivalTimeSelectionViewItem$$Lambda$1 implements Function {
    private static final ArrivalTimeSelectionViewItem$$Lambda$1 instance = new ArrivalTimeSelectionViewItem$$Lambda$1();

    private ArrivalTimeSelectionViewItem$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ArrivalTimeSelectionViewItem.access$lambda$0((CheckinTimeSelectionOptions) obj);
    }
}
