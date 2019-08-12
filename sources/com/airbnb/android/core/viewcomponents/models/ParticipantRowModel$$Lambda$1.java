package com.airbnb.android.core.viewcomponents.models;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.User;

final /* synthetic */ class ParticipantRowModel$$Lambda$1 implements OnClickListener {
    private final User arg$1;

    private ParticipantRowModel$$Lambda$1(User user) {
        this.arg$1 = user;
    }

    public static OnClickListener lambdaFactory$(User user) {
        return new ParticipantRowModel$$Lambda$1(user);
    }

    public void onClick(View view) {
        view.getContext().startActivity(UserProfileIntents.intentForUser(view.getContext(), this.arg$1));
    }
}
