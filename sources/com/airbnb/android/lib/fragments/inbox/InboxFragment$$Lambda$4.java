package com.airbnb.android.lib.fragments.inbox;

import p032rx.functions.Action1;

final /* synthetic */ class InboxFragment$$Lambda$4 implements Action1 {
    private final InboxFragment arg$1;

    private InboxFragment$$Lambda$4(InboxFragment inboxFragment) {
        this.arg$1 = inboxFragment;
    }

    public static Action1 lambdaFactory$(InboxFragment inboxFragment) {
        return new InboxFragment$$Lambda$4(inboxFragment);
    }

    public void call(Object obj) {
        InboxFragment.lambda$new$3(this.arg$1, (Boolean) obj);
    }
}
