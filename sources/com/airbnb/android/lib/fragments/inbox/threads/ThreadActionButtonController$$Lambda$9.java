package com.airbnb.android.lib.fragments.inbox.threads;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ThreadActionButtonController$$Lambda$9 implements OnClickListener {
    private final Runnable arg$1;

    private ThreadActionButtonController$$Lambda$9(Runnable runnable) {
        this.arg$1 = runnable;
    }

    public static OnClickListener lambdaFactory$(Runnable runnable) {
        return new ThreadActionButtonController$$Lambda$9(runnable);
    }

    public void onClick(View view) {
        this.arg$1.run();
    }
}
