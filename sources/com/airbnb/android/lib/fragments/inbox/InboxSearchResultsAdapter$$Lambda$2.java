package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.models.InboxSearchResult;
import com.airbnb.android.utils.ListUtils.PositionalTransformer;

final /* synthetic */ class InboxSearchResultsAdapter$$Lambda$2 implements PositionalTransformer {
    private final InboxSearchResultsAdapter arg$1;

    private InboxSearchResultsAdapter$$Lambda$2(InboxSearchResultsAdapter inboxSearchResultsAdapter) {
        this.arg$1 = inboxSearchResultsAdapter;
    }

    public static PositionalTransformer lambdaFactory$(InboxSearchResultsAdapter inboxSearchResultsAdapter) {
        return new InboxSearchResultsAdapter$$Lambda$2(inboxSearchResultsAdapter);
    }

    public Object transform(int i, Object obj) {
        return this.arg$1.generateModel(i, (InboxSearchResult) obj);
    }
}
