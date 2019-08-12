package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;

final /* synthetic */ class InboxContainerFragment$$Lambda$1 implements OnClickListener {
    private final InboxContainerFragment arg$1;

    private InboxContainerFragment$$Lambda$1(InboxContainerFragment inboxContainerFragment) {
        this.arg$1 = inboxContainerFragment;
    }

    public static OnClickListener lambdaFactory$(InboxContainerFragment inboxContainerFragment) {
        return new InboxContainerFragment$$Lambda$1(inboxContainerFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(HomeActivityIntents.intentForGuestHome(this.arg$1.getActivity()));
    }
}
