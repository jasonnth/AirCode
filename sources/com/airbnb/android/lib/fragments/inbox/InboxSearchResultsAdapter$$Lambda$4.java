package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.google.common.base.Function;

final /* synthetic */ class InboxSearchResultsAdapter$$Lambda$4 implements Function {
    private final InboxSearchResultsAdapter arg$1;

    private InboxSearchResultsAdapter$$Lambda$4(InboxSearchResultsAdapter inboxSearchResultsAdapter) {
        this.arg$1 = inboxSearchResultsAdapter;
    }

    public static Function lambdaFactory$(InboxSearchResultsAdapter inboxSearchResultsAdapter) {
        return new InboxSearchResultsAdapter$$Lambda$4(inboxSearchResultsAdapter);
    }

    public Object apply(Object obj) {
        return new StandardRowEpoxyModel_().title((CharSequence) this.arg$1.context.getString(C0880R.string.inbox_search_recent_search_quoted, new Object[]{(String) obj})).clickListener(InboxSearchResultsAdapter$$Lambda$5.lambdaFactory$(this.arg$1, (String) obj));
    }
}
