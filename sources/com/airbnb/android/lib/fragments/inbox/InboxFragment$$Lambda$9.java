package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.models.DashboardAlert;
import com.google.common.base.Predicate;

final /* synthetic */ class InboxFragment$$Lambda$9 implements Predicate {
    private static final InboxFragment$$Lambda$9 instance = new InboxFragment$$Lambda$9();

    private InboxFragment$$Lambda$9() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return InboxFragment.lambda$updateAlertsBadge$5((DashboardAlert) obj);
    }
}
