package com.airbnb.android.lib.fragments.inbox;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Thread;

final /* synthetic */ class InboxFragment$$Lambda$15 implements OnClickListener {
    private final InboxFragment arg$1;
    private final Thread arg$2;

    private InboxFragment$$Lambda$15(InboxFragment inboxFragment, Thread thread) {
        this.arg$1 = inboxFragment;
        this.arg$2 = thread;
    }

    public static OnClickListener lambdaFactory$(InboxFragment inboxFragment, Thread thread) {
        return new InboxFragment$$Lambda$15(inboxFragment, thread);
    }

    public void onClick(View view) {
        InboxFragment.lambda$toggleArchiveConfirmationSnackBar$10(this.arg$1, this.arg$2, view);
    }
}
