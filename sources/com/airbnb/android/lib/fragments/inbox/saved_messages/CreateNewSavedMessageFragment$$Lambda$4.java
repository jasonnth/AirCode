package com.airbnb.android.lib.fragments.inbox.saved_messages;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class CreateNewSavedMessageFragment$$Lambda$4 implements Action1 {
    private final CreateNewSavedMessageFragment arg$1;

    private CreateNewSavedMessageFragment$$Lambda$4(CreateNewSavedMessageFragment createNewSavedMessageFragment) {
        this.arg$1 = createNewSavedMessageFragment;
    }

    public static Action1 lambdaFactory$(CreateNewSavedMessageFragment createNewSavedMessageFragment) {
        return new CreateNewSavedMessageFragment$$Lambda$4(createNewSavedMessageFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
