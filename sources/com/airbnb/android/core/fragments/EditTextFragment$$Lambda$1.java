package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.UserProfileIntents;

final /* synthetic */ class EditTextFragment$$Lambda$1 implements OnClickListener {
    private final EditTextFragment arg$1;

    private EditTextFragment$$Lambda$1(EditTextFragment editTextFragment) {
        this.arg$1 = editTextFragment;
    }

    public static OnClickListener lambdaFactory$(EditTextFragment editTextFragment) {
        return new EditTextFragment$$Lambda$1(editTextFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(UserProfileIntents.intentForUserId(this.arg$1.getContext(), this.arg$1.user.getId()));
    }
}
