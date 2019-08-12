package com.airbnb.android.lib.fragments.inbox;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;

final /* synthetic */ class InboxFragment$$Lambda$10 implements OnClickListener {
    private final InboxFragment arg$1;

    private InboxFragment$$Lambda$10(InboxFragment inboxFragment) {
        this.arg$1 = inboxFragment;
    }

    public static OnClickListener lambdaFactory$(InboxFragment inboxFragment) {
        return new InboxFragment$$Lambda$10(inboxFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(HomeActivityIntents.intentForGuestHome(this.arg$1.getActivity()));
    }
}
