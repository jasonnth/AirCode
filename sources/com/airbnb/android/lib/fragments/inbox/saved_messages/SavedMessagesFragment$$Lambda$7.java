package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class SavedMessagesFragment$$Lambda$7 implements OnRefreshListener {
    private final SavedMessagesFragment arg$1;

    private SavedMessagesFragment$$Lambda$7(SavedMessagesFragment savedMessagesFragment) {
        this.arg$1 = savedMessagesFragment;
    }

    public static OnRefreshListener lambdaFactory$(SavedMessagesFragment savedMessagesFragment) {
        return new SavedMessagesFragment$$Lambda$7(savedMessagesFragment);
    }

    public void onRefresh() {
        SavedMessagesFragment.lambda$onCreateView$6(this.arg$1);
    }
}
