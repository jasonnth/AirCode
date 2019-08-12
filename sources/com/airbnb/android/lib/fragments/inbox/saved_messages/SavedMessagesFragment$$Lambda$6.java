package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SavedMessagesFragment$$Lambda$6 implements OnClickListener {
    private final SavedMessagesFragment arg$1;

    private SavedMessagesFragment$$Lambda$6(SavedMessagesFragment savedMessagesFragment) {
        this.arg$1 = savedMessagesFragment;
    }

    public static OnClickListener lambdaFactory$(SavedMessagesFragment savedMessagesFragment) {
        return new SavedMessagesFragment$$Lambda$6(savedMessagesFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivityForResult(CreateNewSavedMessageActivity.newIntent(this.arg$1.getContext(), null, this.arg$1.threadId), 144);
    }
}
