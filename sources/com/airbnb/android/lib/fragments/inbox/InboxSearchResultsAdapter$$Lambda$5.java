package com.airbnb.android.lib.fragments.inbox;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InboxSearchResultsAdapter$$Lambda$5 implements OnClickListener {
    private final InboxSearchResultsAdapter arg$1;
    private final String arg$2;

    private InboxSearchResultsAdapter$$Lambda$5(InboxSearchResultsAdapter inboxSearchResultsAdapter, String str) {
        this.arg$1 = inboxSearchResultsAdapter;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(InboxSearchResultsAdapter inboxSearchResultsAdapter, String str) {
        return new InboxSearchResultsAdapter$$Lambda$5(inboxSearchResultsAdapter, str);
    }

    public void onClick(View view) {
        InboxSearchResultsAdapter.lambda$null$2(this.arg$1, this.arg$2, view);
    }
}
