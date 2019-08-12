package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ThreadAdapter$$Lambda$13 implements OnClickListener {
    private final ThreadAdapter arg$1;

    private ThreadAdapter$$Lambda$13(ThreadAdapter threadAdapter) {
        this.arg$1 = threadAdapter;
    }

    public static OnClickListener lambdaFactory$(ThreadAdapter threadAdapter) {
        return new ThreadAdapter$$Lambda$13(threadAdapter);
    }

    public void onClick(View view) {
        ThreadAdapter.lambda$addTranslationButtonIfNeeded$11(this.arg$1, view);
    }
}
