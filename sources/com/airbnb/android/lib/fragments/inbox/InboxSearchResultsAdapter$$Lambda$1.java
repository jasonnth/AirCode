package com.airbnb.android.lib.fragments.inbox;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class InboxSearchResultsAdapter$$Lambda$1 implements OnEditorActionListener {
    private final InboxSearchResultsAdapter arg$1;

    private InboxSearchResultsAdapter$$Lambda$1(InboxSearchResultsAdapter inboxSearchResultsAdapter) {
        this.arg$1 = inboxSearchResultsAdapter;
    }

    public static OnEditorActionListener lambdaFactory$(InboxSearchResultsAdapter inboxSearchResultsAdapter) {
        return new InboxSearchResultsAdapter$$Lambda$1(inboxSearchResultsAdapter);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return InboxSearchResultsAdapter.lambda$setupInputMarquee$0(this.arg$1, textView, i, keyEvent);
    }
}
