package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.models.Thread;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Predicate;
import java.util.Iterator;

final /* synthetic */ class ThreadPreviewModelFactory$$Lambda$3 implements Predicate {
    private final Iterator arg$1;

    private ThreadPreviewModelFactory$$Lambda$3(Iterator it) {
        this.arg$1 = it;
    }

    public static Predicate lambdaFactory$(Iterator it) {
        return new ThreadPreviewModelFactory$$Lambda$3(it);
    }

    public boolean apply(Object obj) {
        return ThreadPreviewModelFactory.doesModelMatchThreadAndLastUpdated((EpoxyModel) obj, (Thread) this.arg$1.next());
    }
}
