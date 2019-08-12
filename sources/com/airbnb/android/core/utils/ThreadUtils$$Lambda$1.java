package com.airbnb.android.core.utils;

import com.airbnb.android.core.models.User;
import com.google.common.base.Predicate;
import java.util.List;

final /* synthetic */ class ThreadUtils$$Lambda$1 implements Predicate {
    private final List arg$1;

    private ThreadUtils$$Lambda$1(List list) {
        this.arg$1 = list;
    }

    public static Predicate lambdaFactory$(List list) {
        return new ThreadUtils$$Lambda$1(list);
    }

    public boolean apply(Object obj) {
        return ThreadUtils.lambda$generateGuestNamesString$0(this.arg$1, (User) obj);
    }
}
