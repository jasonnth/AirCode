package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.models.Thread;
import com.google.common.base.Function;

final /* synthetic */ class InboxAdapter$$Lambda$2 implements Function {
    private final InboxAdapter arg$1;

    private InboxAdapter$$Lambda$2(InboxAdapter inboxAdapter) {
        this.arg$1 = inboxAdapter;
    }

    public static Function lambdaFactory$(InboxAdapter inboxAdapter) {
        return new InboxAdapter$$Lambda$2(inboxAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.threadToModel((Thread) obj);
    }
}
