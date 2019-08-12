package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.models.Thread;
import com.google.common.base.Predicate;

final /* synthetic */ class InboxFragment$$Lambda$16 implements Predicate {
    private static final InboxFragment$$Lambda$16 instance = new InboxFragment$$Lambda$16();

    private InboxFragment$$Lambda$16() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return InboxFragment.lambda$null$11((Thread) obj);
    }
}
