package com.airbnb.android.lib.fragments.inbox;

import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class InboxFragment$$Lambda$11 implements OnRefreshListener {
    private final InboxFragment arg$1;

    private InboxFragment$$Lambda$11(InboxFragment inboxFragment) {
        this.arg$1 = inboxFragment;
    }

    public static OnRefreshListener lambdaFactory$(InboxFragment inboxFragment) {
        return new InboxFragment$$Lambda$11(inboxFragment);
    }

    public void onRefresh() {
        InboxFragment.lambda$onCreateView$7(this.arg$1);
    }
}
