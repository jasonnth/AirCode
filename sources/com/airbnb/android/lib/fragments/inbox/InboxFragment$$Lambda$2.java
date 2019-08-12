package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.responses.DashboardAlertsResponse;
import java.util.ArrayList;
import p032rx.functions.Action1;

final /* synthetic */ class InboxFragment$$Lambda$2 implements Action1 {
    private final InboxFragment arg$1;

    private InboxFragment$$Lambda$2(InboxFragment inboxFragment) {
        this.arg$1 = inboxFragment;
    }

    public static Action1 lambdaFactory$(InboxFragment inboxFragment) {
        return new InboxFragment$$Lambda$2(inboxFragment);
    }

    public void call(Object obj) {
        this.arg$1.setAlerts(new ArrayList(((DashboardAlertsResponse) obj).dashboardAlerts));
    }
}
