package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.viewcomponents.models.MessageItemEpoxyModel_;

final /* synthetic */ class ThreadAdapter$$Lambda$11 implements OnClickListener {
    private final ThreadAdapter arg$1;
    private final MessageItemEpoxyModel_ arg$2;

    private ThreadAdapter$$Lambda$11(ThreadAdapter threadAdapter, MessageItemEpoxyModel_ messageItemEpoxyModel_) {
        this.arg$1 = threadAdapter;
        this.arg$2 = messageItemEpoxyModel_;
    }

    public static OnClickListener lambdaFactory$(ThreadAdapter threadAdapter, MessageItemEpoxyModel_ messageItemEpoxyModel_) {
        return new ThreadAdapter$$Lambda$11(threadAdapter, messageItemEpoxyModel_);
    }

    public void onClick(View view) {
        ThreadAdapter.lambda$setUpMessageItem$9(this.arg$1, this.arg$2, view);
    }
}
