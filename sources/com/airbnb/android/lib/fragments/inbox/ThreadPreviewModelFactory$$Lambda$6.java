package com.airbnb.android.lib.fragments.inbox;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Thread;

final /* synthetic */ class ThreadPreviewModelFactory$$Lambda$6 implements OnClickListener {
    private final ThreadClickListener arg$1;
    private final Thread arg$2;

    private ThreadPreviewModelFactory$$Lambda$6(ThreadClickListener threadClickListener, Thread thread) {
        this.arg$1 = threadClickListener;
        this.arg$2 = thread;
    }

    public static OnClickListener lambdaFactory$(ThreadClickListener threadClickListener, Thread thread) {
        return new ThreadPreviewModelFactory$$Lambda$6(threadClickListener, thread);
    }

    public void onClick(View view) {
        this.arg$1.onReviewButtonClick(this.arg$2);
    }
}
