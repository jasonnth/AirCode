package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.LoginActivityIntents;

final /* synthetic */ class InboxContainerFragment$$Lambda$2 implements OnClickListener {
    private final InboxContainerFragment arg$1;

    private InboxContainerFragment$$Lambda$2(InboxContainerFragment inboxContainerFragment) {
        this.arg$1 = inboxContainerFragment;
    }

    public static OnClickListener lambdaFactory$(InboxContainerFragment inboxContainerFragment) {
        return new InboxContainerFragment$$Lambda$2(inboxContainerFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(LoginActivityIntents.intent(this.arg$1.getActivity()));
    }
}
