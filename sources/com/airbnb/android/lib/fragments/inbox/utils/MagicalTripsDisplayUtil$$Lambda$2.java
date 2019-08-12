package com.airbnb.android.lib.fragments.inbox.utils;

import com.airbnb.android.core.models.User;
import com.google.common.base.Predicate;

final /* synthetic */ class MagicalTripsDisplayUtil$$Lambda$2 implements Predicate {
    private final User arg$1;

    private MagicalTripsDisplayUtil$$Lambda$2(User user) {
        this.arg$1 = user;
    }

    public static Predicate lambdaFactory$(User user) {
        return new MagicalTripsDisplayUtil$$Lambda$2(user);
    }

    public boolean apply(Object obj) {
        return MagicalTripsDisplayUtil.lambda$getOrderedUsers$0(this.arg$1, (Long) obj);
    }
}
