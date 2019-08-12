package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.User;

final /* synthetic */ class ThreadAdapter$$Lambda$9 implements OnClickListener {
    private final ThreadAdapter arg$1;
    private final User arg$2;

    private ThreadAdapter$$Lambda$9(ThreadAdapter threadAdapter, User user) {
        this.arg$1 = threadAdapter;
        this.arg$2 = user;
    }

    public static OnClickListener lambdaFactory$(ThreadAdapter threadAdapter, User user) {
        return new ThreadAdapter$$Lambda$9(threadAdapter, user);
    }

    public void onClick(View view) {
        this.arg$1.context.startActivity(UserProfileIntents.intentForUserAndReservationStatus(this.arg$1.context, this.arg$2, this.arg$1.thread.getReservationStatus()));
    }
}
