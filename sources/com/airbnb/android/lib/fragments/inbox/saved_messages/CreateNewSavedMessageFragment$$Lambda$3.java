package com.airbnb.android.lib.fragments.inbox.saved_messages;

import com.airbnb.android.core.responses.UpdateCannedMessageResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CreateNewSavedMessageFragment$$Lambda$3 implements Action1 {
    private final CreateNewSavedMessageFragment arg$1;

    private CreateNewSavedMessageFragment$$Lambda$3(CreateNewSavedMessageFragment createNewSavedMessageFragment) {
        this.arg$1 = createNewSavedMessageFragment;
    }

    public static Action1 lambdaFactory$(CreateNewSavedMessageFragment createNewSavedMessageFragment) {
        return new CreateNewSavedMessageFragment$$Lambda$3(createNewSavedMessageFragment);
    }

    public void call(Object obj) {
        CreateNewSavedMessageFragment.lambda$new$2(this.arg$1, (UpdateCannedMessageResponse) obj);
    }
}
