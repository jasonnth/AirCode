package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.viewcomponents.viewmodels.MessageImageEpoxyModel_;

final /* synthetic */ class ThreadAdapter$$Lambda$5 implements OnClickListener {
    private final ThreadAdapter arg$1;
    private final MessageImageEpoxyModel_ arg$2;

    private ThreadAdapter$$Lambda$5(ThreadAdapter threadAdapter, MessageImageEpoxyModel_ messageImageEpoxyModel_) {
        this.arg$1 = threadAdapter;
        this.arg$2 = messageImageEpoxyModel_;
    }

    public static OnClickListener lambdaFactory$(ThreadAdapter threadAdapter, MessageImageEpoxyModel_ messageImageEpoxyModel_) {
        return new ThreadAdapter$$Lambda$5(threadAdapter, messageImageEpoxyModel_);
    }

    public void onClick(View view) {
        ThreadAdapter.lambda$getMessageImage$3(this.arg$1, this.arg$2, view);
    }
}
