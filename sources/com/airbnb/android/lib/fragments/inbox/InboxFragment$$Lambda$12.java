package com.airbnb.android.lib.fragments.inbox;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InboxFragment$$Lambda$12 implements OnClickListener {
    private final InboxFragment arg$1;

    private InboxFragment$$Lambda$12(InboxFragment inboxFragment) {
        this.arg$1 = inboxFragment;
    }

    public static OnClickListener lambdaFactory$(InboxFragment inboxFragment) {
        return new InboxFragment$$Lambda$12(inboxFragment);
    }

    public void onClick(View view) {
        this.arg$1.startInboxSearch();
    }
}
