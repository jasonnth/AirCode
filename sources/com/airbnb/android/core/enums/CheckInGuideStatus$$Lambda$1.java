package com.airbnb.android.core.enums;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;

final /* synthetic */ class CheckInGuideStatus$$Lambda$1 implements Predicate {
    private final Integer arg$1;

    private CheckInGuideStatus$$Lambda$1(Integer num) {
        this.arg$1 = num;
    }

    public static Predicate lambdaFactory$(Integer num) {
        return new CheckInGuideStatus$$Lambda$1(num);
    }

    public boolean apply(Object obj) {
        return Objects.equal(((CheckInGuideStatus) obj).serverKey, this.arg$1);
    }
}
