package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CreateNewSavedMessageFragment$$Lambda$5 implements OnClickListener {
    private final CreateNewSavedMessageFragment arg$1;

    private CreateNewSavedMessageFragment$$Lambda$5(CreateNewSavedMessageFragment createNewSavedMessageFragment) {
        this.arg$1 = createNewSavedMessageFragment;
    }

    public static OnClickListener lambdaFactory$(CreateNewSavedMessageFragment createNewSavedMessageFragment) {
        return new CreateNewSavedMessageFragment$$Lambda$5(createNewSavedMessageFragment);
    }

    public void onClick(View view) {
        ((CreateNewSavedMessageActivity) this.arg$1.getActivity()).showFragment(WriteSavedMessageBodyFragment.newInstance(this.arg$1.editTitleText.getText().toString(), this.arg$1.messageBody, this.arg$1.messageId, this.arg$1.threadId));
    }
}
