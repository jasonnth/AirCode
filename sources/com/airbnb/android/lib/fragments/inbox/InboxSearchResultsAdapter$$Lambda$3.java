package com.airbnb.android.lib.fragments.inbox;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InboxSearchResultsAdapter$$Lambda$3 implements OnClickListener {
    private final InboxSearchResultsAdapter arg$1;

    private InboxSearchResultsAdapter$$Lambda$3(InboxSearchResultsAdapter inboxSearchResultsAdapter) {
        this.arg$1 = inboxSearchResultsAdapter;
    }

    public static OnClickListener lambdaFactory$(InboxSearchResultsAdapter inboxSearchResultsAdapter) {
        return new InboxSearchResultsAdapter$$Lambda$3(inboxSearchResultsAdapter);
    }

    public void onClick(View view) {
        this.arg$1.inboxSearchListener.onClickPendingFilter();
    }
}
