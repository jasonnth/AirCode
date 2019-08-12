package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.User;

final /* synthetic */ class ContactHostFragment$$Lambda$2 implements OnClickListener {
    private final ContactHostFragment arg$1;
    private final User arg$2;

    private ContactHostFragment$$Lambda$2(ContactHostFragment contactHostFragment, User user) {
        this.arg$1 = contactHostFragment;
        this.arg$2 = user;
    }

    public static OnClickListener lambdaFactory$(ContactHostFragment contactHostFragment, User user) {
        return new ContactHostFragment$$Lambda$2(contactHostFragment, user);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(UserProfileIntents.intentForUserId(this.arg$1.getContext(), this.arg$2.getId()));
    }
}
