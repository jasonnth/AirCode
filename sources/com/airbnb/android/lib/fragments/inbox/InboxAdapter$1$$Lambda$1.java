package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.models.Thread;
import com.airbnb.android.utils.ListUtils.Condition;
import com.airbnb.epoxy.EpoxyModel;

final /* synthetic */ class InboxAdapter$1$$Lambda$1 implements Condition {
    private final Thread arg$1;

    private InboxAdapter$1$$Lambda$1(Thread thread) {
        this.arg$1 = thread;
    }

    public static Condition lambdaFactory$(Thread thread) {
        return new InboxAdapter$1$$Lambda$1(thread);
    }

    public boolean check(Object obj) {
        return C69421.lambda$onClick$0(this.arg$1, (EpoxyModel) obj);
    }
}
