package com.airbnb.android.lib.fragments.inbox.saved_messages;

import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Predicate;

final /* synthetic */ class SavedMessagesAdapter$$Lambda$1 implements Predicate {
    private final long arg$1;

    private SavedMessagesAdapter$$Lambda$1(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new SavedMessagesAdapter$$Lambda$1(j);
    }

    public boolean apply(Object obj) {
        return SavedMessagesAdapter.lambda$deleteSavedMessage$0(this.arg$1, (EpoxyModel) obj);
    }
}
