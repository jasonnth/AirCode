package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.responses.InboxResponse;
import p032rx.functions.Action1;

final /* synthetic */ class InboxFragment$$Lambda$5 implements Action1 {
    private final InboxFragment arg$1;

    private InboxFragment$$Lambda$5(InboxFragment inboxFragment) {
        this.arg$1 = inboxFragment;
    }

    public static Action1 lambdaFactory$(InboxFragment inboxFragment) {
        return new InboxFragment$$Lambda$5(inboxFragment);
    }

    public void call(Object obj) {
        InboxFragment.lambda$new$12(this.arg$1, (InboxResponse) obj);
    }
}
