package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.UserProfileIntents;

final /* synthetic */ class ThreadAdapter$$Lambda$3 implements OnClickListener {
    private final ThreadAdapter arg$1;

    private ThreadAdapter$$Lambda$3(ThreadAdapter threadAdapter) {
        this.arg$1 = threadAdapter;
    }

    public static OnClickListener lambdaFactory$(ThreadAdapter threadAdapter) {
        return new ThreadAdapter$$Lambda$3(threadAdapter);
    }

    public void onClick(View view) {
        this.arg$1.context.startActivity(UserProfileIntents.intentForCurrentUser(this.arg$1.context));
    }
}
