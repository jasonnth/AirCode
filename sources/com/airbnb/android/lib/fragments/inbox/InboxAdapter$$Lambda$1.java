package com.airbnb.android.lib.fragments.inbox;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InboxAdapter$$Lambda$1 implements OnClickListener {
    private final InboxAdapter arg$1;

    private InboxAdapter$$Lambda$1(InboxAdapter inboxAdapter) {
        this.arg$1 = inboxAdapter;
    }

    public static OnClickListener lambdaFactory$(InboxAdapter inboxAdapter) {
        return new InboxAdapter$$Lambda$1(inboxAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.onSuperHeroClick();
    }
}
